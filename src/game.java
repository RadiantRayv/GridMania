import javax.swing.SwingUtilities;

public class game
{
	private notesChart chart;
	private notesChart chart2;
	private long approachRate = 1000;
	private long nextHitTime;
	private notesAtTime current;
	private notesAtTime current2;
	private boolean noteNotDissapeared;
	private boolean noteNotHit;
	
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
	
	private Thread t1;
	private Thread t2;

	game(notesChart ch, GUIGameplay g, Test maingui)
	{
		chart = ch;
		//		current = chart.getNext();
		bpm = chart.getSong().getBpm();
		offset = chart.getSong().getOffset();
		gui = g;
		timeOfNoteTiming = offset;
		timeOfNoteRender = -501;
//		current = chart.getNext();
		current2 = chart.getNext(2);
		
		next = null;
		
		temparr = current2.getNotes();
		screen = maingui;
		running = true;
		perfect = 0;
		okay = 0;
		totalNotesSoFar = 0;
		
		noteNotDissapeared = true;
		noteNotHit = true;
		
		t1 = new Thread(new GUILoop());
		t2 = new Thread(new TimingLoop());
	}
	
	public void runGame()
	{
		TimeStart = System.currentTimeMillis();
		t1.start();
		t2.start();
	}

	private class GUILoop implements Runnable
	{
		public void run()
		{

			while(chart.hasNext(1) && running)
			{
				timeOfNoteRenderPrev = timeOfNoteRender;
				current = chart.getNext(1);
//				if(chart.hasNext())
//				{
//					next = chart.getNext();
//					chart.getPrevious();
//				}
				timeOfNoteRender = current.getPosition()*(15000.0/bpm) + offset;
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
	}

	private class TimingLoop implements Runnable
	{
		private boolean noteNotHHH = true;
		
		private void hitnuts()
		{
			noteNotHHH = false;
		}
		
		
		public void run()
		{

			while(chart.hasNext(2) && running)
			{
				System.out.println("x");
				while (noteNotDissapeared && noteNotHHH)
				{
				}
				System.out.println("h");
				noteNotHit = true;
				noteNotDissapeared = true;
				current2 = chart.getNext(2);
				timeOfNoteTiming = current2.getPosition()*(15000.0/bpm) + offset;
				temparr = current2.getNotes();
			}
			while(System.currentTimeMillis() - TimeStart < timeOfNoteRender + 5000)
			{
			}
			if(running)
				screen.isDone(true);
		}
	}
	
	public void noteDissapeared()
	{
		noteNotDissapeared = false;
	}

//	public void nextNote(notesAtTime nextNote)
//	{
//		//this is only doing the do every other beat???
//		//		if(timeOfNoteTiming <= timeOfNoteRenderPrev)
//		//		{
//		//			System.out.println(nextNote.getPosition());
//		timeOfNoteTiming = nextNote.getPosition()*(15000.0/bpm) + offset;
//		temparr = nextNote.getNotes();
//
//		//		}
//	}

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
//				nextNote(next);
				noteNotHit = false;
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
