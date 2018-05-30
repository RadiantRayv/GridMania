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
	int offset;
	String lengthString;
	
	Media song;
	
	song(String filepath)
	{	
		diffs = new ArrayList<notesChart>();
		
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		path = jarDir.getAbsolutePath() + filepath;
//		path = System.getProperty("user.dir") + filepath;
		
		File songFile = new File(path + "\\song.txt");
		String songURI = new File(path + "\\audio.mp3").toURI().toString();
		
		try 
		{
			Scanner fileScanner = new Scanner(songFile);
			
			
			name = fileScanner.nextLine();
			bpm = fileScanner.nextInt();
			length = fileScanner.nextInt();
			offset = fileScanner.nextInt();
			lengthString = String.valueOf(length/60) + ":" + String.valueOf(length%60);
			song = new Media(songURI);
			
			System.out.println(name + " " + bpm + "bpm " + lengthString);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.print(e.getMessage());
		}
		
	}

	song(String songname, int b, int off, String filepath)
	{
		bpm = b;
		name = songname;
		offset = off;
		diffs = new ArrayList<notesChart>();
	}
	
	public void loadEasy(String filepath)
	{
		diffs.add(0, new notesChart(filepath));
		diffs.get(0).setSong(this);
	}

	public void newEasy(int diff)
	{
		diffs.add(0, new notesChart(1, diff, bpm, offset));
	}
	
	public notesChart getEasy()
	{
		return diffs.get(0);
	}
	
	public int getBpm()
	{
		return bpm;
	}
	
	public Media getSong()
	{
		return song;
	}
	
	public String getInfo() 
	{
		return name + " " + bpm + "bpm " + lengthString;
	}
	
	public int getOffset()
	{
		return offset;
	}
}
