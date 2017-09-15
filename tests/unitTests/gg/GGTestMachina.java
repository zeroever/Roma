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
public class GGTestMachina extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {-1,-1,-1,MACHINA,-1,-1, -1};
        int[] diceValues = {4,2,1};        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        //move machina to disc 2
        int[] args = new int[8];
        args[4] = 2;
        
        //test machina moving itself to disc 2
        assert(game.activateCard(4, args));
        
        //check that it is on disc 2 and not on 4 anymore
        assert(player1.getFaceUpCards()[1] == MACHINA);
        assert(player1.getFaceUpCards()[3] == NO_CARD);
        
        //change the face up cards
        faceUpCards[0] = NO_CARD;
        faceUpCards[1] = MACHINA;
        faceUpCards[2] = NO_CARD;
        faceUpCards[3] = TURRIS;
        faceUpCards[4] = NO_CARD;
        faceUpCards[5] = NO_CARD;
        faceUpCards[6] = NO_CARD;
        player1.setFaceUpCards(faceUpCards);
        
        //put machina in 1 and turris in 2
        args[2] = 1;
        args[4] = 2;
        
        //activate again, should return true
        assert(game.activateCard(2, args));
        
        //check that cards are in proper spots
        assert(player1.getFaceUpCards()[0] == MACHINA);
        assert(player1.getFaceUpCards()[1] == TURRIS);
        assert(player1.getFaceUpCards()[3] == NO_CARD);

        //now swap the two cards around
        args[1] = 2;
        args[2] = 1;
        args[4] = 0;
        
        //activate again, should reutrn true
        assert(game.activateCard(1, args));

        //check that the cards swapped
        assert(player1.getFaceUpCards()[0] == TURRIS);
        assert(player1.getFaceUpCards()[1] == MACHINA);
}

    @Override
    public String toString() {
        return "GG's Machina Tests";
    }
}