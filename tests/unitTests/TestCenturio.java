package tests.unitTests;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*; 
import static tests.main.DiscCode.*; 
import static tests.main.PlayerCode.*;

// Written by Sam Killin (Thur11Pipe) on 18th May.
// Tests centurio's functionality with and without the need of a supplementary dice
//Email: z3253252@student.unsw.edu.au             

public class TestCenturio extends RomaTest {
    @Override
    public void run() {
        testNoSuppDice();   //attack with no supplementary dice specified
        testWithSuppDice();    //attack with supplementary dice specified
    }

    private void testNoSuppDice() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        
        //Player one face up cards
        int[] player1Cards = { NO_CARD, CENTURIO, NO_CARD, NO_CARD, NO_CARD, NO_CARD, NO_CARD };
        player1.setFaceUpCards(player1Cards);
        //assert p1 face up cards were set
        for (int i = 0; i < player1Cards.length; i++) { assert(player1.getFaceUpCards()[i] == player1Cards[i]); }
        
        //Player two face up cards
        int[] player2Cards = { NO_CARD, LEGAT, NO_CARD, NO_CARD, NO_CARD, NO_CARD, NO_CARD };
        player2.setFaceUpCards(player2Cards);
        
        //assert p2 face up cards were set
        for (int i = 0; i < player2Cards.length; i++) { assert(player2.getFaceUpCards()[i] == player2Cards[i]); }
        
        game.setCurrentPlayer(PLAYER1);
        
        // Set available dice for player1
        int[] availableDice = {2, 2};
        game.setAvailableActionDice(availableDice);
        
        //No arguments provided. Centurio should attack the opposite player without need for a supplementary dice
        int[] centurioArgs = new int[0]; 
        
        //Rig the battle die to dominate! bwa ha haaa
        game.rigBattleDie(6);
        
        //check activation was successful
        assert(game.activateCard(2, centurioArgs));
        assert(player2.getFaceUpCards()[DISC2 - 1] == NO_CARD);        
        assert(game.getAvailableActionDice().length == 1);
        
        //try to activate again - no card on opposite side, activation should fail
        // kitten: commenting out due to invalid game play
        // assert(!game.activateCard(2, centurioArgs));
        // assert(player2.getFaceUpCards()[DISC2 - 1] == NO_CARD);
        // assert(game.getAvailableActionDice().length == 1);
        
        //Reset player two face up cards
        player2Cards = new int[] { NO_CARD, TURRIS, NO_CARD, NO_CARD, NO_CARD, NO_CARD, NO_CARD };
        player2.setFaceUpCards(player2Cards);
        for (int i = 0; i < player2Cards.length; i++) { assert(player2.getFaceUpCards()[i] == player2Cards[i]); }
        
        //set battle die to 1, this will NOT kill the enemy
        game.rigBattleDie(1);
        assert(game.activateCard(2, centurioArgs));
        assert(player2.getFaceUpCards()[DISC2 - 1] == TURRIS);
        assert(game.getAvailableActionDice().length == 0);
    }
    
    private void testWithSuppDice() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        
        //-------------------------------------------
        // FIRST TEST - DESTROY CARD ON OPPOSITE SIDE
        //-------------------------------------------
        
        //Player one face up cards
        int[] player1Cards = { NO_CARD, NO_CARD, CENTURIO, NO_CARD, NO_CARD, NO_CARD, NO_CARD };
        player1.setFaceUpCards(player1Cards);
        //assert p1 face up cards were set
        for (int i = 0; i < player1Cards.length; i++) { assert(player1.getFaceUpCards()[i] == player1Cards[i]); }
        
        //Player two face up cards
        int[] player2Cards = { NO_CARD, NO_CARD, LEGAT, NO_CARD, NO_CARD, NO_CARD, NO_CARD };
        player2.setFaceUpCards(player2Cards);
        //assert p2 face up cards were set
        for (int i = 0; i < player2Cards.length; i++) { assert(player2.getFaceUpCards()[i] == player2Cards[i]); }
        
        game.setCurrentPlayer(PLAYER1);
        
        // Set available dice for player1
        int[] availableDice = {3, 1};
        game.setAvailableActionDice(availableDice);
        
        //Argument of 1 provided and battle dice set to 1. These together equal legats defence of 2 and therefore the attack should be successful.
        int[] centurioArgs = new int[1];
        centurioArgs[0] = 1;
        game.rigBattleDie(1);
        //check activation was successful and legat was removed
        assert(game.activateCard(3, centurioArgs));
        assert(player2.getFaceUpCards()[DISC3 - 1] == NO_CARD);    
        assert(game.getAvailableActionDice().length == 0);
        
        //---------------------------------------------------------
        // SECOND TEST - NO CARD ON OPPOSITE SIDE, ACTIVATION FAILS
        //---------------------------------------------------------
        
        //assert player 2 has no cards in play
        for (int i = 0; i < player2.getFaceUpCards().length; i++) { assert(player2.getFaceUpCards()[i] == NO_CARD); }
        
      
        {
        	int[] availableDice1 = {3, 6};
        	game.setAvailableActionDice(availableDice1);
        }
        
        centurioArgs[0] = 6; // sup dice
        //set battle die to 1, this will NOT kill the enemy
        game.rigBattleDie(1);
//        assert(!game.activateCard(3, new int [0]));
        assert(game.getAvailableActionDice().length == 2);
        
        //------------------------------------------------------------
        // THIRD TEST - NERO ON OPPOSITE SIDE.
        //              BATTLE DICE + SUPPLEMENTARY DICE REQUIRED FOR SUCCESSFULL KILL
        //------------------------------------------------------------
        
        player2Cards = new int[] { NO_CARD, NO_CARD, NERO, NO_CARD, NO_CARD, NO_CARD, NO_CARD };
        player2.setFaceUpCards(player2Cards);
        //assert p2 face up cards were set
        for (int i = 0; i < player2Cards.length; i++) { assert(player2.getFaceUpCards()[i] == player2Cards[i]); }

        game.rigBattleDie(4);
        
        //battle die of 4 plus supp dice of 6 required to kill nero with a defence value of 9
        assert(game.activateCard(3, centurioArgs));
        assert(player2.getFaceUpCards()[DISC3 - 1] == NO_CARD);    
        assert(game.getAvailableActionDice().length == 0);
    }
    
    @Override
    public String toString() {
        return "Centurio test";
    }

}
