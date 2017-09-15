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
public class GGTestConsiliarius extends RomaTest{

    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        int[] faceUpCards = {-1,-1,-1,CONSILIARIUS,-1,-1, -1};
        int[] diceValues = {4,2,1};        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);
        
        //move consiliarius to disc 2
        int[] args = new int[8];
        args[4] = 2;
        
        //test consiliarius moving himself to disc 2
        assert(game.activateCard(4, args));
        
        //check that he is on disc 2 and not on 4 anymore
        assert(player1.getFaceUpCards()[1] == CONSILIARIUS);
        assert(player1.getFaceUpCards()[3] == NO_CARD);
        
        //change the face up cards
        faceUpCards[0] = NO_CARD;
        faceUpCards[1] = CONSILIARIUS;
        faceUpCards[2] = NO_CARD;
        faceUpCards[3] = SENATOR;
        faceUpCards[4] = NO_CARD;
        faceUpCards[5] = NO_CARD;
        faceUpCards[6] = NO_CARD;
        player1.setFaceUpCards(faceUpCards);
        
        //put consiliarius in 1 and senator in 2
        args[2] = 1;
        args[4] = 2;
        
        //activate again, should return true
        assert(game.activateCard(2, args));
        
        //check that cards are in proper spots
        assert(player1.getFaceUpCards()[0] == CONSILIARIUS);
        assert(player1.getFaceUpCards()[1] == SENATOR);
        assert(player1.getFaceUpCards()[3] == NO_CARD);

        //now swap the two cards around
        args[1] = 2;
        args[2] = 1;
        args[4] = 0;
        
        //activate again, should reutrn true
        assert(game.activateCard(1, args));

        //check that the cards swapped
        assert(player1.getFaceUpCards()[0] == SENATOR);
        assert(player1.getFaceUpCards()[1] == CONSILIARIUS);
}

    @Override
    public String toString() {
        return "GG's Consiliarius Tests";
    }
}