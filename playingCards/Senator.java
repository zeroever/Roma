package playingCards;

import Logic.*;
import tests.main.*;

public class Senator extends Card{
	
	private static final String name = "Senator";
    private static final int cost = 3;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "enables the player to lay as many character\n" +
    									  "cards as they wish free of charge. The player\n" +
    									  "is allowed to cover any cards.";

    private static final int type = Card.CHARACTER;
	private static final int NOT_FOUND = 0;
    
	public Senator() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.SENATOR);
	}
	
	/**
     * The args[] array carries pairs of commands for the selections. 
     * ie. {LEGAT,5,TRIBUNIS_PLEBIS,2} - Then a LEGAT from the hand is placed 
     * on disk 5, and a TRIBUNIS_PLEBIS is placed on disk 2. 
     */
	public boolean activate(Game game, int[] args){
		Field field = game.getField();
		Player currentPlayer = game.getCurrentPlayer();
		Deck playerHand = currentPlayer.getHand();
		
		int cardCode, diceDisc, cardPosition;
		boolean success = false;
		
		for(int i = 0; i < args.length; i++) {
			cardCode = args[i++];
			diceDisc = args[i];
			
			//find the card in player's hand
			cardPosition = findCard(playerHand, cardCode);
			
			//place the card on the field
			if (cardPosition != NOT_FOUND && 
			    playerHand.getCard(cardPosition).getType() == Card.CHARACTER){
				
				Card temp = playerHand.pickCard(cardPosition);
				field.layCardOnField(currentPlayer.getId(), temp ,diceDisc, game.getDiscardPile());
				success = true;
			}
		}
		
		return success;
	}

	private int findCard(Deck playerHand, int cardCode) {
		int cardPosition = NOT_FOUND;
		int numCards = playerHand.getNumOfCards();
		for(int j = 1; j <= numCards; j++) {
			Card card = playerHand.getCard(j);
			if(card != null && card.getIndex() == cardCode) {
				cardPosition = j; //position found
			}
		}
		return cardPosition;
	}
}
