package TestCard;

import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Essedum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Machina;
import playingCards.Onager;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestOnager {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        Player currentPlayer = game.getCurrentPlayer();
        Dice battleDice = game.getBattleDice();
        DisplayInterface display = new TextDisplay(game);
        
        field.layCardOnField(CURRENT_PLAYER, new Haruspex(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Onager(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 5, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Onager(), 6, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Onager(), 2, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        field.layCardOnField(OPPONENT, new Machina(), 6, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        
        currentPlayer.throwDice(battleDice);
        //activate card
        field.getCard(CURRENT_PLAYER, 6).activate(game, new int[]{6});
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        currentPlayer.throwDice(battleDice);
        assert(!field.getCard(CURRENT_PLAYER, 6).activate(game, new int[]{3}));
    }

}
