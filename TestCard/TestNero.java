package TestCard;

import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Essedum;
import playingCards.Gladiator;
import playingCards.Legionarius;
import playingCards.Machina;
import playingCards.Nero;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestNero {

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
        
        field.layCardOnField(CURRENT_PLAYER, new Nero(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Nero(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 5, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Legionarius(), 6, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 2, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Machina(), 6, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        
        //activate card
        assert(!field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{7}));
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        System.out.println("discard num = " + game.getDiscardPile().getNumOfCards());
        display.listCard(game.getDiscardPile());
        
        field.layCardOnField(CURRENT_PLAYER, new Nero(), 1, discardPile);
        assert(!field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{4}));
        
        field.layCardOnField(CURRENT_PLAYER, new Nero(), 1, discardPile);
        assert(!field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{3}));
        
    }

}
