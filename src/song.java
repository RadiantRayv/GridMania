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
		
		path = filepath;
		
//		File songFile = new File(path + "\\song.txt");
		InputStream songFile = cldr.getResourceAsStream("Songs/" + filepath + "/song.txt");

//		String songURI = new File(path + "\\audio.mp3").toURI().toString();
		
		String songURI = cldr.getResource("Songs/" + filepath + "/audio.mp3").toString();
		
			Scanner fileScanner = new Scanner(songFile);
			
			name = fileScanner.nextLine();
			bpm = fileScanner.nextInt();
			length = fileScanner.nextInt();
			offset = fileScanner.nextInt();
			lengthString = String.valueOf(length/60) + "m " + String.valueOf(length%60) + "s";
			song = new Media(songURI);
			
			System.out.println(name + " " + bpm + "bpm " + lengthString);
	}
	
	public void loadEasy()
	{
		diffs.add(0, new notesChart(path + "/easy.txt"));
		diffs.get(0).setSong(this);
	}
	
	public notesChart getEasy()
	{
		return diffs.get(0);
	}
	
	public void loadMedium()
	{
		diffs.add(1, new notesChart(path + "/medium.txt"));
		diffs.get(1).setSong(this);
	}
	
	public notesChart getMedium()
	{
		return diffs.get(1);
	}
	
	public void loadHard()
	{
		diffs.add(2, new notesChart(path + "/hard.txt"));
		diffs.get(2).setSong(this);
	}
	
	public notesChart getHard()
	{
		return diffs.get(2);
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
		return "tempo - " + bpm + "bpm, lEngth - " + lengthString;
	}
	
	public int getOffset()
	{
		return offset;
	}
}
