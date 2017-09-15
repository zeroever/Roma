package tests.unitTests;

import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.main.CardPicker;
import tests.userProgram.Roma;
import tests.main.CardCode;
import static tests.main.CardCode.*; 

/*
 * Written by wslo235 & aaak656 [Wed14Banjo]
 * Compiled, Tested and Passed by mtruong
 */

public class LohKarimGGTestEssedum extends RomaTest{
    
    private int argsEmpty[] = {};
    
    @Override
    public String toString() {
        return "WED14BANJO : Test Essedum";
    }
    
    @Override
    public void run() {
        TestWithOneEssedum();
      TestWithTwoEssedum();
    }

    private void TestWithOneEssedum() {
        RomaEngine r =  new Roma();
        
        int[] deck = {
                NERO,
                ESSEDUM,
                LEGIONARIUS,
                TRIBUNUS_PLEBIS,
                BASILICA,
                ARCHITECTUS,
                MERCATUS,
                LEGAT,
                PRAETORIANUS,
                LEGIONARIUS,
                FORUM,
                CONSILIARIUS,
                SENATOR,
                SENATOR,
                  };
        
        // initial round
        r.setDrawPile (deck);
        TestablePlayer p1 = r.getPlayer (1);
        TestablePlayer p2 = r.getPlayer (2);
        assert(p1.getMoney() == 0);
        assert(p2.getMoney() == 0);
        
        // set VP for players
        p1.setVictoryPoints (7);
        p2.setVictoryPoints (10);
        assert(p1.getVictoryPoints() == 7);
        assert(p2.getVictoryPoints() == 10);
        
        // give players cards (not from game deck)
        int[] faceUpCards1 = {ESSEDUM, NO_CARD,SENATOR, LEGIONARIUS,
                NO_CARD, LEGIONARIUS, NO_CARD};
        p1.setFaceUpCards(faceUpCards1);
        
        assert(p1.getFaceUpCards()[0] == ESSEDUM);
        assert(p1.getFaceUpCards()[1] == NO_CARD);
        assert(p1.getFaceUpCards()[2] == SENATOR);
        assert(p1.getFaceUpCards()[3] == LEGIONARIUS);
        assert(p1.getFaceUpCards()[4] == NO_CARD);
        assert(p1.getFaceUpCards()[5] == LEGIONARIUS);
        assert(p1.getFaceUpCards()[6] == NO_CARD);
        
        int[] faceUpCards2 = {GLADIATOR, SICARIUS, NO_CARD, BASILICA,
                NO_CARD, NERO, NO_CARD};
        p2.setFaceUpCards(faceUpCards2);
        
        assert(p2.getFaceUpCards()[0] == GLADIATOR);
        assert(p2.getFaceUpCards()[1] == SICARIUS);
        assert(p2.getFaceUpCards()[2] == NO_CARD);
        assert(p2.getFaceUpCards()[3] == BASILICA);
        assert(p2.getFaceUpCards()[4] == NO_CARD);
        assert(p2.getFaceUpCards()[5] == NERO);
        assert(p2.getFaceUpCards()[6] == NO_CARD);
        
        /* ROUND 1: player1's turn
         * Player1 roll dice and activate essedum
         * and attack one card
         */
        int dice1[] = {1,6,6};
        r.setAvailableActionDice (dice1);
        assert(r.getAvailableActionDice().length == 3);
        
        // activate 1 essedum with empty args
        r.activateCard(1, argsEmpty);
        assert(r.getAvailableActionDice().length == 2);
        
        r.rigBattleDie(6);
        // NERO is NOT killed
        r.activateCard(6, argsEmpty);
        // discardPile should be empty
        int[] discardPile1 = {};
        assert(isPermutation(r.getDiscardPile(), discardPile1));
        // check p2 faceUpCards[3]
        assert(p2.getFaceUpCards()[5] == NERO);
        assert(r.getAvailableActionDice().length == 1);
        // take money
        r.useActionDieForMoney(6);
        assert(p1.getMoney() == 6);
        assert(r.getAvailableActionDice().length == 0);
        // player1 end turn
        r.endCurrentTurn();
        
        /* ROUND 1: player2's turn
         * Player2 roll dice, take and lay 2 cards
         */
        // 10VP minus 3 empty slots
        assert(p2.getVictoryPoints() == 7);
        int dice2[] = {1,6,1};
        r.setAvailableActionDice (dice2);
        assert(r.getAvailableActionDice().length == 3);
        
        //take money
        assert(p2.getMoney() == 0);
        r.useActionDieForMoney(6);
        assert(p2.getMoney() == 6);
        assert(r.getAvailableActionDice().length == 2);
        
        // draw 2 cards
        r.useActionDieForCards(1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.SENATOR; }
            } );
        assert(r.getAvailableActionDice().length == 1);
        r.useActionDieForCards(1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.SENATOR; }
            } );
        assert(r.getAvailableActionDice().length == 0);
        // check p2 hand
        int expectedHand[] = {SENATOR, SENATOR};
        assert(isPermutation(p2.getHand(), expectedHand));
        
        // lay both cards
        r.layCard(SENATOR, 3);
        r.layCard(SENATOR, 5);
        assert(p2.getFaceUpCards()[2] == SENATOR);
        assert(p2.getFaceUpCards()[4] == SENATOR);
        int emptyHand[] = {};
        assert(isPermutation(p2.getHand(), emptyHand));
        assert(p2.getMoney() == 0);
        
        // p2 end turn
        r.endCurrentTurn();
        
        /* ROUND 2: player1's turn
         * Player1 draw and lay 1 card
         * activate essedum and attack one card
         */
        // 7VP minus 3 empty slots
        assert(p1.getVictoryPoints() == 4);
        int dice3[] = {1,1,4};
        r.setAvailableActionDice (dice3);
        assert(r.getAvailableActionDice().length == 3);
        
        // draw and lay a card
        r.useActionDieForCards(1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.CONSILIARIUS; }
        } );
        assert(r.getAvailableActionDice().length == 2);
        r.layCard(CONSILIARIUS, 2);
        assert(p1.getFaceUpCards()[1] == CONSILIARIUS);
        assert(p1.getMoney() == 2);
        
        // activate essedum
        r.activateCard(1, argsEmpty);
        assert(r.getAvailableActionDice().length == 1);
        
        // attack using LEGIONARIUS
        // BASILICA is discarded (lose the battle)
        r.rigBattleDie(3);
        r.activateCard(4, argsEmpty);
        int discardPile2[] = {BASILICA};
        assert(isPermutation(r.getDiscardPile(), discardPile2));
        assert(p2.getFaceUpCards()[3] == NO_CARD);
        assert(r.getAvailableActionDice().length == 0);
        
        //p1 end turn
        r.endCurrentTurn();
        
        /* ROUND 2: player2's turn
         */
        // 7VP minus 2 empty slots
        assert(p2.getVictoryPoints() == 5);
        int dice4[] = {6,2,1};
        r.setAvailableActionDice (dice4);
        assert(r.getAvailableActionDice().length == 3);
        
        //take money
        r.useActionDieForMoney(6);
        assert(r.getAvailableActionDice().length == 2);
        assert(p2.getMoney() == 6);
        
        // draw a card
        r.useActionDieForCards(1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.FORUM; }
            } );
        // lay a card
        r.layCard(FORUM, 4);
        assert(p2.getFaceUpCards()[3] == FORUM);
        assert(p2.getMoney() == 1 );
        assert(r.getAvailableActionDice().length == 1);
        // check p2 hand
        int expectedHand2[] = {};
        assert(isPermutation(p2.getHand(), expectedHand2));
        
        // activate sicarius to destroy essedum
        int argsSicarius[] = {1};
        r.activateCard(2, argsSicarius);
        int discardPile3[] = {BASILICA, SICARIUS, ESSEDUM}; 
        assert(isPermutation(r.getDiscardPile(), discardPile3));
        assert(r.getAvailableActionDice().length == 0);
        
        //p2 end turn
        r.endCurrentTurn();
        
        /* ROUND 3: player1's turn
         * Player1
         */
        // 4VP minus 3 empty slots
        assert(p1.getVictoryPoints() == 1);
        int dice5[] = {4,4,1};
        r.setAvailableActionDice (dice5);
        assert(r.getAvailableActionDice().length == 3);
        
        // 1st attack w/out Essedum, Legionarius lose
        r.rigBattleDie(4);
        r.activateCard(4, argsEmpty);
        assert(p2.getFaceUpCards()[3] == FORUM);
        assert(r.getAvailableActionDice().length == 2);
        
        // 2nd attack w/out Essedum, Legionarius wins
        r.rigBattleDie(5);
        r.activateCard(4, argsEmpty);
        int discardPile4[] = {BASILICA, SICARIUS, ESSEDUM, FORUM}; 
        assert(isPermutation(r.getDiscardPile(), discardPile4));
        assert(r.getAvailableActionDice().length == 1);
        
        // p1 just end the turn
        r.endCurrentTurn();
        
        /* ROUND 3: player2's turn
         */
        // 5VP minus 3 empty slots
        assert(p2.getVictoryPoints() == 2);
        int dice6[] = {4,4,1};
        r.setAvailableActionDice (dice6);
        assert(r.getAvailableActionDice().length == 3);
        // p2 just end turn, tired with playing =="
        r.endCurrentTurn();
        
        // gameOver since p1 vp == 0
        // 1vp minus 3 empty slots
        assert(r.isGameOver());

    }

    private void TestWithTwoEssedum() {
        RomaEngine r = new Roma();
        int argsEmpty[] = {};
        
        int[] deck = {
                NERO,
                ESSEDUM,
                LEGIONARIUS,
                TRIBUNUS_PLEBIS,
                BASILICA,
                ARCHITECTUS,
                MERCATUS,
                LEGAT,
                PRAETORIANUS,
                LEGIONARIUS,
                FORUM,
                CONSILIARIUS,
                SENATOR,
                SENATOR,
                MERCATUS,
                  };
        
        // initial round
        r.setDrawPile (deck);
        TestablePlayer p1 = r.getPlayer (1);
        TestablePlayer p2 = r.getPlayer (2);
        assert(p1.getMoney() == 0);
        assert(p2.getMoney() == 0);
        
        // set VP for players
        p1.setVictoryPoints (7);
        p2.setVictoryPoints (10);
        assert(p1.getVictoryPoints() == 7);
        assert(p2.getVictoryPoints() == 10);
        
        // give players cards (not from game deck)
        int[] faceUpCards1 = {ESSEDUM, ESSEDUM,NO_CARD, LEGIONARIUS,
                NO_CARD,LEGIONARIUS, NO_CARD};
        p1.setFaceUpCards(faceUpCards1);
        
        assert(p1.getFaceUpCards()[0] == ESSEDUM);
        assert(p1.getFaceUpCards()[1] == ESSEDUM);
        assert(p1.getFaceUpCards()[2] == NO_CARD);
        assert(p1.getFaceUpCards()[3] == LEGIONARIUS);
        assert(p1.getFaceUpCards()[4] == NO_CARD);
        assert(p1.getFaceUpCards()[5] == LEGIONARIUS);
        assert(p1.getFaceUpCards()[6] == NO_CARD);
        
        int[] faceUpCards2 = {GLADIATOR, SICARIUS, NO_CARD, BASILICA,
                NO_CARD, NERO, NO_CARD};
        p2.setFaceUpCards(faceUpCards2);
        
        assert(p2.getFaceUpCards()[0] == GLADIATOR);
        assert(p2.getFaceUpCards()[1] == SICARIUS);
        assert(p2.getFaceUpCards()[2] == NO_CARD);
        assert(p2.getFaceUpCards()[3] == BASILICA);
        assert(p2.getFaceUpCards()[4] == NO_CARD);
        assert(p2.getFaceUpCards()[5] == NERO);
        assert(p2.getFaceUpCards()[6] == NO_CARD);
        
        /* ROUND 1: player1's turn
         * Player1 roll dice and activate essedum
         */
        int dice1[] = {1,2,4};
        r.setAvailableActionDice (dice1);
        assert(r.getAvailableActionDice().length == 3);
        
        // activate 2 essedum with empty args
        r.activateCard(1, argsEmpty);
        assert(r.getAvailableActionDice().length == 2);
        r.activateCard(2, argsEmpty);
        assert(r.getAvailableActionDice().length == 1);
        
        // set battle die to 1
        // activate LEGIONARIUS to attack BASILICA (defends value of 1)
        r.rigBattleDie(1);
        // BASILICA is killed and discarded into discard pile
        r.activateCard(4, argsEmpty);
        // discardPile should have BASILICA now
        int[] discardPile1 = {BASILICA};
        assert(isPermutation(r.getDiscardPile(), discardPile1));
        // check p2 faceUpCards[3]
        assert(p2.getFaceUpCards()[3] == NO_CARD);
        // check all dice used
        assert(r.getAvailableActionDice().length == 0);
        // player1 end turn
        r.endCurrentTurn();
        
        /* ROUND1: player2's turn
         * player2 takes money and end turn
         */
        // 10 VP minus 4 empty slots
        assert(p2.getVictoryPoints() == 6);
        int dice2[] = {6,6,1};
        r.setAvailableActionDice (dice2);
        assert(r.getAvailableActionDice().length == 3);
        // take 12 Sestertii using 2 dice with value of 6
        r.useActionDieForMoney (6);
        assert(p2.getMoney() == 6);
        assert(r.getAvailableActionDice().length == 2);
        r.useActionDieForMoney(6);
        assert(p2.getMoney() == 12);
        assert(r.getAvailableActionDice().length == 1);
        
        //draw one card
        r.useActionDieForCards(1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.MERCATUS; }
            } );
        assert(p2.getHand()[0] == MERCATUS);
        r.layCard(MERCATUS, 4);
        // 12 - 6 = 6 (money);
        assert(r.getCost(MERCATUS)== 6);
        assert(p2.getMoney() == 6);
        assert(p2.getFaceUpCards()[3] == MERCATUS);
        // player2 end turn
        r.endCurrentTurn();
        
        /* ROUND2: player1's turn
         * Activate 2 essendum to attack nero and lose the battle
         */
        // 7 VP minus 3 empty slots
        assert(p1.getVictoryPoints() == 4);
        int dice3[] = {1,2,6};
        r.setAvailableActionDice (dice3);
        assert(r.getAvailableActionDice().length == 3);
        
        // activate 2 essedum with empty args
        r.activateCard(1, argsEmpty);
        assert(r.getAvailableActionDice().length == 2);
        r.activateCard(2, argsEmpty);
        assert(r.getAvailableActionDice().length == 1);
        
        // set battle die to 4
        // activate LEGIONARIUS to attack NERO (defends value of 9-4 =5)
        r.rigBattleDie(4);
        // NERO is NOT killed (nothing happens)
        r.activateCard(6, argsEmpty);

        // DiscardPile still the same
        assert(r.getDiscardPile()[0] == BASILICA);
        assert(isPermutation(r.getDiscardPile(), discardPile1));
        // check p2 faceUpCards[6]
        assert(p2.getFaceUpCards()[5] == NERO);
        // Since LEGIONARIUS is activated, dice is used even if it loses the battle
        assert(r.getAvailableActionDice().length == 0);
        // player1 end turn
        r.endCurrentTurn();
        
        /* ROUND2: player2's turn
         * activate nero and kill one essedum
         */
        // 6 VP minus 3 empty slots
        assert(p2.getVictoryPoints() == 3);
        int dice4[] = {2,1,1};
        r.setAvailableActionDice (dice4);
        assert(r.getAvailableActionDice().length == 3);
        
        //activate sicarius and kill one essedum from p1 [dicedisc 1]
        int argsSicarius[] = {1};
        r.activateCard(2, argsSicarius);
        assert(r.getAvailableActionDice().length == 2);
        
        // check discardPile
        int[] discardPile2 = {BASILICA, SICARIUS, ESSEDUM};
        assert(isPermutation(r.getDiscardPile(), discardPile2));
        
        // check p1 and p2 dicedisc
        assert(p1.getFaceUpCards()[0] == NO_CARD);
        assert(p2.getFaceUpCards()[1] == NO_CARD);
        
        // p2 draw 2 cards and lay both cards
        r.useActionDieForCards (1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.SENATOR; }
            } );
        assert(r.getAvailableActionDice().length == 1);
        
        r.useActionDieForCards (1, new CardPicker() { 
            public int pickCard(int cards[]) { 
                return CardCode.SENATOR; }
            } );
        assert(r.getAvailableActionDice().length == 0);
        
        int[] expectedHand = {SENATOR,SENATOR};
        assert(isPermutation(p2.getHand(), expectedHand));

        // p2 lay SENATOR on dicedisc 3
        assert(p2.getMoney() == 6);
        r.layCard(SENATOR, 3);
        assert(r.getCost(SENATOR)== 3);
        // 6 - 3 = 3
        assert(p2.getMoney() == 3);
        assert(p2.getFaceUpCards()[2] == SENATOR);

        // lay another SENATOR on dicedisc 7
        assert(p2.getMoney() == 3);
        r.layCard(SENATOR, 7);
        assert(r.getCost(SENATOR)== 3);
        // 3 - 3 = 0
        assert(p2.getMoney() == 0);
        assert(p2.getFaceUpCards()[6] == SENATOR);
        //player2 end turn
        r.endCurrentTurn();
        
        // gameOver is true because p1 VP = 0
        // 4VP minus 4 empty slots 
        assert(r.isGameOver());
    }

}
