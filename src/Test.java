import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;

public class Test implements ActionListener  {
	JPanel cards; //a panel that uses CardLayout
	JPanel card1;
	JPanel card2;
	JPanel card3;
	JPanel card4;
	final static String SONGS = "Card with song select";
	final static String PROMPT = "Card with prompt";
	final static String INFO = "Card with song info and difficulty";
	final static String GAME = "Card with the game";
	private JButton buttonD = new JButton("I DO have a numpad");
	private JButton buttonN = new JButton("I DO NOT have a numpad");
	private JButton buttonE = new JButton("Easy");
	private JButton buttonM = new JButton("Medium");
	private JButton buttonH = new JButton("Hard");
	private JLabel numpad = new JLabel("Do you have a numpad?");
	private JLabel songInfo = new JLabel("Song Info:");
	private JLabel info;
	private JLabel diff = new JLabel("Choose a difficulty");
	private JLabel game;
	private String[] songs = {"song select", "Rob Gasser - Supersonic"};
	private JComboBox<String> comboBox1;
	private song s;
	MediaPlayer player;

	public void addComponentToPane(Container pane) {
		JPanel comboBoxPane = new JPanel(); //use FlowLayout
		buttonD.addActionListener(this);
		buttonN.addActionListener(this);
		buttonE.addActionListener(this);
		buttonM.addActionListener(this);
		buttonH.addActionListener(this);


		//Create the "cards".
		card1 = new JPanel();
		card1.add(numpad); 
		card1.add(buttonD);
		card1.add(buttonN);


		card2 = new JPanel();
		comboBox1 = new JComboBox<String>(songs);
		comboBox1.setMaximumRowCount(4);
		comboBox1.addItemListener(new ComboBoxHandler());
		card2.add(comboBox1);

		card3 = new JPanel();
		card3.add(diff);
		card3.add(buttonE);
		card3.add(buttonM);
		card3.add(buttonH);
		card3.add(songInfo);
		
		card4 = new JPanel();

		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, PROMPT);
		cards.add(card2, SONGS);
		cards.add(card3, INFO);
		cards.add(card4, GAME);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == buttonD)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, SONGS);
		}
		if(evt.getSource() == buttonN)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, SONGS);
		}
		if(evt.getSource() == buttonE)
		{
			game = new JLabel(buttonE.getText());
			card4.add(game);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
		}
		if(evt.getSource() == buttonM)
		{
			game = new JLabel(buttonM.getText());
			card4.add(game);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
		}
		if(evt.getSource() == buttonH)
		{
			game = new JLabel(buttonH.getText());
			card4.add(game);
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, GAME);
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
					startSong();
					CardLayout cl = (CardLayout)(cards.getLayout());
					cl.show(cards, INFO);
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

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		Test demo = new Test();
		demo.addComponentToPane(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		JFXPanel f = new JFXPanel();
		/* Use an appropriate Look and Feel */
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
	}
}