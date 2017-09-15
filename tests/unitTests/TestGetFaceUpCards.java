package tests.unitTests;

import tests.main.RomaTest;
//import static tests.main.CardCode.*; 
//import static tests.main.DiscCode.*;
//import static tests.main.PlayerCode.*;

/**
 * @author Michael <mtle653@cse.unsw.edu.au>, Rhys <rdav870@cse.unsw.edu.au>, Ka <kwko240@cse.unsw.edu.au>
 * @tute Mon13Oboe
 */


public class TestGetFaceUpCards extends RomaTest {
	public void run() {
		/*
		int[] testFaceUpCards = new int[7];
		int[] actualFaceUpCards = new int[7];

		RomaEngine roma = new Roma();

		TestablePlayer test1 = roma.getPlayer(PLAYER1);
		TestablePlayer test2 = roma.getPlayer(PLAYER2);

		// Testing player 1 - few cards
		roma.setCurrentPlayer(PLAYER1);

		// Placing Consiliarius on dice disc 2
		roma.layCard(CONSILIARIUS, DISC2);
		// Placing Basilica on dice disc 0
		roma.layCard(NO_CARD, DiscCode.BRIBE_DISC);
		// Placing Aesculapinum on dice disc 6
		roma.layCard(NO_CARD, DISC6);

		testFaceUpCards = test1.getFaceUpCards();
		actualFaceUpCards = setActualFaceUpCards(BASILICA,
				NO_CARD, CONSILIARIUS, NO_CARD,
				NO_CARD, NO_CARD, AESCULAPINUM);

		assert (testFaceUpCards.equals(actualFaceUpCards));

		// Testing player 1 - no cards
		resetPlayerBoard(roma);

		testFaceUpCards = test1.getFaceUpCards();
		actualFaceUpCards = setActualFaceUpCards(NO_CARD,
				NO_CARD, NO_CARD, NO_CARD,
				NO_CARD, NO_CARD, NO_CARD);

		assert (testFaceUpCards.equals(actualFaceUpCards));

		// Testing player 1 - all dice discs occupied
		resetPlayerBoard(roma);
		
		// Placing Consiliarius on all discs
		roma.layCard(CONSILIARIUS, DiscCode.BRIBE_DISC);
		roma.layCard(CONSILIARIUS, DISC1);
		roma.layCard(CONSILIARIUS, DISC2);
		roma.layCard(CONSILIARIUS, DISC3);
		roma.layCard(CONSILIARIUS, DISC4);
		roma.layCard(CONSILIARIUS, DISC5);
		roma.layCard(CONSILIARIUS, DISC6);

		testFaceUpCards = test1.getFaceUpCards();
		actualFaceUpCards = setActualFaceUpCards(CONSILIARIUS,
				CONSILIARIUS, CONSILIARIUS, CONSILIARIUS,
				CONSILIARIUS, CONSILIARIUS, CONSILIARIUS);

		assert (testFaceUpCards.equals(actualFaceUpCards));

		// Testing player 2 - few cards
		roma.setCurrentPlayer(PLAYER2);

		// Placing Consiliarius on dice disc 2
		roma.layCard(CONSILIARIUS, DISC2);
		// Placing Basilica on dice disc 0
		roma.layCard(BASILICA, DiscCode.BRIBE_DISC);
		// Placing Aesculapinum on dice disc 6
		roma.layCard(AESCULAPINUM, DISC6);
		//		 Placing Aesculapinum on dice disc 6
		roma.layCard(AESCULAPINUM, DISC6);

		testFaceUpCards = test1.getFaceUpCards();
		actualFaceUpCards = setActualFaceUpCards(BASILICA,
				NO_CARD, CONSILIARIUS, NO_CARD,
				NO_CARD, NO_CARD, AESCULAPINUM);

		assert (testFaceUpCards.equals(actualFaceUpCards));
		
		// Testing player 2 - no cards
		resetPlayerBoard(roma);

		testFaceUpCards = test1.getFaceUpCards();
		actualFaceUpCards = setActualFaceUpCards(NO_CARD,
				NO_CARD, NO_CARD, NO_CARD,
				NO_CARD, NO_CARD, NO_CARD);

		assert (testFaceUpCards.equals(actualFaceUpCards));

		// Testing player 2 - all dice discs occupied
		resetPlayerBoard(roma);
		
		// Placing Consiliarius on all discs
		roma.layCard(CONSILIARIUS, DiscCode.BRIBE_DISC);
		roma.layCard(CONSILIARIUS, DISC1);
		roma.layCard(CONSILIARIUS, DISC2);
		roma.layCard(CONSILIARIUS, DISC3);
		roma.layCard(CONSILIARIUS, DISC4);
		roma.layCard(CONSILIARIUS, DISC5);
		roma.layCard(CONSILIARIUS, DISC6);

		testFaceUpCards = test1.getFaceUpCards();
		actualFaceUpCards = setActualFaceUpCards(CONSILIARIUS,
				CONSILIARIUS, CONSILIARIUS, CONSILIARIUS,
				CONSILIARIUS, CONSILIARIUS, CONSILIARIUS);

		assert (testFaceUpCards.equals(actualFaceUpCards));

	}

	public String toString() {
		return "Get Face Up Card Test";
	}

	private void resetPlayerBoard(RomaEngine roma) {
		roma.layCard(NO_CARD, DiscCode.BRIBE_DISC);
		roma.layCard(NO_CARD, DISC1);
		roma.layCard(NO_CARD, DISC2);
		roma.layCard(NO_CARD, DISC3);
		roma.layCard(NO_CARD, DISC4);
		roma.layCard(NO_CARD, DISC5);
		roma.layCard(NO_CARD, DISC6);
	}

	private int[] setActualFaceUpCards(int card0, int card1, int card2,
			int card3, int card4, int card5, int card6) {
		int[] actualFaceUpCards = new int[7];
		actualFaceUpCards[DiscCode.BRIBE_DISC] = card0;
		actualFaceUpCards[DISC1] = card1;
		actualFaceUpCards[DISC2] = card2;
		actualFaceUpCards[DISC3] = card3;
		actualFaceUpCards[DISC4] = card4;
		actualFaceUpCards[DISC5] = card5;
		actualFaceUpCards[DISC6] = card6;

		return actualFaceUpCards;
		*/
	}

	@Override
	public String toString() {
		return "testGetFaceUpCards is borked";
	}
}
