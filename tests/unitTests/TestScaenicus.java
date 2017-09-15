package tests.unitTests;

import java.util.Arrays;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;

/**
 * @author Adam Black
 */

public class TestScaenicus extends RomaTest {
	private static final int DISC6_INDEX = DISC6 - 1;
	private static final int DISC2_INDEX = DISC2 - 1;

	@Override
	public void run() {
		{
			int cardToCopy = TRIBUNUS_PLEBIS;
			int vpsTransfered = 1;
			
			int[] args = {cardToCopy};
			
			testActivationTransferingVps(new Roma(), cardToCopy, args, vpsTransfered); 
		}
		{
			// Scaenicus performing the action of Legionarius
			
			RomaEngine roma = new Roma();
			TestablePlayer player1 = roma.getPlayer(PLAYER1);
			TestablePlayer player2 = roma.getPlayer(PLAYER2);
			
			{
				int[] faceUpCards = {
					NO_CARD, 	SCAENICUS,	NO_CARD, 
					NO_CARD, 	NO_CARD, 	LEGIONARIUS,
					NO_CARD,
				};
				player1.setFaceUpCards(faceUpCards);
			}
			int[] player2FaceUpCards = {
				AESCULAPINUM, 	MERCATOR,	BASILICA, 
				CENTURIO, 		ESSEDUM,	 FORUM,
				NO_CARD,
			};
			// the only important card here is Mercator
				
			player2.setFaceUpCards(player2FaceUpCards);
			assert(Arrays.equals(player2.getFaceUpCards(), player2FaceUpCards));
			
			
			assert(roma.getCurrentPlayer() == PLAYER1);
			
			int[] dice = {2};
			roma.setAvailableActionDice(dice);
			
			roma.rigBattleDie(2); // Mercator has a defence value of 2 
			assert(player2.getFaceUpCards()[DISC2_INDEX] == MERCATOR);
			
			int args[] = {LEGIONARIUS};  // card to perform the action of
			roma.activateCard(DISC2, args);

			// note that Scaenicus will now kill the card opposite it
			// not the card opposite Legionarius
			player2FaceUpCards[DISC2_INDEX] = NO_CARD;
			
			assert(player2.getFaceUpCards()[DISC6_INDEX] != MERCATOR);
			assert(Arrays.equals(player2.getFaceUpCards(), player2FaceUpCards)) : 
				Arrays.toString(player2FaceUpCards);	
		}
		{
			int cardToCopy = MERCATOR;
			int vpsToBuy = 2;
			
			int[] args = {cardToCopy, vpsToBuy};
			
			RomaEngine roma = new Roma();
			roma.getPlayer(PLAYER1).setMoney(vpsToBuy * 2);
			testActivationTransferingVps(roma, cardToCopy, args, vpsToBuy); 
		}
	}
	
	private void testActivationTransferingVps(RomaEngine roma, int cardToCopy,
				int[] args, int vps) {
		
		TestablePlayer player1 = roma.getPlayer(PLAYER1);
		TestablePlayer player2 = roma.getPlayer(PLAYER2);
		
		assert(player1.getVictoryPoints() == 10): "initial victory points should be 10";
		assert(player2.getVictoryPoints() == 10): "initial victory points should be 10";
		
		
		testActivation(roma, cardToCopy, args);
		
		assert(player1.getVictoryPoints() == 10 + vps): 
			"there should be extra VP from activation";
		assert(player2.getVictoryPoints() == 10 - vps);
	
	}

	private void testActivation(RomaEngine roma, int cardToCopy, int[] args) {
		TestablePlayer player1 = roma.getPlayer(PLAYER1);
		
		{
			int[] faceUpCards = {
				SCAENICUS, NO_CARD, cardToCopy, 
				NO_CARD, 	NO_CARD, 		NO_CARD,
				NO_CARD,
			};
			player1.setFaceUpCards(faceUpCards);
		}
		
		assert(roma.getCurrentPlayer() == PLAYER1);
		
		int[] dice = {1};
		roma.setAvailableActionDice(dice);
				
		roma.activateCard(DISC1, args);

	}

	@Override
	public String toString() {
		return "Scaenicus test";
	}

}
