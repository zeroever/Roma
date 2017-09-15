package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;

public class Forum extends Card {

    private static final String name = "Forum";
    private static final int cost = 5;    
    private static final int defense = 5;
    private static final int activatedDice = 2;
    private static final String ability = "requires 2 action dice: one to activate the  \n" +
                                          "Forum and the other to determine how many \n" +
                                          "victory points the player receives.";

    private static final int type = Card.BUILDING;
    
    public Forum() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.FORUM);
    }
    
    /**
     * args[0] specifies the value of the dice to be used to determine the number 
     * of victory points received.
     * if specified args[1] is the value of an additional dice to be used for Templum.
     */
    @Override
    public boolean activate(Game game, int[] args) {
        boolean success = false;
        Player currentPlayer = game.getCurrentPlayer();
        Field field = game.getField();
        int vp = args[0];
        int forumPosition = 0;
        
        //if the stockpile has less vp than what the player should get
        //then set the stockpile vp to zero
        //but the player still get the missing vp
        if(game.getVictoryPoint() < vp) {
        	game.modifyVictoryPoint(-game.getVictoryPoint());
        } else {
        	game.modifyVictoryPoint(-vp);
        }
        currentPlayer.modifyVictoryPoint(vp);
            
        
        for(int i = 1; i <= Field.SIZE; i++) {
            if(this == field.getCard(currentPlayer.getId(), i)) {
                forumPosition = i;
            }
        }
        checkAdjacent(forumPosition, game, args);
        success = true;

        
        return success;
    }
    
    private void checkAdjacent(int forumPosition, Game game, int[] args ) {
        Field field = game.getField();
        Player player = game.getCurrentPlayer();
        
        int left = forumPosition - 1;
        int right = forumPosition + 1;
        
        activateAdjacent(game, field, player, left, args);
        
        activateAdjacent(game, field, player, right, args);
    }

	private void activateAdjacent(Game game, Field field, Player player, int forumSide, int[] args) {
		if(forumSide >= 1 && forumSide <= Field.SIZE) {
            if(!field.isEmpty(player.getId(), forumSide)) {
                Card temp = field.getCard(player.getId(), forumSide);
                if(temp.getName().equals("Basilica")) {
                    temp.activate(game, args);
                } else if(temp.getName().equals("Templum") && args.length > 1) {
                    temp.activate(game, args);
                }
            }
        }
	}
    
}
