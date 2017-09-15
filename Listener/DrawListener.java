package Listener;

import GUI.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Draw Disc GUI Listener
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class DrawListener implements MouseListener {

    private Control control;
    
    public DrawListener(Control control) {
        this.control = control;
    }
    
    public void mouseClicked(MouseEvent e) {
        control.drawCardClick();
    }

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

}

