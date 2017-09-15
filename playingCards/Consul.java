package playingCards;

import Logic.Card;
import Logic.Dice;
import Logic.Game;
import tests.main.*;
public class Consul extends Card {

    private final int INCREMENT = 1;
    private final int DECREMENT = 0;
    
    private static final String name = "Consul";
    private static final int cost = 3;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "The score on an action die which has not yet\n" +
    									  "been used can be increased or decreased by 1\n" +
    									  "points";
    private static final int type = Card.CHARACTER;
    
    public Consul() {
        super(name, cost, defense, activatedDice, ability, type, CardCode.CONSUL);
    }

    /**
     * args[0] = action die VALUE
     * args[1] = The second integer supplied, determines whether to increase or decrease the value of action die by 1. 
     * 			 0 - decreases and 1 - increases.
     */
    @Override
    public boolean activate(Game game, int[] args) {
        
        Dice[] actionDice = game.getActionDice();
        int action = args[1]; 
        int diceChoice = findDie(actionDice, args[0]); 
        boolean success = false;
        
        if(diceChoice != Dice.NO_DIE && !(actionDice[diceChoice].getFace() == 6 && action == INCREMENT) &&
           !(actionDice[diceChoice].getFace() == 1 && action == DECREMENT)) {
        	if(action == INCREMENT ) {
                actionDice[diceChoice].setFace(actionDice[diceChoice].getFace()+1);
                System.out.println(actionDice[diceChoice].getFace());
            } else { //decrement
                actionDice[diceChoice].setFace(actionDice[diceChoice].getFace()-1);
            }
        	success = true; 
        } else {
        	success = false;
        }
        
        return success;
    }
    
    private int findDie(Dice[] actionDice, int diceValue) {
		int i = 0;
		boolean found = false;
		int dieNumber = Dice.NO_DIE;
		while ( i< actionDice.length && !found) {
			if (!actionDice[i].isUsed() && actionDice[i].getFace() == diceValue) {
				found = true;
				dieNumber = i;
			}
			i++;
		}
		return dieNumber;
	}

}
