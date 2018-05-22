
public class game 
{
	notesChart chart;
	long approachRate = 1000;
	long nextHitTime;
	notesAtTime current;
	int bpm;

	game(notesChart chart)
	{
		this.chart = chart;
		current = chart.getNext();
		bpm = chart.getSong().getBpm();
	}

	public void runGame()
	{
		while(chart.hasNext())
		{
			updateNoteField();
		}
	}
	
	public void updateNoteField()
	{
		int notePrevious = current.getPosition();
		current = chart.getNext();
		int noteNow = current.getPosition();
		nextHitTime = System.currentTimeMillis() + approachRate + (bpm * (noteNow - notePrevious));
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
