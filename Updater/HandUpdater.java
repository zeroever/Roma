package Updater;

import java.util.ArrayList;
import javax.swing.*;

import GUI.*;
import Listener.HandListener;
import Logic.Deck;
import Logic.Player;

/**
 * Hand GUI Updater
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class HandUpdater {
	
	private HandGUI[] hand;
	private Player playerA;
	private Player playerB;
	private Control control;
	
	public HandUpdater(HandGUI[] hand, Player playerA, Player playerB, Control control) {
		this.hand = hand;
		this.playerA = playerA;
		this.playerB = playerB;
		this.control = control;
	}
	
	public void update() {
	    updatePicture(playerA);
		updatePicture(playerB);
		
		this.hand[0].paint();
		this.hand[1].paint();
	}

    private void updatePicture(Player player) {
        ArrayList<JLabel> cardsInHand = hand[player.getId()].getCardsInHand();
        Deck playerHand = player.getHand();
		cardsInHand.clear();
		for (int i = 0; i < playerHand.getNumOfCards(); i++) {
			String name = playerHand.getCard(i+1).getName().toLowerCase();
			JLabel card = new JLabel(new ImageIcon(getClass().getResource("/images/"+name+".jpg")));
			card.addMouseListener(new HandListener(hand, control));
			cardsInHand.add(card);
		}
    }
}
