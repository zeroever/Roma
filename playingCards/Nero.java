package playingCards;
import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;

public class Nero extends Card {

    private static final String name = "Nero";
    private static final int cost = 8;    
    private static final int defense = 9;
    private static final int activatedDice = 1;
    private static String ability = "destroys any face-up opposing building card. The \n" +
    								"destroyed card and Nero are both discarded. ";
    private static final int type = Card.CHARACTER;
    
    public Nero() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.NERO);
    }
    
    /**
     * The args[] array should contain at least one integer. 
     * The first integer will contain the value of the supplementary dice disc to be used. 
     */
    @Override
    public boolean activate(Game game, int[] args) {
        
    	Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        int diceDisc = args[0];
        boolean success;
        
        
        success = activateNero(field, opponent, discardPile, diceDisc);
        
        if(success) {
        	remove(this, field, currentPlayer, discardPile);
        }
        return success;
        
    }
    
    public boolean activate(Game game, Scaenicus scaenicus, int[] args) {
    	Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        int diceDisc = args[0];
        boolean success;
        
        
        success = activateNero(field, opponent, discardPile, diceDisc);
        
        if(success) {
        	remove(scaenicus, field, currentPlayer, discardPile);
        }
        return success;
    }
    
    private boolean activateNero(Field field, Player opponent, Deck discardPile, int diceDisc) {
		boolean success;
		if ( diceDisc < 1 || diceDisc > Field.SIZE ||
             field.isEmpty(opponent.getId(), diceDisc) ||
             field.getCard(opponent.getId(), diceDisc).getType() != Card.BUILDING) {
             success = false;
         } else {
        	 field.removeCard(opponent.getId(), diceDisc, discardPile);
             
        	 
             success = true;
         }
		return success;
	}
    //nero are the same in tester setfaceupcards
    private void remove(Card card, Field field, Player currentPlayer, Deck discardPile) {
		for(int i = 1; i <= Field.SIZE; i++) {
		    if(field.getCard(currentPlayer.getId(), i) == card) {
		        field.removeCard(currentPlayer.getId(), i, discardPile);
		    }
		}
	}
}
