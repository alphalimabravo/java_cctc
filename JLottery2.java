/** Alexis Berens
 *  CPT-237
 *  Project 2
 *  Part 1 of 1
 *  10/07/2018
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JLottery2 extends JFrame implements ItemListener
{
	//Will be using numbers 0-30
	final int LIMIT = 31;
	final int SELECTIONS = 6;
	FlowLayout flow = new FlowLayout();
	Font garamondBold = new Font("Garamond", Font.BOLD, 18);
	
	JLabel greeting = new JLabel("Welcome to the lottery game! You could win up to $1,000,000!");
	JLabel greeting2 = new JLabel("Please choose " + SELECTIONS + " numbers (You must match at least 3 to win)");
	
	String winningNumbers = "The winning numbers are >> ";
	String numbersSelected = "Your numbers >>   ";
	
	JCheckBox[] choice = new JCheckBox[LIMIT];
	JLabel[] label = new JLabel[LIMIT];
	JTextArea outputArea = new JTextArea("");
	
	int x, y;
	int selectedCount = 0;
	int matches = 0;
	int[] lotto = new int[SELECTIONS];
	int[] selected = new int[SELECTIONS];
	
	//No winnings for 0, 1, or 2 matches
	int[] prizeArray = {0, 0, 0, 100, 10000, 50000, 1000000};
	
	public JLottery2()
	{
		super("Lottery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(flow);
		add(greeting);
		add(greeting2);
		greeting.setFont(garamondBold);
		greeting2.setFont(garamondBold);
		
		//Creates the 0-30 check boxes/ number labels and adds each check box as an event source
		for(x = 0; x < LIMIT; ++x)
		{
			choice[x] = new JCheckBox();
			label[x] = new JLabel("     " + x);
			add(label[x]);
			add(choice[x]);
			choice[x].addItemListener(this);
		}
		add(outputArea);
		
		//Generates the 6 random numbers for each lottery
		for(x = 0; x < SELECTIONS; ++x)
		{
			lotto[x] = (int)Math.floor(Math.random()*LIMIT);
			for(y = 0; y < x; ++y)
				if(lotto[x] == lotto[y])
					--x;
		}
		
		//Adds lotto numbers to winningNumbers String
		for(x = 0; x < SELECTIONS; ++x)
		{
			winningNumbers += lotto[x] + "   ";
		}
	}
	
	//Creates visible frame for user to interact with
	public static void main(String[] arguments)
	{
		final int WIDTH = 500;
		final int HEIGHT = 400;
		JLottery2 lottoFrame = new JLottery2();
		lottoFrame.setSize(WIDTH, HEIGHT);
		lottoFrame.setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent check)
	{
		Object source = check.getItem();
		
		//Prevents user from selecting more than the allowed 6 boxes
		if(selectedCount == SELECTIONS)
		{
			for(x= 0; x < LIMIT; ++x)
			{
				if(source == choice[x])
					choice[x].setSelected(false);
			}
		}
		else
		{
			for(x= 0; x < LIMIT; ++x)
			{
				if(source == choice[x])
				{
					int select = check.getStateChange();
					if(select == ItemEvent.SELECTED)
					{
						selected[selectedCount] = x;
						//Adds selected numbers to numbersSelected String
						numbersSelected += x + "   ";
						++selectedCount;
					}
				}
			}
			
			//Prints out results once user has selected the 6 required numbers, 
			//comparing the number of matches to the corresponding position in the prizeArray
			if(selectedCount == SELECTIONS)
			{
				for(x = 0; x < SELECTIONS; ++x)
					for(y = 0; y < SELECTIONS; ++y)
						if(selected[x] == lotto[y])
							++matches; 
				outputArea.append(winningNumbers);
				outputArea.append("\n" + numbersSelected);
				outputArea.append("\nYou got " + matches + " number(s) correct");
				outputArea.append("\nYou earned: $" + prizeArray[matches]);
			}
		}
	}
}