package contents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIPractice extends JPanel{

	//private static JLabel deckLabel = new JLabel("Deck Information");
	
	
	public static void main(String args[]){

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
	}
	private static void createAndShowGUI(){
		JFrame f = new MyFrame("Candy Land Knockoff");
		
		addPanel(f.getContentPane());
		f.show();
		//f.pack();
		//f.setVisible(true);
	}
	private static void addSpace(JPanel gameArea){
		
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
		
		//createSpaces(gameArea);
		
		deckInfo.setBackground(Color.pink);
		playerInfo.setBackground(Color.red);
		gameArea.setBackground(Color.blue);
		
		deckInfo.setPreferredSize(new Dimension(200, 200));
		playerInfo.setPreferredSize(new Dimension(250, 200));
		
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
		
		contentPane.add(new GUIPractice());
		
	}
}