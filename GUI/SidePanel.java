package GUI;

import java.awt.Dimension;
import javax.swing.*;

/**
 * Side panel
 * It displays game status, card description and how to play to game
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class SidePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea gameStatus;
    private JTextArea cardDescription;
    private JLabel gameGuide;
    
    public SidePanel() {
        gameStatus = new JTextArea();
        cardDescription = new JTextArea();
        gameGuide = new JLabel();
        JLabel statusLabel = new JLabel("Game Status");
        JLabel cardLabel = new JLabel("Card Description");
        JScrollPane scroller1 = new JScrollPane(gameStatus);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        gameStatus.setRows(13);
        gameStatus.setColumns(20);
        gameStatus.setEditable(false);
        gameStatus.setLineWrap(true);
        
        cardDescription.setRows(5);
        cardDescription.setColumns(25);
        cardDescription.setEditable(false);
        cardDescription.setLineWrap(true);
        
        scroller1.setPreferredSize(gameStatus.getPreferredSize());
        scroller1.setAlignmentX(LEFT_ALIGNMENT);
        cardDescription.setAlignmentX(LEFT_ALIGNMENT);
        
        gameGuide.setText("" +
        		"<html><body><font size=\"+1\">How to play the game?</font><br>" +
        		"<br> => Click a die and draw dice disc to draw cards" +
        		"<br> => Click a die and money dice disc to get money" +
        		"<br> => Click a die and dice disc to activate card" +
        		"<br> => Click a card in your hand and dice disc to lay card" +
        		"<br><br> Dice Dise 7 is a bribe disc." +
        		"<br> It is a special dice dice which you need to pay" +
        		"<br> money for activating the card placed at this disc");
        gameGuide.setSize(scroller1.getWidth(), 500);
        add(statusLabel);
        add(Box.createRigidArea(new Dimension(0,5)));
        //add(gameStatus);
        add(scroller1);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(cardLabel);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(cardDescription);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(gameGuide);
        add(Box.createRigidArea(new Dimension(0,50)));
    }
    
    public JTextArea getGameStatus() {
        return gameStatus;
    }
    
    public JTextArea getCardDescription() {
        return cardDescription;
    }
    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SidePanel side = new SidePanel();
        
        frame.add(side);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

    }*/
}
