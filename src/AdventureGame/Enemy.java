package AdventureGame;

import javax.swing.ImageIcon;

public class Enemy {
	ImageIcon goku1 = new ImageIcon(Floor.class.getResource("/Resources/goku1.png"));
	
	ImageIcon jinnIdle = new ImageIcon(Floor.class.getResource("/Resources/jinnIdle.png"));
	ImageIcon jinnAttack = new ImageIcon(Floor.class.getResource("/Resources/jinnAttack.png"));
	ImageIcon jinnDeath = new ImageIcon(Floor.class.getResource("/Resources/jinnDeath.png"));
	
	ImageIcon demonIdle = new ImageIcon(Floor.class.getResource("/Resources/demonIdle.png"));
	ImageIcon demonAttack = new ImageIcon(Floor.class.getResource("/Resources/demonAttack.png"));
	ImageIcon demonDeath = new ImageIcon(Floor.class.getResource("/Resources/demonDeath.png"));
	
	ImageIcon drakeIdle = new ImageIcon(Floor.class.getResource("/Resources/drakeIdle.png"));
	ImageIcon drakeAttack = new ImageIcon(Floor.class.getResource("/Resources/drakeAttack.png"));
	ImageIcon drakeDeath = new ImageIcon(Floor.class.getResource("/Resources/drakeDeath.png"));
	
	ImageIcon lizardIdle = new ImageIcon(Floor.class.getResource("/Resources/lizardIdle.png"));
	ImageIcon lizardAttack = new ImageIcon(Floor.class.getResource("/Resources/lizardAttack.png"));
	ImageIcon lizardDeath = new ImageIcon(Floor.class.getResource("/Resources/lizardDeath.png"));
	
	ImageIcon medusaIdle = new ImageIcon(Floor.class.getResource("/Resources/medusaIdle.png"));
	ImageIcon medusaAttack = new ImageIcon(Floor.class.getResource("/Resources/medusaAttack.png"));
	ImageIcon medusaDeath = new ImageIcon(Floor.class.getResource("/Resources/medusaDeath.png"));
	
	ImageIcon thiefIdle = new ImageIcon(Floor.class.getResource("/Resources/thiefIdle.png"));
	ImageIcon thiefAttack = new ImageIcon(Floor.class.getResource("/Resources/thiefAttack.png"));
	ImageIcon thiefDeath = new ImageIcon(Floor.class.getResource("/Resources/thiefDeath.png"));
	
	ImageIcon boss1 = new ImageIcon(Floor.class.getResource("/Resources/boss1.png"));
	ImageIcon boss2 = new ImageIcon(Floor.class.getResource("/Resources/boss2.png"));
	ImageIcon bossDeath = new ImageIcon(Floor.class.getResource("/Resources/deathBoss.png"));
	
	
	ImageIcon[][] unitAnimArray = {
			{jinnIdle,jinnAttack,jinnDeath},
			{demonIdle,demonAttack,demonDeath},
			{drakeIdle,drakeAttack,drakeDeath},
			{lizardIdle,lizardAttack,lizardDeath},
			{medusaIdle,medusaAttack,medusaDeath},
			{thiefIdle,thiefAttack,thiefDeath}
	};
	
	ImageIcon[] bossAnimArray = {
			boss1,boss2,bossDeath,goku1
	};
	
	static String[] enemies = {"Jinn", "Demon", "Drake", "Lizard", "Medusa", "Thief"};
	static int maxEnemyHP = 50;
	static int enemyDMG = 3;
	static double enemyScaleHP = 0;
	static double enemyScaleDMG = 0;
	
	
}
