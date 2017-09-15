package TestCard;

import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Aesculapinum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Legat;
import playingCards.Machina;
import playingCards.Onager;
import static tests.main.CardCode.*;

public class TestAesculapinum {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        Player player = game.getCurrentPlayer();
        DisplayInterface display = new TextDisplay(game);
        discardPile.putCardIn(new Haruspex());
        discardPile.putCardIn(new Machina());
        discardPile.putCardIn(new Onager());
        discardPile.putCardIn(new Gladiator());
        discardPile.putCardIn(new Legat());
        
        
        
        field.layCardOnField(CURRENT_PLAYER, new Aesculapinum(), 1, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        System.out.println("Your hand: ");
        display.listCard(player.getHand());
        System.out.println("Card in discard pile = " + discardPile.getNumOfCards());
        
        //activate card
        assert(!field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{ONAGER}));
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        System.out.println("Your hand: ");
        display.listCard(player.getHand());
        System.out.println("Card in discard pile = " + discardPile.getNumOfCards());
        display.listCard(discardPile);
        
        assert(field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{HARUSPEX}));
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        System.out.println("Your hand: ");
        display.listCard(player.getHand());
        System.out.println("Card in discard pile = " + discardPile.getNumOfCards());
        display.listCard(discardPile);
        
        assert(!field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{HARUSPEX}));
    }

}
