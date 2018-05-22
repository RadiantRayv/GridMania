
public class notesAtTime 
{
	private boolean[] notesInGrid;
	private int amount;
	private int positionOfNote; //in 1/16th notes from the beginning of the song
	
	notesAtTime(boolean[] notes, int pos)
	{
		notesInGrid = notes;
		positionOfNote = pos;
	}

	public int getAmount()
	{
		return amount;
	}
	
	public boolean[] getNotes()
	{
		return notesInGrid;
	}
	
	public boolean getSingleNote(int i)
	{
		return notesInGrid[i];
	}
	
	public int getPosition()
	{
		return positionOfNote;
	}
}
