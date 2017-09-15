package TestCard;

import tests.main.CardCode;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.*;

public class TestMachina {

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
        
        field.layCardOnField(CURRENT_PLAYER, new Onager(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Consiliarius(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Turris(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Machina(), 5, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Forum(), 6, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        
        //activate card
        field.getCard(CURRENT_PLAYER, 5).activate(game, new int[]{CardCode.TURRIS, 1,
        														  CardCode.MACHINA, 2,
        														  CardCode.FORUM, 6,
        														  CardCode.ONAGER, 5});
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();

    }

}
