package playingCards;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Machina extends Card {

    private static final String name = "Machina";
    private static final int cost = 4;    
    private static final int defense = 4;
    private static final int activatedDice = 1;
    private static final String ability = "The player picks up their building cards and \n" +
    									  "can then lay them again on any dice disc. \n" +
                                          "Character card can be covered";
    private static final int type = Card.BUILDING;
	private static final int NOT_FOUND = 0;

    public Machina() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.MACHINA);
    }
    
    /**
     * The args[] array carries pairs of commands for the selections. 
     * ie. {LEGAT,5,TRIBUNIS_PLEBIS,2} - Then a LEGAT from the hand is placed 
     * on disk 5, and a TRIBUNIS_PLEBIS is placed on disk 2. 
     */
    @Override
    public boolean activate(Game game, int[] args) {
    	boolean success = true;
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Deck playerField = field.getPlayerField(currentPlayer);
        Deck discardPile = game.getDiscardPile();
        int cardCode, cardPosition, diceDisc;
        Deck pickedUpCards = new Deck();
        int j = 1;
        
        for(int i = 0; i < args.length; i = i+2) {
			cardCode = args[i];
			
			//find the card in player's field
			cardPosition = findCard(playerField, cardCode);
			field.removeCard(currentPlayer.getId(), cardPosition, pickedUpCards);

		}
        int numCards = pickedUpCards.getNumOfCards();
        for(int i = 0; i < numCards; i++) {
	        //place card on field
			Card card = pickedUpCards.pickCard(1);
			
			diceDisc = args[j];
			j = j+2;
			
			field.layCardOnField(currentPlayer.getId(), card ,diceDisc, discardPile);
        }
        return success;
    }
    
    private int findCard(Deck deck, int cardCode) {
		int cardPosition = NOT_FOUND;
		int numCards = deck.getNumOfCards();
		for(int j = 1; j <= numCards; j++) {
			Card card = deck.getCard(j);
			if(card != null && card.getIndex() == cardCode) {
				cardPosition = j; //position found
			}
		}
		return cardPosition;
	}

}
