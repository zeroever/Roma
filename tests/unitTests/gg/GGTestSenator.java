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
public class GGTestSenator extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {SENATOR,-1,-1,-1,-1,-1,-1};
        int[] diceValues = {1,1,1};
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        player1.setMoney(10); //to check that cards are laid for free
        
        //setting nero as a card in hand
        int[] hand = {NERO, FORUM};
        player1.setHand(hand);
        
        //place nero on disc 2
        int[] args = {NERO, 2};

        //activating card, should return true
        assert(game.activateCard(1, args));

        //money should not change
        assert(player1.getMoney() == 10);
        
        //hand should no longer contain nero
        assert(isPermutation(player1.getHand(), hand) == false);
        
        //checking if nero has been laid and senator remains
        assert(player1.getFaceUpCards()[0] == SENATOR);
        assert(player1.getFaceUpCards()[1] == NERO);
        
        //setting a new hand with sicarius and haruspex in it
        hand = new int[3];
        hand[0] = FORUM;
        hand[1] = SICARIUS;
        hand[2] = HARUSPEX;
        player1.setHand(hand);
        
        //place sicarius on 3, haruspex on 1
        args = new int[4];
        args[0] = SICARIUS;
        args[1] = 3;
        args[2] = HARUSPEX;
        args[3] = 1;
        
        //activating card, should return true
        assert(game.activateCard(1, args));
        
        //money should not change
        assert(player1.getMoney() == 10);
        
        //hand should no longer contain sicarius or haruspex
        assert(isPermutation(player1.getHand(), hand) == false);
        
        //checking if sicarius and haruspex has been laid and architectus removed
        //and that forum remains
        assert(player1.getFaceUpCards()[0] == HARUSPEX);
        assert(player1.getFaceUpCards()[1] == NERO);
        assert(player1.getFaceUpCards()[2] == SICARIUS);
        
        //checking that senator was discarded
        int[] expectedPile = {SENATOR};
        assert(isPermutation(game.getDiscardPile(), expectedPile));
        
    }

    @Override
    public String toString() {
        return "GG's Senator Tests";
    }
    
}
