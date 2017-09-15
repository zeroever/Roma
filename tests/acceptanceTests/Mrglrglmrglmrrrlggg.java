package tests.acceptanceTests;

import static tests.main.CardCode.*;
import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;

import tests.main.*;
import tests.userProgram.*;

/**
 * @author rogerliu
 * assertions per 2 player cycle
 * assertions made b4 every cycle
 * its not a complete game but it should work
 * hf gl
 */
public class Mrglrglmrglmrrrlggg extends RomaTest {
   private TestablePlayer p1;
   private TestablePlayer p2;
   private RomaEngine roma;
   
   //for myself
   public static void main(String[] args){
      RomaTest test = new Mrglrglmrglmrrrlggg();
      test.run();
      System.out.println("Mrglrglmrglmrrrlggg!!!");
   }
   
   @Override
   public void run() {
      initialiseTest();
      cycle1();
      cycle2();
      cycle3();
   }

   /**
    * initialise to player 1's first turn in phase 3,
    * after all preparations are done, 10 vp each player
    */
   private void initialiseTest() {
      roma = new Roma();
      p1 = roma.getPlayer(PLAYER1);
      p2 = roma.getPlayer(PLAYER2);
      //bottom card indexed 0
      int[] drawPile 
           = {TRIBUNUS_PLEBIS,
              MERCATUS,
              AESCULAPINUM,
              HARUSPEX,
              ARCHITECTUS,
              NERO,
              MACHINA,
              LEGAT,
              TRIBUNUS_PLEBIS,
              CONSUL,
              FORUM,
              CONSILIARIUS,
              PRAETORIANUS,
              LEGAT};
      roma.setDrawPile(drawPile);
      p1.setVictoryPoints(10);
      p2.setVictoryPoints(10);
      p1.setMoney(0);
      p2.setMoney(0);
      int[] p1faceUp = {ESSEDUM, NO_CARD, TURRIS, VELITES, NO_CARD, NO_CARD, SICARIUS};
      p1.setFaceUpCards(p1faceUp);
      int[] p2faceUp = {BASILICA, FORUM, TEMPLUM, LEGIONARIUS, NO_CARD, NO_CARD, NO_CARD};
      p2.setFaceUpCards(p2faceUp);
      
      roma.setCurrentPlayer(PLAYER1);
      //assuming you don't have any cards in hand to start with
   }

   @Override
   public String toString() {
      return "Mrglrglmrglmrrrlggg";
   }
   
   private String toString(int[] S){
      String concat = "";
      for(int i : S){
         concat = concat + i +" ";
      }
      return concat;
   }
   
   /**
    * null can be passed in if I don't wana check it
    */
   private void doPlayerAsserts(TestablePlayer p, int ID,
                                Integer expectedVP, Integer expectedMoney,
                                int[] expectedHand, int[] expectedFaceUpCards){
      assert(expectedVP == null || p.getVictoryPoints() == expectedVP) 
         : "player "+ID+" VP "+p.getVictoryPoints()+
         " does not match expected "+expectedVP;
      assert(expectedMoney == null || p.getMoney() == expectedMoney) 
         : "player "+ID+" Money "+p.getMoney()+
         " does not match expected "+expectedMoney;
      assert(expectedHand == null || isPermutation(p.getHand(), expectedHand)) 
         : "player "+ID+" hand "+ toString(p.getHand())+
         " does not match expected " + toString(expectedHand);
      assert(expectedFaceUpCards == null || isPermutation(p.getFaceUpCards(), expectedFaceUpCards))
         : "player "+ID+" face up cards "+toString(p.getFaceUpCards())+
         " does not match expected "+toString(expectedFaceUpCards);
   
   }
   
//   private void doRomaAsserts(int expectedVPstock, int[] expectedAvailableDice,
//                              int[] expectedDrawPile, int[] expectedDiscardPile,
//                              boolean expectedGameOver){
//      doGameOverAssert(expectedGameOver);
//      if(!expectedGameOver){
//         doVPstockAssert(expectedVPstock);
//         doAvailableDiceAssert(expectedAvailableDice);
//         doDrawPileAssert(expectedDrawPile);
//         doDiscardPileAssert(expectedDiscardPile);
//      }
//   }

//   private void doDiscardPileAssert(int[] expectedDiscardPile) {
//      if(expectedDiscardPile != null){  
//         assert(isPermutation(roma.getDiscardPile(), expectedDiscardPile))
//            : "Roma discard pile "+toString(roma.getDrawPile())+
//            " does not match expected "+toString(expectedDiscardPile);
//      }
//   }
//
//   private void doDrawPileAssert(int[] expectedDrawPile) {
//      if(expectedDrawPile != null){
//         assert(isPermutation(roma.getDrawPile(), expectedDrawPile)) 
//            : "Roma draw pile "+toString(roma.getDrawPile())+
//            " does not match expected "+toString(expectedDrawPile);
//      }
//   }
//
   private void doAvailableDiceAssert(int[] expectedAvailableDice) {
      if(expectedAvailableDice != null){
         assert(isPermutation(roma.getAvailableActionDice(), expectedAvailableDice))
            : "Roma available action dice "+toString(roma.getAvailableActionDice())+
            " does not match expected "+toString(expectedAvailableDice);
      }
   }

   private void doVPstockAssert(int expectedVPstock) {
      assert(roma.getVictoryPointsStockpile() == expectedVPstock) 
         : "Roma VP "+roma.getVictoryPointsStockpile()+
         " does not match expected "+expectedVPstock;
   }

   private void doGameOverAssert(boolean expectedGameOver) {
      assert(roma.isGameOver() == expectedGameOver)
         : "Roma is game over "+roma.isGameOver()+
         " does not match expected "+expectedGameOver;
   }
   
   private CardPicker getPicker(final int card){
      return new CardPicker(){
         public int pickCard(int[] cards) {
            return card;
         }
      };
   }
   
   private void cycle1(){
      int expectedVP = 10;
      int expectedMoney = 0;
      int[] expectedFaceUpCards1 
         = {ESSEDUM, NO_CARD, TURRIS, VELITES, NO_CARD, NO_CARD, SICARIUS};
      
      doPlayerAsserts(p1, PLAYER1, expectedVP, expectedMoney, null, expectedFaceUpCards1);
      
      expectedVP = 10;
      expectedMoney = 0;
      int[] expectedFaceUpCards2
         = {BASILICA, FORUM, TEMPLUM, LEGIONARIUS, NO_CARD, NO_CARD, NO_CARD};
      
      doPlayerAsserts(p2, PLAYER2, expectedVP, expectedMoney, null, expectedFaceUpCards2);
      
      doGameOverAssert(false);
      doVPstockAssert(16);
      
      int[] dice1 = {4, 1, 6};
      roma.setAvailableActionDice(dice1);
      roma.useActionDieForMoney(6);
      roma.useActionDieForCards(1, getPicker(LEGAT));
      roma.layCard(LEGAT, DISC5);
      roma.rigBattleDie(2);
      int[] velites = {DISC4};
      roma.activateCard(DISC4, velites);
      roma.endCurrentTurn();
      
      assert(roma.getCurrentPlayer() == PLAYER2);
      int[] dice2 = {4, 5, 2};
      roma.setAvailableActionDice(dice2);
      int[] forumTemplum = {5, 4};
      roma.activateCard(DISC2, forumTemplum);   //just got 11 vp, congrats on skills
      int[] expectedAvailableDice = {};  
      doAvailableDiceAssert(expectedAvailableDice);
      roma.endCurrentTurn();
   }

   private void cycle2(){
      int expectedVP = 8;
      int expectedMoney = 1;
      int[] expectedFaceUpCards1 
         = {ESSEDUM, NO_CARD, TURRIS, VELITES, LEGAT, NO_CARD, SICARIUS};
      
      doPlayerAsserts(p1, PLAYER1, expectedVP, expectedMoney, null, expectedFaceUpCards1);
      
      expectedVP = 18;
      expectedMoney = 0;
      int[] expectedFaceUpCards2
         = {BASILICA, FORUM, TEMPLUM, LEGIONARIUS, NO_CARD, NO_CARD, NO_CARD};
      
      doPlayerAsserts(p2, PLAYER2, expectedVP, expectedMoney, null, expectedFaceUpCards2);
      
      doGameOverAssert(false);
      doVPstockAssert(10);
      
      int[] dice1 = {5, 5, 3};
      roma.setAvailableActionDice(dice1);
      int[] legat = {};
      roma.activateCard(DISC5, legat);
      roma.activateCard(DISC5, legat);
      roma.useActionDieForMoney(3);
      roma.endCurrentTurn();
      
      int[] dice2 = {5, 1, 3};
      roma.setAvailableActionDice(dice2);
      roma.useActionDieForMoney(5);
      roma.useActionDieForCards(1, getPicker(PRAETORIANUS));
      roma.layCard(PRAETORIANUS, BRIBE_DISC);
      roma.useActionDieForMoney(3);
      roma.endCurrentTurn();
   }
   
   private void cycle3(){
      int expectedVP = 12;
      int expectedMoney = 4;
      int[] expectedFaceUpCards1 
         = {ESSEDUM, NO_CARD, TURRIS, VELITES, LEGAT, NO_CARD, SICARIUS};
      
      doPlayerAsserts(p1, PLAYER1, expectedVP, expectedMoney, null, expectedFaceUpCards1);
      
      expectedVP = 15;
      expectedMoney = 4;
      int[] expectedFaceUpCards2
         = {BASILICA, FORUM, TEMPLUM, LEGIONARIUS, NO_CARD, NO_CARD, PRAETORIANUS};
      
      doPlayerAsserts(p2, PLAYER2, expectedVP, expectedMoney, null, expectedFaceUpCards2);
      
      doGameOverAssert(false);
      doVPstockAssert(9);
      
   }
}
