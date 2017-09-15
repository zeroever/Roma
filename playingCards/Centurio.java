package playingCards;

import Logic.*;
import tests.main.*;

public class Centurio extends Card {
	
	private static final String name = "Centurio";
    private static final int cost = 9;    
    private static final int defense = 5;
    private static final int activatedDice = 1;
    private static final String ability = "attacks the card directly opposite, whether it\n" +
    									  "is a chracter or building card. The value of an\n" +
    									  "unused dice can be added to the value of the\n" +
    									  "battle die (the action die is then counted as\n" +
    									  "used). This is decided after the battle die\n" +
    									  "has been thrown";
    private static final int type = Card.CHARACTER;
    
	public Centurio() {
		super(name, cost, defense, activatedDice, ability, type, CardCode.CENTURIO);
	}

	/**
	 * The args[] array contains either 1 or 0 integers. 
	 * If args[] contains 0 integers, the battle die is rolled and 
	 * the opposite card is attacked
     * If args[] contains 1 integer, the integer should contain the value of 
     * the supplementary die to be used. (The die will be used if it has been specified) 
	 */
	public boolean activate(Game game, int[] args) {
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Dice[] actionDice = game.getActionDice();
        int position = 0;
        boolean success = false;
        int dieChoice = Dice.NO_DIE;
        
        //find centurio position
        position = findPosition(this, field, currentPlayer, position);
        
        //require action die
        if(args.length != 0) {
            dieChoice = findSecondDie(args, actionDice, dieChoice);
        }
        
        
        success = activateCenturio(game, position, dieChoice);
		return success;
	}

	public boolean activate(Game game, Scaenicus scaenicus, int[] args) {
        Field field = game.getField();
        Player currentPlayer = game.getCurrentPlayer();
        Dice[] actionDice = game.getActionDice();
        int position = 0;
        boolean success;
        int dieChoice = Dice.NO_DIE;
        
        position = findPosition(scaenicus, field, currentPlayer, position);
        
        //require action die
        if(args[0] != 0) {
            dieChoice = findSecondDie(args, actionDice, dieChoice);
        }
        
        success = activateCenturio(game, position, dieChoice);
		return success;
	}
	
	private int findPosition(Card card, Field field, Player currentPlayer, int position) {
		for(int i = 1; i <= Field.SIZE; i++) {
            if(field.getCard(currentPlayer.getId(), i) == card) {
            	position = i;
            }
        }
		return position;
	}
	
	private int findSecondDie(int[] args, Dice[] actionDice, int dieChoice) {
		//find the second die
		for(int i = 0; i < actionDice.length; i++) {
		    if(!actionDice[i].isUsed() &&
		       actionDice[i].getFace() == args[0]) {
		        dieChoice = i;
		    }
		}
		return dieChoice;
	}

	private boolean activateCenturio(Game game, int position, int dieChoice) {
		Field field = game.getField();
        Player opponent = game.getOpponent();
        Deck discardPile = game.getDiscardPile();
        Dice[] actionDice = game.getActionDice();
        Dice battleDice = game.getBattleDice();
		
		int attackPoint;
		int defensePoint;
		Card opponentCard;
		boolean success = false;
		
		try {
			opponentCard = field.getCard(opponent.getId(), position);
			defensePoint = opponentCard.getDefense();
			attackPoint = battleDice.getFace();
			
			
			if(dieChoice != Dice.NO_DIE) {
			    attackPoint = attackPoint + actionDice[dieChoice].getFace();
			    actionDice[dieChoice].setUsed(true);
			}
			
			
			if (battle(attackPoint, defensePoint)){
			    field.removeCard(opponent.getId(), position, discardPile);
			}
			success = true;
		} catch (Exception e) {
			game.display.showText("You cannot attack the opponent");
			success = false;
		}
		return success;
	}

}
