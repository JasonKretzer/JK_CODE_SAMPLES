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


/**********************
Main Server Class
Creates a new thread for each request
**********************/
public class ConnectFourDaemon
{
	public static void main(String args[]) throws IOException
	{
		ServerSocket webSocket = new ServerSocket(3045);
		int numOfConnections = 0;
		while(true)
		{
			Socket connSocket = webSocket.accept();
			numOfConnections++;
			if(numOfConnections<2)
			{
				Socket connSocket2 = webSocket.accept();
				ConnectFourServer req = new ConnectFourServer(connSocket,connSocket2);
				Thread thread = new Thread(req);
				thread.start();
				numOfConnections = 0;
			}
			
			
		}
	}

}