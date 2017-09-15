package tests.acceptanceTests;

import java.util.Arrays;

import tests.main.*;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;

public class Mon11TubaTests extends RomaTest {
   private static final int DEFAULT_NUM_VICTORY_POINTS = 10;
   
   private static final int BRIBE_DISC_INDEX = BRIBE_DISC - 1; 

   public String toString () {
      return "Mon11Tuba Tests";
   }

   public void run () {
      testActivateWinsGame ();         
      testRoma ();
      testDylanKelly ();
      testCards ();
      legionOfDoomTests ();       
   }

   /* *
    * by Jocelyn Dang (Mon11Tuba)
    * test for Tribunus Plebis, where activation leads to winning the game
    * please email jocelynd@cse.unsw.edu.au if there are any errors/queries
    */

   public void testActivateWinsGame () {
      // initial setting up
      RomaEngine roma = new Roma ();
      assert (!roma.isGameOver());

      TestablePlayer currentPlayer = roma.getPlayer (PLAYER1);
      TestablePlayer opponent      = roma.getPlayer (PLAYER2);

      int[] currentPlayerCardsOnDiscs = createEmptyHand ();
      int[] opponentCardsOnDiscs = createEmptyHand ();

      // place Tribunus Plebis on the 2nd dice disc, rest of discs have no cards
      currentPlayerCardsOnDiscs[DISC2 - 1] = TRIBUNUS_PLEBIS;    

      currentPlayer.setFaceUpCards (currentPlayerCardsOnDiscs);
      opponent.setFaceUpCards (opponentCardsOnDiscs);

      roma.setCurrentPlayer (PLAYER1);

      // to correspond to our dice disc
      int[] actionDice = {DISC2, DISC2, DISC2};
      roma.setAvailableActionDice (actionDice);   

      // rig so that we win next go
      opponent.setVictoryPoints (1);
      assert (opponent.getVictoryPoints () == 1);

      // Tribunus Plebis effect removes 1 vp from opponent,
      // card should be activated, so the game ends
      // (TribunisPlebis activateCard takes in an empty args array)
      assert (roma.activateCard (DISC2, new int[0]));
      assert (roma.isGameOver ());

      // post game checking (will this be of concern?)     
      // check the cards on the card discs current (face up cards)
      // are equal to the cards that were initially on the card discs
      // (order of cards on dice discs does matter)
      assert (Arrays.equals(currentPlayer.getFaceUpCards (), currentPlayerCardsOnDiscs));
      // gained 1 VP by activating Tribunus Plebis
      assert (currentPlayer.getVictoryPoints ()
               == Mon11TubaTests.DEFAULT_NUM_VICTORY_POINTS + 1);
      assert (opponent.getVictoryPoints () == 0);
   }
  
   public int[] createEmptyHand () {

      int[] hand = {NO_CARD, NO_CARD,
                    NO_CARD, NO_CARD,
                    NO_CARD, NO_CARD,
                    NO_CARD};
      return hand;
   }    

   // sparkle princess tests
   public void testRoma() {
      RomaEngine testRoma = new Roma();
      TestablePlayer p1 = testRoma.getPlayer(1);
      TestablePlayer p2 = testRoma.getPlayer(2);
      assert(p1.getMoney() == 0);
      assert(p2.getMoney() == 0);
      assert(p1.getVictoryPoints() == 10);
      assert(p2.getVictoryPoints() == 10);
      testRoma.setCurrentPlayer(1);
      int[] aD = new int[3];
      aD[0] = 4;
      aD[1] = 5;
      aD[2] = 6;
      testRoma.setAvailableActionDice(aD);
      int[] playerOnesHand = {FORUM, CENTURIO, TURRIS, LEGAT};
      int[] playerTwosHand = {NERO, ESSEDUM, SCAENICUS, VELITES};
      int[] playerOnesFaceUpCards = {ONAGER, GLADIATOR, -1, -1, -1, -1, MERCATUS};
      int[] playerTwosFaceUpCards = {SICARIUS, -1, CONSUL, -1, MACHINA, FORUM, LEGIONARIUS};
      p1.setHand(playerOnesHand);
      p2.setHand(playerTwosHand);
      p1.setMoney(100);
      p2.setMoney(100);
      p1.setFaceUpCards(playerOnesFaceUpCards);
      p2.setFaceUpCards(playerTwosFaceUpCards);
      assert(testRoma.getCurrentPlayer() == 1);
      testRoma.layCard(TURRIS, 3);
      testRoma.layCard(FORUM, 4);
      testRoma.layCard(LEGAT, 5);
      int[] args = {6};
      testRoma.activateCard(4, args);
      assert(p1.getVictoryPoints() == 16);
   }

      /*
      * by Dylan Kelly (Mon11Yanjin)
      * Testing Centrino kills a tribunus plebis and that a sicarius cant kill nothing
      * email: dylan AT student.unsw.edu.au
      */

      public void testDylanKelly () {

         // Create a new game 
         RomaEngine roma = new Roma ();
         assert (!roma.isGameOver());
         TestablePlayer player1 = roma.getPlayer (PLAYER1);
         TestablePlayer player2 = roma.getPlayer (PLAYER2);

         int[] playerOneBoard = {
            NO_CARD, NO_CARD,
            NO_CARD, NO_CARD,
            NO_CARD, NO_CARD,
            CENTURIO};
         int[] playerTwoBoard = {
            NO_CARD, NO_CARD,
            NO_CARD, NO_CARD,
            NO_CARD, SICARIUS,
            TRIBUNUS_PLEBIS}; 

         player1.setFaceUpCards (playerOneBoard);
         player2.setFaceUpCards (playerTwoBoard);

         // Set the action die so that we can test the board
         int[] tempBoard;
         roma.setCurrentPlayer (PLAYER1);
         
         int[] actionDice = {DISC6, DISC6, DISC6};
         roma.setAvailableActionDice (actionDice);   

         // Get player two's board and check that BRIBE_DISC is a tribunus blebis
         tempBoard = player2.getFaceUpCards();
         assert (tempBoard[BRIBE_DISC_INDEX] == TRIBUNUS_PLEBIS);
         
         // Activate BRIBE_DISC
         int battleDieValue = 6;
         roma.rigBattleDie (battleDieValue);
         int[] bribeDiscArgs = {6}; // dice value
         
         TestablePlayer currentPlayer = roma.getPlayer(roma.getCurrentPlayer());
         currentPlayer.setMoney(6);
         assert(currentPlayer.getMoney() >= 6);
         assert (roma.activateCard (BRIBE_DISC, bribeDiscArgs));
         
         // Get player two's board and check that BRIBE_DISC is NO_CARD
         tempBoard = player2.getFaceUpCards();
         assert (tempBoard[BRIBE_DISC_INDEX] == NO_CARD);
         
         // Attempt to activate sicarius, but FAIL!
         //roma.setCurrentPlayer (PLAYER2);
         //assert (!roma.activateCard (DISC6, new int[0]));

      //   System.out.println("You passed Dylan Kelly's Tests :)");

      }

     // ndxx988 nathandsouza
    public void testCards () {

      RomaEngine roma = new Roma();
      TestablePlayer player1 = roma.getPlayer(PLAYER1);
      TestablePlayer player2 = roma.getPlayer(PLAYER2);

      int[] playerOneBoard = {LEGAT, NO_CARD,
                           NO_CARD, NO_CARD,
                           NO_CARD, NO_CARD,
                           GLADIATOR};

      int[] playerTwoBoard = {NO_CARD, NO_CARD,
                           CONSUL, LEGIONARIUS,
                           NO_CARD, NO_CARD,
                           BASILICA};

      player1.setFaceUpCards(playerOneBoard);
      player2.setFaceUpCards(playerTwoBoard);

      roma.setCurrentPlayer(PLAYER1);
      int[] actionDice = {DISC1, DISC6, DISC6};
      roma.setAvailableActionDice(actionDice);
      
      // gain $6
      roma.useActionDieForMoney(DISC6);
      assert(player1.getMoney() == DISC6);
      assert(roma.getAvailableActionDice().length == 2);
   
      //activate Player1 - Legat
      player1.setVictoryPoints(14);
      int start = roma.getVictoryPointsStockpile ();
      assert(roma.activateCard(DISC1, new int[3]));
      assert(player1.getVictoryPoints() == 14 + 4);
      assert(roma.getVictoryPointsStockpile () == start - 4);

      //activate gladiator on basilica, nothing (both unsuccessfully) and then consul(successfully)
      /*
      int[] args = {BRIBE_DISC}; 
      // BRIBE_DISC IS NOT A DICE - YOU CAN't DO THIS
       
      assert(playerTwoBoard[DISC3_INDEX] == CONSUL);
      assert(!roma.activateCard(BRIBE_DISC, args));
      args[0] = DISC6;
      assert(!roma.activateCard(BRIBE_DISC, args));
      args[0] = DISC3;
      assert(roma.activateCard(BRIBE_DISC, args));
      assert(player1.getMoney() == 0);
      assert(playerTwoBoard[DISC3_INDEX] == NO_CARD);
	*/
   }   

   /* Tests written by Samuel Li. 
    * Please email slix867@cse.unsw.edu.au if something needs to be changed.
    * Last updated: 8/05/2010
    */

   private void legionOfDoomTests() {
      // Create new game
      RomaEngine roma = new Roma ();

      TestablePlayer player1 = roma.getPlayer (PLAYER1);
      TestablePlayer player2 = roma.getPlayer (PLAYER2);

      // Set hand and face up cards
      int[] player1FaceUpCards = createEmptyHand();
      int[] player2FaceUpCards = createEmptyHand();
      int[] player1Hand = {AESCULAPINUM, GLADIATOR, CONSILIARIUS, HARUSPEX};
      int[] player2Hand = {SENATOR, TRIBUNUS_PLEBIS, BASILICA, CENTURIO};

      player1.setHand (player1Hand);
      player2.setHand (player2Hand);

      assert (player1.getVictoryPoints() == 10);
      assert (player2.getVictoryPoints() == 10);
       
      // Sets initial values
      player1.setMoney(100);
      player2.setMoney(100);
      assert (player1.getMoney() == 100);
      assert (player2.getMoney() == 100);
      
      player1.setFaceUpCards (player1FaceUpCards);
      player2.setFaceUpCards (player2FaceUpCards);

      // Lays cards down
      roma.setCurrentPlayer(PLAYER1);
      roma.layCard(AESCULAPINUM, DISC1);
      roma.layCard(GLADIATOR, DISC2);
      roma.layCard(CONSILIARIUS, DISC3);
      roma.layCard(HARUSPEX, DISC4);

      player1FaceUpCards[DISC1 - 1] = AESCULAPINUM;
      player1FaceUpCards[DISC2 - 1] = GLADIATOR;
      player1FaceUpCards[DISC3 - 1] = CONSILIARIUS;
      player1FaceUpCards[DISC4 - 1] = HARUSPEX;

      roma.setCurrentPlayer(PLAYER2);
      roma.layCard(SENATOR, DISC4);
      roma.layCard(TRIBUNUS_PLEBIS, DISC5);
      roma.layCard(BASILICA, DISC6);
      roma.layCard(CENTURIO, BRIBE_DISC);

      player2FaceUpCards[DISC4 - 1] = SENATOR;
      player2FaceUpCards[DISC5 - 1] = TRIBUNUS_PLEBIS;
      player2FaceUpCards[DISC6 - 1] = BASILICA;
      player2FaceUpCards[BRIBE_DISC - 1] = CENTURIO;

      // Checks cards are laid down
      checkFaceUpCards(player1, player1FaceUpCards);
      checkFaceUpCards(player2, player2FaceUpCards);
      
      // Rig victory points
      int player1VictoryPoints = 3;
      int player2VictoryPoints = 3;

      player1.setVictoryPoints(player1VictoryPoints);
      assert(player1.getVictoryPoints() == player1VictoryPoints);
      player2.setVictoryPoints(player2VictoryPoints);
      assert(player2.getVictoryPoints() == player2VictoryPoints);

      // Rig dice
      int[] dice = {5, 5, 5};
      //int[] noDice = {};

      roma.setAvailableActionDice(dice);
      assert(isPermutation(roma.getAvailableActionDice(), dice));
      
      roma.setCurrentPlayer(PLAYER2);
      assert(roma.getCurrentPlayer() == PLAYER2);

      // Activate tribunus plebis
      roma.activateCard (DISC5, new int[0]);
      assert(player1.getVictoryPoints() == player1VictoryPoints - 1);
      assert(player2.getVictoryPoints() == player2VictoryPoints + 1);

      // Activate tribunus plebis
      roma.activateCard (DISC5, new int[0]);
      assert(player1.getVictoryPoints() == player1VictoryPoints - 2);
      assert(player2.getVictoryPoints() == player2VictoryPoints + 2);

      // Activate tribunus plebis
      roma.activateCard (DISC5, new int[0]);
      assert(player1.getVictoryPoints() == player1VictoryPoints - 3);
      assert(player2.getVictoryPoints() == player2VictoryPoints + 3);

      // Check game over
      assert(roma.isGameOver());
   }

   private void checkFaceUpCards (TestablePlayer player, int[] faceUpCards) {
      int[] playerCards = player.getFaceUpCards();

      for (int i = 0; i < 7; i++) {
         assert(playerCards[i] == faceUpCards[i]);
      }
   }

}
