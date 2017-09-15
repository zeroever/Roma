package tests.acceptanceTests;
import tests.main.*;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.DiscCode.*;

/**
 * 
 * GROUP:       Thracian Slaves
 * TUTE:        Thu17Bell
 * CODED BY:    Natalie Wong & Justin Wong
 * 
 * LAST EDIT:   18th MAY, 11:30PM
 * 
 */

public class ThracianSlavesTest extends RomaTest{

    public String toString() {
        return "Thracian Slaves' Acceptance Test";
    }
    
    public void run() {
        RomaEngine roma = new Roma();
                
        int[] deck = {
                MACHINA,
                VELITES,
                TEMPLUM,
                ONAGER,
                SCAENICUS,
                ESSEDUM, 
                LEGIONARIUS,
                TRIBUNUS_PLEBIS,
                LEGAT,
                CENTURIO,    // added since player picked up 2 cards but chose legat
                TRIBUNUS_PLEBIS,
                GLADIATOR,
                HARUSPEX,
                GLADIATOR,
                MERCATOR,
                SCAENICUS
        };
        
        roma.setDrawPile(deck);
        
        TestablePlayer player1 = roma.getPlayer(1);
        TestablePlayer player2 = roma.getPlayer(2);
        
        
        // INITIAL STATE 
        
        player1.setVictoryPoints(10);
        player2.setVictoryPoints(10);
        
        player1.setMoney(0);
        player2.setMoney(0);
        
        assert(!roma.isGameOver());
        
        int[] player1Hand = {
                TEMPLUM,
                MERCATOR,
                LEGAT,
                TURRIS
        };
        
        int[] player2Hand = {
                ESSEDUM,
                FORUM,
                ARCHITECTUS,
                BASILICA
        };
        
        player1.setHand(player1Hand);
        player2.setHand(player2Hand);
        
        int[] player1Board = {
                NO_CARD, NO_CARD, NO_CARD,
                NO_CARD, NO_CARD, NO_CARD,
                NO_CARD
        };
        
        int[] player2Board = {
                NO_CARD, NO_CARD, NO_CARD,
                NO_CARD, NO_CARD, NO_CARD,
                NO_CARD
        };
        
        player1.setFaceUpCards(player1Board);
        player2.setFaceUpCards(player2Board);
        
        roma.setCurrentPlayer(1);
        
        // player1Board 
        /*  1   -
         *  2   TEMPLUM
         *  3   MERCATOR
         *  4   -
         *  5   LEGAT
         *  6   TURRIS
         *  7   -       
         */
        roma.layCard(TEMPLUM, DISC2);
        roma.layCard(MERCATOR, DISC3);
        roma.layCard(LEGAT, DISC5);
        roma.layCard(TURRIS, DISC6);
        
        //player2Board
        /* 1    FORUM
         * 2    BASILICA
         * 3    ESSEDUM
         * 4    ARCHITECTUS
         * 5    -
         * 6    -
         * 7    -
         */
        roma.layCard(FORUM, DISC1);
        roma.layCard(BASILICA, DISC2);
        roma.layCard(ESSEDUM, DISC3);
        roma.layCard(ARCHITECTUS, DISC4);
        
        // GAME BEGIN!!!
        
        // ----- ROUND 1
        
        assert(player1.getVictoryPoints() == 10);
        assert(player2.getVictoryPoints() == 10);
        assert(player1.getMoney() == 0);
        assert(player2.getMoney() == 0);
        
        //player 1's turn, round 1
        int dice1[] = {1,1,4};
        roma.setAvailableActionDice(dice1);
        // player picks up Machina & Velites using action dice
        roma.useActionDieForCards(1, new MyCardPicker(MACHINA));
        roma.useActionDieForCards(1, new MyCardPicker(VELITES));
        // player gets money from last action die
        roma.useActionDieForMoney(4);
        roma.endCurrentTurn();
        
        //player 2's turn, round 1
        int dice2[] = {6,6,3};
        roma.setAvailableActionDice(dice2);
        // player picks up Templum, Onager & Scaenicus
        roma.useActionDieForCards(3, new MyCardPicker(SCAENICUS));
        roma.useActionDieForCards(6, new MyCardPicker(AESCULAPINUM));
        roma.useActionDieForMoney(6);
        roma.layCard(AESCULAPINUM, DISC5);
        roma.endCurrentTurn();
        
        // ---- ROUND 2
        
        assert(player1.getMoney() == 4);
        assert(player1.getVictoryPoints() == 7);
        
        assert(player2.getMoney() == 1);
        assert(player2.getVictoryPoints() == 7);
        
        // player1
        /*  1   -
         *  2   TEMPLUM
         *  3   MERCATOR
         *  4   -
         *  5   LEGAT
         *  6   TURRIS
         *  7   -
         *  in hand: VELITES, MACHINA   
         */
        
        //player2
        /* 1    FORUM
         * 2    BASILICA
         * 3    ESSEDUM
         * 4    ARCHITECTUS
         * 5    AESCULAPINUM
         * 6    -
         * 7    -
         * in hand: SCAENICUS
         */
        
        //player 1's turn, round 2
        int dice3[] = {5,5,1};
        roma.setAvailableActionDice(dice3);
        roma.useActionDieForMoney(5);        // gain 5 money
        roma.useActionDieForMoney(5);        // gain 5 money
        roma.layCard(VELITES, BRIBE_DISC);    // lose 5 money
        roma.rigBattleDie(2);
        int[] args1 = { 1,DISC3 };
        // activate bribe disc                 lose 1 money
        roma.activateCard(BRIBE_DISC, args1);    //  i.e. VELITES attacks ESSEDUM & fails
        roma.endCurrentTurn();
        
        //player 2's turn, round 2
        int dice4[] = {1,2,5};
        roma.setAvailableActionDice(dice4);
        roma.activateCard(DISC1, new int[5]);    // activate FORUM, uses die 5 
        // activation of basilica is implied
        roma.useActionDieForCards(DISC2, new MyCardPicker(LEGAT));
        roma.endCurrentTurn();

        assert(player1.getMoney() == 8);
        assert(player1.getVictoryPoints() == 4);
        
        assert(player2.getMoney() == 1);
        assert(player2.getVictoryPoints() == 12);
        
        // ---- ROUND 3
        
        // player1
        /*  1   -
         *  2   TEMPLUM
         *  3   MERCATOR
         *  4   -
         *  5   LEGAT
         *  6   TURRIS
         *  7   VELITES
         *  in hand: MACHINA    
         */
        
        //player2
        /* 1    FORUM
         * 2    BASILICA
         * 3    ESSEDUM
         * 4    ARCHITECTUS
         * 5    AESCULAPINUM
         * 6    -
         * 7    -
         * in hand: SCAENICUS, LEGAT
         */
        
        //player 1's turn, round 3
        int dice5[] = {6,3,1};
        roma.setAvailableActionDice(dice5);
        roma.useActionDieForMoney(6);            // gain 6 money
        roma.activateCard(DISC3, new int[1]);   // buys 1 VP with 2 money of player 2
        roma.rigBattleDie(5);                
        int[] args2 = { 1, DISC3 };
        // activate bribe disc                  // lose 1 money
        roma.activateCard(BRIBE_DISC, args2);    // i.e. VELITES attacks ESSEDUM & is successful
        roma.endCurrentTurn();
        
        // player 2's turn, round 3
        int dice6[] = {2,2,1};
        roma.setAvailableActionDice(dice6);
        roma.useActionDieForMoney(2);            // gain 2 money
        roma.activateCard(DISC1, new int[2]);    // activate FORUM, uses die 1 & 2
        roma.endCurrentTurn();

        assert(player1.getMoney() == 11);
        assert(player1.getVictoryPoints() == 3);
        
        assert(player2.getMoney() == 5);
        assert(player2.getVictoryPoints() == 8);
        
        // ---- ROUND 4
        
        // player1
        /*  1   -
         *  2   TEMPLUM
         *  3   MERCATOR
         *  4   -
         *  5   LEGAT
         *  6   TURRIS
         *  7   VELITES
         *  in hand: MACHINA    
         */
        
        //player2
        /* 1    FORUM
         * 2    BASILICA
         * 3    -
         * 4    ARCHITECTUS
         * 5    AESCULAPINUM
         * 6    -
         * 7    -
         * in hand: SCAENICUS, LEGAT
         */
        
        //player 1's turn, round 4
        int dice7[] = {1,1,4};
        roma.setAvailableActionDice(dice7);
        roma.rigBattleDie(3);
        int[] args3 = {1,DISC4};        // uses die 1 on bribe disc, lose 1 money
        roma.activateCard(BRIBE_DISC, args3);    // i.e. VELITES attacks AESCULAPINUM & fails
        roma.useActionDieForCards(1, new MyCardPicker(TRIBUNUS_PLEBIS));
        roma.useActionDieForMoney(4);    // gain 4 money
        roma.endCurrentTurn();
        
        //player 2's turn, round 4
        int dice8[] = {6,6,3};
        roma.setAvailableActionDice(dice8);
        roma.useActionDieForCards(3, new MyCardPicker(GLADIATOR));
        roma.useActionDieForMoney(6);    // gain 6 money
        roma.layCard(GLADIATOR, DISC6);    // lose 6 money
        int[] args4 = {DISC3};
        roma.activateCard(DISC6, args4); // sends opponent's MERCATOR back to hand
        roma.endCurrentTurn();
        
        assert(player1.getMoney() == 14);
        assert(player1.getVictoryPoints() == 1);
        
        assert(player2.getMoney() == 11);
        assert(player2.getVictoryPoints() == 2);
        
        // ---- ROUND 5
        
        // player1
        /*  1   -
         *  2   TEMPLUM
         *  3   -
         *  4   -
         *  5   LEGAT
         *  6   TURRIS
         *  7   VELITES
         *  in hand: MACHINA, MERCATOR, TRIBUNUS_PLEBIS
         */
        
        //player2
        /* 1    FORUM
         * 2    BASILICA
         * 3    -
         * 4    ARCHITECTUS
         * 5    AESCULAPINUM
         * 6    GLADIATOR
         * 7    -
         * in hand: SCAENICUS, LEGAT
         */
        
        //player 1's turn, round 5
        
        int dice9[] = {3,3,2};
        roma.setAvailableActionDice(dice9);
        roma.useActionDieForCards(2, new MyCardPicker(SCAENICUS));
        roma.layCard(MACHINA, DISC4);    // lose 4 money
        roma.useActionDieForMoney(3);    // gain 3 money
        roma.useActionDieForMoney(3);    // gain 3 money
        roma.layCard(TRIBUNUS_PLEBIS, DISC3);    // lose 3 money
        roma.endCurrentTurn();
        
        int dice10[] = {6,6,6};
        roma.setAvailableActionDice(dice10);
        int[] args5 = {3};        
        roma.activateCard(DISC6, args5);    // returns opponent's TRIBUNUS_PLEBIS to hand
        int[] args6 = {5};
        roma.activateCard(DISC6, args6);    // returns opponent's LEGAT to hand
        int[] args7 = {7};
        roma.activateCard(DISC6, args7);    // returns opponenet's VELITES to hand
        roma.endCurrentTurn();
        
        // GAME OVER, PLAYER 2 LOSES (no more victory points left)
        assert roma.isGameOver();    
        
    }
    
    public class MyCardPicker implements CardPicker {
        int cardNum;
        MyCardPicker(int cardNum) {
            this.cardNum = cardNum;
        }
        public int pickCard(int[] cards) {
            return cardNum;
        }
    }
}
