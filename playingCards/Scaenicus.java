package playingCards;

import Logic.Card;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import tests.main.*;

import java.util.*;
public class Scaenicus extends Card implements Cloneable {
	
	private static final String name = "Scaenicus";
    private static final int cost = 8;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "He performs no action of his own but can copy\n" +
    									  "the action of any of the player's own face-up\n" +
    									  "character cards, and the next time round that\n" +
    									  "of another.";
    private static final int type = Card.CHARACTER;
    private Card copied = null;
    
	public Scaenicus() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.SCAENICUS);
	}
	
	/**
	 *  args[0] contains the dice disc of the card to copy the action of
	 *	Any arguments after the first are passed to activate the other card
     *  eg activateCard(whereIAm, args[1..]); 
	 */
	public boolean activate(Game game, int[] args){
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        int position = 0;
        int diceDisc = args[0];
		boolean success = false;
        
		//find scaenicus position
		for(int i = 1; i <= Field.SIZE; i++) {
            if(field.getCard(currentPlayer.getId(), i) == this) {
            	position = i;
            }
        }
        
        if (diceDisc == position || diceDisc <=0 || diceDisc > Field.SIZE ||
        	  field.isEmpty(currentPlayer.getId(), diceDisc) || 
        	  (!field.isEmpty(currentPlayer.getId(), diceDisc) &&
        	  field.getCard(currentPlayer.getId(), diceDisc).getType() != Card.CHARACTER)){
        	success = false;
        } else {
        	copied = field.getCard(currentPlayer.getId(), diceDisc);
        	int[] parameter = Arrays.copyOfRange(args, 1, args.length);
        	if (copied.getName() == "Nero" ) {
        		Nero nero = (Nero) copied;
        		success = nero.activate(game, this, parameter); 
        	} else if (copied.getName() == "Sicarius") {
        		Sicarius sicarius = (Sicarius) copied;
        		success = sicarius.activate(game, this, parameter); 
        	} else if (copied.getName() == "Legionarius" ) {
        		Legionarius legionarius = (Legionarius) copied;
        		success = legionarius.activate(game, this, parameter); 
        	} else if (copied.getName() == "Centurio" ) {
        		Centurio centurio = (Centurio) copied;
        		success = centurio.activate(game, this, parameter); 
        	} else {
        		success = copied.activate(game, parameter);
        	}
        }
        return success;
	}
	
}