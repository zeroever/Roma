package tests.acceptanceTests;

import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;
import tests.main.*;
import tests.userProgram.Roma;


// From your local Doner/Chicken kebab masters,
// Mon13Oboe.

public class FullySickBadassKebabTest extends RomaTest {
    public void run() {
        RomaEngine r = new Roma();

        int[] deck = {
                FORUM,
                MERCATOR,
                TEMPLUM,
                TURRIS,
                BASILICA,
                ARCHITECTUS,
                ARCHITECTUS,
                LEGAT,PRAETORIANUS,
                LEGIONARIUS,
                FORUM,
                CONSILIARIUS,
                CENTURIO,
                FORUM,
                BASILICA,
                FORUM
                  };

        r.setDrawPile (deck);
        TestablePlayer p1 = r.getPlayer (PLAYER1);
        TestablePlayer p2 = r.getPlayer (PLAYER2);
        p1.setVictoryPoints (1);
        p2.setVictoryPoints (1);

        int hand1[] = {MERCATUS, FORUM, TURRIS, MERCATOR};
        p1.setHand (hand1);
        assert(isPermutation(p1.getHand(), hand1));
        
        assert(r.getCurrentPlayer() == PLAYER1);
        
        
        int money = 0;
        for (int i = 0; i < hand1.length; i++) {
        	int card = hand1[i];
        	money += r.getCost(card);
        }
        p1.setMoney(money);
        assert(p1.getMoney() == money);

        // MERCATUS-TURRIS inclusive
        for (int i = 0; i < 3; i++) {
        	int card = hand1[i];
        	r.layCard (card, DISC1 + i);
            money -= r.getCost(card);
            assert(p1.getMoney() == money): "expected money " + money +
            	" actual "+p1.getMoney();       
        }
        
        r.layCard (MERCATOR, BRIBE_DISC);
        money-= r.getCost(MERCATOR);
        assert(p1.getMoney() == money): "expected money " + money +
    	" actual "+p1.getMoney();
        
        int[] faceUpCards = p1.getFaceUpCards();
        assert(faceUpCards[0]== MERCATUS);
        assert(faceUpCards[1]== FORUM);
        assert(faceUpCards[2]== TURRIS);
        assert(faceUpCards[3]== NO_CARD);
        assert(faceUpCards[4]== NO_CARD);
        assert(faceUpCards[5]== NO_CARD);
        assert(faceUpCards[6]== MERCATOR);
        int dice1[] = {4,5,5};
        r.setAvailableActionDice (dice1);
        r.useActionDieForMoney (4);
        r.useActionDieForMoney (5);
        assert(p1.getMoney() == 9);
        assert(r.getAvailableActionDice().length == 1);
        
        int vpToBuy = 1;
        int diceValue = 5;
        int[] args = {diceValue, vpToBuy};
        r.activateCard (BRIBE_DISC, args);
        
        int p1Moneynow = 9 - ((vpToBuy * 2) +  diceValue);
        
        assert(p1.getMoney() == p1Moneynow);
        assert(p2.getMoney() == 2);
        assert(p1.getVictoryPoints() == 2);
        assert(p2.getVictoryPoints() == 0);
        r.endCurrentTurn ();
        assert(r.isGameOver ());
    }

    public void test2() {
        RomaEngine r = new Roma();

        int[] deck = {
                CONSUL,
                GLADIATOR,
                MERCATOR,
                SCAENICUS,
                LEGIONARIUS,
                GLADIATOR,
                CONSILIARIUS,
                CONSILIARIUS,
                PRAETORIANUS,
                HARUSPEX,
                FORUM,
                ESSEDUM,
                SENATOR,
                CENTURIO,
                SENATOR,
                TRIBUNUS_PLEBIS,
                ONAGER,
                LEGAT,
                LEGIONARIUS,
                ONAGER,
                VELITES,
                MACHINA,
                LEGIONARIUS,
                FORUM,
                SICARIUS,
                MACHINA,
                PRAETORIANUS,
                FORUM,
                TURRIS,
                TRIBUNUS_PLEBIS,
                FORUM
                     };
        r.setDrawPile (deck);
        TestablePlayer p1 = r.getPlayer (1);
        TestablePlayer p2 = r.getPlayer (2);
        p1.setVictoryPoints (7);
        p2.setVictoryPoints (10);

        // basilica | forum | templum | .....

        int dice1[] = {6,6,6};
        r.setAvailableActionDice(dice1);
        int hand1[] = {BASILICA, FORUM, TEMPLUM, MERCATOR};
        p1.setHand(hand1);
        p1.setMoney(20);
        r.layCard (BASILICA, 5);
        r.layCard (FORUM, 6);
        r.layCard (TEMPLUM, 7);
        r.layCard (MERCATOR, 1);

        int args[] = {6, 6};
        r.activateCard (6,args);
        assert(p1.getVictoryPoints() == (7+14));
        assert(r.getVictoryPointsStockpile() == 5);
        r.endCurrentTurn();
        assert(p2.getVictoryPoints() == 3);
        p2.setVictoryPoints(2);
        r.endCurrentTurn();
        r.endCurrentTurn();
        assert(r.isGameOver());
    }


	@Override
	public String toString() {
		return "FullySickBadassKebabTest...";
	}
}
