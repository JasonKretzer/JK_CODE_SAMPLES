
import javax.swing.*;
import javax.swing.border.LineBorder;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.io.*;
import java.net.*;


class ConnectFourClient extends JFrame implements ActionListener,ConnectFourOperations
{
	private JPanel mainPane = null;
	private JPanel buttonPanel = null;
	private JPanel centerPanel = null;
	private JPanel underPanel = null;
	private JPanel overPanel = null;
	
	private JButton[] buttons = null;
	private JLabel topLabel = null;
	private JLabel statusLabel = null;
	private JLabel bottomLabel = null;
	
	private Component[] comps = null; 
	
	private ImageIcon iiBlue = null;		
	private ImageIcon iiRed = null;
	private ImageIcon iiWhite = null;
	
	private char myColor = '\0';
	private char oppColor = '\0';
	private boolean myTurn = false;
	
	private Board board = null;
	
	
	//private String host = "zappa.nku.edu";
	private String host = "localhost";
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private Socket sock = null;
	
	

	public ConnectFourClient(String ihost)
	{
		super("Connect Four Client");
		if(ihost != null) {
			host = ihost;
		}
		buttons = new JButton[7];
		initButtons();
		mainPane = new JPanel(new BorderLayout());
		underPanel = new JPanel();
		overPanel = new JPanel(new GridLayout(2,1));
		centerPanel = new JPanel(new GridLayout(6,7,3,3));
		buttonPanel = new JPanel(new GridLayout(1,7,3,3));

		topLabel = new JLabel("CONNECT FOUR");
		statusLabel = new JLabel("STATUS:  Waiting for connection");
		bottomLabel = new JLabel("Select column to drop a piece into.");
		JPanel p = null;
		for(int i=0; i<buttons.length; i++)
		{
			for(int w=0; w<6; w++)
			{
				p = new JPanel();
				p.setBackground(Color.white);
				p.add(new JLabel());
				centerPanel.add(p);
			}
		}
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].addActionListener(this);
			buttonPanel.add(buttons[i]);
		}
		


		topLabel.setForeground(Color.blue);
		topLabel.setFont(new Font("bob the font",Font.ITALIC+Font.BOLD,24));
		
		JPanel topLabelPanel = new JPanel();
		JPanel statusLabelPanel = new JPanel();
		
		topLabelPanel.add(topLabel);
		statusLabelPanel.add(statusLabel);		
		overPanel.add(topLabelPanel);
		overPanel.add(statusLabelPanel);
		underPanel.add(bottomLabel);
		

		centerPanel.setBackground(Color.black);
		
		p = new JPanel(new BorderLayout());
		p.add(centerPanel, BorderLayout.CENTER);
		p.add(buttonPanel, BorderLayout.SOUTH);
		centerPanel.setBorder(new LineBorder(Color.black,4,true));
		
		
		JPanel x= new JPanel();
		x.add(p);
		mainPane.add(overPanel,BorderLayout.NORTH);
		mainPane.add(underPanel,BorderLayout.SOUTH);
		mainPane.add(x,BorderLayout.CENTER);
		setContentPane(mainPane);
		
		comps = centerPanel.getComponents();
		
		iiBlue = new ImageIcon("blue.jpg");		
		iiRed = new ImageIcon("red.jpg");
		iiWhite = new ImageIcon("white.jpg");
		
		//otherwise the board is all scruncehd together because there is nothing in them.
		((JLabel)((JPanel)comps[0]).getComponent(0)).setIcon(iiWhite);


		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,480);
		setLocation(100,100);
		setResizable(false);
		setVisible(true);
		
		board = new Board();
		
		
		try
		{
			sock = new Socket(host,3045);
			in = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
			
			int action = NO_OP;
			while(true)
			{
				
				action = in.readInt();
				
				
				if(action >= 1 && action <=7)
				{
					//JOptionPane.showMessageDialog(this,"MOVE AT "+action);
					addPiece(action);//1 - 7
					continue;
				}
				else if(action == MAKE_FIRST_MOVE)//8
				{
					JOptionPane.showMessageDialog(this,"You Move First");
					myColor = Board.B;
					myTurn = true;
					firstMove();
					continue;
				}
				else if(action == MAKE_SEC_MOVE)//13
				{
					secondMove();
					continue;
				}	
				else if(action == BAD_MOVE)//9
				{
					badMove();
					continue;
				}	
				else if(action == YOU_WIN)//10
				{
					youWin();
					break;
				}
				else if(action == YOU_LOSE)//11
				{
					youLose();
					break;
				}
				else if(action == DRAW)//12
				{
					gameDraw();
					continue;
				}
				else if(action == CONNECTED)//14
				{
					statusLabel.setText("Connected, waiting for player 1");
					continue;
				}
				else
				{
					out.close();
					in.close();
					sock.close();
					System.exit(0);
					break;
				}
			}
			out.close();
			in.close();
			sock.close();
			System.exit(0);
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				JOptionPane.showMessageDialog(this,"This application has unexpectedly quit and will close.\n Is the server running?","Uh Oh, Problem...",JOptionPane.ERROR_MESSAGE);
				if(in != null)
				{
					in.close();
				}
				if(out != null)
				{
					out.close();
				}
				if(sock != null)
				{
					sock.close();
				}
				System.exit(1);
			}
			catch	(Exception e2)
			{
				e2.printStackTrace();
				System.exit(1);
			}
			
		}
		//*/
		
		
		
	}


	/**
	 * 
	 */
	private void secondMove()
	{
		System.out.println(1);
		// TODO Auto-generated method stub
		myColor = Board.R;
		oppColor = Board.B;
		
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setEnabled(true);
		}
		System.out.println(2);
	}


	/**
	 * 
	 */
	private void gameDraw() throws Exception
	{
		// TODO Auto-generated method stub
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setEnabled(false);
		}
		int action = in.readInt();
		addPiece(action);
		JOptionPane.showMessageDialog(this,"Game is a draw.");
	}


	/**
	 * 
	 */
	private void youLose() throws Exception
	{
		// TODO Auto-generated method stub
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setEnabled(false);
		}
		int action = in.readInt();
		addPiece(action);
		JOptionPane.showMessageDialog(this,"Sorry, you lost.  Sucks to be you.");
		
	}


	/**
	 * 
	 */
	private void youWin() throws Exception
	{
		// TODO Auto-generated method stub
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setEnabled(false);
		}
		int action = in.readInt();
		addPiece(action);
		JOptionPane.showMessageDialog(this,"YOU WIN!");
		
	}


	/**
	 * @param action
	 */
	private void addPiece(int action)
	{
		System.out.println(3);
		// TODO Auto-generated method stub
		int posToPaint = NO_OP;
		if(myTurn)
		{
			System.out.println(4);
			posToPaint = board.addToColumn(action,myColor);
			if(myColor == Board.B)
			{
				((JLabel)((JPanel)comps[posToPaint]).getComponent(0)).setIcon(iiBlue);
			}
			else
			{
				((JLabel)((JPanel)comps[posToPaint]).getComponent(0)).setIcon(iiRed);
			}
			System.out.println(5);
			
			((JPanel)comps[posToPaint]).repaint();
			statusLabel.setText("Other Player's Move");
			myTurn = false;
			for(int i=0; i<buttons.length; i++)
			{
				buttons[i].setEnabled(false);
			}
			System.out.println(6);
		}
		else
		{
			System.out.println(7);
			posToPaint = board.addToColumn(action,oppColor);
			if(oppColor == Board.B)
			{
				((JLabel)((JPanel)comps[posToPaint]).getComponent(0)).setIcon(iiBlue);
			}
			else
			{
				((JLabel)((JPanel)comps[posToPaint]).getComponent(0)).setIcon(iiRed);
			}
			System.out.println(8);
			((JPanel)comps[posToPaint]).repaint();
			statusLabel.setText("YOUR MOVE");
			myTurn = true;
			for(int i=0; i<buttons.length; i++)
			{
				buttons[i].setEnabled(true);
			}
			System.out.println(9);
		}
		
	}


	/**
	 * 
	 */
	private void badMove()
	{
		// TODO Auto-generated method stub
		statusLabel.setText("ILLEGAL MOVE, try again");
		myTurn = true;
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setEnabled(true);
		}
		
	}


	/**
	 * 
	 */
	private void firstMove()
	{
		for(int i=0; i<buttons.length; i++)
		{
			buttons[i].setEnabled(true);
		}
		statusLabel.setText("YOUR MOVE");
		myColor = Board.B;
		oppColor = Board.R;
		System.out.println(10);
	}


	public static void main(String[] args)
	{
		if(args.length != 1) {
			System.out.println("Usage:  java ConnectFourClient <SERVER_NAME_OR_IP>");
			System.exit(0);
		}
		String ihost = args[0];	
		ConnectFourClient conn = new ConnectFourClient(ihost);
	}

	private void initButtons()
	{
		for(int i=0; i<buttons.length;i++)
		{
			buttons[i] = new JButton(new Integer(i+1).toString());
			buttons[i].setEnabled(false);
		}
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JButton)
		{
			int source = Integer.parseInt(((JButton)e.getSource()).getText());
			try
			{
				//JOptionPane.showMessageDialog(this,"sending: "+source);
				out.writeInt(source);
				out.writeChar(myColor);
				System.out.println(11+"b");
			} 
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			for(int i=0; i<buttons.length; i++)
			{
				buttons[i].setEnabled(false);
			}
			System.out.println(11);
			
		}
		
	}

}
