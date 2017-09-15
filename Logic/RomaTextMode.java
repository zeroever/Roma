package Logic;

import java.util.Scanner;

public class RomaTextMode {
	
	private final static int READ_RULES = 1;
	private final static int PLAY_GAME = 2;
	private final static int QUIT = 3;
	private final static int DEMO = 0;
	
	private static Scanner input = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		run(game);
	}
	
	public static void run(Game game) {
		int choice = 0;
		
		while(choice != QUIT) {
			Menu.mainMenu();
			choice = input.nextInt();
			
			if(choice == READ_RULES) {
				Menu.rules();
			} else if(choice == PLAY_GAME) {
				//setting up the game before playing
				game.preparePhase();
				//start the game
				game.start();
				
			/* EasterEgg */
			} else if (choice == DEMO) {
				Demo.run();
			/* EasterEgg */
			} else if (choice == QUIT){ //player quit the game
				System.out.println("Good Bye");
			} else {
				System.out.println("Invalid input");
			}
			System.out.println();
		}

	}

	
}
