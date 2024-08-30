package AdventureGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launch implements ActionListener {
	
	JButton buttonStart;
	JButton buttonAbout;
	JButton buttonExit;
	JButton btnBack;
	JLabel labelStart1;
	JLabel labelStart2;
	JFrame frame;
	JPanel panelCenter;
	JPanel panelStart;
	
	JLabel labelAbout1,labelAbout2;
	JPanel panelAbout,panelBack;
	
	Launch() {
		btnBack = new JButton();
		btnBack.setBounds(200,100,300,100);
		btnBack.setText("Back");
		btnBack.setFocusable(false);
		btnBack.setBackground(new Color(50,50,50));
		btnBack.setForeground(new Color(230,210,210));
		btnBack.addActionListener(this);
		btnBack.setPreferredSize(new Dimension(110,25));
		
		buttonStart = new JButton();
		buttonStart.setBounds(200,100,300,100);
		buttonStart.setText("Start");
		buttonStart.setFocusable(false);
		buttonStart.setBackground(new Color(50,50,50));
		buttonStart.setForeground(new Color(230,210,210));
		buttonStart.addActionListener(this);
		buttonStart.setPreferredSize(new Dimension(110,25));
		
		buttonAbout = new JButton();
		buttonAbout.setBounds(200,100,100,50);
		buttonAbout.setText("About");
		buttonAbout.setFocusable(false);
		buttonAbout.setBackground(new Color(50,50,50));
		buttonAbout.setForeground(new Color(230,210,210));
		buttonAbout.addActionListener(this);
		buttonAbout.setPreferredSize(new Dimension(110,25));
		
		buttonExit = new JButton();
		buttonExit.setBounds(200,100,100,50);
		buttonExit.setText("Exit");
		buttonExit.setFocusable(false);
		buttonExit.setBackground(new Color(50,50,50));
		buttonExit.setForeground(new Color(230,210,210));
		buttonExit.addActionListener(this);
		buttonExit.setPreferredSize(new Dimension(110,25));
		
		labelAbout1 = new JLabel();
		labelAbout1.setBackground(new Color(50,50,50));
		labelAbout1.setForeground(Color.white);
		labelAbout1.setFont(new Font("Helvetica",Font.PLAIN,30));
		labelAbout1.setHorizontalAlignment(JLabel.CENTER);
		labelAbout1.setText("                    About                    ");
		
		labelAbout2 = new JLabel();
		labelAbout2.setBounds(1000,1000,1000,1000);
		labelAbout2.setBackground(new Color(50,50,50));
		labelAbout2.setForeground(Color.white);
		labelAbout2.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		labelStart1 = new JLabel();
		labelStart1.setBounds(100,100,1000,1000);
		labelStart1.setBackground(new Color(50,50,50));
		labelStart1.setForeground(new Color(255,25,50));
		labelStart1.setFont(new Font("Helvetica",Font.PLAIN,50));
		labelStart1.setText("Infinity Tower");
		
		labelStart2 = new JLabel();
		labelStart2.setBounds(0,100,1000,50);
		labelStart2.setBackground(new Color(50,50,50));
		labelStart2.setForeground(Color.white);
		labelStart2.setFont(new Font("Helvetica",Font.ITALIC,15));
		labelStart2.setText("ADVENTURE GAME"); 
		
		panelCenter = new JPanel(new java.awt.FlowLayout());
		panelCenter.setBounds(0,50,395,130);
		panelCenter.setBackground(new Color(50,50,50));
		
		panelBack = new JPanel();
		panelBack.setBounds(140,310,110,30);
		panelBack.setBackground(new Color(50,50,50));
		
		panelAbout = new JPanel(new java.awt.GridLayout(1,1));
		panelAbout.setBounds(50,150,300,150);
		panelAbout.setBackground(new Color(50,50,50));
		panelAbout.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelStart = new JPanel(new java.awt.FlowLayout());
		panelStart.setBounds(135,180,120,200);
		panelStart.setBackground(new Color(50,50,50));
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(410,400);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Infinity Tower Adventure Game");
		
		panelAbout.add(labelAbout2);
		panelBack.add(btnBack);
		
		panelCenter.add(labelStart1);
		panelCenter.add(labelStart2);
		panelCenter.add(labelAbout1);
		panelStart.add(buttonStart);
		panelStart.add(buttonAbout);
		panelStart.add(buttonExit);
		

		frame.add(panelBack);
		frame.add(panelAbout);
		frame.add(panelCenter);
		frame.add(panelStart);
		
		labelAbout1.setVisible(false);
		panelAbout.setVisible(false);
		panelBack.setVisible(false);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonStart) {
			frame.dispose();
			Floor floor = new Floor();
		}
		if(e.getSource()==buttonAbout) {
			buttonAbout();
			HelpWindow windowH = new HelpWindow();
			panelCenter.setBounds(0,10,395,130);
			labelAbout1.setVisible(true);
			panelAbout.setVisible(true);
			panelStart.setVisible(false);
			panelBack.setVisible(true);
		}
		if(e.getSource()==buttonExit) {
			System.exit(0);
		}
		if(e.getSource()==btnBack) {
			panelCenter.setBounds(0,50,395,130);
			labelAbout1.setVisible(false);
			panelAbout.setVisible(false);
			panelStart.setVisible(true);
			panelBack.setVisible(false);
		}
	}
	
	void buttonAbout() {
		labelAbout2.setText("<html>&emsp;&emsp;&emsp;Infinity Tower is an adventure game wherein you explore the tower to reach the top with floor that are unique and become stronger as you destroy enemies and obtain their loot! Current features include: fully randomized unique floors, boss floors, stats mechanics, items, and more!</html>");
	}

}
