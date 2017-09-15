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
public class GGTestArchitectus extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {ARCHITECTUS,-1,-1,-1,-1,-1,-1};
        int[] diceValues = {1,1,1};
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        player1.setMoney(10); //to check that cards are laid for free
        
        //setting forum as only card in hand
        int[] hand = {NERO, FORUM};
        player1.setHand(hand);
        
        //place forum on disc 2
        int[] args = {FORUM, 2};

        //activating card, should return true
        assert(game.activateCard(1, args));

        //money should not change
        assert(player1.getMoney() == 10);
        
        //hand should no longer contain forum
        assert(isPermutation(player1.getHand(), hand) == false);
        
        //checking if forum has been laid and architectus remains
        assert(player1.getFaceUpCards()[0] == ARCHITECTUS);
        assert(player1.getFaceUpCards()[1] == FORUM);
        
        //setting a new hand with templum and basilica in it
        hand = new int[3];
        hand[0] = NERO;
        hand[1] = TEMPLUM;
        hand[2] = BASILICA;
        player1.setHand(hand);
        
        //place templum on 3, basilica on 1
        args = new int[4];
        args[0] = TEMPLUM;
        args[1] = 3;
        args[2] = BASILICA;
        args[3] = 1;
        
        //activating card, should return true
        assert(game.activateCard(1, args));
        
        //money should not change
        assert(player1.getMoney() == 10);
        
        //hand should no longer contain templum or basilica
        assert(isPermutation(player1.getHand(), hand) == false);
        
        //checking if templum and basilica has been laid and architectus removed
        //and that forum remains
        assert(player1.getFaceUpCards()[0] == BASILICA);
        assert(player1.getFaceUpCards()[1] == FORUM);
        assert(player1.getFaceUpCards()[2] == TEMPLUM);
        
        //checking that architectus was discarded
        int[] expectedPile = {ARCHITECTUS};
        assert(isPermutation(game.getDiscardPile(), expectedPile));
        
    }

    @Override
    public String toString() {
        return "GG's Architectus Tests";
    }
    
}
