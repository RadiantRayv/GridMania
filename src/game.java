
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
		gui = g;
		timeOfNoteTiming = 1981;
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
			timeOfNoteRender = current.getPosition()*(15000.0/bpm) + 1981;
			//			gui.setCurrentNotes(current.getNotes(), timeOfNote + TimeStart);
			while (System.currentTimeMillis() - TimeStart < timeOfNoteRender - 917)
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
		while(System.currentTimeMillis() - TimeStart < timeOfNoteRender + 5000)
		{
		}
		if(running)
			screen.isDone(true);
	}

	public void nextNote(notesAtTime nextNote)
	{
		//this is only doing the do every other beat???
//		if(timeOfNoteTiming <= timeOfNoteRenderPrev)
//		{
//			System.out.println(nextNote.getPosition());
			timeOfNoteTiming = nextNote.getPosition()*(15000.0/bpm) + 1981;
			temparr = nextNote.getNotes();
			
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

	public void detectHit(int n)
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

	//public void updateNoteField()
	//{


	//		gui.draw(1);
	//		
	//		long TimeStart = System.currentTimeMillis();
	//		long timediff = 0;
	//		while(timediff<500)
	//		{
	//			timediff = (System.currentTimeMillis() - TimeStart);
	//		}
	//		
	//		gui.draw(2);
	//		
	//		TimeStart = System.currentTimeMillis();
	//		timediff = 0;
	//		while(timediff<500)
	//		{
	//			timediff = (System.currentTimeMillis() - TimeStart);
	//		}
	//		
	//		gui.draw(3);
	//		
	//		TimeStart = System.currentTimeMillis();
	//		timediff = 0;
	//		while(timediff<500)
	//		{
	//			timediff = (System.currentTimeMillis() - TimeStart);
	//		}

	//		int notePrevious = current.getPosition();
	//		current = chart.getNext();
	//		int noteNow = current.getPosition();
	//		nextHitTime = System.currentTimeMillis() + approachRate + (bpm * (noteNow - notePrevious));
	//nextHitTime = System.currentTimeMillis() + timeToNext;
	//}

	//	public String pressNote1()
	//	{
	//		if(current.getSingleNote(0))
	//		{
	//			long timeNow = System.currentTimeMillis();
	//
	//			int time = (int) (timeNow - nextHitTime);
	//
	//			if (time < -50)
	//				return "early";
	//			else if (time > 50)
	//				return "late";
	//			else
	//				return "nice";
	//		}
	//		else 
	//			return "";
	//	}
	//	
	//	public String pressNote2()
	//	{
	//		if(current.getSingleNote(1))
	//		{
	//			long timeNow = System.currentTimeMillis();
	//
	//			int time = (int) (timeNow - nextHitTime);
	//
	//			if (time < -50)
	//				return "early";
	//			else if (time > 50)
	//				return "late";
	//			else
	//				return "nice";
	//		}
	//		else 
	//			return "";
	//	}
}
