/** Alexis Berens
 *  CPT-237
 *  Project 4
 *  Part 2 of 2
 *  12/06/2018
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class JTicTacToe2 extends JPanel implements ActionListener
{
	JLabel title = new JLabel("Tic-Tac-Toe");
	JLabel instructions = new JLabel("Make your move!");
	JLabel outcome = new JLabel();
	String msg = "";
	
	Font titleFont = new Font("Bahnschrift", Font.BOLD, 22);
	Font mainFont = new Font("Bahnschrift", Font.BOLD, 16);
	Font outcomeFont = new Font("Bahnschrift", Font.BOLD, 12);
	
	final static int ROW_SIZE = 3;
	final static int COLUMN_SIZE = 3;
	JButton[][] playingArea =  new JButton[ROW_SIZE][COLUMN_SIZE];
	
	final static String BLANK = " ";
	final static String XFORPLAYER = "X";
	final static String OFORCOMPUTER = "O";
	
	int row = 0, column = 0;
	boolean gameOver;
	
	//Character to indicate winner or tie for paint display
	char winner;
	
	public static boolean doWeHaveAWinner(JButton [][] playingArea)
	{
		boolean gameOver = false;
	    
		for(int x = 0; x < 3; ++x)
			if(!playingArea[x][0].getText().equals(BLANK))
				if(playingArea[x][0].getText().equals(playingArea[x][1].getText()) 
						&& playingArea[x][0].getText().equals(playingArea[x][2].getText()))
					gameOver = true;
		for(int x = 0; x < 3; ++x)
			if(!playingArea[0][x].getText().equals(BLANK))
				if(playingArea[0][x].getText().equals(playingArea[1][x].getText()) 
						&& playingArea[1][x].getText().equals(playingArea[2][x].getText()))
					gameOver = true;
	        if(!playingArea[0][0].getText().equals(BLANK))
	    	    if(playingArea[0][0].getText().equals(playingArea[1][1].getText()) 
	    			    && playingArea[0][0].getText().equals(playingArea[2][2].getText()))
	    		    gameOver = true;
	        if(!playingArea[0][2].getText().equals(BLANK))
	    	    if(playingArea[0][2].getText().equals(playingArea[1][1].getText()) 
	    			    && playingArea[0][2].getText().equals(playingArea[2][0].getText()))
	    		    gameOver = true;
	        return gameOver;
	}
	
	public static boolean openSpaces(JButton[][] grid)
	{
		boolean stillSpacesLeft = false;
		for(int x = 0; x < ROW_SIZE; ++x)
	    {
			for(int y = 0; y < COLUMN_SIZE; ++y)
				if(grid[x][y].getText().equals(BLANK))
					stillSpacesLeft = true;
	    }
		return stillSpacesLeft;
	}
	
	public static void chooseSpot(JButton[][] playingArea)
	{
		int x, y;
		boolean turnTaken = false;
		
		for(x = 0; x < ROW_SIZE && !turnTaken; ++x)
		{
			if(playingArea[x][0].getText().equals(OFORCOMPUTER)
					&& playingArea[x][1].getText().equals(OFORCOMPUTER) 
					&& playingArea[x][2].getText().equals(BLANK))
	    	{
				playingArea[x][2].setText(OFORCOMPUTER);
	    		turnTaken = true;
		  	}
			else
				if(playingArea[x][0].getText().equals(OFORCOMPUTER) 
						&& playingArea[x][2].getText().equals(OFORCOMPUTER) 
		  	  			&& playingArea[x][1].getText().equals(BLANK))
		  	  	{
					playingArea[x][1].setText(OFORCOMPUTER);
		  	  		turnTaken = true;
		  	  	}
		  	  	else
		  	  		if(playingArea[x][1].getText().equals(OFORCOMPUTER) 
		  	  				&& playingArea[x][2].getText().equals(OFORCOMPUTER) 
		  	  				&& playingArea[x][0].getText().equals(BLANK))
		  	  		{
		  	  			playingArea[x][0].setText(OFORCOMPUTER);
		  	  			turnTaken = true;
		  	  		}
		}
		
		for(y = 0; y < COLUMN_SIZE && !turnTaken; ++y)
	    {
			if(playingArea[0][y].getText().equals(OFORCOMPUTER) 
					&& playingArea[1][y].getText().equals(OFORCOMPUTER) 
					&& playingArea[2][y].getText().equals(BLANK))
			{
				playingArea[2][y].setText(OFORCOMPUTER);
				turnTaken = true;
			}
		  	else
		  		if(playingArea[0][y].getText().equals(OFORCOMPUTER) 
		  				&& playingArea[2][y].getText().equals(OFORCOMPUTER) 
		  				&& playingArea[1][y].getText().equals(BLANK))
		  		{
		  			playingArea[1][y].setText(OFORCOMPUTER);
		  			turnTaken = true;
		  		}
		  		else
		  			if(playingArea[1][y].getText().equals(OFORCOMPUTER) 
		  					&& playingArea[2][y].getText().equals(OFORCOMPUTER) 
		  					&& playingArea[0][y].getText().equals(BLANK))
		  			{
		  				playingArea[0][y].setText(OFORCOMPUTER);
		  				turnTaken = true;
		  	  		}
	    }
	      
		if(!turnTaken)
			if(playingArea[0][0].getText().equals(OFORCOMPUTER) 
					&& playingArea[1][1].getText().equals(OFORCOMPUTER) 
	      	  		&& playingArea[2][2].getText().equals(BLANK))
			{
				playingArea[2][2].setText(OFORCOMPUTER);
	      	  	turnTaken = true;
	      	}
	      	else
	      		if(playingArea[0][0].getText().equals(OFORCOMPUTER) 
	      				&& playingArea[2][2].getText().equals(OFORCOMPUTER) 
	      				&& playingArea[1][1].getText().equals(BLANK))
	      	  	{
	      			playingArea[1][1].setText(OFORCOMPUTER);
	      			turnTaken = true;
	      	  	}
	      	  	else
	      	  		if(playingArea[2][2].getText().equals(OFORCOMPUTER) 
	      	  				&& playingArea[1][1].getText().equals(OFORCOMPUTER) 
	      	  				&& playingArea[0][0].getText().equals(BLANK))
	      	  		{
	      	  			playingArea[0][0].setText(OFORCOMPUTER);
	      	  			turnTaken = true;
	      	  		}
	      	  		else
	      	  			if(playingArea[0][2].getText().equals(OFORCOMPUTER) 
	      	  					&& playingArea[1][1].getText().equals(OFORCOMPUTER) 
	      	  					&& playingArea[2][0].getText().equals(BLANK))
	      	  			{
	      	  				playingArea[2][0].setText(OFORCOMPUTER);
	      	  				turnTaken = true;
	      	  			}
	      	  			else
	      	  				if(playingArea[0][2].getText().equals(OFORCOMPUTER) 
	      	  						&& playingArea[2][0].getText().equals(OFORCOMPUTER) 
	      	  						&& playingArea[1][1].getText().equals(BLANK))
	      	  				{
	      	  					playingArea[1][1].setText(OFORCOMPUTER);
	      	  					turnTaken = true;
	      	  				}
	      	  				else
	      	  					if(playingArea[1][1].getText().equals(OFORCOMPUTER) 
	      	  							&& playingArea[2][0].getText().equals(OFORCOMPUTER) 
	      	  							&& playingArea[0][2].getText().equals(BLANK))
	      	  					{
	      	  						playingArea[0][2].setText(OFORCOMPUTER);
	      	  						turnTaken = true;
	      	  					}
		
		for(x = 0; x < ROW_SIZE && !turnTaken; ++x)
		{
			if(playingArea[x][0].getText().equals(XFORPLAYER) 
	    			&& playingArea[x][1].getText().equals(XFORPLAYER) 
	    			&& playingArea[x][2].getText().equals(BLANK))
	    	{
	    		playingArea[x][2].setText(OFORCOMPUTER);
	    		turnTaken = true;
	    	}
	    	else
	    		if(playingArea[x][0].getText().equals(XFORPLAYER) 
	    				&& playingArea[x][2].getText().equals(XFORPLAYER) 
	    				&& playingArea[x][1].getText().equals(BLANK))
	    		{
	    			playingArea[x][1].setText(OFORCOMPUTER);
	    			turnTaken = true;
	    		}
	    		else
	    			if(playingArea[x][1].getText().equals(XFORPLAYER) 
	    					&& playingArea[x][2].getText().equals(XFORPLAYER) 
	    					&& playingArea[x][0].getText().equals(BLANK))
	    			{
	    				playingArea[x][0].setText(OFORCOMPUTER);
	    				turnTaken = true;
	    			}
	    }
		
		for(y = 0; y < COLUMN_SIZE && !turnTaken; ++y)
	    {
	    	if(playingArea[0][y].getText().equals(XFORPLAYER) 
	    			&& playingArea[1][y].getText().equals(XFORPLAYER) 
	    			&& playingArea[2][y].getText().equals(BLANK))
	    	{
	    		playingArea[2][y].setText(OFORCOMPUTER);
	    		turnTaken = true;
	    	}
	    	else
	    		if(playingArea[0][y].getText().equals(XFORPLAYER) 
	    				&& playingArea[2][y].getText().equals(XFORPLAYER) 
	    				&& playingArea[1][y].getText().equals(BLANK))
	    		{
	    			playingArea[1][y].setText(OFORCOMPUTER);
	    			turnTaken = true;
	    		}
	    		else
	    			if(playingArea[1][y].getText().equals(XFORPLAYER) 
	    					&& playingArea[2][y].getText().equals(XFORPLAYER) 
	    					&& playingArea[0][y].getText().equals(BLANK))
	    			{
	    				playingArea[0][y].setText(OFORCOMPUTER);
	    				turnTaken = true;
	    			}
	    }
	    
	    if(!turnTaken)
	    	if(playingArea[0][0].getText().equals(XFORPLAYER) 
	    			&& playingArea[1][1].getText().equals(XFORPLAYER) 
	    			&& playingArea[2][2].getText().equals(BLANK))
	    	{
	    		playingArea[2][2].setText(OFORCOMPUTER);
	    		turnTaken = true;
	    	}
	    	else
	    		if(playingArea[0][0].getText().equals(XFORPLAYER) 
	    				&& playingArea[2][2].getText().equals(XFORPLAYER) 
	    				&& playingArea[1][1].getText().equals(BLANK))
	    		{
	    			playingArea[1][1].setText(OFORCOMPUTER);
	    			turnTaken = true;
	    		}
	    		else
	    			if(playingArea[2][2].getText().equals(XFORPLAYER) 
	    					&& playingArea[1][1].getText().equals(XFORPLAYER) 
	    					&& playingArea[0][0].getText().equals(BLANK))
	    			{
	    				playingArea[0][0].setText(OFORCOMPUTER);
	    				turnTaken = true;
	    			}
	    			else
	    				if(playingArea[0][2].getText().equals(XFORPLAYER) 
	    						&& playingArea[1][1].getText().equals(XFORPLAYER) 
	    						&& playingArea[2][0].getText().equals(BLANK))
	    				{
	    					playingArea[2][0].setText(OFORCOMPUTER);
	    					turnTaken = true;
	    				}
	    				else
	    					if(playingArea[0][2].getText().equals(XFORPLAYER) 
	    							&& playingArea[2][0].getText().equals(XFORPLAYER) 
	    							&& playingArea[1][1].getText().equals(BLANK))
	    					{
	    						playingArea[1][1].setText(OFORCOMPUTER);
	    						turnTaken = true;
	    					}
	    					else
	    						if(playingArea[1][1].getText().equals(XFORPLAYER) 
	    								&& playingArea[2][0].getText().equals(XFORPLAYER) 
	    								&& playingArea[0][2].getText().equals(BLANK))
	    						{
	    							playingArea[0][2].setText(OFORCOMPUTER);
	    							turnTaken = true;
	    						}
	    
	    if(!turnTaken)
	    {
	    	while(!turnTaken)
	    	{
	    		turnTaken = true;
	    		int num = (int)((Math.random() * 100) % 9);
	    		x = num / 3;
	    		y = num % 3;
	    		
	    		if(!playingArea[x][y].getText().equals(BLANK))
	    			turnTaken = false;
	    	}
	    	playingArea[x][y].setText(OFORCOMPUTER);
	    }
	}
	
	public JTicTacToe2()
	{
		Color darkGreen = new Color(0, 75, 10);
	    Color lightGreen = new Color(100, 255, 115);
	    this.setBackground(lightGreen);
	    title.setFont(titleFont);
	    title.setForeground(darkGreen);
	    add(title);
	    instructions.setFont(mainFont);
	    instructions.setForeground(darkGreen);
	    add(instructions);
	      
	    for(int x = 0; x < ROW_SIZE; ++x)
	    	for(int y = 0; y < COLUMN_SIZE; ++y)
	    	{
	    		playingArea[x][y] = new JButton();
	    		playingArea[x][y].setText(BLANK);
	    		add(playingArea[x][y]);
	    		playingArea[x][y].addActionListener(this);
	    	}
	    	
	    outcome.setFont(outcomeFont);
	    outcome.setForeground(darkGreen);
	    add(outcome);
	}
	    
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object spaceSelected = e.getSource();
	       
	    for(int x = 0; x < ROW_SIZE; ++x)
	    	for(int y = 0; y < COLUMN_SIZE; ++y)
	    		if(spaceSelected == playingArea[x][y] && !gameOver)
	    		{
	    			row = x;
	    			column = y;
	    			x = ROW_SIZE;
	    			y = COLUMN_SIZE;
	    		}
	    
	    if(playingArea[row][column].getText().equals(XFORPLAYER) || playingArea[row][column].getText().equals(OFORCOMPUTER))
	    {
	    }
	    else
	    {
	    	playingArea[row][column].setText(XFORPLAYER);
	    	msg = "";
	        
	    	gameOver = doWeHaveAWinner(playingArea);           
	    	msg = "";

	    	if(gameOver)
	    	{
	    		msg = "You won! ";
	    		
	    	    //Drawing variable trigger for player win
	    		winner = 'X';
	    	}
	    	
	    	else
	    		if(!openSpaces(playingArea))
	    		{
	    			gameOver = true;
	    			msg = "You tied.";
	    			
	    			//Drawing variable trigger for tie
	    			winner = 'T';
	    		}
	    		
	    	if(gameOver)
	    		outcome.setText("Game over! " + msg);

	    	else
	    	{
	    		chooseSpot(playingArea);
	    		gameOver = doWeHaveAWinner(playingArea);
	              
	    		if(gameOver)
	    		{
	    			outcome.setText("Better luck next time...");
	    			
	    			//Drawing variable trigger for computer win
	    			winner = 'O';
	    		}
	    	}
	    }
	    
	    //Repaints window when game over is triggered to display drawing
	    if(gameOver)
	        repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//Variables for starting points for graphic drawings
		float xOrigin = 40;
		float yOrigin = 180;
		
		//Variables to create size of X's and O's being drawn
		float width = 80;
		float height = 80;
		
		//Creating graphic object and stroke with desired parameters, then applying to object
		Graphics2D graphic = (Graphics2D)g;
		BasicStroke aStroke = new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);		
		graphic.setStroke(aStroke);
		
		//New colors for painting and setting default color
		Color purple = new Color (120, 0, 200);
		Color hotPink = new Color (255, 60, 150);		
		graphic.setColor(purple);
        
		//Player wins and "X" is drawn
		if(winner == 'X')
		{
        graphic.draw(new Line2D.Float(xOrigin, yOrigin, xOrigin + width, yOrigin + height));
        graphic.draw(new Line2D.Float(xOrigin, yOrigin + height, xOrigin + width, yOrigin));
        }
		else
			
			//Computer wins and "O" is drawn
			if(winner == 'O')
			{
				graphic.draw(new Ellipse2D.Float(xOrigin, yOrigin, width, height));
			}
			else
				
				//Tie and both "X" and "O" are drawn together, with the O's color changed for visibility
				if(winner == 'T')
				{
					graphic.draw(new Line2D.Float(xOrigin, yOrigin, xOrigin + width, yOrigin + height));
					graphic.draw(new Line2D.Float(xOrigin, yOrigin + height, xOrigin + width, yOrigin));
					graphic.setColor(hotPink);
					graphic.draw(new Ellipse2D.Float(xOrigin, yOrigin, width, height));
				}
	}
	   public static void main(String[] args)
	   {
		   JFrame frame = new JFrame();
		   frame.add(new JTicTacToe2());
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		   //Made window longer to display graphic
		   frame.setSize(180, 315);
		   frame.setVisible(true);
	   }        
}