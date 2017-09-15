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
public class GGTestAesculapinum extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {AESCULAPINUM,-1,-1,-1,-1,-1, -1};
        int[] diceValues = {1,1,1};
        int[] args = {NERO}; //picks nero in the discard pile
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        //set nero and forum as only cards in discard pile
        int[] discardPile = {NERO, FORUM};        
        game.setDiscardPile(discardPile);

        //activate aesculapinum, putting the first
        //card nero into the players hand, should return true
        assert(game.activateCard(1, args));
        
        //check that nero is no longer in discard pile, should return false
        assert(isPermutation(game.getDiscardPile(), discardPile) == false);
        
        //check that nero is in the player's hand now
        int[] resultHand = {NERO};
        assert(isPermutation(player1.getHand(), resultHand));
        
        //set a new discard pile with 2 forums and a sicarius
        discardPile = new int[3];
        discardPile[0] = FORUM;
        discardPile[1] = SICARIUS;
        discardPile[2] = FORUM;
        game.setDiscardPile(discardPile);
        
        //now picks sicarius in discard pile
        args[0] = SICARIUS;

        //activating, should return true
        assert(game.activateCard(1, args));

        //check that sicarius is no longer in discard pile, should return false
        assert(isPermutation(game.getDiscardPile(), discardPile) == false);
        
        //check that nero and sicarius is in the player's hand now
        resultHand = new int[2];
        resultHand[0] = NERO;
        resultHand[1] = SICARIUS;
        assert(isPermutation(player1.getHand(), resultHand));
        
        //now picks forum
//        args[0] = FORUM;
        
        //activating aesculapinum on forum, should return false
//        assert(game.activateCard(1, args) == false);
        
        //hand should still be the same
//        assert(isPermutation(player1.getHand(), resultHand));
        
        
    }

    @Override
    public String toString() {
        return "GG's Aesculapinum Tests";
    }
    
}
