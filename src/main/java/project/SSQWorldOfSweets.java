package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SSQWorldOfSweets extends JPanel{

	//private static JLabel deckLabel = new JLabel("Deck Information");
	static String names[];
	static String symbols[];
	static int players;
	static int lastCardDrawn = -1;
	static Deck gameDeck;
	public static void main(String args[]){

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
					gameDeck = new Deck();
				}
			});
	}
	private static void createAndShowGUI(){
		JFrame f = new MyFrame("World of Sweets!");
		nameEntry();							//Commented Out of Testing
		addPanel(f.getContentPane());


		f.pack();
		f.setVisible(true);
	}
	private static void addPanel(Container p){
		p.setLayout(new BorderLayout());
		JPanel deckArea = new JPanel();
		JPanel playerInfo = new JPanel();
		JPanel gameArea = new JPanel();

		//DECK PANEL AREA
		drawDeckArea(deckArea);



		//Player Panel area
		JLabel playerLabel = new JLabel("Player Information");
		playerLabel.setFont(new Font("Century", Font.ITALIC, 25));
		playerInfo.setLayout(new BorderLayout());
		playerInfo.add(playerLabel, BorderLayout.NORTH);

		//Setting colors of Panels
		deckArea.setBackground(Color.pink);
		playerInfo.setBackground(Color.pink);
		gameArea.setBackground(Color.white);

		//Setting size for areas
		deckArea.setPreferredSize(new Dimension(200, 200));
		playerInfo.setPreferredSize(new Dimension(250, 200));
		gameArea.setPreferredSize(new Dimension(750,750));

		//Calling addSpace to add spaces to game Area
		addSpace(gameArea);
		//Edits and adds elements to playerInfo Panel
		editPlayerInfo(playerInfo);

		p.add(playerInfo, BorderLayout.EAST);
		p.add(deckArea, BorderLayout.SOUTH);
		p.add(gameArea, BorderLayout.CENTER);
	}


	private static void drawDeckArea(JPanel deckArea){
		deckArea.setLayout( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

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

		JButton drawDeck2 = new JButton("Card Goes Here");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.ipady = 100;
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

	//draw card and display
	private static void draw(){

		if(gameDeck.empty() == false){
			lastCardDrawn = gameDeck.drawCard();
			JOptionPane.showMessageDialog(null, "You drew a "+lastCardDrawn+" card!");
		}
		else{
			JOptionPane.showMessageDialog(null, "You ran out of cards in the deck! Click OK to shuffle!");
			gameDeck.shuffle();
		}

	}

	//Add Play spaces to the game area
	private static void addSpace(JPanel gameArea){
		JButton[] buttons = new JButton[50];
		JButton endZone = new JButton("Grandmother's House");
		endZone.setBackground(Color.magenta);
		endZone.setMinimumSize(new Dimension(100, 300));
		JButton beginZone = new JButton("Start");
		beginZone.setBackground(Color.white);
		beginZone.setLayout(new BorderLayout());
		switch (players){
			case 4:
				JLabel L4 = new JLabel(symbols[3]);
				beginZone.add(L4, BorderLayout.WEST);
			case 3:
				JLabel L3 = new JLabel(symbols[2]);
				beginZone.add(L3, BorderLayout.EAST);
			case 2:
				JLabel L1 = new JLabel(symbols[0]);
				L1.setHorizontalAlignment(JLabel.CENTER);
				JLabel L2 = new JLabel(symbols[1]);
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
				buttons[i].setBackground(Color.orange);
			}

			gameArea.add(buttons[i]);
		}

		gameArea.add(endZone);

	}

  private static void editPlayerInfo(JPanel playerInfo)
  {
		playerInfo.setLayout(new GridLayout(8, 1));
		JLabel[] allLabels;
		allLabels = generatePlayers(players);
		for(int i= 0; i<players; i++) {
			playerInfo.add(allLabels[i]);
		}
	}

	private static JLabel[] generatePlayers(int num)
	{
		JLabel[] playerLabels = new JLabel[num];
		Random r = new Random();
		r.setSeed(System.currentTimeMillis());
		for(int i = 0; i<num; i++) {
			playerLabels[i] = new JLabel("Player " + i + ": " + names[i] + ": " + symbols[i]);
			playerLabels[i].setFont(new Font("Century", Font.BOLD, 15));
      //set color of initial player labels randomly
			playerLabels[i].setForeground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		}
		return playerLabels;
	}

	private static void nameEntry()
  {							//Name Entry Work in PRogress
		String[] numOfPlayers = {"2", "3", "4"};
		String[] symbolsOfPlayers = {"@", "#", "$", "%"};
		ArrayList<String> al = new ArrayList<String>(Arrays.asList("@", "#", "$", "%"));
		Object playersO = JOptionPane.showInputDialog(null, "Welcome to World Of Sweets! How many players are here?", "Selection", JOptionPane.DEFAULT_OPTION, null, numOfPlayers, "2");
		players = Integer.parseInt(playersO.toString());

		names = new String[4];
		symbols = new String[4];
		names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
		playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 1", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
		String str = playersO.toString();
		al.remove(str);
		symbolsOfPlayers=al.toArray(new String[al.size()]);
		symbols[0] = str;
		names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
		playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 2", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
		str = playersO.toString();
		al.remove(str);
		symbolsOfPlayers=al.toArray(new String[al.size()]);
		symbols[1] = playersO.toString();
		if(players>2){
			names[2] = JOptionPane.showInputDialog(null, "What is your name?", "Player 3", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 3", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			str = playersO.toString();
			al.remove(str);
			symbolsOfPlayers=al.toArray(new String[al.size()]);
			symbols[2] = playersO.toString();
			if(players==4){
				names[3] = JOptionPane.showInputDialog(null, "What is your name?", "Player 4", JOptionPane.QUESTION_MESSAGE);
				playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 4", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
				str = playersO.toString();
				al.remove(str);
				symbolsOfPlayers=al.toArray(new String[al.size()]);
				symbols[3] = playersO.toString();
			}
		}
	}
}
class MyFrame extends JFrame {
	public MyFrame(String n){
		setTitle(n);
		setSize(1000,1000);
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
