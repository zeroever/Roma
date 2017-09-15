package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Praetorianus extends Card {

	private static final String name = "Praetorianus";
    private static final int cost = 4;    
    private static final int defense = 4;
    private static final int activatedDice = 1;
    private static final String ability = "Any of the opponent's dice disc can be blocked\n" +
    									  "for one go.";
    
    private static final int type = Card.CHARACTER;
	
	public Praetorianus() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.PRAETORIANUS);
	}
	
	/**
	 * args[0] is the dice disc which will be "blocked".
	 */
	@Override
	public boolean activate(Game game, int[] args) {
		Field field = game.getField();
		Player opponent = game.getOpponent();

		field.setBlock(opponent.getId(), args[0]);
		return true;
	}
}
