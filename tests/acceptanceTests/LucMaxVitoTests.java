package tests.acceptanceTests;

import static tests.main.CardCode.*;
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;

import java.util.Arrays;

import tests.main.*;
import tests.userProgram.Roma;

/* *
 * full five round game that features Gladiator, Templum, Forum, Onager, 
 * Senator, and the overpoweredness of Mercator
 * 
 * borrowed Thu09Bell's idea of splitting up into different states
 *
 * template taken from Jocelyn Dang's (Mon11Tuba) tests
 * 
 * email Lachlan Dally: lachd@cse.unsw.edu.au, Matthew Saxby: mcsa293@cse.unsw.edu.au
 * or Vito Cassisi: vcas720@cse.unsw.edu.au with corrections/bugs
 */

public class LucMaxVitoTests extends RomaTest {

   RomaEngine roma;

   public String toString () {
      return "Lucius, Maximus and Vito's tests";
   }

   public void run () {
      setupGame ();
      roundOne ();
      roundTwo ();
      roundThree ();
      roundFour ();
      roundFive ();
   }


   public void setupGame () {
      this.roma = new Roma ();
      assert (!this.roma.isGameOver());
 
      // local function to set face up cards for each player
      setBoardFaceUpCards ();

      // rig the deck
      this.roma.setDrawPile (createGameDeck ());
   }


   private void setBoardFaceUpCards () {
      TestablePlayer player1 = roma.getPlayer (PLAYER1);
      TestablePlayer player2 = roma.getPlayer (PLAYER2);

      int[] player1Cards = {NO_CARD, NO_CARD, TEMPLUM, FORUM, 
                            ARCHITECTUS, MERCATOR, NO_CARD};
      int[] player2Cards = {TRIBUNUS_PLEBIS, SENATOR, ONAGER, GLADIATOR, 
                            NO_CARD, NO_CARD, NO_CARD};

      // let's put 4 cards onto the board 
      player1.setFaceUpCards (player1Cards);
      player2.setFaceUpCards (player2Cards);

      assert (Arrays.equals(player1Cards, player1.getFaceUpCards()));
      assert (Arrays.equals(player2Cards, player2.getFaceUpCards()));
   }


   private int[] createGameDeck () {
      int[] deck = {HARUSPEX, LEGAT, GLADIATOR, LEGIONARIUS, NERO, CENTURIO, 
                    LEGIONARIUS, FORUM, VELITES, ESSEDUM, LEGIONARIUS, 
                    SICARIUS};
      return deck;
   }


   /**
    * Current player is Player 1
    * No (VP) deductions yet since roma.endCurrentTurn hasn't been invoked
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  NO_CARD        1         TRIBUNUS
    *  NO_CARD        2         SENATOR
    *  TEMPLUM        3         ONAGER
    *  FORUM          4         GLADIATOR
    *  ARCHITECTUS    5         NO_CARD 
    *  MERCATOR       6         NO_CARD
    *  NO_CARD        7         NO_CARD
    *              (board)
    *  VP: 10       VP: 16      VP: 10
    *   $0            $$$        $0
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: empty
    *  PLAYER 2 Cards: empty
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */

   private void roundOne () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER1);
      int currentMoney = currentPlayer.getMoney ();
      //int costOfCard;

      int[] dice = {6, 5, 3};
      roma.setAvailableActionDice (dice);     

      // drawing cards is hard, so i'll just set their hand
      int[] card = {HARUSPEX};
      currentPlayer.setHand (card);
      assert (isPermutation(currentPlayer.getHand(), card));

      roma.useActionDieForMoney (6);
      roma.useActionDieForMoney (5);
      currentMoney += 6 + 5;
      assert(currentPlayer.getMoney() == currentMoney);
      
      // switch to player 2, run phase 1 & 2
      roma.endCurrentTurn ();
   }



   /**
    * Current Player is Player 2
    * Player 2 is deducted 3 vp for 3 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  NO_CARD              1         TRIBUNUS
    *  NO_CARD              2         SENATOR
    *  TEMPLUM              3         ONAGER
    *  FORUM                4         GLADIATOR
    *  ARCHITECTUS          5         NO_CARD 
    *  MERCATOR             6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 10            VP: 19      VP: 7
    *   $11                $$$        $0
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: HARUSPEX
    *  PLAYER 2 Cards: empty
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */

   private void roundTwo () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER2);
      int[] cardToReturn = {DISC6};
      
      int[] dice = {6, 4, 2};
      this.roma.setAvailableActionDice (dice);     
     
      // add LEGIONARIUS to player 2's hand 
      int[] card = {LEGIONARIUS};
      currentPlayer.setHand (card);
      assert (isPermutation(currentPlayer.getHand(), card));

      this.roma.useActionDieForMoney (6);

      // activation of gladiator should be possible, as we have a dice with value 4
      assert (this.roma.activateCard (DISC4, cardToReturn));

      roma.endCurrentTurn ();
   }


   /**
    * Current Player is Player 1
    * Player 1 is deducted 4 vp for 4 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  NO_CARD              1         TRIBUNUS
    *  NO_CARD              2         SENATOR
    *  TEMPLUM              3         ONAGER
    *  FORUM                4         GLADIATOR
    *  ARCHITECTUS          5         NO_CARD 
    *  NO_CARD              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 6            VP: 23      VP: 7
    *   $11                $$$        $6
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: HARUSPEX, MERCATOR
    *  PLAYER 2 Cards: LEGIONARIUS
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */


   private void roundThree () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER1);
      //TestablePlayer opponent      = roma.getPlayer (PLAYER2);
      int currentVP = currentPlayer.getVictoryPoints ();
      //int opponentVP = opponent.getVictoryPoints ();
      int currentMoney = currentPlayer.getMoney ();
      int vPStockpile = roma.getVictoryPointsStockpile();
      
      assert (roma.getVictoryPointsStockpile () == 23):roma.getVictoryPointsStockpile();


      int[] dice = {6, 4, 4};
      roma.setAvailableActionDice (dice);     

      // activate FORUM, and incidentally templum
      int[] args = {6, 4};
      assert (roma.activateCard (DISC4, args));
      assert (currentPlayer.getVictoryPoints () == currentVP + 10);
      assert (roma.getVictoryPointsStockpile () ==  vPStockpile - 10);
      
      int costOfCard = roma.getCost (HARUSPEX);
      assert(costOfCard == 4);
      // costs 4 sestertii, weve got 11, so laying the card should be successful
      roma.layCard (HARUSPEX, DISC2);
      assert (currentPlayer.getMoney () == currentMoney - costOfCard);
      
      // use up our remaining dice
      roma.endCurrentTurn ();
   }


   /**
    * Current Player is Player 2
    * Player 2 is deducted 3 vp for 3 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  NO_CARD              1         TRIBUNUS
    *  HARUSPEX             2         SENATOR
    *  TEMPLUM              3         ONAGER
    *  FORUM                4         GLADIATOR
    *  ARCHITECTUS          5         NO_CARD 
    *  NO_CARD              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 16            VP: 16      VP: 4
    *   $7                 $$$        $6
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: MERCATOR
    *  PLAYER 2 Cards: LEGIONARIUS
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */
    

   private void roundFour () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER2);
    //  int currentMoney = currentPlayer.getMoney ();
     // int currentVP = currentPlayer.getVictoryPoints ();
      //int costOfCard;

      int[] cardToDestroy = {DISC4};

      int[] dice = {4, 3, 2};
      roma.setAvailableActionDice (dice);    

      roma.rigBattleDie (6);
      // USING ONAGER TO DESTROY FORUM
      assert (roma.activateCard (DISC3, cardToDestroy));
      
      assert (roma.getAvailableActionDice ().length == 2);
      
      // using senator to lay legionarius
      int[] args = {LEGIONARIUS, DISC5};
      assert(roma.activateCard (DISC2, args));
      
      int[] faceUpCards = currentPlayer.getFaceUpCards();
      // check that legionarius was laid properly.
      assert(faceUpCards[4] == LEGIONARIUS);

      //use gladiator to send haruspex back to hand
      assert(roma.activateCard (DISC4, new int[]{DISC2}));
      
      roma.endCurrentTurn ();
   }


   /**
    * Current Player is Player 1
    * Player 1 is deducted 5 vp for 5 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  NO_CARD              1         TRBINUS
    *  NO_CARD             2         SENATOR
    *  TEMPLUM              3         ONAGER
    *  NO_CARD              4         GLADIATOR
    *  ARCHITECTUS          5         LEGIONARIUS
    *  NO_CARD              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 11            VP: 21      VP: 4
    *   $7                 $$$        $6
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: MERCATOR, HARUSPEX
    *  PLAYER 2 Cards: empty
    *  Draw Pile: lots of cards
    *  Discard Pile: FORUM
    */
    
   //player 1's turn
   private void roundFive () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER1);
      TestablePlayer player2 = roma.getPlayer (PLAYER2);
      //int currentMoney = currentPlayer.getMoney();
      int[] dice = {5, 3, 6};
      roma.setAvailableActionDice (dice);     
      int[] noSwaps = {4};
      
      int[] discardPile = {FORUM};
      assert (currentPlayer.getVictoryPoints()  == 11):currentPlayer.getVictoryPoints();

      this.roma.useActionDieForMoney (5);
      this.roma.useActionDieForMoney (3);
      assert(currentPlayer.getMoney() == 15);
      
      //cost 7, so therefore have 8 left
      roma.layCard (MERCATOR, DISC6);
           
      //use remaining gold to leech points from opponent, ending the game
      assert (roma.activateCard(DISC6, noSwaps)); 
 
      assert (roma.getAvailableActionDice ().length == 0):roma.getAvailableActionDice().length; 
      assert (player2.getVictoryPoints () == 0);
      assert (currentPlayer.getVictoryPoints()  == 15);
      assert (roma.getVictoryPointsStockpile () == 21);
      assert (roma.isGameOver ());
      
      assert (isPermutation (roma.getDiscardPile (), discardPile));
      
   }
}
