package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Sicarius;
import playingCards.TribunusPlebis;

public class TestTribunusPlebis {
	
	public static void main(String[] args) {
	    final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        System.out.println("You have " + game.getCurrentPlayer().getVictoryPoint() +
		   				   " victory points");
        System.out.println("Opponent has " + game.getOpponent().getVictoryPoint() +
		   				   " victory points");
        System.out.println();
        
        field.layCardOnField(CURRENT_PLAYER, new TribunusPlebis(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 1, discardPile);
        
        display.showField();
        
        //activate card on dice disc 1
        Card card = field.getCard(CURRENT_PLAYER, 1);
        card.activate(game, null);
        
        System.out.println("After activating the card");
        System.out.println("You have " + game.getCurrentPlayer().getVictoryPoint() +
		   				   " victory points");
        System.out.println("Opponent has " + game.getOpponent().getVictoryPoint() +
		                   " victory points");

    }
}
