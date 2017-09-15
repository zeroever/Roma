package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Consul;
import playingCards.Essedum;
import playingCards.Gladiator;
import playingCards.Machina;
import playingCards.Nero;
import playingCards.Sicarius;
import playingCards.Velites;

public class TestSicarius {

    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Consul(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Nero(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 4, discardPile);
        
        field.layCardOnField(OPPONENT, new Sicarius(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Machina(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Essedum(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Velites(), 4, discardPile);
        
        System.out.println("discard num = " + discardPile.getNumOfCards());
        display.showField();
        
        //activate card on dice disc 1
        Card card = field.getCard(CURRENT_PLAYER, 1);
        card.activate(game, new int[]{4});
        
        display.showField();
        System.out.println("discard num = " + game.getDiscardPile().getNumOfCards());
        display.listCard(game.getDiscardPile());
    }
}
