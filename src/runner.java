import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;

public class runner 
{
	MediaPlayer player;

	public static void main(String[] args) 
	{

		JFXPanel f = new JFXPanel(); //this is just so that JavaFX toolkit gets initialized so that the rest of the classes in this program can use it 
		
//		game g = new game();
		GUIGameplay goog = new GUIGameplay();		
		
		song s = new song("\\Songs\\SPAIN POWER");
		s.loadEasy("\\Songs\\SPAIN POWER");
		
		goog.setSong(s);
		goog.startSong();
		
		goog.draw1();
		
		long TimeStart = System.currentTimeMillis();
		long timediff = 0;
		while(timediff<500)
		{
			timediff = (System.currentTimeMillis() - TimeStart);
		}
		
		goog.draw1();
//		g.updateNoteField(0);

//		goog.playMusic();
		
//		MediaPlayer player = new MediaPlayer(s.getSong());
//		player.play();
	}

}
