package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import Logic.Field;

/**
 * Dice Disc GUI
 * It displays the dice disc
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class DiceDiscGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel[] diceDisc;
	
	public DiceDiscGUI() {
		
		diceDisc = new JLabel[Field.SIZE];
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		setBackground(new Color(214,231, 247));
		
		for(int i = 0; i < diceDisc.length-1; i++) {
			add(Box.createRigidArea(new Dimension(10,0)));
			diceDisc[i] = new JLabel(new ImageIcon(getClass().getResource("/images/"+ (i+1) +".gif")));
			add(diceDisc[i]);
			add(Box.createRigidArea(new Dimension(15,0)));
		}
		
		//bribe disc
		add(Box.createRigidArea(new Dimension(10,0)));
		diceDisc[6]= new JLabel(new ImageIcon(getClass().getResource("/images/bribe.gif")));
		add(diceDisc[6]);
		add(Box.createRigidArea(new Dimension(15,0)));
	}
	
	public JLabel[] getDiceDisc() {
		return diceDisc;
	}

	/*
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DiceDiscGUI diceDiscGUI = new DiceDiscGUI();
        
        frame.add(diceDiscGUI);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

	}
	*/

}
