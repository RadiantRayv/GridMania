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
	private notesAtTime current;

	private Scanner fileScanner;
//	private String path;
	private boolean[] tempArray;
	private ClassLoader cldr;


	notesChart(String filepath)
	{
		cldr = this.getClass().getClassLoader();
		
		chart = new LinkedList<notesAtTime>();
		
//		path = System.getProperty("user.dir") + filepath;

		InputStream chartFile = cldr.getResourceAsStream("Songs/" + filepath);

		tempArray =  new boolean[10];

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
