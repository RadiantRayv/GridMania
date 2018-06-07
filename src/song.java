import java.util.*;

import javax.swing.ImageIcon;

import javafx.scene.media.*;
import javafx.embed.swing.JFXPanel;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class song 
{
	private ArrayList<notesChart> diffs;
	
	private String path;
	private String name;
	private int bpm;
	private int length;
	private int offset;
	private String lengthString;
	
	private Media song;
	
	private ClassLoader cldr;
	
	song(String filepath)
	{
		cldr = this.getClass().getClassLoader();
		
		diffs = new ArrayList<notesChart>();
		
		path = System.getProperty("user.dir") + filepath;
		
//		File songFile = new File(path + "\\song.txt");
		InputStream songFile = cldr.getResourceAsStream("Songs/" + filepath + "/song.txt");

//		String songURI = new File(path + "\\audio.mp3").toURI().toString();
		
		String songURI = cldr.getResource("Songs/" + filepath + "/audio.mp3").toString();
		
	
//		try 
//		{
			Scanner fileScanner = new Scanner(songFile);
			
			
			name = fileScanner.nextLine();
			bpm = fileScanner.nextInt();
			length = fileScanner.nextInt();
			offset = fileScanner.nextInt();
			lengthString = String.valueOf(length/60) + ":" + String.valueOf(length%60);
			song = new Media(songURI);
			
			System.out.println(name + " " + bpm + "bpm " + lengthString);
//		} 
//		catch (FileNotFoundException e) 
//		{
//			System.out.print(e.getMessage());
//		}
		
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
	
	public String getName()
	{
		return name;
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
