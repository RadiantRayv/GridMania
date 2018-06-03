
public class game implements Runnable
{
	private notesChart chart;
	private long approachRate = 1000;
	private long nextHitTime;
	private notesAtTime current;
	private int bpm;
	private long TimeStart;
	private GUIGameplay gui;
	private double timeOfNote;

	game(notesChart chart, GUIGameplay g)
	{
		this.chart = chart;
//		current = chart.getNext();
		bpm = chart.getSong().getBpm();
		gui = g;
	}

	public void run()
	{
		TimeStart = System.currentTimeMillis();
		
		while(chart.hasNext())
		{
			current = chart.getNext();
			timeOfNote = current.getPosition()*(15000.0/bpm) + 1981;
			gui.setCurrentNotes(current.getNotes(), timeOfNote);
			while (System.currentTimeMillis() - TimeStart < timeOfNote - 917)
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
	
	public void updateNoteField()
	{
		
		
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
	}

	public String pressNote1()
	{
		if(current.getSingleNote(0))
		{
			long timeNow = System.currentTimeMillis();

			int time = (int) (timeNow - nextHitTime);

			if (time < -50)
				return "early";
			else if (time > 50)
				return "late";
			else
				return "nice";
		}
		else 
			return "";
	}
	
	public String pressNote2()
	{
		if(current.getSingleNote(1))
		{
			long timeNow = System.currentTimeMillis();

			int time = (int) (timeNow - nextHitTime);

			if (time < -50)
				return "early";
			else if (time > 50)
				return "late";
			else
				return "nice";
		}
		else 
			return "";
	}
}
