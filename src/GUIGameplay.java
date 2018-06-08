import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import java.io.IOException;

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
	private ImageIcon grid;

	private song s;
	private MediaPlayer player;

	private double acc;
	
	private BackgroundPanel cont;
	private JLayeredPane one;
	
	private JLabel bgGrid;
	private JLabel accuracy;
	private JLabel health;

	private InputMap inm;
	
	private Font font;

	public GUIGameplay() 
	{
		ClassLoader cldr = this.getClass().getClassLoader();
		cont = new BackgroundPanel(new ImageIcon(cldr.getResource("other graphics/bg.png")).getImage());
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		cont.add(one = new JLayeredPane());
		one.setBounds(0, 0, 529, 529);

		inm = cont.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

		square = new ImageIcon(cldr.getResource("gameplay elements/bluesquare.png"));
		blank = new ImageIcon(cldr.getResource("other graphics/blank.png"));
		red = new ImageIcon(cldr.getResource("gameplay elements/redsquare.png"));
		perfect = new ImageIcon(cldr.getResource("gameplay elements/perfect.png"));
		okay = new ImageIcon(cldr.getResource("gameplay elements/okay.png"));
		
		grid = new ImageIcon(cldr.getResource("gameplay elements/grid.png"));
		bgGrid = new JLabel(grid);
		bgGrid.setBounds(35, 35, 529, 529);
		one.add(bgGrid, 0);
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Spaceport1.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		acc = 100.0;
		accuracy = new JLabel("Accuracy = 100.00%" , SwingConstants.CENTER);
		accuracy.setPreferredSize(new Dimension(300, 20));
		accuracy.setFont(font.deriveFont(Font.BOLD, 20f));
		accuracy.setForeground(Color.CYAN);
		accuracy.setBounds(0, 0, 300, 40);
		one.add(accuracy);
		
		health = new JLabel("HP = 8/16" , SwingConstants.CENTER);
		health.setPreferredSize(new Dimension(300, 20));
		health.setFont(font.deriveFont(Font.BOLD, 20f));
		health.setForeground(Color.MAGENTA);
		health.setBounds(300, 0, 300, 40);
		one.add(health);


		//
		//		add(cont);


		
		
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
	}

	public void startSong()
	{
		player = new MediaPlayer(s.getSong());
		player.play();
	}
	
	public void displayAccuracy(double hits)
	{
		acc = (hits/g.getTotalNotesSoFar())*100;
		accuracy.setText("Accuracy = " +accFormat.format(acc) + "%");
//		System.out.println(accFormat.format(acc) + "%");
	}
	
	public String getAccString()
	{
		return accFormat.format(acc) + "%";
	}
	
	public double getAcc()
	{
		return acc;
	}
	
	public void displayHealth()
	{
		health.setText("Health = " + g.getHP() + "/16");
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
				maxSize = 528;
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
				if(timediff % 50 == 0)
				{
					sq.setHorizontalAlignment(JLabel.CENTER);
					sq.setBounds(xfirst, yfirst, xsecond, ysecond);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							one.add(sq, 0);
						}
					});


					int h = (int)(timediff/50)*12 + 5;
					ImageIcon scaled;
					if(isSpecial)
					{
						size = (int) (h * (175.0/255)) * 3;
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
			while(timediff < 1150)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
			}
			sq.setIcon(blank);
			g.nextNote(nextNote);
			g.incrementTotalNotes();
			displayAccuracy(g.getTotalHitsAccuracy());
			g.decreaseHP();
			displayHealth();
		}
	}

	public void draw(int index, notesAtTime nextNote)
	{
		switch(index)
		{
		case 0:
			new Thread(new note(36,36,175,175,false,nextNote)).start();
			break;
		case 1:
			new Thread(new note(212,36,175,175,false,nextNote)).start();
			break;
		case 2:
			new Thread(new note(388,36,175,175,false,nextNote)).start();
			break;
		case 3:
			new Thread(new note(36,212,175,175,false,nextNote)).start();
			break;
		case 4:
			new Thread(new note(212,212,175,175,false,nextNote)).start();
			break;
		case 5:
			new Thread(new note(388,212,175,175,false,nextNote)).start();
			break;
		case 6:
			new Thread(new note(36,388,175,175,false,nextNote)).start();
			break;
		case 7:
			new Thread(new note(212,388,175,175,false,nextNote)).start();
			break;
		case 8:
			new Thread(new note(388,388,175,175,false,nextNote)).start();
			break;
		case 9:
			new Thread(new note(36,36,528,528,true,nextNote)).start();
			break;
		}
	}
	
	
	private class judgement implements Runnable
	{

		private int xfirst;
		private int yfirst;
		private int xsecond;
		private int ysecond;
		private int judge;

		judgement(int x1, int y1, int x2, int y2, boolean special, int j)
		{
			xfirst = x1;
			yfirst = y1;
			xsecond = x2;
			ysecond = y2;
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
			new Thread(new judgement(36,36,175,175,false,judge)).start();
			break;
		case 1:
			new Thread(new judgement(212,36,175,175,false,judge)).start();
			break;
		case 2:
			new Thread(new judgement(388,36,175,175,false,judge)).start();
			break;
		case 3:
			new Thread(new judgement(36,212,175,175,false,judge)).start();
			break;
		case 4:
			new Thread(new judgement(212,212,175,175,false,judge)).start();
			break;
		case 5:
			new Thread(new judgement(388,212,175,175,false,judge)).start();
			break;
		case 6:
			new Thread(new judgement(36,388,175,175,false,judge)).start();
			break;
		case 7:
			new Thread(new judgement(212,388,175,175,false,judge)).start();
			break;
		case 8:
			new Thread(new judgement(388,388,175,175,false,judge)).start();
			break;
		case 9:
			new Thread(new judgement(212,212,175,175,true,judge)).start();
			break;
		}
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
		inm.put(KeyStroke.getKeyStroke("NUMPAD1"), "tap1");
		inm.put(KeyStroke.getKeyStroke("NUMPAD2"), "tap2");
		inm.put(KeyStroke.getKeyStroke("NUMPAD3"), "tap3");
		inm.put(KeyStroke.getKeyStroke("NUMPAD4"), "tap4");
		inm.put(KeyStroke.getKeyStroke("NUMPAD5"), "tap5");
		inm.put(KeyStroke.getKeyStroke("NUMPAD6"), "tap6");
		inm.put(KeyStroke.getKeyStroke("NUMPAD7"), "tap7");
		inm.put(KeyStroke.getKeyStroke("NUMPAD8"), "tap8");
		inm.put(KeyStroke.getKeyStroke("NUMPAD9"), "tap9");
		inm.put(KeyStroke.getKeyStroke("NUMPAD0"), "taps");
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


