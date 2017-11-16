public class Save {
	private Player[] players;
	private Deck deckToBeSaved;
	private int seconds, minutes, hours, days, currPlayer, lastCard;
	public Save(Player[] p, Deck d, int s, int m, int h, int da, int c, int l)
	{
		players = p;
		deckToBeSaved = d;
		seconds = s;
		minutes = m;
		hours = h;
		days = da;
		currPlayer = c;
		lastCard = l;
		concatData();
	}

	private void concatData() {
		checkForFileFolder();
		PrintWriter pw = new PrintWriter();

		for(int i = 0; i<players.length; i++)
		{

		}
	}

	private void checkForFileFolder()
	{
		File dir = new File("SavedData");
		if(!dir.Exists) {
			dir.mkdir();
		}

	}
}
