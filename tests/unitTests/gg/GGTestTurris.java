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
public class GGTestTurris extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {-1,ONAGER,-1,-1,-1,-1, -1};
        int[] diceValues = {2,2,2};
        int[] args = new int[1];
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int[] oppFaceUpCards = {-1,BASILICA,TURRIS,TEMPLUM,-1,-1,-1};        
        player2.setFaceUpCards(oppFaceUpCards);
        
        //set args so that onager attacks basilica, rig battle dice to lose
        args[0] = 2;
        game.rigBattleDie(5);
        
        //activate card, should return true
        assert(game.activateCard(2, args));
        
        //check that basilica remains
        assert(player2.getFaceUpCards()[1] == BASILICA);
        
        //attack templum, rig dice to lose
        args[0] = 4;
        game.rigBattleDie(2);
        
        //activate card, should return true
        assert(game.activateCard(2, args));
        
        //check that templum remains
        assert(player2.getFaceUpCards()[3] == TEMPLUM);
        
        //attack turris, rig to win
        args[0] = 3;
        game.rigBattleDie(6);

        //activate card, should return true
        assert(game.activateCard(2, args));
        
        //check that turris is defeated
        assert(player2.getFaceUpCards()[2] == NO_CARD);
    
    }

    @Override
    public String toString() {
        return "GG's Turris Tests";
    }
}
