package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


public class GGTestConsul extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		
		int[] diceValues = {1,3,6};
		int[] faceUpCards = {CONSUL, -1, -1, -1, -1, -1, -1};
		int[] args = {3, 1}; //1 to increase
		int diceResult = 4;
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(consulTester(game, player1, args, diceResult));
		
	}
	
	@Override
	public String toString(){
		return "CONSUL tests.";
	}
	
	public boolean consulTester(RomaEngine game, TestablePlayer player1, int[] args, int diceResult){
		boolean activated = false;
		boolean diceFound = true;
		
		activated = game.activateCard (1, args);
		
		for(int i = 0; i < game.getAvailableActionDice().length && diceFound == false; i++){
			if(game.getAvailableActionDice()[i] == diceResult){
				diceFound = true;
			}
		}
		
		assert(diceFound == true);
		
		return activated;
	}
}
