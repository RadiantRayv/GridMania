import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;

import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class GUIGameplay extends JPanel
{ 

	private ImageIcon square;
	private ImageIcon blank;
	private ImageIcon red;
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

	//	private JPanel cont;
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
		add(one = new JLayeredPane());
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
		blank = new ImageIcon(cldr.getResource("blank.png"));
		red = new ImageIcon(cldr.getResource("redsquare.png"));
		grid = new ImageIcon(cldr.getResource("grid.png")).getImage();



		//
		//		add(cont);

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

		private int xfirst;
		private int yfirst;
		private int xsecond;
		private int ysecond;
		private boolean isSpecial;

		note(int x1, int y1, int x2, int y2, boolean special)
		{
			xfirst = x1;
			yfirst = y1;
			xsecond = x2;
			ysecond = y2;
			isSpecial = special;
		}

		public void run() 
		{
			long TimeStart = System.currentTimeMillis();
			long timediff = 0;
			int size = 2;
			JLabel sq1  = new JLabel();
			while(size<225)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
				if(timediff % 50 == 0)
				{
					sq1.setHorizontalAlignment(JLabel.CENTER);
					sq1.setBounds(xfirst, yfirst, xsecond, ysecond);
					one.add(sq1, 0);

					int h = (int)(timediff/50);
					size += h;
					ImageIcon scaled;
					if(isSpecial)
					{
						scaled = new ImageIcon(red.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					}
					else
					{
						scaled = new ImageIcon(square.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					}
					sq1.setIcon(scaled);


					//				counter++;
					//				System.out.println("time is " + counter);
					//				repaint();
				}
				while((System.currentTimeMillis() - TimeStart) == timediff)
				{

				}
			}
			while(timediff < 1250)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
			}
			sq1.setIcon(blank);

		}
	}

	public void draw(int index)
	{
		note noteThread;
		Thread t;
		switch(index)
		{
		case 1:
			noteThread = new note(0,0,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 2:
			noteThread = new note(225,0,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 3:
			noteThread = new note(450,0,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 4:
			noteThread = new note(0,225,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 5:
			noteThread = new note(225,225,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 6:
			noteThread = new note(450,225,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 7:
			noteThread = new note(0,450,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 8:
			noteThread = new note(225,450,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 9:
			noteThread = new note(450,450,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 10:
			noteThread = new note(225,225,225,225,true);
			t = new Thread(noteThread);
			t.start();
			break;

		}

	}

}


