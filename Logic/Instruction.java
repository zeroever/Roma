package Logic;

import java.util.Scanner;

public class Instruction {
	
	private static Scanner input = new Scanner(System.in);
	public final static int YES = 1;
	//public final static int NO = 2;
	/**
	 * ask player to select a card
	 * @return number of card starting from 1
	 */
	
	public static int selectCard() {
		int cardNumber;
		System.out.println("What card to do want to use: ");
		cardNumber = input.nextInt();
		System.out.println();
		return cardNumber;
	}
	
	/**
	 * ask player to select a dice disc
	 * @return number of dice disc 1 - 6 
	 */
	public static int selectDiceDisc() {
		int position;
		System.out.println("Which dice disc do you want to use: ");
		position = input.nextInt();
		System.out.println();
		return position;
	}
	
	/**
	 * ask player to toss again
	 * player can decide not to
	 * @return player decision
	 * 1.Yes 2.No
	 */
	public static boolean tossAgain() {
		System.out.println("Do you want to toss again? (y/n)");
		String a = input.nextLine();
		a = input.nextLine();
		return (a.matches("^[\\s]*[Yy].*"));
	}
	
	/**
	 * ask player to select action die
	 * @param actionDice
	 * @return number between 1 and 3
	 */
	public static int selectDie(Dice[] actionDice) {
		int choice = 0;
		
		while (choice < 1 || choice > 3) {
			System.out.println("what die(1-3) do you want to use");
			for(int i = 0; i < 3; i++) {
				if(!actionDice[i].isUsed()) {
					System.out.print("Die number:" + (i+1) +": has value of ");
					System.out.println(actionDice[i].getFace()+" ");
				}
			}
			//check that the dice the player choose is not used already
			choice = input.nextInt();
		}
		return choice;
	}
}
