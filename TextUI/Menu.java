package TextUI;

public class Menu {
	
	public static void mainMenu() {
		System.out.println("Welcome to Roma");
		System.out.println("Please select your choices");
		System.out.println("1. Read How To Play");
		System.out.println("2. Start playing");
		System.out.println("3. Exit");
		System.out.print("Enter your choice (1-3): ");
	}
	
	public static void actionMenu() {
		System.out.println();
		System.out.println("Please select your choices");
		System.out.println("1. Lay card(s)");
		System.out.println("2. Take Money");
		System.out.println("3. Draw Card(s)");
		System.out.println("4. Activate Card");
		System.out.println("5. End Turn");
		System.out.print("Enter your choice (1-5): ");
	}
	public static void rules() {
		System.out.println();
		System.out.println("The players take it in turns to make a complete move. A move is made up of 3 phases, which are carried out in the following order:");
		System.out.println("Phase 1: Add up score for unoccupied dice discs");
		System.out.println("Phase 2: Throw the dice");
		System.out.println("Phase 3: Carry out the actions");
		System.out.println();	
		System.out.println("You could enter ? following with card name to get a detail description of the card");
		System.out.println("e.g. ?Forum ");
	}

}
