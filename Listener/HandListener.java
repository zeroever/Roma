package Listener;

import GUI.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * Hand GUI Listener
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class HandListener implements MouseListener {

    private Control control;
    private ArrayList<JLabel> p1Hand;
    private ArrayList<JLabel> p2Hand;
    
    public HandListener(HandGUI[] hand, Control control) {
        //this.updater = updater;
        this.control = control;
        p1Hand = hand[0].getCardsInHand();
        p2Hand = hand[1].getCardsInHand();
    }
    
    public void mouseClicked(MouseEvent e) {
        int playerId = 0;
        int cardPosition = 0;
        //search p1Hand
        for(int i = 0; i < p1Hand.size(); i++) {
            if(e.getSource() == p1Hand.get(i)) {
                playerId = 0;
                cardPosition = i;
            }
        }
        //search p2Hand
        for(int i = 0; i < p2Hand.size(); i++) {
            if(e.getSource() == p2Hand.get(i)) {
                playerId = 1;
                cardPosition = i;
            }
        }
        control.handClick(playerId, cardPosition+1);
    }

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

}
