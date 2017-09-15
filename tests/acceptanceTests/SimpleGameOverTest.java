package tests.acceptanceTests;
// Written by Hayden Stephenson, Fri09Harp.
// Skeleton of file taken from the Thu09Bell test on the wiki.

import tests.main.*;
import tests.userProgram.Roma;

// UDD == Unoccupied Dice Disk

public class SimpleGameOverTest extends RomaTest {
   private TestablePlayer[] players;
   private Roma roma;


   public static void main(String[] args) {
      SimpleGameOverTest test = new SimpleGameOverTest();
      test.run();
   }

   public SimpleGameOverTest() {
      roma = new Roma();
      players = new TestablePlayer[2];
      for (int i = 0; i < players.length; i++) {
         players[i] = roma.getPlayer(i+PlayerCode.PLAYER1);
      }
   }

   public void run() {
      initialState();
      stateZero();
      stateOne();
      stateTwo();
      stateThree();
      stateFour();
      stateFive();
      stateSix();
      stateSeven();
      stateEight();
      stateNine();
      stateTen();
      stateEleven();
      stateTwelve();
      stateThirteen();
      stateFourteen();
      stateFifteen();
      stateSixteen();
      stateSeventeen();
      stateEighteen();
      stateNineteen();
      gameOver();
   }

   void initialState() {
      for (int i = 0; i < players.length; i++) {
         assert(players[i].getVictoryPoints() == 10);
         assert(players[i].getMoney() == 0);
         assert(players[i].getHand().length == 0);
         int[] faceUpCards = players[i].getFaceUpCards();
         assert(faceUpCards.length == 7);
         for (int j = 0; j < faceUpCards.length; j++) {
            assert(faceUpCards[j] == CardCode.NO_CARD);
         }
      }
      assert(roma.getDrawPile().length == 52);
      assert(roma.getDiscardPile().length == 0);
      roma.setCurrentPlayer(PlayerCode.INITIAL_PLAYER);
      assert(roma.getCurrentPlayer() == PlayerCode.INITIAL_PLAYER);
   }

   void stateZero() {
      roma.setCurrentPlayer(PlayerCode.INITIAL_PLAYER);
      // same as players[0], just showing off.

      int faceUpCards[] = {CardCode.NO_CARD, CardCode.NO_CARD, CardCode.NO_CARD,
            CardCode.NO_CARD, CardCode.NO_CARD, CardCode.NO_CARD,
            CardCode.NO_CARD // NOT USED. Just avoids victory point deductions.
      };
      roma.getPlayer(roma.getCurrentPlayer()).setFaceUpCards(faceUpCards);

      roma.setCurrentPlayer(PlayerCode.PLAYER2);
      // same as players[1], just showing off.

      int secondFaceUpCards[] = {CardCode.HARUSPEX, CardCode.NO_CARD, CardCode.FORUM,
            CardCode.BASILICA, CardCode.NO_CARD, CardCode.CONSILIARIUS,
            CardCode.MACHINA // NOT USED.
      };
      roma.getPlayer(roma.getCurrentPlayer()).setFaceUpCards(secondFaceUpCards);


      //!!! GAME START
      roma.endCurrentTurn();  //This affects state one, the game has officially started here.
      //!!!
   }

   void stateOne() {
      int money = players[0].getMoney();
      //int cardsInPile = roma.getDrawPile().length;
      //int discardedPile = roma.getDiscardPile().length;

      int[] dice = {1, 2, 3};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(1);
      roma.useActionDieForMoney(2);
      roma.useActionDieForMoney(3);
      money += 6;

      assert(players[0].getMoney() == money);
      assert(players[0].getVictoryPoints() == 3);
      roma.endCurrentTurn();
   }
   
   void stateTwo() {
      int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
     // int stockpile = roma.getVictoryPointsStockpile();

      assert(victoryPoints == 8);     // ^^ end turn above minuses  2 victory points from this player.

      int[] dice = {2, 3, 4};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(2);
      roma.useActionDieForMoney(3);
      roma.useActionDieForMoney(4);
      money += 9;

      assert(players[1].getMoney() == money);
      assert(players[1].getVictoryPoints() == victoryPoints);
      roma.endCurrentTurn();
   }

   void stateThree() {
      //int money = players[0].getMoney();
      int victoryPoints = players[0].getVictoryPoints();
      
      assert(roma.isGameOver() == true);
      assert(victoryPoints <= 0);
   }
   void stateFour() {


   }
   void stateFive() {
    
   }
   void stateSix() {

   }
   void stateSeven() {

   }
   void stateEight() {

   }
   void stateNine() {

   }
   void stateTen() {

   }
   void stateEleven() {

   }
   void stateTwelve() {

   }
   void stateThirteen() {

   }
   void stateFourteen() {

   }
   void stateFifteen() {

   }
   void stateSixteen() {

   }
   void stateSeventeen() {

   }
   void stateEighteen() {

   }
   void stateNineteen() {
   }

   void gameOver() {
   }

	@Override
	public String toString() {
		return "SimpleGameOverTest";
	}

}
