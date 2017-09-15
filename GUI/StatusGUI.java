package GUI;

import javax.swing.*;

import java.awt.*;

/**
 * Status GUI
 * It displays player name, player's money and player's victory points
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class StatusGUI extends JPanel {
    
	private static final long serialVersionUID = 1L;
	private JLabel playerLabel;
    private JLabel moneyLabel;
    private JLabel victoryPointLabel;
    private JLabel moneyValueLabel;
    private JLabel victoryPointValueLabel;
    
    public StatusGUI(String name) {
    	
        moneyLabel = new JLabel("Money :");
        moneyLabel.setFont(new java.awt.Font("Arial",Font.BOLD,14));
        victoryPointLabel = new JLabel("Victory Points :");
        victoryPointLabel.setFont(new java.awt.Font("Arial",Font.BOLD,14));
        
        playerLabel = new JLabel(name);
        playerLabel.setFont(new java.awt.Font("Segoe UI",Font.PLAIN,20));
        
        moneyValueLabel = new JLabel("0");
        moneyValueLabel.setFont(new java.awt.Font("Arial",Font.BOLD,14));
        victoryPointValueLabel = new JLabel("0");
        victoryPointValueLabel.setPreferredSize(getMaximumSize());
        victoryPointValueLabel.setFont(new java.awt.Font("Arial",Font.BOLD,14));
        
        Dimension di = moneyValueLabel.getPreferredSize();
        Dimension d = new Dimension(di.width+20, di.height);
        moneyValueLabel.setPreferredSize(d);
        moneyValueLabel.setMaximumSize(d);
        moneyValueLabel.setMinimumSize(d);
        victoryPointValueLabel.setPreferredSize(d);
        victoryPointValueLabel.setMaximumSize(d);
        victoryPointValueLabel.setMinimumSize(d);
        
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        setBackground(new Color(214,231, 247));
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(playerLabel)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(moneyLabel)
                    .addComponent(victoryPointLabel)
                )
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                    .addComponent(moneyValueLabel)
                    .addComponent(victoryPointValueLabel)
                )
            )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(playerLabel)
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(moneyLabel)
                    .addComponent(victoryPointLabel)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(moneyValueLabel)
                    .addComponent(victoryPointValueLabel)
                )
            )
        );
        
    }
    
    public JLabel getPlayerLabel() {
        return playerLabel;
    }
    
    public JLabel getMoneyValueLabel() {
        return moneyValueLabel;
    }
    
    public JLabel getVictoryPointValueLabel() {
        return victoryPointValueLabel;
    }
    
    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        StatusGUI statusGUI = new StatusGUI("Name");
        System.out.println(statusGUI.getPreferredSize().getWidth());
        frame.add(statusGUI);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

    }*/
}
