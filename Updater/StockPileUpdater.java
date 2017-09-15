package Updater;

import javax.swing.JLabel;
import Logic.*;
import GUI.StockPileGUI;

/**
 * Stock pile GUI Updater
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class StockPileUpdater {
	
	private StockPileGUI stockPile;
	private Game game;
	
	public StockPileUpdater(StockPileGUI stockPile, Game game){
		this.stockPile = stockPile;
		this.game = game;
	}
	
	public void update() {
		JLabel victoryPointLabel = stockPile.getVictoryPointLabel();
		victoryPointLabel.setText(String.valueOf(game.getVictoryPoint()));
	}
}
