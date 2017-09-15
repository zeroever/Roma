package GUI;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Hand GUI
 * It displays cards in player hand
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class HandGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> cardsInHand = new ArrayList<JLabel>();
    private JPanel cardPanel = new JPanel();
    
public HandGUI() {
        
        //ImageIcon back = new ImageIcon("images/back.jpg");
        //JLabel testCard = new JLabel(back);
       
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        cardPanel.setLayout(new BoxLayout(cardPanel,BoxLayout.X_AXIS));
        cardPanel.setBackground(new Color(214,231, 247));
        
        
        paint();
        JScrollPane scroller = new JScrollPane(cardPanel);
        scroller.setPreferredSize(new Dimension(745,200));
        scroller.setBorder(null);
        add(scroller);
        //paint();
    }
    
    
    public ArrayList<JLabel> getCardsInHand() {
        return cardsInHand;
    }
    
    public void paint() {
        
        cardPanel.removeAll();
        for(JLabel card: cardsInHand) {
            cardPanel.add(card);
            cardPanel.add(Box.createRigidArea(new Dimension(5,0)));
        }
        cardPanel.updateUI();

    }
    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        HandGUI handGUI = new HandGUI();
        
        frame.add(handGUI);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }*/
}
