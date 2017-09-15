package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
import static tests.main.PlayerCode.*;


public class GGTestNero extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		
		int[] diceValues = {1,5,6};
		int[] faceUpCards = {NERO, -1, -1, -1, -1, -1, -1};
		int[] P2faceUpCards = {-1, FORUM, SENATOR, -1, -1, -1, -1};
		int[] P1expectedResult = {-1, -1, -1, -1, -1, -1, -1};
		int[] P2expectedResult = {-1, -1, SENATOR, -1, -1, -1, -1};
		
		int[] args = {2};
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		player2.setFaceUpCards(P2faceUpCards);
		
		assert(neroTester(game, player1, player2, args, P1expectedResult, P2expectedResult));
		
	}
	
	@Override
	public String toString(){
		return "NERO tests.";
	}
	
	public boolean neroTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2, 
			int[] args, int[] P1expectedResult, int[] P2expectedResult){
		boolean activated = false;
		
		activated = game.activateCard(1, args);
		assert(isPermutation(player1.getFaceUpCards(), P1expectedResult));
		assert(isPermutation(player2.getFaceUpCards(), P2expectedResult));
		
		return activated;
	}
}
