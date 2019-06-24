/** Alexis Berens
 *  CPT-237
 *  Project 4
 *  Part 1 of 2
 *  12/06/2018
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Override because warnings are annoying
@SuppressWarnings("serial")
public class JTicTacToe extends JPanel implements ActionListener
{
	JLabel title = new JLabel("Tic-Tac-Toe");
	JLabel instructions = new JLabel("Make your move!");
	
	//Lets user know when game outcome is decided
	JLabel outcome = new JLabel();
	
	//String variable to change based on outcome of game
    //No message displayed until a winner (or tie) is determined
    String msg = "";
   
    //Fonts for game title, instructions, and outcome
    Font titleFont = new Font("Bahnschrift", Font.BOLD, 22);
    Font mainFont = new Font("Bahnschrift", Font.BOLD, 16);
    Font outcomeFont = new Font("Bahnschrift", Font.BOLD, 12);

    //Limits for 3x3 game board that avoid repeated variable declaration
    final static int ROW_SIZE = 3;
    final static int COLUMN_SIZE = 3;
    JButton[][] playingArea =  new JButton[ROW_SIZE][COLUMN_SIZE];
   
    //Variable outputs for chosen positions for each player and a blank for an unchosen space
    final static String BLANK = " ";
    final static String XFORPLAYER = "X";
    final static String OFORCOMPUTER = "O";
   
    //Reference variables for "playingArea"
    int row = 0, column = 0;
   
    //Variable to trigger game ending
    boolean gameOver;
   
    //Checks winning 3-in-a-row combinations
    public static boolean doWeHaveAWinner(JButton [][] playingArea)
    {
    	//Default returned value is false/ no winner
        boolean gameOver = false;
       
        //Checks for the presence of an "X" or "O" in each square of the playing grid and then for matching Strings (3-in-a-row)
        //Each nested statement only executes if the space is not (!) blank (which cannot contribute to a winner)
        //If 3-in-a-row is found, gameOver changes to "true"
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

    //Checks for blank spaces on board
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
    
    //Determines "O"/ computer selections
    public static void chooseSpot(JButton[][] playingArea)
    {
    	
    	//Reusable variables
    	int x, y;
	  
    	//Used to stop turn when a selection has been made
    	boolean turnTaken = false;
	  
    	//Ensure computer completes 3-in-a-row to win if available by checking for existing 2-in-a-row scenarios
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
    	
    	//Ensures computer blocks player wins if no computer wins are possible by same logic
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
    	
    	//Chooses an open space at random if no winning moves or blocks are present
    	if(!turnTaken)
    	{
    		while(!turnTaken)
    		{
    			turnTaken = true;
    			int num = (int)((Math.random() * 100) % 9);
    			x = num / 3;
    			y = num % 3;
             
    			//If the selected space is already taken, resets turnTaken variable and runs again
    			if(!playingArea[x][y].getText().equals(BLANK))
    				turnTaken = false;
    		}
    		playingArea[x][y].setText(OFORCOMPUTER);
    	} 
    }
    
    public JTicTacToe()
    {
    	
    	//Applies color/ font changes and adds components to window
    	Color darkGreen = new Color(0, 75, 10);
    	Color lightGreen = new Color(100, 255, 115);
    	this.setBackground(lightGreen);
    	title.setFont(titleFont);
    	title.setForeground(darkGreen);
    	add(title);
    	instructions.setFont(mainFont);
    	instructions.setForeground(darkGreen);
    	add(instructions);
      
    	//Creates playing board and sets listeners to each space
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
	   
    	//Creates object reference to source of click
    	Object spaceSelected = e.getSource();
       
    	//Grabs info from space selected and assigns to variables
    	for(int x = 0; x < ROW_SIZE; ++x)
    		for(int y = 0; y < COLUMN_SIZE; ++y)
    			
    			//Stops execution if game has already been decided so more spaces cannot be selected
    			if(spaceSelected == playingArea[x][y] && !gameOver)
    			{
    				row = x;
    				column = y;
    			   
    				//Reassigns board size limits to x and y
    				x = ROW_SIZE;
    				y = COLUMN_SIZE;
    			}
    	
    	//If space selected is already assigned an "X" or an "O", nothing happens
    	if(playingArea[row][column].getText().equals(XFORPLAYER) || playingArea[row][column].getText().equals(OFORCOMPUTER))
    	{
    	}
    	else
    	{
    	   
    		//Space selected by player is set to "X"
    		playingArea[row][column].setText(XFORPLAYER);
    		msg = "";
           
    		//Runs winner check to determine if game is over
    		gameOver = doWeHaveAWinner(playingArea);           
    		msg = "";

    		if(gameOver)
    			msg = "You won! ";
           
    		//When no open spaces are left and there is no winner, a tie is called
    		else
    			if(!openSpaces(playingArea))
    			{
    				gameOver = true;
    				msg = "You tied.";
    			}
    		
    		//Sets outcome message in the event of a player win or tie
    		if(gameOver)
    			outcome.setText("Game over! " + msg);

    		//Since spaces remain and player/ "X" hasn't won, computer checks to see if it can win
    		else
    		{
    			chooseSpot(playingArea);
    			gameOver = doWeHaveAWinner(playingArea);
               
    			//Sets outcome message in the event of a computer win
    			if(gameOver)
    				outcome.setText("Better luck next time...");
    		}
    	}
   }
   
   //Creates visible window for user to interact with
   public static void main(String[] args)
   {
	   JFrame frame = new JFrame();
	   frame.add(new JTicTacToe());
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setSize(180, 225);
	   frame.setVisible(true);
   }        
}