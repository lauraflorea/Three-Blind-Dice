import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.*;

public class ThreeBlindDice extends JFrame
{
	DisplayPanel display;
    TBDTable table;
    Scores score; 
    ControlPanel controls; 
    JPanel panel;
    JPanel menuPanel;
    JPanel picPanel; 
    JPanel buttonPanel; 

    String scores;
    int numPressed;   // Counts up the number of times the button to start/stop the music is pressed. 
	
    public ThreeBlindDice() 
	{
		super("Three Blind Dice"); 
		
		score = new Scores(); 
		scores = score.getScore(); 
		
		numPressed = 0;
		
		menuPanel = new JPanel(); 
		
		// Creates a new JPanel containing the title screen image.
		picPanel = new JPanel();
		ImageIcon title = new ImageIcon("C:\\Users\\laura\\Documents\\Java\\TBD.png"); 
		menuPanel.add(new JLabel(title), BorderLayout.NORTH); 
		
		// Creates a new panel to hold the buttons for the different screen interactions.
		buttonPanel = new JPanel(); 
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBorder(new EmptyBorder(1, 50, 0, 50));
		
		// Creates and adds the different buttons to a JPanel, adding it to the bottom of the screen.
		JButton instructions = new JButton("Instructions & Options");
		instructions.addActionListener(event -> instructions());
		buttonPanel.add(instructions, BorderLayout.WEST);
			
		JButton play = new JButton("Play Game");
		play.addActionListener(event -> play()); 
		buttonPanel.add(play, BorderLayout.CENTER);		
		
		JButton stats = new JButton("Game Statistics");
		stats.addActionListener(event -> stats()); 
		buttonPanel.add(stats, BorderLayout.EAST);
		
		menuPanel.add(buttonPanel, BorderLayout.SOUTH); 
		
		add(menuPanel); 
		
		backgroundMusic(); //Starts the music. 
	
	}
	
	// Method which displays a pop-up containing the instructions of the game, and also gives the user the option to play/ pause music. 
	public void instructions() 
	{
		
		Object[] optionList = {"OK", "Play / Pause Music"};
		
		
		int  n = JOptionPane.showOptionDialog(null, 
				"Welcome to Three Blind Dice! This game costs $2.00 to play. \n" 
						+ "\nINSTRUCTIONS: \n"
					    + "\n 1. The player chooses a number from 1 - 6 which they would like to end \n" 
					    + "up with on the dice. This will be the “number to get”. \n "
						+ "\n 2. The player then chooses another number which differs from \n" 
					    + "the first number chosen which they want to avoid on the 3 dice \n "
						+ "they roll. This will be the “number to avoid”. \n" 
					    + "\n 3. The player inputs these numbers into the game and rolls the virtual dice. \n" 
						+ "\n 4. The player will either make a profit, lose money, or break "
					    + "\n even based on the outcome they roll. \n"
					    + "\nAIM OF THE GAME:\n" 
						+ "\n"
					    + " The goal of the player is to roll as many of their “number to get” while\n" 
						+ "simultaneously avoiding the “number to avoid”. Even if the player’s roll produces \n"
					    + "one instance of the number they chose to avoid in any case, this will result in a \n"
						+ "loss of money. A “neutral number” (number not chosen by the player) will affect \n"
					    + "the player positively if rolled when “number to avoid” does not show up, and will \n"
						+ "mitigate the effects of the “number to avoid” if it is rolled. \n",
						"Instructions & Options" ,JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, optionList, null);
			
			if(n == JOptionPane.NO_OPTION) // If the "no" button is pressed, then the state of the background music will be toggled. 
			{
				numPressed++;
				
				// If the amounts of time the button is pressed is even, then the music will start.
				if(numPressed % 2 == 0) 
				{
					backgroundMusic();
				} 
				else //If this number is odd, then the music will stop. 
				{
					stopMusic(); 
				}	
			}	
		}
		
		// Method which sets up and begins the main component of the game. 
		public void play() 
		{   
		this.getContentPane().removeAll(); // Removes everything from the screen
		
		// Paints the components of the main game onto the screen in place of the menu screen.
		this.display = new DisplayPanel();
		this.table = new TBDTable(display);
	    this.controls = new ControlPanel(table); 
	    this.panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
	    panel.setBorder(new EmptyBorder(0, 5, 0, 5));
	    panel.add(display, BorderLayout.NORTH);
	    panel.add(table, BorderLayout.CENTER);
	    panel.add(controls, BorderLayout.SOUTH); 

	    add(panel); 
	    this.validate();
	    repaint(); 
	}
	
	// Method which displays the game's statistics through a pop-up. 
	public void stats() 
	{	
		JOptionPane.showMessageDialog(this,
			    " W = Number to get \t\t N = Neutral number \t\t L = Number to Avoid\n" 
			    + "\n" + scores,
			    "Statistics",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	static Clip clip;
	
	// Method which starts and stops the background music. 
	public static synchronized void backgroundMusic() 
	{
		  new Thread(new Runnable() 
		  {  
			  public void run() 
			  {
				  
		      try 
		      { 	  
		    	 clip = AudioSystem.getClip();
		        URL earl = this.getClass().getClassLoader().getResource("FZero.wav"); 
		        File file = new File("FZero.wav");
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
		        
		       
		        	clip.open(inputStream);
			        clip.start(); 
			        clip.loop(Clip.LOOP_CONTINUOUSLY);
		        
		      } 
		      catch (Exception e) 
		      {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}
	
	// Method which stops the music. 
	static void stopMusic() 
	{
		clip.stop(); 
	}
	
	
	public static void main(String[] args) 
	{
		 ThreeBlindDice window = new ThreeBlindDice();
		    window.setBounds(100, 100, 500, 500);
		    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    window.setResizable(false);
		    window.setVisible(true);
	}

}
