package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.*;

public class TestEssedum {

	public static void main(String[] args) {
	    final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Centurio(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Turris(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Turris(), 5, discardPile);
        display.showField();
        System.out.println("Opponent has " + field.getCard(OPPONENT, 1).getDefense() +
			               " defense.");
        System.out.println();
        //activate card on dice disc 1
        Card card2 = field.getCard(CURRENT_PLAYER, 1);
        card2.activate(game, null);
        
        System.out.println("After activating the card");
        System.out.println("Opponent has " + field.getCard(OPPONENT, 1).getDefense() +
        		     	   " defense.");
        game.switchTurn();
        System.out.println("Opponent has " + field.getCard(OPPONENT, 1).getDefense() +
  	   " defense.");
    }
}
