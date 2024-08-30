package AdventureGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.util.Random;

public class RewardsWindow implements ActionListener{
	
	static Random rand = new Random();
	Player player = new Player();
	
	JFrame windowR;
	JPanel panelList,panelTitle;
	JLabel[] labelItems;
	JLabel labelTitle,label1,label2,label3,label4,label5,label6,label7,label8;
	JButton btnClose;
	
	RewardsWindow() {
		btnClose = new JButton();
		btnClose.setBounds(63,210,80,25);
		btnClose.setText("OK");
		btnClose.setFocusable(false);
		btnClose.addActionListener(this);
		btnClose.setBackground(new Color(50,50,50));
		btnClose.setForeground(new Color(230,210,210));
		btnClose.setPreferredSize(new Dimension(110,25));
		
		labelTitle = new JLabel();
		labelTitle.setBackground(new Color(50,50,50)); 
		labelTitle.setForeground(new Color(235,245,70));
		labelTitle.setFont(new Font("Helvetica",Font.BOLD,20));
		labelTitle.setText("Rewards");
		
		label1 = new JLabel();
		label1.setBackground(new Color(50,50,50));
		label1.setForeground(new Color(255,115,135));
		label1.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label2 = new JLabel();
		label2.setBackground(new Color(50,50,50));
		label2.setForeground(new Color(255,115,135));
		label2.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label3 = new JLabel();
		label3.setBackground(new Color(50,50,50));
		label3.setForeground(new Color(255,115,135));
		label3.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label4 = new JLabel();
		label4.setBackground(new Color(50,50,50));
		label4.setForeground(new Color(255,115,135));
		label4.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label5 = new JLabel();
		label5.setBackground(new Color(50,50,50));
		label5.setForeground(new Color(255,115,135));
		label5.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label6 = new JLabel();
		label6.setBackground(new Color(50,50,50));
		label6.setForeground(new Color(255,115,135));
		label6.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label7 = new JLabel();
		label7.setBackground(new Color(50,50,50));
		label7.setForeground(new Color(255,115,135));
		label7.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		label8 = new JLabel();
		label8.setBackground(new Color(50,50,50));
		label8.setForeground(new Color(255,115,135));
		label8.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		windowR = new JFrame();
		windowR.setSize(210,300);
		windowR.getContentPane().setBackground(new Color(50,50,50));
		windowR.setResizable(false);
		windowR.setLayout(null);
		
		panelTitle = new JPanel();
		panelTitle.setBounds(50,5,98,50);
		panelTitle.setBackground(new Color(50,50,50));
		
		panelList = new JPanel(new java.awt.GridLayout(8,1));
		panelList.setBounds(5,40,185,210);
		panelList.setBackground(new Color(50,50,50));
		panelList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelList.add(label1);
		panelList.add(label2);
		panelList.add(label3);
		panelList.add(label4);
		panelList.add(label5);
		panelList.add(label6);
		panelList.add(label7);
		panelList.add(label8);
		
		panelTitle.add(labelTitle);
		
		windowR.add(btnClose);
		windowR.add(panelList);
		windowR.add(panelTitle);
		
		windowR.setVisible(true);
		
		labelItems = new JLabel[]{ label1, label2, label3, label4, label5, label6, label7, label8};
		
		Rewards();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnClose) {
			windowR.dispose();
		}
		
	}
	
	@SuppressWarnings("static-access")
	void Rewards() {
		player.rewardRates--;
		int checker = 0;
		int rewardDropRates = rand.nextInt(100);
		int rewardAMT = 0;
		int i = 0;
		int r = 0;
		int p = 0;
		
		if (rewardDropRates < 75) {
			rewardAMT = 2;
		}
		else {
			rewardAMT = 1;
		}
		
		if (player.bossState == true) {
			labelItems[i].setForeground(new Color(252, 219, 3));
			labelItems[i].setText("◆ " + player.ssrItems[player.bossCount] + "!!!");
			player.inventory.add(player.ssrItems[player.bossCount]);
			player.ssrDrop();
			player.bossCount++;
			i++;
			labelItems[i].setForeground(new Color(252, 219, 3));
			labelItems[i].setText("◆ 3x Healing Potions!!");
			player.healPot += 3;
			i++;
			rewardAMT += 2;
			r = 2;
			checker++;
			player.bossState = false;
		}
		
		if (player.treasureDrop == true) {
			for(p=0; p<3; p++) {
				int reward = rand.nextInt(10);
				if (reward <= 1) {
					int n = rand.nextInt(player.urareItems.length);
					labelItems[i].setForeground(new Color(255, 0, 191));
					labelItems[i].setText("◆ "+ player.urareItems [n]+ "!!!");
					player.inventory.add(player.urareItems [n]);
					player.urareDrop();
					i++;
				}
				else if (reward <= 10) {
					int potDropRates = rand.nextInt(100);
					if (potDropRates <25) {
						labelItems[i].setForeground(new Color(255, 0, 191));
						labelItems[i].setText("◆ Healing Potion!!");
						player.healPot++;
						i++;
					}
					else {
						int n = rand.nextInt(player.rareItems.length);
						labelItems[i].setForeground(new Color(255, 0, 191));
						labelItems[i].setText("◆ "+ player.rareItems [n]+ "!!");
						player.inventory.add(player.rareItems [n]);
						player.rareDrop();
						i++;
					}
				}
			}
			rewardAMT += 3;
			r = 3;
			player.treasureDrop = false;
			checker++;
		}
		
		if (player.rewDrop == true) {
			int n = rand.nextInt(player.rareItems.length);
			labelItems[i].setForeground(new Color(255, 0, 191));
			labelItems[i].setText("◆ "+ player.rareItems [n]+ "!!");
			player.inventory.add(player.rareItems [n]);
			player.rareDrop();
			i++;
			labelItems[i].setForeground(new Color(255, 0, 191));
			labelItems[i].setText("◆ Healing Potion!!");
			player.healPot++;
			i++;
			rewardAMT += 2;
			player.rewDrop = false;
			checker++;
			r = 2;
		}
		
		
		for(i=r; i<rewardAMT; i++) {
			int reward = rand.nextInt(player.rewardRates);
			if (reward < 5) {
				int n = rand.nextInt(player.urareItems.length);
				labelItems[i].setForeground(new Color(255, 35, 40));
				labelItems[i].setText("◆ "+ player.urareItems [n]+ "!!!");
				player.inventory.add(player.urareItems [n]);
				player.urareDrop();
				checker++;
			}
			else if (reward < 17) {
				int n = rand.nextInt(player.rareItems.length);
				labelItems[i].setForeground(new Color(40, 255, 30));
				labelItems[i].setText("◆ "+ player.rareItems [n]+ "!!");
				player.inventory.add(player.rareItems [n]);
				player.rareDrop();
				checker++;
			}
			else if (reward < 85) {
				int potDropRates = rand.nextInt(100);
				if (potDropRates <25) {
					labelItems[i].setForeground(new Color(40, 255, 30));
					labelItems[i].setText("◆ Healing Potion!!");
					player.healPot++;
					checker++;
				}
				else {
					labelItems[i].setForeground(new Color(200, 210, 160));
					int n = rand.nextInt(player.commonItems.length);
					labelItems[i].setText("◆ "+ player.commonItems [n]+ "!");
					player.commonDrop();
					player.inventory.add(player.commonItems [n]);
					checker++;
				}
			}
			
		}
		if (checker == 0) {
			label1.setForeground(new Color(200, 210, 160));
			label1.setText("◆ No drops... unlucky :(");
		}
		checker = 0;
	}

}
