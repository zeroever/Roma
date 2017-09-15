package tests.unitTests;

import java.util.Arrays;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;

public class TestSetFaceUpCards extends RomaTest {
	public String toString () {
		return "Simple Hand Test";
	}
	
	public void run () {
		test1();
		test2();
		test3();
	}

	private void test1() {
		RomaEngine roma = new Roma ();				
		TestablePlayer player1 = roma.getPlayer (PLAYER1);
		 
		int[] toBePlaced = {							
			NO_CARD, LEGAT, GLADIATOR,
			ESSEDUM, SCAENICUS, HARUSPEX,
			NO_CARD
		};													
		
		player1.setFaceUpCards(toBePlaced);		
		
		int[] result = player1.getFaceUpCards();
		assert(Arrays.equals(result, toBePlaced));
	}

	private void test2() {
		RomaEngine roma = new Roma ();				
		TestablePlayer player2 = roma.getPlayer (PLAYER2);
		
		int[] toBePlaced = {							
			NO_CARD,							
			NO_CARD,
			NO_CARD,								
			NO_CARD,								
			NO_CARD,								
			NO_CARD,								
			NO_CARD								
		};													
		
		player2.setFaceUpCards(toBePlaced);		
		
		int[] result = player2.getFaceUpCards();
		assert(Arrays.equals(result, toBePlaced));
	}

	private void test3() {
		RomaEngine roma = new Roma ();				
		TestablePlayer player2 = roma.getPlayer (PLAYER2);
		
		int[] toBePlaced = {							
			FORUM, ONAGER, BASILICA,
			TURRIS,
			TEMPLUM,
			MACHINA,
			AESCULAPINUM
		};													
		
		player2.setFaceUpCards(toBePlaced);		
		
		int[] result = player2.getFaceUpCards();	
		assert(Arrays.equals(result, toBePlaced));
	}
}
