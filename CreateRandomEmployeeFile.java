/** Alexis Berens
 *  CPT-237
 *  Project 3
 *  Part 1 of 2
 *  11/04/2018
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

//Warning suppression because warnings are annoying
@SuppressWarnings("serial")
public class CreateRandomEmployeeFile extends JFrame implements ActionListener
{
	private JLabel title = new JLabel("Employee Record System");
	Font myFont = new Font("Impact", Font.PLAIN, 20);
    Color darkBlue = new Color(25, 0, 75);
    Color betterOrange = new Color(255, 200, 0);
	private JLabel ask = new JLabel("Please enter the employee's information below:");
	
	//Employee ID from 1-99
	private JTextField idField = new JTextField(5);	
	private JLabel idLabel = new JLabel("ID number (valid numbers are 1-99)");
	
	//First and last name
	private JLabel firstNameLabel = new JLabel("Legal first name");
	private JTextField firstNameField = new JTextField(15);
	private JLabel lastNameLabel = new JLabel("Legal last name");
	private JTextField lastNameField = new JTextField(15);
	
	//Hourly pay rate
	private JLabel payRateLabel = new JLabel("Hourly pay rate");
	private JTextField payRateField = new JTextField(10);
	
	//Allow user to enter each record one at a time and to click a button to save the record
	private JButton enterDataButton = new JButton("Enter");
	
	//Variable to store reference to getContentPane() call for efficiency in adding components
	private Container con = getContentPane();
	RandomAccessFile employeeFile;
	
	//Sets maximum record size for uniformity in file to allow for accurate seeking
	final int RECORD_SIZE = 50;
		
	//Limiting variable for employee ID numbers
	final int NUMRECS = 100;
	
	//More efficient blank data entry for intended rewriting in file
	StringBuffer blanks = new StringBuffer(15);
	
	public CreateRandomEmployeeFile()
	{
		try
		{
			
			//Names file and gives read/write access
			employeeFile = new RandomAccessFile("Employees.dat","rw");
			
			//Using < operator to limit records to numbers 1-99
			for(int count = 0; count < NUMRECS; ++count)
			{
				
				//Creates blank records in .dat file
				employeeFile.writeInt(0);
	       		employeeFile.writeUTF(blanks.toString());
	       		employeeFile.writeUTF(blanks.toString());
	       		employeeFile.writeDouble(0.0);
			}
		}
		
		//Notifies and describes any error in initial file creation
		catch(IOException event)
		{
			System.out.println("Error message: " + event);
		}
		
		//Setting default size of GUI
		setSize(325, 225);
		
		//Flow layout adds components left to right and automatically flows to next line
	    con.setLayout(new FlowLayout());
	    
	    //Sets custom font and colors to title and background for better visual appeal
	    title.setFont(myFont);
	    title.setForeground(Color.WHITE);
	    con.setBackground(darkBlue);
	    
	    //Adds all elements to container and sets colors to text and button
	    con.add(title);
	    con.add(ask);
	    ask.setForeground(betterOrange);
	    con.add(idLabel);
	    idLabel.setForeground(betterOrange);
	    con.add(idField);
	    con.add(firstNameLabel);
	    firstNameLabel.setForeground(betterOrange);
	    con.add(firstNameField);
	    con.add(lastNameLabel);
	    lastNameLabel.setForeground(betterOrange);
	    con.add(lastNameField);
	    con.add(payRateLabel);
	    payRateLabel.setForeground(betterOrange);
	    con.add(payRateField);
	    con.add(enterDataButton);
	    enterDataButton.setBackground(darkBlue);
	    enterDataButton.setForeground(Color.WHITE);
	    
	    //Adds a listener to the button that creates new employee files
	    enterDataButton.addActionListener(this);
	    
	    //Lets user see window to interact with
	    setVisible(true);
	    
	    //Ends program when window is closed instead of hiding
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent event1)
	{
		
		//Used to prevent user from creating ID number entry higher than 99
		final int MAX_NUM = 99;
	    int num;
	    double pay;
	    try
	    {
	    	
	    	//Takes user input for Employee ID and Hourly Pay Rate and converts to workable numbers instead of Strings
	        num = Integer.parseInt(idField.getText());
	        pay = Double.parseDouble(payRateField.getText());
	        
	        //Sets record's employee ID to maximum (99) if user enters too many digits
	        if(num > MAX_NUM)
	           num = MAX_NUM;
	        
	        //Multiplied by record size to locate correct location to input new record
	        //-1 needed since record numbers start at zero 
	        //Example: for employee number 1, (1-1) = 0, and (0 * anything) = 0, so write begins at start of file
	        employeeFile.seek((num - 1) * RECORD_SIZE);
	        
	        //Writes each user input to the chosen record in the .dat file
	        employeeFile.writeInt(num);
	        employeeFile.writeUTF(firstNameField.getText());
	        employeeFile.writeUTF(lastNameField.getText());
	        employeeFile.writeDouble(pay);
	        
	        //Clears fields for next entry
	        idField.setText("");
	        lastNameField.setText("");
	        firstNameField.setText("");
	        payRateField.setText("");
	     }
	     
	     //Examples: Displays error message if user attempts to input a fraction (with a slash), 
	     //letters, or nothing for the Hourly Pay Rate or Employee ID
	     //Also triggers if a zero or a double (with decimal) is attempted for ID
	     catch(NumberFormatException event2)
	     {
	    	 System.err.println("Invalid or missing number input");
	     }
	     
	     //Example: Displays error message if pay rate entered is zero (unless an earlier exception is caught first)
	     catch(IOException event3)
	     {
	         System.err.println("File write error");
	         System.exit(1);
	     }
	}
	public static void main(String[] args)
	{
		
		//Warning suppression because warnings are annoying
		@SuppressWarnings("unused")
		CreateRandomEmployeeFile file = new CreateRandomEmployeeFile(); 
	}
}