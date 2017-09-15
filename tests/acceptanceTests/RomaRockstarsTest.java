package tests.acceptanceTests;

import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;

import java.util.Arrays;

import tests.main.*;
import tests.userProgram.Roma;

/* *
 * Simple test for checking several main things 
 * based on Thu09Bell & Epoch idea
 *
 * by Hans <hpie606@cse.unsw.edu.au> & Dave <dkgr582@cse.unsw.edu>
 *  
 */

public class RomaRockstarsTest extends RomaTest {

   RomaEngine game;

   public String toString () {
      return "RomaRockstars Tests";
   }

   public void run () {
      initialState ();
      turn01();
      turn02();
      turn03();
      turn04();
      turn05();
      turn06();
      turn07();
      turn08();
   }


   public void initialState () {
      this.game = new Roma ();
      assert (!this.game.isGameOver());
 
      // local function to set face up cards for each player
      TestablePlayer player1 = game.getPlayer (PLAYER1);
      TestablePlayer player2 = game.getPlayer (PLAYER2);

      int[] player1Cards = {SICARIUS, FORUM, PRAETORIANUS, NO_CARD, LEGAT, NO_CARD, NO_CARD};
      int[] player2Cards = {PRAETORIANUS, TURRIS, NO_CARD, SICARIUS, NERO, NO_CARD, NO_CARD};

      // let's put 4 cards onto the board 
      player1.setFaceUpCards (player1Cards);
      player2.setFaceUpCards (player2Cards);

      assert (Arrays.equals(player1Cards, player1.getFaceUpCards()));
      assert (Arrays.equals(player2Cards, player2.getFaceUpCards()));

      // rig the deck
      int[] deck = {CONSILIARIUS, ARCHITECTUS, GLADIATOR, ARCHITECTUS, LEGAT, CONSILIARIUS, FORUM, TEMPLUM, GLADIATOR, TURRIS, NERO};
      this.game.setDrawPile (deck);
   }

   /* Game State
    * Current Player: PLAYER1
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   EMPTY   LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 10  Money: 0
    * P2    VP: 10  Money: 0
    * VP BOARD  : 16
    * 
    */

   private void turn01() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER1);
      int currentMoney = currentPlayer.getMoney ();
      int costOfCard;

      int[] dice = {5, 5, 4};
      game.setAvailableActionDice (dice);

      // Just set the hand card (without draw)
      int[] card = {NERO};
      currentPlayer.setHand (card);
      assert (isPermutation(currentPlayer.getHand(), card));

      game.useActionDieForMoney (5);
      game.useActionDieForMoney (4);
      currentMoney = currentMoney + 5 + 4;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 9
      
      costOfCard = game.getCost (NERO);
      assert(costOfCard == 8);
      game.layCard (NERO, DISC4);
      assert (currentPlayer.getMoney () == currentMoney - costOfCard);
      // Current money = 1

      // End of turn. Switch to PLAYER2.
      game.endCurrentTurn ();
   }
   
   /* Game State
    * Current Player: PLAYER2 - deducted 3VP for unoccupied discs
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 10  Money: 1
    * P2    VP: 7   Money: 0
    * VP BOARD  : 19
    * 
    */
   
   private void turn02() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER2);
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {1, 1, 2};
      this.game.setAvailableActionDice (dice);

      this.game.useActionDieForMoney (1);
      this.game.useActionDieForMoney (1);
      this.game.useActionDieForMoney (2);
      currentMoney = currentMoney + 1 + 1 + 2;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 4

      game.endCurrentTurn ();
      // End of turn. Switch to PLAYER1
   }
   
   /* Game State
    * Current Player: PLAYER1 - deducted 2VP for unoccupied discs
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 8   Money: 1
    * P2    VP: 7   Money: 4
    * VP BOARD  : 21
    * 
    */

   private void turn03() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER1);
      int currentVP = currentPlayer.getVictoryPoints ();
      int vpStockpile = this.game.getVictoryPointsStockpile ();
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {6, 6, 5};
      this.game.setAvailableActionDice (dice);

      // Add card 
      int[] card = {TURRIS};
      currentPlayer.setHand (card);
      assert (isPermutation(currentPlayer.getHand(), card));

      this.game.useActionDieForMoney (6);
      this.game.useActionDieForMoney (6);
      currentMoney = currentMoney + 6 + 6;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 13
      
      // Activating LEGAT using Dice 5
      assert (this.game.activateCard (DISC5, new int[0]));

      // opponent had 3 empty dice discs - we get 3 VP (taken from stockpile)
      assert (currentPlayer.getVictoryPoints () == currentVP + 3);
      assert (this.game.getVictoryPointsStockpile () == vpStockpile - 3);
      game.endCurrentTurn ();
      // End of Turn. Switch to PLAYER2
   }
   
   /* Game State
    * Current Player: PLAYER2 - deducted 3VP for unoccupied discs
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 11  Money: 13
    * P2    VP: 4   Money: 4
    * VP BOARD  : 21
    * 
    */

   private void turn04() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER2);
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {2, 1, 1};
      this.game.setAvailableActionDice (dice);

      this.game.useActionDieForMoney (2);
      this.game.useActionDieForMoney (1);
      this.game.useActionDieForMoney (1);
      currentMoney = currentMoney + 2 + 1 + 1;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 8

      game.endCurrentTurn ();
      // End of turn. Switch to PLAYER1
       
   }
   
   /* Game State
    * Current Player: PLAYER1 - deducted 2VP for unoccupied discs
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 9   Money: 13
    * P2    VP: 4   Money: 8
    * VP BOARD  : 23
    * 
    */
   
   private void turn05() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER1);
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {1, 3, 1};
      this.game.setAvailableActionDice (dice);

      this.game.useActionDieForMoney (1);
      this.game.useActionDieForMoney (3);
      this.game.useActionDieForMoney (1);
      currentMoney = currentMoney + 1 + 3 + 1;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 18

      game.endCurrentTurn ();
      // End of turn. Switch to PLAYER2
           
   }

   /* Game State
    * Current Player: PLAYER2 - deducted 3VP for unoccupied discs
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 9   Money: 18
    * P2    VP: 1   Money: 8
    * VP BOARD  : 26
    * 
    */
   private void turn06() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER2);
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {2, 3, 4};
      this.game.setAvailableActionDice (dice);

      this.game.useActionDieForMoney (2);
      this.game.useActionDieForMoney (3);
      this.game.useActionDieForMoney (4);
      currentMoney = currentMoney + 2 + 3 + 4;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 17

      game.endCurrentTurn ();
      // End of turn. Switch to PLAYER1
   }
   
   /* Game State
    * Current Player: PLAYER1 - deducted 2VP for unoccupied discs
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 7   Money: 18
    * P2    VP: 1   Money: 17
    * VP BOARD  : 28
    * 
    */
   private void turn07() {
      TestablePlayer currentPlayer = game.getPlayer (PLAYER1);
      int currentMoney = currentPlayer.getMoney ();

      int[] dice = {2, 2, 4};
      this.game.setAvailableActionDice (dice);

      this.game.useActionDieForMoney (2);
      this.game.useActionDieForMoney (2);
      this.game.useActionDieForMoney (4);
      currentMoney = currentMoney + 2 + 2 + 4;
      assert(currentPlayer.getMoney() == currentMoney);
      // Current money = 26

      game.endCurrentTurn ();
      // End of turn. Switch to PLAYER2
   }
   
   /* Game State
    * Current Player: PLAYER2 - deducted 3VP for unoccupied discs (NOT ENOUGH VP - GAME OVER)
    * 
    * DISC: 1       2       3       4       5       6       7
    * P1  : SICAR   FORUM   PRAET   NERO    LEGAT   EMPTY   EMPTY
    * P2  : HARUS   TURRI   EMPTY   SICAR   NERO    EMPTY   EMPTY
    * 
    * P1    VP: 7   Money: 26
    * P2    VP: 0   Money: 17
    * VP BOARD  : 29
    * 
    */
   
   private void turn08() {
      TestablePlayer player2 = game.getPlayer (PLAYER2);

      // player 2 has no VP left - GAME OVER!
      assert (player2.getVictoryPoints () <= 0);
      assert (game.isGameOver ());    
   }
}
