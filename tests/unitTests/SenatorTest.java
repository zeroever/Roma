package tests.unitTests;

import java.util.Arrays;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
import static tests.main.DiscCode.*; 

// By Adam Black and Sami Kilani


public class SenatorTest extends RomaTest {
	@Override
	public void run() {
		Roma roma = new Roma();
	
		{
			int[] faceUpCards = {
				SENATOR, NO_CARD, NO_CARD,
				NO_CARD, NO_CARD, NO_CARD,
				NO_CARD,
			};
			
			getCurrentPlayer(roma).setFaceUpCards(faceUpCards);
			
			int[] hand = {NERO, SICARIUS, SENATOR};
			getCurrentPlayer(roma).setHand(hand);
			
			int[] actionDice = {1};
			roma.setAvailableActionDice(actionDice);
			
			int[] cardPairs = {
				NERO, DISC1,
				SICARIUS, DISC2,
			};
			
			boolean activationWorked = roma.activateCard(DISC1, cardPairs);
			assert(activationWorked);
			
			int[] newFaceupCards = {
				NERO, SICARIUS, NO_CARD,
				NO_CARD, NO_CARD, NO_CARD,
				NO_CARD,
			};
			
			assert(Arrays.equals(getCurrentPlayer(roma).getFaceUpCards(), newFaceupCards));
		}
		
	}

	
	private TestablePlayer getCurrentPlayer(RomaEngine roma) {
		return roma.getPlayer(roma.getCurrentPlayer());
	}
	@Override
	public String toString() {
		return "Senator test";
	}

}
