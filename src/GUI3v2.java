import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;
import java.net.URL;

public class GUI3v2 extends JFrame
{ 

	private Image square;
	private Image red;
	private Image grid;
	private int counter;         // counts seconds
	private int sqX, sqY, sqX2, sqY2, sqX3, sqY3;
	private int redX, redY;
	private int redSize;
	private int size, size2, size3;
	
	private long TimeStart;
	private long timediff;

	public GUI3v2() 
	{
		super("Demo Graphics: Grid, Square, Red Square");
		ClassLoader cldr = this.getClass().getClassLoader();
		square = new ImageIcon(cldr.getResource("bluesquare.png")).getImage();
		red = new ImageIcon(cldr.getResource("redsquare.png")).getImage();
		grid = new ImageIcon(cldr.getResource("grid.png")).getImage();
		addWindowListener(new java.awt.event.WindowAdapter() {public void windowClosing(WindowEvent evt) {System.exit(0);}});
		sqX = 82;
		sqY = 82;
		sqX2 = 233;
		sqY2 = 157;
		sqX3 = 158;
		sqY3 = 232;
		redX = 157;
		redY = 158;
		setSize(500, 500);
		setVisible(true);
		size = 10;
		size2 = 10;
		size3 = 10;
		redSize = 10;
		
		counter = 0;
	}

	/*
	public void paint (Graphics g )
	{
		super.paint(g);
		g.drawImage(square, sqX, sqY, size, size, this);
		g.drawImage(grid, 50, 50, this);

	}
	*/
	
	public void paint(Graphics g)
	{
		Image offImage = createImage(1000, 1000);
	// Creates an off-screen drawable image to be used for
	// double buffering; XSIZE, YSIZE are each of type ‘int’;
	// represents size of JFrame or JPanel, etc
		Graphics buffer = offImage.getGraphics();
	// Creates a graphics context for drawing to an 
	// off-screen image
		paintOffScreen(buffer);		// your own method
		g.drawImage(offImage, 0, 0, null);	
	// draws the image with upper left corner at 0,0
	}

	public void paintOffScreen(Graphics g)
	{
		// sometimes helpful to do this first to clear things:
		g.clearRect(0, 0, 500, 500);
		g.drawImage(grid, 50, 50, this);
		if (counter < 42)
		{
			g.drawImage(square, sqX, sqY, size, size, this);
		}
		if (counter > 20 && counter < 60)
		{
			g.drawImage(square, sqX2, sqY2, size2, size2, this);
		}
		if (counter > 40 && counter < 80)
		{
			g.drawImage(square, sqX3, sqY3, size3, size3, this);
		}
		if (counter < 80)
		{
			g.drawImage(red, redX, redY, redSize, redSize, this);
		}
		
	}
	
	public void update()
	{
		TimeStart = System.currentTimeMillis();
		while(true)
		{
			timediff = (System.currentTimeMillis() - TimeStart);
			if(timediff % 25 == 0)
			{
				heck();
				counter++;
				System.out.println("time is " + counter);
				repaint();
			}
			while((System.currentTimeMillis() - TimeStart) == timediff)
			{
				
			}
		}

			
	}


	public void heck()
	{
		int h = (int)(timediff/25);
		
		if(size < 75)
		{
			sqX = 82-h;
			sqY = 82-h;
			size = 2*h;
		}
		if (size >= 50 && size2 < 75)
		{
			sqX2 = 233-h+25;
			sqY2 = 157-h+25;
			size2 = 2*h-50;
		}
		if (size2 >= 50 && size3 < 75)
		{
			sqX3 = 158-h+50;
			sqY3 = 232-h+50;
			size3 = 2*h-100;
		}
		if (size3 < 15)
		{
			redX = 157- (3*h);
			redY = 158- (3*h);
			redSize = 6*h ;
		}
		
		//USE COUNTER INSTEAD OF SIZE IN IF STATEMENTS LIKE YOU DO IN DRAW OFFSCREEN

	}

	public static void main(String[] args) 
	{
		GUI3v2 application = new GUI3v2();
		double x=0;
		while(x<1000000000)
		{
			x += 0.5;
		}
		application.update();

	}
}
