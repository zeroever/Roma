package tests.acceptanceTests;

import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;

import tests.main.*;
import tests.userProgram.Roma;


/** 
 * This test mainly tests
 * Architectus, Machina, Onager, Praetorianus, Scaenicus, Senator
 *
 * 18/5/10
 * by jocelyn dang (Mon11Tuba)
 * please email jocelynd@cse.unsw.edu.au with errors 
 *
 * UPDATED: 19/5/10
 *  - line 485 where the args for activating Scaenicus has been changed
 *  - to specify the dice disc to copy, rather than the character card to copy
 */


public class AuronNew4 extends RomaTest {

    private Roma roma;

    public String toString () {
        return "Auron";
    }
    
    
    public void run () {
        setupGame ();

        roundOne ();
        roundTwo ();
        roundThree ();
        roundFour ();
        roundFive (); 
        roundSix ();
        roundSeven ();

        theEnd ();
    }

    public void setupGame () {
        this.roma = new Roma ();
        assert (!this.roma.isGameOver());

        // local function to set face up cards for each player
        setBoardFaceUpCards ();        
        setPlayerHands ();

        // just in case
        roma.setCurrentPlayer (PLAYER1);
    }


    private void setBoardFaceUpCards () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);

        int[] player1Cards = {NO_CARD, NO_CARD, PRAETORIANUS, LEGIONARIUS, 
                              NO_CARD, ONAGER, ARCHITECTUS};
        int[] player2Cards = {SENATOR, NO_CARD, NO_CARD, NO_CARD, 
                              MACHINA, SCAENICUS, CENTURIO};

        // let's put 4 cards onto the board 
        player1.setFaceUpCards (player1Cards);
        player2.setFaceUpCards (player2Cards);
    }
    
    
    private void setPlayerHands () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);
        
        int[] player1Hand = {MACHINA, ONAGER, SENATOR, LEGAT, 
                             FORUM, BASILICA, FORUM, ONAGER};
        int[] player2Hand = {LEGAT, PRAETORIANUS, ONAGER, CENTURIO};
        
        player1.setHand (player1Hand);
        player2.setHand (player2Hand);
    }


   /**
    * *** Testing Architectus ***
    *
    * Current player is Player 1
    * No (VP) deductions yet since roma.endCurrentTurn hasn't been invoked
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  NO_CARD        1         SENATOR
    *  NO_CARD        2         NO_CARD
    *  PRAETORIANUS   3         NO_CARD
    *  LEGIONARIUS    4         NO_CARD
    *  NO_CARD        5         MACHINA 
    *  ONAGER         6         SCAENICUS
    *  ARCHITECTUS    7         CENTURIO
    *              (board)
    *  VP: 10       VP: 16      VP: 10
    *   $0            $$$        $0
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: MACHINA, ONAGER, SENATOR, LEGAT, FORUM, BASILICA, FORUM, ONAGER
    *  PLAYER 2 Cards: LEGAT, PRAETORIANUS, ONAGER, CENTURIO
    *  Draw Pile: empty
    *  Discard Pile: empty
    */

    private void roundOne () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        int victoryPoints = player1.getVictoryPoints ();
        int[] dice = {3,4,6};
        roma.setAvailableActionDice (dice);
        
        roma.useActionDieForMoney (4);
        roma.useActionDieForMoney (6);
        assert (player1.getMoney() == 10);

        
        // activate architectus, which is on the bribe disc
        // use the dice with value 3, i.e. pay 3 sestertii
        int[] args = {3, ONAGER,   DISC1,      // lay on top of NO_CARD
                         FORUM,    DISC2,      // lay on top of NO_CARD
                         FORUM,    BRIBE_DISC, // lay on top of ARCHITECTUS 
                         BASILICA, DISC6,      // lay on top of ONAGER
                         MACHINA,  DISC1};     // lay on top of ONAGER 
                                               // (that we just laid earlier!)
    
        roma.activateCard (BRIBE_DISC, args);
        // bribe disc used up 3 sestertii, but laying cards down was free
        assert (player1.getMoney() == 7);
        int[] discardPile = {ONAGER, ONAGER, ARCHITECTUS};

        assert (isPermutation (discardPile, roma.getDiscardPile ()));
        
        int[] player1CardsOnDiceDiscs = player1.getFaceUpCards ();
   
        assert (player1CardsOnDiceDiscs[DISC1 - 1]      == MACHINA);
        assert (player1CardsOnDiceDiscs[DISC2 - 1]      == FORUM);
        assert (player1CardsOnDiceDiscs[DISC3 - 1]      == PRAETORIANUS);        
        assert (player1CardsOnDiceDiscs[DISC4 - 1]      == LEGIONARIUS);        
        assert (player1CardsOnDiceDiscs[DISC5 - 1]      == NO_CARD);
        assert (player1CardsOnDiceDiscs[DISC6 - 1]      == BASILICA);
        assert (player1CardsOnDiceDiscs[BRIBE_DISC - 1] == FORUM);    
        
        int[] playerHand = {SENATOR, LEGAT, ONAGER};
        assert (isPermutation (player1.getHand (), playerHand));
        
        // no change in victory points
        assert (player1.getVictoryPoints () == victoryPoints);
        
        roma.endCurrentTurn ();      
    }


   /**
    * *** Testing Machina, Praetorianus ***
    *
    * Current player is Player 2
    * Player 2 is deducted 3 VP for 3 empty dice discs
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  FORUM          2         NO_CARD
    *  PRAETORIANUS   3         NO_CARD
    *  LEGIONARIUS    4         NO_CARD
    *  NO_CARD        5         MACHINA 
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 10       VP: 19      VP: 7
    *   $7            $$$        $0
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards: LEGAT, PRAETORIANUS, ONAGER, CENTURIO
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER
    */

    private void roundTwo () {
        TestablePlayer player2 = roma.getPlayer (PLAYER2);           

        int victoryPointsInStockpile = roma.getVictoryPointsStockpile ();
        int myVictoryPoints          = player2.getVictoryPoints ();
                 
        int[] dice = {5,4,6};
        roma.setAvailableActionDice (dice);
        
        int[] args = {MACHINA, DISC2};
        // moving MACHINA from DISC5 to DISC2, don't ask why.
        roma.activateCard (DISC5, args); 

        int[] diceLeft = {4,6};
        assert (isPermutation (roma.getAvailableActionDice (), diceLeft));

        int[] faceUpCards = player2.getFaceUpCards ();
        assert (faceUpCards [DISC2 - 1] == MACHINA);
        assert (faceUpCards [DISC5 - 1] == NO_CARD);       
    
        roma.useActionDieForMoney (6);
        roma.useActionDieForMoney (4);

        // cheating
        roma.setAvailableActionDice (dice);

        roma.layCard (LEGAT, DISC4);
        // legat costs 5 sestertii to lay        
        roma.layCard (PRAETORIANUS, DISC5);
        assert (player2.getMoney () == 1);

        roma.activateCard (DISC4, new int[0]);
        
        // the opponent only had 1 empty dice disc, so we gain 1 vp
        assert (roma.getVictoryPointsStockpile () == victoryPointsInStockpile - 1);
        assert (player2.getVictoryPoints () == myVictoryPoints + 1);
        
        int[] args2 = {DISC6};
        // let's activate praetorianus to block the opponent's basilica
        roma.activateCard (DISC5, args2); 

        int[] player2Cards = {ONAGER, CENTURIO};
        assert (isPermutation (player2Cards, player2.getHand()));

        roma.endCurrentTurn ();
        
    }
    

   /**
    * *** Testing Activation of forum and basilica, with basilica dice disc blocked ***
    * *** Testing Praetorianus where effect of blocking will actually apply next round ***
    *
    * Current player is Player 1
    * Player 1 is deducted 1 VP for 1 empty dice disc
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  FORUM          2         MACHINA
    *  PRAETORIANUS   3         NO_CARD
    *  LEGIONARIUS    4         LEGAT
    *  NO_CARD        5         PRAETORIANUS
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 9       VP: 19     VP: 8
    *   $7            $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards: ONAGER, CENTURIO
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER
    */

    
    private void roundThree () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);    
            
        int victoryPoints = player1.getVictoryPoints ();
        int victoryPointsStockpile = this.roma.getVictoryPointsStockpile ();
        
        // check we've kept track of our game stats so far
        assert (player1.getVictoryPoints () == 9);
        assert (player2.getVictoryPoints () == 8);
        assert (victoryPointsStockpile == 19);
        assert (player1.getMoney () == 7);
        assert (player2.getMoney () == 1);
       
        int[] dice = {1,3,6};
        roma.setAvailableActionDice (dice);  
    
        // we want to use dice 1 to activate bribe disc, get 6 extra vp
        int[] args = {1, 6};
    
        // activate forum, basilica effect still applies even though
        // basilica's disc is blocked!
        roma.activateCard (BRIBE_DISC, args);


        
        // gained 2 vp from basilica, 6 from forum
        assert (player1.getVictoryPoints () == victoryPoints + 8);
        assert (this.roma.getVictoryPointsStockpile () == victoryPointsStockpile - 8);
        
        assert (roma.getAvailableActionDice ().length == 1);
        assert (roma.getAvailableActionDice ()[0] == 3);
        
        // bribe disc activation means we spent 1 sestertii
        assert (player1.getMoney () == 6);
        
        // block disc 4
        int[] args2 = {DISC4};
        roma.activateCard (DISC3, args2);
        
        roma.endCurrentTurn ();
    } 
     
 
   /**
    * Current player is Player 2
    * Player 2 is deducted 1 VP for 1 empty dice disc
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  FORUM          2         MACHINA
    *  PRAETORIANUS   3         NO_CARD
    *  LEGIONARIUS    4         LEGAT
    *  NO_CARD        5         PRAETORIANUS
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 17       VP: 12      VP: 7
    *   $6            $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards: ONAGER, CENTURIO
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER
    */   
     
     
     private void roundFour () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);        

        assert (player1.getVictoryPoints () == 17);
        assert (player2.getVictoryPoints () == 7);
        assert (roma.getVictoryPointsStockpile () == 12);
        assert (player1.getMoney () == 6);
        assert (player2.getMoney () == 1);    

        int[] dice = {4,3,5};
        roma.setAvailableActionDice (dice);  
     
        // activate legat, nothing happens since it's blocked
        // (by last round's praetorianus effect)
        roma.activateCard (DISC4, new int[0]);
        assert (player2.getVictoryPoints () == 7);
     
    
        roma.useActionDieForMoney (5);
    
        // onager costs 5 sestertii to lay
        roma.layCard (ONAGER, DISC3);
        assert (player2.getMoney () == 1);
        
        // rig so that we roll a 5 and win the battle
        roma.rigBattleDie (5);
        
        int[] args = {DISC2};
        roma.activateCard (DISC3, args);
        // forum has defence 5, is destroyed
        assert (player1.getFaceUpCards()[DISC2 - 1] == NO_CARD);

        roma.endCurrentTurn ();
    }


   /**
    * Current player is Player 1
    * Player 1 is deducted 2 VP for 2 empty dice discs
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  NO_CARD        2         MACHINA
    *  PRAETORIANUS   3         ONAGER
    *  LEGIONARIUS    4         LEGAT
    *  NO_CARD        5         PRAETORIANUS
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 15       VP: 14      VP: 7
    *   $6            $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards: CENTURIO
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER, FORUM
    */   


    private void roundFive () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);        

        int money = player1.getMoney ();
        int victoryPoints = player1.getVictoryPoints ();

        assert (player1.getVictoryPoints () == 15);
        assert (player2.getVictoryPoints () == 7);
        assert (roma.getVictoryPointsStockpile () == 14);
        assert (player1.getMoney () == 6);
        assert (player2.getMoney () == 1);    
     
        int[] dice = {4,3,1};
        roma.setAvailableActionDice (dice);  

        int[] args = {1, 4};
        roma.activateCard (BRIBE_DISC, args);

        // 1 sestertii spent on bribe disc activation
        assert (player1.getMoney () == money - 1);
        assert (player1.getVictoryPoints () == victoryPoints + 6);

        roma.endCurrentTurn ();
    }

     
   /**
    * *** Simple Testing of Senator, Scaenicus ***
    *
    * Current player is Player 2
    * Player 2 is deducted 0 VP for 0 empty dice discs
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  NO_CARD        2         MACHINA
    *  PRAETORIANUS   3         ONAGER
    *  LEGIONARIUS    4         LEGAT
    *  NO_CARD        5         PRAETORIANUS
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 21       VP: 8      VP: 7
    *   $5            $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards: CENTURIO
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER, FORUM
    */   


    private void roundSix () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);

        assert (player1.getVictoryPoints () == 21);
        assert (player2.getVictoryPoints () == 7);
        assert (roma.getVictoryPointsStockpile () == 8);
        assert (player1.getMoney () == 5);
        assert (player2.getMoney () == 1);    

        int[] dice = {1,6,5};
        roma.setAvailableActionDice (dice);  
    
        // activate senator and lay down centurio on the board for free
        // (machina is discarded)
        int[] args = {CENTURIO, DISC2};
        roma.activateCard (DISC1, args);

        int[] faceUpCards = player2.getFaceUpCards ();
        assert (faceUpCards [DISC2 - 1] == CENTURIO);
        
        // only the dice 6 and 5 left
        assert (roma.getAvailableActionDice().length == 2);

        // activating Scaenicus, copy Legat
        int[] args2 = {LEGAT};
        roma.activateCard (DISC6, args2);

        roma.endCurrentTurn ();
    }


     
   /**
    * Current player is Player 1
    * Player 1 is deducted 2 VP for 2 empty dice discs
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  NO_CARD        2         CENTURIO
    *  PRAETORIANUS   3         ONAGER
    *  LEGIONARIUS    4         LEGAT
    *  NO_CARD        5         PRAETORIANUS
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 19       VP: 8      VP: 9
    *   $5            $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards: 
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER, FORUM, MACHINA
    */   


    private void roundSeven () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);

        assert (player1.getVictoryPoints () == 19);
        assert (player2.getVictoryPoints () == 9);
        assert (roma.getVictoryPointsStockpile () == 8);
        assert (player1.getMoney () == 5);
        assert (player2.getMoney () == 1);    

        int[] dice = {4,6,5};
        roma.setAvailableActionDice (dice);  

        // activating forum again
        int[] args = {4, 6};
        roma.activateCard (BRIBE_DISC, args);
        roma.endCurrentTurn ();
    }


   /**
    * Current player is Player 2
    * Player 2 is deducted 0 VP for 0 empty dice discs
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  MACHINA        1         SENATOR
    *  NO_CARD        2         CENTURIO
    *  PRAETORIANUS   3         ONAGER
    *  LEGIONARIUS    4         LEGAT
    *  NO_CARD        5         PRAETORIANUS
    *  BASILICA       6         SCAENICUS
    *  FORUM          7         CENTURIO
    *              (board)
    *  VP: 27       VP: 0      VP: 9
    *   $1            $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: SENATOR, LEGAT, ONAGER
    *  PLAYER 2 Cards:
    *  Draw Pile: empty
    *  Discard Pile: ARCHITECTUS, ONAGER, ONAGER, FORUM, MACHINA
    */   


    private void theEnd () {
        TestablePlayer player1 = roma.getPlayer (PLAYER1);
        TestablePlayer player2 = roma.getPlayer (PLAYER2);

        assert (player1.getVictoryPoints () == 27);
        assert (player2.getVictoryPoints () == 9);
        assert (roma.getVictoryPointsStockpile () == 0);
        assert (player1.getMoney () == 1);
        assert (player2.getMoney () == 1);    

        assert (roma.isGameOver());

        int[] player1Cards = {SENATOR, LEGAT, ONAGER};
        assert (isPermutation (player1Cards, player1.getHand()));

        // player 2 has no cards left
        assert (player2.getHand().length == 0);        

        int[] discardedCards = {FORUM, ARCHITECTUS, ONAGER, ONAGER, MACHINA};
//        System.out.println ("DISCARD PILE:");
//        for (int i: roma.getDiscardPile ()) {
//            System.out.println (Roma.CARD_NUM_TO_STRING[i]);
//        }
//        System.out.println ();
        assert (isPermutation(roma.getDiscardPile (), discardedCards));

        // check the cards on the dice discs
        int[] player1CardsOnDiceDiscs = player1.getFaceUpCards ();
        assert (player1CardsOnDiceDiscs[DISC1 - 1]      == MACHINA);
        assert (player1CardsOnDiceDiscs[DISC2 - 1]      == NO_CARD);
        assert (player1CardsOnDiceDiscs[DISC3 - 1]      == PRAETORIANUS);        
        assert (player1CardsOnDiceDiscs[DISC4 - 1]      == LEGIONARIUS);        
        assert (player1CardsOnDiceDiscs[DISC5 - 1]      == NO_CARD);
        assert (player1CardsOnDiceDiscs[DISC6 - 1]      == BASILICA);
        assert (player1CardsOnDiceDiscs[BRIBE_DISC - 1] == FORUM);    

        int[] player2CardsOnDiceDiscs = player2.getFaceUpCards ();
        assert (player2CardsOnDiceDiscs[DISC1 - 1]      == SENATOR);
        assert (player2CardsOnDiceDiscs[DISC2 - 1]      == CENTURIO);
        assert (player2CardsOnDiceDiscs[DISC3 - 1]      == ONAGER);        
        assert (player2CardsOnDiceDiscs[DISC4 - 1]      == LEGAT);        
        assert (player2CardsOnDiceDiscs[DISC5 - 1]      == PRAETORIANUS);
        assert (player2CardsOnDiceDiscs[DISC6 - 1]      == SCAENICUS);
        assert (player2CardsOnDiceDiscs[BRIBE_DISC - 1] == CENTURIO);    
    }
}
