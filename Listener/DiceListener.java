package Listener;

import GUI.*;
import Logic.*;
import java.awt.event.*;
import javax.swing.JLabel;

/**
 * Dice GUI Listener
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class DiceListener implements MouseListener {

    private JLabel[] diceLabel;
    private Control control;
    
    public DiceListener(JLabel[] label, Control control) {
        this.diceLabel = label;
        this.control = control;
    }
    
    public void mouseClicked(MouseEvent e) {
        int dieNumber = Dice.NO_DIE;
        if(e.getSource() == diceLabel[0]) {
            dieNumber = 0;
        }else if(e.getSource() == diceLabel[1]) {
            dieNumber = 1;
        }else if(e.getSource() == diceLabel[2]) {
            dieNumber = 2;
        }
        //System.out.println(dieNumber);
        control.dieClick(dieNumber);
    }

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

}
