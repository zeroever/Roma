package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


public class GGTestForum extends RomaTest{

	@Override
	public void run(){
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		
		int[] diceValues = {1,5,6};
		int[] faceUpCards = {FORUM, -1, -1, -1, -1, -1, -1};
		int[] VP = {10, 15}; //before and after VP
		int[] VPToBeTaken = {5};
		
		game.setAvailableActionDice(diceValues);
		game.setCurrentPlayer(PLAYER1);
		player1.setFaceUpCards(faceUpCards);
		
		assert(forumTester(game, player1, VP, VPToBeTaken));
		
	}
	
	@Override
	public String toString(){
		return "Forum tests.";
	}
	
	public boolean forumTester(RomaEngine game, TestablePlayer player1, int[] VP, int[] VPToBeTaken){
		
		boolean activated = false;
		
		player1.setVictoryPoints(VP[0]);
		
		assert(player1.getVictoryPoints() == VP[0]);
		activated = game.activateCard(1, VPToBeTaken);
		assert(player1.getVictoryPoints() == VP[1]);
		return activated;
	}
}
