package TestCard;


import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.*;

public class TestPraetorianus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        Player player = game.getCurrentPlayer();
        //Dice[] actionDice = game.getActionDice();
        DisplayInterface display = new TextDisplay(game);
        //field.setBlock(0, 2);
        
        field.layCardOnField(CURRENT_PLAYER, new Haruspex(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Consiliarius(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Praetorianus(), 6, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        
        System.out.println("Before activating the card");
        display.showField();
        
        //player.throwDice(actionDice);
        
        //activate card
        //field.getCard(CURRENT_PLAYER, 6).activate(game);
        game.getActionDice()[0].setFace(6);
        //game.activateCard(6);
        //field.getCard(CURRENT_PLAYER, 6).activate(game, new int[]{6});
        //player.activateCard(game, field, actionDice);
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        game.switchTurn();
        player = game.getCurrentPlayer();
        assert(!player.activateCard(game, 6, 6, new int[]{1}));
	}

}
