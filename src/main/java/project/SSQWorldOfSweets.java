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

public class SSQWorldOfSweets extends JPanel{

	//Global variables
	static int players;
	static int lastCardDrawn = -1;
	static Deck gameDeck;
	static int curPlayer=0;
	static JButton drawDeck2;
  static Player[] playerObjs;

	/**
	 *
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
	 *
	 */
	private static void createAndShowGUI(){
		JFrame f = new MyFrame("World of Sweets!");
		nameEntry();
		addPanel(f.getContentPane());

		f.pack();
		f.setVisible(true);
		JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 *
	 */
	private static void addPanel(Container p){
		p.setLayout(new BorderLayout());
		JPanel deckArea = new JPanel();
		JPanel playerArea = new JPanel();
		JPanel gameArea = new JPanel();

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
	 *
	 */
	private static void drawDeckArea(JPanel deckArea){
		deckArea.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Setting background color of deckArea panel
		deckArea.setBackground(Color.pink);

		//Settinf size of deckArea panel
		deckArea.setPreferredSize(new Dimension(700, 200));

		JLabel deckLabel = new JLabel("Deck Information", SwingConstants.CENTER);
		deckLabel.setFont(new Font("Century", Font.BOLD, 30));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 0;
		deckArea.add(deckLabel, c);

		JLabel blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =0;
		c.gridy = 1;
		deckArea.add(blankLabel, c);


		JButton drawDeck = new JButton();
		try {
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./CardBack.png"));
			drawDeck.setIcon(img);
		} catch (Exception e) {
			System.out.println(e);
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		//c.ipady = 50;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		deckArea.add(drawDeck, c);

		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.25;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =2;
		c.gridy = 1;
		deckArea.add(blankLabel, c);
		drawDeck2 = new JButton();
		ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./NoCard.png"));
		drawDeck2.setIcon(img);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		//c.ipady = 100;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		deckArea.add(drawDeck2, c);

		blankLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx =4;
		c.gridy = 1;
		deckArea.add(blankLabel, c);


		JButton drawButton = new JButton("Click to draw card");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		deckArea.add(drawButton, c);
		drawButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        draw();
				//move()
				updateTurn();
      }
    });

		JLabel lastLabel = new JLabel("Last Card Drawn", SwingConstants.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 2;
		deckArea.add(lastLabel, c);
	}

	/**Add Play spaces to the game area
	 *
	 */
	private static void drawGameArea(JPanel gameArea){
		//Setting background color of gameArea panel
		gameArea.setBackground(Color.white);

		//Setting size of gameArea panel
		gameArea.setPreferredSize(new Dimension(800,800));

		JButton[] buttons = new JButton[50];
		JButton endZone = new JButton("Grandmother's House");
		endZone.setBackground(Color.magenta);
		endZone.setMinimumSize(new Dimension(100, 300));
		endZone.setOpaque(true);
		endZone.setBorderPainted(false);

		JButton beginZone = new JButton("Start");
		beginZone.setBackground(Color.white);
		beginZone.setLayout(new BorderLayout());
		beginZone.setOpaque(true);
		beginZone.setBorderPainted(false);
		switch (playerObjs.length){
			case 4:
				JLabel L4 = new JLabel(playerObjs[3].getToken());
				beginZone.add(L4, BorderLayout.WEST);
			case 3:
				JLabel L3 = new JLabel(playerObjs[2].getToken());
				beginZone.add(L3, BorderLayout.EAST);
			case 2:
				JLabel L1 = new JLabel(playerObjs[0].getToken());
				L1.setHorizontalAlignment(JLabel.CENTER);
				JLabel L2 = new JLabel(playerObjs[1].getToken());
				L2.setHorizontalAlignment(JLabel.CENTER);
				beginZone.add(L1, BorderLayout.NORTH);
				beginZone.add(L2, BorderLayout.SOUTH);
		}
		gameArea.add(beginZone);

		for(int i = 0; i < 50; i++){
			//Array of JButtons that is the game board spaces
			buttons[i] = new JButton();
		}
		gameArea.setLayout(new GridLayout(7, 7, 10, 10));
		for(int i = 0; i < 50; i++){
			//Hard Coding colors of Spaces
			if((i % 5) == 0 ){
				buttons[i].setBackground(Color.red);
			}
			if((i % 5) == 1){
				buttons[i].setBackground(Color.yellow);
			}
			if((i % 5) == 2){
				buttons[i].setBackground(Color.blue);
			}
			if((i % 5) == 3){
				buttons[i].setBackground(Color.green);
			}
			if((i % 5) == 4){
				buttons[i].setBackground(new Color(255, 201, 14));
			}

			buttons[i].setOpaque(true);
			buttons[i].setBorderPainted(false);

			gameArea.add(buttons[i]);
		}

		gameArea.add(endZone);

	}

	/**
	 *
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
		playerArea.setPreferredSize(new Dimension(300, 800));

		playerArea.setLayout(new GridLayout(8, 1));
		JLabel[] allLabels;
		allLabels = generatePlayers(playerObjs.length);
		for(int i= 0; i<playerObjs.length; i++) {
			playerArea.add(allLabels[i]);
		}
	}

	/**draw card and display
	 *
	 */
	private static void draw(){

		if(gameDeck.empty() == false){
			lastCardDrawn = gameDeck.drawCard();
      playerObjs[curPlayer].setLastCard(lastCardDrawn);
			try {
				if(lastCardDrawn == -1){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./NoCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 0){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./RedCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 1){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./YellowCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 2){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./BlueCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 3){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./GreenCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 4){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./OrangeCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 5){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleRedCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 6){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleYellowCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 7){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleBlueCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 8){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleGreenCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 9){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./DoubleOrangeCard.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 10){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./SkipATurn.png"));
					drawDeck2.setIcon(img);
				}
				else if(lastCardDrawn == 11){
					ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./MiddleCard.png"));
					drawDeck2.setIcon(img);
				}


			} catch (Exception e) {
				System.out.println(e);
			}
			drawDeck2.repaint();

		}
		else{
			lastCardDrawn = -1;
			ImageIcon img = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("./NoCard.png"));
			drawDeck2.setIcon(img);

			JOptionPane.showMessageDialog(null, "You ran out of cards in the deck! Click OK to shuffle!");
			gameDeck.shuffle();
			drawDeck2.repaint();
		}
	}

  /**
  *
  */
  private static void updateTurn()
  {
    curPlayer++;
    if(curPlayer == playerObjs.length) curPlayer = 0;
    JOptionPane.showMessageDialog(null, "It is "+playerObjs[curPlayer].getPlayerName()+"'s turn!", "Whose turn is it?", JOptionPane.PLAIN_MESSAGE);
  }

	/**
	 *
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
	 *
	 */
	private static void nameEntry()
	{
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
}

/**
 *
 */
class MyFrame extends JFrame {
	public MyFrame(String n){
		setTitle(n);
		setSize(1100,1000);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();

		contentPane.add(new SSQWorldOfSweets());
	}
}
