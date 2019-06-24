/** Alexis Berens
 *  CPT-237
 *  Project 1
 *  Part 3 of 3
 *  09/16/2018
 */

import java.nio.file.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;

public class ReadBankAccountsRandomly
{
   public static void main(String[] args)  
   {
      Scanner keyboard = new Scanner(System.in);
      Path file = Paths.get("C:\\Java\\Chapter.13\\RochesterBankAccounts.txt");
      String s = "0000,        ,00000.00" + System.getProperty("line.separator");
      final int RECSIZE = s.length();
      byte data[] = s.getBytes();
      ByteBuffer buffer = ByteBuffer.wrap(data);
      FileChannel fc = null;
      String idString;
      int id;
      
      //Application-terminating value
      final String QUIT = "9999";
      
      try
      {
         fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
         System.out.print("Please enter customer's account number (four digits) >>");
         idString = keyboard.nextLine();
         
         //Allows user to check account information until QUIT value "9999" is entered
         while(!idString.equals(QUIT))
         {
            id = Integer.parseInt(idString); 
            
            //Locates desired record by multiplying the fixed record size by the account number entered
            fc.position(id * RECSIZE);
            fc.read(buffer);
            buffer= ByteBuffer.wrap(data);
            s = new String(data);
            System.out.println("You entered: " + id + "\nFile match: " + s);
            System.out.print("Please enter next customer's account number, or " + QUIT + " to quit >> ");
            idString = keyboard.nextLine();
         }
         fc.close();
      }
      
      catch (Exception e)
      {
          System.out.println("Error message: " + e);
      }
      keyboard.close();
   }
}