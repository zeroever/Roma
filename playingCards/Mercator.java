package playingCards;

import Logic.Card;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Mercator extends Card{

    private static final String name = "Mercator";
    private static final int cost = 7;    
    private static final int defense = 2;
    private static final int activatedDice = 1;
    private static final String ability = "For 2 sestertii each, the player can buy 1 \n" +
    									  "victory point from their opponent. The opponent\n" +
    									  "gets the money. Can be used to purchase a \n" +
    									  "maximum of 3 VPs (for 6 gold) per activation.";
    private static final int type = Card.CHARACTER;

    public Mercator() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.MERCATOR);
    }

    /**
     * args[0] = amount of victory point the player needs to buy
     */
    @Override
    public boolean activate(Game game, int[] args) {
    	final int VP_COST = 2;
        
    	Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        int vp = args[0];
        boolean success = false;
        
        if(currentPlayer.getMoney() < (vp*VP_COST) ||
           opponent.getVictoryPoint() < vp) {
        	game.display.showText("You do not have enough money");
            success = false;
        } else {
            currentPlayer.modifyVictoryPoint(vp);
            currentPlayer.modifyMoney(vp * -VP_COST);
            opponent.modifyVictoryPoint(-vp);
            opponent.modifyMoney(vp * VP_COST);
            success = true;
        }
        return success;
    }
}
