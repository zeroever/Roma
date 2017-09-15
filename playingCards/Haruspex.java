package playingCards;


import Logic.Card;
import Logic.Deck;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Haruspex extends Card{
	
	private static final String name = "Haruspex";
    private static final int cost = 4;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "The player can choose any card from the pile \n" +
    									  "of face-down cards and add it to their hand. " +
    									  "Afterwards the pile is shuffled.";

    private static final int type = Card.CHARACTER;
    
	public Haruspex() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.HARUSPEX);
	}
	
	/**
     * args[0] = cardCode of the card that the user want
     */
	public boolean activate(Game game, int[] args){
		Player currentPlayer = game.getCurrentPlayer();
		Deck deck = game.getDrawDeck();
		Card card = null;
		boolean found = false;
		int position = 0;
		boolean success = false;
		
		if (deck.isEmpty()) {
			currentPlayer.buildDeck(deck, game.getDiscardPile());
		}
		
		//find the card in the discard pile
		int numCards = deck.getNumOfCards();
        for(int i = 1; !found && i <= numCards; i++) {
            card = deck.getCard(i);
            if(card != null && card.getIndex() == args[0]) {
                found = true;
                position = i;
            }
        }
		
        if(found) {
            card = deck.pickCard(position);
            currentPlayer.getHand().putCardIn(card);
            deck.shuffle();
            success = true;
        }
		
		return success;
	}
}
