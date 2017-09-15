package TestCard;

import tests.main.CardCode;
import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.DisplayInterface;
import TextUI.TextDisplay;
import playingCards.Haruspex;

public class TestHaruspex {
	
	public static void main(String[] args) {
        Game game = new Game();
        DisplayInterface display = new TextDisplay(game);
        Player playerA = game.getCurrentPlayer();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        Deck deck = game.getDrawDeck();
        field.layCardOnField(0, new Haruspex(), 1, discardPile);
        for (int i = 0; i < 52; i++){
        	discardPile.putCardIn(deck.draw());
        }
        display.listCard(deck);
        playerA.showHand();
        
        System.out.println();
        Card card = field.getCard(0, 1);
        card.activate(game, new int[]{CardCode.CENTURIO});
        
        System.out.println("After activating the card");
        playerA.showHand();
    }
}
