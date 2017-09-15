package Updater;

import Logic.*;
import GUI.*;
import javax.swing.*;

/**
 * Field GUI Updater
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class FieldUpdater {

    private static final int PLAYER_A = 0;
    private static final int PLAYER_B = 1;
    private Field field;
    private JLabel[] p1Field;
    private JLabel[] p2Field;
    
    public FieldUpdater(FieldGUI[] fieldGui, Field field){
        this.field = field;
        p1Field = fieldGui[0].getFieldLabel();
        p2Field = fieldGui[1].getFieldLabel();
    }
    
    public void update() {
        updatePicture(p1Field, PLAYER_A);
        updatePicture(p2Field, PLAYER_B);
    }

    private void updatePicture(JLabel[] LabelField, int playerId) {
        Card card;
        for(int i = 0; i < LabelField.length; i++) {
            card = field.getCard(playerId, i+1);
            if( card != null) {
                LabelField[i].setIcon(new ImageIcon(getClass().getResource(
                        "/images/"+ card.getName().toLowerCase() + ".jpg")));
            } else {
                LabelField[i].setIcon(new ImageIcon(getClass().getResource("/images/contorno.gif")));
            }
        }
    }
    
}