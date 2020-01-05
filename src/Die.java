import java.util.Random; 

public class Die 
{
	private int numDots; // Stores the int value of the number of dots on the die. 
	
	// Method which generates a random number of dots to put on the dice.
	public void roll()
	{
		Random r = new Random(); 
		numDots = r.nextInt(6) + 1; 
	}
	
	// Getter method which retrieves the number of dots to draw on the die.
	public int getNumDots() 
	{
		return numDots; 
	}
		
}
