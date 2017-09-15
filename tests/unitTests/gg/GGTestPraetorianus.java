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
public class GGTestPraetorianus extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {-1,PRAETORIANUS,PRAETORIANUS,PRAETORIANUS,-1,-1, -1};
        int[] diceValues = {2,3,4};
        int[] args = new int[1];
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int[] oppFaceUpCards = {-1,LEGIONARIUS,LEGIONARIUS,LEGIONARIUS,-1,-1,-1};        
        player2.setFaceUpCards(oppFaceUpCards);
        player1.setVictoryPoints(17);//In case the players run out due
        player2.setVictoryPoints(17);//to empty dice discs
        
        //choose to block disc 2
        args[0] = 2;
        
        //activate the one on disc 4, should return true
        assert(game.activateCard(4, args));

        //end the turn to test blocked discs
        game.endCurrentTurn();
        
        //now player2's turn, set dice values
        game.setAvailableActionDice(diceValues);
        
        //attempt to activate legionarius on 2, should return false, repeat twice
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[2] == PRAETORIANUS);
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[2] == PRAETORIANUS);
        
        //end turn to allow for more testing of blocking
        game.endCurrentTurn();
        
        //set player1's dice now
        game.setAvailableActionDice(diceValues);
        
        //now block all three of the opponent's occupied discs,
        //always returns true
        args[0] = 2;
        assert(game.activateCard(3, args));
        args[0] = 3;
        assert(game.activateCard(4, args));
        args[0] = 4;
        assert(game.activateCard(2, args));
        
        //end current turn
        game.endCurrentTurn();
        
        //set dice for player2, then check discs can't activate
        game.setAvailableActionDice(diceValues);
        
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[1] == PRAETORIANUS);
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[2] == PRAETORIANUS);
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[3] == PRAETORIANUS);
        
        //lay cards on blocked dice discs and check that they
        //are still blocked
        player2.setFaceUpCards(faceUpCards);
        
        //check they can't be activated
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[1] == PRAETORIANUS);
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[2] == PRAETORIANUS);
        game.rigBattleDie (6);
        game.activateCard(2, args);
        assert (player1.getFaceUpCards ()[3] == PRAETORIANUS);
    
    }

    @Override
    public String toString() {
        return "GG's Praetorianus Tests";
    }
}
