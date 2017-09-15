package tests.acceptanceTests;
//import static tests.main.DiscCode.*;
import static tests.main.CardCode.*; 
//import static tests.main.PlayerCode.*;

import tests.main.*;
import tests.userProgram.Roma;



 
 /** Edit by Minh Le
  *  changed the null activations to empty args instead
  *  unhandled null pointers in activation implementation 
  *  will cause errors, otherwise small change.
  *  This change is in compliance with AcceptanceTest Card specs:
  *  WRONG arguments
  *     -you MUST NOT pass null pass null as the args array (empty means args = new int[0]) 
  **/

/**
 * Edited again by Saki Tang to fix bugs and to make it of type RomaTest
 * assertTrue replaced with assert to prevent need to import junit
 * more asserts were included
 * random comments inserted to point out issues/clarify things
 * only the first test, now renamed run, has been debugged
 */

public class Wed09OboeTests extends RomaTest{
    
    public void run() {
        RomaEngine r = new Roma();
        
        //the order here matters when drawing cards
        //an extra cards was added because the deck should not be
        //empty after drawing all the relevant cards
        //the extra card is to stop the discard pile being shuffled into the deck
        int[] deck = {
                TEMPLUM, //added card to prevent discard pile disappearing
                VELITES,
                AESCULAPINUM,
                SCAENICUS,
                TRIBUNUS_PLEBIS,
                BASILICA,
                ARCHITECTUS,
                ARCHITECTUS,
                LEGAT,
                PRAETORIANUS,
                LEGIONARIUS,
                FORUM,
                CONSILIARIUS,
                CENTURIO,
                FORUM,
                BASILICA,
                FORUM
                  };
        r.setDrawPile (deck);
        TestablePlayer p1 = r.getPlayer (1);
        TestablePlayer p2 = r.getPlayer (2);
        p1.setVictoryPoints (7);
        p2.setVictoryPoints (10);
        
        //asserts inserted
        assert(p1.getVictoryPoints() == 7);
        assert(p2.getVictoryPoints() == 10);
        
        //code below is buggy
        /*int hand1[] = {MERCATUS, CONSUL, SICARIUS, MERCATOR};
        p1.setHand (hand1);
        r.layCard (MERCATUS, 1);
        r.layCard (CONSUL, 2);
        r.layCard (SICARIUS, 4);
        r.layCard (MERCATOR, 7);*/
        //Assumption: above code sets the first 4 cards for player 1, fix below
        int[] faceUpCards1 = {MERCATUS, CONSUL, NO_CARD, SICARIUS,
                              NO_CARD, NO_CARD, MERCATOR};
        p1.setFaceUpCards(faceUpCards1);
        
        //asserts inserted
        assert(p1.getFaceUpCards()[0] == MERCATUS);
        assert(p1.getFaceUpCards()[1] == CONSUL);
        assert(p1.getFaceUpCards()[2] == NO_CARD);
        assert(p1.getFaceUpCards()[3] == SICARIUS);
        assert(p1.getFaceUpCards()[4] == NO_CARD);
        assert(p1.getFaceUpCards()[5] == NO_CARD);
        assert(p1.getFaceUpCards()[6] == MERCATOR);

        //code below is buggy
        /*int hand2[] = {FORUM, FORUM, LEGAT, GLADIATOR};
        p2.setHand (hand2);
        r.layCard (FORUM, 1);
        r.layCard (FORUM, 2);
        r.layCard (LEGAT, 3);
        r.layCard (GLADIATOR, 7);*/
        //Assumption: above code sets the first 4 cards for player 2, fix below
        int[] faceUpCards2 = {FORUM, FORUM, LEGAT, NO_CARD,
                              NO_CARD, NO_CARD, GLADIATOR};
        p2.setFaceUpCards(faceUpCards2);

        assert(p2.getFaceUpCards()[0] == FORUM);
        assert(p2.getFaceUpCards()[1] == FORUM);
        assert(p2.getFaceUpCards()[2] == LEGAT);
        assert(p2.getFaceUpCards()[3] == NO_CARD);
        assert(p2.getFaceUpCards()[4] == NO_CARD);
        assert(p2.getFaceUpCards()[5] == NO_CARD);
        assert(p2.getFaceUpCards()[6] == GLADIATOR);
        
        //player1's turn begins, assertions inserted as necessary
        int dice1[] = {4,5,5};
        r.setAvailableActionDice (dice1);
        assert(r.getAvailableActionDice().length == 3);
        r.useActionDieForMoney (5);
        assert(p1.getMoney() == 5);
        assert(r.getAvailableActionDice().length == 2);
        r.useActionDieForMoney (5);
        assert(p1.getMoney() == 10);
        assert(r.getAvailableActionDice().length == 1);
        int[] args = {7};
        
        //activate sicarius to kill gladiator, asserts inserted
        assert(r.activateCard (4,args));
        int[] discardPile1 = {SICARIUS, GLADIATOR};
        assert(isPermutation(r.getDiscardPile(), discardPile1));
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getFaceUpCards()[3] == NO_CARD);
        assert(p2.getFaceUpCards()[6] == NO_CARD);
        r.endCurrentTurn ();
        
        //player2's turn begins, asserts inserted as needed
        assert(p2.getVictoryPoints() == 6);
        int dice2[] = {3,3,4};
        r.setAvailableActionDice (dice2);
        assert(r.getAvailableActionDice().length == 3);
        
        //activate legat, should gain 4 vp each time
        assert(r.activateCard (3, new int[1]));                 //new int[1] used to avoid
        assert(r.getAvailableActionDice().length == 2); // emtpy array issues
        assert(p2.getVictoryPoints () == 10);
        assert(r.activateCard (3, new int[1]));
        assert(r.getAvailableActionDice().length == 1);
        assert(p2.getVictoryPoints () == 14);
        
        //note, which cards are drawn are based on order of cards in deck
        //this is how you can determine which cards should be discarded based
        //on the picker
        r.useActionDieForCards (4, new CardPicker() { public int pickCard(int cards[]) { return CardCode.CENTURIO; }} );
        assert(r.getAvailableActionDice().length == 0);

        //check card is in hand
        int[] expectedHand = {CENTURIO};
        assert(isPermutation(p2.getHand(), expectedHand));

        //check cards were discarded as necessary, info above
        int[] discardPile2 = {SICARIUS, GLADIATOR, FORUM, FORUM, BASILICA};
        assert(isPermutation(r.getDiscardPile(), discardPile2));
        
        r.endCurrentTurn ();
        
        //player1's turn again, asserts inserted
        assert(p1.getVictoryPoints() == 3);
        int dice3[] = {2,3,5};
        r.setAvailableActionDice (dice3);
        assert(r.getAvailableActionDice().length == 3);
        r.useActionDieForMoney (5);
        assert(p1.getMoney() == 15);
        assert(r.getAvailableActionDice().length == 2);
        r.useActionDieForMoney (3);
        assert(p1.getMoney() == 18);
        assert(r.getAvailableActionDice().length == 1);
        int arg2[] = {2, 8}; // buys 8 VP, fixed to include bribe cost
        
        //activate mercator, asserts inserted
        assert(r.activateCard (7, arg2));
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getVictoryPoints () == 11);
        assert(p1.getMoney() == 0);
        assert(p2.getVictoryPoints() == 6);
        assert(p2.getMoney() == 16);
        r.endCurrentTurn ();
        
        //player2's turn again, asserts inserted
        assert(p2.getVictoryPoints () == 2);
        int dice4[] = {2,2,6};
        r.setAvailableActionDice (dice4);
        assert(r.getAvailableActionDice().length == 3);
        
        //laying centurio from hand, asserts etc.
        r.layCard (CENTURIO, 7);
        assert(p2.getMoney() == 7);
        assert(p2.getFaceUpCards()[6] == CENTURIO);
        
        //rig dice for battle etc.
        r.rigBattleDie (5);
        int[] arg4 = {2}; //added extra value for bribe disc
        
        //activate centurio to kill mercator
        assert(r.activateCard (7, arg4)); // activate 7th disc
        assert(r.getAvailableActionDice().length == 2);
        assert(p1.getFaceUpCards()[6] == NO_CARD);
        int[] discardPile3 = {SICARIUS, GLADIATOR, FORUM, FORUM, BASILICA,
                              MERCATOR};
        assert(isPermutation(r.getDiscardPile(), discardPile3));
        
        //activate forum on disc 2 with gaining 6 vp
        int arg3[] = {6};
        assert(r.activateCard (2, arg3));
        assert(p2.getVictoryPoints () == 8);
        assert(r.getAvailableActionDice().length == 0);
        r.endCurrentTurn ();

        //player1's turn 3, asserts etc.
        assert (p1.getVictoryPoints () == 6);
        int dice5[] = {2,2,4};
        r.setAvailableActionDice (dice5);
        assert(r.getAvailableActionDice().length == 3);
        
        //drawing cards, discard cards based on deck at top
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.FORUM; }});
        int[] discardPile4 = {SICARIUS, GLADIATOR, FORUM, FORUM, BASILICA,
                              MERCATOR, CONSILIARIUS};
        assert(isPermutation(r.getDiscardPile(), discardPile4));
        assert(r.getAvailableActionDice().length == 2);
        expectedHand[0] = FORUM;
        assert(isPermutation(p1.getHand(), expectedHand));

        //dice for money
        r.useActionDieForMoney (4);
        assert(r.getAvailableActionDice().length == 1);
        assert(p1.getMoney() == 4);
        r.useActionDieForMoney (2);
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getMoney() == 6);
        
        //lay forum
        r.layCard (FORUM, 4);
        assert(p1.getFaceUpCards()[3] == FORUM);
        assert(p1.getMoney() == 1);        
        r.endCurrentTurn ();
        
        //player2's 3rd turn, asserts...
        assert (p2.getVictoryPoints () == 5);
        int dice6[] = {6,5,6};
        r.setAvailableActionDice (dice6);
        assert(r.getAvailableActionDice().length == 3);        

        //drawing, expect asserts...
        r.useActionDieForCards (6, new CardPicker() { public int pickCard(int cards[]) { return CardCode.LEGAT; }});
        assert(r.getAvailableActionDice().length == 2);
        int[] discardPile5 = {SICARIUS, GLADIATOR, FORUM, FORUM, BASILICA,
                              MERCATOR, BASILICA,ARCHITECTUS,ARCHITECTUS,
                              PRAETORIANUS,LEGIONARIUS, CONSILIARIUS};
        assert(isPermutation(r.getDiscardPile(), discardPile5));

        
        expectedHand[0] = LEGAT;
        assert(isPermutation(p2.getHand(), expectedHand));
        
        //lay legat
        r.layCard (LEGAT, 5);
        assert (p2.getMoney () == 0);
        
        //activate legat
        assert(r.activateCard (5, new int[1]));
        assert(p2.getVictoryPoints () == 9);
        assert(r.getAvailableActionDice().length == 1);                

        //rest of turn
        r.useActionDieForMoney (6);
        assert (p2.getMoney () == 6);
        assert(r.getAvailableActionDice().length == 0);                
        r.endCurrentTurn ();
        
        //player1's 4th turn, expect asserts
        assert (p1.getVictoryPoints () == 2);
        int dice7[] = {2,2,6};
        r.setAvailableActionDice (dice7);
        assert(r.getAvailableActionDice().length == 3);
        
        //draw again, discarded cards based on deck
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.TRIBUNUS_PLEBIS; }});
        assert(r.getAvailableActionDice().length == 2);
        int[] discardPile6 = {SICARIUS, GLADIATOR, FORUM, FORUM, BASILICA,
                MERCATOR, BASILICA,ARCHITECTUS,ARCHITECTUS,
                PRAETORIANUS,LEGIONARIUS,SCAENICUS, CONSILIARIUS};
        assert(isPermutation(r.getDiscardPile(), discardPile6));                
        expectedHand[0] = TRIBUNUS_PLEBIS;
        assert(isPermutation(p1.getHand(), expectedHand));
        
        //draw...
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.AESCULAPINUM; }});
        assert(r.getAvailableActionDice().length == 1);
        int[] discardPile7 = {SICARIUS, GLADIATOR, FORUM, FORUM, BASILICA,
                MERCATOR, BASILICA,ARCHITECTUS,ARCHITECTUS,
                PRAETORIANUS,LEGIONARIUS,SCAENICUS, VELITES, CONSILIARIUS};
        assert(isPermutation(r.getDiscardPile(), discardPile7));
        expectedHand = new int[2];
        expectedHand[0] = TRIBUNUS_PLEBIS;
        expectedHand[1] = AESCULAPINUM;
        assert(isPermutation(p1.getHand(), expectedHand));
        
        //dice for money, laying...
        r.useActionDieForMoney (6);
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getMoney() == 7);        
        r.layCard (AESCULAPINUM, 6);
        assert (p1.getMoney () == 2);
        assert(p1.getFaceUpCards()[5] == AESCULAPINUM);
        r.endCurrentTurn ();
        
        //just a check that game is still being played
        assert (!r.isGameOver ());
        
        //player2's turn, mostly unimportant but hey, lets test it
        assert (p2.getVictoryPoints () == 7);
        int dice8[] = {3,4,6};
        r.setAvailableActionDice (dice8);
        assert(r.getAvailableActionDice().length == 3);
        
        r.endCurrentTurn ();
        
        //game
        assert (p1.getVictoryPoints () <= 0);
        assert (r.isGameOver ());
        
        //run the next test while we're at it
        //test2(); //bugged
    }
    
    /**
     * This test activates the bribery disc twice in one turn
     * Therefore not a reliable test, you can only activate it once
     */
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
        
/*        int hand1[] = {FORUM, TEMPLUM, MERCATUS, SCAENICUS}; //set the hand
        p1.setHand (hand1);
        r.layCard (MERCATUS, 1);
        r.layCard (TEMPLUM, 2);
        r.layCard (FORUM, 3);
        r.layCard (SCAENICUS, 7);
        int hand2[] = {LEGAT, BASILICA, NERO, VELITES};
        p2.setHand (hand2);
        r.layCard (LEGAT, 1);
        r.layCard (BASILICA, 2);
        r.layCard (NERO, 3);
        r.layCard (VELITES, 7);*/
        int[] faceUpCards1 = {MERCATUS, TEMPLUM, FORUM, NO_CARD,
                            NO_CARD, NO_CARD, SCAENICUS
        };
        p1.setFaceUpCards(faceUpCards1);
        int[] faceUpCards2 = {LEGAT, BASILICA, NERO, NO_CARD,
                NO_CARD, NO_CARD, VELITES
        };
        p2.setFaceUpCards(faceUpCards2);
        
        
        int dice1[] = {1,6,4};
        r.setAvailableActionDice (dice1);
        r.useActionDieForCards (4, new CardPicker() { public int pickCard(int cards[]) { return CardCode.FORUM; }});
        r.useActionDieForMoney (6);
        r.layCard (FORUM, 5);
        r.useActionDieForMoney (1);
        assert(p1.getMoney () == 2);
        r.endCurrentTurn ();
        
        int dice2[] = {2,3,6};
        r.setAvailableActionDice (dice2);
        assert(p2.getVictoryPoints () == 7);
        int arg1[] = {3};
        r.activateCard (3, arg1);
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.PRAETORIANUS; }});
        r.useActionDieForMoney (6);
        r.layCard (PRAETORIANUS, 4);
        assert (p2.getMoney () == 2);
        r.endCurrentTurn ();
        
        int dice3[] = {5,4,2};
        r.setAvailableActionDice (dice3);
        assert (p1.getVictoryPoints () == 4);
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.FORUM; }});
        int arg2[] = {4};
        r.activateCard (5, arg2);
        assert (p1.getMoney () == 2);
        assert (p1.getVictoryPoints () == 8);
        r.endCurrentTurn ();
        
        int dice4[] = {1,6,6};
        r.setAvailableActionDice (dice4);
        assert (p2.getVictoryPoints () == 4);
        r.activateCard (1, new int[1]);
        assert (p2.getVictoryPoints () == 7);
        r.useActionDieForMoney (6);
        r.useActionDieForCards (6, new CardPicker() { public int pickCard(int cards[]) { return CardCode.LEGAT; }});
        r.layCard (LEGAT, 3);
        assert (p2.getMoney () == 3);
        r.endCurrentTurn ();
        
        int dice5[] = {3,3,4};
        assert (p1.getVictoryPoints () == 5);
        r.setAvailableActionDice (dice5);
        r.useActionDieForMoney (3);
        r.layCard (FORUM, 3);
        int arg3[] = {4};
        r.activateCard (3, arg3);
        assert (p1.getVictoryPoints () == 9);
        assert (p1.getMoney() == 0);
        r.endCurrentTurn ();
        
        int dice6[] = {5,5,2};
        r.setAvailableActionDice (dice6);
        assert (p2.getVictoryPoints () == 5);
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.ONAGER; }});
        r.useActionDieForMoney (5);
        r.layCard (ONAGER, 5);
        int arg4[] = {2};
        r.rigBattleDie (2);
        r.activateCard (5, arg4);
        assert (p1.getFaceUpCards ()[1] == NO_CARD);
        r.endCurrentTurn ();
        
        int dice7[] = {1,5,6};
        r.setAvailableActionDice (dice7);
        assert (p1.getVictoryPoints () == 6);
        r.useActionDieForCards (1, new CardPicker() { public int pickCard(int cards[]) { return CardCode.SENATOR; }});
        int arg5[] = {6};
        r.activateCard (5, arg5);
        assert (p1.getVictoryPoints () == 12);
        r.endCurrentTurn ();
        
        int dice8[] = {1,3,5};
        r.setAvailableActionDice (dice8);
        assert (p2.getVictoryPoints () == 4);
        r.activateCard (1, new int[1]);
        r.activateCard (3, new int[1]);
        int arg6[] = {1};
        r.rigBattleDie (3);
        r.activateCard (5, arg6);
        assert (p1.getFaceUpCards ()[1] == NO_CARD);
        assert (p2.getVictoryPoints () == 10);
        r.endCurrentTurn ();
        
        int dice9[] = {3,6,6};
        r.setAvailableActionDice (dice9);
        assert (p1.getVictoryPoints () == 8);
        int arg7[] = {6};
        r.activateCard (3, arg7);
        r.useActionDieForMoney (6);
        r.layCard (SENATOR, 1);
        assert (p1.getVictoryPoints () == 14);
        assert (p1.getMoney () == 3);
        r.endCurrentTurn ();
        
        int dice10[] = {1,1,6};
        r.setAvailableActionDice (dice10);
        assert (p2.getVictoryPoints () == 9);
        r.activateCard (1, new int[1]);
        r.activateCard (1, new int[1]);
        r.useActionDieForMoney (6);
        assert (p2.getVictoryPoints () == 15);
        assert (p2.getMoney () == 9);
        r.endCurrentTurn ();
        
        int dice11[] = {1,3,3};
        r.setAvailableActionDice (dice11);
        assert (p1.getVictoryPoints () == 11);
        r.useActionDieForCards (3, new CardPicker() { public int pickCard(int cards[]) { return CardCode.CENTURIO; }});
        r.useActionDieForCards (3, new CardPicker() { public int pickCard(int cards[]) { return CardCode.HARUSPEX; }});
        int arg8[] = {HARUSPEX, 2, CENTURIO, 4};
        r.activateCard (1, arg8);
        assert (p1.getVictoryPoints () == 11);
        assert (p1.getMoney () == 3);
        r.endCurrentTurn ();
        
        int dice12[] = {4,3,5};
        r.setAvailableActionDice (dice12);
        assert (p2.getVictoryPoints () == 14);
        int arg9[] = {4,2};
        r.rigBattleDie (6);
        r.activateCard (7, arg9);
        int arg10[] = {3};
        r.rigBattleDie (5);
        r.activateCard (5, arg10);
        assert (p1.getFaceUpCards ()[2] == NO_CARD);
        assert (p1.getFaceUpCards ()[1] == NO_CARD);
        r.activateCard (3, new int[1]);
        assert (p2.getVictoryPoints () == 17);
        assert (p2.getMoney () == 5);
        r.endCurrentTurn ();
        
        
        int dice13[] = {2,1,1};
        r.setAvailableActionDice (dice13);
        assert (p1.getVictoryPoints () == 8);
        r.useActionDieForCards (2, new CardPicker() { public int pickCard(int cards[]) { return CardCode.CONSILIARIUS; }});
        r.useActionDieForCards (1, new CardPicker() { public int pickCard(int cards[]) { return CardCode.GLADIATOR; }});
        int arg11[] = {GLADIATOR, 2, CONSILIARIUS, 3};
        r.activateCard (1, arg11);
        assert (p1.getVictoryPoints () == 8);
        assert (p1.getMoney () == 3);
        r.endCurrentTurn ();

        
        int dice14[] = {1,2,3};
        r.setAvailableActionDice (dice14);
        assert (p2.getVictoryPoints () == 16);
        int arg12[] = {1,1};
        r.rigBattleDie (2);
        r.activateCard (7, arg12);
        assert (p1.getFaceUpCards ()[0] == SENATOR);
        int arg13[] = {2,1};
        r.rigBattleDie (6);
        r.activateCard (7, arg13);
        assert (p1.getFaceUpCards ()[0] == NO_CARD);
        r.useActionDieForCards (3, new CardPicker() { public int pickCard(int cards[]) { return CardCode.MERCATOR; }});
        assert (p2.getVictoryPoints () == 16);
        assert (p2.getMoney () == 2);
        r.endCurrentTurn ();
        
        int dice15[] = {2,2,1};
        r.setAvailableActionDice (dice15);
        assert (p1.getVictoryPoints () == 6);
        int arg14[] = {1,4}; // assuming no args means no dice added to centurio
        r.activateCard (7, arg14);
        int arg15[] = {1};
        r.activateCard (2, arg15);
        int arg16[] = {3};
        r.activateCard (2, arg16);
        assert (p1.getVictoryPoints () == 6);
        assert (p1.getMoney () == 2);
        r.endCurrentTurn ();
        
        int dice16[] = {1,5, 5};
        r.setAvailableActionDice (dice16);
        assert (p2.getVictoryPoints () == 12);
        r.useActionDieForCards (1, new CardPicker() { public int pickCard(int cards[]) { return CardCode.GLADIATOR; }});
        r.useActionDieForMoney (5);
        r.useActionDieForMoney (5);
        assert (p2.getVictoryPoints () == 12);
        assert (p2.getMoney () == 12);
        r.endCurrentTurn ();
        
        int dice17[] = {5,3,1};
        r.setAvailableActionDice (dice17);
        assert (p1.getVictoryPoints () == 4);
        r.useActionDieForCards (1, new CardPicker() { public int pickCard(int cards[]) { return CardCode.GLADIATOR; }});
        int arg17[] = {3};
        r.activateCard (5, arg17);
        assert (p1.getVictoryPoints () == 7);
        assert (p1.getMoney () == 2);
        r.endCurrentTurn ();
        
        
        int dice18[] = {1, 3, 5};
        r.setAvailableActionDice (dice18);
        assert (p2.getVictoryPoints () == 8);
        
        r.useActionDieForMoney (3);
        r.useActionDieForMoney (5);
        r.layCard (MERCATOR, 1);
        int arg18[] = {12};
        r.activateCard (1, arg18);
        
        assert (p2.getVictoryPoints () == 14);
        assert (p2.getMoney () == 1);
        
        assert (p1.getVictoryPoints () == 1);
        assert (p1.getMoney () == 14);
        
        assert (!r.isGameOver ());
        
        r.endCurrentTurn ();
        
        assert (r.isGameOver ());
        // SHIT YES IT'S OVER
    }
    
    public String toString(){
        return "Wed 09 Oboe Tests";
    }
    
}
