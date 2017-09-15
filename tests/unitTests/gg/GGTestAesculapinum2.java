package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;



public class GGTestAesculapinum2 extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		
		int[] diceValues = {1,1,1};
		int[] faceUpCards ={AESCULAPINUM, -1, -1, -1, -1, -1, -1};
		
		int[] cardsInHand = {LEGAT, ESSEDUM};
		int[] cardsInDiscardPile = {CONSUL, SICARIUS, NERO, VELITES};
		int[] expectedResult = {LEGAT, ESSEDUM, SICARIUS};
		int[] cardToBeTaken = {SICARIUS}; //take SICARIUS
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(aesculapinumTester(game, player1, cardsInHand, cardsInDiscardPile, 
				cardToBeTaken, expectedResult));
	}
	
	@Override
	public String toString(){
		return "GG's Aesculapinum tests 2.";
	}
	
	public boolean aesculapinumTester(RomaEngine game, TestablePlayer player1, 
			int[] cardsInHand, int[] cardsInDiscardPile, int[] cardToBeTaken, int[] expectedResult){
		boolean activated = false;
		player1.setHand(cardsInHand);

		game.setDiscardPile(cardsInDiscardPile);
		
		activated = game.activateCard(1, cardToBeTaken);
		assert(isPermutation(player1.getHand(), expectedResult));
		assert(player1.getHand().length == expectedResult.length);
		assert(game.getDiscardPile().length == cardsInDiscardPile.length - 1);
		
		return activated;
	}
}
