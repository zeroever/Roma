package TestCard;

import playingCards.*;
import Logic.*;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import tests.main.*;

public class TestArchitectus {

	public static void main(String[] args) {
	    final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
	    
        Game game = new Game();
        Player playerA = game.getCurrentPlayer();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        
        field.layCardOnField(CURRENT_PLAYER, new Architectus(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Architectus(), 4, discardPile);
        field.layCardOnField(OPPONENT, new Forum(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Forum(), 2, discardPile);
        Card card = field.getCard(CURRENT_PLAYER, 1);
        display.showField();
        
        playerA.showHand();
        assert(!card.activate(game, new int[]{CardCode.AESCULAPINUM, 2,CardCode.BASILICA, 4}));
        
        playerA.drawNCard(game.getDrawDeck(), 52);
        display.showField();
        
        playerA.showHand();
        System.out.println();
        //Card card = field.getCard(CURRENT_PLAYER, 1);
        assert(card.activate(game, new int[]{CardCode.AESCULAPINUM, 2,CardCode.BASILICA, 4}));
        
        System.out.println("After activating the card");
        display.showField();
        playerA.showHand();
        System.out.println("Number of discarded card = " + 
        		           game.getDiscardPile().getNumOfCards());
        
        assert(!card.activate(game, new int[]{CardCode.CENTURIO, 7}));
    }
}
