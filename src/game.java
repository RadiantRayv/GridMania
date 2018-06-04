
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

	game(notesChart chart, GUIGameplay g)
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
	}

	public void run()
	{
		TimeStart = System.currentTimeMillis();

		while(chart.hasNext())
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
					gui.draw(i);
				}
			}

		}
	}

	public void nextNote()
	{
		//this is only doing the do every other beat???
		if(timeOfNoteTiming <= timeOfNoteRenderPrev)
		{
			System.out.println(next.getPosition());
			timeOfNoteTiming = next.getPosition()*(15000.0/bpm) + 1981;
			temparr = next.getNotes();
		}
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
				System.out.println("hit");
			}
			for(int i = 0; i <= 9; i++)
			{
				if(temparr[i] == true)
					temparrIsEmpty = false;
			}
			if(temparrIsEmpty)
			{
				nextNote();
			}
		}
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
