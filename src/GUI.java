import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class GUI implements ActionListener {
	private JPanel cards;//a panel that uses CardLayout
	private BackgroundPanel menu, how, cred, card1, card2, cardg, fin; 
	private GUIGameplay gamegui;
	private GridBagConstraints c;
	final static String SONGS = "Card with song select";
	final static String PROMPT = "Card with prompt";
	final static String INFO = "Card with song info and difficulty";
	final static String GAME = "Card with the game";
	final static String MENU = "Card with menu";
	final static String HOW = "Card with how to play";
	final static String CRED = "Card with credits";
	final static String FIN = "Card with score";
	private JButton bDo, bNot, bEasy, bMedium, bHard, bPlay, bHowToPlay, bCredits, bBack, bBack2, bBack3, song1, song2, song3;
	private JLabel select, numpad, recommend, blank, logo, songInfo, info, diff, howto, credits, credits2, credits3, score, acc, perfect, ok, miss, name;
	private song s;
	private MediaPlayer player;
	private game ggg;
	private double accuracy;

	private Font font;
	private ClassLoader cldr;
	private Image bg;
	private Image bgcred;
	private Image bgprompt;
	private Image bghow;
	private Image bgscore;
	private Image bgsongs;

	public GUI()
	{


		cldr = this.getClass().getClassLoader();
		bg = new ImageIcon(cldr.getResource("other graphics/bg.png")).getImage();
		bgcred = new ImageIcon(cldr.getResource("other graphics/bgcredits.png")).getImage();
		bgprompt = new ImageIcon(cldr.getResource("other graphics/bgprompt.png")).getImage();
		bghow = new ImageIcon(cldr.getResource("other graphics/bginstructions.png")).getImage();
		bgscore = new ImageIcon(cldr.getResource("other graphics/bgscore.png")).getImage();
		bgsongs = new ImageIcon(cldr.getResource("other graphics/bgsongs.png")).getImage();

		try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Spaceport1.ttf"));
            numpad = new JLabel("DO YOu hAvE A numpAD?", SwingConstants.CENTER);
            recommend = new JLabel("(NumpAD highlY rECOmmEnDED)", SwingConstants.CENTER);
            score = new JLabel();
    		acc = new JLabel();
    		perfect = new JLabel();
    		ok = new JLabel(); 
    		miss = new JLabel();
    		name = new JLabel();
    		songInfo = new JLabel("SOng InfO:", SwingConstants.CENTER);
    		info = new JLabel(" ", SwingConstants.CENTER);
    		diff = new JLabel("ChOOsE A DiffiCultY", SwingConstants.CENTER);
    		select = new JLabel("sElEct A sOng", SwingConstants.CENTER);
            numpad.setFont(font.deriveFont(Font.BOLD, 20f));
            recommend.setFont(font.deriveFont(Font.BOLD, 12f));
            score.setFont(font.deriveFont(Font.BOLD, 100f));
            acc.setFont(font.deriveFont(Font.BOLD, 16f));
            perfect.setFont(font.deriveFont(Font.BOLD, 16f));
            ok.setFont(font.deriveFont(Font.BOLD, 16f));
            miss.setFont(font.deriveFont(Font.BOLD, 16f));
            name.setFont(font.deriveFont(Font.BOLD, 16f));
            songInfo.setFont(font.deriveFont(Font.BOLD, 16f));
            info.setFont(font.deriveFont(Font.BOLD, 16f));
            diff.setFont(font.deriveFont(Font.BOLD, 16f));
            select.setFont(font.deriveFont(Font.BOLD, 16f));
            numpad.setForeground(Color.CYAN);
            recommend.setForeground(Color.magenta);
            acc.setForeground(Color.CYAN);
            perfect.setForeground(Color.green);
            ok.setForeground(Color.magenta);
            miss.setForeground(Color.red);
            name.setForeground(Color.CYAN);
            songInfo.setForeground(Color.CYAN);
            info.setForeground(Color.magenta);
            diff.setForeground(Color.magenta);
            select.setForeground(Color.CYAN);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }



		cards = new JPanel(new CardLayout());
		card1 = new BackgroundPanel(bgprompt); 
		menu = new BackgroundPanel(bg);
		card2 = new BackgroundPanel(bgsongs);
		how = new BackgroundPanel(bghow);
		cred = new BackgroundPanel(bgcred);
		fin = new BackgroundPanel(bgscore);
		gamegui = new GUIGameplay();
		cardg = gamegui.getCont();
		c = new GridBagConstraints();
		
		bDo = new JButton(new ImageIcon(cldr.getResource("buttons/prompt/yes.png")));
		bDo.setFocusPainted(false);
		
		bNot = new JButton(new ImageIcon(cldr.getResource("buttons/prompt/no.png")));
		bNot.setFocusPainted(false);

		bEasy = new JButton(new ImageIcon(cldr.getResource("buttons/song select/easy.png")));
		bEasy.setFocusPainted(false);

		bMedium = new JButton(new ImageIcon(cldr.getResource("buttons/song select/med.png")));
		bMedium.setFocusPainted(false);

		bHard = new JButton(new ImageIcon(cldr.getResource("buttons/song select/hard.png")));
		bHard.setFocusPainted(false);

		bPlay = new JButton(new ImageIcon(cldr.getResource("buttons/main menu/play.png")));
		bPlay.setFocusPainted(false);

		bHowToPlay = new JButton(new ImageIcon(cldr.getResource("buttons/main menu/howto.png")));
		bHowToPlay.setFocusPainted(false);

		bCredits = new JButton(new ImageIcon(cldr.getResource("buttons/main menu/credits.png")));
		bCredits.setFocusPainted(false);

		bBack = new JButton(new ImageIcon(cldr.getResource("buttons/squareBack.png")));
		bBack.setFocusPainted(false);
		bBack.setContentAreaFilled(false);

		bBack2 = new JButton(new ImageIcon(cldr.getResource("buttons/main menu/back.png")));
		bBack2.setFocusPainted(false);

		bBack3 = new JButton(new ImageIcon(cldr.getResource("buttons/main menu/back.png")));
		bBack3.setFocusPainted(false);
		bBack3.setContentAreaFilled(false);

		song1 = new JButton(new ImageIcon(cldr.getResource("buttons/song select/supersonic.png")));
		song1.setFocusPainted(false);

		song2 = new JButton(new ImageIcon(cldr.getResource("buttons/song select/bliss.png")));
		song2.setFocusPainted(false);

		song3 = new JButton(new ImageIcon(cldr.getResource("buttons/song select/here.png")));
		song3.setFocusPainted(false);

		
		blank = new JLabel("");
		logo = new JLabel(new ImageIcon(cldr.getResource("other graphics/logo.png")), SwingConstants.CENTER);
		howto = new JLabel();
		credits = new JLabel();
		credits2 = new JLabel();
		credits3 = new JLabel ();
		
		accuracy = 0.0;


	}
	
	public Font getFont()
	{
		return font;
	}

	public void addComponentToPane(Container pane) {
		bDo.addActionListener(this);
		bNot.addActionListener(this);
		bEasy.addActionListener(this);
		bMedium.addActionListener(this);
		bHard.addActionListener(this);
		bPlay.addActionListener(this);
		bHowToPlay.addActionListener(this);
		bCredits.addActionListener(this);
		bBack.addActionListener(this);
		bBack2.addActionListener(this);
		song1.addActionListener(this);
		song2.addActionListener(this);
		song3.addActionListener(this);
		bBack3.addActionListener(this);

		bDo.setPreferredSize(new Dimension(250, 75));
		bNot.setPreferredSize(new Dimension(250, 75));

		//Create the "cards".
		card1.setLayout(new GridBagLayout());
		c.insets = new Insets(20,20,20,20);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		card1.add(numpad, c); 
		c.gridy = 1;
		card1.add(recommend, c);
		c.gridy = 2;
		card1.add(bDo, c);
		c.gridy = 3;
		card1.add(bNot, c);
		c.gridy = 4;
		card1.add(blank, c);

		bPlay.setPreferredSize(new Dimension(200, 50));
		bHowToPlay.setPreferredSize(new Dimension(200, 50));
		bCredits.setPreferredSize(new Dimension(200, 50));
		bBack2.setPreferredSize(new Dimension(200, 50));

		menu.setLayout(new GridBagLayout());
		c.insets = new Insets(10,10,10,10);
		c.gridy = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		menu.add(logo, c); 
		c.gridy = 1;
		menu.add(bPlay, c);
		c.gridy = 2;
		menu.add(bHowToPlay, c);
		c.gridy = 3;		
		menu.add(bCredits, c);
		c.gridy = 4;
		menu.add(bBack2, c);
		c.gridy = 5;
		menu.add(blank, c);

		bEasy.setPreferredSize(new Dimension(130, 75));
		bMedium.setPreferredSize(new Dimension(130, 75));
		bHard.setPreferredSize(new Dimension(130, 75));
		bBack.setPreferredSize(new Dimension(75, 75));

		song1.setPreferredSize(new Dimension(560, 106));
		song2.setPreferredSize(new Dimension(560, 106));
		song3.setPreferredSize(new Dimension(560, 106));

		card2.setLayout(new GridBagLayout());
		c.insets = new Insets(15,15,15,15);
		c.gridy = 0;
		c.gridwidth = 5;
		c.gridx = 1;
		card2.add(songInfo, c); 
		c.gridy = 1;
		card2.add(info, c);
		c.gridy = 2;
		c.weighty = 0.0; 	
		card2.add(select, c);
		c.gridy = 3;
		c.weighty = 0.5;
		card2.add(song1, c);
		c.gridy = 4;
		card2.add(song2, c);
		c.gridy = 5;
		card2.add(song3, c);
		c.gridy = 6;
		c.gridx = 2;
		c.weighty = 0;
		card2.add(diff, c);
		c.gridy = 7;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space     
		c.weightx = 1.5; 
		c.gridwidth = 2;
		c.gridx = 0;
		card2.add(bBack, c);
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 2;
		card2.add(bEasy, c);
		c.gridx = 3;
		card2.add(bMedium, c);
		c.gridx = 4;
		card2.add(bHard, c);       

		how.add(howto);

		cred.add(credits);
		cred.add(credits2);
		cred.add(credits3);

		bBack3.setMaximumSize(new Dimension(550, 75));
		bBack3.setPreferredSize(new Dimension(500,75));

		fin.setLayout(new GridBagLayout());
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.PAGE_START;
		//c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		fin.add(score, c);
		c.gridx = 1;
		c.gridwidth = 2;
		fin.add(name, c);
		c.gridwidth = 1; 
		c.gridy = 1;
		fin.add(acc, c);
		c.gridy = 2;
		fin.add(perfect, c);
		c.gridy = 3;
		fin.add(ok, c);
		c.gridy = 4;
		fin.add(miss, c);
		c.gridy = 5;
		c.gridwidth = 3;
		fin.add(bBack3, c);

		bBack3.setMaximumSize(new Dimension(550, 75));
		bBack3.setPreferredSize(new Dimension(500,75));	
		cardg.setLayout(new BoxLayout(cardg, BoxLayout.Y_AXIS));
		bBack3.setAlignmentX(Component.CENTER_ALIGNMENT);
		cardg.add(bBack3);

		//Create the panel that contains the "cards".
		cards.add(card1, PROMPT);
		cards.add(card2, SONGS);
		cards.add(cardg, GAME);
		cards.add(menu, MENU);
		cards.add(how, HOW);
		cards.add(cred, CRED);
		cards.add(fin, FIN);

		pane.add(cards, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == bDo)
		{
			gamegui.setKeysYes();
			gamegui.setKeysFinal();
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, MENU);
		}
		if(evt.getSource() == bNot)
		{
			gamegui.setKeysNo();
			gamegui.setKeysFinal();
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, MENU);
		}
		if(evt.getSource() == bPlay)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, SONGS);
			c.insets = new Insets(15,15,15,15);
			c.gridy = 7;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.PAGE_END; //bottom of space     
			c.weightx = 1.5; 
			c.gridwidth = 2;
			c.gridx = 0;
			card2.add(bBack, c);

		}
		if(evt.getSource() == bHowToPlay)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, HOW);
			how.add(bBack, BorderLayout.SOUTH);
		}
		if(evt.getSource() == bCredits)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, CRED);
			cred.add(bBack, BorderLayout.SOUTH);
		}
		if(evt.getSource() == bEasy)
		{
			stopSong();
			ggg = new game(s.getEasy(), gamegui, this);
			gamegui.addGame(ggg);
			cardg.add(bBack3);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
			Thread t = new Thread(ggg);
			t.start();
		}
		if(evt.getSource() == bMedium)
		{
			stopSong();
			ggg = new game(s.getMedium(), gamegui, this);
			gamegui.addGame(ggg);
			cardg.add(bBack3);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
			Thread t = new Thread(ggg);
			t.start();
		}
		if(evt.getSource() == bHard)
		{
			stopSong();
			ggg = new game(s.getHard(), gamegui, this);
			gamegui.addGame(ggg);
			cardg.add(bBack3);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
			Thread t = new Thread(ggg);
			t.start();
		}
		if(evt.getSource() == bBack)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, MENU);
			info.setText(" ");
			if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				stopSong();
			}
		}
		if(evt.getSource() == bBack2)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, PROMPT);
		}
		if(evt.getSource() == bBack3)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, SONGS);
			if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				stopSong();
			}
			ggg.stopRunning();
			ggg = null;
			delay();
			info.setText(" ");

		}
		if(evt.getSource() == song1)
		{
			if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				stopSong();
			}
			player = null;
			c.fill = GridBagConstraints.HORIZONTAL;
			s = new song("Rob Gasser - Supersonic");
			s.loadEasy();
			s.loadMedium();
			s.loadHard();
			info.setText(s.getInfo());
			name.setText(s.getName());
			if(player == null)
				setSong(s);
			if(!player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				startMid(45000.0);
			}
		}
		if(evt.getSource() == song2)
		{
			if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				stopSong();
			}
			player = null;
			c.fill = GridBagConstraints.HORIZONTAL;
			s = new song("TARI & Yix - Bliss");
			s.loadEasy();
			s.loadMedium();
			s.loadHard();
			info.setText(s.getInfo());
			name.setText(s.getName());
			if(player == null)
				setSong(s);
			if(!player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				startMid(44900.0);
			}
		}
		if(evt.getSource() == song3)
		{
			if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				stopSong();
			}
			player = null;
			c.fill = GridBagConstraints.HORIZONTAL;
			s = new song("Laszlo - Here We Are");
			s.loadEasy();
			s.loadMedium();
			s.loadHard();
			info.setText(s.getInfo());
			name.setText(s.getName());
			if(player == null)
				setSong(s);
			if(!player.getStatus().equals(MediaPlayer.Status.PLAYING))
			{
				startMid(22640.0);
			}
		}
	}
	
	private void delay()
	{
		long TimeStart = System.currentTimeMillis();
		while(System.currentTimeMillis() - TimeStart < 3000)
		{
		}
	}

	public void isDone(boolean pass)
	{
		accuracy = gamegui.getAcc();
		acc.setText("ACCurACY: " + gamegui.getAccString());
		perfect.setText("PErfECt: " + ggg.getPerfect());
		ok.setText("OkAy: " + ggg.getOkay());
		miss.setText("Miss: " + ggg.getMiss());
		if(pass)
		{
			if(accuracy == 100)
			{
				score.setText("SS");
				score.setForeground(Color.white);
			}
			else if(accuracy >= 90)
			{

				if(ggg.getMiss() == 0)
				{
					score.setText("S");
					score.setForeground(Color.yellow);
				}
				else 
				{
					score.setText("A");
					score.setForeground(Color.GREEN);
				}
			}
			else if(accuracy >= 80)
			{
				score.setText("B");
				score.setForeground(Color.BLUE);
			}
			else if(accuracy >= 70)
			{
				score.setText("C");
				score.setForeground(Color.MAGENTA);
			}
				
			else if(accuracy >= 60)
			{
				score.setText("D");
				score.setForeground(Color.ORANGE);
			}
			else
			{
				score.setText("F");
				score.setForeground(Color.red);
			}
		}
		else 
		{
			score.setText("F");
			score.setForeground(Color.red);
		}
		c.gridy = 5;
		c.gridwidth = 3;
		fin.add(bBack3, c);
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, FIN);
	}

	public void setSong(song ss)
	{
		s = ss;
		player = new MediaPlayer(s.getSong());
	}

	public void startSong()
	{
		player.setStartTime(Duration.millis(0.0));
		player.play();
	}

	public void stopSong()
	{
		player.stop();
	}

	public void startMid(double time)
	{

		player.setStartTime(Duration.millis(time));
		player.play();
	}


	public void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		//Create and set up the window.
		JFrame frame = new JFrame("Grid Beats");
		frame.setResizable(false);
		frame.setSize(600, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		GUI demo = new GUI();
		demo.addComponentToPane(frame.getContentPane());

		//Display the window.
		frame.setVisible(true);
	}

}