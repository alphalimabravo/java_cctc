/**Alexis Horn, CPT 168
Programming Project 4, 11/14/17
Part 1 of 1*/

import java.util.Scanner;
public class TwelveDays 
{
	public static void main(String[] args) 
	{
		String day;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Which day of Christmas is it?");
		day = input.nextLine();
		
		System.out.println("On the " + day + " day of Christmas, my true love gave to me...");
		
		switch(day)
		{
		case "0":
			System.out.println("Zero day? Is that like Doomsday? You get no presents! Try again.");
			break;
		case "42":
			System.out.println("The answer to life, the universe, and everything. I can't argue with that. You win.");
			break;
		case "Twelfth":
		case "twelfth":
		case "12th":
			System.out.println("Twelve drummers drumming,");
		case "Eleventh":
		case "eleventh":
		case "11th":
			System.out.println("Eleven pipers piping,");
		case "Tenth":
		case "tenth":
		case "10th":
			System.out.println("Ten lords a-leaping,");
		case "Ninth":
		case "ninth":
		case "9th":
			System.out.println("Nine ladies dancing,");
		case "Eighth":
		case "eighth":
		case "8th":
			System.out.println("Eight maids a-milking,");
		case "Seventh":
		case "seventh":
		case "7th":
			System.out.println("Seven swans a-swimming,");
		case "Sixth":
		case "sixth":
		case "6th":
			System.out.println("Six geese a-laying,");
		case "Fifth":
		case "fifth":
		case "5th":
			System.out.println("Five golden rings,");
		case "Fourth":
		case "fourth":
		case "4th":
			System.out.println("Four calling birds,");
		case "Third":
		case "third":
		case "3rd":
			System.out.println("Three French hens,");
		case "Second":
		case "second":
		case "2nd":
			System.out.println("Two turtle doves, and");
		case "First":
		case "first":
		case "1st":
			System.out.println("A partridge in a pear tree.");
			break;
		default:
			System.out.println();
			System.out.println("Oops! You entered an invalid day.");
			System.out.println("Hint: There are twelve days of Christmas. Also, try typing it as it would appear in the song. For example, add \"th\" to the number 5, to get \"5th\".");
			
		input.close();
		}
	}
}