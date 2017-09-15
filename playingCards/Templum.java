package playingCards;

import Logic.Card;
import Logic.Dice;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Templum extends Card {

    private static final String name = "Templum";
    private static final int cost = 2;    
    private static final int defense = 2;
    private static final int activatedDice = 0;
    private static final String ability = "If a Forum is activated (it must lie directly \n" +
                         				  "next to the basilica), the third action die can\n" +
                         				  "be used to determine the number of additional \n" +
                         				  "victory points which the player gets from the \n" +
                         				  "general stock pile. The action dice must not \n" +
                         				  "yet have been used in this go. The Templum \n" +
                         				  "itself is not activated seperately.";

    private static final int type = Card.BUILDING;
    
    public Templum() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.TEMPLUM);
    }
    
    /**
     * args[0] specifies the value of the dice to be used to determine the number of victory points received.
     * if specified args[1] is the value of an additional dice to be used for Templum. (see reconditions)
     */
    @Override
    public boolean activate(Game game, int[] args) {
		boolean success= true;
        Player player = game.getCurrentPlayer();
        Dice[] actionDice = game.getActionDice();
        int vp = args[1];
        
        //find the third die and set it to used
        //search the second die
		for(int i = 0; i < actionDice.length; i++) {
			if(!actionDice[i].isUsed() && actionDice[i].getFace() == args[1]) {
				actionDice[i].setUsed(true);
			}
		}
		
		//if the stockpile has less vp than what the player should get
        //then set the stockpile vp to zero
        //but the player still get the missing vp
        if(game.getVictoryPoint() < vp) {
        	game.modifyVictoryPoint(-game.getVictoryPoint());
        } else {
        	game.modifyVictoryPoint(-vp);
        }
		
        player.modifyVictoryPoint(vp);
        return success;
    }
    
}
