import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;

import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class GUIGameplay extends JFrame
{ 

	private ImageIcon square;
	private Image red;
	private Image grid;
	private int counter;         // counts seconds
	private int sqX, sqY, sqX2, sqY2, sqX3, sqY3;
	private int redX, redY;
	private int redSize;
	private int size, size2, size3;

	//	private long TimeStart;
	//	private long timediff;
	//	private int h;


	private song s;
	MediaPlayer player;

	private notesChart chart;
	private int bpm;

	private JPanel cont;
	private JLayeredPane one;
//	private JLabel sq1;
	//	private JLayeredPane two;
	//	private JLayeredPane three;
	//	private JLayeredPane four;
	//	private JLayeredPane five;
	//	private JLayeredPane six;
	//	private JLayeredPane seven;
	//	private JLayeredPane eight;
	//	private JLayeredPane nine;
	//	private JLabel big;

	//in the method that adds a panel with a square and displays it to the jlayeredpane, store the created layer to a arraylist. Iterate over entire arraylist to resize everything at once in the paint method. After a square is hidden, delete it from the arraylist.

	//BRO LOOK AT GRIDWORLD GUI AND SEE IF THAT HELPS AT ALLr

	public GUIGameplay() 
	{
		super("Gameplay test");

		cont = new JPanel(null);
		cont.add(one = new JLayeredPane());
		one.setBounds(0, 0, 675, 675);
		//		cont.add(two);
		//		cont.add(three);
		//		cont.add(four);
		//		cont.add(five);
		//		cont.add(six);
		//		cont.add(seven);
		//		cont.add(eight);
		//		cont.add(nine);

		ClassLoader cldr = this.getClass().getClassLoader();
		square = new ImageIcon(cldr.getResource("bluesquare.png"));
		red = new ImageIcon(cldr.getResource("redsquare.png")).getImage();
		grid = new ImageIcon(cldr.getResource("grid.png")).getImage();
		addWindowListener(new java.awt.event.WindowAdapter() {public void windowClosing(WindowEvent evt) {System.exit(0);}});




		add(cont);

		sqX = 87;
		sqY = 87;
		sqX2 = 238;
		sqY2 = 162;
		sqX3 = 163;
		sqY3 = 237;
		redX = 162;
		redY = 163;
		setSize(675, 675);
		setVisible(true);
		size = 0;
		size2 = 0;
		size3 = 0;
		redSize = 10;

		counter = 0;
	}

	public void setSong(song ss)
	{
		s = ss;
		bpm = s.getBpm();
	}

	public void startGame(int diff)
	{
		chart = s.getEasy();
	}

	public void startSong()
	{
		player = new MediaPlayer(s.getSong());
		player.play();
	}
	
	private class note implements Runnable
	{
		
		private int xcoord;
		private int ycoord;
		note(int x, int y)
		{
			xcoord = x;
			ycoord = y;
		}
		
		public void run() 
		{
			long TimeStart = System.currentTimeMillis();
			long timediff = 0;
			int size = 2;
			while(timediff<1000)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
				if(timediff % 50 == 0)
				{
					JLabel sq1 = new JLabel();
					sq1.setHorizontalAlignment(JLabel.CENTER);
					sq1.setBounds(0,0,xcoord,ycoord);
					one.add(sq1, 0);
					
					int h = (int)(timediff/50);
					size += h;
					ImageIcon scaled = new ImageIcon(square.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					sq1.setIcon(scaled);
					//				counter++;
					//				System.out.println("time is " + counter);
					//				repaint();
				}
				while((System.currentTimeMillis() - TimeStart) == timediff)
				{

				}
			}
		}
	}

	public void draw1()
	{
		note noteThread = new note(255, 255);
		Thread t = new Thread(noteThread);
		t.start();
	}


	//	public void paint(Graphics g)
	//	{
	//		Image offImage = createImage(1000, 1000);
	//		// Creates an off-screen drawable image to be used for
	//		// double buffering; XSIZE, YSIZE are each of type ‘int’;
	//		// represents size of JFrame or JPanel, etc
	//		Graphics buffer = offImage.getGraphics();
	//		// Creates a graphics context for drawing to an 
	//		// off-screen image
	////		paintOffScreen(buffer);		// your own method
	//		g.drawImage(offImage, 0, 0, null);	
	//		// draws the image with upper left corner at 0,0
	//	}

	//	public void paintOffScreen(Graphics g)
	//	{
	//		// sometimes helpful to do this first to clear things:
	//		g.clearRect(0, 0, 500, 500);
	//		g.drawImage(grid, 50, 50, this);
	//		if (h < 35)
	//		{
	//			g.drawImage(square, sqX, sqY, size, size, this);
	//		}
	//		if (h > 15 && h < 50)
	//		{
	//			g.drawImage(square, sqX2, sqY2, size2, size2, this);
	//		}
	//		if (h > 30 && h < 65)
	//		{
	//			g.drawImage(square, sqX3, sqY3, size3, size3, this);
	//		}
	//		if (h < 35)
	//		{
	//			g.drawImage(red, redX, redY, redSize, redSize, this);
	//		}
	//
	//	}

	//	public void updates()
	//	{
	//		TimeStart = System.currentTimeMillis();
	//		while(true)
	//		{
	//			timediff = (System.currentTimeMillis() - TimeStart);
	//			if(timediff % 50 == 0)
	//			{
	//				h = (int)(timediff/50);
	//				heck();
	//				counter++;
	//				System.out.println("time is " + counter);
	//				repaint();
	//			}
	//			while((System.currentTimeMillis() - TimeStart) == timediff)
	//			{
	//
	//			}
	//		}
	//
	//
	//	}
	//
	//
	//	public void heck()
	//	{
	//
	//
	//		if(h < 30)
	//		{
	//			sqX = 87-h;
	//			sqY = 87-h;
	//			size = 2*h;
	//		}
	//		if (h > 15 && h < 45)
	//		{
	//			sqX2 = 238-h+15;
	//			sqY2 = 162-h+15;
	//			size2 = 2*(h-15);
	//		}
	//		if (h > 30 && h < 60)
	//		{
	//			sqX3 = 163-h+30;
	//			sqY3 = 237-h+30;
	//			size3 = 2*(h-30);
	//		}
	//		if (h < 30)
	//		{
	//			redX = 162- (3*h);
	//			redY = 163- (3*h);
	//			redSize = 6*h ;
	//		}
	//  }

//USE COUNTER INSTEAD OF SIZE IN IF STATEMENTS LIKE YOU DO IN DRAW OFFSCREEN

}


