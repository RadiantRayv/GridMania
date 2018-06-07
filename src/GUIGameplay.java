import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;

import javafx.scene.media.*;

import java.text.DecimalFormat;
import java.net.URL;

public class GUIGameplay
{ 
	public static final DecimalFormat accFormat = new DecimalFormat("#.##");
	
	private game g;

	private ImageIcon square;
	private ImageIcon blank;
	private ImageIcon red;
	private ImageIcon perfect;
	private ImageIcon okay;
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
	private MediaPlayer player;
	String hitsound;
//	private MediaPlayer hits;

	private notesChart chart;
	private boolean[] currentNotes;
	private double currentTime;
	private double acc;
	private int bpm;
	
	private long TimeStart;

	private BackgroundPanel cont;
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

	InputMap inm;

	public GUIGameplay() 
	{
		ClassLoader cldr = this.getClass().getClassLoader();
		cont = new BackgroundPanel(new ImageIcon(cldr.getResource("other graphics/bg.png")).getImage());
		cont.add(one = new JLayeredPane());
		one.setBounds(0, 0, 529, 529);

		inm = cont.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

		//		cont.add(two);
		//		cont.add(three);
		//		cont.add(four);
		//		cont.add(five);
		//		cont.add(six);
		//		cont.add(seven);
		//		cont.add(eight);
		//		cont.add(nine);


		square = new ImageIcon(cldr.getResource("gameplay elements/bluesquare.png"));
		blank = new ImageIcon(cldr.getResource("other graphics/blank.png"));
		red = new ImageIcon(cldr.getResource("gameplay elements/redsquare.png"));
		grid = new ImageIcon(cldr.getResource("gameplay elements/grid.png")).getImage();
		perfect = new ImageIcon(cldr.getResource("gameplay elements/perfect.png"));
		okay = new ImageIcon(cldr.getResource("gameplay elements/okay.png"));
		hitsound = cldr.getResource("hit.wav").toString();


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
		//		setSize(675, 675);
		//		setVisible(true);
		size = 0;
		size2 = 0;
		size3 = 0;
		redSize = 10;

		counter = 0;
		acc = 100.0;
		
		
		TimeStart = System.currentTimeMillis();
	}

	public BackgroundPanel getCont()
	{
		return cont;
	}

	public void addGame(game gg)
	{
		g = gg;
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
		private notesAtTime nextNote;
		private int maxSize;

		note(int x1, int y1, int x2, int y2, boolean special, notesAtTime next)
		{
			xfirst = x1;
			yfirst = y1;
			xsecond = x2;
			ysecond = y2;
			isSpecial = special;
			nextNote = next;
			if(special)
				maxSize = 572;
			else
				maxSize = 175;
		}

		public void run() 
		{
			long TimeStart = System.currentTimeMillis();
			long timediff = 0;
			int size = 2;
			JLabel sq  = new JLabel();
			while(size<maxSize)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
				if(timediff % 25 == 0)
				{
					sq.setHorizontalAlignment(JLabel.CENTER);
					sq.setBounds(xfirst, yfirst, xsecond, ysecond);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							one.add(sq, 0);
						}
					});


					int h = (int)(timediff/25)*6 + 5;
					ImageIcon scaled;
					if(isSpecial)
					{
						size = h*3;
						scaled = new ImageIcon(red.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					}
					else
					{
						size = (int) (h * (175.0/255));
						scaled = new ImageIcon(square.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					}
					sq.setIcon(scaled);


					//				counter++;
					//				System.out.println("time is " + counter);
					//				repaint();
				}
				while((System.currentTimeMillis() - TimeStart) == timediff)
				{

				}
			}
			while(timediff < 1251)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
			}
			sq.setIcon(blank);
			g.nextNote(nextNote);
			g.incrementTotalNotes();
			displayAccuracy(g.getTotalHitsAccuracy());
		}
	}

	public void draw(int index, notesAtTime nextNote)
	{
		switch(index)
		{
		case 0:
			new Thread(new note(1,1,175,175,false,nextNote)).start();
			break;
		case 1:
			new Thread(new note(177,1,175,175,false,nextNote)).start();
			break;
		case 2:
			new Thread(new note(353,1,175,175,false,nextNote)).start();
			break;
		case 3:
			new Thread(new note(1,177,175,175,false,nextNote)).start();
			break;
		case 4:
			new Thread(new note(177,177,175,175,false,nextNote)).start();
			break;
		case 5:
			new Thread(new note(353,177,175,175,false,nextNote)).start();
			break;
		case 6:
			new Thread(new note(1,353,175,175,false,nextNote)).start();
			break;
		case 7:
			new Thread(new note(177,353,175,175,false,nextNote)).start();
			break;
		case 8:
			new Thread(new note(353,353,175,175,false,nextNote)).start();
			break;
		case 9:
			new Thread(new note(177,177,175,175,true,nextNote)).start();
			break;
		}
	}
	
	public void setCurrentNotes(boolean[] n, double time)
	{
		currentNotes = n;
		currentTime = time;
	}
	
	private class judgement implements Runnable
	{

		private int xfirst;
		private int yfirst;
		private int xsecond;
		private int ysecond;
		private boolean isSpecial;
		private int judge;

		judgement(int x1, int y1, int x2, int y2, boolean special, int j)
		{
			xfirst = x1;
			yfirst = y1;
			xsecond = x2;
			ysecond = y2;
			isSpecial = special;
			judge = j;
		}

		public void run() 
		{
			long TimeStart = System.currentTimeMillis();
			JLabel lb  = new JLabel();
			lb.setHorizontalAlignment(JLabel.CENTER);
			lb.setBounds(xfirst, yfirst, xsecond, ysecond);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					one.add(lb, 0);
				}
			});
			if(judge == 0)
			{
				lb.setIcon(perfect);
			}
			else
			{
				lb.setIcon(okay);
			}
			
			while(System.currentTimeMillis() - TimeStart < 250)
			{
			}
			lb.setIcon(blank);
		}
	}
	
	public void drawJudgement(int index, int judge)
	{
		switch(index)
		{
		case 0:
			new Thread(new judgement(1,1,175,175,false,judge)).start();
			break;
		case 1:
			new Thread(new judgement(177,1,175,175,false,judge)).start();
			break;
		case 2:
			new Thread(new judgement(353,1,175,175,false,judge)).start();
			break;
		case 3:
			new Thread(new judgement(1,177,175,175,false,judge)).start();
			break;
		case 4:
			new Thread(new judgement(177,177,175,175,false,judge)).start();
			break;
		case 5:
			new Thread(new judgement(353,177,175,175,false,judge)).start();
			break;
		case 6:
			new Thread(new judgement(1,353,175,175,false,judge)).start();
			break;
		case 7:
			new Thread(new judgement(177,353,175,175,false,judge)).start();
			break;
		case 8:
			new Thread(new judgement(353,353,175,175,false,judge)).start();
			break;
		case 9:
			new Thread(new judgement(177,177,175,175,true,judge)).start();
			break;
		}
	}
	
	public void displayAccuracy(double hits)
	{
		acc = (hits/g.getTotalNotesSoFar())*100;
		System.out.println(accFormat.format(acc) + "%");
	}
	
	public double getAcc()
	{
		return acc;
	}

	public void setKeysNo()
	{
		inm.clear();
		inm.put(KeyStroke.getKeyStroke('y'), "tap1");
		inm.put(KeyStroke.getKeyStroke('u'), "tap2");
		inm.put(KeyStroke.getKeyStroke('i'), "tap3");
		inm.put(KeyStroke.getKeyStroke('h'), "tap4");
		inm.put(KeyStroke.getKeyStroke('j'), "tap5");
		inm.put(KeyStroke.getKeyStroke('k'), "tap6");
		inm.put(KeyStroke.getKeyStroke('n'), "tap7");
		inm.put(KeyStroke.getKeyStroke('m'), "tap8");
		inm.put(KeyStroke.getKeyStroke(','), "tap9");	
		inm.put(KeyStroke.getKeyStroke(' '), "taps");
	}
	
	public void setKeysYes()
	{
		inm.clear();
		inm.put(KeyStroke.getKeyStroke("1"), "tap1");
		inm.put(KeyStroke.getKeyStroke("2"), "tap2");
		inm.put(KeyStroke.getKeyStroke("3"), "tap3");
		inm.put(KeyStroke.getKeyStroke("4"), "tap4");
		inm.put(KeyStroke.getKeyStroke("5"), "tap5");
		inm.put(KeyStroke.getKeyStroke("6"), "tap6");
		inm.put(KeyStroke.getKeyStroke("7"), "tap7");
		inm.put(KeyStroke.getKeyStroke("8"), "tap8");
		inm.put(KeyStroke.getKeyStroke("9"), "tap9");
		inm.put(KeyStroke.getKeyStroke("0"), "taps");
	}
	
	public void setKeysFinal()
	{
		cont.getActionMap().clear();
		cont.getActionMap().put("tap1", new tapAction(0));
		cont.getActionMap().put("tap2", new tapAction(1));
		cont.getActionMap().put("tap3", new tapAction(2));
		cont.getActionMap().put("tap4", new tapAction(3));
		cont.getActionMap().put("tap5", new tapAction(4));
		cont.getActionMap().put("tap6", new tapAction(5));
		cont.getActionMap().put("tap7", new tapAction(6));
		cont.getActionMap().put("tap8", new tapAction(7));
		cont.getActionMap().put("tap9", new tapAction(8));
		cont.getActionMap().put("taps", new tapAction(9));
	}

	private class tapAction extends AbstractAction
	{
		int note;

		tapAction(int n)
		{
			note = n;
		}

		public void actionPerformed(ActionEvent e) 
		{
//			Thread t = new Thread(new Runnable() {
//				public void run() {
//					MediaPlayer hits = new MediaPlayer(new Media(hitsound));
//					counter++;
//					System.out.println(counter);
//					hits.play();
//					long hitTimeDiff = System.currentTimeMillis();
//					if(hitTimeDiff < 500 + currentTime && hitTimeDiff > -500 + currentTime)
//					{
//
//						System.out.println("hit");
//						currentTime = -500;
//					}
					
			g.detectHit(note);
					
//				}
//			});
//
//			t.start();
		}
	}

}


