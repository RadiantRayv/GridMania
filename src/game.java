
public class game implements Runnable
{
	private notesChart chart;
	private notesAtTime current;
	private notesAtTime next;
	private boolean[] temparr;
	private boolean temparrIsEmpty;
	private int bpm;
	private int offset;
	private long TimeStart;
	private GUIGameplay gui;
	private double timeOfNoteRender;
	private double timeOfNoteTiming;
	private long hitTimeDiff;
	private GUI screen;
	private boolean running;
	private int perfect;
	private int okay; 
	private int totalNotesSoFar;
	
	private int count;
	
	private int health;
	
	private double totalHitsAccuracy;
	
	private boolean isAlive;

	game(notesChart chart, GUIGameplay g, GUI maingui)
	{
		this.chart = chart;
		//		current = chart.getNext();
		bpm = chart.getSong().getBpm();
		offset = chart.getSong().getOffset();
		gui = g;
		timeOfNoteTiming = offset;
		timeOfNoteRender = -501;
		current = chart.getNext();
		temparr = current.getNotes();
		chart.getPrevious();
		screen = maingui;
		running = true;
		perfect = 0;
		okay = 0;
		totalNotesSoFar = 0;
		health = 8;
		count = 0;
		isAlive = true;
	}

	public void run()
	{
		TimeStart = System.currentTimeMillis();

		while(chart.hasNext() && running)
		{
			current = chart.getNext();
			if(chart.hasNext())
			{
				next = chart.getNext();
				chart.getPrevious();
			}
			timeOfNoteRender = current.getPosition()*(15000.0/bpm) + offset;
			while (System.currentTimeMillis() - TimeStart < timeOfNoteRender - 1000)
			{
			}
			for(int i = 0; i <= 9; i++)
			{
				if(current.getSingleNote(i))
				{
					gui.draw(i, next);
				}
			}	
		}
		while(System.currentTimeMillis() - TimeStart < timeOfNoteRender + 3000)
		{
		}
		if(running)
			screen.isDone(isAlive);
	}

	public void nextNote(notesAtTime nextNote)
	{
//		if(timeOfNoteTiming <= timeOfNoteRenderPrev)
//		{
//			System.out.println(nextNote.getPosition());
			timeOfNoteTiming = nextNote.getPosition()*(15000.0/bpm) + offset;
			temparr = nextNote.getNotes();
			count++;
			System.out.println(count);
			
//		}
	}
	
	public void stopRunning()
	{
		running = false;
	}
	
	public double getTotalHitsAccuracy()
	{
		return totalHitsAccuracy;
	}
	
	public void decreaseHP()
	{
		if(isAlive)
		{
			health -= 2;
			if(health <= 0)
			{
				isAlive = false;
				health = 0;
			}
		}
	}
	
	public int getHP()
	{
		return health;
	}

	public void detectHit(int n)
	{
		temparrIsEmpty = true;
		hitTimeDiff = System.currentTimeMillis();
		if(hitTimeDiff < 150 + timeOfNoteTiming + TimeStart && hitTimeDiff > -150 + timeOfNoteTiming + TimeStart)
		{
			if(temparr[n] == true)
			{
				temparr[n] = false;
				if(hitTimeDiff < 120 + timeOfNoteTiming + TimeStart && hitTimeDiff > -120 + timeOfNoteTiming + TimeStart)
				{
					gui.drawJudgement(n, 0);
					totalHitsAccuracy += 1;
					perfect++;
					health +=3;
					if(health > 18 && isAlive)
						health = 18;
				}
				else
				{
					gui.drawJudgement(n, 1);
					totalHitsAccuracy += 0.6;
					okay++;
					health +=2;
					if(health > 18 && isAlive)
						health = 18;
				}
			}
			for(int i = 0; i <= 9; i++)
			{
				if(temparr[i] == true)
					temparrIsEmpty = false;
			}
			if(temparrIsEmpty)
			{
				nextNote(next);
			}
		}
	}
 
	public void incrementTotalNotes()
	{
		totalNotesSoFar++;
	}
	
	public int getTotalNotesSoFar()
	{
		return totalNotesSoFar;
	}
	
	public int getPerfect()
	{
		return perfect;
	}
	
	public int getOkay()
	{
		return okay;
	}
	
	public int getMiss()
	{
		return (totalNotesSoFar - (perfect + okay));
	}
}
