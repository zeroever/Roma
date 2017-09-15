package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


public class GGTestLegat extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		
		int[] diceValues = {1,5,6};
		int[] faceUpCards = {LEGAT, -1, -1, -1, -1, -1, -1};
		int[] P2faceUpCards = {-1, -1, ESSEDUM, -1, -1, -1, -1};
		int[] VP = {10, 16}; //VP before and after
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		player2.setFaceUpCards(P2faceUpCards);
		
		assert(legatTester(game, player1, player2, VP));
		
	}
	
	@Override
	public String toString(){
		return "LEGAT tests.";
	}
	
	public boolean legatTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2, 
			int[] VP){
		boolean activated = false;
		
		player1.setVictoryPoints(VP[0]);
		
		assert(player1.getVictoryPoints() == VP[0]);
		activated = game.activateCard(1, new int[1]);
		assert(player1.getVictoryPoints() == VP[1]);
		
		return activated;
	}
}
