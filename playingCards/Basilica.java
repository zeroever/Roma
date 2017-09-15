package playingCards;

import Logic.*;
import tests.main.*;

public class Basilica extends Card implements Cloneable {

    private static final String name = "Basilica";
    private static final int cost = 6;    
    private static final int defense = 5;
    private static final int activatedDice = 0;
    private static final String ability = "If a Forum is activated (it must lie directly\n" +
                         				  "next to the basilica), the player gets 2 more\n" +
                         				  "victory points. The Basilica itself is not\n" +
                         				  "activated.";

    private static final int type = Card.BUILDING;
    
    public Basilica() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.BASILICA);
    }
    
    /**
     * no args
     */
    @Override
    public boolean activate(Game game, int[] args) {
    	Player player = game.getCurrentPlayer();
    	game.display.showText("You gained 2 more victory points from Basilica");
    	
    	//if the stockpile has less vp than what the player should get
        //then set the stockpile vp to zero
        //but the player still get the missing vp
        if(game.getVictoryPoint() < 2) {
        	game.modifyVictoryPoint(-game.getVictoryPoint());
        } else {
        	game.modifyVictoryPoint(-2);
        }
        
        player.modifyVictoryPoint(2);
        
        return true;
    }

}
