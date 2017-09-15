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
public class GGTestVelites extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {-1,VELITES,-1,-1,-1,-1, -1};
        int[] diceValues = {2,2,2};
        int[] args = new int[1];
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int[] oppFaceUpCards = {-1,MERCATOR,HARUSPEX,GLADIATOR,-1,-1,-1};        
        player2.setFaceUpCards(oppFaceUpCards);
        
        //set args so that velites attacks mercator, rig battle dice to win
        args[0] = 2;
        game.rigBattleDie(3);
        
        //activate card, should return true
        assert(game.activateCard(2, args));
        
        //check that mercator is defeated
        assert(player2.getFaceUpCards()[1] == NO_CARD);
        
        //attack gladiator, rig dice to win
        args[0] = 4;
        game.rigBattleDie(5);
        
        //activate card, should return true
        assert(game.activateCard(2, args));
        
        //check that gladiator is defeated
        assert(player2.getFaceUpCards()[3] == NO_CARD);
        
        //attack haruspex, rig to lose
        args[0] = 3;
        game.rigBattleDie(2);

        //activate card, should return true
        assert(game.activateCard(2, args));
        
        //check that forum remains
        assert(player2.getFaceUpCards()[2] == HARUSPEX);
    
    }

    @Override
    public String toString() {
        return "GG's Velites Tests";
    }
}
