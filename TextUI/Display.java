package TextUI;

import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;

public class Display {

    private Game game;
    
    public Display(Game game) {
        this.game = game;
    }
    
    /* (non-Javadoc)
     * @see TextUI.Display#showStatus()
     */
    public void showStatus() {
        Dice[] actionDice = game.getActionDice();
        Player currentPlayer = game.getCurrentPlayer();
        
        showField();
        
        System.out.printf("======    %s's Status   ======\n", currentPlayer.getName());
        System.out.println();
        System.out.print("     Action Dice: ");
        for(int i = 0; i < actionDice.length; i++) {
            if(!actionDice[i].isUsed()) {
                System.out.print(actionDice[i].getFace() + " ");
            }
        }
        System.out.println();
        System.out.printf("     Victory point: %2d                     \n",currentPlayer.getVictoryPoint());
        System.out.printf("     Money: %2d                             \n",currentPlayer.getMoney());
        System.out.println();
        System.out.println("==============================");
        currentPlayer.showHand();
    }
    
    /* (non-Javadoc)
     * @see TextUI.Display#showField()
     */
    public void showField() {
        Field field = game.getField();
        Player player1 = game.getPlayer(Player.PLAYER1);
        Player player2 = game.getPlayer(Player.PLAYER2);
        
        System.out.println("==============   Cards on field   ===================");
        System.out.println("Number in the brackets represent card's defense value");
        System.out.println();
        System.out.printf("%14s       Dice Disc       %s\n", player1.getName(), player2.getName());
        for(int i = 1; i <= Field.SIZE; i++) {
            
            if (field.getCard(player1.getId(), i) == null) {
                System.out.printf("%16s     ", "Empty");
            } else if (field.getCard(player1.getId(), i).isFaceUp()){
                System.out.printf("%16s (%2d)", field.getCard(player1.getId(), i).getName(),
                        field.getCard(player1.getId(), i).getDefense());
            } else {
                System.out.printf("%16s     ", "Face-down Card");
            }
            
            System.out.print("    " + i +"    ");
            
            if (field.getCard(player2.getId(), i) == null) {
                System.out.printf("     %s", "Empty");
            } else if (field.getCard(player2.getId(), i).isFaceUp()){
                System.out.printf("(%2d) %s", field.getCard(player2.getId(), i).getDefense(), 
                        field.getCard(player2.getId(), i).getName());
            } else {
                System.out.printf("     %s", "Face-down Card ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.printf("General Victory Stock Pile: %d\n", game.getVictoryPoint());
        System.out.printf("Draw Pile: %d\n", game.getDrawDeck().getNumOfCards());
        System.out.printf("Discard Pile: %d\n", game.getDiscardPile().getNumOfCards());
        System.out.println();
    }
    
    /* (non-Javadoc)
     * @see TextUI.Display#showText(java.lang.String)
     */
    public void showText(String text) {
        System.out.println(text);
    }

    public void showMainMenu() {
		System.out.println("Welcome to Roma");
		System.out.println("Please select your choices");
		System.out.println("1. Read How To Play");
		System.out.println("2. Start playing");
		System.out.println("3. Exit");
		System.out.print("Enter your choice (1-3): ");
	}
	
	public void showActionMenu() {
		System.out.println();
		System.out.println("Please select your choices");
		System.out.println("1. Lay card(s)");
		System.out.println("2. Take Money");
		System.out.println("3. Draw Card(s)");
		System.out.println("4. Activate Card");
		System.out.println("5. End Turn");
		System.out.print("Enter your choice (1-5): ");
	}
	public void showRules() {
		System.out.println();
		System.out.println("The players take it in turns to make a complete move. A move is made up of 3 phases, which are carried out in the following order:");
		System.out.println("Phase 1: Add up score for unoccupied dice discs");
		System.out.println("Phase 2: Throw the dice");
		System.out.println("Phase 3: Carry out the actions");
		System.out.println();	
		System.out.println("You could enter ? following with card name to get a detail description of the card");
		System.out.println("e.g. ?Forum ");
	}

}
