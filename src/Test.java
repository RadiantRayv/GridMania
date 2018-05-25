import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test implements ActionListener  {
	JPanel cards; //a panel that uses CardLayout
	final static String SONGS = "Card with JButtons";
	final static String PROMPT = "Card with JTextField";
	private JButton button1 = new JButton("I DO have a numpad");
	private JButton button2 = new JButton("I DO NOT have a numpad");
	private JLabel label1 = new JLabel("Do you have a numpad?");
	private String[] songs = {"song select", "SPAIN POWER", "big black", "MTC"};
	private JComboBox<String> comboBox1;



	public void addComponentToPane(Container pane) {
		//Put the JComboBox in a JPanel to get a nicer look.
		JPanel comboBoxPane = new JPanel(); //use FlowLayout
		button1.addActionListener(this);
		button2.addActionListener(this);

		//Create the "cards".
		JPanel card1 = new JPanel();
		card1.add(label1); 
		card1.add(button1);
		card1.add(button2);


		JPanel card2 = new JPanel();
		comboBox1 = new JComboBox<String>(songs);
		comboBox1.setMaximumRowCount(4);
		card2.add(comboBox1);
		;
		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, PROMPT);
		cards.add(card2, SONGS);

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

		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}