package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


public class GGTestTemplum extends RomaTest{

	public boolean templumTester(RomaEngine game, TestablePlayer player1, int[] VP, int[] VPToBeTaken){
		boolean activated = false;
		
		player1.setVictoryPoints(VP[0]);
		
		assert(player1.getVictoryPoints() == VP[0]);
		activated = game.activateCard(2, VPToBeTaken);
		assert(player1.getVictoryPoints() == VP[1]);
		
		return activated;
	}

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		
		int[] diceValues = {2,3,6};
		int[] faceUpCards = {TEMPLUM, FORUM, -1, -1, -1, -1, -1};
		int[] VP = {10, 19}; //before and after VP, VP should be 10+3+6 = 19 
		int[] VPToBeTaken = {3, 6};
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(templumTester(game, player1, VP, VPToBeTaken));
		
	}
	
	@Override
	public String toString(){
		return "GG TEMPLUM tests.";
	}
}
