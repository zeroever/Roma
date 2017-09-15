package TextUI;

import static tests.main.CardCode.*;
import Logic.*;

import java.util.*;

public class TextInstruction implements InstructionInterface {

	private static final int INCREMENT = 1;
	private static final int DECREMENT = 0;

	private DisplayInterface display;
	private InputGetter input;
	
	public TextInstruction(DisplayInterface display, InputGetter input) {
	    this.display = display;
	    this.input = input;
	}
	
	public int selectCard(Deck deck) {
		int cardNumber = 0;
		display.listCard(deck);
		while(cardNumber < 1 || cardNumber > deck.getNumOfCards()) {
		    display.showText("Select Card (Enter card number): ");
		    cardNumber = input.nextInt();
		}
		display.showText("");
		return cardNumber;
	}

	public int selectDiceDisc() {
		int position = 0;
		while(position < 1 || position > 7) {
		    display.showText("Select Dice Disc (Enter number between 1 - 7): ");
		    position = input.nextInt();
		}
		display.showText("");
		return position;
	}

	public boolean tossAgain() {
	    display.showText("Do you want to toss again? (y/n)");
		String a = input.nextLine();
		return (a.matches("^[\\s]*[Yy].*"));
	}

	public int selectDie(Dice[] actionDice) {
		int choice = Dice.NO_DIE;

		while (choice < 1 || choice > 3) {
		    display.showText("what die do you want to use(Enter number between 1-3)");
			for (int i = 0; i < 3; i++) {
			    //show only unused action die
				if (!actionDice[i].isUsed()) {
				    display.showText("Die number:" + (i + 1)
							+ ": has value of " + actionDice[i].getFace());
				}
			}
			choice = input.nextInt();
		}
		return choice - 1;
	}

	public int[] getActivateParameter(Game game, int cardCode,
			int activatedCardPosition) {
		Player player = game.getCurrentPlayer();
		Field field = game.getField();

		int[] parameter = null;

		if (cardCode == SCAENICUS) {
		    //select dice disc to copy from
			int diceDisc = selectDiceDisc();
			// find a copied card
			Card card = field.getCard(player.getId(), diceDisc);
			if(card != null) {
				// get activation parameter of the copied card
				int[] copyParameter = getParameter(card.getIndex(), game,
						activatedCardPosition);
				parameter = new int[copyParameter.length + 1];
				parameter[0] = diceDisc;
				for (int i = 0; i < copyParameter.length; i++) {
					parameter[i + 1] = copyParameter[i];
				}
			} else {
				parameter = new int[1];
				parameter[0] = 0;
			}
		} else {
			parameter = getParameter(cardCode, game, activatedCardPosition);
		}
		return parameter;
	}

	private int[] getParameter(int cardCode, Game game, int activatedCardPosition) {
		Player player = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Deck drawPile = game.getDrawDeck();
        Deck discardPile = game.getDiscardPile();
        Dice battleDice = game.getBattleDice();
        Dice[] actionDice = game.getActionDice();
        Field field = game.getField();
		int[] parameter;
		ArrayList<Integer> paramList = new ArrayList<Integer>();
		
		if(cardCode == AESCULAPINUM) {
			System.out.println("Which card do you want to pick up?");
            int cardNumber = selectCard(discardPile);
            Card card = discardPile.getCard(cardNumber);
            paramList.add(card.getIndex());
        } else if(cardCode == HARUSPEX) {
        	System.out.println("Which card do you want to pick up?");
            int cardNumber = selectCard(drawPile);
            Card card = drawPile.getCard(cardNumber);
            paramList.add(card.getIndex());
        } else if(cardCode == CENTURIO) {
            // throw battle die;
            player.throwDice(battleDice);
            display.showText("Do you want to use extra die?(y/n)");
            String a = input.nextLine();
            if(a.matches("^[\\s]*[Yy].*")) {
            	int dieNumber = selectDie(actionDice);
            	paramList.add(actionDice[dieNumber].getFace());
            }
        } else if(cardCode == MACHINA || cardCode == CONSILIARIUS) {        	
        	Deck playerField = field.getPlayerField(player);
        	int diceDisc;
        	boolean[] diceDiscFlag = new boolean[Field.SIZE];
        	for(int i = 0; i < diceDiscFlag.length; i++) {
            	diceDiscFlag[i] = false;
            }
        	
        	int type;
        	if(cardCode == MACHINA) {
        	    display.showText("All building cards are picked up");
        	    type = Card.BUILDING;
        	} else {
        	    display.showText("All character cards are picked up");
        	    type = Card.CHARACTER;
        	}
        	
        	Card card;
        	for(int i = 1; i <= Field.SIZE; i++) {
        		card = playerField.getCard(i); //card that will be placed
        		if(card != null && card.getType() == type ) {
        			paramList.add(card.getIndex()); //add card code
        			display.showText("Where do you want to place " + card.getName());
        			diceDisc = selectDiceDisc();
        			//check that the dice disc is valid (not occupied by character card and not already used)
        			if(playerField.getCard(diceDisc) == null || //dice Disc is empty
        			   (playerField.getCard(diceDisc) != null && 
        			    playerField.getCard(diceDisc).getType() != type) //dice Disc is not a character card
        			   || !diceDiscFlag[diceDisc-1] ) { //dice disc has not been used.
        				paramList.add(diceDisc); //add which dice disc the card will be placed
        				diceDiscFlag[diceDisc-1] = true;
        			}
        		}
        	}
        
        } else if(cardCode == SENATOR || cardCode == ARCHITECTUS ) {
        	String a = "y";
        	Deck playerHand = player.getHand();
        	int cardNumber, diceDisc;
        	
        	String type;
        	if(cardCode == SENATOR) {
        		type = "Character";
        	} else {
        		type = "Building";
        	}
        	
        	if(!playerHand.isEmpty()) {
        	    // loop until player decide not to place more card
	        	while (a.matches("^[\\s]*[Yy].*")) {
	        	    display.showText("What "+ type +" card to do want to place: ");
	            	cardNumber = selectCard(playerHand);
	            	paramList.add(playerHand.getCard(cardNumber).getIndex());
	              
	            	display.showText("Which dice disc do you want to place the card next to: ");
	            	diceDisc = selectDiceDisc();
	            	paramList.add(diceDisc);
	              
	            	display.showText("Do you want to put another "+ type + " card ?(y/n)");
	                a = "";
	            	while (! a.matches("^[\\s]*[YyNn].*")) {
	            	    a = input.nextLine();
	            	}
	        	}  
        	}
        } else if(cardCode == PRAETORIANUS || cardCode == SICARIUS || 
        		  cardCode == GLADIATOR || cardCode == NERO ) {
            if (cardCode == PRAETORIANUS) {
                display.showText("Block a dice disc");
            } else if (cardCode == SICARIUS) {
                display.showText("Destroy a character card of the corresponding dice disc");
            } else if (cardCode == GLADIATOR) {
                display.showText("Return a character card of the orresponding dice disc\n " +
            					   "back to opponent's hand");
            } else if (cardCode == NERO) {
                display.showText("Destroy a building card of the corresponding dice disc");
            }
            paramList.add(selectDiceDisc());
        } else if (cardCode == ONAGER || cardCode == VELITES ) {
        	String type;
        	if (cardCode == ONAGER) {
        		type = "Character";
        	} else {
        		type = "Building";
        	}
        	display.showText("Attack a "+ type +" card of the corresponding dice disc");
        	paramList.add(selectDiceDisc());
        	player.throwDice(battleDice);

        } else if(cardCode == CONSUL) {
            display.showText("To increase or decrease a die, ");
        	paramList.add(actionDice[selectDie(actionDice)].getFace());
        	
        	display.showText("Do you want to increase or decrease? (i/d)");
            String a = input.nextLine();
            a = input.nextLine();
            if(a.matches("^[\\s]*[Ii].*")) {
                paramList.add(INCREMENT);
            } else {
            	paramList.add(DECREMENT);
            }
        } else if(cardCode == MERCATOR) {
            display.showText("The opponent has " + opponent.getVictoryPoint());
            display.showText("How many victory points do you want to buy? " +
            		"(Enter number greater than 1):");
            paramList.add(input.nextInt());
        } else if(cardCode == FORUM) {
            display.showText("Forum needs second die to determine how many victory points" +
        			"player will receive");
            display.showText("Die selected must not be the same as the previous die.");
        	int dieNumber = selectDie(actionDice);
        	paramList.add(actionDice[dieNumber].getFace());
            
            // check if the player has a templum or not
            // and if there is, then ask the player to choose the third dice
            if( checkTemplum(activatedCardPosition, game) ) {
                display.showText("Do you want to activate Templum? (y/n)");
                String a = input.nextLine();
                if(a.matches("^[\\s]*[Yy].*")) {
                	// SWITCH TO AUTO selection of the third die
                	int dieNumber2 = Dice.NO_DIE;
                	for (int i = 0; i < actionDice.length; i++){
                		if (!actionDice[i].isUsed() && i != dieNumber){
                			dieNumber2 = i;
                		}
                	}
                	// check if the third die is valid or not
                	if(dieNumber2 == Dice.NO_DIE) {
                		System.out.println("You do not have more actionDie left.");
                	} else {
                		paramList.add(actionDice[dieNumber2].getFace());
                	}
                }
            }
            
    	} else if (cardCode == LEGIONARIUS){
    		player.throwDice(battleDice);
    	} else { // tribunus plebis, essedum, legat, turris, mercatus, templum, basilica
            paramList.add(0);
        }
		
		//convert array list to array
		parameter = new int[paramList.size()];
		for(int i = 0; i < paramList.size(); i++) {
			parameter[i] = paramList.get(i);
		}
		return parameter;
	}

	/**
	 * Check if there is a templum adjacent to forum or not
	 * @param forumPosition 
	 * @param game
	 * @return true if there is a templum adjacent to forum
	 */
	private boolean checkTemplum(int forumPosition, Game game) {
		int left = forumPosition - 1;
		int right = forumPosition + 1;
		boolean found = false;
		Field field = game.getField();
		Player player = game.getCurrentPlayer();

		found = checkAdjacent(game, field, player, left)
				|| checkAdjacent(game, field, player, right);
		return found;
	}

	/**
	 * Check adjacent side
	 * @param game
	 * @param field
	 * @param player
	 * @param forumSide - side that will be check
	 * @return true there is a templum in the forumSide
	 */
	private boolean checkAdjacent(Game game, Field field, Player player,
			int forumSide) {
		boolean found = false;
		if (forumSide >= 1 && forumSide <= 6) {
			if (!field.isEmpty(player.getId(), forumSide)) {
				Card temp = field.getCard(player.getId(), forumSide);
				if (temp.getName().equals("Templum")) {
					found = true;
				}
			}
		}
		return found;
	}
}
