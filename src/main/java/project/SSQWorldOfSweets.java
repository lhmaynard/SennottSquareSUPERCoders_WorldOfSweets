package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SSQWorldOfSweets extends JPanel{

	//private static JLabel deckLabel = new JLabel("Deck Information");
	static String names[];
	static String symbols[];
	//Tokens of Each of the Players
	static JLabel Player1, Player2, Player3, Player4;
	static int players;
	public static void main(String args[]){

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
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
		JPanel deckInfo = new JPanel();
		JPanel playerInfo = new JPanel();
		JPanel gameArea = new JPanel();
		
		//DECK LABEL
		JLabel deckLabel = new JLabel("Deck Information");
		deckLabel.setFont(new Font("Century", Font.BOLD, 30));
		
		//DECK PANEL AREA
		JButton deck = new JButton("Click to draw card");
		
		
		deckInfo.add(deckLabel);
		deckInfo.add(deck);
		
		//Player Panel area
		JLabel playerLabel = new JLabel("Player Information");		
		playerLabel.setFont(new Font("Century", Font.ITALIC, 25));
		playerInfo.setLayout(new BorderLayout());
		playerInfo.add(playerLabel, BorderLayout.NORTH);
		
		//Setting colors of Panels
		deckInfo.setBackground(Color.pink);
		playerInfo.setBackground(Color.pink);
		gameArea.setBackground(Color.white);
		
		//Setting size for areas
		deckInfo.setPreferredSize(new Dimension(200, 200));					
		playerInfo.setPreferredSize(new Dimension(250, 200));
		gameArea.setPreferredSize(new Dimension(750,750));
		
		//Calling addSpace to add spaces to game Area
		addSpace(gameArea);
		//Edits and adds elements to playerInfo Panel									
		editPlayerInfo(playerInfo);
		
		p.add(playerInfo, BorderLayout.EAST);
		p.add(deckInfo, BorderLayout.SOUTH);
		p.add(gameArea, BorderLayout.CENTER);
	}
	//Add Play spaces to the game area
	private static void addSpace(JPanel gameArea){						
		JButton[] buttons = new JButton[50];
		JButton endZone = new JButton("End");
		
		JButton beginZone = new JButton("Begin");
		JLabel L1 = new JLabel("1");
		L1.setHorizontalAlignment(JLabel.CENTER);
		JLabel L2 = new JLabel("2");
		JLabel L3 = new JLabel("3");
		L3.setHorizontalAlignment(JLabel.CENTER);
		JLabel L4 = new JLabel("4");
		beginZone.setLayout(new BorderLayout());
		beginZone.add(L1, BorderLayout.NORTH);
		beginZone.add(L2, BorderLayout.EAST);
		beginZone.add(L3, BorderLayout.SOUTH);
		beginZone.add(L4, BorderLayout.WEST);
		
		gameArea.add(beginZone);
		for(int i = 0; i < 50; i++){
			//Array of JButtons that is the game board spaces			
			buttons[i] = new JButton(Integer.toString(i));
			buttons[i].add(L1, BorderLayout.NORTH);
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
	private static void editPlayerInfo(JPanel playerInfo){
		playerInfo.setLayout(new GridLayout(8, 1));
		if (players == 2){
			Player2 = new JLabel("Player 2: " + names[1]);
			Player1 = new JLabel("Player 1: " + names[0]);
			Player1.setFont(new Font("Century", Font.BOLD, 15));
			Player2.setFont(new Font("Century", Font.BOLD, 15));
			playerInfo.add(Player1);
			playerInfo.add(Player2);
		}
		else if(players == 3){
			Player2 = new JLabel("Player 2: " + names[1]);
			Player1 = new JLabel("Player 1: " + names[0]);
			Player3 = new JLabel("Player 3: " + names[2]);
			Player1.setFont(new Font("Century", Font.BOLD, 15));
			Player2.setFont(new Font("Century", Font.BOLD, 15));
			Player3.setFont(new Font("Century", Font.BOLD, 15));
			playerInfo.add(Player1);
			playerInfo.add(Player2);
			playerInfo.add(Player3);
		}
		else{
			Player2 = new JLabel("Player 2: " + names[1]);
			Player1 = new JLabel("Player 1: " + names[0]);
			Player3 = new JLabel("Player 3: " + names[2]);
			Player4 = new JLabel("Player 4: " + names[3]);
			Player1.setFont(new Font("Century", Font.BOLD, 15));
			Player2.setFont(new Font("Century", Font.BOLD, 15));
			Player3.setFont(new Font("Century", Font.BOLD, 15));
			Player4.setFont(new Font("Century", Font.BOLD, 15));
			playerInfo.add(Player1);
			playerInfo.add(Player2);
			playerInfo.add(Player3);
			playerInfo.add(Player4);
		}
		
	}
	private static void nameEntry(){							//Name Entry WOrk in PRogress
		String[] numOfPlayers = {"2", "3", "4"};
		String[] symbolsOfPlayers = {"@", "#", "$", "%"};
		Object playersO = JOptionPane.showInputDialog(null, "Welcome to World Of Sweets! How many players are here?", "Selection", JOptionPane.DEFAULT_OPTION, null, numOfPlayers, "2");
		players = Integer.parseInt(playersO.toString());
		if (players == 2){
			names = new String[2];
			symbols = new String[2];
			names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 1", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[0] = playersO.toString();
			names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 2", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[1] = playersO.toString();
		}
		else if(players == 3){
			names = new String[3];
			symbols = new String[3];
			names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 1", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[0] = playersO.toString();
			names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 2", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[1] = playersO.toString();
			names[2] = JOptionPane.showInputDialog(null, "What is your name?", "Player 3", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 3", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[2] = playersO.toString();
		}
		else{
			names = new String[4];
			symbols = new String[4];
			names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 1", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[0] = playersO.toString();
			names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 2", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[1] = playersO.toString();
			names[2] = JOptionPane.showInputDialog(null, "What is your name?", "Player 3", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 3", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[2] = playersO.toString();
			names[3] = JOptionPane.showInputDialog(null, "What is your name?", "Player 4", JOptionPane.QUESTION_MESSAGE);
			playersO = JOptionPane.showInputDialog(null, "What Token would you like?", "Player 4", JOptionPane.DEFAULT_OPTION, null, symbolsOfPlayers, "$");
			symbols[3] = playersO.toString();
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
