import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;       // access to JFrame and Jcomponents
import javax.swing.event.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUI extends JFrame
{
	private JLabel test;
	private game g;
	private song s;
	
	MediaPlayer player;

	GUI(song ss)
	{
		s = ss;
//		super("osu lol");
//		Container container = getContentPane();
//		container.setLayout(new GridLayout(2, 1));
//		test = new JLabel("test");
//		container.add(test);
//		
//		
//		Scene sc = createScene();
//		
//
//		addKeyListener(new keyInput(gg, this));	
		initAndShowGUI();
		addWindowListener(
				new java.awt.event.WindowAdapter() 
				{
					public void windowClosing(WindowEvent evt) 
					{
						System.exit(0);
					}
				});

		setSize( 275, 170);   // alternative: pack();
		setVisible(true);
	}
	
	private void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(300, 200);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
    }

    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
		player = new MediaPlayer(s.getSong());
		player.play();
    }

    private Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        Text  text  =  new  Text();
        
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");

        root.getChildren().add(text);

        return (scene);
    }
    
    public void playMusic() 
    {
    	
    }
    
    public void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }

	public void timingText(String txt)
	{
		test.setText(txt);
	}
	
//	MediaPlayer player = new MediaPlayer(s.getSong());
	
    JFXPanel fxPanel = new JFXPanel();
    
//    container.setSize(300, 200);
//    container.setVisible(false);
//    container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Group g = new Group();
//	g.getChildren().add(new MediaView(player));
    Scene scene = new Scene(g);
    fxPanel.setScene(scene);
    container.add(fxPanel);
//    Platform.runLater(new Runnable() {
//        @Override
//        public void run() {
//        	
//    		
//    		player.play();
//        }
//   });

}
