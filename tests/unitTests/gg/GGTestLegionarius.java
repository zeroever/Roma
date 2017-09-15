/**
 * 
 */
package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
import static tests.main.PlayerCode.*;


/**
 * @author Saki Tang, Pieter Jontera Lius
 *
 */
public class GGTestLegionarius extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {-1,LEGIONARIUS,-1,-1,-1,-1, -1};
        int[] diceValues = {2,2,2};
        int[] args = new int[0];
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int[] oppFaceUpCards = {-1,BASILICA,-1,-1,-1,-1,-1};        
        player2.setFaceUpCards(oppFaceUpCards);
        
        //rig dice to be 5
        game.rigBattleDie(5);
        
        //activate legionarius, should return true
        assert(game.activateCard(2, args));
        
        //check that basilica is defeated
        assert(player2.getFaceUpCards()[1] == NO_CARD);
        
        //set another card, sicarius
        oppFaceUpCards[1] = SICARIUS;
        player2.setFaceUpCards(oppFaceUpCards);
        
        //rig dice again, try battle, should not win
        game.rigBattleDie(1);
        assert(game.activateCard(2, args));
        
        //check sicarius is still there
        assert(player2.getFaceUpCards()[1] == SICARIUS);
        
        //rig dice again, try battle, should win
        game.rigBattleDie(6);
        assert(game.activateCard(2, args));
        
        //check sicarius is not there
        assert(player2.getFaceUpCards()[1] == NO_CARD);
    
    }

    @Override
    public String toString() {
        return "GG's Legionarius Tests";
    }
}