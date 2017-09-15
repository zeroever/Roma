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
public class GGTestEssedum extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {ESSEDUM,LEGIONARIUS,-1,-1,-1,-1, -1};
        int[] diceValues = {1,2,1};
        int[] noArgs = new int[0];
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int[] oppFaceUpCards = {-1,BASILICA,-1,-1,-1,-1,-1};        
        player2.setFaceUpCards(oppFaceUpCards);
        
        //activate essedum, should be true
        assert(game.activateCard(1, noArgs));
        
        //rig dice to be 2
        game.rigBattleDie(2);
        
        //legionarius now attacks, should not win
        assert(game.activateCard(2, noArgs));
        
        //check that basilica is not defeated
        assert(player2.getFaceUpCards()[1] == BASILICA);
        
        //reset dice
        game.setAvailableActionDice(diceValues);

        //activate essedum again, should return true
        assert(game.activateCard(1, noArgs));
        
        //now that basilica has 1 defence, legionarius should win no matter what
        assert(game.activateCard(2, noArgs));
        
        //check that basilica is defeated
        assert(player2.getFaceUpCards()[1] == NO_CARD);
    }

    @Override
    public String toString() {
        return "GG's Essedum Tests";
    }
}
