
import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;       // access to JFrame and JComponents
import javax.swing.event.*;       // access to JSlider events

import javafx.scene.media.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUI2 extends JFrame{

	private JLabel label1;
	private JButton button1;
	private JButton button2;
	private JComboBox<String> comboBox1;
	private String[] names = {"easy", "meduim", "hard"};// list for comboBox1
	private JMenuItem startItem, exitItem;
	private song s;
	MediaPlayer player;
	
	
	public GUI2() 
	{
		super("Comment in Window Title Bar");
//		s=ss;
		Container container = getContentPane();
		container.setLayout( new FlowLayout() );
		label1 = new JLabel("Do you have a numpad?");
		button1 = new JButton("I DO have a numpad");
		button2 = new JButton("I DO NOT have a numpad");
		comboBox1 = new JComboBox<String>(names);
		comboBox1.setMaximumRowCount(3);
		JMenu fileMenu = new JMenu("File");
		startItem = new JMenuItem("Start");
		exitItem = new JMenuItem("Exit");
		JMenuBar bar = new JMenuBar();
		
//	    g = new Group();
//	    Scene scene = new Scene(g);
//		JFXPanel fxPanel = new JFXPanel();
//		g.getChildren().add(new MediaView(player));
//	    fxPanel.setScene(scene);
//	    container.add(fxPanel);
		
		container.add(label1);
		container.add(button1);
		container.add(button2);
		container.add(comboBox1);
		fileMenu.add(startItem);
		fileMenu.add(exitItem);
		setJMenuBar (bar);
		bar.add (fileMenu);
		button1.addActionListener(new ButtonHandler());
		button2.addActionListener(new ButtonHandler());
		comboBox1.addItemListener(new ComboBoxHandler());
		MenuItemHandler menuItemHandler = new MenuItemHandler();
		startItem.addActionListener(menuItemHandler);
		exitItem.addActionListener(menuItemHandler);
		addWindowListener(new java.awt.event.WindowAdapter() {public void windowClosing(WindowEvent evt) {System.exit(0);}});
		setSize( 275, 275);
		setVisible(true);

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

	private class ButtonHandler implements ActionListener 
	{
		public void actionPerformed (ActionEvent event) 
		{
			// replace with appropriate reaction to button press
			// sample code to show that button pressed
			if (event.getSource() == button1) 
			{
				System.out.print("Button1 pressed; label in it is: ");
				System.out.println(event.getActionCommand());
			}
			else if (event.getSource() == button2)
			{
				System.out.print("Button2 pressed; label in it is: ");
				System.out.println(event.getActionCommand());
			}
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
				if ( event.getStateChange() == ItemEvent.SELECTED)
				{
					// a particular item in list was selected
					String itemSelected = names[comboBox1.getSelectedIndex()];
					System.out.println("Item selected: " + itemSelected);
				}
			}
		}
	}

	private class MenuItemHandler implements ActionListener 
	{
		public void actionPerformed (ActionEvent event) 
		{
			// replace with appropriate reaction to menu choice
			// sample code
			if (event.getSource() == startItem) 
			{
				System.out.println("'Start' menu item selected");
				startSong();
			}
			else if (event.getSource() == exitItem ) {
				System.exit(0);     // EXIT
			}
		}
	}

}