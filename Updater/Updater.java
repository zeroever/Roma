package Updater;

import GUI.*;
import Logic.*;
import javax.swing.*;

/**
 * Main Updater class
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class Updater {
	
	private DiceUpdater diceUpdater;
	private StatusUpdater statusUpdater;
	private StockPileUpdater stockPileUpdater;
	private HandUpdater handUpdater;
	private FieldUpdater fieldUpdater;
	private Game game;
	private JButton startEndButton;
	private JLabel discardPileLabel;

	public Updater(MainWin window, Game game, Control control) {
	    startEndButton = window.getStartEndButton();
		this.game = game;
	    diceUpdater = new DiceUpdater(window.getDiceLabel(), game.getActionDice());
        statusUpdater = new StatusUpdater(window.getStatusLabel(), 
                game.getCurrentPlayer(), game.getOpponent());
        stockPileUpdater = new StockPileUpdater(window.getStockPileLabel(), game);
        handUpdater = new HandUpdater(window.getHand(), game.getCurrentPlayer(), 
                game.getOpponent(), control);
        fieldUpdater = new FieldUpdater(window.getField(), game.getField());
        discardPileLabel = window.getDiscardPile();
	}
	
	public void resetButton() {
	    startEndButton.setText("Start Game");
	}
	
	private void updateDiscardPile() {
	    Deck discardPile = game.getDiscardPile();
	    //get the latest card in pile
	    if(discardPile.getNumOfCards() > 0) {
	        Card card = discardPile.getCard(discardPile.getNumOfCards());
	        discardPileLabel.setIcon(new ImageIcon(getClass().getResource(
	                "/images/"+ card.getName().toLowerCase() + ".jpg")));
	    }
	}

	/**
	 * Update the GUI to have the same value as the game
	 */
    public void updateGame() {
        diceUpdater.update(game.getCurrentPlayer().getId());
        statusUpdater.update();
        stockPileUpdater.update();
        handUpdater.update();
        fieldUpdater.update();
        updateDiscardPile();
    }
}
