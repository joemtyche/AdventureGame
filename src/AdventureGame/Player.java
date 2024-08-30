package AdventureGame;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	ImageIcon mcIdle = new ImageIcon(Floor.class.getResource("/Resources/KnightIdle.png"));
	ImageIcon mcDead = new ImageIcon(Floor.class.getResource("/Resources/KnightDead.png"));
	ImageIcon mcAttack = new ImageIcon(Floor.class.getResource("/Resources/KnightAttack.png"));
	ImageIcon mcAttackIdle = new ImageIcon(Floor.class.getResource("/Resources/KnightIdle2.png"));
	
	ImageIcon gameOver = new ImageIcon(Floor.class.getResource("/Resources/gameOver.png"));
	ImageIcon treasure = new ImageIcon(Floor.class.getResource("/Resources/treasure.png"));
	ImageIcon trap = new ImageIcon(Floor.class.getResource("/Resources/trap.png"));
	ImageIcon portal = new ImageIcon(Floor.class.getResource("/Resources/portal.png"));
	ImageIcon healFount = new ImageIcon(Floor.class.getResource("/Resources/healFountain.png"));
	ImageIcon encounter = new ImageIcon(Floor.class.getResource("/Resources/encounter.png"));
	
	ImageIcon[] mcAnimArray = {mcIdle,mcAttackIdle,mcAttack,mcDead};
	ImageIcon[] gameImgArray = {gameOver, treasure, trap, portal, healFount, encounter};
	
	static ArrayList<String> inventory = new ArrayList<String>();
	
	static String[] commonItems = {"Stick(C)", "Rusted Helmet(C)", "Chipped Sword(C)",
			"Brick(C)", "Peasant Knowledge(C)", "Apprentice Magic(C)", "Tattered Shield(C)", 
			"Worn Breastplate(C)", "Hockey Stick(C)"};
	static String[] rareItems = {"Sharp Stick(R)", "Shiny new Helmet(R)", "Branded Sword(R)",
			"Noble Knowledge(R)", "Magician Magic(R)", "Gilded Shield(R)", "Katana(R)", "Plate Armor(R)"};
	static String[] urareItems = {"Suuuper sharp Stick(UR)", "King's Crown(UR)", "Guts Sword(UR)",
			"Moon Carver(UR)", "Daedalus(UR)", "Ultra Instinct(UR)", "Royalty Knowledge(UR)", "Magus Magic(UR)"};
	static String[] ssrItems = {
			"Twilight Sword(SSR)", "Robotics Core(SSR)", "Ultra Instinct(SSR)"
	};
	
	static int rewardRates = 200;
	
	int maxhp = 100;
	double currenthp = 100;
	static double baseDefense = 3;
	double defenseInc = 0;
	
	int maxMana = 100;
	int currentMana = 100;
	
	int basedmg = 45;
	static int itemDMG = 0;
	static int healPot = 5;
	int healPotAmount = 20;
	double potDropChance = 25;
	int roomScaling = 100;
	static int room = 1;
	
	int defssr = 20;
	int itemssr = 25;
	
	static boolean bossState = false;
	
	static boolean rewDrop;
	static boolean treasureDrop = false;
	static int bossCount = 0;
	
	static boolean boss1Death = false;
	static boolean boss2Death = false;
	static boolean boss3Death = false;
	
	void defenseStat() {
		double defHold = 0;
		
		if (baseDefense <= 50){
			baseDefense += defenseInc;
			if (baseDefense > 50) {
				defHold = baseDefense - 50;
				baseDefense = 1 - (1 - (baseDefense/100))* (1 - (defHold/100));
				baseDefense *= 100;
			} 
		}
		else {
			baseDefense = 1 - (1 - (baseDefense/100))*(1 - (defenseInc/100));
			baseDefense *= 100;
		}
		defenseInc = 0;
		
		if (baseDefense > 80) {
			baseDefense = 80;
		}
	}
	
	void urareDrop() {
		defenseInc = 7;
		itemDMG += 15;
		defenseStat();
	}
	void rareDrop() {
		defenseInc = 3;
		itemDMG += 8;
		defenseStat();
	}
	void commonDrop() {
		defenseInc = 1;
		itemDMG += 3;
		defenseStat();
	}
	void ssrDrop() {
		defenseInc = defssr;
		itemDMG += itemssr;
		maxhp += 15;
		currenthp += 15;
		defenseStat();
		
		defssr += 10;
		itemssr += 35;
	}
	
}
