/**
 * SSQWorldOfSweets.java
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

/**
 * 
 */
public class SSQWorldOfSweets extends JPanel{

	//Global variables
	static final int MAX_SPACES = 60;
	static int players;
	static int lastCardDrawn = -1;
	static Deck gameDeck;
	static int curPlayer=0;
	static JButton drawDeck2;
	static JButton panelTimer;
	static JButton t0, t1, t2, t3;
	static JPanel gameArea;
	static JButton[] buttons;
	static JButton[] candyCards;
	static JLabel L1, L2, L3, L4;
	static Player[] playerObjs;
	static JFrame f;
	static int days = 0;
	static int hours = 0;
	static int minutes = 0;
	static int seconds = 0;
	
	/**
	 * This is the main method that runs and initializes the game window
	 * and initializes the game deck.
	 * 
	 * @param the arguments passed in on the command line
	 * @return none
	 */
	public static void main(String args[]){

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gameDeck = new Deck();

					createAndShowGUI();
				}
			});
	}

	/**
	 * This method creates the game GUI window
	 * 
	 * @param none
	 * @return none
	 */
	private static void createAndShowGUI(){
		f = new MyFrame("World of Sweets!");
		nameEntry();
		addPanel(f.getContentPane());

		f.pack();
		f.setVisible(true);

		//Making frame just shy of full screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int) tk.getScreenSize().getWidth();
		int ySize = (int) tk.getScreenSize().getHeight();
		f.setSize(xSize - 100, ySize - 100);
		f.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * This method is called to help draw panels inside the GUI window
	 * 
	 * @param	p	The contentpane in the myFrame
	 * @return none
	 */
	private static void addPanel(Container p){
		p.setLayout(new BorderLayout());
		JPanel deckArea = new JPanel();
		JPanel playerArea = new JPanel();
		gameArea = new JPanel();

		//Call to draw deckArea panel
		drawDeckArea(deckArea);

		//Call to draw gameArea panel
		drawGameArea(gameArea);

		//Call to draw playerArea panel
		drawPlayerArea(playerArea);

		//Adding Panels to the window
		p.add(playerArea, BorderLayout.EAST);
		p.add(deckArea, BorderLayout.SOUTH);
		p.add(gameArea, BorderLayout.CENTER);

	}

	/**
	 * This method draws the deck area panel
	 * 
	 * @param deckArea	The JPanel object representing the deck area
	 * @return none
	 */
	private static void drawDeckArea(JPanel deckArea){
		deckArea.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Setting background color of deckArea panel
		deckArea.setBackground(Color.pink);

		//Settinf size of deckArea panel
		deckArea.setPreferredSize(new Dimension(1000, 200));

		JLabel deckLabel = new JLabel("Deck Information", SwingConstants.CENTER);
		deckLabel.setFont(new Font("Century", Font.BOLD, 30));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 0;
		deckArea.add(deckLabel, c);
		
		//Padding
		JLabel blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =2;
		c.gridy = 0;
		deckArea.add(blankLabel, c);
		
		//Padding
		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =3;
		c.gridy = 0;
		deckArea.add(blankLabel, c);
		
		//Padding
		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =4;
		c.gridy = 0;
		deckArea.add(blankLabel, c);
		
		//Timer Label
		JLabel timeLabel = new JLabel("Timer", SwingConstants.CENTER);
		timeLabel.setFont(new Font("Century", Font.BOLD, 30));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 5;
		c.gridy = 0;
		deckArea.add(timeLabel, c);
		
		//Padding
		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =0;
		c.gridy = 1;
		deckArea.add(blankLabel, c);
		
		//Click to Draw Card Button
		JButton drawDeck = new JButton();
		try {
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./CardBack.png"));
			drawDeck.setIcon(img);
		} catch (Exception e) {
			System.out.println(e);
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		deckArea.add(drawDeck, c);
		ActionListener actionDraw = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				draw();
				updateTurn();
			}
		};
		drawDeck.addActionListener(actionDraw);	
		
		//Padding
		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.25;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =2;
		c.gridy = 1;
		deckArea.add(blankLabel, c);
		
		//Last Card Drawn Button
		drawDeck2 = new JButton();
		try{
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./NoCard.png"));
			drawDeck2.setIcon(img);
		}catch (Exception e){
			System.out.println(e);
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		deckArea.add(drawDeck2, c);
		
		//Padding
		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =4;
		c.gridy = 1;
		deckArea.add(blankLabel, c);
		
		//Timer Panel
		panelTimer = new JButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weightx = 0;
		c.gridwidth = 1;
		c.gridx = 5;
		c.gridy = 1;
		panelTimer.setBackground(Color.pink);
		panelTimer.setOpaque(true);
		panelTimer.setBorderPainted(false);
		panelTimer.setLayout(new GridLayout(1,4));
		deckArea.add(panelTimer, c);
		
		t0 = new JButton();
		t0.setBackground(Color.white);
		t0.setOpaque(true);
		t0.setBorderPainted(false);
		panelTimer.add(t0);
		
		t1 = new JButton();
		t1.setBackground(Color.white);
		t1.setOpaque(true);
		t1.setBorderPainted(false);
		panelTimer.add(t1);
		
		t2 = new JButton();
		t2.setBackground(Color.white);
		t2.setOpaque(true);
		t2.setBorderPainted(false);
		panelTimer.add(t2);
		
		t3 = new JButton();
		t3.setBackground(Color.white);
		t3.setOpaque(true);
		t3.setBorderPainted(false);
		panelTimer.add(t3);
		
		
		Thread t = new Thread(() ->{
			int millsDelay = 1000;
			ActionListener actionClock = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					updateClock();
				}
			};
			new Timer(millsDelay, actionClock).start();
		});
		
		t.start();
		
		//Last Card Drawn Label below the button
		JLabel lastLabel = new JLabel("Last Card Drawn", SwingConstants.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 2;
		deckArea.add(lastLabel, c);
	}

	/**
	 * This method draws the game area panel
	 * 
	 * @param deckArea	The JPanel object representing the game area
	 * @return none
	 */
	private static void drawGameArea(JPanel gameArea){
		//Setting background color of gameArea panel
		gameArea.setBackground(Color.white);

		//Setting size of gameArea panel
		gameArea.setPreferredSize(new Dimension(750,750));
		buttons = new JButton[55];
		candyCards = new JButton[5];
		
		JButton beginZone = new JButton("Start");
		beginZone.setBackground(Color.white);
		beginZone.setLayout(new GridLayout(2,2));
		beginZone.setOpaque(true);
		beginZone.setBorderPainted(false);
		
		
		switch (playerObjs.length){
			case 4:
				L4 = new JLabel(playerObjs[3].getToken());
				L4.setHorizontalAlignment(JLabel.CENTER);
				beginZone.add(L4);
			case 3:
				L3 = new JLabel(playerObjs[2].getToken());
				L3.setHorizontalAlignment(JLabel.CENTER);
				beginZone.add(L3);
			case 2:
				L1 = new JLabel(playerObjs[0].getToken());
				L1.setHorizontalAlignment(JLabel.CENTER);
				L2 = new JLabel(playerObjs[1].getToken());
				L2.setHorizontalAlignment(JLabel.CENTER);
				beginZone.add(L1);
				beginZone.add(L2);
		}
		
		for(int i = 0; i < 54; i++){
			//Array of JButtons that is the game board spaces
			buttons[i] = new JButton();
			buttons[i].setLayout(new GridLayout(2,2));
		}
		for(int i = 0; i < 5; i++){
			candyCards[i] = new JButton();
			candyCards[i].setBackground(Color.pink);
		}
		gameArea.setLayout(new GridLayout(13, 8, 0, 0));
		for(int i = 0; i <= 53; i++){
			//Hard Coding colors of Spaces
			if((i % 5) == 0 ){
				buttons[i].setBackground(Color.red);
			}
			if((i % 5) == 1){
				buttons[i].setBackground(Color.yellow);
			}
			if((i % 5) == 2){
				buttons[i].setBackground(Color.cyan);
			}
			if((i % 5) == 3){
				if(i == 53)
					buttons[i].setBackground(Color.magenta);
				else
					buttons[i].setBackground(Color.green);
			}
			if((i % 5) == 4){
				buttons[i].setBackground(new Color(255, 201, 14));
			}

			buttons[i].setOpaque(true);
			buttons[i].setBorderPainted(false);
		}

		//Snakes the area of buttons accross the board, adding white space to counteract the grid layout dynamics
		int curSpace = 0;
		gameArea.add(beginZone);
		for(int i = 0; i <= 2; i++){
			if(i == 0){
				
				for(int j = 0; j < 7; j++){
					if(j == 2){
						gameArea.add(candyCards[0]);
					}
					else{
						gameArea.add(buttons[curSpace++]);
					}
				}
			}
			else{
				for(int j = 0; j < 8; j++){
					if(i == 2 && j == 1){
						gameArea.add(candyCards[3]);
					}
					else{
						gameArea.add(buttons[curSpace++]);
					}
				}
			}

			for(int j = 0; j < 7; j++){
				JButton white = new JButton();
				white.setOpaque(true);
				white.setBorderPainted(false);
				white.setBackground(Color.WHITE);
				gameArea.add(white);
			}
			
			if(i == 1){
				gameArea.add(candyCards[2]);
			}
			else{
				gameArea.add(buttons[curSpace++]);
			}
			
			if(i == 1){
				for(int j = 7; j >= 0; j--){
					gameArea.add(buttons[curSpace + j]);
				}
				curSpace = curSpace + 8;
			}
			else{
				for(int j = 6; j >= 0; j--){
					if(i == 0 && j == 5){
						gameArea.add(candyCards[1]);
					}
					else if(i == 2 && j == 2){
						gameArea.add(candyCards[4]);
					}
					gameArea.add(buttons[curSpace + j]);
				}
				curSpace = curSpace + 7;
			}
			
			gameArea.add(buttons[curSpace++]);

			for(int j = 0; j < 7; j++){
				JButton white = new JButton();
				white.setOpaque(true);
				white.setBorderPainted(false);
				white.setBackground(Color.WHITE);
				gameArea.add(white);
			}
		}
		gameArea.add(buttons[curSpace++]);
		gameArea.add(buttons[curSpace++]);
		gameArea.add(buttons[curSpace++]);
		gameArea.add(buttons[curSpace++]);
		gameArea.add(buttons[curSpace++]);

		
		JButton endzone1 = new JButton();
		endzone1.setLayout(new GridLayout(2, 1));
		JLabel ezL1 = new JLabel("Grandma's");
		JLabel ezL2 = new JLabel("House");
		ezL1.setFont(new Font("Ariel", Font.BOLD, 15));
		ezL2.setFont(new Font("Ariel", Font.BOLD, 15));
		ezL1.setHorizontalAlignment(JLabel.CENTER);
		ezL2.setHorizontalAlignment(JLabel.CENTER);
		endzone1.setBackground(Color.magenta);
		endzone1.setBorderPainted(false);
		endzone1.setOpaque(true);
		endzone1.add(ezL1);
		endzone1.add(ezL2);
		
		gameArea.add(endzone1);

		JButton house = new JButton();
		house.setBackground(Color.white);
		house.setBorderPainted(false);
		house.setOpaque(true);
		try {
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./Grandma.png"));
			house.setIcon(img);
		} catch (Exception e) {
			System.out.println(e);
		}
		gameArea.add(buttons[curSpace]);
		gameArea.add(house);		
	}

	/**
	 * This method draws the player area panel
	 * 
	 * @param deckArea	The JPanel object representing the player area
	 * @return none
	 */
	private static void drawPlayerArea(JPanel playerArea)
	{
		//Setting main label in playerArea panel
		JLabel playerLabel = new JLabel("Player Information");
		playerLabel.setFont(new Font("Century", Font.BOLD, 25));
		playerArea.setLayout(new BorderLayout());
		playerArea.add(playerLabel, BorderLayout.NORTH);

		//Setting background color in playerArea panel
		playerArea.setBackground(Color.pink);

		//Setting size of playerArea panel
		playerArea.setPreferredSize(new Dimension(250, 750));

		playerArea.setLayout(new GridLayout(8, 1));
		JLabel[] allLabels;
		allLabels = generatePlayers(playerObjs.length);
		for(int i= 0; i<playerObjs.length; i++) {
			playerArea.add(allLabels[i]);
		}
	}

	/**
	 * This method draws a card from the deck and displays the last card 
	 * drawn, and if necessary, shuffles and redraws from the deck
	 * 
	 * @param none
	 * @return none
	 */
	private static void draw(){
		ImageIcon img;
		if(gameDeck.empty() == false){
			lastCardDrawn = gameDeck.drawCard();
			playerObjs[curPlayer].setLastCard(lastCardDrawn);

			try {
				switch(lastCardDrawn) {
					case -1:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./NoCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 0:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./RedCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 1:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./YellowCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 2:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./BlueCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 3:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./GreenCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 4:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./OrangeCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 5:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleRedCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 6:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleYellowCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 7:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleBlueCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 8:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleGreenCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 9:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleOrangeCard.png"));
						drawDeck2.setIcon(img);
						break;
					case 10:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./SkipATurn.png"));
						drawDeck2.setIcon(img);
						break;
					case 11:
						img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./MiddleCard.png"));
						drawDeck2.setIcon(img);
						break;
				/*	case 11:
						 img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./CandyCorn.png"));
						drawDeck2.setIcon(img);
						break;
					case 12:
						 img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./Lollipop.png"));
						drawDeck2.setIcon(img);
						break;
					case 13:
						 img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./CandyWrapper.png"));
						drawDeck2.setIcon(img);
						break;
					case 14:
						 img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./CandyBar.png"));
						drawDeck2.setIcon(img);
						break;
					case 15:
						 img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./Cake.png"));
						drawDeck2.setIcon(img);
						break;  */
				}
		} catch (Exception e) {
				System.out.println(e);
		}
			drawDeck2.repaint();

		}
		else{

			lastCardDrawn = -1;
			 img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./NoCard.png"));
			drawDeck2.setIcon(img);

			JOptionPane.showMessageDialog(null, "You ran out of cards in the deck! Click OK to shuffle and redraw!");
			gameDeck.shuffle();
			drawDeck2.repaint();
			draw();
		}
	}

  /**
   * This method moves the player and checks to see if they are at grandma's 
   * house yet. If they are, the players are informed about who won and are 
   * asked if they want to play again.  If so, the game is reloaded, and if 
   * not, the game window is closed.
   * 
   * @param none
   * @return none
   */
  private static void updateTurn()
  {
		movePlayer();
		if(playerObjs[curPlayer].getGrandmasHouse() || playerObjs[curPlayer].getCurrentSpace()==50){
			if (JOptionPane.showConfirmDialog(null, playerObjs[curPlayer].getPlayerName()+" won the game! Have fun at Grandma's House!\nPlay again?", "WINNER WINNER WINNER",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				//yes option
				curPlayer=0;
				gameDeck = new Deck();
				f.dispose();
				createAndShowGUI();
			}
			else {
				f.dispose();
			}
		}
		else{
			curPlayer++;
			if(curPlayer == playerObjs.length) curPlayer = 0;
			JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
		}
  }

	/**
	 * This method is a helper method that collects the player information, stores it in an array of JLabels and returns it to the caller
	 * 
	 * @param 	num				An integer representing the number of players in the game
	 * @return	playerLabels	An array of JLabels that are strings of the players information to be displayed in the player info panel
	 */
	private static JLabel[] generatePlayers(int num)
	{
		JLabel[] playerLabels = new JLabel[num];
		for(int i = 0; i<num; i++) {
			playerLabels[i] = new JLabel(playerObjs[i].toString());
			playerLabels[i].setFont(new Font("Century", Font.BOLD, 15));
			playerLabels[i].setForeground(new Color(0,0,0));
		}
		return playerLabels;
	}

	/**
	 * This method is a helper method that collects player information from 
	 * the users such as number of players, player names, and player tokens
	 * 
	 * @param none
	 * @return none
	 */
	private static void nameEntry()
	{
		try{
			String[] numOfPlayers = {"2", "3", "4"};
			String[] symbolsOfPlayers = {"@", "#", "$", "%"};
			ArrayList<String> al = new ArrayList<String>(Arrays.asList("@", "#", "$", "%"));
			Object playersO = JOptionPane.showInputDialog(null, "How many players are here?", "Welcome to World Of Sweets!", JOptionPane.DEFAULT_OPTION, null, numOfPlayers, "2");
			playerObjs = new Player[Integer.parseInt(playersO.toString())];
			for(int i=0; i < playerObjs.length; i++)
			{
			  String tempName = JOptionPane.showInputDialog(null, "What is your name?", "Player " + (i+1), JOptionPane.QUESTION_MESSAGE);
				playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player " + (i+1), JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "@");
				String str = playersO.toString();
				al.remove(str);
				symbolsOfPlayers=al.toArray(new String[al.size()]);
			  playerObjs[i] = new Player(i+1, tempName, str);
			}
		}
		//This catches if the user closes or exits from a name or token prompt before the game loads
		catch(java.lang.NullPointerException npe){
			System.exit(1);
		}
	}

	/**
	 * This method determines which space the current player is on, 
	 * determines which space the player will move to based on the card 
	 * that they drew, and moves that player to the corresponding space
	 * 
	 * @param none
	 * @return none
	 */
	private static void movePlayer(){
		int space = playerObjs[curPlayer].getCurrentSpace();
		int card = playerObjs[curPlayer].getLastCard();
		Color c = new Color(255,255,255);
		if(space > -1){
			c = buttons[space].getBackground();
		}
		
		if(space == -1){
			switch(card){
				case 0:
					playerObjs[curPlayer].setCurrentSpace(space + 1);
					break;
				case 1:
					playerObjs[curPlayer].setCurrentSpace(space + 2);
					break;
				case 2:
					playerObjs[curPlayer].setCurrentSpace(space + 3);
					break;
				case 3:
					playerObjs[curPlayer].setCurrentSpace(space + 4);
					break;
				case 4:
					playerObjs[curPlayer].setCurrentSpace(space + 5);
					break;
				case 5:
					playerObjs[curPlayer].setCurrentSpace(space + 6);
					break;
				case 6:
					playerObjs[curPlayer].setCurrentSpace(space + 7);
					break;
				case 7:
					playerObjs[curPlayer].setCurrentSpace(space + 8);
					break;
				case 8:
					playerObjs[curPlayer].setCurrentSpace(space + 9);
					break;
				case 9:
					playerObjs[curPlayer].setCurrentSpace(space + 10);
					break;
				case 10:
					playerObjs[curPlayer].setCurrentSpace(space);
					break;
				case 11:
					playerObjs[curPlayer].setCurrentSpace(25);
					break;
			}
		}
		else if(c.equals(Color.red)){
			switch(card){
				case 0:
					playerObjs[curPlayer].setCurrentSpace(space + 5);
					break;
				case 1:
				
					playerObjs[curPlayer].setCurrentSpace(space + 1);
					break;
				case 2:
					playerObjs[curPlayer].setCurrentSpace(space + 2);
					break;
				case 3:
					playerObjs[curPlayer].setCurrentSpace(space + 3);
					break;
				case 4:
					playerObjs[curPlayer].setCurrentSpace(space + 4);
					break;
				case 5:
					playerObjs[curPlayer].setCurrentSpace(space + 10);
					break;
				case 6:
					playerObjs[curPlayer].setCurrentSpace(space + 6);
					break;
				case 7:
					playerObjs[curPlayer].setCurrentSpace(space + 7);
					break;
				case 8:
					playerObjs[curPlayer].setCurrentSpace(space + 8);
					break;
				case 9:
					playerObjs[curPlayer].setCurrentSpace(space + 9);
					break;
				case 10:
					playerObjs[curPlayer].setCurrentSpace(space);
					break;
				case 11:
					playerObjs[curPlayer].setCurrentSpace(25);
					break;
			}
		}
		else if(c.equals(Color.yellow)){
			switch(card){
				case 0:
					playerObjs[curPlayer].setCurrentSpace(space + 4);
					break;
				case 1:
					playerObjs[curPlayer].setCurrentSpace(space + 5);
					break;
				case 2:
					playerObjs[curPlayer].setCurrentSpace(space + 1);
					break;
				case 3:
					playerObjs[curPlayer].setCurrentSpace(space + 2);
					break;
				case 4:
					playerObjs[curPlayer].setCurrentSpace(space + 3);
					break;
				case 5:
					playerObjs[curPlayer].setCurrentSpace(space + 9);
					break;
				case 6:
					playerObjs[curPlayer].setCurrentSpace(space + 10);
					break;
				case 7:
					playerObjs[curPlayer].setCurrentSpace(space + 6);
					break;
				case 8:
					playerObjs[curPlayer].setCurrentSpace(space + 7);
					break;
				case 9:
					playerObjs[curPlayer].setCurrentSpace(space + 8);
					break;
				case 10:
					playerObjs[curPlayer].setCurrentSpace(space);
					break;
				case 11:
					playerObjs[curPlayer].setCurrentSpace(25);
					break;
			}
		}
		else if(c.equals(Color.cyan)){
			switch(card){
				case 0:
					playerObjs[curPlayer].setCurrentSpace(space + 3);
					break;
				case 1:
					playerObjs[curPlayer].setCurrentSpace(space + 4);
					break;
				case 2:
					playerObjs[curPlayer].setCurrentSpace(space + 5);
					break;
				case 3:
					playerObjs[curPlayer].setCurrentSpace(space + 1);
					break;
				case 4:
					playerObjs[curPlayer].setCurrentSpace(space + 2);
					break;
				case 5:
					playerObjs[curPlayer].setCurrentSpace(space + 8);
					break;
				case 6:
					playerObjs[curPlayer].setCurrentSpace(space + 9);
					break;
				case 7:
					playerObjs[curPlayer].setCurrentSpace(space + 10);
					break;
				case 8:
					playerObjs[curPlayer].setCurrentSpace(space + 6);
					break;
				case 9:
					playerObjs[curPlayer].setCurrentSpace(space + 7);
					break;
				case 10:
					playerObjs[curPlayer].setCurrentSpace(space);
					break;
				case 11:
					playerObjs[curPlayer].setCurrentSpace(25);
					break;
			}
		}
		else if(c.equals(Color.green)){
			switch(card){
				case 0:
					playerObjs[curPlayer].setCurrentSpace(space + 2);
					break;
				case 1:
					playerObjs[curPlayer].setCurrentSpace(space + 3);
					break;
				case 2:
					playerObjs[curPlayer].setCurrentSpace(space + 4);
					break;
				case 3:
					playerObjs[curPlayer].setCurrentSpace(space + 5);
					break;
				case 4:
					playerObjs[curPlayer].setCurrentSpace(space + 1);
					break;
				case 5:
					playerObjs[curPlayer].setCurrentSpace(space + 7);
					break;
				case 6:
					playerObjs[curPlayer].setCurrentSpace(space + 8);
					break;
				case 7:
					playerObjs[curPlayer].setCurrentSpace(space + 9);
					break;
				case 8:
					playerObjs[curPlayer].setCurrentSpace(space + 10);
					break;
				case 9:
					playerObjs[curPlayer].setCurrentSpace(space + 6);
					break;
				case 10:
					playerObjs[curPlayer].setCurrentSpace(space);
					break;
				case 11:
					playerObjs[curPlayer].setCurrentSpace(25);
					break;
			}
		}
		else{
			switch(card){
				case 0:
					playerObjs[curPlayer].setCurrentSpace(space + 1);
					break;
				case 1:
					playerObjs[curPlayer].setCurrentSpace(space + 2);
					break;
				case 2:
					playerObjs[curPlayer].setCurrentSpace(space + 3);
					break;
				case 3:
					playerObjs[curPlayer].setCurrentSpace(space + 4);
					break;
				case 4:
					playerObjs[curPlayer].setCurrentSpace(space + 5);
					break;
				case 5:
					playerObjs[curPlayer].setCurrentSpace(space + 6);
					break;
				case 6:
					playerObjs[curPlayer].setCurrentSpace(space + 7);
					break;
				case 7:
					playerObjs[curPlayer].setCurrentSpace(space + 8);
					break;
				case 8:
					playerObjs[curPlayer].setCurrentSpace(space + 9);
					break;
				case 9:
					playerObjs[curPlayer].setCurrentSpace(space + 10);
					break;
				case 10:
					playerObjs[curPlayer].setCurrentSpace(space);
					break;
				case 11:
					playerObjs[curPlayer].setCurrentSpace(25);
					break;
			}
		}
		if(playerObjs[curPlayer].getCurrentSpace() == 53)
			playerObjs[curPlayer].setGrandmasHouse(true);
		addLabels();
	}
	
	/**
	 * This method adds the player token label to the button that the 
	 * player is supposed to move to
	 * 
	 * @param none
	 * @return none
	 */
	private static void addLabels(){
		if(curPlayer == 0){
			if(playerObjs[0].getCurrentSpace() >= MAX_SPACES){
				playerObjs[0].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[0].getCurrentSpace()].add(L1);
			}
			else if(playerObjs[0].getCurrentSpace() != -1){
				buttons[playerObjs[0].getCurrentSpace()].add(L1);
			}
		}
		else if(curPlayer == 1){
			if(playerObjs[1].getCurrentSpace() >= MAX_SPACES){
				playerObjs[1].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[1].getCurrentSpace()].add(L2);
			}
			else if(playerObjs[1].getCurrentSpace() != -1){
				buttons[playerObjs[1].getCurrentSpace()].add(L2);
			}
		}
		else if(curPlayer == 2){
			if(playerObjs[2].getCurrentSpace() >= MAX_SPACES){
				playerObjs[2].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[2].getCurrentSpace()].add(L3);
			}
			else if(playerObjs[2].getCurrentSpace() != -1){
				buttons[playerObjs[2].getCurrentSpace()].add(L3);
			}
		}
		else{
			if(playerObjs[3].getCurrentSpace() >= MAX_SPACES){
				playerObjs[3].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[3].getCurrentSpace()].add(L4);
			}
			else if(playerObjs[3].getCurrentSpace() != -1){
				buttons[playerObjs[3].getCurrentSpace()].add(L4);
			}
		}
		gameArea.repaint();
	}
	
	private static void updateClock(){
		seconds++;
		if(seconds == 60){
			minutes++;
			seconds = 0;
		}
		if(minutes == 60){
			hours++;
			minutes = 0;
		}
		if(hours == 24){
			days++;
			hours = 0;
		}
		
		try{
			if(days < 10){
				String secString = "./seconds/s";
				if(seconds < 10){
					secString = secString.concat("0"+seconds+".png");
				}
				else{
					secString = secString.concat(seconds+".png");
				}
				
				ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(secString));
				t3.setIcon(img);
				
				String minString = "./minutes/m";
				if(minutes < 10){
					minString = minString.concat("0"+minutes+".png");
				}
				else{
					minString = minString.concat(minutes+".png");
				}
				
				img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(minString));
				t2.setIcon(img);
				
				String hourString = "./hours/h";
				if(hours < 10){
					hourString = hourString.concat("0"+hours+".png");
				}
				else{
					hourString = hourString.concat(hours+".png");
				}
				img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(hourString));
				t1.setIcon(img);
				
				String dayString = "./days/d";
				dayString = dayString.concat("0"+days+".png");
				img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(dayString));
				t0.setIcon(img);
				
				t0.repaint();
				t1.repaint();
				t2.repaint();
				t3.repaint();
			}
			else{
				ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./seconds/s00.png"));
				t3.setIcon(img);
				img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./minutes/m00.png"));
				t2.setIcon(img);
				img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./hours/h00.png"));
				t1.setIcon(img);
				img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./days/d10.png"));
				t0.setIcon(img);
				
				t0.repaint();
				t1.repaint();
				t2.repaint();
				t3.repaint();
			}
		}catch(Exception e) {
				System.out.println(e);
		}
		
		
	}
}

/**
 * This is the MyFrame class
 */
class MyFrame extends JFrame {
	/**
	 * This MyFrame initialization sets the title and handles the 
	 * window being closed
	 * 
	 * @param n	The title to be set
	 * @return none
	 */
	public MyFrame(String n){
		setTitle(n);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();

		contentPane.add(new SSQWorldOfSweets());
	}
}
