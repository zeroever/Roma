package playingCards;

import Logic.Card;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class TribunusPlebis extends Card{
	
	private static final String name = "Tribunus Plebis";
    private static final int cost = 5;    
    private static final int defense = 5;
    private static final int activatedDice = 1;
    private static final String ability = "The player gets 1 victory point from their \n" +
    									  "opponent.";
    
    private static final int type = Card.CHARACTER;
    
	public TribunusPlebis() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.TRIBUNUS_PLEBIS);
	}
	
	/**
	 * no args
	 */
	public boolean activate(Game game, int[] args) {
		Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        currentPlayer.modifyVictoryPoint(1);
        opponent.modifyVictoryPoint(-1);
        return true;
	}
}
