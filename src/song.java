import java.util.*;

import javafx.scene.media.*;
import javafx.embed.swing.JFXPanel;

import java.io.*;
import java.net.URI;


public class song 
{
	ArrayList<notesChart> diffs;
	
	String path;
	String name;
	int bpm;
	int length;
	String lengthString;
	
	Media song;
	
	song(String filepath)
	{	
		diffs = new ArrayList<notesChart>();
		
		path = System.getProperty("user.dir") + filepath;
		
		File songFile = new File(path + "\\song.txt");
		String songURI = new File(path + "\\audio.mp3").toURI().toString();
		
		try 
		{
			Scanner fileScanner = new Scanner(songFile);
			
			
			name = fileScanner.nextLine();
			bpm = fileScanner.nextInt();
			length = fileScanner.nextInt();
			lengthString = String.valueOf(length/60) + ":" + String.valueOf(length%60);
			song = new Media(songURI);
			
			System.out.println(name + " " + bpm + "bpm " + lengthString);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.print(e.getMessage());
		}
		
	}

	song(String songname, int bpm, String filepath)
	{
		this.bpm = bpm;
		name = songname;
		diffs = new ArrayList<notesChart>();
	}
	
	public void loadEasy(String filepath)
	{
		diffs.add(0, new notesChart(filepath));
		diffs.get(0).setSong(this);
	}

	public void newEasy(int diff)
	{
		diffs.add(0, new notesChart(1, diff, bpm));
	}
	
	public int getBpm()
	{
		return bpm;
	}
	
	public Media getSong()
	{
		return song;
	}
}
