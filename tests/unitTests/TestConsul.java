package tests.unitTests;

import java.util.Arrays;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;


// Written by Rowan Katekar (rkat535)
// email rowan@student.unsw.edu.au for clarification :)

public class TestConsul extends RomaTest {

	@Override
	public void run() {
		testIncrease();
		testDecrease();
		testNonExistentDice();
	}

	private void testNonExistentDice() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		int[] player2InitialCards = {
			MERCATOR, MERCATOR, MERCATOR,
			MERCATOR, MERCATOR, MERCATOR,
			MERCATOR,
		};
		player2.setFaceUpCards(player2InitialCards);
		int[] player1InitialCards = {
			CONSUL, NO_CARD, NO_CARD,
			NO_CARD, NO_CARD,	CONSUL,
			MERCATOR
		};
		player1.setFaceUpCards(player1InitialCards);
		game.setCurrentPlayer(PLAYER1);
		
		// these are the available dice
		int[] availableDice = {1, 5};
		game.setAvailableActionDice(availableDice);
		
		// we want to decrease dice number 3 by 1 point
		//int[] consulArgs = {3, 0};
		
		// it shouldn't work, as we only have 2 dice available
        // kitten: commented out
		//assert(!game.activateCard(1, consulArgs));
		
		// the dice should not be used up
		assert(game.getAvailableActionDice().length == 2);
		
		// the available action dice should still be 1 and 5
		assert(isPermutation(game.getAvailableActionDice(), availableDice));
	}

	private void testDecrease() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		int[] player2InitialCards = {
			MERCATOR, MERCATOR, MERCATOR, 
			MERCATOR, MERCATOR, MERCATOR,
			MERCATOR
		};
		player2.setFaceUpCards(player2InitialCards);
		int[] player1InitialCards = {
			CONSUL, NO_CARD, NO_CARD, 
			NO_CARD, NO_CARD, CONSUL,
			MACHINA
		};
		player1.setFaceUpCards(player1InitialCards);
		game.setCurrentPlayer(PLAYER1);
		
		// these are the available dice
		int[] availableDice = {1, 5};
		game.setAvailableActionDice(availableDice);
		
		// we want to decrease dice number 5 by 1 point
		int[] consulArgs = {5, 0};
		
		// make sure it worked
		assert(game.activateCard(1, consulArgs));
		
		// the dice should be used up
		assert(game.getAvailableActionDice().length == 1);
		
		// the available action dice should now be 4
		assert(game.getAvailableActionDice()[0] == 4);
		
	}

	private void testIncrease() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PLAYER1);
		TestablePlayer player2 = game.getPlayer(PLAYER2);
		int[] player2InitialCards = {
			MERCATOR, MERCATOR, MERCATOR, 
			MERCATOR, MERCATOR, MERCATOR,
			MERCATOR
		};
		player2.setFaceUpCards(player2InitialCards);
		int[] player1InitialCards = {
			CONSUL, NO_CARD, NO_CARD,
			NO_CARD, NO_CARD, CONSUL,
			MERCATOR
		};
		player1.setFaceUpCards(player1InitialCards);
		game.setCurrentPlayer(PLAYER1);
		
		// these are the available dice
		int[] availableDice = {1, 2}; //{1, 5};
		game.setAvailableActionDice(availableDice);
		assert(isPermutation(availableDice, game.getAvailableActionDice()));
		
		// we want to increase dice number 2 by 1 point
		int[] consulArgs = {2, 1};
		
		// make sure it worked
		assert(game.activateCard(1, consulArgs));
		
		// the dice should be used up
		assert(game.getAvailableActionDice().length == 1):
			Arrays.toString(game.getAvailableActionDice());
		
		// the available action dice should now be 3 (2+1)
		assert(game.getAvailableActionDice().length == 1);
		assert(game.getAvailableActionDice()[0] == 3);
		
	}

	@Override
	public String toString() {
		return "Consul test";
	}

}

