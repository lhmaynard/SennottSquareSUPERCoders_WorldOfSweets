public class Save {
	/**
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
		String fName;
		for(Player p: players)
		{
			fName+=p.getName();
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		fName+= dtf.format(localDate) + ".wos";
		File f = new File(fName);
		PrintWriter pw = new PrintWriter(f);
		pw.println(players.length);
		for(int i = 0; i<players.length; i++)
		{
			pw.println(players[i]);
		}
	}

	private void checkForFileFolder()
	{
		File dir = new File("SavedData");
		if(!dir.Exists) {
			dir.mkdir();
		}

	}
	*/
}
