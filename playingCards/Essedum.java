package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;
public class Essedum extends Card implements Cloneable {
	
	private static final String name = "Essedum";
    private static final int cost = 6;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "The defence value of the opponent's face-up \n" +
    									  "cards is reduced by 2. ";

    private static final int type = Card.CHARACTER;
    
	public Essedum() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.ESSEDUM);
	}
	
	/**
	 * no args
	 */
	public boolean activate(Game game, int[] args){
		boolean success = true;
		Field field = game.getField();
		Player opponent = game.getOpponent();
		Card card;
		for (int i = 1; i <= Field.SIZE; i++){
			card = field.getCard(opponent.getId(), i);
			if (card != null) {
				if (card.getDefense() >= 2){
					card.modifyAdditionalDefense(-2);
				} else {
					card.modifyAdditionalDefense(-card.getDefense());
				}
			}
		}
		return success;
	}
}
