package GUI;

import Logic.*;
import TextUI.RomaTextMode;
import javax.swing.*;
import Updater.*;
import java.awt.Dimension;

/**
 * GUI version of Roma Game
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 *
 */

public class RomaGUIMode {

	public static void main(String[] args) {
		
		if (args.length > 0 && args[0].equals("-t")){
			RomaTextMode.main(null);
		} else {
		    JFrame frame = new JFrame();
	
	        MainWin window = new MainWin();
		    SidePanel sidePanel = new SidePanel();
		    
		    Game game = new Game();
		    GuiDisplay display = new GuiDisplay(game, sidePanel.getGameStatus(),
		    									sidePanel.getCardDescription());
	        GuiInstruction instruction = new GuiInstruction(frame);
	        Control control = new Control(game, display, frame);
	        Updater updater = new Updater(window, game, control);
	        
	        //setting Input/Output and updater
	        game.setIO(display, instruction);
	        window.setListener(control);
	        control.setUpdater(updater);
	        
	        frame.setTitle("Roma");
	        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        frame.setVisible(true);
	        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
	        
	        frame.add(window);
	        frame.add(Box.createRigidArea(new Dimension(10,0)));
	        frame.add(sidePanel);
	        frame.add(Box.createRigidArea(new Dimension(5,0)));
	        frame.pack();
	        frame.setMinimumSize(frame.getPreferredSize());
	        updater.updateGame();
		}
	}

}
