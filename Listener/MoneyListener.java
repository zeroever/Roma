package Listener;

import GUI.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Money Disc GUI Listener
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class MoneyListener implements MouseListener {

    private Control control;
    
    public MoneyListener(Control control) {
        this.control = control;
    }
    
    public void mouseClicked(MouseEvent e) {
        control.moneyClick();
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
