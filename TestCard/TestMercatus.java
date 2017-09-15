package TestCard;

import Logic.Deck;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import playingCards.Forum;
import playingCards.Mercatus;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestMercatus {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        //normal case
        {
            Game game = new Game();
            Player player = game.getCurrentPlayer();
            Player opponent = game.getOpponent();
            Field field = game.getField();
            Deck discardPile = game.getDiscardPile();
            
            field.layCardOnField(CURRENT_PLAYER, new Mercatus(), 1, discardPile);
            
            field.layCardOnField(OPPONENT, new Forum(), 1, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 2, discardPile);
            field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
            field.layCardOnField(OPPONENT, new Forum(), 4, discardPile);
            field.layCardOnField(OPPONENT, new Forum(), 5, discardPile);
            
            System.out.println("Before activating the card");
            //field.showField(game);
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("Opponent victory point: " + opponent.getVictoryPoint());
            assert(player.getVictoryPoint() == 10);
            assert(opponent.getVictoryPoint() == 10);
            
            //activate card
            field.getCard(CURRENT_PLAYER, 1).activate(game, null);
            
            System.out.println();
            System.out.println("After activating the card");
            //field.showField(game);
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("Opponent victory point: " + opponent.getVictoryPoint());
            assert(player.getVictoryPoint() == (10+3));
            assert(opponent.getVictoryPoint() == (10));
            assert(game.getVictoryPoint() == 16-3);
        }
        
        //opponent has vp less than what we should get
        {
            Game game = new Game();
            Player player = game.getCurrentPlayer();
            Player opponent = game.getOpponent();
            Field field = game.getField();
            Deck discardPile = game.getDiscardPile();
            
            //opponent.modifyVictoryPoint(-9);
            game.modifyVictoryPoint(-15);
            
            field.layCardOnField(CURRENT_PLAYER, new Mercatus(), 1, discardPile);
            
            field.layCardOnField(OPPONENT, new Forum(), 1, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 2, discardPile);
            field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
            field.layCardOnField(OPPONENT, new Forum(), 4, discardPile);
            field.layCardOnField(OPPONENT, new Forum(), 5, discardPile);
            
            System.out.println("Before activating the card");
            //field.showField(game);
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("Opponent victory point: " + opponent.getVictoryPoint());
            assert(player.getVictoryPoint() == 10);
            assert(opponent.getVictoryPoint() == 10);
            assert(game.getVictoryPoint() == 1);
            System.out.println(game.getVictoryPoint());
            //activate card
            field.getCard(CURRENT_PLAYER, 1).activate(game, null);
            
            System.out.println();
            System.out.println("After activating the card");
            //field.showField(game);
            
            System.out.println("Your victory point: " + player.getVictoryPoint());
            System.out.println("Opponent victory point: " + opponent.getVictoryPoint());
            assert(player.getVictoryPoint() == (10+3));
            assert(opponent.getVictoryPoint() == (10));
            assert(game.getVictoryPoint() == 0):game.getVictoryPoint();
        }

    }

}
