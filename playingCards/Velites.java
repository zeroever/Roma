package playingCards;

import Logic.*;
import tests.main.*;
public class Velites extends Card{
	
	private static final String name = "Velites";
    private static final int cost = 5;    
    private static final int defense = 3;
    private static final int activatedDice = 1;
    private static final String ability = "attacks any opposing character card (does not\n" +
    									  "have to be directly opposite). The battle die\n" +
    									  "is thrown once.";

    private static final int type = Card.CHARACTER;
    
	public Velites() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.VELITES);
	}
	
	/**
	 * args[0] = dice disc that velites will attack
	 */
	public boolean activate(Game game, int[] args){
        Field field = game.getField();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        Dice battleDice = game.getBattleDice();
        int diceDisc = args[0]; 
        int attackPoint, defensePoint;
        Card opponentCard;
        boolean success;
      
        
        if (diceDisc < 1 || diceDisc > Field.SIZE ||
            field.isEmpty(opponent.getId(), diceDisc) ||
            field.getCard(opponent.getId(), diceDisc).getType() != Card.CHARACTER){
        	success = false;
        } else {
        	opponentCard = field.getCard(opponent.getId(), diceDisc);
            defensePoint = opponentCard.getDefense();
            
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
