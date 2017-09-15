package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


public class GGTestBasilica extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		
		int[] diceValues = {2,3,6};
		int[] faceUpCards = {-1, FORUM, BASILICA, -1, -1, -1, -1};
		int[] VP = {10, 18}; //before and after VP, VP should be 10+6+2 = 18 
		int[] VPToBeTaken = {6};
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(basicilaTester(game, player1, VP, VPToBeTaken));
		
	}
	
	@Override
	public String toString(){
		return "BASILICA tests.";
	}
	
	public boolean basicilaTester(RomaEngine game, TestablePlayer player1, int[] VP, int[] VPToBeTaken){
		boolean activated = false;
		
		player1.setVictoryPoints(VP[0]);
		
		assert(player1.getVictoryPoints() == VP[0]);
		activated = game.activateCard(2, VPToBeTaken);
		assert(player1.getVictoryPoints() == VP[1]);
		return activated;
	}
}
