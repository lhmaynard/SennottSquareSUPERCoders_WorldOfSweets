/**
 * Player.java
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

/**
 * This is the Player class.  It holds all relevant player information
 * variables and has functions for getting and setting those variables.
 */
public class Player {
	//Global variables
	private int playerNumber;
	private String playerName;
	private int lastCard;
	private int currentSpace;
	private String playerToken;
	private Boolean grandmasHouse;
	private boolean dad;

	/**
	 * This is the player constructor that initializes a player based
	 * on the values that are passed in
	 *
	 * @param	num	This is the player number identifier ranging from 1 to 4
	 * @param	nam	This is the string representing the player's name
	 * @param	tok	This is the token that represents the player on the board
	 * @return none
	 */
	public Player(int num, String nam, String tok){
		currentSpace = -1;
		lastCard = -1;
		playerName = nam;
		playerNumber = num;
		playerToken = tok;
		grandmasHouse=false;
		if(nam.equalsIgnoreCase("dad")) dad = true;
		else dad = false;
	}

	public Player(int num, String nam, String tok, int space, int c){
		currentSpace = space;
		playerName = nam;
		playerNumber = num;
		playerToken = tok;
		lastCard = c;
		grandmasHouse=false;
		if(nam.equalsIgnoreCase("dad")) dad = true;
		else dad = false;
	}

	/**
	 * This getPlayerName method retrieves the player's name
	 *
	 * @param none
	 * @return	playerName	A string representing the player's name
	 */
	public String getPlayerName(){
		return playerName;
	}

	/**
	 * This getPlayerNumber method retrieves the player's number identifier
	 *
	 * @param none
	 * @return	playerNumber	An integer from 1 to 4 representing player number
	 */
	public int getPlayerNumber(){
		return playerNumber;
	}

	/**
	 * This getLastCard method retrieves the player's last card drawn
	 *
	 * @param none
	 * @return	lastCard	An integer value representing the last card the the player drew
	 */
	public int getLastCard(){
		return lastCard;
	}

	/**
	 * This getCurrentSpace method retrieves the player's current space
	 *
	 * @param none
	 * @return	currentSpace	An integer value representing the current space the player is on
	 */
	public int getCurrentSpace(){
		return currentSpace;
	}

	/**
	 * This getToken method retrieves the player's token
	 *
	 * @param none
	 * @return	playerToken	A string representing the player's token
	 */
	public String getToken(){
		return playerToken;
	}

	/**
	 * This getGrandmasHouse method determines if the player is at grandma's house
	 *
	 * @param none
	 * @return	true	The player is at grandma's house
	 * @return	false	The player is not at grandma's house
	 */
	public Boolean getGrandmasHouse(){
		return grandmasHouse;
	}

	/**
	 * This setPlayerName method sets the player's name
	 *
	 * @param	nam	The string representing the player's name
	 * @return none
	 */
	public void setPlayerName(String nam){
		playerName = nam;
	}

	/**
	 * This setPlayerNumber method sets the player's number
	 *
	 * @param	num	The integer representing the player's number
	 * @return none
	 */
	public void setPlayerNumber(int num){
		playerNumber = num;
	}

	/**
	 * This setLastCard method sets the player's last card
	 *
	 * @param	card	The integer representing the last card the player drew
	 * @return none
	 */
	public void setLastCard(int card){
		lastCard = card;
	}

	/**
	 * This setCurrentSpace method sets the player's current space
	 *
	 * @param	space	The integer representing the current space the player is on
	 * @return none
	 */
	public void setCurrentSpace(int space){
		currentSpace = space;
	}

	/**
	 * This setPlayerToken method sets the player's token
	 *
	 * @param	tok	The string representing the player's token
	 * @return none
	 */
	public void setPlayerToken(String tok){
		playerToken = tok;
	}

	/**
	 * This setGrandmasHouse method sets the variable representing if
	 * the player is at grandma's house or not
	 *
	 * @param	g	The boolean value representing if the player is at grandma's house or not
	 * @return none
	 */
	public void setGrandmasHouse(Boolean g){
		grandmasHouse = g;
	}

	public boolean isDad(){
		return dad;
	}

	/**
	 * This toString method prints out relevant player information
	 *
	 * @param none
	 * @return	str	The string containing the player number, player name, and player token
	 */
	public String toString(){
		String str;
		str = ("Player " + playerNumber + ": " + playerToken + " " + playerName);
		return str;
	}
}
