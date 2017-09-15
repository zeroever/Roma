package tests.unitTests;

import java.util.Arrays;

import tests.main.CardCode;
import tests.main.PlayerCode;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;


// Written by Rowan Katekar (rkat535)
// email rowan@student.unsw.edu.au for clarification :)


public class TestNero3 extends RomaTest {

	@Override
	public void run() {
		testSimpleCase();
		testEmptyDisc();
		testCharacterCard();
	}

	private void testEmptyDisc() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PlayerCode.PLAYER1);
		TestablePlayer player2 = game.getPlayer(PlayerCode.PLAYER2);
		int[] player2InitialCards = {
			CardCode.NO_CARD, CardCode.FORUM, CardCode.FORUM,
			CardCode.FORUM, CardCode.FORUM, CardCode.FORUM,
			CardCode.FORUM,
		};
		player2.setFaceUpCards(player2InitialCards);
		int[] player1InitialCards = {
			CardCode.NERO, CardCode.NERO, CardCode.NERO,
			CardCode.NERO, CardCode.NERO, CardCode.NERO,
			CardCode.NERO,
		};
		player1.setFaceUpCards(player1InitialCards);
		game.setCurrentPlayer(PlayerCode.PLAYER1);
		
//		// attempt to remove empty card - should return false
//		int[] actionDice = {1};
//		game.setAvailableActionDice(actionDice);
//		
//		///!!!
//		int[] cardToDestroy = {DiscCode.DISC1};
//		assert (!game.activateCard(1, cardToDestroy));
//		
//		// the nero should still be alive
//		assert (player1.getFaceUpCards()[0] == CardCode.NERO);
		
	}

	private void testCharacterCard() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PlayerCode.PLAYER1);
		TestablePlayer player2 = game.getPlayer(PlayerCode.PLAYER2);
		int[] player2InitialCards = {
			CardCode.ARCHITECTUS, CardCode.FORUM, CardCode.FORUM,
			CardCode.FORUM, CardCode.FORUM, CardCode.FORUM,
			CardCode.FORUM,
		};
		player2.setFaceUpCards(player2InitialCards);
		int[] player1InitialCards = new int[7];
		Arrays.fill(player1InitialCards, CardCode.NERO);
		
		player1.setFaceUpCards(player1InitialCards);
		game.setCurrentPlayer(PlayerCode.PLAYER1);
		
//		// attempt to remove opponents architectus - should return false
//		int[] actionDice = {1};
//		game.setAvailableActionDice(actionDice);
//		
//		int[] cardToDestroy = {1};
//		assert (!game.activateCard(DiscCode.DISC1, cardToDestroy));
//		
//		// the architectus should still be there
//		assert (player2.getFaceUpCards()[0] == CardCode.ARCHITECTUS);
//		
//		// and so should the nero
//		assert (player1.getFaceUpCards()[0] == CardCode.NERO);
	}

	private void testSimpleCase() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PlayerCode.PLAYER1);
		TestablePlayer player2 = game.getPlayer(PlayerCode.PLAYER2);
		int[] player1InitialCards = {
			CardCode.NERO, CardCode.NERO, CardCode.NERO,
			CardCode.NERO, CardCode.NERO, CardCode.NERO,
			CardCode.NERO,
		};
		player1.setFaceUpCards(player1InitialCards);
		int[] player2InitialCards = {
			CardCode.FORUM, CardCode.FORUM, CardCode.FORUM,
			CardCode.FORUM, CardCode.FORUM, CardCode.FORUM,
			CardCode.FORUM,
		};
		player2.setFaceUpCards(player2InitialCards);
		game.setCurrentPlayer(PlayerCode.PLAYER1);
		
		// destroy building card on disc 1
		int[] buildingCardToDestroy = {1};

		int[] actionDice = {1};
		game.setAvailableActionDice(actionDice);
		assert(game.getAvailableActionDice().length > 0);
		// activation should return true
		assert(game.activateCard(1, buildingCardToDestroy));
		
		// the building card should have been destroyed
		assert (player2.getFaceUpCards()[0] == CardCode.NO_CARD);
		
		// the nero should have also been removed from the disc
		assert (player1.getFaceUpCards()[0] == CardCode.NO_CARD);
		
		// other neros should not be affected
		assert (player1.getFaceUpCards()[1] == CardCode.NERO);
		assert (player1.getFaceUpCards()[2] == CardCode.NERO);
		assert (player1.getFaceUpCards()[3] == CardCode.NERO);
		assert (player1.getFaceUpCards()[4] == CardCode.NERO);
		assert (player1.getFaceUpCards()[5] == CardCode.NERO);
		
		// other forums should not be affected
		assert (player2.getFaceUpCards()[1] == CardCode.FORUM);
		assert (player2.getFaceUpCards()[2] == CardCode.FORUM);
		assert (player2.getFaceUpCards()[3] == CardCode.FORUM);
		assert (player2.getFaceUpCards()[4] == CardCode.FORUM);
		assert (player2.getFaceUpCards()[5] == CardCode.FORUM);
		
	}

	@Override
	public String toString() {
		return "Nero test";
	}

}

