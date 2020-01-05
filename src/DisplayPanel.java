import java.awt.*;
import java.awt.List;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class DisplayPanel extends JPanel 
{
	private JTextField getText, avoidText;
	private int numGet, numAvoid;
	private String sNumGet, sNumAvoid;
	private TBDTable table; 
	
	// Constructor which displays and asks the user to choose the two numbers they want/ don't want on their dice. 
	public DisplayPanel() 
	{
		super(new GridLayout(2, 2, 10, 0)); // Creates a 2 x 2 matrix for the labels of "Get" and "Avoid" numbers. 
		setBorder(new EmptyBorder(0, 0, 5, 0));
		
		// Array which contains the possible choices the user can pick in the drop down menu. 
		String[] num = {"1", "2", "3", "4", "5", "6"}; 
		
		// Pop=Gets the chooser to choose the number they want to show up on their dice. 
		String sNumGet = (String)JOptionPane.showInputDialog(
						this,
						"Choose a number to get: ",
								"Three Blind Dice",
								JOptionPane.PLAIN_MESSAGE,
								null,
								num, 
								num[0]	
								);

		numGet = Integer.parseInt(sNumGet); // Gets the numerical value of the String. 
		
		/* Since the user cannot choose the same number to not be on their dice, it is 
		 * removed from the array before they can make their selection for the number
		 * they want to avoid.
		 */
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(num));
		list.remove(Integer.toString(numGet));
		num = list.toArray(new String[5]); 
		
		// Gets the chooser to choose the number they want to show up on their dice. 
		String sNumAvoid = (String)JOptionPane.showInputDialog(
						this,
						"Choose a number to avoid: ",
								"Three Blind Dice",
								JOptionPane.PLAIN_MESSAGE,
								null,
								num, 
								num[0]
								);
		
		numAvoid = Integer.parseInt(sNumAvoid); // Gets the numerical value of the String.
		
		// Creates the labels for the "get" and "avoid" numbers.
		add(new JLabel("    Get:"));
		add(new JLabel("    Avoid:"));

		Font displayFont = new Font("Monospaced", Font.BOLD, 16); // Sets the font for the labels.

		// Creates and adds to the JPanel the two uneditable TextFields where the "get" and "avoid" numbers will be situated.
		getText = new JTextField(sNumGet, 5); 
		getText.setFont(displayFont);
		getText.setEditable(false);
		getText.setBackground(Color.WHITE);
		add(getText);

		avoidText = new JTextField(sNumAvoid, 5); 
		avoidText.setFont(displayFont);
		avoidText.setEditable(false);
		avoidText.setBackground(Color.WHITE);
		add(avoidText);	
		
	  	}
	
	// Getter method which retrieves the number the user wants to show up on their dice. 
	public int getNumGet() 
	{
		return this.numGet;
	}
	
	// Getter method which retrieves the number the user doesn't want to show up on their dice. 
	public int getNumAvoid() 
	{
		return this.numAvoid; 
	}

}
