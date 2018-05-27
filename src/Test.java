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
	final static String SONGS = "Card with song select";
	final static String PROMPT = "Card with prompt";
	final static String INFO = "Card with song info";
	private JButton button1 = new JButton("I DO have a numpad");
	private JButton button2 = new JButton("I DO NOT have a numpad");
	private JLabel label1 = new JLabel("Do you have a numpad?");
	private JLabel label2;
	private String[] songs = {"song select", "SPAIN POWER", "big black", "MTC"};
	private JComboBox<String> comboBox1;
	private song s;
	MediaPlayer player;

	public void addComponentToPane(Container pane) {
		//Put the JComboBox in a JPanel to get a nicer look.
		JPanel comboBoxPane = new JPanel(); //use FlowLayout
		button1.addActionListener(this);
		button2.addActionListener(this);


		//Create the "cards".
		card1 = new JPanel();
		card1.add(label1); 
		card1.add(button1);
		card1.add(button2);


		card2 = new JPanel();
		comboBox1 = new JComboBox<String>(songs);
		comboBox1.setMaximumRowCount(4);
		comboBox1.addItemListener(new ComboBoxHandler());
		card2.add(comboBox1);

		card3 = new JPanel();

		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, PROMPT);
		cards.add(card2, SONGS);
		cards.add(card3, INFO);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == button1)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, SONGS);
		}
		if(evt.getSource() == button2)
		{
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, SONGS);
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
					label2 = new JLabel(s.getInfo());
					card3.add(label2);
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