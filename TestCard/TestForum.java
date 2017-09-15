package TestCard;

import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Basilica;
import playingCards.Essedum;
import playingCards.Forum;
import playingCards.Gladiator;
import playingCards.Senator;
import playingCards.Sicarius;
import playingCards.Templum;

public class TestForum {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Player player = game.getCurrentPlayer();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        Dice[] actionDice = game.getActionDice();
        DisplayInterface display = new TextDisplay(game);
        player.throwDice(actionDice);
        //actionDice[2].setUsed(true);
        
        field.layCardOnField(CURRENT_PLAYER, new Basilica(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Forum(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Templum(), 3, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        {
        System.out.println("Before activating the card");
        display.showField();
        System.out.println("Your victory point: " + player.getVictoryPoint());
        System.out.println("General stockpile VP: " + game.getVictoryPoint());
        
        System.out.println("You have 2 unused dice\n" +
                           "You use 1 die to activate forum");
        actionDice[0].setUsed(true);
        actionDice[1].setFace(6);
        actionDice[2].setFace(2);
        //activate
        field.getCard(CURRENT_PLAYER, 2).activate(game, new int[]{6, 2});
        
        //game.setDisplay(display);
        //actionDice[1].setFace(2);
        //game.activateCard(1);
        
        System.out.println();
        System.out.println("After activating the card");
        display.showField();
        System.out.println("Your victory point: " + player.getVictoryPoint());
        System.out.println("General stockpile VP: " + game.getVictoryPoint());
        }
        /*
        {
            System.out.println("Before activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
            
            System.out.println("You have 2 unused dice\n" +
                               "You use 1 die to activate forum");
            actionDice[0].setUsed(true);
            actionDice[1].setFace(6);
            actionDice[2].setFace(2);
            //activate
            field.getCard(CURRENT_PLAYER, 2).activate(game, new int[]{6});
            
            System.out.println();
            System.out.println("After activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
        }
        
        {
        	field.layCardOnField(CURRENT_PLAYER, new Forum(), 1, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Basilica(), 2, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Templum(), 3, discardPile);
            
            System.out.println("Before activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
            
            System.out.println("You have 2 unused dice\n" +
                               "You use 1 die to activate forum");
            actionDice[0].setUsed(true);
            actionDice[1].setFace(6);
            actionDice[2].setFace(2);
            //activate
            field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{2});
            
            System.out.println();
            System.out.println("After activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
        }
        
        {
        	field.layCardOnField(CURRENT_PLAYER, new Forum(), 6, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Basilica(), 4, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Templum(), 7, discardPile);
            
            System.out.println("Before activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
            
            System.out.println("You have 2 unused dice\n" +
                               "You use 1 die to activate forum");
            actionDice[0].setUsed(true);
            actionDice[1].setFace(6);
            actionDice[2].setFace(2);
            //activate
            field.getCard(CURRENT_PLAYER, 6).activate(game, new int[]{2, 6});
            
            System.out.println();
            System.out.println("After activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
        }
        
        {
        	field.layCardOnField(CURRENT_PLAYER, new Forum(), 7, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Basilica(), 6, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Templum(), 4, discardPile);
            
            System.out.println("Before activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
            
            System.out.println("You have 2 unused dice\n" +
                               "You use 1 die to activate forum");
            actionDice[0].setUsed(true);
            actionDice[1].setFace(6);
            actionDice[2].setFace(2);
            //activate
            field.getCard(CURRENT_PLAYER, 7).activate(game, new int[]{2});
            
            System.out.println();
            System.out.println("After activating the card");
            display.showField();
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("General stockpile VP: " + game.getVictoryPoint());
        }
        */
    }

}
