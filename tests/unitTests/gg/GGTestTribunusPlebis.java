package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


public class GGTestTribunusPlebis extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		
		int[] diceValues = {1,5,6};
		int[] faceUpCards = {TRIBUNUS_PLEBIS, -1, -1, -1, -1, -1, -1};
		int[] VP = {9, 10, 10, 9}; //P1 before, P1 after, P2 before, P2 after
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(tribunusPlebisTester(game, player1, player2, VP));
		
	}
	
	@Override
	public String toString(){
		return "TRIBUNUS PLEBIS tests.";
	}
	
	public boolean tribunusPlebisTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2, 
			int[] VP){
		boolean activated = false;
		
		player1.setVictoryPoints(VP[0]);
		player2.setVictoryPoints(VP[2]);
		
		assert(player1.getVictoryPoints() == VP[0]);
		assert(player2.getVictoryPoints() == VP[2]);
		activated = game.activateCard(1, new int[0]);
		assert(player1.getVictoryPoints() == VP[1]);
		assert(player2.getVictoryPoints() == VP[3]);
		
		return activated;
	}
}
