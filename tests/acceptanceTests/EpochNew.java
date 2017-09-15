package tests.acceptanceTests;

import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;

import java.util.Arrays;

import tests.main.*;
import tests.userProgram.Roma;

/* *
 * friendly test which only checks a few things along the way 
 *
 * borrowed Thu09Bell's idea of splitting up into different states
 *
 * by Jocelyn Dang (Mon11Tuba)
 * 8/5/10
 * please email jocelynd@cse.unsw.edu.au if there are any errors/queries 
 */

public class EpochNew extends RomaTest {

   RomaEngine roma;

   public String toString () {
      return "Epoch Tests";
   }

   public void run () {
      setupGame ();
      roundOne ();
      roundTwo ();
      roundThree ();
      roundFour ();
      roundFive ();
      roundSix ();
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

      int[] player1Cards = {NO_CARD, NO_CARD, CONSUL, LEGIONARIUS, 
                            NERO, SENATOR, NO_CARD};
      int[] player2Cards = {ARCHITECTUS, CONSUL, CONSILIARIUS, LEGAT, 
                            NO_CARD, NO_CARD, NO_CARD};

      // let's put 4 cards onto the board 
      player1.setFaceUpCards (player1Cards);
      player2.setFaceUpCards (player2Cards);

      assert (Arrays.equals(player1Cards, player1.getFaceUpCards()));
      assert (Arrays.equals(player2Cards, player2.getFaceUpCards()));
   }


   private int[] createGameDeck () {
      int[] deck = {FORUM,    GLADIATOR, AESCULAPINUM, ARCHITECTUS,
                    TEMPLUM,  TURRIS,    MACHINA,      CENTURIO, 
                    BASILICA, SICARIUS,  TRIBUNUS_PLEBIS};
      return deck;
   }


   /**
    * Current player is Player 1
    * No (VP) deductions yet since roma.endCurrentTurn hasn't been invoked
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1    DICE DISC    PLAYER 2
    *  NO_CARD        1         ARCHITECTUS
    *  NO_CARD        2         CONSUL
    *  CONSUL         3         CONSILIARIUS
    *  LEGIONARIUS    4         LEGAT
    *  NERO           5         NO_CARD 
    *  SENATOR        6         NO_CARD
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
      int costOfCard;

      int[] dice = {6, 2, 1};
      roma.setAvailableActionDice (dice);     

      // drawing cards is hard, so i'll just set their hand
      int[] card = {TRIBUNUS_PLEBIS};
      currentPlayer.setHand (card);
      assert (isPermutation(currentPlayer.getHand(), card));

      roma.useActionDieForMoney (6);
      roma.useActionDieForMoney (2);
      currentMoney += 6 + 2;
      assert(currentPlayer.getMoney() == currentMoney);
      
      costOfCard = roma.getCost (TRIBUNUS_PLEBIS);
      assert(costOfCard == 5);
      // costs 5 sestertii, we drew 8, so laying the card should be successful
      roma.layCard (TRIBUNUS_PLEBIS, DISC1);
      assert (currentPlayer.getMoney () == currentMoney - costOfCard) :
    	  "Actual money: "+currentPlayer.getMoney();

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
    *  TRIBUNUS_PLEBIS      1         ARCHITECTUS
    *  NO_CARD              2         CONSUL
    *  CONSUL               3         CONSILIARIUS
    *  LEGIONARIUS          4         LEGAT
    *  NERO                 5         NO_CARD 
    *  SENATOR              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 10            VP: 19      VP: 7
    *   $3                 $$$        $0
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: empty
    *  PLAYER 2 Cards: empty
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */

   private void roundTwo () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER2);
      int currentVP = currentPlayer.getVictoryPoints ();
      int vpStockpile = this.roma.getVictoryPointsStockpile ();
   //   int currentMoney = currentPlayer.getMoney ();

      int[] dice = {6, 4, 1};
      this.roma.setAvailableActionDice (dice);     
     
      // add sicarius to player 2's hand 
      int[] card = {SICARIUS};
      currentPlayer.setHand (card);
      assert (isPermutation(currentPlayer.getHand(), card));

      this.roma.useActionDieForMoney (6);

      // activation of legat should be possible, as we have a dice with value 4
      assert (this.roma.activateCard (DISC4, new int[0]));

      // opponent had 2 empty dice discs - we get 2 VP (taken from stockpile)
      assert (currentPlayer.getVictoryPoints () == currentVP + 2);
      assert (this.roma.getVictoryPointsStockpile () == vpStockpile - 2);
      roma.endCurrentTurn ();
   }


   /**
    * Current Player is Player 1
    * Player 1 is deducted 2 vp for 2 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  TRIBUNUS_PLEBIS      1         ARCHITECTUS
    *  NO_CARD              2         CONSUL
    *  CONSUL               3         CONSILIARIUS
    *  LEGIONARIUS          4         LEGAT
    *  NERO                 5         NO_CARD 
    *  SENATOR              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 8            VP: 19      VP: 9
    *   $3                 $$$        $6
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: empty
    *  PLAYER 2 Cards: SICARIUS
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */


   private void roundThree () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER1);
      TestablePlayer opponent      = roma.getPlayer (PLAYER2);
      int currentVP = currentPlayer.getVictoryPoints ();
      int opponentVP = opponent.getVictoryPoints ();
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {3, 1, 1};
      roma.setAvailableActionDice (dice);     

      // activate tribunus plebis, take a VP from the opponent
      assert (roma.activateCard (DISC1, new int[0]));
      assert (currentPlayer.getVictoryPoints () == currentVP + 1);
      assert (opponent.getVictoryPoints () == opponentVP - 1);

      // use up our remaining dice
      this.roma.useActionDieForMoney (3);
      this.roma.useActionDieForMoney (1);
      assert (currentPlayer.getMoney () == currentMoney + 4);

      roma.endCurrentTurn ();
   }


   /**
    * Current Player is Player 2
    * Player 2 is deducted 3 vp for 3 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  TRIBUNUS_PLEBIS      1         ARCHITECTUS
    *  NO_CARD              2         CONSUL
    *  CONSUL               3         CONSILIARIUS
    *  LEGIONARIUS          4         LEGAT
    *  NERO                 5         NO_CARD 
    *  SENATOR              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 9            VP: 22      VP: 5
    *   $7                 $$$        $6
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: empty
    *  PLAYER 2 Cards: SICARIUS
    *  Draw Pile: lots of cards
    *  Discard Pile: empty
    */
	

   private void roundFour () {
      TestablePlayer currentPlayer = roma.getPlayer (PLAYER2);
      int currentMoney = currentPlayer.getMoney ();
     // int currentVP = currentPlayer.getVictoryPoints ();
      int costOfCard;

      int[] cardToDestroy = {DISC1};

      int[] dice = {3, 1, 1};
      roma.setAvailableActionDice (dice);     

      roma.useActionDieForMoney (3);
      roma.useActionDieForMoney (1);
      currentMoney += 4;
      assert (currentPlayer.getMoney () == currentMoney);

      // we have $10, so laying card should be possible (sicarius costs $9)
      costOfCard = roma.getCost (SICARIUS);
      // lay sicarius on top of architectus, which is discarded
      roma.layCard (SICARIUS, DISC1);
      assert (currentPlayer.getMoney () == currentMoney - costOfCard);

      // get rid of opponent's tribunus plebis before 
      // they take another VP from us! both cards are discarded
      assert (roma.activateCard (DISC1, cardToDestroy));
      
      roma.endCurrentTurn ();
   }


   /**
    * Current Player is Player 1
    * Player 1 is deducted 3 vp for 3 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  NO_CARD              1         NO_CARD
    *  NO_CARD              2         CONSUL
    *  CONSUL               3         CONSILIARIUS
    *  LEGIONARIUS          4         LEGAT
    *  NERO                 5         NO_CARD 
    *  SENATOR              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 6            VP: 25      VP: 5
    *   $7                 $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: empty
    *  PLAYER 2 Cards: empty
    *  Draw Pile: lots of cards
    *  Discard Pile: SICARIUS, TRIBUNUS_PLEBIS, ARCHITECTUS
    */
	

   private void roundFive () {
      //TestablePlayer currentPlayer = roma.getPlayer (PLAYER1);

      int[] dice = {1, 4, 4};
      roma.setAvailableActionDice (dice);     

      // win the battle! though legat only has defence 2 anyway...
      roma.rigBattleDie (6);
 
      // activate legionarius and destroy opposing card
      roma.activateCard (DISC4, new int[0]);

      assert (roma.getAvailableActionDice ().length == 2); 
      roma.endCurrentTurn ();
   }

   /**
    * Current Player is Player 2
    * Player 2 is deducted 5 vp for 5 empty disc slots
    *
    * ------------ Game Board ------------
    *
    *  PLAYER 1        DICE DISC      PLAYER 2
    *  NO_CARD              1         NO_CARD
    *  NO_CARD              2         CONSUL
    *  CONSUL               3         CONSILIARIUS
    *  LEGIONARIUS          4         NO_CARD
    *  NERO                 5         NO_CARD 
    *  SENATOR              6         NO_CARD
    *  NO_CARD              7         NO_CARD
    *                   (board)
    *  VP: 6            VP: 30      VP: 0
    *   $7                 $$$        $1
    *
    * ------- Cards in hands/piles ------- 
    *
    *  PLAYER 1 Cards: empty
    *  PLAYER 2 Cards: empty
    *  Draw Pile: lots of cards
    *  Discard Pile: SICARIUS, TRIBUNUS_PLEBIS, ARCHITECTUS, LEGAT
    */
	

   private void roundSix () {
      TestablePlayer player2 = roma.getPlayer (PLAYER2);
      int[] discardPile = {TRIBUNUS_PLEBIS, SICARIUS, ARCHITECTUS, LEGAT};

      // player 2 has no VP left - game is over
      assert (player2.getVictoryPoints () == 0);
      assert (roma.isGameOver ());
      assert (roma.getVictoryPointsStockpile () == 30);     

      // we've only discarded three cards over the duration of the game
      assert (isPermutation (roma.getDiscardPile (), discardPile));
   }
}

