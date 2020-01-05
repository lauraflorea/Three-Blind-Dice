import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TBDTable extends JPanel implements ActionListener 
{
	private RollingDie die1, die2, die3;
	private final int DELAY = 20;
	private double money;
	private Timer clock;
	private TBDGame game;
	private Scores score;
	private DisplayPanel display;
	
	private String scoreStr; 
	
	// Constructor which creates the "table" for the dice to roll on, as well as initializes the dice and timer.
	public TBDTable(DisplayPanel disp)
	{
		score = new Scores(); 
		
		score.getScore(); 
		
		setBackground(Color.red.darker());
	    setBorder(new LineBorder(Color.ORANGE.darker(), 3));
	    display = disp;
	    die1 = new RollingDie();
	    die2 = new RollingDie();
	    die3 = new RollingDie();
	    clock = new Timer(DELAY, this);
	  }

	
	// This method rolls the dice (called when the "Roll" button is clicked).
	public void rollDice()
	{
	    RollingDie.setBounds(3, getWidth() - 3, 3, getHeight() - 3);
	    die1.roll();
	    die2.roll();
	    die3.roll();
	    clock.start(); // Starts the timer.
	}

	  // Method which processes the events of the timer.
	  public void actionPerformed(ActionEvent e)
	  {  
		  
		  // Checks to see the positions of the dice, and makes sure that they avoid collisions. 
		  if (diceAreRolling())
		  {   
		      if (die1.isRolling()) 
		      {
		    	  die1.avoidCollision(die2);
		    	  die1.avoidCollision(die3);
		      } 
		      else if (die2.isRolling()) 
		      {
		    	  die2.avoidCollision(die1);
		    	  die2.avoidCollision(die3);
		      } 
		      else if (die3.isRolling()) 
		      {
		    	  die3.avoidCollision(die1);
		    	  die3.avoidCollision(die2);
		      }        
	    }
	    else
	    {
	    	clock.stop(); // Stops the timer.
	    	
	    	repaint(); // Repaints the dice to show their final values.   
	    	
	    	// Calculates the outcome of the user and their monetary return based on the values rolled on the dice.
	    	game = new TBDGame(display.getNumGet(), display.getNumAvoid(), die1.getNumDots(), die2.getNumDots(), die3.getNumDots());
	    	game.setOutcomes(score.getOutcomes()); 
	    	score.setOutcomes(game.getOutcomes());
	    	scoreStr = score.getScore();
	    	
	    	money = game.calculate();   
	    	
	    	endGame(); 
	    }
		  repaint();
	  }

	  // Method which displays a pop-up at the end of the game.
	  public void endGame() 
	  {
		  Object[] options = {"See statistics", "Exit"};

		  String message; 
		  
		  // Displays a different message based on the user's outcome.
		  if(money == 5.00) 
		  {
			  	message = "JACKPOT! \n You receive $5.00.";  
		  } else if (money < 5.00 && money > 2.00) 
		  {
			  	message = "Congratulations! \n You receive $" + money + "0."; 
		  } else if (money == 2.00) 
		  {
			  	message = "Not bad... \n You receive $" + money + "0."; 
		  } else if (money < 2.00 && (money != 0.00)) 
		  {
			  	message = "Oh well... \n You receive $" + money + "0."; 
		  } else 
		  {
			  	message = "RIP... \n You receive $0.00."; 
		  }
	
		  				
			int  n = JOptionPane.showOptionDialog(null, 
					message,
					"Game Over" ,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, null);
				
			if (n == 0) 
			{	
				 JOptionPane.showMessageDialog(this,
						" W = Number to get \t\t N = Neutral number \t\t L = Number to Avoid\n"
						+ "\n" + scoreStr,
					    "Statistics",
					    JOptionPane.PLAIN_MESSAGE);
				 exit(); 
			} 
				
			if(n == JOptionPane.NO_OPTION) // If the "no" button is pressed, then the game will be exited. 
			{
				exit();
			}
	  }
	  
	 // Exits the game. 
	public static void exit()
	{
		System.exit(0);
	}
	  
	  // Method which returns true if dice are still rolling; otherwise returns false.
	public boolean diceAreRolling()
	{
	  return die1.isRolling() || die2.isRolling() || die3.isRolling();
	}

	  // Called automatically after repaint is called. 
	  public void paintComponent(Graphics g)
	  {
	    super.paintComponent(g);
	    die1.draw(g);
	    die2.draw(g);
	    die3.draw(g); 
	    
	  }
}
