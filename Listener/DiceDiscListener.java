package Listener;

import GUI.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 * Dice Disc GUI Listener
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class DiceDiscListener implements MouseListener {

    private JLabel[] discLabel;
    private Control control;
    
    public DiceDiscListener(DiceDiscGUI diceDisc, Control control) {
        this.discLabel = diceDisc.getDiceDisc();
        this.control = control;
    }
    
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < discLabel.length; i++) {
            if(e.getSource() == discLabel[i]) {
                control.discClick(i+1);
            }
        }
    }

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

}
