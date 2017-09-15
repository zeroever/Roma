package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import tests.main.*;
public class Turris extends Card {
	
	private static final String name = "Turris";
    private static final int cost = 6;    
    private static final int defense = 6;
    private static final int activatedDice = 0;
    private static final String ability = "As long as turris is face-up, the defence value\n" +
    									  "of all the player's other other face-up cards \n" +
    									  "increases by 1 .";
    
    private static final int type = Card.BUILDING;
	
	public Turris() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.TURRIS);
	}
	
	/**
	 * no args
	 */
	@Override
	public boolean activate(Game game, int[] args) {
		return true;
	}
	
	/**
	 * Set the effect of turris to other cards on the field
	 * @param id - player id to indicates which player field will be checked
	 * @param numOfTurris - number of turris on the field
	 * @param field - card field
	 */
	public void activate(int id, int numOfTurris, Field field) {
		
		for(int i = 1; i <= Field.SIZE; i++) {
            if(field.getCard(id, i) != null ) {
            	if (!field.getCard(id,i).getName().equals("Turris") ){
            		field.getCard(id,i).setAdditionalDefense(numOfTurris);
            	} else if(field.getCard(id,i).getName().equals("Turris") ) {
            		field.getCard(id,i).setAdditionalDefense(numOfTurris-1);
            	}
            }
        }

	}
	
	/**
	 * When the turris is removed from the field
	 * @param id player id to indicates which player field will be checked
	 * @param field - card field
	 */
	public void deactivate(int id, Field field) {
		for(int i = 1; i <= 6; i++) {
            if(field.getCard(id, i) != null && field.getCard(id, i) != this) {
            	field.getCard(id,i).modifyAdditionalDefense(-1);
            }
        }

	}

}
