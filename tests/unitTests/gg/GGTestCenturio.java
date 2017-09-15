/**
 * 
 */
package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
//import static tests.main.DiscCode.*;
import static tests.main.PlayerCode.*;


/**
* @author Saki Tang, Pieter Jontera Lius
*
*/
public class GGTestCenturio extends RomaTest{

   @Override
   public void run() {
       RomaEngine game = new Roma();
       TestablePlayer player1 = game.getPlayer(PLAYER1);
       TestablePlayer player2 = game.getPlayer(PLAYER2);
       int[] playerFaceUpCards = {CENTURIO,-1,-1,-1,-1,-1,-1};
       int[] opponentFaceUpCards = {SICARIUS,-1,-1,-1,-1,-1,-1};
       int[] diceValues = {1,1,1};
       
       game.setCurrentPlayer(PLAYER1);
       game.setAvailableActionDice(diceValues);
       player1.setFaceUpCards(playerFaceUpCards);
       player2.setFaceUpCards(opponentFaceUpCards);
       
       //rig battle die to be 1, no extra dice used
       game.rigBattleDie(1);
       
       //activation occurs, should return true
       assert(game.activateCard(1, new int[0]));

       //sicarius should still remain
       assert(player2.getFaceUpCards()[0] == SICARIUS);
       
       //rig dice to be 1 again, this time use 1 for extra attack
       game.rigBattleDie(1);

       
       //activation should occur, should return true
       {
    	   int [] args = {1};
    	   assert(game.activateCard(1, args));
       }
       
       //Sicarius should be discarded
       assert(player2.getFaceUpCards()[0] == NO_CARD);
       
       //resetting dice values
       game.setAvailableActionDice(diceValues);
       
   }

   @Override
   public String toString() {
       return "GG's Centurio Tests";
   }
   
}
