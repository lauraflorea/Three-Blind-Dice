import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class TBDGame 
{	
	private int get, avoid, die1, die2, die3; 
	
	private int[] outcomes; // Array which stores amount of times the possible outcomes occur in the game.
	
	/* Constructor which retrieves the numbers chosen by the user and the values on the dice.
	 * It also initializes the array which contains the outcomes.   
	 */
	public TBDGame (int get, int avoid, int die1, int die2, int die3) 
	{	
		this.get = get; 
		this.avoid = avoid; 
		this.die1 = die1;
		this.die2 = die2;
		this.die3 = die3; 
	}
	/* Method which counts the number of matches of the number the user wants to "get" and the
	 * numbers which show up on the dice.  
	 */
	void setOutcomes(int[] outcomes) 
	{
		this.outcomes = outcomes; 
	}
	
	// Getter method which gets the outcomes from the array.
	public int[] getOutcomes() 
	{
		return outcomes;
	}
	
	// Method which counts the amount of times the values on the dice match the number the user chose to "get".
	private int countGets() 
	{
		int matches = 0; 
		
		if (die1 == get) 
		{
			matches++;
		}
		
		if (die2 == get) 
		{
			matches++;
		}
		
		if (die3 == get) 
		{
			matches++;
		}
		
		return matches; // Returns the number of times the number the user wants to "get" shows up on the dice.
	}
	
	/* Method which counts the number of matches of the number the user wants to "avoid" and the
	 * numbers which show up on the dice.  
	 */
	private int countAvoids() 
	{
		int matches = 0;
		
		if (die1 == avoid) 
		{
			matches++;
		}
	
		if (die2 == avoid) 
		{
			matches++;
		}
	
		if (die3 == avoid) 
		{
			matches++;
		}

		return matches; // Returns the number of times the number the user wants to "avoid" shows up on the dice.
	}
	
	// Method which determines the return of the user based on the outcome they achieve on their dice. 
	public double calculate() 
	{
		double money = 0; // Variable which stores the monetary return of the user. 
		
		int gets, avoids; 
		
		gets = countGets();
		avoids = countAvoids(); 
		
		if (gets == 0 && avoids == 0 ) // Outcome NNN (3 numbers the user didn't choose ( 3 neutral))
		{
			outcomes[0]++;  // Adds 1 to the amount of times this outcome occurred. 
			money = 2.00; 
		}
		
		if (gets == 1 && avoids == 0) // Outcome WNN (1 get, 2 neutral) 
		{
			outcomes[1]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 2.50;
		}
		
		if (gets == 1 && avoids == 1) // Outcome WNL (1 get, 1 neutral, 1 avoid)
		{
			outcomes[2]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 0.80;
		}
		
		if (gets == 1 && avoids == 2) // Outcome WLL (1 get, 2 avoids)
		{
			outcomes[3]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 0.20;
		}
		
		if (gets == 2 && avoids == 0) // Outcome WWN (2 gets, 1 neutral)
		{
			outcomes[4]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 2.70; 
		}
		
		if (gets == 2 && avoids == 1) // Outcome WWL (2 gets, 1 avoid)
		{
			outcomes[5]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 1.80;
		}
		
		if (gets == 3 && avoids == 0) // Outcome WWW (3 gets)
		{
			outcomes[6]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 5.00;
		}
		
		if (gets == 0 && avoids == 3) // Outcome LLL (3 avoids)
		{
			outcomes[7]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 0.00; 
		}
		
		if (gets == 0 && avoids == 1) // Outcome NNL (2 neutral, 1 avoid)
		{
			outcomes[8]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 1.50;
		}
		
		if (gets == 0 && avoids == 2)  // Outcome NLL (1 neutral, 2 avoid)
		{
			outcomes[9]++;	// Adds 1 to the amount of times this outcome occurred. 
			money = 0.10;
		}
		
		return money; // Returns the amount of money the user receives based on their outcome.
	}
}