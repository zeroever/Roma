package TestCard;

import tests.main.CardCode;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Consiliarius;
import playingCards.Essedum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestConsiliarius {

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
        field.layCardOnField(CURRENT_PLAYER, new Consiliarius(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 5, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        
        
        field.getCard(CURRENT_PLAYER, 2).activate(game, new int[]{CardCode.HARUSPEX, 3, CardCode.GLADIATOR, 7,
        														  CardCode.SICARIUS, 6, CardCode.CONSILIARIUS, 5});
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
    }

}
