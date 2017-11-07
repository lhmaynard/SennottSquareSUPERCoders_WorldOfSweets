/**
 *
 */
package project;

import org.junit.Test;
import static org.junit.Assert.*;

import project.SSQWorldOfSweets;

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


}
