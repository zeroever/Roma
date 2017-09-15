package TestCard;

import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import playingCards.Essedum;
import playingCards.Gladiator;
import playingCards.Mercator;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestMercator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        
        currentPlayer.modifyMoney(30);
        //opponent.modifyVictoryPoint(-9);
        
        field.layCardOnField(CURRENT_PLAYER, new Mercator(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        
        System.out.println("Before activating the card");
        System.out.println("You have " + currentPlayer.getVictoryPoint() + " victory points");
        System.out.println("You have " + currentPlayer.getMoney() + " sestertii");
        System.out.println("Your oppponent has " + opponent.getVictoryPoint() + " victory points");
        System.out.println("Your oppponent has " + opponent.getMoney() + " sestertii");
        
        //activate card
        field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{3});
        
        System.out.println("After activating the card");
        System.out.println("You have " + currentPlayer.getVictoryPoint() + " victory points");
        System.out.println("You have " + currentPlayer.getMoney() + " sestertii");
        System.out.println("Your oppponent has " + opponent.getVictoryPoint() + " victory points");
        System.out.println("Your oppponent has " + opponent.getMoney() + " sestertii");
        
        field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{3});
        
        System.out.println("After activating the card");
        System.out.println("You have " + currentPlayer.getVictoryPoint() + " victory points");
        System.out.println("You have " + currentPlayer.getMoney() + " sestertii");
        System.out.println("Your oppponent has " + opponent.getVictoryPoint() + " victory points");
        System.out.println("Your oppponent has " + opponent.getMoney() + " sestertii");
        
        field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{3});
        
        System.out.println("After activating the card");
        System.out.println("You have " + currentPlayer.getVictoryPoint() + " victory points");
        System.out.println("You have " + currentPlayer.getMoney() + " sestertii");
        System.out.println("Your oppponent has " + opponent.getVictoryPoint() + " victory points");
        System.out.println("Your oppponent has " + opponent.getMoney() + " sestertii");
        
        field.getCard(CURRENT_PLAYER, 1).activate(game, new int[]{2});
        
        System.out.println("After activating the card");
        System.out.println("You have " + currentPlayer.getVictoryPoint() + " victory points");
        System.out.println("You have " + currentPlayer.getMoney() + " sestertii");
        System.out.println("Your oppponent has " + opponent.getVictoryPoint() + " victory points");
        System.out.println("Your oppponent has " + opponent.getMoney() + " sestertii");
        
    }

}
