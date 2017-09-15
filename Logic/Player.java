package Logic;

import TextUI.DisplayInterface;
import TextUI.InstructionInterface;

/**
 * Player class - represents the player
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public class Player {
	
	public static final int STARTER_VP = 10;
	public static final int PLAYER1 = 0;
	public static final int PLAYER2 = 1;
	public static final int TOTAL_PLAYER = 2;
	
	private String name;
	private int id;
	private int victoryPoint;
	private int money;
	private Deck hand;
	private InstructionInterface instruction;
    private DisplayInterface display;
	
    /**
     * Create a new player
     * @param playerName - player name
     * @param number - player id. The number 0 for player1 and number 1 for player2
     */
	public Player(String playerName, int number) {
	    display = null;
	    instruction = null;
		name = playerName;
		id = number;
		victoryPoint = STARTER_VP;
		money = 0;
		hand = new Deck();
	}
	
	/**
	 * 
	 * @return player name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @return player id
	 *  0 for player 1
	 *  1 for player 2
	 */
	public int getId() {
		return this.id;
	}
	
	/**
     * Modify victory points
     * @param points - points that will be ADD to current victory point
     */
	public void modifyVictoryPoint(int points) {
	    this.victoryPoint = this.victoryPoint + points;
	    if(this.victoryPoint < 0) {
	        this.victoryPoint = 0;
	    }
	}
	
	/**
	 * Get current victory point of player
	 * @return victory point
	 */
	public int getVictoryPoint() {
		return this.victoryPoint;
	}
	
	/**
	 * Taking money
	 * @param actionDice - array of dice class
	 * @param dieChoice - die that will be used to take money
	 * The number is between 0 and 2. It correspons to array index of action dice
	 */
	public void takeMoney(Dice[] actionDice, int dieChoice) {
	    
        this.money = this.money + actionDice[dieChoice].getFace();
	}
	
	/**
	 * Get the current money that player has
	 * @return money of player
	 */
	public int getMoney(){
		return this.money;
	}
	
	/**
	 * Modify money
	 * @param money - money that will be ADD to current money 
	 */
	public void modifyMoney(int money) {
	    this.money += money;
	}
	
	/**
	 * Lay card on field
	 * if the player covers card the covered card will be
	 * discarded to discard pile 
	 * @param field
	 * @param discardPile
	 */
	public void layCard(Field field, Deck discardPile, int index, int diceDisc){
		Card card = hand.getCard(index);
		if(card != null) {
    	    if(money < card.getCost()) {
    		    display.showText("You do not have enough money to lay this card");
    		} else {
    		    //if(hand.getCard(index) != null) {
    		        //Card temp = hand.pickCard(index);
    			card = hand.pickCard(index);
    		        this.money -= card.getCost();
    			    field.layCardOnField(this.getId(), card, diceDisc, discardPile);
    		    //}
    		}
		}
	}
	
	
	/**
     * Lay card during preparing phase
     * @param game
     * @param field = card field
     * 
     */
    public void layCard(Game game, Field field){
        int position;
        int cardNumber;
        int numCard = 0;
        
        while(numCard < 4) {
            
            if(hand == null) {
                System.out.println("...");
            }
            
            if(instruction == null) {
                System.out.println("???");
            }
            cardNumber = instruction.selectCard(hand);

            //check invalid input
            position = instruction.selectDiceDisc();
            
            //player can't cover card
            while(!field.isEmpty(this.id, position)) {
                game.display.showText("There's a card there!!!");
                position = instruction.selectDiceDisc();
            }
            
            hand.getCard(cardNumber).setFaceUp(false);
            field.layCardOnField(this.getId(), hand.pickCard(cardNumber), position, null);
            numCard++;
            
            game.display.showField();
        }
            
    }
	
    /**
     * Draw card equals to a face of the action die. The player can only select
     * one card from the cards drawn and put it in the hand 
     * @param dieChoice - die that will be used to draw card (Number between 0 and 2)
     * @param deck - draw deck
     * @param discardPile - discard pile
     * @param actionDice - action dice
     */
	public void drawCard(int dieChoice, Deck deck,Deck discardPile, Dice[] actionDice) {
		
	    int numCardDraw = actionDice[dieChoice].getFace();
		actionDice[dieChoice].setUsed(true);
		
		//draw process
		Deck temp = new Deck(numCardDraw); 
		for (int i=0; i < numCardDraw; i++){
		   //check if the deck is empty
		   if (deck.isEmpty()){
		       buildDeck(deck,discardPile);
		   }
		   //draw to a temporary location
		   temp.putCardIn(deck.draw());
		}
		
		int cardNumber = instruction.selectCard(temp);
		
		Card newCard = temp.pickCard(cardNumber);
		
		//put one card in hand
		this.hand.putCardIn(newCard);
		
		display.showText("");
		display.showText("You chose " + newCard.getName());
		
		int numCardDiscard = temp.getNumOfCards();
		for(int i = 0; i < numCardDiscard; i++) {
			discardPile.putCardIn(temp.draw());
		}
		
	}
	
	/**
     * draw n card where n is the amount of cards
     * cards will automatically be put in player's hand
     * @param deck = draw deck
     * @param numCard = number of card you want to draw
     * 
     */
    public void drawNCard(Deck deck,int numCard){ 
        for (int i=0; i < numCard ; i++){
           //draw to a temporary location
           this.hand.putCardIn(deck.draw());
        }
    }
	
	/**
	 * show cards in player's hand
	 */
	public void showHand() {
		display.showText("Card(s) in your hand: ");
		display.listCard(hand);
	}
	
	/**
	 * player activate a card
	 * @param game
	 * @param diceDisc - dice disc that player want to activate a card
	 * @param dieNumber - die that will be used to activate card
	 * @param args - arguments for the card activation
	 * @return true if the card activation is a success
	 *         false if otherwise
	 */
    public boolean activateCard(Game game, int diceDisc, int dieNumber, int[] args){
    	Field field = game.getField();
    	Dice[] actionDice = game.getActionDice();
    	
        Card card = null;
        boolean success = false;  
        
        //dice disc is blocked
        if (field.isBlock(id, diceDisc) ) {
            success = false;
            display.showText("Dice disc is blocked");
        
        }else {
            card = field.getCard(this.id, diceDisc);
            //normal dice disc
            if(diceDisc != Field.BRIBE
               && actionDice[dieNumber].getFace() == diceDisc ) { 
               //die face must be the same as dice disc
                //if the card is forum activation dice will be 2
                if (card != null && card.getActivationDice() == 2){
                	//set the first die to use
                	actionDice[dieNumber].setUsed(true);
                	success = checkForumPrecondition(game, dieNumber, args, card);
                //activate other cards 
                } else if (card != null && card.getActivationDice() == 1 ) {
                    actionDice[dieNumber].setUsed(true);
                    display.showText("====== Activating " + card.getName() +" =====");
                    success = card.activate(game, args);
                    if(!success) {
                        actionDice[dieNumber].setUsed(false);
                    }
                } else {
                    success = false;
                }
            
            //bribe disc
            } else { 
            	if (this.money >= actionDice[dieNumber].getFace() ){
            		//set die to used
            		actionDice[dieNumber].setUsed(true);
                    modifyMoney(-actionDice[dieNumber].getFace());
                    //activate forum at bribe disc
                    if (card != null && card.getActivationDice() == 2){
                    	success = checkForumPrecondition(game, dieNumber, args, card);
                    } else { //activate other cards
                    	success = card.activate(game, args);
                    }
                    if(!success) {
                        modifyMoney(actionDice[dieNumber].getFace());
                        actionDice[dieNumber].setUsed(false);
                    }
                } else {
                    success = false;
                }
            }
            if(success) {
                display.showText("Successfully activated " + card.getName());
            } else {
                display.showText("Card activation failed");
            }
        }
        return success;

    }

    /**
     * Check and activate forum. Forum needs 2 dice for activation. Check that
     * there are two valid dice to activate forum
     * @param game
     * @param dieNumber - die used to activate forum
     * @param args - argument for activate forum
     * @param card - forum that will be activate
     * @return true if there are 2 valid die
     */
	private boolean checkForumPrecondition(Game game, int dieNumber, int[] args, Card card) {
		Dice[] actionDice = game.getActionDice();
	    boolean success = false; 
	    int dieNumber2 = Dice.NO_DIE;
	    
	    //check that there are valid second die
		if (!actionDice[0].isUsed() || !actionDice[1].isUsed() ||
		        !actionDice[2].isUsed()){
			//search the second die
			for(int i = 0; i < actionDice.length; i++) {
				if(!actionDice[i].isUsed() && actionDice[i].getFace() == args[0]) {
					dieNumber2 = i;
				}
			}
			//set the second die to used
			actionDice[dieNumber2].setUsed(true);
			display.showText("====== Activating " + card.getName() +" =====");
			success = card.activate(game, args);
			if (!success){
				actionDice[dieNumber2].setUsed(false);
				actionDice[dieNumber].setUsed(false);
			}
		
		//there is no valid second die, thus cannot activate forum
		} else {
		    actionDice[dieNumber].setUsed(false);
		}
		return success;
	}
	
	/**
	 * Player throws battle die
	 * @param battleDice
	 */
	public void throwDice(Dice battleDice) {
	    battleDice.roll();
	    display.showText("You toss " + battleDice.getFace());
        display.showText("");

	}
	
	/**
	 * Player throws action dice
	 * @param actionDice
	 */
	public void throwDice(Dice[] actionDice) {
		boolean tossAgain = true;
		
		for(int i = 0; i < actionDice.length; i++) {
			actionDice[i].roll();
		}
		
		//get triple, ask is the player wants to throw again
		while(actionDice[0].getFace() == actionDice[1].getFace() && 
		   actionDice[1].getFace() == actionDice[2].getFace() && tossAgain){
		    display.showText(String.format("You toss %d %d %d", actionDice[0].getFace(), 
		            actionDice[1].getFace(), actionDice[2].getFace()));
			tossAgain = instruction.tossAgain();
			if (tossAgain) {
				for(int i = 0; i < actionDice.length; i++) {
					actionDice[i].roll();
				}
			} 
		}
	}

	/**
	 * Get player hand
	 * @return a deck class that represents player hand
	 */
	public Deck getHand() {
		return this.hand;
	}
	
	/**
	 * The draw deck is empty Shuffle the discard pile to be used as a draw deck.
	 * @param drawDeck
	 * @param discardPile
	 */
	public void buildDeck(Deck drawDeck, Deck discardPile) {
		int discardCards = discardPile.getNumOfCards();
		for(int i = 0; i < discardCards; i++) {
			drawDeck.putCardIn(discardPile.draw());
		}
		drawDeck.shuffle();		
	}

	/**
     * Set input and output for player
     * @param gameDisplay - output for the game = display destination (console or GUI)
     * @param inst - input for the game. This will ask used some questions and read in
     * user reaction
     */
    public void setIO(DisplayInterface gameDisplay, InstructionInterface inst) {
        this.display = gameDisplay;
        this.instruction = inst;
        
    }

    /**
     * reset all the value
     */
    public void restart() {
        victoryPoint = STARTER_VP;
        money = 0;
        hand = new Deck();
    }
}
