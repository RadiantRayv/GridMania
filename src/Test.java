import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Test implements ActionListener  {
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
	private JLabel numpad = new JLabel("Do you have a numpad?");
	private JLabel songInfo = new JLabel("Song Info:");
	private JLabel info;
	private JLabel diff = new JLabel("Choose a difficulty");
	private JLabel lGame;
	private JLabel howto = new JLabel("Get gud");
	private JLabel credits = new JLabel("Game created by: Rayden Wang and Euan Cousar. External Resources used: https://docs.oracle.com/javase/tutorial/index.html, https://youtu.be/TdEo002K2GQ");
	private String[] songs = {"song select", "Rob Gasser - Supersonic"};
	private JComboBox<String> comboBox1;
	private song s;
	MediaPlayer player;

	public void addComponentToPane(Container pane) {
		JPanel comboBoxPane = new JPanel(); //use FlowLayout
		bDo.addActionListener(this);
		bNot.addActionListener(this);
		bEasy.addActionListener(this);
		bMedium.addActionListener(this);
		bHard.addActionListener(this);
		bPlay.addActionListener(this);
		bHowToPlay.addActionListener(this);
		bCredits.addActionListener(this);
		

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

		card2 = new JPanel();
		comboBox1 = new JComboBox<String>(songs);
		comboBox1.setMaximumRowCount(4);
		comboBox1.addItemListener(new ComboBoxHandler());
		card2.add(comboBox1);

		card3 = new JPanel();
		card3.add(diff);
		card3.add(bEasy);
		card3.add(bMedium);
		card3.add(bHard);
		card3.add(songInfo);
		
		card4 = new JPanel();
		
		how = new JPanel(); 
		how.add(howto);
		
		cred = new JPanel();
		cred.add(credits);

		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, PROMPT);
		cards.add(card2, SONGS);
		cards.add(card3, INFO);
		cards.add(card4, GAME);
		cards.add(menu, MENU);
		cards.add(how, HOW);
		cards.add(cred, CRED);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
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
		}
		if(evt.getSource() == bHowToPlay)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, HOW);
		}
		if(evt.getSource() == bCredits)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, CRED);
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
	}

	private class ComboBoxHandler implements ItemListener 
	{
		public void itemStateChanged (ItemEvent event) 
		{
			// replace with appropriate reaction to list choice
			// sample code
			if (event.getSource() == comboBox1) 
			{
				if (event.getStateChange() == ItemEvent.SELECTED)
				{
					// a particular item in list was selected
					String itemSelected = songs[comboBox1.getSelectedIndex()];
					s = new song("\\Songs\\" + itemSelected);
					s.loadEasy("\\Songs\\" + itemSelected);
					info = new JLabel(s.getInfo());
					card3.add(info);
					Test goog = new Test();	
					goog.setSong(s);
					CardLayout cl = (CardLayout)(cards.getLayout());
					cl.show(cards, INFO);
					startMid(45000.0);
				}
			}
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
		player.seek(new Duration(time));
		
		
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