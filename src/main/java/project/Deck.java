/**
 * Deck.java
 * SennottSquareSUPERCoders
 * 
 * Leonard Maynard	git- lhmaynard
 * Zachary Mell		git- zacharymell
 * Kevin Moore		git- KMoore21
 * Brandon Palonis	git- brandonp728
 * 
 * World of Sweets Project
 */
 
package project;
import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;

/**
 * 
 */
public class Deck {
	//global variable
	private Stack<Integer> mydeck;
	
	/**
	 * This is the no-args constructor for the Deck class
	 * It will call the shuffle method
	 * 
	 * @param none
	 * @return none
	 */
	public Deck(){
		shuffle();
	}
	
	
	/**
	 * This shuffle method is used to "shuffle" the deck and create a 
	 * new stack with integers representing the corresponding "color" 
	 * of the card.
	 * 
	 * @param none
	 * @return none 
	 */
	public void shuffle(){
		mydeck = new Stack<>();
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		//Arraylist to hold all of the cards that still need to be added
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		for(int i = 0; i < 60; i++){
			if(i < 10){
				toAdd.add(0);
			}
			else if(i > 9 && i < 20){
				toAdd.add(1);
			}
			else if(i > 19 && i < 30){
				toAdd.add(2);
			}
			else if(i > 29 && i < 40){
				toAdd.add(3);
			}
			else if(i > 39 && i < 50){
				toAdd.add(4);
			}
			else if(i == 50 || i == 51){
				toAdd.add(5);
			}
			else if(i == 52 || i == 53){
				toAdd.add(6);
			}
			else if(i == 54 || i == 55){
				toAdd.add(7);
			}
			else if(i == 56 || i == 57){
				toAdd.add(8);
			}
			else if(i == 58 || i == 59){
				toAdd.add(9);
			}
		}
		
		while(toAdd.isEmpty() == false){
			//Each time a card is placed into the deck, the rng
			//only selects from the remaining cards, because the cards
			//are being removed from the arraylist
			int tempIndex = rand.nextInt(toAdd.size());
			int card = toAdd.remove(tempIndex);
			mydeck.push(card);
		}
	}
	
	/**
	 * This drawCard method "draws" a card and pops its value off of 
	 * the "deck" (stack), and returns an integer that is associated 
	 * with a vard value
	 * 
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
	 * @return	10	A skip turn card
	 * @return	11	A go to middle card
	 */
	public int drawCard(){
		int card = mydeck.pop();
		return card;
	}
	
	/**
	 * This empty method is used to determine if the "deck" (stack) is empty and
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
