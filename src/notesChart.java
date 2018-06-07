import java.io.*;
import java.util.*;

public class notesChart implements Cloneable
{
	public final int AR1 = 1000;
	public final int AR2 = 800;
	public final int AR3 = 600;

	private song s;

	private LinkedList<notesAtTime> chart1;
	private LinkedList<notesAtTime> chart2;
	private int difficultyLevel;
	private int difficulty;
	private ListIterator<notesAtTime> iter1;
	private ListIterator<notesAtTime> iter2;
	private int bpm;
	private int offset;
	private notesAtTime current;

	private Scanner fileScanner;
	private String path;
	private boolean[] tempArray;
	private ClassLoader cldr;

	notesChart(int difflvl, int diff, int b, int off)
	{
		chart1 = new LinkedList<notesAtTime>();
		iter1 = chart1.listIterator();
		iter2 = chart2.listIterator();
		difficultyLevel = difflvl;
		difficulty = diff;
		bpm = b;
		offset = off;

	}

	notesChart(String filepath)
	{
		cldr = this.getClass().getClassLoader();

		chart1 = new LinkedList<notesAtTime>();
		chart2 = new LinkedList<notesAtTime>();

		path = System.getProperty("user.dir") + filepath;

		InputStream chartFile = cldr.getResourceAsStream("Songs/" + filepath);

		tempArray =  new boolean[10];

		//		try 
		//		{
		fileScanner = new Scanner(chartFile);


		while(fileScanner.hasNext())
		{
			int lastInt;
			
			for(int i = 0; i <= 9; i++)
			{
				if(fileScanner.nextInt() == 1)
					tempArray[i] = true;
				else
					tempArray[i] = false;
			}
			
			lastInt = fileScanner.nextInt();

			chart1.add(new notesAtTime(Arrays.copyOf(tempArray, 10), lastInt));
			chart2.add(new notesAtTime(Arrays.copyOf(tempArray, 10), lastInt));
		}


		iter1 = chart1.listIterator();
		iter2 = chart2.listIterator();

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

	public boolean hasNext(int iteratorNum)
	{
		if(iteratorNum == 1)
			return iter1.hasNext();
		else
			return iter2.hasNext();
	}

	public boolean hasPrev(int iteratorNum)
	{
		if(iteratorNum == 1)
			return iter1.hasPrevious();
		else
			return iter2.hasPrevious();
	}

	public notesAtTime getNext(int iteratorNum)
	{
		if(iteratorNum == 1)
			return iter1.next();
		else
			return iter2.next();
	}

	public notesAtTime getPrevious(int iteratorNum)
	{
		if(iteratorNum == 1)
			return iter1.previous();
		else
			return iter2.previous();
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

//	public long getNoteTime()
//	{
//		return Math.round(current.getPosition() * ((250)/(bpm/60.0)));
//	}

	public void setSong(song ss)
	{
		s = ss;
	}

	public song getSong()
	{
		return s;
	}

	//	public notesChart clone()
	//	{
	//		try {
	//			notesChart clone = (notesChart) super.clone();
	//			clone.iter
	//			return clone;
	//		} catch (CloneNotSupportedException e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}
}
