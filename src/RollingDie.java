import java.awt.*; 
import javax.swing.*;

public class RollingDie extends Die
{
	// Constant which controls how fast the dice slow down. 
	private static final double SLOW_DOWN = 0.97;  
	private static final double SPEED_FACTOR = 0.04;
	private static final double SPEED_LIMIT = 2.0; // Maximum speed of the dice.

	private static int tableLeft, tableRight, tableTop, tableBottom;

	private final int DIE_SIZE = 24;
	private int xCentre, yCentre;
	private double xSpeed, ySpeed;

	// Sets the boundaries of the "table" that the dice will roll on.
	public static void setBounds(int left, int right, int top, int bottom)
	{
		tableLeft = left;
		tableRight = right;
		tableTop = top;
		tableBottom = bottom;
	}

	// Constructor which sets the die to be "off the table"
	public RollingDie()
	{
		xCentre = -1;
		yCentre = -1;
	}

	// Starts the die rolling
	public void roll()
	{
		super.roll();
		
		int width = tableRight - tableLeft; // x position of the dice on the table.
		int height = tableBottom - tableTop; // y position of the dice on the table.
		xCentre = tableLeft;
		yCentre = tableTop + height / 2;
		xSpeed = width * (Math.random() + 1) * SPEED_FACTOR;
		ySpeed = height * (Math.random() -.5) * 2. * SPEED_FACTOR;
	}

	// Returns true if this die is rolling; otherwise returns false
	public boolean isRolling()
	{
		// Based on the conditions of the speed of the die, returns true or false
		return xSpeed > SPEED_LIMIT || xSpeed < -SPEED_LIMIT       
				|| ySpeed > SPEED_LIMIT || ySpeed < -SPEED_LIMIT;
	}

	// Keeps moving this die as long as it overlaps with another one of the dice. 
	public void avoidCollision(RollingDie other)     
	{
		if (other == this)
		return;

		while (Math.abs(xCentre - other.xCentre) < DIE_SIZE && Math.abs(yCentre - other.yCentre) < DIE_SIZE) 
		{
			move();
		}
	}

	// Moves this die on the surface of the table, bouncing the die off the edges if required.
	private void move()
	{
		xCentre += xSpeed;
		yCentre += ySpeed;

		int radius = DIE_SIZE / 2;

		if (xCentre < tableLeft + radius)
		{
			xCentre = tableLeft + radius;
			xSpeed = -xSpeed;
		}

		if (xCentre > tableRight - radius)
		{
			xCentre = tableRight - radius;
			xSpeed = -xSpeed;
		}

		if (yCentre < tableTop + radius)
		{
			yCentre = tableTop + radius;
			ySpeed = -ySpeed;
		}

		if (yCentre > tableBottom - radius)
		{
			yCentre = tableBottom - radius;
			ySpeed = -ySpeed;
		}
	}

	// This method draws the die. It also moves the die if it is rolling.
	public void draw(Graphics g) 
	{
		if (xCentre < 0 || yCentre < 0) 
		{
			return;
		}	
		else if (isRolling()) // If the die is rolling then it will be slowed down.
		{
			move();
			drawRolling(g);
			xSpeed *= SLOW_DOWN;
			ySpeed *= SLOW_DOWN;
		}
		else
		{
			drawStopped(g); 
		}
	}

	// This method draws this die when rolling with a random number of dots.
	private void drawRolling(Graphics g)
	{
		// Coordinates of the die. 
		int x = xCentre - DIE_SIZE / 2 + (int)(3 * Math.random()) - 1;
		int y = yCentre - DIE_SIZE / 2 + (int)(3 * Math.random()) - 1;
		g.setColor(Color.WHITE);

		if (x % 2 != 0)
			g.fillRoundRect(x, y, DIE_SIZE, DIE_SIZE, DIE_SIZE/4, DIE_SIZE/4);
		else
			g.fillOval(x - 2, y - 2, DIE_SIZE + 4, DIE_SIZE + 4);

		// Draws the die with a random number of dots to simulate rolling.
		Die die = new Die();
		die.roll();
		drawDots(g, x, y, die.getNumDots());
	}

	// Draws this die when it is stopped (static).
	private void drawStopped(Graphics g)
	{
		int x = xCentre - DIE_SIZE / 2;
		int y = yCentre - DIE_SIZE / 2;
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, DIE_SIZE, DIE_SIZE, DIE_SIZE/4, DIE_SIZE/4);
		drawDots(g, x, y, getNumDots());
	}

	// This method draws a given number of dots on this die.
	private void drawDots(Graphics g, int x, int y, int numDots)
	{
		g.setColor(Color.BLACK);

		int dotSize = DIE_SIZE / 4; // Size of the dot. 
		int step = DIE_SIZE / 8; //
		
		// Locations of the 6 possible places to put dots on the die.
		int x1 = x + step - 1;
		int x2 = x + 3*step;
		int x3 = x + 5*step + 1;
		int y1 = y + step - 1;
		int y2 = y + 3*step;
		int y3 = y + 5*step + 1;

		// Draws the number of dots on die.
		switch (numDots)
		{
			case 1:
				g.fillOval(x2, y2, dotSize, dotSize);
				break;
			
			case 2: 
				g.fillOval(x1, y1, dotSize, dotSize);
				g.fillOval(x3, y3, dotSize, dotSize);
				break;
				
			case 3:
				g.fillOval(x1, y3, dotSize, dotSize);
				g.fillOval(x2, y2, dotSize, dotSize);
				g.fillOval(x3, y1, dotSize, dotSize);
				break;
				
			case 4:
				g.fillOval(x1, y1, dotSize, dotSize);
				g.fillOval(x1, y3, dotSize, dotSize);
				g.fillOval(x3, y1, dotSize, dotSize);
				g.fillOval(x3, y3, dotSize, dotSize);
				break;
				
			case 5:
				g.fillOval(x1, y1, dotSize, dotSize);
				g.fillOval(x1, y3, dotSize, dotSize);
				g.fillOval(x3, y1, dotSize, dotSize);
				g.fillOval(x3, y3, dotSize, dotSize);
				g.fillOval(x2, y2, dotSize, dotSize);
				break;
				
			case 6:
				g.fillOval(x1, y1, dotSize, dotSize);
				g.fillOval(x1, y2, dotSize, dotSize);
				g.fillOval(x1, y3, dotSize, dotSize);
				g.fillOval(x3, y1, dotSize, dotSize);
				g.fillOval(x3, y2, dotSize, dotSize);
				g.fillOval(x3, y3, dotSize, dotSize);
		}
	}
}
