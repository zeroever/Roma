package TestCard;

import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Essedum;
import playingCards.Forum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestGladiator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Player opponent = game.getOpponent();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        
        field.layCardOnField(CURRENT_PLAYER, new Haruspex(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        field.layCardOnField(OPPONENT, new Forum(), 7, discardPile);
        System.out.println("Before activating the card");
        display.showField();
        System.out.println("Opponent's hand");
        opponent.showHand();
        
        //activate Gladiator
        field.getCard(CURRENT_PLAYER, 3).activate(game, new int[]{5});
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        System.out.println("Opponent's hand");
        opponent.showHand();
        
        {
        	System.out.println("Before activating the card");
            display.showField();
            System.out.println("Opponent's hand");
            opponent.showHand();

            //activate Gladiator
            field.getCard(CURRENT_PLAYER, 3).activate(game, new int[]{7});
            
            System.out.println();
            System.out.println("After activating the card");
            display.showField();
            System.out.println("Opponent's hand");
            opponent.showHand();
        }
    }

}
