package AdventureGame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.*;

public class HelpWindow {
	JFrame windowH;
	JLabel labelTitle;
	JTextArea textList;
	JScrollPane scrollList;
	JPanel scrollPanel;
	
	HelpWindow() {
		labelTitle = new JLabel();
		labelTitle.setBounds(120,-85,185,210);
		labelTitle.setBackground(new Color(50,50,50));
		labelTitle.setForeground(new Color(230,210,210));
		labelTitle.setFont(new Font("Helvetica",Font.BOLD,20));
		labelTitle.setText("Help");
		
		windowH = new JFrame();
		windowH.setSize(300,300);
		windowH.getContentPane().setBackground(new Color(50,50,50));
		windowH.setResizable(false);
		windowH.setLayout(null);
		windowH.setLocationRelativeTo(null);
		windowH.setTitle("Help");
		
		textList = new JTextArea(15,5);
		textList.setLayout(new java.awt.FlowLayout());
		textList.setBackground(new Color(150,150,150));
		textList.setForeground(Color.black);
		textList.setEditable(false);
		textList.setFont(new Font("Helvetica",Font.PLAIN,15));
		textList.setLineWrap(true);
		textList.setWrapStyleWord(true);
		
		scrollList = new JScrollPane(textList);
		scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollList.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

		
		scrollPanel = new JPanel(new java.awt.GridLayout(1,1));
		scrollPanel.setBounds(50,50,185,180);
		scrollPanel.setBackground(new Color(50,255,50));
		
		scrollPanel.add(scrollList);
		
		windowH.add(labelTitle);
		windowH.add(scrollPanel);

		windowH.setVisible(true);
		contents();
	}
	
	void contents() {
		textList.setText("Info:\r\n"
				+ "- Floor can reach infinity\r\n\n"
				+ "- There are 7 unique floors and 3 boss floors\r\n\n"
				+ "- Boss are at Floor 10, 20, and 30\r\n\n"
				+ "- Floor types are randomized, and fight rooms are more common on higher floors\r\n\n"
				+ "- Floor monsters get stronger as you go up\r\n\n"
				+ "- Common Items provide +1 defense and +3 damage\r\n\n"
				+ "- Rare Items provide +3 defense and +8 damage\r\n\n"
				+ "- Ultra Rare Items provide +7 defense and +15 damage\r\n\n"
				+ "- Super Super Rare Items only drop from bosses and give +20 defense, +25 damage, + 15 maxHP, and quality increases after every boss\r\n\n"
				+ "- Defense provides damage reduction which blocks a % of damage received per armor, and increases diminishingly after 50%, max 80%"
				);
	}

}
