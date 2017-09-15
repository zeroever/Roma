package Listener;

import GUI.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 * FieldGUI Listener
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class FieldListener implements MouseListener {

    private Control control;
    private JLabel[] p1field;
    private JLabel[] p2field;
    
    
    public FieldListener(Control control, FieldGUI[] field) {
        this.control = control;
        this.p1field = field[0].getFieldLabel();
        this.p2field = field[1].getFieldLabel();
    }
    
    public void mouseClicked(MouseEvent e) {
        int playerId = 0;
        int diceDisc = 0;
        //search p1Hand
        for(int i = 0; i < p1field.length; i++) {
            if(e.getSource() == p1field[i]) {
                playerId = 0;
                diceDisc = i;
            }
        }
        //search p2Hand
        for(int i = 0; i < p2field.length; i++) {
            if(e.getSource() == p2field[i]) {
                playerId = 1;
                diceDisc = i;
            }
        }
        control.fieldClick(playerId, diceDisc+1);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

}

