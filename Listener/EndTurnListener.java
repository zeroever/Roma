package Listener;

import GUI.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

/**
 * End turn/Star Game Button Listener
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class EndTurnListener implements ActionListener {

    private Control control;
    
    public EndTurnListener(Control control) {
        this.control = control;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "End Turn") {
            control.endTurn();
        } else if (e.getActionCommand() == "Start Game") {
            JButton button = (JButton)e.getSource();
            button.setText("End Turn");
            control.start();
        }
        
    }

}
