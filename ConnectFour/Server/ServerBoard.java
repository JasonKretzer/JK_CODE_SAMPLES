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

//Board that operations for the server to perform

public class ServerBoard extends Board
{
	public boolean isDraw()
	{
		for(int i=0; i<6; i++)
		{
			for(int w=0; w<7; w++)
			{
				if(board[w][i] == E)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isWinner(int column, char color)
	{
		int col = column-1;
		int i=5;
		boolean foundEmpty = false;
		while(i>=0)
		{
			if(board[col][i] == E)
			{
				//use i here
				foundEmpty = true;
				break;
			}
			i--;
		}
		if(!foundEmpty)
		{
			i = 0;
		}
		else
		{
			i++;
		}
		
		
		//THIS IS LONG, but it works
		
		
		if(col == 0)
		{
			if(i==0)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col+1][i]//down
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==3)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3]);
			}
		}
		else if(col == 1)
		{
			if(i==0)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])			
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2]);
			}
			else if(i==3)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3]);
			}
		}
		else if(col == 2)
		{
			if(i==0)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col+1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right -- 
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2])
						;
			}
			else if(i==3)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3]);
			}
		}
		else if(col == 3)
		{
			if(i==0)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col+1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col+3][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==3)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col+1][i]//across right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col+3][i])
						||
						(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col+1][i-1]//diagonal up right
						&&board[col][i]==board[col+2][i-2]
						&&board[col][i]==board[col+3][i-3])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
		}
		else if(col == 4)
		{
			if(i==0)
			{
				return 	(board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col+1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==3)
			{
				return (board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle left going down right
						&&board[col][i]==board[col+2][i+2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle left going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col+2][i-2]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col+1][i]//across middle left going right
						&&board[col][i]==board[col+2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
		}
		else if(col == 5)
		{
			if(i==0)
			{
				return 	(board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col+1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==3)
			{
				return (board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i+1]//diagonal middle right going up right
						&&board[col][i]==board[col+1][i-1]
						&&board[col][i]==board[col-2][i+2]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col+1][i+1]//diagonal middle right going down right
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-1][i-1])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col+1][i]//across middle right going right
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-1][i])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
		}
		else if(col == 6)
		{
			if(i==0)
			{
				return 	(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3]);
			}
			else if(i==1)
			{
				return (board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3])
						||
						(board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i]);
			}
			else if(i==2)
			{
				return (board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i+1]//diagonal down left
						&&board[col][i]==board[col-2][i+2]
						&&board[col][i]==board[col-3][i+3])
						||
						(board[col][i]==board[col][i+1]//down
						&&board[col][i]==board[col][i+2]
						&&board[col][i]==board[col][i+3]);
			}
			else if(i==3)
			{
				return (board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
			else if(i==4)
			{
				return (board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
			else if(i==5)
			{
				return (board[col][i]==board[col-1][i]//across left
						&&board[col][i]==board[col-2][i]
						&&board[col][i]==board[col-3][i])
						||
						(board[col][i]==board[col-1][i-1]//diagonal up left
						&&board[col][i]==board[col-2][i-2]
						&&board[col][i]==board[col-3][i-3]);
			}
		}
		
		return false;
	}
	
	public boolean addToCol(int col, char color)
	{
		int newCol = col-1;
		int i=5;
		boolean addedToColumn = false;
		while(i>=0)
		{
			if(board[newCol][i] == E)
			{
				board[newCol][i] = color;
				addedToColumn = true;
				break;
			}
			i--;
		}
		return addedToColumn;
	}
}
