package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Mercatus extends Card {

    private static final String name = "Mercatus";
    private static final int cost = 6;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "The player gets 1 victory point for every \n" +
                                          "face-up Forum that the opponent has.";

    private static final int type = Card.BUILDING;
    
    public Mercatus() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.MERCATUS);
    }
    
    /**
     * no args
     */
    @Override
    public boolean activate(Game game, int[] args) {
        boolean success = true;
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Field field = game.getField();
        int vp = 0;
        Card temp;
        
        for(int i = 1; i <= Field.SIZE; i++) {
            temp = field.getCard(opponent.getId(), i);
            if(temp != null && temp.getName().equals("Forum") ) {
                vp++;
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
        
        //get vp from stockpile
        currentPlayer.modifyVictoryPoint(vp);
        
        //System.out.println("The opponent has " + vp + " Forum");
        game.display.showText(String.format("You gained %d victory points", vp));
        return success;
    }

}
