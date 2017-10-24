package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SSQWorldOfSweets extends JPanel{

	//private static JLabel deckLabel = new JLabel("Deck Information");
	static String names[];
	static String symbols[];
	public static void main(String args[]){

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
	}
	private static void createAndShowGUI(){
		JFrame f = new MyFrame("Candy Land Knockoff");
		//nameEntry();							//Commented Out of Testing		
		addPanel(f.getContentPane());

		f.pack();
		f.setVisible(true);
	}
	private static void addSpace(JPanel gameArea){
		JButton[] buttons = new JButton[25];
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
		for(int i = 0; i < 25; i++){			//Array of JButtons that is the game board spaces
			buttons[i] = new JButton(Integer.toString(i));
		}
		gameArea.setLayout(new GridLayout(5, 5, 10, 10));
		for(int i = 0; i < 25; i++){
			if(i == 0 || i == 5 || i == 10 || i == 15 || i == 20){				//Hard Coding colors of Spaces
				buttons[i].setBackground(Color.red);
			}
			if(i == 1 || i == 6 || i == 11 || i == 16 || i == 21){
				buttons[i].setBackground(Color.yellow);
			}
			if(i == 2 || i == 7 || i == 12 || i == 17 || i == 22){
				buttons[i].setBackground(Color.blue);
			}
			if(i == 3 || i == 8 || i == 13 || i == 18 || i == 23){
				buttons[i].setBackground(Color.green);
			}
			if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
				buttons[i].setBackground(Color.orange);
			}
			
			gameArea.add(buttons[i]);
		}	
	
		gameArea.add(endZone);
		
	}
	private static void nameEntry(){							//Name Entry WOrk in PRogress
		String[] numOfPlayers = {"2", "3", "4"};
		String[] symbolsOfPlayers = {"@", "#", "$", "%"};
		Object playersO = JOptionPane.showInputDialog(null, "Welcome to World Of Sweets! How many players are here?", "Selection", JOptionPane.DEFAULT_OPTION, null, numOfPlayers, "2");
		int players = Integer.parseInt(playersO.toString());
		//String name = JOptionPane.showInputDialog(null, "What is your name?");
		if (players == 2){
			names = new String[2];
			names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
			names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
		}
		else if(players == 3){
			names = new String[3];
			names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
			names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
			names[2] = JOptionPane.showInputDialog(null, "What is your name?", "Player 3", JOptionPane.QUESTION_MESSAGE);
		}
		else{
			names = new String[4];
			names[0] = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.QUESTION_MESSAGE);
			names[1] = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.QUESTION_MESSAGE);
			names[2] = JOptionPane.showInputDialog(null, "What is your name?", "Player 3", JOptionPane.QUESTION_MESSAGE);
			names[3] = JOptionPane.showInputDialog(null, "What is your name?", "Player 4", JOptionPane.QUESTION_MESSAGE);
		}
	}
	private static void addPanel(Container p){
		p.setLayout(new BorderLayout());
		JPanel deckInfo = new JPanel();
		JPanel playerInfo = new JPanel();
		JPanel gameArea = new JPanel();
		
		JLabel deckLabel = new JLabel("Deck Information");			//DECK LABEL
		deckLabel.setFont(new Font("Century", Font.BOLD, 30));
		
		JButton deck = new JButton("Cards Shown Here");			//DECK PANEL AREA
		deck.setPreferredSize(new Dimension(200, 200));
		deckInfo.setLayout(new BorderLayout());
		deckInfo.add(deckLabel, BorderLayout.NORTH);
		deckInfo.add(deck, BorderLayout.EAST);
		
		JLabel playerLabel = new JLabel("Player Information");		//Player Panel area
		playerLabel.setFont(new Font("Century", Font.ITALIC, 25));
		playerInfo.setLayout(new BorderLayout());
		playerInfo.add(playerLabel, BorderLayout.NORTH);
		
		addSpace(gameArea);
		
		deckInfo.setBackground(Color.pink);									//Setting colors of Panels
		playerInfo.setBackground(Color.pink);
		gameArea.setBackground(Color.white);
		
		deckInfo.setPreferredSize(new Dimension(200, 200));					//Setting size for areas
		playerInfo.setPreferredSize(new Dimension(250, 200));
		gameArea.setPreferredSize(new Dimension(750,750));
		
		p.add(playerInfo, BorderLayout.EAST);
		p.add(deckInfo, BorderLayout.SOUTH);
		p.add(gameArea, BorderLayout.CENTER);
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
