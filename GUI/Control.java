package GUI;

import Logic.*;
import TextUI.DisplayInterface;
import Updater.*;
import javax.swing.*;

/**
 * Control Unit
 * 
 * The class linked the game Logic and GUI together
 * It decided which function in game logic to be called
 * At the end of each action, It updated GUI.
 * 
 * @author KaKong and JarupatJisarojito
 * Tute: Mon13Oboe
 */
public class Control {

    private DisplayInterface display;
    private Game game;
    private Updater updater;
    private JFrame frame;
    private int dieNumber;
    private int diceDisc;
    private int cardPosition;
    
    private boolean startGame;
    private boolean dieClick;
    private boolean diceDiscClick;
    private boolean draw;
    private boolean money;
    private boolean handClick;
    
    
    public Control(Game g, DisplayInterface d, JFrame f) {
        game = g;
        display = d;
        frame = f;
        updater = null;
        dieNumber = Dice.NO_DIE;
        
        startGame = false;
        dieClick = false;
        diceDiscClick = false;
        draw = false;
        money = false;
        handClick = false;
    }
    
    /**
     * When the start button is press. The game is started
     * 
     * If the user does not press this button, then when he
     * click the other component nothing happens
     */
    public void start() {
        startGame = true;
        dieNumber = Dice.NO_DIE;
        dieClick = false;
        diceDiscClick = false;
        draw = false;
        money = false;
        handClick = false;
        game.start();
        updater.updateGame();
    }
    
    /**
     * One of the die label is clicked
     * 
     * @param dieNumber - number between 0 and 2 
     * The number corresponded to the index of an array
     */
    public void dieClick(int dieNumber) {
        this.dieNumber = dieNumber;
        dieClick = true;
        checkAction();
    }

    /**
     * One of the dice disc is clicked
     * 
     * @param diceDisc - number between 1 and 7
     * The number corresponded to the number of dice disc
     */
    public void discClick(int diceDisc) {
        this.diceDisc = diceDisc;
        diceDiscClick = true;
        checkAction();
    }

    /**
     * draw card disc is clicked
     */
    public void drawCardClick() {
        draw = true;
        checkAction();
    }
    
    /**
     * money disc is clicked
     */
    public void moneyClick() {
        money = true;
        checkAction();
    }

    /**
     * End Turn button is click
     * 
     * Switch the turn
     * Clear game status screen
     * Set every flag to false
     * 
     * If the game is ended show a popup window indicated that 
     * the game has end and also show the winner
     */
    public void endTurn() {
        dieClick = false;
        diceDiscClick = false;
        draw = false;
        money = false;
        handClick = false;
        game.switchTurn();
        game.startTurn();
        updater.updateGame();
        if(game.isEnd()) {
            int vp1 = game.getPlayer(Player.PLAYER1).getVictoryPoint();
            int vp2 = game.getPlayer(Player.PLAYER2).getVictoryPoint();
            String message;
            if(vp1 > vp2 ) {
                message = "The Winner is " + game.getPlayer(Player.PLAYER1).getName();
            } else if (vp1 < vp2) {
                message = "The Winner is " +game.getPlayer(Player.PLAYER2).getName();
            } else {
                message = "The game is tie";
            }
            JOptionPane.showMessageDialog(frame,
                    message, 
                    "The game end",
                    JOptionPane.INFORMATION_MESSAGE);
            updater.resetButton();
        }
        display.clear();
    }

    /**
     * On of the card in hand is click
     * The card description is shown
     * @param playerId - player number. The number is 0 or 1
     * @param cardPosition - position of the card in player's hand. 
     * The number starts from 1.
     */
    public void handClick(int playerId, int cardPosition) {
        if(game.getCurrentPlayer().getId() == playerId) {
            handClick = true;
            this.cardPosition = cardPosition;
        }
        Card card = game.getPlayer(playerId).getHand().getCard(cardPosition);
        if(card != null) {
            String type;
            if(card.getType() == Card.CHARACTER) {
                type = "Character";
            } else {
                type = "Building";
            }
            String s = String.format("%-18s\nType:%-5s\nCost:%-10d  Defense:%2d\n%s",
                    card.getName(), type, card.getCost(), card.getDefense(), card.getAbility());
            display.showCard(s);
        }
        checkAction();
    }
    
    /**
     * One of the card on field is clicked
     * The card description is shown
     * @param playerId - player number. The number is 0 or 1
     * @param diceDisc - dice disc number
     */
    public void fieldClick(int playerId, int diceDisc) {
        Card card = game.getField().getCard(playerId, diceDisc);
        String type;
        if(card != null) {
            if(card.getType() == Card.CHARACTER) {
                type = "Character";
            } else {
                type = "Building";
            }
            String s = String.format("%-18s\nType:%-5s\nCost:%-10d   Defense:%2d\n%s",
                    card.getName(), type, card.getCost(), card.getDefense(), card.getAbility());
            display.showCard(s);
        }
    }

    /**
     * Check the possible action from the user action
     * 
     * clicking die && dice disc = card activation
     * clicking die && draw disc = draw card
     * clicking die && money disc = get money
     * clicking card in hand && dice disc = lay card
     * 
     * At then end, the updater is called to update GUI.
     */
    private void checkAction() {
        if(startGame && !game.isEnd()) {
            if(dieClick && diceDiscClick) {
                game.activateCard(diceDisc, dieNumber);
                //System.out.println("activate");
                dieClick = false;
                diceDiscClick = false;
            } else if(dieClick && draw) {
                //System.out.println("draw");
                game.drawCard(dieNumber);
                dieClick = false;
                draw = false;
            } else if(dieClick && money) {
                //System.out.println("money");
                game.takeMoney(dieNumber);
                dieClick = false;
                money = false;
            } else if(handClick && diceDiscClick) {
                //System.out.println("lay card");
                game.layCard(diceDisc, cardPosition);
                handClick = false;
                diceDiscClick = false;
            }
            updater.updateGame();
        }
    }
    
    public void setUpdater(Updater updater) {
        this.updater = updater;
    }

}
