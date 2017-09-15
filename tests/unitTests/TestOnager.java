package tests.unitTests;

import tests.main.CardCode;
import tests.main.PlayerCode;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;


public class TestOnager extends RomaTest {

	@Override
	public void run() {
		RomaEngine game = new Roma();
		TestablePlayer player1 = game.getPlayer(PlayerCode.PLAYER1);
		TestablePlayer player2 = game.getPlayer(PlayerCode.PLAYER2);
		int[] player2InitialCards = {
			CardCode.NO_CARD, CardCode.FORUM, CardCode.FORUM, 
			CardCode.FORUM, CardCode.FORUM, CardCode.FORUM,
			CardCode.MERCATOR};
		player2.setFaceUpCards(player2InitialCards);
		int[] player1InitialCards = {
			CardCode.ONAGER, CardCode.NERO, CardCode.NERO,
			CardCode.NERO, CardCode.NERO, CardCode.NERO,
			CardCode.MERCATOR
		};
		player1.setFaceUpCards(player1InitialCards);
		game.setCurrentPlayer(PlayerCode.PLAYER1);
		
		// attempt to remove opponent's forum on disc 2
		int[] cardToDestroy = {2};
		game.rigBattleDie(9000);
		assert (game.activateCard(1, cardToDestroy));
		
		// Forum should be destroyed
		assert (game.getPlayer(PlayerCode.PLAYER2).getFaceUpCards()[0] == CardCode.NO_CARD);
		assert (game.getPlayer(PlayerCode.PLAYER2).getFaceUpCards()[1] == CardCode.NO_CARD);
		assert (game.getPlayer(PlayerCode.PLAYER2).getFaceUpCards()[2] == CardCode.FORUM);
		assert (game.getPlayer(PlayerCode.PLAYER2).getFaceUpCards()[3] == CardCode.FORUM);
		assert (game.getPlayer(PlayerCode.PLAYER2).getFaceUpCards()[4] == CardCode.FORUM);
		assert (game.getPlayer(PlayerCode.PLAYER2).getFaceUpCards()[5] == CardCode.FORUM);
		
		// Player 1's cards should be the same
		assert (game.getPlayer(PlayerCode.PLAYER1).getFaceUpCards()[0] == CardCode.ONAGER);
		assert (game.getPlayer(PlayerCode.PLAYER1).getFaceUpCards()[1] == CardCode.NERO);
		assert (game.getPlayer(PlayerCode.PLAYER1).getFaceUpCards()[2] == CardCode.NERO);
		assert (game.getPlayer(PlayerCode.PLAYER1).getFaceUpCards()[3] == CardCode.NERO);
		assert (game.getPlayer(PlayerCode.PLAYER1).getFaceUpCards()[4] == CardCode.NERO);
		assert (game.getPlayer(PlayerCode.PLAYER1).getFaceUpCards()[5] == CardCode.NERO);
	}

	@Override
	public String toString() {
		return "Onager test";
	}

}
