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
import java.awt.Robot;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	static int gameMode = 0;
	static JButton drawDeck2;
	static Object playersO;
	static int aiO;
	static JButton panelTimer;
	static JButton t0, t1, t2, t3;
	static JPanel gameArea;
	static JPanel playerArea;
	static JButton[] buttons;
	static JButton[] candyCards;
	static JButton[][] boomButtons;
	static JLabel L1, L2, L3, L4;
	static Player[] playerObjs;
	static JFrame f;
	static int days = 0;
	static int hours = 0;
	static int minutes = 0;
	static int seconds = 0;
	static SpaceFinder sf;
	static Thread t;
	static Timer currTimer;
	static boolean loaded;
	static boolean successfulLoad = false;
	static JButton beginZone;
	static Robot robot;

	/**
	 * This is the main method that runs and initializes the game window
	 * and initializes the game deck.
	 *
	 * @param the arguments passed in on the command line
	 * @return none
	 */
	public static void main(String args[]){
		startUp();
	}

	private static void startUp(){
		try{
			robot = new Robot();
		} catch(Exception e) { System.out.println(e); }


		javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try{
						if (JOptionPane.showConfirmDialog(null, "Do you want to load a previous game?", "Welcome to World of Sweets!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								loaded = true;
								load();
						}
						else{
							gameDeck = new Deck();
							loaded = false;
						}
						//Ensures game is loaded after successful file read, or starting a new game
						if(successfulLoad == true || loaded == false){
							sf = new SpaceFinder();
							createAndShowGUI();
						}
					}
					catch(java.lang.NullPointerException npe){
						System.out.println(npe);
						System.exit(1);
					}
					catch(NoSuchAlgorithmException nsae1){
						System.out.println(nsae1);
						System.exit(1);
					}

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
		if(!loaded){
			playerTypes();
			String[] choices = {"Classic", "Strategic"};
			Object selected = JOptionPane.showInputDialog(null, "What game mode would you like to play?", "Game Mode", JOptionPane.DEFAULT_OPTION, null, choices, "Classic");
			if(selected.toString().equals("Classic"))
				gameMode = 0;
			else
				gameMode = 1;
			nameEntry();
		}
		addPanel(f.getContentPane());

		f.pack();
		f.setVisible(true);

		//Making frame just shy of full screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int) tk.getScreenSize().getWidth();
		int ySize = (int) tk.getScreenSize().getHeight();
		f.setSize(xSize - 100, ySize - 100);
		f.setLocationRelativeTo(null);
		if(loaded) {
			int saveCurPlayer = curPlayer;
			for(int i = 0; i < playerObjs.length; i++){
				boolean candyCardCheck=false;
				int candyCardNum=0;
				curPlayer = i;
				int card = playerObjs[curPlayer].getLastCard();
				if(card != -1){
					if (card > 10 && card < 16){
						candyCardCheck = true;
						switch (card){
							case 11:
								candyCardNum = 0;
								break;
							case 12:
								candyCardNum = 1;
								break;
							case 13:
								candyCardNum = 2;
								break;
							case 14:
								candyCardNum = 3;
								break;
							case 15:
								candyCardNum = 4;
								break;
						}
					}

					addLabels(candyCardCheck, candyCardNum);
				}
				else{
					switch(i){
						case 0:
							beginZone.add(L1);
							break;
						case 1:
							beginZone.add(L2);
							break;
						case 2:
							beginZone.add(L3);
							break;
						case 3:
							beginZone.add(L4);
							break;
					}

				}
			}
			curPlayer = saveCurPlayer;
			displayDrawnCard(lastCardDrawn);
		}
		if(playerObjs[curPlayer].isAI()){
			pressEnter();
		}
		JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
		if(playerObjs[curPlayer].isAI()){
			AIturn();
		}
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
		playerArea = new JPanel();
		if(gameMode > 0)
			playerArea.setLayout(new GridLayout(2,1));
		// System.out.println(gameMode);
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

		//Setting size of deckArea panel
		deckArea.setPreferredSize(new Dimension(1000, 200));

		//Save Button
		JButton saveButton = new JButton("Save and Exit");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.50;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		saveButton.setOpaque(true);
		saveButton.setBorderPainted(false);
		ActionListener saveAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					saveGame();
				}
				catch(Exception exc) {
					System.out.println(exc);
					System.exit(1);
				}
			}
		};
		saveButton.addActionListener(saveAction);
		deckArea.add(saveButton, c);


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

		//Classic Mode
		if(gameMode==0){
			//Padding
			blankLabel = new JLabel("");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;
			c.ipady = 0;
			c.gridwidth = 1;
			c.gridx =0;
			c.gridy = 1;
			deckArea.add(blankLabel, c);
		}
		//Strategic Mode
		else{
			// Button
			JButton boomerangButton = new JButton("Use Boomerang");

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.50;
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = 1;
			boomerangButton.setOpaque(true);
			boomerangButton.setBorderPainted(false);
			ActionListener boomerangAction = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						useBoomerang();
					}
					catch(Exception e01) {
						System.out.println(e01);
						System.exit(1);
					}
				}
			};
			boomerangButton.addActionListener(boomerangAction);
			deckArea.add(boomerangButton, c);

		}





		//Click to Draw Card Button
		JButton drawDeck = new JButton();
		try {
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/CardBack.png"));
			drawDeck.setIcon(img);
		} catch (Exception e) {
			System.out.println(e);
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		drawDeck.setBackground(Color.pink);
		drawDeck.setOpaque(true);
		drawDeck.setBorderPainted(false);
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
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/NoCard.png"));
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
		drawDeck2.setBackground(Color.pink);
		drawDeck2.setOpaque(true);
		drawDeck2.setBorderPainted(false);
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


		t = new Thread(() ->{
			int millsDelay = 1000;
			currTimer = new Timer(millsDelay, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					updateClock();
				}
			});
			currTimer.start();
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
		gameArea.setPreferredSize(new Dimension(650,750));
		JButton[] buttonsInit = new JButton[61];
		int count = 1;
		buttons = new JButton[61];
		candyCards = new JButton[5];

		beginZone = new JButton("Start");
		beginZone.setBackground(Color.white);
		beginZone.setLayout(new GridLayout(2,2));
		beginZone.setOpaque(true);
		beginZone.setBorderPainted(false);

		switch (playerObjs.length){
			case 4:
				L4 = new JLabel(playerObjs[3].getToken());
				L4.setHorizontalAlignment(JLabel.CENTER);
				if(!loaded)beginZone.add(L4);
			case 3:
				L3 = new JLabel(playerObjs[2].getToken());
				L3.setHorizontalAlignment(JLabel.CENTER);
				if(!loaded)beginZone.add(L3);
			case 2:
				L1 = new JLabel(playerObjs[0].getToken());
				L1.setHorizontalAlignment(JLabel.CENTER);
				L2 = new JLabel(playerObjs[1].getToken());
				L2.setHorizontalAlignment(JLabel.CENTER);
				if(!loaded)beginZone.add(L1);
				if(!loaded)beginZone.add(L2);
		}

		buttonsInit[0] = beginZone;
		for(int i = 1; i < 60; i++){
			//Array of JButtons that is the game board spaces
			buttonsInit[i] = new JButton();
			buttonsInit[i].setLayout(new GridLayout(2,2));
		}
		for(int i = 0; i < 5; i++){
			candyCards[i] = new JButton();
			candyCards[i].setLayout(new BorderLayout());
			candyCards[i].setBackground(Color.pink);
		}
		paintCandyCards();

		gameArea.setLayout(new GridLayout(13, 8, 0, 0));
		for(int i = 0; i <= 54; i++){
			//Hard Coding colors of Spaces
			if((i % 5) == 1 ){
				buttonsInit[i].setBackground(Color.red);
			}
			if((i % 5) == 2){
				buttonsInit[i].setBackground(Color.yellow);
			}
			if((i % 5) == 3){
				buttonsInit[i].setBackground(Color.cyan);
			}
			if((i % 5) == 4){
				buttonsInit[i].setBackground(Color.green);
			}
			if((i % 5) == 0){
				if(i != 0){
					buttonsInit[i].setBackground(new Color(255, 201, 14));
				}
			}
			buttonsInit[i].setOpaque(true);
			buttonsInit[i].setBorderPainted(false);
		}
		buttons[0] = buttonsInit[0];
		for (int i = 1; i < 60; i++){
			if(i == 3)
				buttons[i] = candyCards[4];
			else if(i == 15)
				buttons[i] = candyCards[2];
			else if(i == 26)
				buttons[i] = candyCards[3];
			else if(i == 37)
				buttons[i] = candyCards[1];
			else if(i == 47)
				buttons[i] = candyCards[0];
			else{
				if((count % 5) == 1 ){
					sf.addRed(i);
				}
				if((count % 5) == 2){
					sf.addYellow(i);
				}
				if((count % 5) == 3){
					if(count == 60){
						sf.setGrandma(i);
					}
					else{
						sf.addBlue(i);
					}
				}
				if((count % 5) == 4){
					sf.addGreen(i);
				}
				if((count % 5) == 0){
					if(i != 0){
						sf.addOrange(i);
					}
				}
				buttons[i] = buttonsInit[count];
				count++;
			}
		}

		//Snakes the area of buttons accross the board, adding white space to counteract the grid layout dynamics

		gameArea.add(beginZone);
		int curSpace = 1;
		for(int i = 0; i <= 2; i++){
			if(i == 0){
				for(int j = 0; j < 7; j++){
					gameArea.add(buttons[curSpace++]);
				}
			}
			else{
				for(int j = 0; j < 8; j++){
					gameArea.add(buttons[curSpace++]);
				}
			}

			for(int j = 0; j < 7; j++){
				JButton white = new JButton();
				white.setOpaque(true);
				white.setBorderPainted(false);
				white.setBackground(Color.WHITE);
				gameArea.add(white);
			}

			gameArea.add(buttons[curSpace++]);

			for(int j = 7; j >= 0; j--){
				gameArea.add(buttons[curSpace + j]);
			}
			curSpace = curSpace + 8;

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



		JButton house = new JButton();
		house.setBackground(Color.white);
		house.setBorderPainted(false);
		house.setOpaque(true);
		try {
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./spaces/Grandma.png"));
			house.setIcon(img);
		} catch (Exception e) {
			System.out.println(e);
		}

		buttons[curSpace] = endzone1;
		gameArea.add(buttons[curSpace]);
		gameArea.add(house);
	}

	/**
	 *
	 *
	 */
	private static void paintCandyCards(){
		try {
			ImageIcon lollipop = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./spaces/LollipopSpace.png"));
			ImageIcon candy = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./spaces/WrappedCandySpace.png"));
			ImageIcon chocolate = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./spaces/ChocolateSpace.png"));
			ImageIcon cake = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./spaces/CakeSpace.png"));
			ImageIcon corn = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./spaces/CandyCornSpace.png"));
			candyCards[4].setIcon(cake);
			candyCards[3].setIcon(chocolate);
			candyCards[2].setIcon(candy);
			candyCards[1].setIcon(lollipop);
			candyCards[0].setIcon(corn);

		} catch (Exception e) {
			System.out.println(e);
		}

	}


	/**
	 * This method draws the player area panel
	 *
	 * @param deckArea	The JPanel object representing the player area
	 * @return none
	 */
	private static void drawPlayerArea(JPanel playerArea){
		playerArea.setBackground(Color.pink);
		JPanel namesPanel = new JPanel();
		JPanel boomPanel = new JPanel();
		namesPanel.setBackground(Color.pink);
		boomPanel.setBackground(Color.pink);
		//Setting main label in playerArea panel
		JLabel playerLabel = new JLabel("Player Information");
		playerLabel.setFont(new Font("Century", Font.BOLD, 25));
		//Setting size of namesPanel panel
		playerArea.setPreferredSize(new Dimension(350, 750));

		if(gameMode > 0){
			namesPanel.setLayout(new GridLayout(5, 1, 2, 2));
			namesPanel.add(playerLabel);
		}
		else{
			playerArea.setLayout(new GridLayout(5, 1, 2, 2));
			playerArea.add(playerLabel);
		}

		JLabel[] allLabels;
		allLabels = generatePlayers(playerObjs.length);
		for(int i= 0; i<playerObjs.length; i++) {
			if(gameMode > 0)
				namesPanel.add(allLabels[i]);
			else
				playerArea.add(allLabels[i]);
		}

		if(gameMode > 0){
			playerArea.add(namesPanel);

			JLabel l1, l2, l3, l4;
			JButton[] but = new JButton[13];
			int j = 0;
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/boomerang.png"));
			l1 = new JLabel(playerObjs[0].getToken());
			l1.setFont(new Font("Century", Font.BOLD, 35));
			l2 = new JLabel(playerObjs[1].getToken());
			l2.setFont(new Font("Century", Font.BOLD, 35));
			l3 = new JLabel();
			l4 = new JLabel();

			if(playerObjs.length == 2){
				boomPanel.setLayout(new GridLayout(2, 4, 10, 10));
				boomButtons = new JButton[2][3];
			}
			else if(playerObjs.length == 3){
				boomPanel.setLayout(new GridLayout(3, 4, 10, 10));
				boomButtons = new JButton[3][3];
			}
			else{
				boomPanel.setLayout(new GridLayout(4, 4, 10, 10));
				boomButtons = new JButton[4][3];
			}
			switch(playerObjs.length){
				case 4:
					l4 = new JLabel(playerObjs[3].getToken());
					l4.setFont(new Font("Century", Font.BOLD, 35));
					but[9] = new JButton();
					but[10] = new JButton();
					but[11] = new JButton();
					boomButtons[3][0] = but[9];
					boomButtons[3][1] = but[10];
					boomButtons[3][2] = but[11];
				case 3:
					l3 = new JLabel(playerObjs[2].getToken());
					l3.setFont(new Font("Century", Font.BOLD, 35));
					but[6] = new JButton();
					but[7] = new JButton();
					but[8] = new JButton();
					boomButtons[2][0] = but[6];
					boomButtons[2][1] = but[7];
					boomButtons[2][2] = but[8];
				case 2:
					but[0] = new JButton();
					but[1] = new JButton();
					but[2] = new JButton();
					but[3] = new JButton();
					but[4] = new JButton();
					but[5] = new JButton();
					boomButtons[0][0] = but[0];
					boomButtons[0][1] = but[1];
					boomButtons[0][2] = but[2];
					boomButtons[1][0] = but[3];
					boomButtons[1][1] = but[4];
					boomButtons[1][2] = but[5];

			}
			int i = 0;
			boomPanel.add(l1);
			int drawPlayer = 0;
			int playerBooms = playerObjs[drawPlayer].getBoomerangs();
			while(but[i] != null){
				// System.out.println("Booms: "+playerBooms);

				if(i == 3){
					// boomPanel.add(l2);
					drawPlayer = 1;
					playerBooms = playerObjs[drawPlayer].getBoomerangs();
				}
				if(i == 6){
					// boomPanel.add(l3);
					drawPlayer = 2;
					playerBooms = playerObjs[drawPlayer].getBoomerangs();
				}

				if(i == 9){
					// boomPanel.add(l4);
					drawPlayer = 3;
					playerBooms = playerObjs[drawPlayer].getBoomerangs();
				}


				if(playerBooms > 0){
					but[i].setIcon(img);
					playerBooms--;
				}
				else{
					but[i].setIcon(null);
				}

				if(i == 3) boomPanel.add(l2);
				if(i == 6) boomPanel.add(l3);
				if(i == 9) boomPanel.add(l4);

				boomPanel.add(but[i]);
				i++;
			}





			playerArea.add(boomPanel);
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
		if(gameDeck.empty() == false){
			if(playerObjs[curPlayer].isDad()){
				int replaceCard = sf.findDadCard(playerObjs[curPlayer].getCurrentSpace(), gameDeck);
				gameDeck.moveToTop(replaceCard);
			}
			lastCardDrawn = gameDeck.drawCard();
			if(lastCardDrawn != 16) playerObjs[curPlayer].setLastCard(lastCardDrawn);
			displayDrawnCard(lastCardDrawn);
		}
		else{

			lastCardDrawn = -1;
			displayDrawnCard(lastCardDrawn);

			JOptionPane.showMessageDialog(null, "You ran out of cards in the deck! Click OK to shuffle and redraw!");
			gameDeck.shuffle();
			draw();
		}
	}

	public static void displayDrawnCard(int card){
		ImageIcon img;
		try {
			switch(card) {
				case -1:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/NoCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 0:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/RedCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 1:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/YellowCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 2:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/BlueCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 3:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/GreenCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 4:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/OrangeCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 5:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/DoubleRedCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 6:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/DoubleYellowCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 7:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/DoubleBlueCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 8:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/DoubleGreenCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 9:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/DoubleOrangeCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 10:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/SkipATurn.png"));
					drawDeck2.setIcon(img);
					break;
				case 11:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/CandyCornCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 12:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/LollipopCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 13:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/WrappedCandy.png"));
					drawDeck2.setIcon(img);
					break;
				case 14:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/ChocolateCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 15:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/CakeCard.png"));
					drawDeck2.setIcon(img);
					break;
				case 16:
					img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./cards/SwapCard.png"));
					drawDeck2.setIcon(img);
					break;
			}
	} catch (Exception e) {
			System.out.println(e);
	}
		drawDeck2.repaint();


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
	private static void updateTurn(){
		if(lastCardDrawn != 16)movePlayer();
		else swapPlayers();
		if(playerObjs[curPlayer].getGrandmasHouse()){
			//Play again
			if(playerObjs[curPlayer].isAI()) JOptionPane.showConfirmDialog(null, "Good job everyone!!!!!!!!!!!!!!!!", "", JOptionPane.PLAIN_MESSAGE);
			if (JOptionPane.showConfirmDialog(null, playerObjs[curPlayer].getPlayerName()+" won the game! Have fun at Grandma's House!\nPlay again?", "WINNER WINNER WINNER", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				curPlayer=0;
				gameDeck = new Deck();
				lastCardDrawn = -1;
				curPlayer=0;
				days = 0;
				hours = 0;
				minutes = 0;
				seconds = 0;
				currTimer.stop();

				try{
					t.join();
				}catch(InterruptedException iex){
					System.out.println(iex);
					System.exit(1);
				}
				f.dispose();
				startUp();
			}
			//Exit game
			else {
				f.dispose();
				System.exit(0);
			}
		}
		else{
			curPlayer++;
			if(curPlayer == playerObjs.length) curPlayer = 0;
			JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
			if(playerObjs[curPlayer].isAI()) {
				AIturn();
			}
		}
  }

	/**
	 * This method is a helper method that collects the player information, stores it in an array of JLabels and returns it to the caller
	 *
	 * @param 	num				An integer representing the number of players in the game
	 * @return	playerLabels	An array of JLabels that are strings of the players information to be displayed in the player info panel
	 */
	private static JLabel[] generatePlayers(int num){
		JLabel[] playerLabels = new JLabel[num];
		for(int i = 0; i<num; i++) {
			playerLabels[i] = new JLabel(playerObjs[i].toString(gameMode));
			playerLabels[i].setFont(new Font("Century", Font.BOLD, 25));
			playerLabels[i].setForeground(new Color(0,0,0));
		}
		return playerLabels;
	}


	private static void playerTypes(){
			String[] numOfPlayers = {"2", "3", "4"};
			playersO = JOptionPane.showInputDialog(null, "How many players are here?", "Welcome to World Of Sweets!", JOptionPane.DEFAULT_OPTION, null, numOfPlayers, "2");
			playerObjs = new Player[Integer.parseInt(playersO.toString())];

			if(playerObjs.length  == 3){
				String[] aiPlayers = {"0", "1", "2", "3"};
				Object aiObj = JOptionPane.showInputDialog(null, "How many players are AI Players?", "Welcome to World Of Sweets!", JOptionPane.DEFAULT_OPTION, null,aiPlayers, "0");
				aiO = Integer.parseInt(aiObj.toString());

			}
			else if(playerObjs.length < 3){
				String[] aiPlayers = {"0", "1", "2"};
				Object aiObj = JOptionPane.showInputDialog(null, "How many players are AI Players?", "Welcome to World Of Sweets!", JOptionPane.DEFAULT_OPTION, null,aiPlayers, "0");
				aiO = Integer.parseInt(aiObj.toString());
			}
			else{
				String[] aiPlayers = {"0", "1", "2", "3", "4"};
				Object aiObj = JOptionPane.showInputDialog(null, "How many players are AI Players?", "Welcome to World Of Sweets!", JOptionPane.DEFAULT_OPTION, null,aiPlayers, "0");
				aiO = Integer.parseInt(aiObj.toString());
			}
	}


	/**
	 * This method is a helper method that collects player information from
	 * the users such as number of players, player names, and player tokens
	 *
	 * @param none
	 * @return none
	 */
	private static void nameEntry(){
		try{
			String[] symbolsOfPlayers = {"@", "#", "$", "%"};
			ArrayList<String> al = new ArrayList<String>(Arrays.asList("@", "#", "$", "%"));
			int p = playerObjs.length - aiO;
			for(int i=0; i < (playerObjs.length); i++)
			{
				if(i > (p-1)){
					String tempName = JOptionPane.showInputDialog(null, "What is the AI Name?", "AI " + (i+1), JOptionPane.QUESTION_MESSAGE);
					playersO = JOptionPane.showInputDialog(null, "What Token should the AI have?", "AI " + (i+1), JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "@");
					String str = playersO.toString();
					al.remove(str);
					symbolsOfPlayers=al.toArray(new String[al.size()]);
					playerObjs[i] = new Player(i+1, tempName, str);
					playerObjs[i].setAIPlayer(true);
				}
				else{
					String tempName = JOptionPane.showInputDialog(null, "What is your name?", "Player " + (i+1), JOptionPane.QUESTION_MESSAGE);
					playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player " + (i+1), JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "@");
					String str = playersO.toString();
					al.remove(str);
					symbolsOfPlayers=al.toArray(new String[al.size()]);
					playerObjs[i] = new Player(i+1, tempName, str);
				}

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
		boolean candyCardCheck = false;
		int candyCardNum = 0;
		int space = playerObjs[curPlayer].getCurrentSpace();
		int card = playerObjs[curPlayer].getLastCard();
		int newSpace = sf.findSpace(space, card);
		playerObjs[curPlayer].setCurrentSpace(newSpace);

		if (card > 10 && card < 16){
			candyCardCheck = true;
			switch (card){
				case 11:
					candyCardNum = 0;
					break;
				case 12:
					candyCardNum = 1;
					break;
				case 13:
					candyCardNum = 2;
					break;
				case 14:
					candyCardNum = 3;
					break;
				case 15:
					candyCardNum = 4;
					break;
			}

		}

		if(playerObjs[curPlayer].getCurrentSpace() == 60)
			playerObjs[curPlayer].setGrandmasHouse(true);
		addLabels(candyCardCheck, candyCardNum);
	}

	/**
	 * This method adds the player token label to the button that the
	 * player is supposed to move to
	 *
	 * @param none
	 * @return none
	 */
	private static void addLabels(boolean cardCheck, int cardNum){
		if(curPlayer == 0){
			if(playerObjs[0].getCurrentSpace() >= MAX_SPACES){
				playerObjs[0].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[0].getCurrentSpace()].add(L1);
			}
			else if(playerObjs[0].getCurrentSpace() != -1){
				// if(cardCheck == true){
				// 	candyCards[cardNum].add(L1, BorderLayout.SOUTH);
				// }
				// else{
					buttons[playerObjs[0].getCurrentSpace()].add(L1);
				// }
			}
		}
		else if(curPlayer == 1){
			if(playerObjs[1].getCurrentSpace() >= MAX_SPACES){
				playerObjs[1].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[1].getCurrentSpace()].add(L2);
			}
			else if(playerObjs[1].getCurrentSpace() != -1){
				// if(cardCheck == true){
				// 	candyCards[cardNum].add(L2, BorderLayout.SOUTH);
				// }
				// else{
					buttons[playerObjs[1].getCurrentSpace()].add(L2);
				// }
			}
		}
		else if(curPlayer == 2){
			if(playerObjs[2].getCurrentSpace() >= MAX_SPACES){
				playerObjs[2].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[2].getCurrentSpace()].add(L3);
			}
			else if(playerObjs[2].getCurrentSpace() != -1){
				// if(cardCheck == true){
				// 	candyCards[cardNum].add(L3, BorderLayout.SOUTH);
				// }
				// else{
					buttons[playerObjs[2].getCurrentSpace()].add(L3);
				// }
			}
		}
		else{
			if(playerObjs[3].getCurrentSpace() >= MAX_SPACES){
				playerObjs[3].setCurrentSpace(MAX_SPACES);
				buttons[playerObjs[3].getCurrentSpace()].add(L4);
			}
			else if(playerObjs[3].getCurrentSpace() != -1){
				// if(cardCheck == true){
				// 	candyCards[cardNum].add(L4, BorderLayout.SOUTH);
				// }
				// else{
					buttons[playerObjs[3].getCurrentSpace()].add(L4);
				// }
			}
		}
		gameArea.repaint();
	}

	/**
	 *
	 *
	 */
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

				t3.repaint();
				t2.repaint();
				t1.repaint();
				t0.repaint();
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

	public static File[] finder(){
        File dir = new File(System.getProperty("user.dir"));

        return dir.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".wos"); }
        } );

    }

	public static void load() throws NoSuchAlgorithmException{
		try{
			File dir = new File(System.getProperty("user.dir"));
			File[] files = finder();

			if(files.length == 0){
				JOptionPane.showMessageDialog(null, "No save files found, starting new game!", "File not found", JOptionPane.PLAIN_MESSAGE);
				loaded = false;
				gameDeck = new Deck();
				return;
			}

			String[] filenames = new String[files.length];
			for(int i = 0; i < files.length; i++){
				filenames[i] = files[i].getName();
			}

			try{
				Object f = JOptionPane.showInputDialog(null, "Which file do you want to load?", "Welcome to World Of Sweets!", JOptionPane.DEFAULT_OPTION, null, filenames, filenames[0]);
				String selectedFName = f.toString();


				FileReader fr = new FileReader(selectedFName);
				BufferedReader br = new BufferedReader(fr);
				PrintWriter pw = new PrintWriter(new File("tempFile.wos"));

				String hexHash = br.readLine();
				String curLine;

				//Printing data minus hash to a new temp file so that
				//it can be rehashed to compare the value
				while((curLine = br.readLine()) != null){
					pw.println(curLine);
				}
				fr.close();
				br.close();
				pw.close();

				//Rehashing temp file so that hash values can be compared
				File tempFileToHash = new File("tempFile.wos");
				FileInputStream fis = new FileInputStream(tempFileToHash);
				int byteLength = (int)tempFileToHash.length();

				byte[] fileByteArray = new byte[byteLength];
				byte[] fileHash;
				fis.read(fileByteArray, 0, byteLength);
				fis.close();

				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(fileByteArray);
				fileHash = md.digest();

				StringBuilder sb = new StringBuilder();
				for(byte b : fileHash){
					sb.append(String.format("%02x", b&0xff));
				}

				//Deleting temp file used for hashing
				File rmf0 = new File("tempFile.wos");
				rmf0.delete();

				String newHash = sb.toString();

				//If file has been tampered with
				if(!newHash.equals(hexHash)){
					JOptionPane.showMessageDialog(null, "The file you are trying to load is corrupt or has been tampered with!", "CORRUPT FILE!", JOptionPane.PLAIN_MESSAGE);
					successfulLoad = false;
					startUp();
				}
				else{
					File selectedFile = new File(selectedFName);
					Scanner scan = new Scanner(selectedFile);
					//Consuming hash string from first line
					scan.nextLine();

					int numPlayers = scan.nextInt();
					playerObjs = new Player[numPlayers];
					for(int i = 0; i < numPlayers; i++){
						String n = scan.next();
						if (n.contains("\"")) {
							if (n.equals("\"\"")) {
								n = "";
							} else {
								n = n.split("\"")[1];
							}
						}
						String t = scan.next();
						int s = scan.nextInt();
						int c = scan.nextInt();
						boolean a = scan.nextBoolean();
						int b = scan.nextInt();
						playerObjs[i] = new Player(i, n, t, s, c, a, b);
					}
					int dSize = scan.nextInt();
					Stack<Integer> tempStack = new Stack<Integer>();
					for(int i = 0; i < dSize; i++){
						tempStack.push(scan.nextInt());
					}
					gameDeck = new Deck(1);
					for(int i = 0; i < dSize; i++){
						gameDeck.push(tempStack.pop());
					}
					seconds = scan.nextInt();
					minutes = scan.nextInt();
					hours = scan.nextInt();
					days = scan.nextInt();
					curPlayer = scan.nextInt();
					lastCardDrawn = scan.nextInt();
					gameMode = scan.nextInt();
					successfulLoad = true;
				}
			}
			catch(java.lang.NullPointerException npe){
				System.exit(1);
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(1);
		}
	}

	private static void useBoomerang() {
		boolean canUseBoomerang = playerObjs[curPlayer].useBoomerang();
		if(canUseBoomerang){
				int tempCount = 0;
				String selectedName = new String();
				int boomPlayer = -1;
				if(!playerObjs[curPlayer].isAI()) {
					String [] playerNames = new String[playerObjs.length-1];
					for(int i = 0;i < playerObjs.length; i ++){
						if(!playerObjs[i].getPlayerName().equals(playerObjs[curPlayer].getPlayerName())){
							playerNames[tempCount] = playerObjs[i].getPlayerName();
							tempCount++;
						}
					}
					Object f = JOptionPane.showInputDialog(null, "Who do you want to chuck that boomerang at?", "Git em!", JOptionPane.DEFAULT_OPTION, null, playerNames, playerNames[0]);
					selectedName = f.toString();
					for(int i = 0; i < playerObjs.length; i++) {
						if(playerObjs[i].getPlayerName().equals(selectedName)) boomPlayer = i;
					}
				} else {
					Random r = new Random();
					r.setSeed(System.currentTimeMillis());
					boomPlayer = r.nextInt(playerObjs.length);
					while(boomPlayer == curPlayer) {
						boomPlayer = r.nextInt(playerObjs.length);
					}
				}
				do {
					draw();
				} while(lastCardDrawn == 16);
				int targetSpace = sf.findBoomerangSpace(playerObjs[curPlayer].getLastCard(), playerObjs[boomPlayer].getCurrentSpace());
				playerObjs[boomPlayer].setCurrentSpace(targetSpace);
				int savePlayer = curPlayer;
				curPlayer = boomPlayer;
				addLabels(true, 0);
				curPlayer = savePlayer;
				changeBoomButton();
				curPlayer++;
				if(curPlayer == playerObjs.length) curPlayer = 0;
				JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
				if(playerObjs[curPlayer].isAI()) {
					AIturn();
				}
				} else {
						JOptionPane.showMessageDialog(null, "You're out of boomerangs, dummy!", "No boomerangs", JOptionPane.PLAIN_MESSAGE);
				}

	}

	private static void AIturn() {
		pressEnter();
		if(gameMode == 1) {
			if(playerObjs[curPlayer].getBoomerangs() > 0){
				Random r = new Random();
				r.setSeed(System.currentTimeMillis());
				if(r.nextInt()%3 == 0) {
					useBoomerang();
				} else {
					draw();
					updateTurn();
				}
			}
			else{
				draw();
				updateTurn();
			}
		}
		else{
			draw();
			updateTurn();
		}

	}


	/**
	 *
	 *
	 */
	private static void saveGame() throws Exception{
		try{
			JOptionPane.showMessageDialog(null, "Now saving game!", "Save Game", JOptionPane.PLAIN_MESSAGE);
			Save s = new Save(playerObjs, gameDeck, seconds, minutes, hours, days, curPlayer, lastCardDrawn, gameMode);
			JOptionPane.showMessageDialog(null, "Done, goodbye!", " ", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		catch(Exception exc){
			System.out.println(exc);
			System.exit(1);
		}
	}

	private static void swapPlayers() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		ArrayList<Integer> usedNums = new ArrayList<Integer>(playerObjs.length);
		int playerToChangeWith = rand.nextInt(playerObjs.length);
		int oldSpace = playerObjs[curPlayer].getCurrentSpace();
		while(usedNums.contains(playerToChangeWith) || playerToChangeWith == curPlayer)
		{
			playerToChangeWith = rand.nextInt(playerObjs.length);
		}
		usedNums.add(playerToChangeWith);
		JOptionPane.showMessageDialog(null, "Swapping " + playerObjs[curPlayer].getPlayerName() + " with " + playerObjs[playerToChangeWith].getPlayerName(), " ", JOptionPane.PLAIN_MESSAGE);
		if(playerObjs[curPlayer].isAI()) {
			pressEnter();
		}
		playerObjs[curPlayer].setCurrentSpace(playerObjs[playerToChangeWith].getCurrentSpace());
		playerObjs[playerToChangeWith].setCurrentSpace(oldSpace);
		int saveCurPlayer = curPlayer;
		for(int i = 0; i < 2; i++){
			boolean candyCardCheck=false;
			int candyCardNum=0;
			int card;
			if(i == 1){
				card = playerObjs[curPlayer].getLastCard();
				curPlayer = playerToChangeWith;
			}
			else{
				card = playerObjs[playerToChangeWith].getLastCard();
			}

			if(card != -1){
				if (card > 10 && card < 16){
					candyCardCheck = true;
					switch (card){
						case 11:
							candyCardNum = 0;
							break;
						case 12:
							candyCardNum = 1;
							break;
						case 13:
							candyCardNum = 2;
							break;
						case 14:
							candyCardNum = 3;
							break;
						case 15:
							candyCardNum = 4;
							break;
					}
				}

				addLabels(candyCardCheck, candyCardNum);
			}
			else{
				switch(curPlayer){
					case 0:
						beginZone.add(L1);
						break;
					case 1:
						beginZone.add(L2);
						break;
					case 2:
						beginZone.add(L3);
						break;
					case 3:
						beginZone.add(L4);
						break;
				}

			}
		}
		curPlayer = saveCurPlayer;
		playerObjs[curPlayer].setLastCard(16);
	}

	public static void pressEnter() {
		try {
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
		  robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (InterruptedException iex) { System.out.println(iex); }
		}

	public static void changeBoomButton(){
		int booms = playerObjs[curPlayer].getBoomerangs();
		// System.out.println(curPlayer);
		// System.out.println(booms);
		boomButtons[curPlayer][booms].setIcon(null);
		boomButtons[curPlayer][booms].repaint();
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
