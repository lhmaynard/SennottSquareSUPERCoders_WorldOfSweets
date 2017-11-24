/**
 *
 */
package project;

import org.junit.Test;
import static org.junit.Assert.*;
import project.SSQWorldOfSweets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;

public class SSQWorldOfSweetsTest {

	//this test checks if the deck has the required 50 single cards
	//User Story (Single and Double Cards)
	@Test
	public void testCountSingles(){
		Deck d = new Deck();
		int count =0;
		while(!d.empty()){
			if(d.drawCard()<5) count++;
		}

		assertTrue(count==50);

	}

	//this test checks if the deck has the required 10 double cards
	//User Story (Single and Double Cards)
	@Test
	public void testCountDoubles(){
		Deck d = new Deck();
		int count =0;
		while(!d.empty()){
			int temp = d.drawCard();
			if(temp>4 && temp<10) count++;
		}

		assertTrue(count==10);

	}

	//This test checks that the ratio of single cards to doubles is 5:1
	//User Story (Card Chances)
	@Test
	public void testSingleDoubleRatio(){
		Deck d = new Deck();
		int singles=0;
		int doubles=0;
		while(!d.empty()){
			int c=d.drawCard();
			if(c>4 && c<10) doubles++;
			else if(c<5) singles++;
		}
		int ratio = singles/doubles;
		assertTrue(ratio==5);
	}

	//This test checks that there are more single cards than double cards
	//User Story (Card Chances)
	@Test
	public void testMoreSingles(){
		Deck d = new Deck();
		int singles=0;
		int doubles=0;
		while(!d.empty()){
			int c=d.drawCard();
			if(c>4) doubles++;
			else singles++;
		}

		assertTrue(singles>doubles);
	}

	@Test
	public void testForSkipATurn() {
		Deck d = new Deck();
		int s = 0;
		while(!d.empty()) {
			int c=d.drawCard();
			if(c == 10) {
				s++;
			}
		}
		assertTrue(s==5);
	}

	@Test
	public void testDeckSizeWithNewCards() {
		Deck d = new Deck();
		int s = 0;
		int size = d.getSize();
		while(!d.empty()) {
			int c = d.drawCard();
			s++;
		}
		assertTrue(s == size);
	}


	@Test
	public void testForCandyCards(){
		Deck d = new Deck();
		int m = 0;
		while(!d.empty()) {
			int c=d.drawCard();
			if(c==11 || c==12 || c==13 || c==14 || c==15) {
				m++;
			}
		}
		assertTrue(m==5);
	}

	@Test
	public void testForSwapCard(){
		Deck d = new Deck();
		int m = 0;
		while(!d.empty()) {
			int c=d.drawCard();
			if(c==16) {
				m++;
			}
		}
		assertTrue(m==5);
	}

	@Test
	public void testFileCreation() throws IOException {
		Player[] p = new Player[4];
		for(int i = 0; i<4; i++)
		{
			p[i] = new Player(i+1, "Player", "_");
		}
		Deck d = new Deck();
		Save sav = new Save(p, d, 0, 0, 0, 0, 0, 0);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		File f = new File("PlayerPlayerPlayerPlayer" + dtf.format(localDate).replace('/', ';') + ".wos");
		f.deleteOnExit();
		assertTrue(f.exists());
	}



}
