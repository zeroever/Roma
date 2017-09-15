package tests.unitTests;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;

public class TestLegat extends RomaTest {

	@Override
	public void run() {
		TestLegat tester = new TestLegat();
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		
		int[] variables = {10,10,5,6}; //in order of vp1, vp2 money1, money2
		int[] expectedResult = {14,10,5,6}; //in order of vp1, vp2 money1, money2
		int[] faceUpCards1 = {
				LEGAT, NO_CARD, NO_CARD,
				NO_CARD, NO_CARD, NO_CARD,
				NO_CARD
		};
	
		int[] faceUpCards2 = {
					LEGAT, NO_CARD, AESCULAPINUM,
					NO_CARD ,CENTURIO, NO_CARD,
					NO_CARD};
		int[] diceValues = {1,1,1};
        
        game.setAvailableActionDice(diceValues);
        game.setCurrentPlayer(PLAYER1);
        
        player1.setFaceUpCards(faceUpCards1);
        player2.setFaceUpCards(faceUpCards2);
        
        assert(tester.LegatTester(game, player1, player2,  variables, expectedResult));
        
        
	}
	
	public boolean LegatTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2,
			            int[] variables, int[] expectedResult){
		boolean activated = false;
		int args[] = new int[0];
		player1.setVictoryPoints(variables[0]);
		player2.setVictoryPoints(variables[1]);
		player1.setMoney(variables[2]);
		player2.setMoney(variables[3]);
		activated = game.activateCard(1,args);
		assert(player1.getVictoryPoints() == expectedResult[0]);
		assert(player2.getVictoryPoints() == expectedResult[1]);
		assert(player1.getMoney() == expectedResult[2]);
		assert(player2.getMoney() == expectedResult[3]);
        return activated;
        
	}

	@Override
	public String toString() {
		return "Test Legat";
	}

}
