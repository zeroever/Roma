package GUI;

import javax.swing.*;

import java.awt.*;

/**
 * Stock Pile GUI
 * It displays general stock pile of victory points
 * It also contains the end turn button
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class StockPileGUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel name = new JLabel("StockPile");
	private JLabel victoryPointLabel = new JLabel("Victory Points :");
	private JLabel victoryPointValue = new JLabel("0");
    private JButton endTurn = new JButton("Start Game");

	public StockPileGUI() {
		name.setFont(new java.awt.Font("Segoe UI",Font.PLAIN,18));
		victoryPointLabel.setFont(new java.awt.Font("Arial",Font.BOLD,14));
		victoryPointValue.setFont(new java.awt.Font("Arial",Font.BOLD,14));
		GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        setBackground(new Color(214,231, 247));
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(name)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(victoryPointLabel)
                )
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                    .addComponent(victoryPointValue)
                )
            )
            .addComponent(endTurn)
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(name)
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(victoryPointLabel)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(victoryPointValue)
                )
            )
            .addComponent(endTurn)
        );
	}
	
	public JLabel getVictoryPointLabel() {
		return victoryPointValue;
	}
	
	public JButton getEndTurnButton() {
        return endTurn;
    }
}
