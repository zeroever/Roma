package GUI;

import javax.swing.*;

import Logic.Field;

import java.awt.*;

/**
 * Field GUI
 * It displays player's cards on field
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class FieldGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] field;
	
	public FieldGUI() {
		field = new JLabel[Field.SIZE];
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		setBackground(new Color(214,231, 247));
		for(int i = 0; i < field.length; i++) {
			field[i] = new JLabel(new ImageIcon(getClass().getResource("/images/contorno.gif")));
			add(field[i]);
			add(Box.createRigidArea(new Dimension(5,0)));
		}
		
	}
	
	public JLabel[] getFieldLabel() {
		return field;
	}
	
	/*
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        FieldGUI fieldGUI = new FieldGUI();
        
        frame.add(fieldGUI);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }*/
}
