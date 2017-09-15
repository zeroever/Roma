package TestCard;

import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Essedum;
import playingCards.Forum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Nero;
import playingCards.Senator;
import playingCards.Sicarius;
import playingCards.Turris;

public class TestTurris {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        field.layCardOnField(CURRENT_PLAYER, new Haruspex(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Nero(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Turris(), 5, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        
        //activate Gladiator
        Turris turris = (Turris) field.getCard(CURRENT_PLAYER, 5);
        turris.activate(CURRENT_PLAYER,1, field);
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        
        turris.deactivate(CURRENT_PLAYER, field);
        System.out.println();
        System.out.println("After deactivating the card");
        display.showField();
        
        field.layCardOnField(CURRENT_PLAYER, new Turris(), 6, discardPile);
        display.showField();
        field.layCardOnField(CURRENT_PLAYER, new Forum(), 6, discardPile);
        display.showField();
        field.layCardOnField(CURRENT_PLAYER, new Forum(), 5, discardPile);
        display.showField();
	}

}
