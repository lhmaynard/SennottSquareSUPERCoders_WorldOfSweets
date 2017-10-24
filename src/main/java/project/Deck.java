/**
 * Deck.java
 * SennottSquareSUPERCoders
 * World of Sweets Project
 */

import java.util.Stack;
import java.util.Random;

public class Deck {
	private Stack<Integer> mydeck;
	
	/**
	 * This is the no-args constructor for the Deck class
	 * It will call the shuffle method
	 * @param none
	 * @return none
	 * 
	 */
	public Deck(){
		shuffle();
	}
	
	/**
	 * This method is used to "shuffle" the deck and create a new stack
	 * with integers representing the corresponding "color" of the card.
	 * @param none
	 * @return none
	 * 
	 */
	public void shuffle(){
		mydeck = new Stack<>();
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		//integer array representing the number of cards by color that 
		//need to be added into the deck
		int[] toAdd = {10,10,10,10,10,2,2,2,2,2};
		
		while(toAdd[0] != 0 || toAdd[1] != 0 || toAdd[2] != 0 || toAdd[3] != 0 || toAdd[4] != 0 || toAdd[5] != 0 || toAdd[6] != 0 || toAdd[7] != 0 || toAdd[8] != 0 || toAdd[9] != 0){
			
			int tempIndex = rand.nextInt(10);
			
			if(toAdd[tempIndex] != 0){
				mydeck.push(tempIndex);
				toAdd[tempIndex] = toAdd[tempIndex] - 1;
			}
		}
	}
	/**
	 * This method "draws" a card and pops its value off of the "deck" (stack)
	 * And returns an integer that is associated with a vard value
	 * @param none
	 * @return	0	A single red card
	 * @return	1	A single yellow card
	 * @return	2	A single blue card
	 * @return	3	A single green card
	 * @return	4	A single orange card
	 * @return	5	A double red card
	 * @return	6	A double yellow card
	 * @return	7	A double blue card
	 * @return	8	A double green card
	 * @return	9	A double orange card
	 * 
	 */
	public int drawCard(){
		int card = mydeck.pop();
		return card;
	}
	/**
	 * This method is used to determine if the "deck" (stack) is empty and
	 * needs to be reshuffled
	 * @param none
	 * @return	true	if there are no more "cards" in the deck
	 * @return	false	if there are still "cards" in the deck
	 */
	public boolean empty(){
		if(mydeck.empty() == true){
			return true;
		}
		else{
			return false;
		}
	}
}
