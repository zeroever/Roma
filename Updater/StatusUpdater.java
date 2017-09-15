package Updater;

import javax.swing.JLabel;

import GUI.StatusGUI;
import Logic.Player;

/**
 * Status GUI Updater
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class StatusUpdater {

	private StatusGUI[] status;
	private Player playerA;
	private Player playerB;
	
	public StatusUpdater(StatusGUI[] status, Player playerA, Player playerB){
		this.status = status;
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
	public void update() {
		JLabel moneyLabel = status[playerA.getId()].getMoneyValueLabel();
		int money = playerA.getMoney();
		moneyLabel.setText(String.valueOf(money));
		
		moneyLabel = status[playerB.getId()].getMoneyValueLabel();
		money = playerB.getMoney();
		moneyLabel.setText(String.valueOf(money));
		
		JLabel victoryPointLabel = status[playerA.getId()].getVictoryPointValueLabel();
		int victoryPoint = playerA.getVictoryPoint();
		victoryPointLabel.setText(String.valueOf(victoryPoint));
		
		victoryPointLabel = status[playerB.getId()].getVictoryPointValueLabel();
		victoryPoint = playerB.getVictoryPoint();
		victoryPointLabel.setText(String.valueOf(victoryPoint));
	}
}
