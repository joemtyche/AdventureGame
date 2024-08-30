package AdventureGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;

@SuppressWarnings("static-access")
public class Floor implements ActionListener {
	Enemy enemy = new Enemy();
	Player player = new Player();
	
	static final DecimalFormat df = new DecimalFormat("0.00");
	static final DecimalFormat nf = new DecimalFormat("0");
	static Random rand = new Random();
	
	String[] questions = {
			"1 + 1",
			"9 * 9",
			"275 + 90",
			"208 * 5",
			"31 + 53",
			"70 / 5",
			"1 * 543",
			"90 - 76",
			"67 - 64",
			"78 - 69"
	};
	String[] answers = {
			"2",
			"81",
			"365",
			"1040",
			"84",
			"14",
			"543",
			"14",
			"3",
			"9"
	};
	String currentAnswer;
	
	String[] traps = {
			"†   You fell on a falling platform!   †",
			"†   You got hit by a poisoned arrow!   †",
			"†      You tripped on a rock!      †",
			"†   You got hit by a death trap!   †",
			"†   You got hit by a spike trap!   †",
			"†   You got attacked by an adorable cat!   †",
			"†      You escaped a lava trap!     †",
			"†   You got hit by a barrage of arrows!   †"
	};
	
	int enemyAssigned;
	String enemyName;
	int enemyMHP;
	int enemyHP;
	int dmgVisual;
	
	boolean bossComplete = false;
	String bossName;
	int bossMHP = 0;
	int bossCounter = 0;
	int bossDMG = 0;
	int bossHP = 0;
	int maxRoom = 0;
	
	boolean evasionOn = false;
	
	
	JFrame window;
	
	JPanel panelDMain,panelCMain;
	
	JPanel panelQuestion;
	JPanel panelAnswer;
	JLabel labelQuestion;
	JTextField textAnswer;
	JButton btnAnswer;
	
	JPanel panelMain,panelLeft,panelRight,panelTitle,panelCenter;
	
	JLabel labelMana,labelRoomNum,labelTitleSmall,labelHP,labelDMG,labelARMR,labelPots,labelEnemyHP,labelEnemyDMG;
	JLabel labelCenter2,labelCenter;
	
	JButton btnHelp,explore,inventory,heal,leave,btnAttack,btnSpells,btnRewards,btnContinue,btnHealRoom,btnPortalRoom,btnGameOver,btnExit;
	
	JButton btnEvade,btnBackMagic;//,btnShield;
	
	JButton btnAttackBoss;
	
	JPanel panelImgLeft,panelImgRight;
	JLabel labelImgMC,labelImgEncounter;
	
	Floor() {
		// init
		player.inventory.add("Worn Armor");
		player.inventory.add("Flimsy Sword");
		// Images
		labelImgMC = new JLabel();
		labelImgMC.setIcon(player.mcAnimArray[0]);
		labelImgEncounter = new JLabel();
		
		panelImgLeft = new JPanel(new java.awt.GridLayout(1,1));
		panelImgLeft.setBounds(5,69,110,110);
		panelImgLeft.setBackground(new Color(50,50,50));
		panelImgLeft.setBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED));
		
		panelImgRight = new JPanel(new java.awt.FlowLayout());
		panelImgRight.setBounds(119,71,270,107);
		panelImgRight.setBackground(new Color(50,50,50));

		panelImgLeft.add(labelImgMC);
		panelImgRight.add(labelImgEncounter);

		panelImgRight.setVisible(true);
		//
		labelMana = new JLabel();
		labelMana.setBackground(new Color(50,50,50));
		labelMana.setForeground(new Color(232, 215, 213));
		labelMana.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelCenter = new JLabel();
		labelCenter.setBackground(new Color(50,50,50));
		labelCenter.setForeground(new Color(232, 215, 213));
		labelCenter.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		labelCenter2 = new JLabel();
		labelCenter2.setBackground(new Color(50,50,50));
		labelCenter2.setForeground(new Color(232, 215, 213));
		labelCenter2.setFont(new Font("Helvetica",Font.PLAIN,15));
		
		dmgVisual = (int) (player.basedmg + player.itemDMG + 5);
		labelEnemyDMG = new JLabel();
		labelEnemyDMG.setBackground(new Color(50,50,50));
		labelEnemyDMG.setForeground(new Color(255,115,135));
		labelEnemyDMG.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelEnemyHP = new JLabel();
		labelEnemyHP.setBackground(new Color(50,50,50));
		labelEnemyHP.setForeground(new Color(255,115,135));
		labelEnemyHP.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelHP = new JLabel();
		labelHP.setBackground(new Color(50,50,50));
		labelHP.setForeground(new Color(232, 215, 213));
		labelHP.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelDMG = new JLabel();
		labelDMG.setBackground(new Color(50,50,50));
		labelDMG.setForeground(new Color(232, 215, 213));
		labelDMG.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelARMR = new JLabel();
		labelARMR.setBackground(new Color(50,50,50));
		labelARMR.setForeground(new Color(232, 215, 213));
		labelARMR.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelPots = new JLabel();
		labelPots.setBackground(new Color(0,0,0,0));
		labelPots.setForeground(new Color(232, 215, 213));
		labelPots.setFont(new Font("Helvetica",Font.PLAIN,10));
		
		labelTitleSmall = new JLabel();
		labelTitleSmall.setBackground(new Color(50,50,50));
		labelTitleSmall.setForeground(new Color(66, 87, 245));
		labelTitleSmall.setFont(new Font("Helvetica",Font.PLAIN,15));
		labelTitleSmall.setHorizontalAlignment(JLabel.CENTER);
		labelTitleSmall.setText("Safe Zone");
		
		labelRoomNum = new JLabel();
		labelRoomNum.setBackground(new Color(50,50,50));
		labelRoomNum.setForeground(new Color(255,25,50));
		labelRoomNum.setFont(new Font("Helvetica",Font.PLAIN,30));
		labelRoomNum.setHorizontalAlignment(JLabel.CENTER);
		labelRoomNum.setText("Tower Entrance");
		//
		//
		btnHelp = new JButton();
		btnHelp.setBounds(357,73,30,30);
		btnHelp.setText("?");
		btnHelp.setFocusable(false);
		btnHelp.addActionListener(this);
		btnHelp.setBackground(new Color(50,50,50));
		btnHelp.setForeground(new Color(230,210,210));
		
		btnExit = new JButton();
		btnExit.setBounds(200,100,300,100);
		btnExit.setText("Exit");
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);
		btnExit.setBackground(new Color(50,50,50));
		btnExit.setForeground(new Color(230,210,210));
		btnExit.setPreferredSize(new Dimension(110,25));
		
		btnGameOver = new JButton();
		btnGameOver.setBounds(200,100,300,100);
		btnGameOver.setText("Continue");
		btnGameOver.setFocusable(false);
		btnGameOver.addActionListener(this);
		btnGameOver.setBackground(new Color(50,50,50));
		btnGameOver.setForeground(new Color(230,210,210));
		btnGameOver.setPreferredSize(new Dimension(110,25));
		
		explore = new JButton();
		explore.setBounds(200,100,300,100);
		explore.setText("Explore");
		explore.setFocusable(false);
		explore.addActionListener(this);
		explore.setBackground(new Color(50,50,50));
		explore.setForeground(new Color(230,210,210));
		explore.setPreferredSize(new Dimension(110,25));
		
		inventory = new JButton();
		inventory.setBounds(200,100,300,100);
		inventory.setText("Inventory");
		inventory.setFocusable(false);
		inventory.addActionListener(this);
		inventory.setBackground(new Color(50,50,50));
		inventory.setForeground(new Color(230,210,210));
		inventory.setPreferredSize(new Dimension(110,25));
		/*
		btnShield = new JButton();
		btnShield.setBounds(200,100,300,100);
		btnShield.setText("Magic Shield");
		btnShield.setFocusable(false);
		btnShield.addActionListener(this);
		btnShield.setBackground(new Color(50,50,50));
		btnShield.setForeground(new Color(230,210,210));
		btnShield.setPreferredSize(new Dimension(110,25));
		*/
		btnEvade = new JButton();
		btnEvade.setBounds(200,100,300,100);
		btnEvade.setText("Evasion");
		btnEvade.setFocusable(false);
		btnEvade.addActionListener(this);
		btnEvade.setBackground(new Color(50,50,50));
		btnEvade.setForeground(new Color(230,210,210));
		btnEvade.setPreferredSize(new Dimension(110,25));
		
		heal = new JButton();
		heal.setBounds(200,100,300,100);
		heal.setText("Drink Potion");
		heal.setFocusable(false);
		heal.addActionListener(this);
		heal.setBackground(new Color(50,50,50));
		heal.setForeground(new Color(230,210,210));
		heal.setPreferredSize(new Dimension(110,25));
		
		leave = new JButton();
		leave.setBounds(200,100,300,100);
		leave.setText("Go Home");
		leave.setFocusable(false);
		leave.addActionListener(this);
		leave.setBackground(new Color(50,50,50));
		leave.setForeground(new Color(230,210,210));
		leave.setPreferredSize(new Dimension(110,25));
		
		btnAttackBoss = new JButton();
		btnAttackBoss.setBounds(200,100,300,100);
		btnAttackBoss.setText("Attack");
		btnAttackBoss.setFocusable(false);
		btnAttackBoss.addActionListener(this);
		btnAttackBoss.setBackground(new Color(50,50,50));
		btnAttackBoss.setForeground(new Color(230,210,210));
		btnAttackBoss.setPreferredSize(new Dimension(110,25));
		
		btnAttack = new JButton();
		btnAttack.setBounds(200,100,300,100);
		btnAttack.setText("Attack");
		btnAttack.setFocusable(false);
		btnAttack.addActionListener(this);
		btnAttack.setBackground(new Color(50,50,50));
		btnAttack.setForeground(new Color(230,210,210));
		btnAttack.setPreferredSize(new Dimension(110,25));

		btnSpells = new JButton();
		btnSpells.setBounds(200,100,300,100);
		btnSpells.setText("Magic");
		btnSpells.setFocusable(false);
		btnSpells.addActionListener(this);
		btnSpells.setBackground(new Color(50,50,50));
		btnSpells.setForeground(new Color(230,210,210));
		btnSpells.setPreferredSize(new Dimension(110,25));
		
		btnRewards = new JButton();
		btnRewards.setBounds(200,100,300,100);
		btnRewards.setText("Collect Loot!");
		btnRewards.setFocusable(false);
		btnRewards.addActionListener(this);
		btnRewards.setBackground(new Color(50,50,50));
		btnRewards.setForeground(new Color(235,245,70));
		btnRewards.setPreferredSize(new Dimension(110,25));
		
		btnContinue = new JButton();
		btnContinue.setBounds(200,100,300,100);
		btnContinue.setText("Go on");
		btnContinue.setFocusable(false);
		btnContinue.addActionListener(this);
		btnContinue.setBackground(new Color(50,50,50));
		btnContinue.setForeground(new Color(230,210,210));
		btnContinue.setPreferredSize(new Dimension(110,25));
		
		btnPortalRoom = new JButton();
		btnPortalRoom.setBounds(200,100,300,100);
		btnPortalRoom.setText("Go on");
		btnPortalRoom.setFocusable(false);
		btnPortalRoom.addActionListener(this);
		btnPortalRoom.setBackground(new Color(50,50,50));
		btnPortalRoom.setForeground(new Color(230,210,210));
		btnPortalRoom.setPreferredSize(new Dimension(110,25));
		
		btnBackMagic = new JButton();
		btnBackMagic.setBounds(200,100,300,100);
		btnBackMagic.setText("Back");
		btnBackMagic.setFocusable(false);
		btnBackMagic.addActionListener(this);
		btnBackMagic.setBackground(new Color(50,50,50));
		btnBackMagic.setForeground(new Color(230,210,210));
		btnBackMagic.setPreferredSize(new Dimension(110,25));
		//
		// Quiz
		panelQuestion= new JPanel(new java.awt.GridLayout(2,1));
		panelQuestion.setBounds(163,75,210,50);
		panelQuestion.setBackground(new Color(50,50,50));
		
		panelAnswer= new JPanel(new java.awt.GridLayout(2,1));
		panelAnswer.setBounds(163,125,50,50);
		panelAnswer.setBackground(new Color(50,50,50));
		
		labelQuestion = new JLabel();
		labelQuestion.setBackground(new Color(50,50,50));
		labelQuestion.setForeground(new Color(177, 230, 131));
		labelQuestion.setFont(new Font("Helvetica",Font.PLAIN,20));
		
		textAnswer = new JTextField();
		textAnswer.setPreferredSize(new Dimension(100,20));
		textAnswer.setBackground(new Color(50,50,50));
		textAnswer.setForeground(new Color(227, 221, 204));
		
		btnAnswer = new JButton();
		btnAnswer.setBounds(200,100,300,100);
		btnAnswer.setText("Answer!");
		btnAnswer.setFocusable(false);
		btnAnswer.addActionListener(this);
		btnAnswer.setBackground(new Color(50,50,50));
		btnAnswer.setForeground(new Color(230,210,210));
		btnAnswer.setPreferredSize(new Dimension(110,25));
		
		btnHealRoom = new JButton();
		btnHealRoom.setBounds(200,100,300,100);
		btnHealRoom.setText("Drink");
		btnHealRoom.setFocusable(false);
		btnHealRoom.addActionListener(this);
		btnHealRoom.setBackground(new Color(50,50,50));
		btnHealRoom.setForeground(new Color(0, 255, 128));
		btnHealRoom.setPreferredSize(new Dimension(110,25));
		
		panelQuestion.add(labelQuestion);
		panelAnswer.add(textAnswer);
		
		panelQuestion.setVisible(false);
		panelAnswer.setVisible(false);
		btnAnswer.setVisible(false);
		//
		//
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(410,400);
		window.getContentPane().setBackground(new Color(50,50,50));
		window.setResizable(false);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		window.setTitle("Infinity Tower Adventure Game");
		
		panelLeft = new JPanel(new java.awt.GridLayout(4,1));
		panelLeft.setBounds(0,230,250,132);
		panelLeft.setBackground(new Color(50,50,50));
		panelLeft.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelRight = new JPanel(new java.awt.FlowLayout());
		panelRight.setBounds(250,230,145,132);
		panelRight.setBackground(new Color(50,50,50));
		panelRight.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelMain = new JPanel();
		panelMain.setBounds(0,65,395,115);
		panelMain.setBackground(new Color(50,50,50));
		
		panelTitle = new JPanel(new java.awt.GridLayout(2,1));
		panelTitle.setBounds(0,0,395,65);
		panelTitle.setBackground(new Color(50,50,50));
		panelTitle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelCenter = new JPanel();
		panelCenter.setBounds(0,175,395,50);
		panelCenter.setBackground(new Color(50,50,50));
		
		panelDMain = new JPanel();
		panelDMain.setBounds(0,65,395,165);
		panelDMain.setBackground(new Color(50,50,50));
		panelDMain.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		panelCMain = new JPanel();
		panelCMain.setBounds(117,69,275,110);
		panelCMain.setBackground(new Color(50,50,50));
		panelCMain.setBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED));
		
		panelCenter.add(labelCenter); 
		panelCenter.add(labelCenter2); 
		
		panelTitle.add(labelRoomNum);
		panelTitle.add(labelTitleSmall);
		
		panelLeft.add(labelHP);
		panelLeft.add(labelEnemyHP);
		panelLeft.add(labelDMG);
		panelLeft.add(labelEnemyDMG);
		panelLeft.add(labelARMR);
		panelLeft.add(labelMana);
		panelLeft.add(labelPots);
		
		//panelRight.add(btnShield);
		panelRight.add(btnEvade);
		panelRight.add(btnAttackBoss);
		panelRight.add(btnExit);
		panelRight.add(btnGameOver);
		panelRight.add(btnPortalRoom);
		panelRight.add(btnHealRoom);
		panelRight.add(btnContinue);
		panelRight.add(btnAnswer);
		panelRight.add(btnRewards);
		panelRight.add(btnAttack);
		panelRight.add(explore);
		panelRight.add(inventory);
		panelRight.add(btnSpells);
		panelRight.add(btnBackMagic);
		panelRight.add(heal);
		panelRight.add(leave);
		
		window.add(btnHelp);
		window.add(panelAnswer);
		window.add(panelQuestion);
		window.add(panelImgLeft);
		window.add(panelImgRight);
		window.add(panelCMain);
		window.add(panelCenter);
		window.add(panelDMain);
		window.add(panelTitle);
		window.add(panelMain);
		window.add(panelLeft);
		window.add(panelRight);
		window.setVisible(true);
		//
		
		
		btnEvade.setVisible(false);
		//btnShield.setVisible(false);
		btnAttackBoss.setVisible(false);
		btnExit.setVisible(false);
		btnGameOver.setVisible(false);
		btnPortalRoom.setVisible(false);
		btnHealRoom.setVisible(false);
		btnContinue.setVisible(false);
		btnRewards.setVisible(false);
		btnAttack.setVisible(false);
		btnSpells.setVisible(false);
		btnBackMagic.setVisible(false);
		
		callStats();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==explore) {
			labelCenter2.setText("");
			exploreRoom();
		}
		if(e.getSource()==btnSpells) {
			if (player.bossState == true) {
				btnAttackBoss.setVisible(false);
			}
			else {
				btnAttack.setVisible(false);
			}
			btnSpells.setVisible(false);
			heal.setVisible(false);
			
			btnEvade.setVisible(true);
			//btnShield.setVisible(true);
			btnBackMagic.setVisible(true);
		}
		if(e.getSource()==btnBackMagic) {
			if (player.bossState == true) {
				btnAttackBoss.setVisible(true);
			}
			else {
				btnAttack.setVisible(true);
			}
			
			btnSpells.setVisible(true);
			heal.setVisible(true);
			
			btnEvade.setVisible(false);
			//btnShield.setVisible(false);
			btnBackMagic.setVisible(false);
		}
		if(e.getSource()==btnAttack) {
			attackButton();
		}
		if(e.getSource()==btnEvade) {
			btnMagicEvade();
		}
		if(e.getSource()==leave) {
			gameOver();
		}
		if(e.getSource()==btnAttackBoss) {
			attackButtonBoss();
		}
		if(e.getSource()==inventory) {
			new InventoryWindow();
		}
		if(e.getSource()==heal) {
			healButton();
		}
		if(e.getSource()==btnRewards) {
			rewardRoom();
		}
		if(e.getSource()==btnAnswer) {
			answerCheck();
		}
		if(e.getSource()==btnContinue) {
			noRewardRoom();
		}
		if(e.getSource()==btnHealRoom) {
			healRoomActivate();
		}
		if(e.getSource()==btnPortalRoom) {
			labelRoomNum.setText("Floor " + Player.room);
			labelTitleSmall.setText("Enemy Encounter");
			btnPortalRoom.setVisible(false);
			heal.setVisible(true);
			enemyRoom();
		}
		if(e.getSource()==btnHelp) {
			new HelpWindow();
		}
		if(e.getSource()==btnGameOver) {
			gameOver();
		}
		if(e.getSource()==btnExit) {
			System.exit(0);
		}
		
	}	
	
	void exploreRoom() {
		player.room++;
		player.rewardRates--;
		if (player.room > 10) {
			enemyHP += 15;
			Enemy.enemyScaleDMG += 6;
		}
		if (player.room > 20) {
			enemyHP += 15;
			Enemy.enemyScaleDMG += 6;
		}
		if (player.room > 30) {
			enemyHP += 15;
			Enemy.enemyScaleDMG += 6;
		}
		Enemy.enemyScaleHP += 9;
		Enemy.enemyScaleDMG += 1;
		//if edit, also on portal room
		
		if (player.room == 11 && maxRoom < 11) {
			bossFind();
		}
		else if(player.room == 21) {
			bossFind();
		}
		else if(player.room == 31) {
			bossFind();
		}
		else if(player.room >= 41 && bossComplete == false) {
			bossFind();
		}
		else { 
			int roomKey = rand.nextInt(player.roomScaling);  
			
			if (player.roomScaling < 130) {
				player.roomScaling++;
			}
			
			if (roomKey <= 40) {
				randomEventRoom();
			}
			else if (roomKey <= 200) {
				enemyRoom();
			}
		}
	}
	
	void enemyRoom() {
		enemyAssigned = rand.nextInt(Enemy.enemies.length);
		enemyName = Enemy.enemies[enemyAssigned];
		labelTitleSmall.setText("Enemy Encounter");
		labelImgEncounter.setIcon(enemy.unitAnimArray[enemyAssigned][0]);
		
		labelImgMC.setIcon(player.mcAnimArray[1]);
		labelCenter.setText("† " + enemyName + " has engaged in battle! †");
		labelCenter2.setText("");
		
		panelImgRight.setVisible(true);
		explore.setVisible(false);
		inventory.setVisible(false);
		leave.setVisible(false);
		
		btnAttack.setVisible(true);
		btnSpells.setVisible(true);
		
		enemyHP = rand.nextInt(Enemy.maxEnemyHP);
		enemyHP += Enemy.enemyScaleHP;
		enemyMHP = enemyHP;
		
		callStats();
	}
	void bossFind() {
		player.bossState = true;
		labelTitleSmall.setText("Boss Floor");
		
		labelImgMC.setIcon(player.mcAnimArray[1]);
		labelCenter2.setText("");
		
		panelImgRight.setVisible(true);
		explore.setVisible(false);
		inventory.setVisible(false);
		leave.setVisible(false);
		
		btnAttackBoss.setVisible(true); 
		btnSpells.setVisible(true); 
		bossCounter++;
		
		if (bossCounter == 1) {
			bossName = "Angis";
			labelCenter.setText("† " + bossName + " goes through a wall and is raging around the floor! †");
			labelImgEncounter.setIcon(enemy.bossAnimArray[0]);
			
			bossHP = 300;
			bossDMG = 25;
			
		}
		else if (bossCounter == 2) {
			bossHP = 750;
			bossDMG = 20;
			
			bossName = "GT-9120";
			labelCenter.setText("† " + bossName + " wakes up and charges right to you! †");
			labelImgEncounter.setIcon(enemy.bossAnimArray[1]);
			player.currenthp -= 20;
			labelCenter2.setText("† You recieved 20 damage in surprise! †");
			
			if(player.currenthp < 1) {
				death();
			}
		}
		else if (bossCounter == 3) {
			bossHP = 1000;
			bossDMG = 200;
			
			bossName = "Goku";
			labelCenter.setText("† " + bossName + " has arrived! †");
			labelImgEncounter.setIcon(enemy.bossAnimArray[3]);
			
			bossComplete = true;
		}
		
		bossMHP = bossHP;
		callStats();
	}
	void randomEventRoom() {
		explore.setVisible(false);
		inventory.setVisible(false);
		heal.setVisible(false);
		leave.setVisible(false);
		labelCenter2.setText("");
		panelImgRight.setVisible(true);
		
		int eventKey = rand.nextInt(10);  
		
		if (eventKey == 5 || eventKey == 7 || eventKey == 6 || eventKey == 0 || eventKey == 9 || eventKey == 10) {
			panelImgRight.setVisible(false);
			labelTitleSmall.setText("Quiz Floor");
			quizRoom();
		}
		else if (eventKey == 4) {
			labelTitleSmall.setText("Heal Floor");
			labelImgEncounter.setIcon(player.gameImgArray[4]);
			healRoom();
		}
		else if (eventKey == 3 || eventKey == 8) {
			labelTitleSmall.setText("Trap Floor");
			labelImgEncounter.setIcon(player.gameImgArray[2]);
			trapRoom();
		}
		else if (eventKey == 2) {
			labelTitleSmall.setText("Portal Floor");
			labelImgEncounter.setIcon(player.gameImgArray[3]);
			portalRoom();
		}
		else if (eventKey == 1) {
			labelTitleSmall.setText("Treasure Floor");
			labelImgEncounter.setIcon(player.gameImgArray[1]);
			treasureRoom();
		}
	}

	void quizRoom() {
		int i = rand.nextInt(questions.length);
		currentAnswer = answers[i];
		labelCenter.setText("");
		labelCenter2.setText("");
		
		panelImgLeft.setVisible(true);
		panelQuestion.setVisible(true);
		panelAnswer.setVisible(true);
		btnAnswer.setVisible(true);
		
		labelQuestion.setText("➤ What is " + questions[i] + "?");
	}
	void answerCheck() {
		String answer = textAnswer.getText();
		if (answer.equals(currentAnswer)) {
			labelCenter.setText("† CORRECT! The answer is " + currentAnswer + ". †");
			player.rewDrop = true;
			labelCenter2.setText("† You got an extra rare treasure and a healing potion! †");
			btnAnswer.setVisible(false);
			btnRewards.setVisible(true);
		}
		else {
			labelCenter.setText("† INCORRECT! The answer is " + currentAnswer + ". †");
			labelCenter2.setText("† You get no loot as you proceed to the next floor. †");
			btnAnswer.setVisible(false);
			btnContinue.setVisible(true);
		}
	}
	void healRoom() {
		labelCenter.setText("† The healing fountain heals 10 - 50 hp †");
		labelCenter2.setText("† The healing fountain increases max HP by 10 †");
		btnHealRoom.setVisible(true);
	}
	void healRoomActivate() {
		int healAMT = rand.nextInt(40);
		healAMT += 10;
		player.maxhp += 10;
		player.currenthp += 10;
		if (player.currenthp >= player.maxhp) {
			labelCenter.setText("† You drink the water but you are already at max HP! †");
		}
		else {
			
			player.currenthp += healAMT;
			callStats();
			if (player.currenthp >= player.maxhp) {
				player.currenthp = player.maxhp;
				labelCenter.setText("† The fountain healed you to max HP! †");
				callStats();
			}
			else {
				labelCenter.setText("† You healed for " + healAMT + " HP after drinking the fountain water. †");
				labelCenter2.setText("† You now have " + nf.format(player.currenthp) + " HP. †");
				callStats();
			}
		}
		
		btnHealRoom.setVisible(false);
		btnContinue.setVisible(true);
	}
	void trapRoom() {
		int i = rand.nextInt(traps.length);
		labelCenter.setText(traps[i]);
		int trapDMG = rand.nextInt(25);
		trapDMG += 10;
		player.currenthp -= trapDMG;
		callStats();
		labelCenter2.setText("† You lost " + trapDMG + "HP to the trap! †");
		
		if(player.currenthp < 1) {
			death();
		}
		else {
			trapDMG = 0;
			btnContinue.setVisible(true);
		}
	}
	void portalRoom() {
		int i = rand.nextInt(6);
		boolean val = rand.nextBoolean();
		maxRoom = player.room;
		
		int hpScale = 9;
		int dmgScale = 1;

		if (val == true) {
			i = i-i-i;
		}
		
		if (i >= 1) {
			Enemy.enemyScaleHP += hpScale*i;
			Enemy.enemyScaleDMG += dmgScale*i;
		}
		else if (i <= -1) {
			Enemy.enemyScaleHP -= hpScale*i;
			Enemy.enemyScaleDMG -= dmgScale*i;
		}
		
		player.room += i;
		labelCenter.setText("† You discover a trapped portal room! †");
		
		if (player.room < 0) {
			labelCenter2.setText("† You got back to the start! †");
			btnContinue.setVisible(true);
			player.room = 0;
			Enemy.enemyScaleHP = 0;
			Enemy.enemyScaleDMG = 0;
		}
		else if (player.room >= 11 && player.boss1Death == false) {
			player.room = 10;
			labelCenter2.setText("† You got teleported to the boss room! †");
			btnContinue.setVisible(true);
		}
		else if (player.room >= 21 && player.boss2Death == false) {
			player.room = 20;
			labelCenter2.setText("† You got teleported to the boss room! †");
			btnContinue.setVisible(true);
		}
		else if (player.room >= 31 && player.boss3Death == false) {
			player.room = 30;
			labelCenter2.setText("† You got teleported to the boss room! †");
			btnContinue.setVisible(true);
		}
		else {
			if (i == 0) {
				labelCenter2.setText("† You got teleported but ended up in the same floor! †");
			}
			else {
				labelCenter2.setText("† You moved " + i + " floor/s! †");
			}
			if (i == 0) {
				btnContinue.setVisible(true);
			}
			else {
				btnPortalRoom.setVisible(true);
			}
		}
		
	}
	void treasureRoom() {
		labelCenter.setText("† You discover a room with treasures within it! †");
		player.treasureDrop = true;
		btnRewards.setVisible(true);
	}
	
	void mainHub() {
		if (player.currentMana >= 100) {
			player.currentMana = 100;
		}
		else {
			player.currentMana += 10;
			if (player.currentMana >= 100) {
				player.currentMana = 100;
			}
		}
		enemyHP = 0;
		evasionOn = false;
		
		labelImgMC.setIcon(player.mcAnimArray[0]);
		panelImgRight.setVisible(false);
		btnAttackBoss.setVisible(false);
		explore.setVisible(true);
		inventory.setVisible(true);
		heal.setVisible(true);
		leave.setVisible(true);
		labelRoomNum.setText("Floor " + Player.room);
		labelTitleSmall.setText("Safe Area");
		callStats();
	}
	void callStats() {
		labelMana.setText("➜ Mana: " + player.currentMana + "/" + player.maxMana);
		dmgVisual = player.itemDMG+25+player.basedmg;
		if(player.currenthp < 1) {
			labelHP.setText("➜ HP: 0/" + player.maxhp);
		}
		else {
			labelHP.setText("➜ HP: " + nf.format(player.currenthp) + "/" + player.maxhp);
		}
		labelDMG.setText("➜ Damage: " + nf.format(player.itemDMG+25) + " - " + nf.format(dmgVisual));
		labelARMR.setText("➜ Armor: " + df.format(player.baseDefense) + "%");
		labelPots.setText("➜ Healing Potions: " + player.healPot);
		if (player.bossState == true) {
			if (bossHP <= 0) {
				labelEnemyHP.setText("");
				labelEnemyDMG.setText("");
			}
			else {
				labelEnemyHP.setText("➜ "+ bossName + "'s HP: " + bossHP + "/" + bossMHP);
				labelEnemyDMG.setText("➜ "+ bossName + "'s DMG: "+ bossDMG + "-" + (bossDMG+25));
			}
		}
		else {
			if (enemyHP <= 0) {
				labelEnemyHP.setText("");
				labelEnemyDMG.setText("");
			}
			else {
				labelEnemyHP.setText("➜ "+ enemyName + "'s HP: " + enemyHP + "/" + enemyMHP);
				labelEnemyDMG.setText("➜ "+ enemyName + "'s DMG: " + nf.format(Enemy.enemyScaleDMG) + "-" + nf.format(Enemy.enemyDMG + Enemy.enemyScaleDMG + 5));
			}
		}
		
	}
	void rewardRoom() {
		callStats();
		textAnswer.setText("");
		panelQuestion.setVisible(false);
		panelAnswer.setVisible(false);
		labelCenter.setText("");
		labelCenter2.setText("");
		btnRewards.setVisible(false);
		new RewardsWindow();
		mainHub();
	}
	void noRewardRoom() {
		callStats();
		btnContinue.setVisible(false);
		textAnswer.setText("");
		panelQuestion.setVisible(false);
		panelAnswer.setVisible(false);
		labelCenter.setText("");
		labelCenter2.setText("");
		btnRewards.setVisible(false);
		mainHub();
	}
	void gameOver() {
		btnAttack.setVisible(false);
		heal.setVisible(false);
		btnSpells.setVisible(false);
		btnGameOver.setVisible(true);
		btnAttackBoss.setVisible(false);
		explore.setVisible(false);
		inventory.setVisible(false);
		heal.setVisible(false);
		leave.setVisible(false);
		labelEnemyHP.setText("");
		labelEnemyDMG.setText("");
		labelTitleSmall.setText("Losers Floor");
		labelCenter.setText("† GAME OVER †");
		labelCenter2.setText("† † † † † † † † You conquered " + (player.room-1) + " floors! † † † † † † † †");
		labelImgEncounter.setIcon(player.gameImgArray[0]);
		btnExit.setVisible(true);
		btnGameOver.setVisible(false);
	}
	
	void attackButton() {
		if (player.currentMana >= 100) {
			player.currentMana = 100;
		}
		else {
			player.currentMana += 5;
			if (player.currentMana >= 100) {
				player.currentMana = 100;
			}
		}
		labelImgMC.setIcon(player.mcAnimArray[2]);
		int damageDealt = rand.nextInt(player.basedmg);
		double damageTaken = rand.nextInt(enemy.enemyDMG);
		damageDealt += player.itemDMG+25;
		damageTaken = ((Enemy.enemyScaleDMG+damageTaken+3)-((player.baseDefense/100)*(Enemy.enemyScaleDMG+damageTaken+3)));
		
		enemyHP -= damageDealt;
		
		if (enemyHP <= 0) {
			labelCenter.setText("† You strike the " + enemyName + " for " + damageDealt + " damage. †");
			labelCenter2.setText("† The " + enemyName + " has fallen! †");
			labelImgEncounter.setIcon(enemy.unitAnimArray[enemyAssigned][2]);
			
			btnRewards.setVisible(true);
			btnAttack.setVisible(false);
			heal.setVisible(false);
			btnSpells.setVisible(false);
			
			callStats();
			labelEnemyHP.setText("➜ "+ enemyName + "'s HP: 0/" + enemyMHP);
			labelEnemyDMG.setText("➜ "+ enemyName + "'s DMG: " + nf.format(Enemy.enemyScaleDMG) + "-" + nf.format(Enemy.enemyDMG + Enemy.enemyScaleDMG + 5));
		}
		else {
			if (evasionOn == true) {
				labelCenter.setText("† You strike the " + enemyName + " for " + damageDealt + " damage. †");
				labelCenter2.setText("† Dodged the enemy attack! †");
				labelImgEncounter.setIcon(enemy.unitAnimArray[enemyAssigned][1]);
				evasionOn = false;
				callStats();
			}
			else {
				player.currenthp -= damageTaken;
				
				labelCenter.setText("† You strike the " + enemyName + " for " + damageDealt + " damage. †");
				labelCenter2.setText("† You recieved " + nf.format(damageTaken) + " in retaliation! †");
				labelImgEncounter.setIcon(enemy.unitAnimArray[enemyAssigned][1]);
				callStats();
				
				if(player.currenthp < 1) {
					death();
				}
			}
		}
	}
	void healButton() {
		labelImgMC.setIcon(player.mcAnimArray[1]);
		if (player.currenthp >= player.maxhp) {
			labelCenter.setText("† You are already at max HP! †");
			labelCenter2.setText("");
		}
		else {
			if (player.healPot > 0) {
				player.currenthp += player.healPotAmount;
				player.healPot--;
				callStats();
				if (player.currenthp >= player.maxhp) {
					player.currenthp = player.maxhp;
					labelCenter.setText("† You healed yourself to max HP! †");
					labelCenter2.setText("");
					callStats();
				}
				else {
					labelCenter.setText("† You healed for " + player.healPotAmount + " HP after drinking the potion. †");
					labelCenter2.setText("† You now have " + nf.format(player.currenthp) + " HP. †");
					callStats();
				}
				
			}
			else {
				labelCenter.setText("† You have ran out of healing potions! †");
				labelCenter2.setText("");
			}
		}
	}
	void attackButtonBoss() {
		if (player.currentMana >= 100) {
			player.currentMana = 100;
		}
		else {
			player.currentMana += 5;
			if (player.currentMana >= 100) {
				player.currentMana = 100;
			}
		}
		labelImgMC.setIcon(player.mcAnimArray[2]);
		int damageDealt = rand.nextInt(player.basedmg);
		double damageTaken = rand.nextInt(bossDMG);
		damageDealt += player.itemDMG+25;
		damageTaken = ((damageTaken+25)-((player.baseDefense/100)*(damageTaken+25)));
		
		
		if (bossCounter == 1) { // first boss
			int dodgeBoss = rand.nextInt(4); 
			
			if (dodgeBoss == 0) {
				
				if (evasionOn == true) {
					labelCenter.setText("† You swing your weapon and hit " + bossName + " but he dodged it! †");
					labelCenter2.setText("† You dodged the enemy attack! †");
				}
				else {
					player.currenthp -= damageTaken;
					
					labelCenter.setText("† You swing your weapon and hit " + bossName + " but he dodged it! †");
					labelCenter2.setText("† You recieved " + nf.format(damageTaken) + " in retaliation! †");
				}
				
				callStats();
				
				if(player.currenthp < 1) {
					death();
				}
			}
			
			else {
				bossHP -= damageDealt;
				if (bossHP <= 0) {
					labelCenter.setText("† You swing your weapon and hit " + bossName + " for " + damageDealt + " damage. †");
					labelCenter2.setText("† " + bossName + " has fallen! †");
					labelImgEncounter.setIcon(enemy.bossAnimArray[2]);
					
					player.boss1Death = true;
					
					btnRewards.setVisible(true); 
					btnAttackBoss.setVisible(false);
					heal.setVisible(false);
					btnSpells.setVisible(false);
					
					callStats();
					labelEnemyHP.setText("➜ "+ bossName + "'s HP: 0/" + bossMHP);
					labelEnemyDMG.setText("➜ "+ bossName + "'s DMG: "+ bossDMG + "-" + (bossDMG+25));
				}
				else {
					if (evasionOn == true) {
						labelCenter.setText("† You swing your weapon and hit " + bossName + " for " + damageDealt + " damage. †");
						labelCenter2.setText("† You dodged the enemy attack! †");
					}
					else {
						player.currenthp -= damageTaken;
						
						labelCenter.setText("† You swing your weapon and hit " + bossName + " for " + damageDealt + " damage. †");
						labelCenter2.setText("† You recieved " + nf.format(damageTaken) + " in retaliation! †");
					}
					
					callStats();
					
					if(player.currenthp < 1) {
						death();
					}
				}
			}
		}
		
		else if (bossCounter == 2) { // 2nd boss
			damageDealt = damageDealt/2;
			bossHP -= damageDealt;
			
			if (bossHP <= 0) {
				labelCenter.setText("† You swing your weapon and hit " + bossName + " for " + damageDealt + " damage. †");
				labelCenter2.setText("† " + bossName + " has fallen! †");
				labelImgEncounter.setIcon(enemy.bossAnimArray[2]);
				
				player.boss2Death = true;
				
				btnRewards.setVisible(true); 
				btnAttackBoss.setVisible(false);
				heal.setVisible(false);
				btnSpells.setVisible(false);
				
				callStats();
				labelEnemyHP.setText("➜ "+ bossName + "'s HP: 0/" + bossMHP);
				labelEnemyDMG.setText("➜ "+ bossName + "'s DMG: "+ bossDMG + "-" + (bossDMG+25));
			}
			else {
				if (evasionOn == true) {
					labelCenter.setText("† You strike the " + bossName + " for " + damageDealt + " dealing only half the damage. †");
					labelCenter2.setText("† You dodged the enemy attack! †");
				}
				else {
					player.currenthp -= damageTaken;
					
					labelCenter.setText("† You strike the " + bossName + " for " + damageDealt + " dealing only half the damage. †");
					labelCenter2.setText("† You recieved " + nf.format(damageTaken) + " in retaliation! †");
				}
				
				callStats();
				
				if(player.currenthp < 1) {
					death();
				}
			}
		}
		
		else if (bossCounter == 3) { // third boss
			damageDealt = damageDealt/3;
			bossHP -= damageDealt;
			
			int dodgeBoss = rand.nextInt(5); 
			
			if (dodgeBoss == 0) {
				if (evasionOn == true) {
					labelCenter.setText("† You swing your weapon and hit " + bossName + " but he dodged it! †");
					labelCenter2.setText("† You dodged the enemy attack! †");
				}
				else {
					player.currenthp -= damageTaken;
					
					labelCenter.setText("† You swing your weapon and hit " + bossName + " but he dodged it! †");
					labelCenter2.setText("† You recieved " + nf.format(damageTaken) + " in retaliation! †");
				}
				callStats();
				
				if(player.currenthp < 1) {
					death();
				}
			}
			
			else {
				bossHP -= damageDealt;
				if (bossHP <= 0) {
					labelCenter.setText("† You strike " + bossName + " for " + damageDealt + " dealing a third of damage †");
					labelCenter2.setText("† " + bossName + " has fallen! †");
					labelImgEncounter.setIcon(enemy.bossAnimArray[2]);
					
					player.boss3Death = true;
					
					btnRewards.setVisible(true); 
					btnAttackBoss.setVisible(false);
					heal.setVisible(false);
					btnSpells.setVisible(false);
					
					callStats();
					labelEnemyHP.setText("➜ "+ bossName + "'s HP: 0/" + bossMHP);
					labelEnemyDMG.setText("➜ "+ bossName + "'s DMG: "+ bossDMG + "-" + (bossDMG+25));
				}
				else {
					if (evasionOn == true) {
						labelCenter.setText("† You strike " + bossName + " for " + damageDealt + " dealing a third of damage †");
						labelCenter2.setText("† You dodged the enemy attack! †");
					}
					else {
						player.currenthp -= damageTaken;
						
						labelCenter.setText("† You strike " + bossName + " for " + damageDealt + " dealing a third of damage †");
						labelCenter2.setText("† You recieved " + nf.format(damageTaken) + " in retaliation! †");
					}
					callStats();
					
					if(player.currenthp < 1) {
						death();
					}
				}
			}
		}
		evasionOn = false;
	}

	void btnMagicEvade() {
		if (evasionOn == false) {
			if(player.currentMana >= 35) {
				labelCenter.setText("† You used an evasion spell! (-35 mana) †");
				labelCenter2.setText("† You will dodge the next attack and receive no damage! †");
				player.currentMana -= 35;
				evasionOn = true;
				
			}
			else {
				labelCenter.setText("† You do not have enough mana †");
				labelCenter2.setText("");
			}
		}
		else {
			labelCenter.setText("† You already used an evasion spell †");
			labelCenter2.setText("");
		}
		if (player.bossState == true) {
			btnAttackBoss.setVisible(true);
		}
		else {
			btnAttack.setVisible(true);
		}
		btnSpells.setVisible(true);
		heal.setVisible(true);
		
		btnEvade.setVisible(false);
		//btnShield.setVisible(false);
		btnBackMagic.setVisible(false);
		
		callStats();
	}
	
	void death() {
		callStats();
		labelImgMC.setIcon(player.mcAnimArray[3]);
		labelCenter2.setText("†     You have died!     †");
		btnAttackBoss.setVisible(false);
		btnAttack.setVisible(false);
		heal.setVisible(false);
		btnSpells.setVisible(false);
		btnGameOver.setVisible(true);
	}
}
		
	


