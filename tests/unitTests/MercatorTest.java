package tests.unitTests;

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
public class MercatorTest extends RomaTest{
	private static final int INDEX_PLAYER1_VP = 0;
	private static final int INDEX_PLAYER2_VP = 1;
	private static final int INDEX_PLAYER1_MONEY = 2;
	private static final int INDEX_PLAYER2_MONEY = 3;
	
	public boolean mercatorTester(RomaEngine game, TestablePlayer player1, TestablePlayer player2,
			           int victoryPointsPurchased, int[] variables, int[] expectedResult) {
        boolean activated = false;
		player1.setVictoryPoints(variables[INDEX_PLAYER1_VP]);
		player2.setVictoryPoints(variables[INDEX_PLAYER2_VP]);
		player1.setMoney(variables[INDEX_PLAYER1_MONEY]);
		player2.setMoney(variables[INDEX_PLAYER2_MONEY]);
		
		TestablePlayer player = game.getPlayer(game.getCurrentPlayer());
		assert(player.getFaceUpCards()[0] == MERCATOR);
		assert(game.getAvailableActionDice().length > 0);
		
		int[] args = {victoryPointsPurchased};
		activated = game.activateCard(1, args);
		assert(activated);
		assert(player1.getVictoryPoints() == expectedResult[0]):
			"actual "+player1.getVictoryPoints() + " Expected "+expectedResult[0];
		assert(player2.getVictoryPoints() == expectedResult[1]);
		assert(player1.getMoney() == expectedResult[2]);
		assert(player2.getMoney() == expectedResult[3]);
        return activated;
	}

    /* (non-Javadoc)
     * @see testRoma.RomaTest#run()
     */
    @Override
    public void run() {
        RomaEngine game = new Roma();
        TestablePlayer player1 = game.getPlayer(PLAYER1);
        TestablePlayer player2 = game.getPlayer(PLAYER2);
        int victoryPointsPurchased = 0;
        int[] variables = {10,10,5,6}; //in order of vp1, vp2 money1, money2
        int[] expectedResult = {10,10,5,6}; //in order of vp1, vp2 money1, money2
        int[] faceUpCards = {MERCATOR,-1,-1,-1,-1,-1, -1};
        int[] diceValues = {1,1,1};
        
        game.setAvailableActionDice(diceValues);
        
        game.setCurrentPlayer(PLAYER1);
        player1.setFaceUpCards(faceUpCards);

        //test 0 money used
        assert(mercatorTester(game, player1, player2,
        		victoryPointsPurchased, variables, expectedResult));

        variables[INDEX_PLAYER1_VP] = 15;
        variables[INDEX_PLAYER2_VP] = 8;
        variables[INDEX_PLAYER1_MONEY] = 10;
        variables[INDEX_PLAYER2_MONEY] = 200;
        victoryPointsPurchased = 5;
        expectedResult[INDEX_PLAYER1_VP] = 20;
        expectedResult[INDEX_PLAYER2_VP] = 3;
        expectedResult[INDEX_PLAYER1_MONEY] = 0;
        expectedResult[INDEX_PLAYER2_MONEY] = 210;

        //test 10 money used
        assert(mercatorTester(game, player1, player2,
        		victoryPointsPurchased, variables, expectedResult));

        /*
        victoryPointsPurchased = 9;
        
        //test odd amount of money used, nothing should happen
        assert(mercatorTester(game, player1, player2,
        	moneySpent, variables, variables) == false);
        */
        /*
        victoryPointsPurchased = 9002;
        
        //test more money victory points currently have, nothing should happen
        assert(mercatorTester(game, player1, player2,
        		victoryPointsPurchased, variables, variables) == false);
        */
    }

    /* (non-Javadoc)
     * @see testRoma.RomaTest#toString()
     */
    @Override
    public String toString() {
        return "Mercator tests";
    }
	
}
