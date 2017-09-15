package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Sicarius;
import playingCards.Velites;

public class TestVelites {
	
	 public static void main(String[] args) {
	        Game game = new Game();
	        Field field = game.getField();
	        Player currentPlayer = game.getCurrentPlayer();
	        Dice battleDice = game.getBattleDice();
	        Deck discardPile = game.getDiscardPile();
	        
	        DisplayInterface display = new TextDisplay(game);
	        field.layCardOnField(0, new Velites(), 1, discardPile);
	        field.layCardOnField(1, new Sicarius(), 2, discardPile);
	        
	        display.showField();
	        currentPlayer.throwDice(battleDice);
	        //activate card on dice disc 1
	        Card card = field.getCard(0, 1);
	        card.activate(game, new int[]{2});
	        
	        System.out.println("After activating the card");
	        display.showField();
	    }
}