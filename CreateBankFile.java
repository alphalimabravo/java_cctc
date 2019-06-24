/** Alexis Berens
 *  CPT-237
 *  Project 1
 *  Part 1 of 3
 *  09/16/2018
 */

import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.text.*;

public class CreateBankFile
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      Path file = Paths.get("C:\\Java\\Chapter.13\\RochesterBankAccounts.txt");
      String s = "0000,        ,00000.00" + System.getProperty("line.separator");
      final int RECSIZE = s.length();
      FileChannel fc = null;
      
      //"Number of records" constant to create default records in file
      final int NUMRECS = 10000;
      String delimiter = ",";
      String idString;
      int id;
      String name;
      double balance;
      
      //"Quit" constant identifies the end of data entry
      final String QUIT = "9999";
      
      try
      {
         OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
         
         //Creating the required 10,000 dummy file entries
         for(int count = 0; count < NUMRECS; ++count)
             writer.write(s, 0, s.length());
         
         writer.close();
      }
      catch(Exception e)
      {
          System.out.println("Error message: " + e);
      }
      
      //Accepting data entry from user
      try
      {
         fc = (FileChannel)Files.newByteChannel(file, CREATE, WRITE);
         
         System.out.print("Please enter customer's account number (four digits) >> ");
         idString = input.nextLine();
         
         while(!(idString.equals(QUIT)))
         {
            id = Integer.parseInt(idString);
            System.out.print("Please enter customer's name >> ");
            name = input.nextLine();
            StringBuilder sb = new StringBuilder(name);
            
            //Forces eight characters for name (adding spaces or truncating as needed)
            sb.setLength(8);
            name = sb.toString();
            System.out.print("Please enter account balance >> ");
            balance = input.nextDouble();
            input.nextLine();
            
            //Forces currency format and length to maintain data access integrity (assumes no input above 99,000.00)
            DecimalFormat df = new DecimalFormat("00000.00");
            s = idString + delimiter + name + delimiter + df.format(balance) + System.getProperty("line.separator");
            byte data[] = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);
            fc.position(id * RECSIZE);
            fc.write(buffer);
            System.out.print("Please enter next customer's account number, or " + QUIT + " to quit >> ");
            idString = input.nextLine();
         }
         fc.close();
      }
      catch (Exception e)
      {
          System.out.println("Error message: " + e);
      }
      input.close();
   }
}