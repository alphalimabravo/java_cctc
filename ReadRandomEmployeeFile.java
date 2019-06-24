/** Alexis Berens
 *  CPT-237
 *  Project 3
 *  Part 2 of 2
 *  11/04/2018
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class ReadRandomEmployeeFile extends JFrame implements ActionListener
{
	
	//Maintained look and feel of "Create" file by copying details needed
	private JLabel title = new JLabel("Employee Record System");
	Font myFont = new Font("Impact", Font.PLAIN, 20);
    Color darkBlue = new Color(25, 0, 75);
    Color betterOrange = new Color(255, 200, 0);
	private JTextField idField = new JTextField(5);	
	private JLabel idLabel = new JLabel("ID number (valid numbers are 1-99)");
	private JLabel firstNameLabel = new JLabel("Legal first name");
	private JTextField firstNameField = new JTextField(15);
	private JLabel lastNameLabel = new JLabel("Legal last name");
	private JTextField lastNameField = new JTextField(15);
	private JLabel payRateLabel = new JLabel("Hourly pay rate");
	private JTextField payRateField = new JTextField(10);
	
	//Allow user to enter an employee ID and click the button to retrieve stored data on that employee
	private JButton enterDataButton = new JButton("Enter ID# and press this button to view record");
	private Container con = getContentPane();
	RandomAccessFile employeeFile;
	final int RECORD_SIZE = 50;
	final int NUMRECS = 100;
	
	public ReadRandomEmployeeFile()
	{
		try
	    {
	      employeeFile = new RandomAccessFile("Employees.dat","rw"); 
	    }
		
		//Notifies and describes any error in initial file access
	    catch(IOException event)
	    {
	    	System.out.println("Error message: " + event);
	    }
		
		setSize(325, 225);
	    con.setLayout(new FlowLayout());
	    title.setFont(myFont);
	    title.setForeground(Color.WHITE);
	    con.setBackground(darkBlue);
	    con.add(title);
	    con.add(idLabel);
	    idLabel.setForeground(betterOrange);
	    con.add(idField);
	    con.add(enterDataButton);
	    enterDataButton.setBackground(darkBlue);
	    enterDataButton.setForeground(Color.WHITE);
	    
	    //Adds a listener to the button that retrieves desired employee record
	    enterDataButton.addActionListener(this);
	    con.add(firstNameLabel);
	    firstNameLabel.setForeground(betterOrange);
	    con.add(firstNameField);
	    con.add(lastNameLabel);
	    lastNameLabel.setForeground(betterOrange);
	    con.add(lastNameField);
	    con.add(payRateLabel);
	    payRateLabel.setForeground(betterOrange);
	    con.add(payRateField);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent event1)
	{
		
		//Creates variables for all fields since user will only be inputting one
		String firstName;
		String lastName;
	    int num;
	    double pay;
	    try
	    {
	        num = Integer.parseInt(idField.getText());
	        employeeFile.seek((num - 1) * RECORD_SIZE);
	        
	        //Forces currency format to display both decimal places in return values for Hourly Pay Rate
	        DecimalFormat df = new DecimalFormat("0.00");
	        
	        //Reads each value from the chosen record in the .dat file
	        num = employeeFile.readInt();
	        firstName = employeeFile.readUTF();
	        lastName = employeeFile.readUTF();
	        pay = employeeFile.readDouble();
	         
	        //Populates text fields with information for chosen record
	        idField.setText("" + num);
	        firstNameField.setText(firstName);
	        lastNameField.setText(lastName);
	        payRateField.setText("" + df.format(pay));
	    }
	     
	    //Examples: Displays error message if user attempts to input a fraction (with a slash), 
	    //letters, a double (with decimal), or nothing for the Employee ID
	    catch(NumberFormatException event2)
	    {
	    	System.err.println("Invalid or missing number input");
	    }
	     
	    //Examples: Displays error message if user attempts to input a number >99 or <1
	    catch(IOException event3)
	    {
	    	System.err.println("Does not exist");
	        System.exit(1);
	    }
	}
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		ReadRandomEmployeeFile file = new ReadRandomEmployeeFile(); 
	}
}