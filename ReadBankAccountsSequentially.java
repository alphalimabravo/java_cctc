/** Alexis Berens
 *  CPT-237
 *  Project 1
 *  Part 2 of 3
 *  09/16/2018
 */

import java.nio.file.*;
import java.text.*;
import java.io.*;

public class ReadBankAccountsSequentially
{
   public static void main(String[] args)
   {
      Path file = Paths.get("C:\\Java\\Chapter.13\\RochesterBankAccounts.txt");
      
      //String array to hold pieces of file entries once split is done
      String[] array = new String[3];
      String s = "";
      String delimiter = ",";
      int id;
      String stringId;
      String name;
      double balance;
      DecimalFormat df = new DecimalFormat("00,000.00");
      
      try
      {
         InputStream input = new BufferedInputStream(Files.newInputStream(file));
         BufferedReader reader = new BufferedReader(new InputStreamReader(input));
         System.out.println();        
         s = reader.readLine();
         
         while(s != null)
         {
            array = s.split(delimiter);
            
            //Assigns first part of split string as the account number
            stringId = array[0];
            id = Integer.parseInt(array[0]);
            
            //Only pulls entries for print output that aren't the dummy zero values
            if(id !=  0)
            {
               name = array[1];
               balance = Double.parseDouble(array[2]);
               System.out.println("Account #" + stringId + "  " + name + "   $" + df.format(balance));
            }
            s = reader.readLine();           
         }
         reader.close();
      }
      
      catch(Exception e)
      {
         System.out.println("Message: " + e);
      }
   }
}