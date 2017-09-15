package GUI;

import Listener.*;
import javax.swing.*;
import java.awt.*;

/**
 * Main GUI of Roma Game
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class MainWin extends JPanel{

	private static final long serialVersionUID = 1L;
	private final int TOTAL_PLAYER = 2;
	private final int PLAYER_A = 0;
	private final int PLAYER_B = 1;
	
	private FieldGUI[] field = new FieldGUI[TOTAL_PLAYER];
	private DiceDiscGUI diceDisc;
	private HandGUI[] hand = new HandGUI[TOTAL_PLAYER];
	private StatusGUI[] status = new StatusGUI[TOTAL_PLAYER];
	private JLabel drawDeck;
	private JLabel moneyDisc;
	private JLabel discardPile;
	private JLabel drawCardDisc;
	private StockPileGUI stockPile = new StockPileGUI();
	private JLabel[]  diceLabel;
	
	public MainWin() {
		GroupLayout mainLayout = new GroupLayout(this);
		setLayout(mainLayout);
		mainLayout.setAutoCreateGaps(true);
		mainLayout.setAutoCreateContainerGaps(true);
		
		drawDeck = new JLabel(new ImageIcon(getClass().getResource("/images/back_70.gif")));
		discardPile = new JLabel(new ImageIcon(getClass().getResource("/images/back_70.gif")));
		moneyDisc = new JLabel(new ImageIcon(getClass().getResource("/images/dinero.gif")));
		drawCardDisc = new JLabel(new ImageIcon(getClass().getResource("/images/cards.gif")));
		diceLabel = new JLabel[3];
		for(int i = 0; i < diceLabel.length; i++) {
			diceLabel[i] = new JLabel(new ImageIcon(getClass().getResource("/images/d1-a.gif")));
		}
		
		diceDisc = new DiceDiscGUI();
		field[PLAYER_A] = new FieldGUI();
		field[PLAYER_B] = new FieldGUI();
		
		hand[PLAYER_A] = new HandGUI();
		Dimension d = new Dimension((int) field[PLAYER_A].getPreferredSize().getWidth(), 
								    (int) field[PLAYER_A].getPreferredSize().getHeight()+20);
		hand[PLAYER_A].setMaximumSize(d);
		hand[PLAYER_B] = new HandGUI();
		hand[PLAYER_B].setMaximumSize(d);
		
		status[PLAYER_A] = new StatusGUI("Red");
		status[PLAYER_B] = new StatusGUI("Blue");
		
		mainLayout.setHorizontalGroup(mainLayout.createParallelGroup()
				.addGroup(mainLayout.createSequentialGroup()
						.addComponent(status[PLAYER_B])
						.addGap(10)
						.addComponent(hand[PLAYER_B])
						.addGap(15)
						.addComponent(drawDeck)
				)
				.addGroup(mainLayout.createSequentialGroup()
						.addGap(50)
						.addComponent(diceLabel[0])
						.addGap(60)
						.addComponent(field[PLAYER_B])
						.addGap(25)
						.addComponent(moneyDisc)
				)
				.addGroup(mainLayout.createSequentialGroup()
						.addGap(50)
						.addComponent(diceLabel[1])
						.addGap(60)
						.addComponent(diceDisc)
						.addGap(10)
						.addComponent(stockPile)
				)
				.addGroup(mainLayout.createSequentialGroup()
						.addGap(50)
						.addComponent(diceLabel[2])
						.addGap(60)
						.addComponent(field[PLAYER_A])
						.addGap(25)
						.addComponent(drawCardDisc)
				)
				.addGroup(mainLayout.createSequentialGroup()
						.addComponent(status[PLAYER_A])
						.addGap(10)
						.addComponent(hand[PLAYER_A])
						.addGap(15)
						.addComponent(discardPile)
				)
		);
		
		mainLayout.setVerticalGroup(mainLayout.createSequentialGroup()
				.addGroup(mainLayout.createParallelGroup()
						.addComponent(status[PLAYER_B], GroupLayout.Alignment.CENTER)
						.addComponent(hand[PLAYER_B], GroupLayout.Alignment.CENTER)
						.addComponent(drawDeck, GroupLayout.Alignment.CENTER)
				)
				.addGroup(mainLayout.createParallelGroup()
						.addComponent(diceLabel[0], GroupLayout.Alignment.CENTER)
						//.addGap((int) statusB.getPreferredSize().getWidth())
						.addComponent(field[PLAYER_B], GroupLayout.Alignment.CENTER)
						.addComponent(moneyDisc, GroupLayout.Alignment.CENTER)
						//.addGap((int) statusB.getPreferredSize().getWidth())
				)
				.addGroup(mainLayout.createParallelGroup()
						.addComponent(diceLabel[1], GroupLayout.Alignment.CENTER)
						.addComponent(diceDisc, GroupLayout.Alignment.CENTER)
						.addComponent(stockPile, GroupLayout.Alignment.CENTER)
				)
				.addGroup(mainLayout.createParallelGroup()
						.addComponent(diceLabel[2], GroupLayout.Alignment.CENTER)
						//.addGap((int) statusB.getPreferredSize().getWidth())
						.addComponent(field[PLAYER_A], GroupLayout.Alignment.CENTER)
						.addComponent(drawCardDisc, GroupLayout.Alignment.CENTER)
						//.addGap((int) statusB.getPreferredSize().getWidth())
				)
				.addGroup(mainLayout.createParallelGroup()
						.addComponent(status[PLAYER_A], GroupLayout.Alignment.CENTER)
						.addComponent(hand[PLAYER_A], GroupLayout.Alignment.CENTER)
						.addComponent(discardPile, GroupLayout.Alignment.CENTER)
				)
		);
		
		setBackground(new Color(214,231, 247));
		setMinimumSize(getPreferredSize());
		
	}
	
	/**
	 * Set the listener for label and button
	 * @param control - control unit
	 */
	public void setListener(Control control) {
		
        DiceListener diceListener = new DiceListener(diceLabel, control);
        DiceDiscListener diceDiscListener = new DiceDiscListener(diceDisc, control);
        MoneyListener moneyListener = new MoneyListener(control);
        DrawListener drawListener = new DrawListener(control); 
        FieldListener fieldListener = new FieldListener(control, field);
		EndTurnListener endTurnListener = new EndTurnListener(control);
		
		for(int i = 0; i < diceLabel.length; i++) {
			diceLabel[i].addMouseListener(diceListener);
		}
		
		for (int i = 0; i < diceDisc.getDiceDisc().length; i++) {
			diceDisc.getDiceDisc()[i].addMouseListener(diceDiscListener);
		}
		for (int i = 0; i < TOTAL_PLAYER; i++) {
            for (int j = 0; j < field[i].getFieldLabel().length; j++) {
                field[i].getFieldLabel()[j].addMouseListener(fieldListener);
            }
        }
		moneyDisc.addMouseListener(moneyListener);
		drawCardDisc.addMouseListener(drawListener);
		stockPile.getEndTurnButton().addActionListener(endTurnListener);
	}
	
	public JLabel[] getDiceLabel() {
		return diceLabel;
	}
	
	public HandGUI[] getHand() {
		return hand;
	}
	
	public FieldGUI[] getField() {
		return field;
	}
	
	public StatusGUI[] getStatusLabel() {
		return status;
	}
	
	public StockPileGUI getStockPileLabel() {
		return stockPile;
	}
	
	public DiceDiscGUI getDiceDisc() {
		return diceDisc;
	}
	
	public JLabel getDiscardPile() {
		return discardPile;
	}
	
	public JButton getStartEndButton() {
	    return stockPile.getEndTurnButton();
	}
	
	
}
