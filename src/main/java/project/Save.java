package project;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.StringBuilder;

public class Save {
	private Player[] players;
	private Deck deckToBeSaved;
	private int seconds, minutes, hours, days, currPlayer, lastCard, gameMode;
	public Save(Player[] p, Deck d, int s, int m, int h, int da, int c, int l, int g) throws IOException
	{
		players = p;
		deckToBeSaved = d;
		seconds = s;
		minutes = m;
		hours = h;
		days = da;
		currPlayer = c;
		lastCard = l;
		gameMode = g;
		concatData();
	}


	private void concatData() throws IOException{
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
			pw.println(players[i].isAI());
			pw.println(players[i].getBoomerangs());
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
		pw.println(gameMode);
		pw.close();

		checksum(fName);
	}
	private void checksum(String fileName) throws IOException{
		try{
			File newFile = new File(fileName);
			FileInputStream fis = new FileInputStream(newFile);
			int byteLength = (int)newFile.length();

			byte[] fileByteArray = new byte[byteLength];
			byte[] fileHash;
			fis.read(fileByteArray, 0, byteLength);


			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(fileByteArray);
			fileHash = md.digest();

			StringBuilder sb = new StringBuilder();
			for(byte b : fileHash){
				sb.append(String.format("%02x", b&0xff));
			}

			String finalHexString = sb.toString();
			fis.close();
			rewriteFile(fileName, finalHexString);

		}
		catch(NoSuchAlgorithmException nsae){
			System.out.println(nsae);
			System.exit(1);
		}
	}
	private void rewriteFile(String fileName, String hexVal) throws IOException{
		String tempFileName = fileName + "$";
		PrintWriter pw = new PrintWriter(new File(tempFileName));
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);

		//Printing hex hash to first line of new file
		pw.println(hexVal);
		String curLine;

		//Printing same data from original file to new file
		while((curLine = br.readLine()) != null){
			pw.println(curLine);
		}
		br.close();
		fr.close();
		pw.close();

		//Removing old file
		File rmF = new File(fileName);
		rmF.delete();

		//Renaming file to old file name
		pw = new PrintWriter(new File(fileName));
		fr = new FileReader(tempFileName);
		br = new BufferedReader(fr);

		String newLine;

		while((newLine = br.readLine()) != null){
			pw.println(newLine);
		}

		br.close();
		fr.close();
		pw.close();

		//Removing temp file since new file has been renamed to old
		//file name
		File rmf1 = new File(tempFileName);
		rmf1.delete();

	}
}
