import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class RPG {
	public static void main(String[] args) {
		JFrame frame = new JFrame("The Elder Codes V: G.U.I-rim ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RPGPanel game = new RPGPanel();
		frame.getContentPane().add(game);
		
		frame.setSize(975, 700);
		frame.setVisible(true);
	}
}
class RPGPanel extends JPanel{
	//GUI variables
	private JPanel upperTitlePanel, midTitlePanel, startPanel, mainScreenTextPanel, optionPanel, HUDPanel;
	private JLabel upperTitleLabel, midTitleLabel, HPLabel, HPLabelValue, magickaLabel, magickaLabelValue, staminaLabel, staminaLabelValue, weaponLabel, weaponLabelValue, armorLabel, armorLabelValue;
	private JButton startButton, option1, option2, option3, option4;
	private Font upperTitleFont = new Font("Cambria", Font.PLAIN, 28), midTitleFont = new Font("Cambria", Font.BOLD, 40), mainTextFont = new Font("Cambria", Font.PLAIN, 22), HUDTextFont = new Font("Cambria", Font.PLAIN, 14);
	private JTextArea mainScreenTextArea;
	
	//player and enemy variables
	private int HP, maxHP, magicka, maxMagicka, stamina, maxStamina, attackDamage, maxAttackDamage;
	private int enemyHP = 50, maxEnemyHP, dragonHP, maxDragonHP, enemyAttackDamage, maxEnemyAttackDamage, dragonAttackDamage, maxDragonAttackDamage; 
	private String weapon, armor, location;
	private String[] spells = {"Flames","Frostbite", "Sparks", "Firebolt", "Ice Spike", "Lightning Bolt"};
	private String[] enemies = {"Wolf", "Sabre Cat", "Bandit", "Bandit Wizard", "Skeleton", "Bandit Outlaw", "Fire Mage", "Thief", "Warhound", "Giant Spider", "Frost Troll", "Giant", "Conjurer", "Frost Atronach", "Frost Bear", "Witch", "Ghost", "Werewolf", "Vampire", "Storm Mage"};
	private String spell;
	private String enemy, dragon;
	private boolean visited = false;
	Random rand = new Random();
	
	public RPGPanel() {
		setLayout(new BorderLayout());
		upperTitlePanel = new JPanel();
		upperTitlePanel.setBackground(Color.black);
		
		upperTitleLabel = new JLabel("The Elder Codes V: G.U.I-rim");
		upperTitleLabel.setForeground(Color.white);
		upperTitleLabel.setFont(upperTitleFont);
		
		midTitlePanel = new JPanel();
		midTitlePanel.setBackground(Color.black);
		
		midTitleLabel = new JLabel("A Text-Based Adventure");
		midTitleLabel.setForeground(Color.white);
		midTitleLabel.setFont(midTitleFont);
		
		startPanel = new JPanel();
		startPanel.setBackground(Color.black);
		
		startButtonListener startListener = new startButtonListener();
		startButton = new JButton("Start");
		startButton.addActionListener(startListener);
		startButton.setPreferredSize(new Dimension(250, 40));
		startButton.setFocusPainted(false);
		
		upperTitlePanel.add(upperTitleLabel);
		midTitlePanel.add(midTitleLabel);
		startPanel.add(startButton);
		
		add(upperTitlePanel, BorderLayout.NORTH);
		add(midTitlePanel, BorderLayout.CENTER);
		add(startPanel, BorderLayout.SOUTH); 
		
		setBackground(Color.black);
	}

	public void mainScreen() {
		//game screen setup
		setLayout(null);
		midTitlePanel.setVisible(false);
		startPanel.setVisible(false);
		
		HUDPanel = new JPanel();
		HUDPanel.setBounds(35, 50, 900, 50);
		HUDPanel.setBackground(Color.black);
		HUDPanel.setLayout(new GridLayout(1, 5));
		
		HPLabel = new JLabel("Health:");
		HPLabel.setFont(HUDTextFont);
		HPLabel.setForeground(Color.white);
		HPLabelValue = new JLabel();
		HPLabelValue.setFont(HUDTextFont);
		HPLabelValue.setForeground(Color.white);
		
		magickaLabel = new JLabel("Magicka:");
		magickaLabel.setFont(HUDTextFont);
		magickaLabel.setForeground(Color.white);
		magickaLabelValue = new JLabel();
		magickaLabelValue.setFont(HUDTextFont);
		magickaLabelValue.setForeground(Color.white);
		
		staminaLabel = new JLabel("Stamina:");
		staminaLabel.setFont(HUDTextFont);
		staminaLabel.setForeground(Color.white);
		staminaLabelValue = new JLabel();
		staminaLabelValue.setFont(HUDTextFont);
		staminaLabelValue.setForeground(Color.white);
		
		weaponLabel = new JLabel("Weapon:");
		weaponLabel.setFont(HUDTextFont);
		weaponLabel.setForeground(Color.white);
		weaponLabelValue = new JLabel();
		weaponLabelValue.setFont(HUDTextFont);
		weaponLabelValue.setForeground(Color.white);
		
		armorLabel = new JLabel("Armor:");
		armorLabel.setFont(HUDTextFont);
		armorLabel.setForeground(Color.white);
		armorLabelValue = new JLabel();
		armorLabelValue.setFont(HUDTextFont);
		armorLabelValue.setForeground(Color.white);
		
		mainScreenTextPanel = new JPanel();
		mainScreenTextPanel.setBounds(130, 150, 700, 250);
		mainScreenTextPanel.setBackground(Color.black);
		
		mainScreenTextArea = new JTextArea("Main text here.");
		mainScreenTextArea.setFont(mainTextFont);
		mainScreenTextArea.setBounds(100, 100, 650, 250);
		mainScreenTextArea.setBackground(Color.black);
		mainScreenTextArea.setForeground(Color.white);
		mainScreenTextArea.setLineWrap(true);
		
		optionButtonListener opListener = new optionButtonListener();
		
		optionPanel = new JPanel();
		optionPanel.setBounds(315, 400, 300, 200);
		optionPanel.setBackground(Color.black);
		
		option1 = new JButton();
		option1.setPreferredSize(new Dimension(250, 40));
		option1.setFocusPainted(false);
		option1.setActionCommand("op1");
		option1.addActionListener(opListener);
		
		option2 = new JButton();
		option2.setPreferredSize(new Dimension(250, 40));
		option2.setFocusPainted(false);
		option2.setActionCommand("op2");
		option2.addActionListener(opListener);
		
		option3 = new JButton();
		option3.setPreferredSize(new Dimension(250, 40));
		option3.setFocusPainted(false);
		option3.setActionCommand("op3");
		option3.addActionListener(opListener);
		
		option4 = new JButton();
		option4.setPreferredSize(new Dimension(250, 40));
		option4.setFocusPainted(false);
		option4.setActionCommand("op4");
		option4.addActionListener(opListener);
		
		HUDPanel.add(HPLabel);
		HUDPanel.add(HPLabelValue);
		HUDPanel.add(magickaLabel);
		HUDPanel.add(magickaLabelValue);
		HUDPanel.add(staminaLabel);
		HUDPanel.add(staminaLabelValue);
		HUDPanel.add(weaponLabel);
		HUDPanel.add(weaponLabelValue);
		HUDPanel.add(armorLabel);
		HUDPanel.add(armorLabelValue);
		
		mainScreenTextPanel.add(mainScreenTextArea);
		optionPanel.add(option1);
		optionPanel.add(option2);
		optionPanel.add(option3);
		optionPanel.add(option4);
		
		add(HUDPanel);
		add(mainScreenTextPanel);
		add(optionPanel);
		
		HUDStats();
		
	}
	//player and enemy stats
	public void HUDStats() {
		HP = 100;
		maxHP = 100;
		magicka = 100;
		maxMagicka = 100;
		stamina = 100;
		maxStamina = 100;
		weapon = "Fists";
		armor = "Belted Tunic";
		
		enemyHP = 50;
		maxEnemyHP = 50;
		dragonHP = 150;
		maxDragonHP = 150;
		
		HPLabelValue.setText("" + HP + "/" + maxHP);
		magickaLabelValue.setText("" + magicka + "/" + maxMagicka);
		staminaLabelValue.setText("" + stamina + "/" + maxStamina);
		weaponLabelValue.setText(weapon);
		armorLabelValue.setText(armor);
		
		startingWoods();
	}
	//scene movement
	public void startingWoods() { 
		location = "startingWoods";
		mainScreenTextArea.setText("You wake to the sound of rustling trees and the feeling of warm sun. You feel the hard ground beneath you. Hmmm...it seems you fell asleep in the woods again. You are an explorer, new to the province of G.U.I-rim.");
		option1.setText("Look Around");
		option2.setText("Get Up");
		option3.setText("Go Back To Sleep");
		option4.setText("");
	}
	public void startingWoodsLookAround() {
		location = "startingWoodsLookAround";
		mainScreenTextArea.setText("Tall trees blow in the light wind. A distant stream runs slowly. There is no one around you for a few miles. Your old sword lays on the ground next to you.");
		option1.setText("Get Up");
		option2.setText("Go Back To Sleep");
		option3.setText("");
		option4.setText("");
	}
	public void startingWoodsGetUp() {
		location = "startingWoodsGetUp";
		mainScreenTextArea.setText("You get up from your nap and prepare to be on your way. Your old sword lays on the ground.");
		option1.setText("Pick It Up");
		option2.setText("Leave It There");
		option3.setText("");
		option4.setText("");
	}
	public void startingWoodsGoBackToSleep() {
		location = "startingWoodsGoBackToSleep";
		mainScreenTextArea.setText("You already feel rested and can no longer go back to sleep.");
		option1.setText("Look Around");
		option2.setText("Get Up");
		option3.setText("");
		option4.setText("");
	}
	public void startingWoodsPickItUp() {
		location = "startingWoodsPickItUp";
		weapon = "Iron Sword";
		weaponLabelValue.setText("Iron Sword");
		mainScreenTextArea.setText("(Obtained Iron Sword)!\n\nThe sword is old and weathered. The blade, although dull, is more effective than your bare fists.");
		option1.setText("Continue...");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void startingWoodsLeaveItThere() {
		location = "startingWoodsLeaveItThere";
		mainScreenTextArea.setText("In spite of your better judgement, you leave the sword where it is.");
		option1.setText("Continue...");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void startingWoodsNextArea() {
		location = "startingWoodsNextArea";
		mainScreenTextArea.setText("G.U.I.-rim is a large place. You have a few locations to consider travelling to. There is a small town, the city, or the mountains. The town is quiet, but you can rest there. The city is full of merchants and adventuring opportunities, but the road there is unsafe. You will encounter enemies on the way. The mountains are very dangerous. Without proper arms and armor you should not go there. \n\nWhere do you want to go?");
		option1.setText("Go To The Small Town");
		option2.setText("Go To The City");
		option3.setText("Go To The Mountains");
		option4.setText("");
	}
	public void nextAreaGoToTheSmallTown() {
		location = "nextAreaGoToTheSmallTown";
		mainScreenTextArea.setText("The small town of Codingwood!\n\n");
		option1.setText("Rest At The Tavern");
		option2.setText("Listen To The Locals");
		option3.setText("Leave");
		option4.setText("");
	}
	public void tavern() {
		location = "tavern";
		HP = 100;
		magicka = 100;
		stamina = 100;
		HPLabelValue.setText("" + HP + "/" + maxHP);
		magickaLabelValue.setText("" + magicka + "/" + maxMagicka);
		staminaLabelValue.setText("" + stamina + "/" + maxStamina);
		mainScreenTextArea.setText("The tavern is lit by a warm fire. The inviting atmosphere puts you at ease. You feel rested.\n\n(Stats replenished)");
		option1.setText("Listen To The Locals");
		option2.setText("Leave");
		option3.setText("");
		option4.setText("");
	}
	public void listenToLocals() {
		location = "listenToLocals";
		mainScreenTextArea.setText("The locals speak of a dragon atop the mountain. Apparently, the lord of the nearby city is looking for noble adventurers to face the beast.");
		option1.setText("Rest At The Tavern");
		option2.setText("Leave");
		option3.setText("");
		option4.setText("");
	}
	public void nextAreaGoToTheCity() {
		location = "nextAreaGoToTheCity";
		mainScreenTextArea.setText("The city of Compilerun!");
			option1.setText("Speak To The Merchant");
			option2.setText("Rest At The Tavern");
			option3.setText("Go To The Palace");
			option4.setText("Leave");
		
	}
	public void merchant() {
		location = "merchant";
		if(weapon.equals("Steel Sword") && armor.equals("Steel Armor")) {
			mainScreenTextArea.setText("Merchant: Unfortunately that's all I can give you.");
			option1.setText("Continue...");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}else {
			mainScreenTextArea.setText("Merchant: You look like the adventuring type. You must be here to aid the Lord of Compilerun! I am offering some of my equipment to adventurers in hopes that one of you will solve this dragon problem. So what do you say?\n\nYou can take this steel armor and Sword (+50 HP)");
			option1.setText("Take The Steel Armor And Sword");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}
	}
	public void merchant2() {
		location = "merchant2";
		HP = HP + 50;
		maxHP = maxHP + 50;
		magicka = magicka + 50;
		maxMagicka = maxMagicka + 50;
		stamina = stamina + 50;
		maxStamina = maxStamina + 50;
		weapon = "Steel Sword";
		armor = "Steel Armor";
		weaponLabelValue.setText("Steel Sword");
		armorLabelValue.setText("Steel Armor");
		HPLabelValue.setText("" + HP + "/" + maxHP);
		magickaLabelValue.setText("" + magicka + "/" + maxMagicka);
		staminaLabelValue.setText("" + stamina + "/" + maxStamina);
		
		mainScreenTextArea.setText("Merchant: Good luck to you adventurer!");
		option1.setText("Continue...");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void tavern2() {
		location = "tavern2";
		HP = maxHP;
		magicka = maxMagicka;
		stamina = maxStamina;
		HPLabelValue.setText("" + HP + "/" + maxHP);
		magickaLabelValue.setText("" + magicka + "/" + maxMagicka);
		staminaLabelValue.setText("" + stamina + "/" + maxStamina);
		mainScreenTextArea.setText("The tavern is lit by a warm fire. The inviting atmosphere puts you at ease. You feel rested.\n\n(Stats replenished)");
			option1.setText("Speak To The Merchant");
			option2.setText("Go To The Palace");
			option3.setText("Leave");
			option4.setText("");
	}
	public void palace() {
		location = "palace";
		mainScreenTextArea.setText("The regal palace buzzes with noise. The Lord of Compilerun sits in the center of the main hall.");
		option1.setText("Speak To The Lord");
		option2.setText("Leave");
		option3.setText("");
		option4.setText("");
	}
	public void lord() {
		location = "lord";
		if(visited == true) {
			mainScreenTextArea.setText("Lord of Compilerun: Hello again, adventurer! Good luck on your journey.");
			option1.setText("Continue...");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}else {
			mainScreenTextArea.setText("Lord of Compilerun: Hello adventurer,\n I trust you're here about the dragon atop the mountain. It is a great danger to my people. Defeat it and I will name you Hero of Compilerun.\n\nDo you accept?");
			option1.setText("Accept The Terms");
			option2.setText("");
			option3.setText("");
			option4.setText("");
		}
	}
	public void lord2() {
		location = "lord2";
		visited = true;
		mainScreenTextArea.setText("Lord of Compilerun: I am glad to hear you'll help my people. I hope you fair better than the others.");
		option1.setText("Continue");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void nextAreaGoToTheMountains() {
		location = "nextAreaGoToTheMountains";
		if(weapon.equals("Steel Sword") && armor.equals("Steel Armor")){
			mainScreenTextArea.setText("With proper armor and a strong sword, you are a lot safer going through the mountains. You travel high up until you reach the dragon.");
			option1.setText("Battle The Dragon");
			option2.setText("Leave");
			option3.setText("");
			option4.setText("");
		}else {
			mainScreenTextArea.setText("Without the proper equipment, the mountains are too dangerous. You think it would be best to try to go here later.");
			option1.setText("Go To The Small Town");
			option2.setText("Go To The City");
			option3.setText("");
			option4.setText("");
		}
	}
	public void bossBattle() {
		location = "bossBattle";
		enemy = "Dragon";
		mainScreenTextArea.setText(enemy + " HP: " + dragonHP + "/" + maxDragonHP);
		option1.setText("Attack");
		option2.setText("Spell Attack");
		option3.setText("");
		option4.setText("");
	}
	public void bossBattleAttack() {
		location = "bossBattleAttack";
		stamina = stamina - 5;
		staminaLabelValue.setText("" + stamina + "/" + maxStamina);
		dragonHP = dragonHP - attackDamage;
		if(dragonHP < 1) {
			mainScreenTextArea.setText(enemy + " HP: 0/" + maxDragonHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}else {
			mainScreenTextArea.setText(enemy + " HP: " + dragonHP + "/" + maxDragonHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}
		option1.setText("Continue..");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void bossBattleSpellAttack() {
		location = "bossBattleSpellAttack";
		spell = spells[rand.nextInt(spells.length)];
		maxAttackDamage = 45;
		attackDamage = rand.nextInt(maxAttackDamage);
		magicka = magicka - 5;
		magickaLabelValue.setText("" + magicka + "/" + maxMagicka);
		dragonHP = dragonHP - attackDamage;
		if(dragonHP < 1) {
			mainScreenTextArea.setText(enemy + " HP: 0/" + maxDragonHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}else {
			mainScreenTextArea.setText(enemy + " HP: " + dragonHP + "/" + maxDragonHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}
		option1.setText("Continue..");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void dragonAttack() {
		location = "dragonAttack";
		maxDragonAttackDamage = 50;
		dragonAttackDamage = rand.nextInt(maxDragonAttackDamage);
		mainScreenTextArea.setText(enemy + " HP: " + dragonHP + "/" + maxDragonHP + "\n\nThe " + enemy + " inflicted " + dragonAttackDamage + " damage!");
		HP = HP - dragonAttackDamage;
		if(HP < 1) {
			HPLabelValue.setText("0/" + maxHP);
		}else {
		HPLabelValue.setText("" + HP + "/" + maxHP);
		}
		option1.setText("Continue...");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void enemyEncounter() {
		location = "enemyEncounter";
		enemy = enemies[rand.nextInt(enemies.length)];
		mainScreenTextArea.setText("You encounter a " + enemy + "!" + "\n\nWhat do you want to do?");
		option1.setText("Fight");
		option2.setText("Run");
		option3.setText("");
		option4.setText("");
	}
	public void enemyEncounterFight() {
		location = "enemyEncounterFight";
		mainScreenTextArea.setText(enemy + " HP: " + enemyHP + "/" + maxEnemyHP);
		option1.setText("Attack");
		option2.setText("Spell Attack");
		option3.setText("Run");
		option4.setText("");
	}
	public void enemyEncounterRun() {
		location = "enemyEncounterRun";
		mainScreenTextArea.setText("You ran away from the " + enemy);
		option1.setText("Continue...");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void playerAttack() {
		location = "playerAttack";
		if(weapon.equals("Fists")) {
			maxAttackDamage = 15;
			attackDamage = rand.nextInt(maxAttackDamage);
		}else if(weapon.equals("Iron Sword")) {
			maxAttackDamage = 25;
			attackDamage = rand.nextInt(maxAttackDamage);
		}else {
			maxAttackDamage = 50;
			attackDamage = rand.nextInt(maxAttackDamage);
		}
		stamina = stamina - 5;
		staminaLabelValue.setText("" + stamina + "/" + maxStamina);
		enemyHP = enemyHP - attackDamage;
		if(enemyHP < 1) {
			mainScreenTextArea.setText(enemy + " HP: 0/" + maxEnemyHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}else {
			mainScreenTextArea.setText(enemy + " HP: " + enemyHP + "/" + maxEnemyHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}
		option1.setText("Continue..");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void spellAttack() {
		location = "spellAttack";
		spell = spells[rand.nextInt(spells.length)];
		maxAttackDamage = 35;
		attackDamage = rand.nextInt(maxAttackDamage);
		magicka = magicka - 5;
		magickaLabelValue.setText("" + magicka + "/" + maxMagicka);
		enemyHP = enemyHP - attackDamage;
		if(enemyHP < 1) {
			mainScreenTextArea.setText(enemy + " HP: 0/" + maxEnemyHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}else {
			mainScreenTextArea.setText(enemy + " HP: " + enemyHP + "/" + maxEnemyHP + "\n\nYou attacked the " + enemy + " inflicting " + attackDamage + " damage!");
		}
		option1.setText("Continue..");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void enemyAttack() {
		location = "enemyAttack";
		maxEnemyAttackDamage = 15;
		enemyAttackDamage = rand.nextInt(maxEnemyAttackDamage);
		mainScreenTextArea.setText(enemy + " HP: " + enemyHP + "/" + maxEnemyHP + "\n\nThe " + enemy + " inflicted " + enemyAttackDamage + " damage!");
		HP = HP - enemyAttackDamage;
		if(HP < 1) {
			HPLabelValue.setText("0/" + maxHP);
		}else {
		HPLabelValue.setText("" + HP + "/" + maxHP);
		}
		option1.setText("Continue...");
		option2.setText("");
		option3.setText("");
		option4.setText("");
	}
	public void win() {
		location = "win";
		mainScreenTextArea.setText("You defeated the " + enemy + "!");
		option1.setText("Continue...");
	}
	public void lose() {
		location = "lose";
		mainScreenTextArea.setText("You have died! You were defeated by a " + enemy + "\n\nGAME OVER!");
		option1.setText("Start Over");
		option2.setText("Quit");
		option3.setText("");
		option4.setText("");
	}
	public void beatGame() {
		location = "beatGame";
		mainScreenTextArea.setText("You defeated the Dragon!\n\nAs a reward for your efforts you were named Hero of Compilerun, a title know all throughout G.U.I-rim.\n\n CONGRATULATIONS!");
		option1.setText("Try Again");
		option2.setText("Quit");
		option3.setText("");
		option4.setText("");
	}
	
	private class startButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			mainScreen();
		}
	}
	private class optionButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String optionChosen = event.getActionCommand();
			
			switch(location) {
			case "startingWoods": 
				switch(optionChosen) {
				case "op1": 
					startingWoodsLookAround();
					break;
				
				case "op2": 
					startingWoodsGetUp();
					break;
				
				case "op3":
					startingWoodsGoBackToSleep();
					break;
				}
				break;
			case "startingWoodsLookAround": 
				switch(optionChosen) {
				case "op1":
					startingWoodsGetUp();
					break;
					
				case "op2":
					startingWoodsGoBackToSleep();
					break;
				}
				break;
			case "startingWoodsGetUp":
				switch(optionChosen) {
				case "op1":
					startingWoodsPickItUp();
					break;
					
				case "op2":
					startingWoodsLeaveItThere();
					break;
				}
				break;
			case "startingWoodsGoBackToSleep":
				switch(optionChosen) {
				case "op1":
					startingWoodsLookAround();
					break;
					
				case "op2": 
					startingWoodsGetUp();
					break;
				}
				break;
			case "startingWoodsLeaveItThere":
				switch(optionChosen) {
				case "op1":
					startingWoodsNextArea();
					break;
				}
				break;
			case "startingWoodsPickItUp":
				switch(optionChosen) {
				case "op1":
					startingWoodsNextArea();
					break;
				}
				break;
			case "startingWoodsNextArea": 
				switch(optionChosen) {
				case "op1":
					nextAreaGoToTheSmallTown();
					break;
				case "op2":
					enemyEncounter();
					break;
				case "op3":
					nextAreaGoToTheMountains();
				}
				break;
				
			case "enemyEncounter": 
				switch(optionChosen) {
				case "op1":
					enemyEncounterFight();
					break;
				case"op2":
					enemyEncounterRun();
					break;
				}
				break;
			case "enemyEncounterFight": 
				switch(optionChosen) {
				case "op1":
					playerAttack();
					break;
				case"op2":
					spellAttack();
					break;
				case "op3":
					enemyEncounterRun();
					break;
				}
				break;
			case "enemyEncounterRun": 
				switch(optionChosen) {
				case "op1":
					nextAreaGoToTheCity();
					break;
				}
				break;
			case "playerAttack": 
				switch(optionChosen) {
				case "op1":
					if(enemyHP < 1) {
						win();
					}else {
						enemyAttack();
					}
					break;
				}
				break;
			case "spellAttack":
				switch(optionChosen) {
				case "op1":
					if(enemyHP < 1) {
						win();
					}else {
						enemyAttack();
					}
					break;
				}
				break;
			case "enemyAttack":
				switch(optionChosen) {
				case "op1":
					if(HP < 1) {
						lose();
					}else {
						enemyEncounterFight();
					}
					break;
				}
				break;
			case "win":
				switch(optionChosen) {
				case "op1":
					if(enemy.equals("Dragon")) {
						beatGame();
					}else {
						nextAreaGoToTheCity();	
					}
					break;
				}
				break;
			case "lose":
				switch(optionChosen) {
				case "op1":
					startingWoods();
					break;
				case "op2":
					System.exit(0);
					break;
				}
				break;
			case "nextAreaGoToTheSmallTown": 
				switch(optionChosen) {
				case "op1":
					tavern();
					break;
				case "op2":
					listenToLocals();
					break;
				case "op3":
					startingWoodsNextArea();
					break;
				}
				break;
			case "tavern":
				switch(optionChosen) {
				case "op1":
					listenToLocals();
					break;
				case "op2":
					nextAreaGoToTheSmallTown();
					break;
				}
				break;
			case "listenToLocals":
				switch(optionChosen) {
				case "op1":
					tavern();
					break;
				case "op2":
					nextAreaGoToTheSmallTown();
					break;
				}
				break;
			case "nextAreaGoToTheCity":
				switch(optionChosen) {
				case "op1":
					merchant();
					break;
				case "op2":
					tavern2();
					break;
				case "op3":
					palace();
					break;
				case "op4":
					startingWoodsNextArea();
					break;
				}
				break;
			case "merchant":
				switch(optionChosen) {
				case "op1":
					if(weapon.equals("Steel Sword") && armor.equals("Steel Armor")) {
						nextAreaGoToTheCity();
					}else {
						merchant2();
					}
					break;
				}
				break;
			case "merchant2":
				switch(optionChosen) {
				case "op1":
					nextAreaGoToTheCity();
				}
				break;
			case "nextAreaGoToTheMountains": 
				
				switch(optionChosen) {
				
				case "op1":
					if(weapon.equals("Steel Sword") && armor.equals("Steel Armor")) {
						bossBattle();
					}else {
						nextAreaGoToTheSmallTown();
					}
					break;
				case "op2":
					if(weapon.equals("Steel Sword") && armor.equals("Steel Armor")) {
						startingWoodsNextArea();
					}else {
						nextAreaGoToTheCity();
					}
					break;
				}
				break;
			case "tavern2":
				switch(optionChosen) {
				case "op1":
					merchant();
					break;
				case "op2":
					palace();
					break;
				case "op3":
					nextAreaGoToTheCity();
					break;
				}
				break;
			case "palace":
				switch(optionChosen) {
				case "op1":
					lord();
					break;
				case "op2":
					nextAreaGoToTheCity();
				}
				break;
			case "lord":
				switch(optionChosen) {
				case "op1":
					lord2();
				}
			case "lord2":
				switch(optionChosen) {
				case "op1":
					palace();
					break;
				}
				break;
			case "bossBattle":
				switch(optionChosen) {
				case "op1":
					bossBattleAttack();
					break;
				case "op2": 
					bossBattleSpellAttack();
					break;
				}
				break;
			case "bossBattleAttack":
				switch(optionChosen) {
				case "op1":
					if(dragonHP < 1) {
						win();
					}else {
						dragonAttack();
					}
					break;
				}
				break;
			case "bossBattleSpellAttack":
				switch(optionChosen) {
				case "op1":
					if(dragonHP < 1) {
						win();
					}else {
						dragonAttack();
					}
					break;
				}
				break;
			case "dragonAttack":
				switch(optionChosen) {
				case "op1":
					if(HP < 1) {
						lose();
					}else {
						bossBattle();
					}
					break;
				}
				break;
			}
		}
	}
}

