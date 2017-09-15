package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Legat extends Card {
    
    private static final String name = "Legat";
    private static final int cost = 5;    
    private static final int defense = 2;
    private static final int activatedDice = 1;
    private static final String ability = "A player gets 1 victory point from the stockpile\n" +
                        				  "for every dice disc not occupied by the opponent";
    private static final int type = Card.CHARACTER;

    public Legat() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.LEGAT);
    }

    /**
     * no args
     */
    @Override
    public boolean activate(Game game, int[] args) {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Field field = game.getField();
        boolean success = true;
        
        int countEmpty = 0;
        
        for(int i = 1; i <= Field.SIZE; i++ ) {
            if(field.isEmpty(opponent.getId(), i)) {
                countEmpty++;
            }
        }
        int generalVP = game.getVictoryPoint();
        
        //if the stockpile has less vp than what the player should get
        //then set the stockpile vp to zero
        //but the player still get the missing vp
        if(generalVP < countEmpty) {
        	game.modifyVictoryPoint(-game.getVictoryPoint());
        } else {
        	game.modifyVictoryPoint(-countEmpty);
        }
        
        currentPlayer.modifyVictoryPoint(countEmpty);
        
        
        
        return success;
    }

}
