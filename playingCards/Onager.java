package playingCards;

import Logic.Card;
import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;

public class Onager extends Card {

    private static final String name = "Onager";
    private static final int cost = 5;    
    private static final int defense = 4;
    private static final int activatedDice = 1;
    private static final String ability = "This Roman catapult attacks any opposing building.\n" +
                                          "The battle die is thrown once." ;

    private static final int type = Card.BUILDING;
    
    public Onager() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.ONAGER);
    }
    
    /**
     * args[0] = dice disc that Onager will be attack
     */
    @Override
    public boolean activate(Game game, int[] args) {
        Field field = game.getField();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        Dice battleDice = game.getBattleDice();
        int diceDisc = args[0]; 
        int attackPoint, defensePoint;
        Card opponentCard;
        boolean success = true;
        
        //if not char and null select again
        //need to be non null to check type
        if (diceDisc < 1 || diceDisc > Field.SIZE ||
              field.isEmpty(opponent.getId(), diceDisc) ||
              field.getCard(opponent.getId(), diceDisc).getType() != Card.BUILDING){
            success = false;
        } else {
            opponentCard = field.getCard(opponent.getId(), diceDisc);
            defensePoint = opponentCard.getDefense();
            
            //currentPlayer.throwDice(battleDice);
            attackPoint = battleDice.getFace();
            game.display.showText(String.format("You tossed %d for battle die\n", attackPoint));
            game.display.showText(String.format("Your opponent has %d defense\n", defensePoint));
            
            if (battle(attackPoint, defensePoint)){
                field.removeCard(opponent.getId(), diceDisc, discardPile);
            }
            success = true;
        }
        return success;
    }

}
