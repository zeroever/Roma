package tests.unitTests;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;

// Written by Rowan Katekar (rkat535)
// email rowan@student.unsw.edu.au for clarification :)

public class TestPraetorianus extends RomaTest {

	@Override
	public void run() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		
		int[] player2InitialCards = {
			ESSEDUM, LEGAT, ESSEDUM, 
			ESSEDUM, TURRIS, NO_CARD,
			MERCATOR
		};
		player2.setFaceUpCards(player2InitialCards);
		
		int[] player1InitialCards = {
			PRAETORIANUS, NO_CARD, NO_CARD,
			NO_CARD, NO_CARD, NO_CARD,
			MERCATOR
		};
		player1.setFaceUpCards(player1InitialCards);
		
		// Player 1's turn
		game.setCurrentPlayer(PLAYER1);
		
		// set available dice as 1
		int[] p1Dice = {1};
		game.setAvailableActionDice(p1Dice);
		
		// tell the Praetorianus to block disc 1
		int[] blockedDisc = {1};
		
		// activate Praetorianus
		assert(game.activateCard(1, blockedDisc));
		
		// Player 2's turn
		game.setCurrentPlayer(PLAYER2);
		
		// set avaiable dice as 1
		int[] p2Dice = {1};
		game.setAvailableActionDice(p2Dice);
		
		// get to player 2's next turn
		game.endCurrentTurn();
		assert(game.getCurrentPlayer() == PLAYER1);
		game.endCurrentTurn();
		assert(game.getCurrentPlayer() == PLAYER2);
		
		// try to activate essedum - should be unblocked
		int[] essedumArgs = {};
		game.setAvailableActionDice(p2Dice);
		assert(game.activateCard(1, essedumArgs));
	}

	@Override
	public String toString() {
		return "Praetorianus test";
	}

}

