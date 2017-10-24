/**
 * 
 */


public class Test {
	
	public static void main (String args[]) {
		Deck deck = new Deck();
		
		while (deck.empty() == false){
			int card = deck.drawCard();
			if(card == 0){
				System.out.println("You drew a red card!");
			}
			else if(card == 1){
				System.out.println("You drew a yellow card!");
			}
			else if(card == 2){
				System.out.println("You drew a blue card!");
			}
			else if(card == 3){
				System.out.println("You drew a green card!");
			}
			else if(card == 4){
				System.out.println("You drew an orange card!");
			}
			else if(card == 5){
				System.out.println("You drew a double red card!");
			}
			else if(card == 6){
				System.out.println("You drew a double yellow card!");
			}
			else if(card == 7){
				System.out.println("You drew a double blue card!");
			}
			else if(card == 8){
				System.out.println("You drew a double green card!");
			}
			else if(card == 9){
				System.out.println("You drew a double orange card!");
			}
			else{
				System.out.println("This should not print");
			}
		}
		deck.shuffle();
		
	}
}

