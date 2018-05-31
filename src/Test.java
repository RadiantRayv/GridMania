import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Test implements ActionListener {
	JPanel cards; //a panel that uses CardLayout
	JPanel menu;
	JPanel how;
	JPanel cred;
	JPanel card1;
	JPanel card2;
	JPanel card3;
	JPanel card4;
	JPanel game = new GUIGameplay();
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
	private JButton bBack3 = new JButton("Back");
	private JButton song1 = new JButton("Rob Gasser - Supersonic");
	private JButton song2 = new JButton("other song (Not coded yet)");
	private JButton song3 = new JButton("third song (Not coded either)");
	private JLabel select = new JLabel("select a song");
	private JLabel numpad = new JLabel("Do you have a numpad?");
	private JLabel songInfo = new JLabel("Song Info:");
	private JLabel info;
	private JLabel diff = new JLabel("Choose a difficulty");
	private JLabel lGame;
	private JLabel howto = new JLabel("Get gud");
	private JLabel credits = new JLabel("Game created by: Rayden Wang and Euan Cousar. ");
	private JLabel credits2 = new JLabel("External Resources used: ");
	private JLabel credits3 = new JLabel ("https://docs.oracle.com/javase/tutorial/index.html, https://youtu.be/TdEo002K2GQ");
	private song s;
	MediaPlayer player;

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
		bBack3.addActionListener(this);
		song1.addActionListener(this);


		//Create the "cards".
		card1 = new JPanel();
		card1.add(numpad); 
		card1.add(bDo);
		card1.add(bNot);

		menu = new JPanel();
		menu.setBackground(Color.PINK);
		menu.add(bPlay);
		menu.add(bHowToPlay);
		menu.add(bCredits);
		menu.add(bBack2, BorderLayout.SOUTH);

		card2 = new JPanel();
		card2.add(select);
		card2.add(song1);
		card2.add(song2);
		card2.add(song3);

		card3 = new JPanel();
		card3.add(diff);
		card3.add(bEasy);
		card3.add(bMedium);
		card3.add(bHard);
		card3.add(bBack3, BorderLayout.SOUTH);
		card3.add(songInfo);

		card4 = new JPanel();

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
		cards.add(card3, INFO);
		cards.add(card4, GAME);
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
			card2.add(bBack, BorderLayout.SOUTH);
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
			card4.add(game);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
		}
		if(evt.getSource() == bMedium)
		{
			stopSong();
			lGame = new JLabel(bMedium.getText());
			card4.add(lGame);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
		}
		if(evt.getSource() == bHard)
		{
			stopSong();
			lGame = new JLabel(bHard.getText());
			card4.add(lGame);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
			startSong();
		}
		if(evt.getSource() == bBack)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, MENU);
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
			info.setText("");
			stopSong();
		}
		if(evt.getSource() == song1)
		{
			String selected = song1.getText();
			s = new song("\\Songs\\" + selected);
			s.loadEasy("\\Songs\\" + selected);
			info = new JLabel(s.getInfo());
			card3.add(info);
			Test goog = new Test();	
			goog.setSong(s);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, INFO);
			startMid(45000.0);
		}
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

	public void stopSong()
	{
		player.stop();
	}

	public void startMid(double time)
	{
		player = new MediaPlayer(s.getSong());
		player.play();
		new SeekThread(time).run();
	}

	private class SeekThread implements Runnable
	{
		private double time;

		SeekThread(double t)
		{
			time = t;
		}
		public void run()
		{
			player.seek(new Duration(time));
		}
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
		frame.setSize(500, 500);
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