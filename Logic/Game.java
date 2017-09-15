package Logic;

import TextUI.DisplayInterface;
import TextUI.InstructionInterface;

/**
 * Main Game Logic
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public class Game {
	
	public static final int TOTAL_ACTION_DICE = 3;
	public static final int START_STOCKPILE_VP = 16;	
	
	private Dice[] actionDice;
	private Dice battleDice;
	private Deck drawDeck;
	private Deck discardPile;
	private Player playerA;
	private Player playerB;
	private Player currentPlayer;
	private int victoryPoint; 
	private Field field;
	
	public DisplayInterface display;
	public InstructionInterface instruction;
	
	public Game() {
	    actionDice = new Dice[TOTAL_ACTION_DICE];
        for(int i = 0; i< actionDice.length; i++) {
            actionDice[i] = new Dice();
            actionDice[i].roll();
        }
        
        battleDice = new Dice();
        drawDeck = new Deck();
        discardPile = new Deck();
        playerA = new Player("A", Player.PLAYER1);
        playerB = new Player("B", Player.PLAYER2);
        currentPlayer = playerA;
        victoryPoint = START_STOCKPILE_VP; 
        field = new Field();
        
        drawDeck.initializeDrawDeck();
        drawDeck.shuffle();
	}
	
	/**
	 * starts the game
	 * - reset everything
	 * - do preparePhase
	 */
	public void start() {
	    for(int i = 0; i < actionDice.length; i++) {
            actionDice[i].setUsed(false);
        }
        discardPile = new Deck();
        field.resetField();
        playerA.restart();
        playerB.restart();
        currentPlayer = playerA;
        victoryPoint = START_STOCKPILE_VP; 
        
        drawDeck.initializeDrawDeck();
        drawDeck.shuffle();
        
        preparePhase();
	}
	
	/**
	 * Throw action dice and deduce victory point when the turn starts
	 */
	public void startTurn() {
	    String s = String.format("=============   %s's Turn   =============\n", currentPlayer.getName());
	    display.showText(s);
	    currentPlayer.throwDice(actionDice);
	    //deduce the victory point of the new player
        deduceVictoryPoint();
	    
	}
	
	/**
	 * Check if the game ended
	 * @return true - if the game end
	 *         false - if the game isn't end
	 */
	public boolean isEnd() {
		boolean end;
		
		if(playerA.getVictoryPoint() <= 0 ||
		   playerB.getVictoryPoint() <= 0 ||
		   this.victoryPoint == 0) {
			end = true;
		} else {
			end = false;
		}
		return end;
	}
	
	/**
	 * End of turn
	 * 
	 * reset the defense value of all card which might be affect by some cards
	 * unblock the dice disc which could be blocked by praetorianus
	 * reset the action dice
	 */
	public void switchTurn() {
		field.resetDefense(getOpponent().getId());
		field.resetBlock(currentPlayer.getId());
		if(currentPlayer == playerA) {
		    currentPlayer = playerB;
		} else {
		    currentPlayer = playerA;
		}
		//the turn is switched 
		
		//reset the dice
        for(int i = 0; i < actionDice.length; i++) {
            actionDice[i].setUsed(false);
        }
	}
	
	/**
	 * preparing phase
	 * 
	 * players draw 4 cards and switch 2 cards
	 * after than players lay the card. The card is faced down
	 * At the end of preparing phase, all card will be faced up
	 */
	public void preparePhase() {
        Deck tempA, tempB;
        playerA.drawNCard(drawDeck, 4);
        tempA = swapCard(playerA);
        playerB.drawNCard(drawDeck, 4);
        tempB = swapCard(playerB);
        
        display.showText("=====   Player A's Turn   =====");
        playerA.drawNCard(tempB, 2);
        display.showText("=====       Lay Card      =====");
        playerA.layCard(this, this.field);
        display.showText("=====   Player B's Turn   =====");
        playerB.drawNCard(tempA, 2);
        display.showText("=====       Lay Card      =====");
        playerB.layCard(this, this.field);
        
        field.revealCard();
        startTurn();
    }
	/**
	 * Swap cards during preparing phase
	 * @param player - current player
	 * @return 2 cards that player want to swap with other player
	 */
	private Deck swapCard(Player player){
        int choice = 0;
        Deck temp = new Deck();
        Deck playerHand = player.getHand();
        
        display.showText("");
        display.showText("=====   Player" + player.getName() + "'s Turn   =====");
        display.showText("Choose two cards to swap ");
        display.showText("Select the first card");
        choice = instruction.selectCard(playerHand);
        temp.putCardIn(player.getHand().pickCard(choice));
        
        display.showText("Select the second card");
        choice = instruction.selectCard(playerHand);
        temp.putCardIn(player.getHand().pickCard(choice));
        
        return temp;
    }
	
	/**
	 * deduce victory point
	 */
	public void deduceVictoryPoint() {
        int pointsDecrease = this.field.getEmptyDisc(currentPlayer.getId());
        
        if(currentPlayer.getVictoryPoint() < pointsDecrease) {
            pointsDecrease = currentPlayer.getVictoryPoint();
        }
        
        currentPlayer.modifyVictoryPoint(-pointsDecrease);
        modifyVictoryPoint(pointsDecrease);
    }
    
	/**
	 * Modify victory points
	 * @param points - points that will be ADD to current victory point
	 */
    public void modifyVictoryPoint(int points){
        this.victoryPoint = this.victoryPoint + points; 
    }
	
    /**
     * Get player for player id
     * @param id - 0 is player1 and 1 is player2
     * @return return player
     */
	public Player getPlayer(int id){
        Player player;
        if (id == Player.PLAYER1) {
            player = playerA;
        } else {
            player = playerB;
        }
        return player;
    }
	
	/**
	 * Get game field
	 * @return game field
	 */
	public Field getField() {
	    return field;
	}
	
	/**
	 * Get draw deck
	 * @return draw deck
	 */
	public Deck getDrawDeck() {
	    return drawDeck;
	}
	
	/**
	 * Get discard pile
	 * @return discard pile
	 */
	public Deck getDiscardPile() {
	    return discardPile;
	}
	
	/**
	 * Get general stock pile victory point
	 * @return general stock pile victory point
	 */
	public int getVictoryPoint() {
	    return victoryPoint;
	}
	
	/**
	 * Get action dice
	 * @return action dice
	 */
	public Dice[] getActionDice() {
	    return actionDice;
	}
	
	/**
	 * Get current player
	 * @return current player
	 */
	public Player getCurrentPlayer() {
	    return currentPlayer; 
	}
	
	/**
	 * Get opponent
	 * @return opponent player
	 */
	public Player getOpponent() {
	    Player opponent;
	    if(currentPlayer == playerA) {
	        opponent = playerB;
        } else {
            opponent = playerA;
        }
        return opponent;
	}
	
	/**
	 * Get battle die
	 * @return return battle die
	 */
	public Dice getBattleDice() {
		return battleDice;
	}

	/**
	 * lay card on to field
	 * @param diceDisc = dice disc destination
	 * @param cardNumber = position of card in hand
	 */
    public void layCard(int diceDisc, int cardNumber) {
        display.showText(">> Lay Card");
        currentPlayer.layCard(field,discardPile,cardNumber,diceDisc);
        display.showText(">> Successfully Taking Money");
    }

    /**
     * Take money
     * @param dieNumber - number of die that will be used to get money 
     * The number is between 0 and 2 
     */
    public void takeMoney(int dieNumber) {
        if(dieNumber != Dice.NO_DIE && !actionDice[dieNumber].isUsed()) {
        	actionDice[dieNumber].setUsed(true);
            currentPlayer.takeMoney(actionDice, dieNumber);
            display.showText(">> Successfully Taking Money");
        }
    }

    /**
     * Draw card
     * @param dieNumber - number of die that will be used to draw card 
     * The number is between 0 and 2 
     */
    public void drawCard(int dieNumber) {
        display.showText(">> Draw Card");
        if(dieNumber != Dice.NO_DIE && !actionDice[dieNumber].isUsed()) {
        	actionDice[dieNumber].setUsed(true);
            currentPlayer.drawCard(dieNumber, drawDeck, discardPile, actionDice);
            display.showText(">> Successfully Draw card");
        }
    }

    /**
     * if activate normal dice disc the die number can be any value
     * if activate bribe disc, must supply the correct die number of
     * die that will be used to activate
     * @param diceDisc
     * @param dieNumber
     */
    public void activateCard(int diceDisc, int dieNumber) {
        display.showText(">> Activate Card");
        if(diceDisc != Field.BRIBE) {
            //search whether the player has a valid die for activation
            for(int i = 0; i < actionDice.length; i++) {
                if(!actionDice[i].isUsed() && 
                   actionDice[i].getFace() == diceDisc) {
                    dieNumber = i;
                }
            }
        } 
        if(dieNumber != Dice.NO_DIE && !actionDice[dieNumber].isUsed()
           && field.getCard(currentPlayer.getId(), diceDisc) != null) {
        	actionDice[dieNumber].setUsed(true);
            Card card = field.getCard(currentPlayer.getId(), diceDisc);
            
            //ask the user some parameter
            int[] parameter = instruction.getActivateParameter(this, card.getIndex(), diceDisc);
            //activate card not success
            if( !currentPlayer.activateCard(this, diceDisc, dieNumber, parameter)) {
                actionDice[dieNumber].setUsed(false);
            }
        }
    }

    /**
     * Set input and output for the game
     * @param gameDisplay - output for the game = display destination (console or GUI)
     * @param inst - input for the game. This will ask used some questions and read in
     * user reaction
     */
	public void setIO(DisplayInterface gameDisplay, InstructionInterface inst) {
	    display = gameDisplay;
		instruction = inst;
		playerA.setIO(gameDisplay, inst);
		playerB.setIO(gameDisplay, inst);
	}
	
}