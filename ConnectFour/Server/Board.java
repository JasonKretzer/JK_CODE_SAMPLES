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

//Basic board for use by client

class Board
{
	public static final char E='E';
	public static final char R='R';
	public static final char B='B';
	protected char[][] board = null;
	public Board()
	{
		board = new char[7][6];
		for(int i=0; i<6; i++)
		{
			for(int w=0; w<7; w++)
			{
				board[w][i] = E;
			}
		}
	}
	
	public int addToColumn(int col, char color)
	{
		int newCol = col-1;
		int i=5;
		while(i>=0)
		{
			if(board[newCol][i] == E)
			{
				board[newCol][i] = color;
				break;
			}
			i--;
		}
		return ((((i)*7))+col)-1;		
	}
	
	
}
