package tests.unitTests.gg;
import tests.main.RomaEngine;
import tests.main.RomaTest;
import tests.main.TestablePlayer;
import tests.userProgram.Roma;
import static tests.main.CardCode.*;
import static tests.main.PlayerCode.*;


/**
 * @author Saki Tang, Pieter Jontera Lius
 * This is pretty much the same as GGTestMercator except scaenicus is
 * being used here
 */
public class GGTestScaenicus extends RomaTest{


    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int[] args = {2,0};
        int[] variables = {10,10,5,6}; //in order of vp1, vp2 money1, money2
        int[] expectedResult = {10,10,5,6}; //in order of vp1, vp2 money1, money2
        int[] faceUpCards = {SCAENICUS,MERCATOR,-1,-1,-1,-1,-1};
        int[] diceValues = {1,1,1};
        
        game.setCurrentPlayer(PLAYER1);
        game.setAvailableActionDice(diceValues);
        player1.setFaceUpCards(faceUpCards);

        //test 0 money used
        assert(mercatorTester(game, player1, player2,
                     args, variables, expectedResult));

        variables[0] = 15;
        variables[1] = 8;
        variables[2] = 10;
        variables[3] = 200;
        args[1] = 10;
        expectedResult[0] = 20;
        expectedResult[1] = 3;
        expectedResult[2] = 0;
        expectedResult[3] = 210;

        //test 10 money used
        assert(mercatorTester(game, player1, player2,
                     args, variables, expectedResult));

        args[1] = 9;
        
        //test odd amount of money used, nothing should happen
        assert(mercatorTester(game, player1, player2,
                         args, variables, variables) == false);

        args[1] = 9002;
        
        //test more money than currently have, nothing should happen
        assert(mercatorTester(game, player1, player2,
                           args, variables, variables) == false);
        
    }

    /* (non-Javadoc)
     * @see testRoma.RomaTest#toString()
     */
    @Override
    public String toString() {
        return "GG's Scaenicus Tests";
    }

    public boolean mercatorTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2,
               int[] moneySpent, int[] variables, int[] expectedResult) {
        boolean activated = false;
        
        player1.setVictoryPoints(variables[0]);
        player2.setVictoryPoints(variables[1]);
        player1.setMoney(variables[2]);
        player2.setMoney(variables[3]);
        activated = game.activateCard(1, moneySpent);
        assert(player1.getVictoryPoints() == expectedResult[0]);
        assert(player2.getVictoryPoints() == expectedResult[1]);
        assert(player1.getMoney() == expectedResult[2]);
        assert(player2.getMoney() == expectedResult[3]);
        return activated;
    }
}
