package playingCards;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;

public class Architectus extends Card {
	
	private static final String name = "Architectus";
    private static final int cost = 3;    
    private static final int defense = 4;
    private static final int activatedDice = 1;
    private static final String ability = "enables the player to lay as many building\n" +
    									  "cards as they wish free of charge. The player\n" +
    									  "is allowed to cover any cards.";

    private static final int type = Card.CHARACTER;
    private static final int NOT_FOUND = 0;
    
	public Architectus() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.ARCHITECTUS);
	}
	
	/**
	 * The args[] array carries pairs of commands for the selections 
	 * for all the cards that should be placed. 
	 */
	public boolean activate(Game game, int[] args){
		Field field = game.getField();
		Player currentPlayer = game.getCurrentPlayer();
		Deck playerHand = currentPlayer.getHand();
		
		int cardCode, diceDisc;
		int cardPosition;
		boolean success = false;
		
		for(int i = 0; i < args.length; i++) {
			cardCode = args[i++];
			diceDisc = args[i];
			
			//find the card in player's hand
			cardPosition = findCard(playerHand, cardCode);
			
			//place the card on the field
			if (cardPosition != NOT_FOUND && 
			    playerHand.getCard(cardPosition).getType() == Card.BUILDING){
				
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
