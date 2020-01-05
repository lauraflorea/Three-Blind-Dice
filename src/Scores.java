import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Scores 
{	
	// Score file where all of the outcomes and their occurrences are stored.
	final static String SCORE_FILE = "scores.txt"; 
	int[] outcomes;

	public Scores() 
	{
		outcomes = new int[10]; // Initializes the integer array with 10 positions
	}
	
	// Setter method which sets the outcomes of the outcomes array. 
	public void setOutcomes(int[] arr) 
	{
		outcomes = arr; 
	}
	
	// Getter method which gets the outcomes of the outcomes array. 
	public int[] getOutcomes() 
	{
		return outcomes;
	}
	
	// Method which writes the outcome of the user to a file. 
	public void writeScore() 
	{
		try 
		{
			FileWriter writer = new FileWriter(SCORE_FILE);
			
			for (int i = 0; i < 10; i++) 
			{
				
				writer.write(Integer.toString(outcomes[i]));
				writer.write(System.getProperty("line.separator"));
			}
			writer.close();
		} catch (IOException ex) 
		{
			ex.printStackTrace();
			
		}
	}
	
	// Method which retrieves the outcome of the user to a file. 
	public String getScore() 
	{
		String[] elements;
		String popUpDisplay = "";
		
		File scoreFile = new File(SCORE_FILE);
		if( !scoreFile.exists()) 
		{
			 return "";  
		}
			
		try 
		{	
			FileReader fileReader = new FileReader(scoreFile);
			
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			int i = 0;
			
			while ( (line = reader.readLine()) != null) 
			{
					
				elements = line.split("\\s");
				outcomes[i] = Integer.parseInt(elements[0]);
				
				switch (i) 
				{
				case 0:
					popUpDisplay = popUpDisplay + "NNN: " + elements[0] + "\n";
					break;
				case 1: 
					popUpDisplay = popUpDisplay + "WNN: " + elements[0] + "\n";
					break;
				case 2:
					popUpDisplay = popUpDisplay + "WNL: " + elements[0] + "\n";
					break;
				case 3:
					popUpDisplay = popUpDisplay + "WLL: " + elements[0] + "\n";
					break;
				case 4:
					popUpDisplay = popUpDisplay + "WWN: " + elements[0] + "\n";
					break;
				case 5: 
					popUpDisplay = popUpDisplay + "WWL: " + elements[0] + "\n";
					break;
				case 6:
					popUpDisplay = popUpDisplay + "WWW: " + elements[0] + "\n";
					break;
				case 7:
					popUpDisplay = popUpDisplay + "LLL: " + elements[0] + "\n";
					break;
				case 8:
					popUpDisplay = popUpDisplay + "LNN: " + elements[0] + "\n";
					break;
				case 9:
					popUpDisplay = popUpDisplay + "LLN: " + elements[0] + "\n";
					break;
				}	
			
				i++;
			
			}
			reader.close();
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return popUpDisplay;
	}
}
