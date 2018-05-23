import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class GUI4 extends JFrame {

	public GUI4()
	{
		this.setVisible(true);
		this.setTitle("FramesDemo");
		this.setSize(150, 100);
		
		Container cont = new Container();
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.PINK);
		panel.setBackground(Color.BLUE);
		this.setContentPane(panel);
		cont.add(panel);
		cont.add(panel2);
		
		
	}
	
	public static void main(String[] args) 
	{
		GUI4 application = new GUI4();
	}
}
