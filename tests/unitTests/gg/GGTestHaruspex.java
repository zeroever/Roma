package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;



public class GGTestHaruspex extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		
		int[] diceValues = {1,1,1};

		int[] faceUpCards ={HARUSPEX, -1, -1, -1, -1, -1, -1};
		int[] cardsInHand = {LEGAT, ESSEDUM , SENATOR};
		int[] cardsInDrawPile = {CONSUL, SICARIUS, NERO, VELITES, GLADIATOR};
		int[] expectedResult = {LEGAT, ESSEDUM, SENATOR, NERO};
		int[] cardToBeTaken = {NERO}; //take NERO
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(haruspexTester(game, player1, cardsInHand, cardsInDrawPile, cardToBeTaken, expectedResult));
		
	}
	
	@Override
	public String toString(){
		return "Haruspex tests.";
	}
	
	public boolean haruspexTester(RomaEngine game, TestablePlayer player1, 
		int[] cardsInHand, int[] cardsInDrawPile, int[] cardToBeTaken, int[] expectedResult){
		
		boolean activated = false;
		
		player1.setHand(cardsInHand);
		game.setDiscardPile(cardsInDrawPile);
		game.setDrawPile(cardsInDrawPile);
		
		activated = game.activateCard(1, cardToBeTaken);
		assert(isPermutation(player1.getHand(), expectedResult));
		assert(player1.getHand().length == expectedResult.length);
		assert(game.getDrawPile().length == cardsInDrawPile.length - 1);
		
		return activated;
	}
}
