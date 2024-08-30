package AdventureGame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InventoryWindow {
	Player player = new Player();
	
	JFrame windowI;
	JLabel labelTitle;
	JTextArea textList;
	JScrollPane scrollList;
	JPanel scrollPanel;
	
	InventoryWindow() {
		labelTitle = new JLabel();
		labelTitle.setBounds(100,-85,185,210);
		labelTitle.setBackground(new Color(50,50,50));
		labelTitle.setForeground(new Color(235,245,70));
		labelTitle.setFont(new Font("Helvetica",Font.BOLD,20));
		labelTitle.setText("Inventory");
		
		windowI = new JFrame();
		windowI.setSize(300,300);
		windowI.getContentPane().setBackground(new Color(50,50,50));
		windowI.setResizable(false);
		windowI.setLayout(null);
		windowI.setLocationRelativeTo(null);
		windowI.setTitle("Inventory");
		
		textList = new JTextArea(15,5);
		textList.setBackground(new Color(150,150,150));
		textList.setForeground(Color.black);
		textList.setEditable(false);
		textList.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		scrollList = new JScrollPane(textList);
		scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		scrollPanel = new JPanel(new java.awt.GridLayout(1,1));
		scrollPanel.setBounds(50,50,185,180);
		scrollPanel.setBackground(new Color(50,255,50));
		
		scrollPanel.add(scrollList);
		
		windowI.add(labelTitle);
		windowI.add(scrollPanel);

		windowI.setVisible(true);
		addItems();
	}
	
	void addItems() {
		for (int i = 0; i < player.inventory.size(); i++) {
		      System.out.println(player.inventory.get(i));
		      textList.append("◆" + player.inventory.get(i) + "\n");
		    }
	}

}
