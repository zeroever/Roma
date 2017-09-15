package playingCards;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Sicarius extends Card{

    private static final String name = "Sicarius";
    private static final int cost = 9;    
    private static final int defense = 2;
    private static final int activatedDice = 1;
    private static final String ability = "eliminates an opposing, face-up character card. \n" +
                                          "The opposing card and the Sicarius are both discarded.";
    private static final int type = Card.CHARACTER;
    
    public Sicarius() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.SICARIUS);
    }
    
    /**
	 * args[0] = dice disc that sicarius will destroy
	 */
    public boolean activate(Game game, int[] args) {
    	
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        int diceDisc = args[0];
        boolean success;
        
        
        success = activateSicarius(field, opponent, discardPile, diceDisc);
        
        if(success) {
        	remove(this, field, currentPlayer, discardPile);
        }
        return success;
    }
    
    /**
	 * args[0] = dice disc that sicarius will destroy
	 */
    public boolean activate(Game game, Scaenicus scaenicus, int[] args) {
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        int diceDisc = args[0];
        boolean success = false;
        
        success = activateSicarius(field, opponent, discardPile, diceDisc);
        
        if(success) {
        	remove(scaenicus, field, currentPlayer, discardPile);
        }
        return success;
    	
    }
    
    private boolean activateSicarius(Field field, Player opponent, Deck discardPile, int diceDisc) {
		boolean success;
		if ( diceDisc < 1 || diceDisc > Field.SIZE ||
             field.isEmpty(opponent.getId(), diceDisc) ||
             field.getCard(opponent.getId(), diceDisc).getType() != Card.CHARACTER) {
             success = false;
         } else {
        	 field.removeCard(opponent.getId(), diceDisc, discardPile);
             
        	 
             success = true;
         }
		return success;
	}
    
	private void remove(Card card, Field field, Player currentPlayer, Deck discardPile) {
		for(int i = 1; i <= Field.SIZE; i++) {
		    if(field.getCard(currentPlayer.getId(), i) == card) {
		        field.removeCard(currentPlayer.getId(), i, discardPile);
		    }
		}
	}
}
