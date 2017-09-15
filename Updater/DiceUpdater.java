package Updater;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Logic.Dice;

/**
 * Dice GUI Updater
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class DiceUpdater {

	private final int PLAYER_A = 0;
	private JLabel[] diceLabel;
	private Dice[] actionDice;
	
	public DiceUpdater(JLabel[] diceLabel,Dice[] actionDice) {
		this.diceLabel = diceLabel;
		this.actionDice = actionDice;
	}
	
	public void update(int playerId) {
		String colour = "";
		if(playerId == PLAYER_A) {
			colour = "r";
		} else {
			colour = "a";
		}
		for(int i = 0; i < diceLabel.length; i++) {
		    if(actionDice[i].isUsed()) {
		        diceLabel[i].setIcon(new ImageIcon(getClass().getResource(
                        "/images/d" + actionDice[i].getFace() + "-"+colour+"-used.gif")));
		    } else {
		        diceLabel[i].setIcon(new ImageIcon(getClass().getResource(
			        "/images/d" + actionDice[i].getFace() + "-"+colour+".gif")));
		    }
		}

	}
}
