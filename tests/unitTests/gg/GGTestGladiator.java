package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
import static tests.main.PlayerCode.*;


public class GGTestGladiator extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		
		int[] diceValues = {1,3,3};
		int[] faceUpCards = {-1, -1, GLADIATOR, -1, -1, -1, -1};
		int[] P2faceUpCards = {-1, -1, ESSEDUM, -1, -1, -1, -1};
		int[] P2faceUpCardsResult = {-1, -1, -1, -1, -1, -1, -1};
		
		int[] player2Hand = {};
		int[] player2HandResult = {ESSEDUM};
		int[] args = {3};
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		player2.setFaceUpCards(P2faceUpCards);
		
		assert(gladiatorTester(game, player1, player2, args, P2faceUpCardsResult, 
				player2Hand, player2HandResult));
		
	}
	
	@Override
	public String toString(){
		return "GLADIATOR tests.";
	}
	
	public boolean gladiatorTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2, 
			int[] args, int[] P2faceUpCardsResult, int[] player2Hand, int[] player2HandResult){
		boolean activated = false;
		
		player2.setHand(player2Hand);
		
		activated = game.activateCard(3, args);
		assert(isPermutation(player2.getHand(), player2HandResult));
		assert(isPermutation(player2.getFaceUpCards(), P2faceUpCardsResult));
		
		return activated;
	}
}
