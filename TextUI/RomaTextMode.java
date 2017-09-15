package TextUI;

import Logic.*;

/**
 * Text version of Roma game
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public class RomaTextMode {
	
	private final static int READ_RULES = 1;
	private final static int PLAY_GAME = 2;
	private final static int QUIT = 3;
	private final static int DEMO = 0;
	private final static int END_TURN = 5;
	
	public static final int TOTAL_ACTION = 5;
    public static final int LAY_CARD = 1;
    public static final int TAKE_MONEY = 2;
    public static final int DRAW_CARD = 3;
    public static final int ACTIVATE_CARD = 4;
	
	private Game game;
	private DisplayInterface display;
	private InstructionInterface instruction;
	private InputGetter input;
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
	    RomaTextMode rm = new RomaTextMode();
	    rm.run();
    }
	
	/**
	 * Running the roma game
	 */
	public void run() {
		game = new Game();
        display = new TextDisplay(game);
        input = new InputGetter(display);
        instruction = new TextInstruction(display, input);
        game.setIO(display, instruction);
		
		int choice = 0;
		
		while(choice != QUIT) {
			display.showMainMenu();
			choice = input.nextInt();
			if(choice == READ_RULES) {
				display.showRules();
			} else if(choice == PLAY_GAME) {
				startGame();
			/* EasterEgg */
			} else if (choice == DEMO) {
				Demo.run();
			/* EasterEgg */
			} else if (choice == QUIT){ //player quit the game
				display.showText("Good Bye");
			} else {
			    display.showText("Invalid input");
			}
			display.showText("");
		}

	}
	
	public void startGame() {
	    int actionChoice;
	    int choice;
	    //start the game
	    game.start();
	    
	    //check if the game ends?
	    while( !game.isEnd() ) {
	        display.showText("");
            display.showStatus();
	        display.showActionMenu();
	        actionChoice = input.nextInt();
	        if(actionChoice != END_TURN) {
	            if(actionChoice == LAY_CARD) {
	                display.showText("");
	                display.showText(">> Lay card");
	                //player has at least 1 card in hand
	                if(game.getCurrentPlayer().getHand().getNumOfCards() > 0) {
	                    int cardNumber = instruction.selectCard(game.getCurrentPlayer().getHand());
	                    display.showText("Which dice disc do you want to lay the card?");
	                    int diceDisc = instruction.selectDiceDisc();
	                    game.layCard(diceDisc, cardNumber);
	                }
	            } else if(actionChoice == TAKE_MONEY) {
	                display.showText("");
	                display.showText(">> Take Money");
                    choice = instruction.selectDie(game.getActionDice());                    
                    game.takeMoney(choice);
                } else if(actionChoice == DRAW_CARD) {
                    display.showText("");
                    display.showText(">> Draw Card(s)");
                    choice = instruction.selectDie(game.getActionDice()); 
                    game.drawCard(choice);
                } else if(actionChoice == ACTIVATE_CARD) {
                    display.showText("");
                    display.showText(">> Activate Card");
                    choice = instruction.selectDiceDisc();
                    int diceDisc = choice;
                    if(choice == Field.BRIBE) { 
                        //select a die which will be used to activate bribe disc 
                        choice = instruction.selectDie(game.getActionDice());
                    } 
                    game.activateCard(diceDisc, choice);
                }
	        } else {
	            game.switchTurn();
	            display.showText("Turn end");
	            display.showText("");
	            game.startTurn();
	        }
	        
        }
	    display.showText("Game Ends");
	    int vp1 = game.getPlayer(Player.PLAYER1).getVictoryPoint();
	    int vp2 = game.getPlayer(Player.PLAYER2).getVictoryPoint();
	    if(vp1 > vp2 ) {
	        display.showText("The Winner is " + game.getPlayer(Player.PLAYER1).getName());
	    } else if (vp1 < vp2) {
	        display.showText("The Winner is " +game.getPlayer(Player.PLAYER2).getName());
	    } else {
	        display.showText("The game is tie");
	    }
	}

	
}
