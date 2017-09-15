package GUI;

import static tests.main.CardCode.*;
import Logic.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Logic.Card;
import Logic.Deck;
import Logic.Dice;
import Logic.Game;
import TextUI.InstructionInterface;

public class GuiInstruction implements InstructionInterface {

    private static final Integer INCREMENT = 1;
    private static final Integer DECREMENT = 0;
    private JFrame parentWindow;
    
    public GuiInstruction(JFrame window) {
        this.parentWindow = window;
    }
    
    /**
	 * ask player to select a card
	 * This method will call selectCard(Deck deck, String question)
	 * with the question = "Select a card"
	 * 
	 * @return number of card starting from 1
	 */
    public int selectCard(Deck deck) {
        return selectCard(deck, "Select a card");
    }
    
    /**
     * ask player to select a card
     * @param deck
     * @param question - question that will be asked
     * @return integer value starting from 1 represent the position of card
     */
    public int selectCard(Deck deck, String question) {
        int cardNumber = 0;
        
        Object[] possibilities = new Object[deck.getNumOfCards()];
        for(int i = 1; i <= deck.getNumOfCards(); i++) {
        	possibilities[i-1] = deck.getCard(i).getName();
        }
        if(possibilities.length > 0) {
	        String s = (String)JOptionPane.showInputDialog(
	                            parentWindow,
	                            question,
	                            "Card selection",
	                            JOptionPane.PLAIN_MESSAGE,
	                            null,
	                            possibilities,
	                            possibilities[0]);
	        
	        for(int i = 1; i <= deck.getNumOfCards(); i++) {
	        	if(s.equals(deck.getCard(i).getName())) {
	        		cardNumber = i;
	        	}
	        }
        }
        return cardNumber;
    }
    
    /**
	 * ask player to select a dice disc
	 * 
	 * This method will call selectDiceDisc(String question)
	 * with the question = "Select a dice disc"
	 * @return integer value between 1-7
	 */
    public int selectDiceDisc() {
        int position = 0;
        position = selectDiceDisc("Select a dice disc");
        return position;
    }
    
    /**
     * ask player to select a dice disc
     * 
     * @return integer value between 1-7
     */
    public int selectDiceDisc(String question) {
        int position = 0;
        Object[] possibilities = {"1", "2", "3","4","5","6","7"};
        String s = (String)JOptionPane.showInputDialog(
                            parentWindow,
                            question,
                            "Dice Disc selection",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            possibilities[0]);
        position = Integer.parseInt(s);
        return position;
    }
    
    /**
	 * ask player to toss again player can decide not to
	 * 
	 * @return player decision 1.Yes 2.No
	 */
    public boolean tossAgain() {
    	int n = confirmDialog("You tossed triple value. Do you want to toss again?", 
    	        "Do you want to toss again?");
        return ( n == JOptionPane.YES_OPTION );
    }

    /**
     * Asking yes/no question
     * @param question
     * @param title
     * @return integer value of JOptionPane.YES_OPTION or JOptionPane.NO_OPTION.
     */
    private int confirmDialog(String question, String title) {
        int n = JOptionPane.showConfirmDialog(
    		    parentWindow,
    		    question,
    		    title,
    		    JOptionPane.YES_NO_OPTION);
        return n;
    }

    /**
	 * ask player to select action die
	 * 
	 * This method will call selectDie(Dice[] actionDice, String question)
	 * with question = "Select a die. The number shows the die value"
	 * @param actionDice
	 * @return integer value between 0-2
	 */
    public int selectDie(Dice[] actionDice) {
        int choice = Dice.NO_DIE;
        choice = selectDie(actionDice, "Select a die. The number shows the die value");
        return choice;
    }
    
    /**
     * ask player to select action die
     * @param actionDice
     * @param question - question to be asked
     * @return integer value between 0-2
     */
    public int selectDie(Dice[] actionDice, String question) {
        //count unused dice
        int choice = Dice.NO_DIE;
        int countDice = 0;
        for(int i = 0; i < actionDice.length; i++) {
            if(!actionDice[i].isUsed()) {
                countDice++;
            }
        }
        //set the option
        Object[] possibilities = new Object[countDice];
        int j = 0;
        for(int i = 0; i < actionDice.length; i++) {
            if(!actionDice[i].isUsed()) {
                possibilities[j++] = String.format("%d", actionDice[i].getFace());
            }
        }
        //if there are at least one unused action dice, then ask the player
        if(possibilities.length > 0) {
            String s = (String)JOptionPane.showInputDialog(
                                parentWindow,
                                question,
                                "Die selection",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                possibilities,
                                possibilities[0]);
            //search for die number
            for(int i = 0; i < actionDice.length; i++) {
                if(Integer.parseInt(s) == actionDice[i].getFace()) {
                    choice = i;
                }
            }
        }
        return choice;
    }

    public int[] getActivateParameter(Game game, int cardCode,
            int activatedCardPosition) {
        Player player = game.getCurrentPlayer();
        Field field = game.getField();

        int[] parameter = null;

        if (cardCode == SCAENICUS) {
            int diceDisc = selectDiceDisc();
            // find a copied card
            Card card = field.getCard(player.getId(), diceDisc);
            if(card != null) {
                // get activation parameter of the copied card
                int[] copyParameter = getParameter(card.getIndex(), game,
                        activatedCardPosition);
    
                parameter = new int[copyParameter.length + 1];
                parameter[0] = diceDisc;
                for (int i = 0; i < copyParameter.length; i++) {
                    parameter[i + 1] = copyParameter[i];
                }
            } else {
                parameter = new int[1];
                parameter[0] = 0;
            }
        } else {
            parameter = getParameter(cardCode, game, activatedCardPosition);
        }

        return parameter;
    }

    private int[] getParameter(int cardCode, Game game, int activatedCardPosition) {
        Player player = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Deck drawPile = game.getDrawDeck();
        Deck discardPile = game.getDiscardPile();
        Dice battleDice = game.getBattleDice();
        Dice[] actionDice = game.getActionDice();
        Field field = game.getField();
        int[] parameter;
        ArrayList<Integer> paramList = new ArrayList<Integer>();
        
        if(cardCode == AESCULAPINUM) {
            int cardNumber = selectCard(discardPile, "Activating Aesculapinum. " +
            		"Select one card from the discard pile");
            Card card = discardPile.getCard(cardNumber);
            paramList.add(card.getIndex());
        } else if(cardCode == HARUSPEX) {
            int cardNumber = selectCard(drawPile, "Activating Haruspex. " +
            		"Select one card from the draw pile");
            Card card = drawPile.getCard(cardNumber);
            paramList.add(card.getIndex());
        } else if(cardCode == CENTURIO) {
            // throw battle die;
            player.throwDice(battleDice);
            int n = confirmDialog(String.format("You tossed %d for a battle die. " +
            		"Do you want to use action die?", battleDice.getFace()), 
            		"Do you want to use action die?");
            
            if(n == JOptionPane.YES_OPTION) {
                int dieNumber = selectDie(actionDice, "Activating Centurio. " +
                		"Select additional action die for activation");
                paramList.add(actionDice[dieNumber].getFace());
            }
        } else if(cardCode == MACHINA || cardCode == CONSILIARIUS ) {
            Deck playerField = field.getPlayerField(player);
            int diceDisc;
            boolean[] diceDiscFlag = new boolean[Field.SIZE];
            for(int i = 0; i < diceDiscFlag.length; i++) {
                diceDiscFlag[i] = false;
            }
            
            int type;
            if(cardCode == MACHINA) {
                type = Card.BUILDING;
            } else {
                type = Card.CHARACTER;
            }
            
            Card card;
            for(int i = 1; i <= Field.SIZE; i++) {
                card = playerField.getCard(i); //card that will be placed
                if(card != null && card.getType() == type ) {
                    paramList.add(card.getIndex()); //add card code
                    diceDisc = selectDiceDisc(String.format("Where do you want to place %s\n", card.getName()));
                    
                    //check that the dice disc is valid (not occupied by character card and not already used)
                    if(playerField.getCard(diceDisc) == null || //dice Disc is empty
                       (playerField.getCard(diceDisc) != null && 
                        playerField.getCard(diceDisc).getType() != type) //dice Disc is not a character card
                       || !diceDiscFlag[diceDisc-1] ) { //dice disc has not been used.
                        paramList.add(diceDisc); //add which dice disc the card will be placed
                        diceDiscFlag[diceDisc-1] = true;
                    }
                }
            }
        } else if(cardCode == SENATOR || cardCode == ARCHITECTUS ) {
            int n = JOptionPane.YES_OPTION;
            Deck playerHand = player.getHand();
            int cardNumber, diceDisc;
            
            if(!playerHand.isEmpty() ) {
                // loop until player decide not to place more card
                while (n == JOptionPane.YES_OPTION) {
                    cardNumber = selectCard(playerHand, String.format("What card to do want to place: \n"));
                    paramList.add(playerHand.getCard(cardNumber).getIndex());
                    
                    diceDisc = selectDiceDisc(String.format("Which dice disc do you want to place a card next to: \n"));
                    paramList.add(diceDisc);
                    
                    n = confirmDialog(String.format("You tossed %d for a battle die. " +
                    		"Do you want to use action die?", battleDice.getFace()), 
                    		"Do you want to use action die?");
                }  
            }
        } else if (cardCode == PRAETORIANUS) {
            paramList.add(selectDiceDisc("Activating Praetorianus. Select dice disc to be blocked"));
        } else if (cardCode == SICARIUS) {
            paramList.add(selectDiceDisc("Activating Sicarius. Destroy a character card of the corresponding dice disc"));
        } else if (cardCode == GLADIATOR) {
            paramList.add(selectDiceDisc("Return a character card of the orresponding dice disc\n " +
                    "back to opponent's hand"));
        } else if (cardCode == NERO) {
            paramList.add(selectDiceDisc("Destroy a building card of the corresponding dice disc"));
        } else if (cardCode == ONAGER || cardCode == VELITES ) {
            player.throwDice(battleDice);
            paramList.add(selectDiceDisc("Select dice disc to attack"));
        } else if(cardCode == CONSUL) {
            int dieNumber= selectDie(actionDice);
            if(dieNumber != Dice.NO_DIE ) {
                paramList.add(actionDice[dieNumber].getFace());
            } else {
                paramList.add(0);
            }
            Object[] options = {"Increase","Decrease"};
            int n = JOptionPane.showOptionDialog(parentWindow,
                    "Do you want to increase or decrease?",
                    "Consul Activation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
            
            if(n == JOptionPane.YES_OPTION) {
                paramList.add(INCREMENT);
            } else {
                paramList.add(DECREMENT);
            }
        } else if(cardCode == MERCATOR) {
            String s = (String)JOptionPane.showInputDialog(
                    parentWindow,
                    String.format("The opponent has %d\nHow many victory points do you " +
                            "want to buy? (Enter number greater than 1):\n", opponent.getVictoryPoint()),
                    "Mercator Activation",
                    JOptionPane.PLAIN_MESSAGE);
            
            paramList.add(Integer.parseInt(s));
        } else if(cardCode == FORUM) {
            int dieNumber = selectDie(actionDice, String.format(
                    "Forum needs second die to determine how many \n" +
                    "victory points player will receive\n" +
                    "Die selected must not be the same as the previous die.\n"));
            paramList.add(actionDice[dieNumber].getFace());
            
            // check if the player has a templum or not
            // and if there is, then ask the player to choose the third dice
            if( checkTemplum(activatedCardPosition, game) ) {
                int n = confirmDialog("Do you want to activate Templum?", "Forum Activation");
                if(n == JOptionPane.YES_OPTION) {
                    //SWITCH TO AUTO
                    int dieNumber2 = Dice.NO_DIE;
                    for (int i = 0; i < actionDice.length; i++){
                        if (!actionDice[i].isUsed() && i != dieNumber){
                            dieNumber2 = i;
                        }
                    }
                    // check if the third die is valid or not
                    if(dieNumber2 != Dice.NO_DIE) {
                        paramList.add(actionDice[dieNumber2].getFace());
                    }
                }
            }
            
        } else if (cardCode == LEGIONARIUS){
            player.throwDice(battleDice);
        } else { //tribunus plebis, essedum, legat, turris, mercatus, templum, basilica
            paramList.add(0);
        }
        
        //convert array list to array
        parameter = new int[paramList.size()];
        for(int i = 0; i < paramList.size(); i++) {
            parameter[i] = paramList.get(i);
        }
        return parameter;
    }

    /**
     * Check if there is a templum adjacent to forum or not
     * @param forumPosition 
     * @param game
     * @return true if there is a templum adjacent to forum
     */
    private boolean checkTemplum(int forumPosition, Game game) {
        int left = forumPosition - 1;
        int right = forumPosition + 1;
        boolean found = false;
        Field field = game.getField();
        Player player = game.getCurrentPlayer();

        found = checkAdjacent(game, field, player, left)
                || checkAdjacent(game, field, player, right);
        return found;
    }

    /**
     * Check adjacent side
     * @param game
     * @param field
     * @param player
     * @param forumSide - side that will be check
     * @return true there is a templum in the forumSide
     */
    private boolean checkAdjacent(Game game, Field field, Player player,
            int forumSide) {
        boolean found = false;
        if (forumSide >= 1 && forumSide <= 6) {
            if (!field.isEmpty(player.getId(), forumSide)) {
                Card temp = field.getCard(player.getId(), forumSide);
                if (temp.getName().equals("Templum")) {
                    found = true;
                }
            }
        }
        return found;
    }

}
