
public class game implements Runnable
{
	private notesChart chart;
	private long approachRate = 1000;
	private long nextHitTime;
	private notesAtTime current;
	private notesAtTime next;
	private boolean[] temparr;
	private boolean temparrIsEmpty;
	private int bpm;
	private int offset;
	private long TimeStart;
	private GUIGameplay gui;
	private double timeOfNoteRender;
	private double timeOfNoteRenderPrev;
	private double timeOfNoteTiming;
	private long hitTimeDiff;
	private Test screen;
	private boolean running;
	private int perfect;
	private int okay; 
	private int totalNotesSoFar;
	
	private double totalHitsAccuracy;

	game(notesChart chart, GUIGameplay g, Test maingui)
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
	}

	public void run()
	{
		TimeStart = System.currentTimeMillis();

		while(chart.hasNext() && running)
		{
			timeOfNoteRenderPrev = timeOfNoteRender;
			current = chart.getNext();
			if(chart.hasNext())
			{
				next = chart.getNext();
				chart.getPrevious();
			}
			timeOfNoteRender = current.getPosition()*(15000.0/bpm) + offset;
			//			gui.setCurrentNotes(current.getNotes(), timeOfNote + TimeStart);
			while (System.currentTimeMillis() - TimeStart < timeOfNoteRender - 917)
			{
			}
			for(int i = 0; i <= 9; i++)
			{
				if(current.getSingleNote(i))
				{
					gui.draw(i, next, timeOfNoteRender);
				}
			}	
		}
		while(System.currentTimeMillis() - TimeStart < timeOfNoteRender + 5000)
		{
		}
		if(running)
			screen.isDone(true);
	}

	public void nextNote(notesAtTime nextNote, double lastRenderedNoteTime)
	{
		//this is only doing the do every other beat???
		if(timeOfNoteTiming <= lastRenderedNoteTime)
		{
			System.out.println("yeett");
			timeOfNoteTiming = nextNote.getPosition()*(15000.0/bpm) + offset;
			temparr = nextNote.getNotes();
			gui.nextTimingInQueue();
		}
	}
	
	public void stopRunning()
	{
		running = false;
	}
	
	public double getTotalHitsAccuracy()
	{
		return totalHitsAccuracy;
	}

	public void detectHit(int n, double timeRender)
	{
		temparrIsEmpty = true;
		hitTimeDiff = System.currentTimeMillis();
		if(hitTimeDiff < 250 + timeOfNoteTiming + TimeStart && hitTimeDiff > -250 + timeOfNoteTiming + TimeStart)
		{
			if(temparr[n] == true)
			{
				temparr[n] = false;
				if(hitTimeDiff < 150 + timeOfNoteTiming + TimeStart && hitTimeDiff > -150 + timeOfNoteTiming + TimeStart)
				{
					gui.drawJudgement(n, 0);
					totalHitsAccuracy += 1;
					perfect++;
				}
				else
				{
					gui.drawJudgement(n, 1);
					totalHitsAccuracy += 0.6;
					okay++;
				}
			}
			for(int i = 0; i <= 9; i++)
			{
				if(temparr[i] == true)
					temparrIsEmpty = false;
			}
			if(temparrIsEmpty)
			{
				nextNote(next, timeRender);
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
