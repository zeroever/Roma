package TestCard;

import tests.main.CardCode;
import Logic.*;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.*;

public class TestSenator {
	
	public static void main(String[] args) {
	    final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
	    
        Game game = new Game();
        Player playerA = game.getCurrentPlayer();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        //Dice[] actionDice = game.getActionDice();
        field.layCardOnField(CURRENT_PLAYER, new Senator(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Forum(), 2, discardPile);
        field.layCardOnField(OPPONENT, new Centurio(), 1, discardPile);
        
        //playerA.getHand().putCardIn(new Turris());
        
        playerA.drawNCard(game.getDrawDeck(), 52);
        display.showField();
        //actionDice[0].setFace(1);
        playerA.showHand();
        System.out.println();
        Card card = field.getCard(CURRENT_PLAYER, 1);
        //game.activateCard(1);
        card.activate(game, new int[]{CardCode.CONSUL, 2,
        							  CardCode.VELITES, 7,
        							  CardCode.SCAENICUS, 1});
        
        System.out.println("After activating the card");
        display.showField();
        playerA.showHand();
        System.out.println("Number of discarded card = " + 
        		           game.getDiscardPile().getNumOfCards());
        
        assert(!card.activate(game, new int[]{CardCode.FORUM, 2}));
    }
	
}
