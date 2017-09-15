package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.*;

public class TestScaenicus {
	
	public static void main(String[] args) {
        Game game = new Game();
        Field field = game.getField();
        Dice[] actionDice = game.getActionDice();
        Deck discardPile = game.getDiscardPile();
        DisplayInterface display = new TextDisplay(game);
        
        field.layCardOnField(0, new Scaenicus(), 1, discardPile);
        field.layCardOnField(0, new Nero(), 2, discardPile);
        field.layCardOnField(0, new Legionarius(), 3, discardPile);
        field.layCardOnField(0, new Centurio(), 4, discardPile);
        field.layCardOnField(0, new Sicarius(), 5, discardPile);
        field.layCardOnField(0, new Consul(), 6, discardPile);
        
        field.layCardOnField(1, new Legionarius(), 3, discardPile);
        field.layCardOnField(1, new Nero(), 2, discardPile);
        field.layCardOnField(1, new Consul(), 1, discardPile);
        field.layCardOnField(1, new Centurio(), 4, discardPile);
        field.layCardOnField(1, new Forum(), 5, discardPile);
        
        display.showField();
        Card card = field.getCard(0, 1);
        System.out.println("Copy Legionarius");
        card.activate(game, new int[]{3});
        display.showField();
        actionDice[0].setFace(5);
        card.activate(game, new int[]{6, 0, 1});
        assert(actionDice[0].getFace() == 6);
        System.out.println("Copy Nero");
        card.activate(game, new int[]{2, 2});
        System.out.println("Copy Nero");
        card.activate(game, new int[]{2, 5});
        display.showField();
	}
	
}
