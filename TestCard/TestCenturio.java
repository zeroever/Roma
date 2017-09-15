package TestCard;

import Logic.*;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.*;

public class TestCenturio {
	
	 public static void main(String[] args) {
	     final int CURRENT_PLAYER = 0;
	     final int OPPONENT = 1;   
	     
	     Game game = new Game();
	     Field field = game.getField();
	     Deck discardPile = game.getDiscardPile();
	     Player player = game.getCurrentPlayer();
	     Dice battleDice = game.getBattleDice();
	     Dice[] actionDice = game.getActionDice();
	     DisplayInterface display = new TextDisplay(game);
	     game.getPlayer(CURRENT_PLAYER).throwDice(game.getActionDice());
        
	     field.layCardOnField(CURRENT_PLAYER, new Centurio(), 1, discardPile);
	     field.layCardOnField(OPPONENT, new Senator(), 1, discardPile);
	     field.layCardOnField(OPPONENT, new Senator(), 6, discardPile);
	     field.layCardOnField(CURRENT_PLAYER, new Centurio(), 6, discardPile);
	     display.showField();
         
	     //activate card on dice disc 1
	     Card card = field.getCard(CURRENT_PLAYER, 1);
	     
	     player.throwDice(battleDice);
	     card.activate(game, new int []{0});
	     
	     System.out.println("After activating the card");
	     display.showField();
	     
	     card = field.getCard(CURRENT_PLAYER, 6);
	     player.throwDice(battleDice);
	     actionDice[2].setFace(6);
	     card.activate(game, new int []{6});
	     
	     System.out.println("After activating the card");
	     display.showField();
	     //System.out.println(battleDice.getFace());
	     
	 }
}
