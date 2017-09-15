package playingCards;


import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Gladiator extends Card {

    private static final String name = "Gladiator";
    private static final int cost = 6;    
    private static final int defense = 5;
    private static final int activatedDice = 1;
    private static final String ability = "An opponent's face-up character card (chosen\n" +
    									  "by the player whose turn it is) must be \n" +
    									  "returned to the opponent's hand.";
    private static final int type = Card.CHARACTER;

    public Gladiator() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.GLADIATOR);
    }
    
    /**
     * args contains 1 integer. This integer is the dice disc that the card is to be used on 
     */
    @Override
    public boolean activate(Game game, int[] args) {
        Field field = game.getField();
        Player opponent = game.getOpponent();
        int diceDisc = args[0];
        boolean success = false;
        
        if (!field.isEmpty(opponent.getId(), diceDisc) &&
            field.getCard(opponent.getId(), diceDisc).getType() == Card.CHARACTER) {
            //put back to opponent hand
            field.removeCard(opponent.getId(), diceDisc, opponent.getHand());
            success = true;
        }
        
        return success;
    }

}
