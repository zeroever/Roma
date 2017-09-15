package playingCards;

import Logic.Card;
import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Legionarius extends Card {

    private static final String name = "Legionarius";
    private static final int cost = 4;    
    private static final int defense = 5;
    private static final int activatedDice = 1;
    private static final String ability = "Attacks the opponent's card which is directly\n" +
    									  "opposite, whether it is a character or a \n" +
    									  "building card.";
    private static final int type = Card.CHARACTER;

    public Legionarius() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.LEGIONARIUS);
    }
    
    /**
     * no args
     */
    @Override
    public boolean activate(Game game, int[] args) {
        boolean success = false;
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        int position = 0;
        
        //find Legionarius position
        position = findCard(this, field, currentPlayer);
        
        success = activateLegionarius(game, position);
        
        return success;
    }
    
    public boolean activate(Game game, Scaenicus scaenicus, int[] args) {
        boolean success = true;
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        int position = 0;
        
        position = findCard(scaenicus, field, currentPlayer);
        
        success = activateLegionarius(game, position);
        
        return success;
    }

	private boolean activateLegionarius(Game game, int position) {
	    Field field = game.getField();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        Dice battleDice = game.getBattleDice();
	    boolean success;
		int defensePoint;
		int attackPoint;
		Card opponentCard;
		try {
            opponentCard = field.getCard(opponent.getId(), position);
            defensePoint = opponentCard.getDefense();
            
            game.display.showText(String.format("You tossed %d for battle die\n", battleDice.getFace()));
            game.display.showText(String.format("Your opponent has %d defense\n", defensePoint));
            
            attackPoint = battleDice.getFace();
            
            if (battle(attackPoint, defensePoint)){
                field.removeCard(opponent.getId(), position, discardPile);
            }
            success = true;
        } catch (Exception e) {
            success = false;
            System.out.println("You cannot attack the opponent");
        }
		return success;
	}

	private int findCard(Card card, Field field, Player currentPlayer) {
		int position = 0;
		for(int i = 1; i <= Field.SIZE; i++ ) {
            if(field.getCard(currentPlayer.getId(), i) == card) {
                position = i;
            }
        }
		return position;
	}
}
