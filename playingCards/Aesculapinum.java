package playingCards;


import Logic.Card;
import Logic.Deck;
import Logic.Game;
import Logic.Player;
import tests.main.*;

public class Aesculapinum extends Card implements Cloneable {

    private static final String name = "Aesculapinum";
    private static final int cost = 5;    
    private static final int defense = 2;
    private static final int activatedDice = 1;
    private static final String ability = "The temple of Asculapius (the God of healing\n" +
                                          "enables the player to pick up any character\n " +
                                          "card from the discard pile and add it to their\n" +
                                          "hand.";

    private static final int type = Card.BUILDING;
    
    public Aesculapinum() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.AESCULAPINUM);
    }
    
    /**
     * args[0] = cardCode of the card that the user want
     */
    @Override
    public boolean activate(Game game, int[] args){
        Player currentPlayer = game.getCurrentPlayer();
        Deck discardPile = game.getDiscardPile();
        Card card = null;
        boolean found = false;
        boolean success = false;
        int position = 0;
        
        //find the card in the discard pile
        int numCards = discardPile.getNumOfCards();
        for(int i = 1; !found && i <= numCards; i++) {
            card = discardPile.getCard(i);
            if(card != null && card.getIndex() == args[0] && card.getType() == Card.CHARACTER) {
                found = true;
                position = i;
            }
        }
        
        if(found) {
            //pick the card from the discard pile
            card = discardPile.pickCard(position);
            //put the card into hand
            currentPlayer.getHand().putCardIn(card);
            success = true;
        } 
        return success;
    }
}
