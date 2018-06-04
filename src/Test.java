import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Test implements ActionListener {
	private JPanel cards; //a panel that uses CardLayout
	private JPanel menu;
	private JPanel how;
	private JPanel cred;
	private JPanel card1;
	private JPanel card2;
	private JPanel gamegui = new GUIGameplay();
	private JPanel cardg = gamegui.getCont();
	private GridBagConstraints c = new GridBagConstraints();
	final static String SONGS = "Card with song select";
	final static String PROMPT = "Card with prompt";
	final static String INFO = "Card with song info and difficulty";
	final static String GAME = "Card with the game";
	final static String MENU = "Card with menu";
	final static String HOW = "Card with how to play";
	final static String CRED = "Card with credits";
	private JButton bDo = new JButton("I DO have a numpad");
	private JButton bNot = new JButton("I DO NOT have a numpad");
	private JButton bEasy = new JButton("Easy");
	private JButton bMedium = new JButton("Medium");
	private JButton bHard = new JButton("Hard");
	private JButton bPlay = new JButton("Play");
	private JButton bHowToPlay = new JButton("How To Play");
	private JButton bCredits = new JButton("Credits");
	private JButton bBack = new JButton("Back");
	private JButton bBack2 = new JButton("Back");
	private JButton song1 = new JButton("Rob Gasser - Supersonic");
	private JButton song2 = new JButton("other song (Not coded yet)");
	private JButton song3 = new JButton("third song (Not coded either)");
	private JLabel select = new JLabel("select a song", SwingConstants.CENTER);
	private JLabel numpad = new JLabel("Do you have a numpad?", SwingConstants.CENTER);
	private JLabel blank = new JLabel("");
	private JLabel logo = new JLabel("Grid Beats", SwingConstants.CENTER);
	private JLabel songInfo = new JLabel("Song Info:", SwingConstants.CENTER);
	private JLabel info = new JLabel(" ", SwingConstants.CENTER);
	private JLabel diff = new JLabel("Choose a difficulty", SwingConstants.CENTER);
	private JLabel lGame;
	private JLabel howto = new JLabel("Get gud");
	private JLabel credits = new JLabel("Game created by: Rayden Wang and Euan Cousar. ");
	private JLabel credits2 = new JLabel("External Resources used: ");
	private JLabel credits3 = new JLabel ("https://docs.oracle.com/javase/tutorial/index.html, https://youtu.be/TdEo002K2GQ");
	private song s;
	private MediaPlayer player;

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

		bDo.setPreferredSize(new Dimension(250, 75));
		bNot.setPreferredSize(new Dimension(250, 75));
		
		//Create the "cards".
		card1 = new JPanel();
		card1.setLayout(new GridBagLayout());
		c.insets = new Insets(20,20,20,20);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		card1.add(numpad, c); 
		c.gridy = 1;
		card1.add(bDo, c);
		c.gridy = 2;
		card1.add(bNot, c);
		c.gridy = 3;
		card1.add(blank, c);

		bPlay.setPreferredSize(new Dimension(200, 50));
		bHowToPlay.setPreferredSize(new Dimension(200, 50));
		bCredits.setPreferredSize(new Dimension(200, 50));
		bBack2.setPreferredSize(new Dimension(200, 50));
		
		menu = new JPanel();
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
		
		card2 = new JPanel();
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

		how = new JPanel(); 
		how.add(howto);

		cred = new JPanel();
		cred.add(credits);
		cred.add(credits2);
		cred.add(credits3);

		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, PROMPT);
		cards.add(card2, SONGS);
		cards.add(cardg, GAME);
		cards.add(menu, MENU);
		cards.add(how, HOW);
		cards.add(cred, CRED);

		pane.add(cards, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == bDo)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, MENU);
		}
		if(evt.getSource() == bNot)
		{
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
			game ggg = new game(s.getEasy(), gamegui);
			gamegui.addGame(ggg);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
			Thread t = new Thread(ggg);
			t.start();
		}
		if(evt.getSource() == bMedium)
		{
			stopSong();
			lGame = new JLabel(bMedium.getText());
			cardg.add(lGame);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
		}
		if(evt.getSource() == bHard)
		{
			stopSong();
			lGame = new JLabel(bHard.getText());
			cardg.add(lGame);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
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
		if(evt.getSource() == song1)
		{
			String selected = song1.getText();
			s = new song("\\Songs\\" + selected);
			s.loadEasy("\\Songs\\" + selected);
			c.fill = GridBagConstraints.HORIZONTAL;
			info.setText(s.getInfo());
			this.setSong(s);
			if(player.getStatus().equals(MediaPlayer.Status.UNKNOWN) || player.getStatus().equals(MediaPlayer.Status.STOPPED))
			{
				startMid(45000.0);
			}
		}
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


	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	public static void createAndShowGUI() {
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
		frame.setSize(600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		Test demo = new Test();
		demo.addComponentToPane(frame.getContentPane());

		//Display the window.
		frame.setVisible(true);
	}

	//	public static void main(String[] args) {
	//		JFXPanel f = new JFXPanel();
	//		/* Use an appropriate Look and Feel */
	//		try {
	//			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	//		} catch (UnsupportedLookAndFeelException ex) {
	//			ex.printStackTrace();
	//		} catch (IllegalAccessException ex) {
	//			ex.printStackTrace();
	//		} catch (InstantiationException ex) {
	//			ex.printStackTrace();
	//		} catch (ClassNotFoundException ex) {
	//			ex.printStackTrace();
	//		}
	//		/* Turn off metal's use of bold fonts */
	//		UIManager.put("swing.boldMetal", Boolean.FALSE);
	//		createAndShowGUI();
	//	}
}