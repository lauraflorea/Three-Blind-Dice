import java.awt.event.*; 
import javax.swing.*;

public class ControlPanel extends JPanel implements ActionListener 
{
	 private TBDTable table;

	  // Constructor which creates the "table" on which the dice are rolled, also creates the "Roll" button.
	  public ControlPanel(TBDTable t)
	  {
	    table = t;
	    JButton rollButton = new JButton("Roll");
	    rollButton.addActionListener(this); // Clicking the button will roll the virtual dice. 
	    this.add(rollButton); 
	  }

	  // Called when the roll button is clicked, rolling the dice. 
	  public void actionPerformed(ActionEvent e)
	  {
	    if (!table.diceAreRolling())
	      table.rollDice();
	  }
}