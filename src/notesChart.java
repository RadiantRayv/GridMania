import java.io.*;
import java.util.*;

public class notesChart 
{
	public final int AR1 = 1000;
	public final int AR2 = 800;
	public final int AR3 = 600;
	
	private song s;

	private LinkedList<notesAtTime> chart;
	private int difficultyLevel;
	private int difficulty;
	private ListIterator<notesAtTime> iter;
	private int bpm;
	private int offset;
	private notesAtTime current;

	private Scanner fileScanner;
	private String path;
	private boolean[] tempArray;
	private ClassLoader cldr;

	notesChart(int difflvl, int diff, int b, int off)
	{
		chart = new LinkedList<notesAtTime>();
		iter = chart.listIterator();
		difficultyLevel = difflvl;
		difficulty = diff;
		bpm = b;
		offset = off;
		
	}

	notesChart(String filepath)
	{
		cldr = this.getClass().getClassLoader();
		
		chart = new LinkedList<notesAtTime>();
		
		path = System.getProperty("user.dir") + filepath;

		InputStream chartFile = cldr.getResourceAsStream("Songs/" + filepath + "/easy.txt");

		tempArray =  new boolean[10];

//		try 
//		{
			fileScanner = new Scanner(chartFile);


			while(fileScanner.hasNext())
			{
				for(int i = 0; i <= 9; i++)
				{
					if(fileScanner.nextInt() == 1)
						tempArray[i] = true;
					else
						tempArray[i] = false;
				}
				
				chart.add(new notesAtTime(Arrays.copyOf(tempArray, 10), fileScanner.nextInt()));
			}
			
			iter = chart.listIterator();

					//UHHHHHHHH DUDE SO ADD FILE first 10 int are notes, 11 is position of note
					//when iterating back and forth, inc beat by 1 and only display ntoe if beat == the note count in the notesAtTime
//		} 
//		catch (FileNotFoundException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//this constructor for loading from disk
	}

	public void export()
	{

	}
	
	public boolean hasNext()
	{
		return iter.hasNext();
	}
	
	public boolean hasPrev()
	{
		return iter.hasPrevious();
	}

	public notesAtTime getNext()
	{
		current = iter.next();
		return current;
	}

	public notesAtTime getPrevious()
	{
		current = iter.previous();
		return current;
	}

	public String getDiffLevel()
	{
		if (difficultyLevel == 1)
			return "Easy";
		else if (difficultyLevel == 2)
			return "Medium";
		else
			return "Hard";
	}

	public int getDiff()
	{
		return difficulty;
	}

	public long getNoteTime()
	{
		return Math.round(current.getPosition() * ((250)/(bpm/60.0)));
	}
	
	public void setSong(song ss)
	{
		s = ss;
	}
	
	public song getSong()
	{
		return s;
	}
}
