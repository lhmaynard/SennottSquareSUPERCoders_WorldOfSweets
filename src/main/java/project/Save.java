package project;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Save {
	private Player[] players;
	private Deck deckToBeSaved;
	private int seconds, minutes, hours, days, currPlayer, lastCard;
	public Save(Player[] p, Deck d, int s, int m, int h, int da, int c, int l) throws IOException
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


	private void concatData() throws IOException {
		String fName = new String();
		for(Player p: players)
		{
			fName+=p.getPlayerName();
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		fName+= dtf.format(localDate) + ".wos";
		fName = fName.replace('/', ';');
		PrintWriter pw = new PrintWriter(new File(fName));
		pw.println(players.length);
		for(int i = 0; i<players.length; i++)
		{
			pw.println("\"" + players[i].getPlayerName() + "\"");
			pw.println(players[i].getToken());
			pw.println(players[i].getCurrentSpace());
			pw.println(players[i].getLastCard());

		}
		pw.println(deckToBeSaved.getSize());
		while(!deckToBeSaved.empty())
		{
			pw.println(deckToBeSaved.drawCard());
		}
		pw.println(seconds);
		pw.println(minutes);
		pw.println(hours);
		pw.println(days);
		pw.println(currPlayer);
		pw.println(lastCard);
		pw.close();
	}
}
