import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;

public class runner 
{

	public static void main(String[] args) 
	{

		JFXPanel f = new JFXPanel(); //this is just so that JavaFX toolkit gets initialized so that the rest of the classes in this program can use it 
		
		GUI menu = new GUI();
		menu.createAndShowGUI();
	}

}
