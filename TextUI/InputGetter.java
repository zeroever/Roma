package TextUI;

import java.util.Scanner;

import Logic.Card;

import playingCards.Aesculapinum;
import playingCards.Architectus;
import playingCards.Basilica;
import playingCards.Centurio;
import playingCards.Consiliarius;
import playingCards.Consul;
import playingCards.Essedum;
import playingCards.Forum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Legat;
import playingCards.Legionarius;
import playingCards.Machina;
import playingCards.Mercator;
import playingCards.Mercatus;
import playingCards.Nero;
import playingCards.Onager;
import playingCards.Praetorianus;
import playingCards.Scaenicus;
import playingCards.Senator;
import playingCards.Sicarius;
import playingCards.Templum;
import playingCards.TribunusPlebis;
import playingCards.Turris;
import playingCards.Velites;

/**
 * Getting the input from console
 * 
 * if the user enter ?<card name> means that the user query the card information
 * thus card information will be display. 
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public class InputGetter {
	
	private static Scanner input = new Scanner(System.in);
	private DisplayInterface display;
	
	public InputGetter(DisplayInterface display) {
	    this.display = display;
	}
	
	/**
	 * Getting next integer
	 * @return integer that the user type
	 */
	public int nextInt() {
		
		String string = input.nextLine();
		int number = 0;

		//line starts with ? or line that doest not start with number
		while (!string.matches("[\\d].*")) {
			if (string.length() > 0) {
				String cardName = string.substring(1);
				queryCard(cardName);
			}
			string = input.nextLine();
		} 
		
		//get only the number
		String[] splitString = string.split("[^\\d]");
		
		number = Integer.parseInt(splitString[0]);
		
		return number;
	}
	
	/**
	 * Getting next input line
	 * @return string that the user enter
	 */
	public String nextLine() {
		
		String string = input.nextLine();
		//String returnString = null;
		
		while(string.matches("^[\\s]*[?].*")) {
			String cardName = string.substring(1);
			//query card info here
			queryCard(cardName);
			string = input.nextLine();
		} 
		
		return string; 
	}
	
	/**
	 * Printing out card information to the user
	 * @param cardName - name of a card that the user want its information
	 */
	private void queryCard(String cardName) {
		Card card = null;
		if (cardName.equals("Sicarius")) {
			card = new Sicarius();
		} else if (cardName.equals("Architectus")) {
			card = new Architectus();
		} else if (cardName.equals("Consiliarius")) {
			card = new Consiliarius();
		} else if (cardName.equals("Legat")) {
			card = new Legat();
		} else if (cardName.equals("Gladiator")) {
			card = new Gladiator();
		} else if (cardName.equals("Mercator")) {
			card = new Mercator();
		} else if (cardName.equals("Consul")) {
			card = new Consul();
		} else if (cardName.equals("Legionarius")) {
			card = new Legionarius();
		} else if (cardName.equals("Nero")) {
			card = new Nero();
		} else if (cardName.equals("Praetorianus")) {
			card = new Praetorianus();
		} else if (cardName.equals("Scaenicus")) {
			card = new Scaenicus();
		} else if (cardName.equals("Haruspex")) {
			card = new Haruspex();
		} else if (cardName.equals("Senator")) {
			card = new Senator();
		} else if (cardName.equals("Velites")) {
			card = new Velites();
		} else if (cardName.equals("Essedum")) {
			card = new Essedum();
		} else if (cardName.equals("Tribunus Plebis")) {
			card = new TribunusPlebis();
		} else if (cardName.equals("Centurio")) {
			card = new Centurio();
		} else if (cardName.equals("Aesculapinum")) {
			card = new Aesculapinum();
		} else if (cardName.equals("Basilica")) {
			card = new Basilica();
		} else if (cardName.equals("Machina")){
			card = new Machina();
		} else if (cardName.equals("Forum")) {
			card = new Forum();
		} else if (cardName.equals("Mercatus")) {
			card = new Mercatus();
		} else if (cardName.equals("Onager")) {
			card = new Onager();
		} else if (cardName.equals("Templum")) {
			card = new Templum();
		} else if (cardName.equals("Turris")) {
			card = new Turris();
		}
		
		if(card != null) {
			String type="";
			if (card.getType() == Card.BUILDING) {
				type = "Building";
			} else if (card.getType() == Card.CHARACTER) {
				type = "Character";
			}
			
			String s = String.format("%-15s   Type: %-5s \nCost:%-11d  Defense:%2d\n%s",
	                card.getName(), type, card.getCost(), card.getDefense(), card.getAbility());
			display.showCard(s);
		} else {
			display.showText("Wrong Card Name");
		}
	}

}
