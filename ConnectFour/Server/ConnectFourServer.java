/*
 * Created on Mar 13, 2005
 *
 * 
 */

/**
 * @author Jason R. Kretzer
 *
 * 
 */

import java.io.*;
import java.net.*;


public class ConnectFourServer implements Runnable,ConnectFourOperations
{

	/**
	 * @param connSocket
	 */
	public static int counter = 0;
	private Socket conn = null;
	private Socket conn2 = null;
	private ServerBoard board = null;
	private boolean oneTurn = false;
	
	
	private final int NO_OP = Integer.MIN_VALUE;
	
	public ConnectFourServer(Socket connSocket, Socket connSocket2)
	{
		conn = connSocket;
		conn2 = connSocket2;
		counter++;
		board = new ServerBoard();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		// TODO Auto-generated method stub
		System.out.println(counter + " -- operating on: "+conn.getPort());
		System.out.println(counter + " -- operating on: "+conn.getLocalPort());
		System.out.println(counter + " -- operating on: "+conn2.getPort());
		System.out.println(counter + " -- operating on: "+conn2.getLocalPort());
		try
		{
			
			processRequest();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void processRequest()
	{
		try
		{
		DataInputStream inFromClient1 = new DataInputStream(conn.getInputStream());
		DataInputStream inFromClient2 = new DataInputStream(conn2.getInputStream());
		
		DataOutputStream outToClient1 = new DataOutputStream(conn.getOutputStream());
		DataOutputStream outToClient2 = new DataOutputStream(conn2.getOutputStream());
	
		//first and second move are special so, treated specially
		boolean madeFirstMove = true;
		boolean gameOver = false;
		
		
		outToClient1.writeInt(MAKE_FIRST_MOVE);
		outToClient2.writeInt(CONNECTED);
		
		//player one's turn -- first player to connect goes first
		oneTurn = true;
		
		int clientMsg = NO_OP;
		char clientColor = '\0';
		
		while(true)
		{
			if(oneTurn)
			{
				clientMsg = inFromClient1.readInt();
				clientColor = inFromClient1.readChar();
				if(updateBoard(clientMsg,clientColor))
				{
					//good move
					if(checkForWinner(clientMsg,clientColor))
					{
						//is a winner
						outToClient1.writeInt(YOU_WIN);
						outToClient2.writeInt(YOU_LOSE);
						gameOver = true;
					}
					else
					{
						//is not a winner
						if(isGameDraw())
						{
							outToClient2.writeInt(DRAW);
							outToClient1.writeInt(DRAW);
							gameOver = true;
						}
						else
						{
							oneTurn = false;
						}
					}
					
				}
				else
				{
					//illegal move
					outToClient1.writeInt(BAD_MOVE);
					continue;
				}
				
				
			}
			else
			{
				clientMsg = inFromClient2.readInt();
				clientColor = inFromClient2.readChar();
				if(updateBoard(clientMsg,clientColor))
				{
					//good move
					if(checkForWinner(clientMsg,clientColor))
					{
						//is a winner
						outToClient2.writeInt(YOU_WIN);
						outToClient1.writeInt(YOU_LOSE);
						gameOver = true;
					}
					else
					{
						//is not a winner
						if(isGameDraw())
						{
							outToClient2.writeInt(DRAW);
							outToClient1.writeInt(DRAW);
							gameOver = true;
						}
						else
						{
							oneTurn = true;
						}
						
					}
					
				}
				else
				{
					//illegal move
					outToClient2.writeInt(BAD_MOVE);
					continue;
				}
				
			}
			
			if(madeFirstMove)
			{
				outToClient2.writeInt(MAKE_SEC_MOVE);
				madeFirstMove = false;
			}
			outToClient1.writeInt(clientMsg);
			outToClient2.writeInt(clientMsg);
			
			if(gameOver)
			{
				break;
			}
			
		}		
		outToClient1.close();
		conn.close();
		outToClient2.close();
		conn2.close();
		}
		catch(Exception e)
		{
			System.out.println("Either the game is over or a problem occured.");
			
		}
		
	}

	/**
	 * @return
	 */
	private boolean isGameDraw()
	{
		return board.isDraw();
	}

	/**
	 * @param clientMsg
	 */
	private boolean checkForWinner(int clientMsg, char clientColor)
	{
		return board.isWinner(clientMsg,clientColor);
		
	}

	/**
	 * @param clientMsg
	 * @param clientColor
	 */
	private boolean updateBoard(int clientMsg, char clientColor)
	{
		return board.addToCol(clientMsg,clientColor);
		
	}

}
