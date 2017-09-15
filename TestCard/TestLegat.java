package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Field;
import Logic.Game;
import playingCards.Legat;
import playingCards.Legionarius;
import playingCards.Machina;
import playingCards.Mercator;
import playingCards.Sicarius;

public class TestLegat {

    public static void main(String[] args) {
        
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        //normal test
        {
            System.out.println("Test1");
            
            Game game = new Game();
            Field field = game.getField();
            Deck discardPile = game.getDiscardPile();
            field.layCardOnField(CURRENT_PLAYER, new Legat(), 1, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Legat(), 2, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 3, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
            
            field.layCardOnField(OPPONENT, new Legat(), 5, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 3, discardPile);
            
            //field.showField(game);
            //System.out.println("player VP = " + game.getCurrentPlayer().getVictoryPoint());
            assert(game.getCurrentPlayer().getVictoryPoint() == 10);
            assert(game.getVictoryPoint() == 16);
            
            //activate card on dice disc 1
            Card card = field.getCard(CURRENT_PLAYER, 1);
            card.activate(game, null);
            
            //game.getField().showField(game);
            //System.out.println("player VP = " + game.getCurrentPlayer().getVictoryPoint());
            assert(game.getCurrentPlayer().getVictoryPoint() == (10+4));
            assert(game.getVictoryPoint() == (16-4));
            System.out.println("Test1 Pass!");
        }
        
        //test corner case - when general stock pile VP is less than the opponent 
        //vacant dice disc
        {
            System.out.println("Test2");
           
            Game game = new Game();
            Field field = game.getField();
            Deck discardPile = game.getDiscardPile();
            
            game.modifyVictoryPoint(-14);
            
            field.layCardOnField(CURRENT_PLAYER, new Legat(), 1, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Legat(), 2, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 3, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
            
            field.layCardOnField(OPPONENT, new Legat(), 5, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 3, discardPile);
            
            //field.showField(game);
            //System.out.println("player VP = " + game.getCurrentPlayer().getVictoryPoint());
            assert(game.getCurrentPlayer().getVictoryPoint() == 10);
            assert(game.getVictoryPoint() == 2);
            //activate card on dice disc 1
            Card card = field.getCard(CURRENT_PLAYER, 1);
            card.activate(game, null);
            
            //game.getField().showField(game);
            //System.out.println("player VP = " + game.getCurrentPlayer().getVictoryPoint());
            assert(game.getCurrentPlayer().getVictoryPoint() == (10+2));
            assert(game.getVictoryPoint() == 0);
            System.out.println("Test2 Pass");
        }
        
        {
            System.out.println("Test3");
           
            Game game = new Game();
            Field field = game.getField();
            Deck discardPile = game.getDiscardPile();
            
            game.modifyVictoryPoint(-14);
            
            field.layCardOnField(CURRENT_PLAYER, new Legat(), 1, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Legat(), 2, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 3, discardPile);
            field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
            
            field.layCardOnField(OPPONENT, new Legat(), 1, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 2, discardPile);
            field.layCardOnField(OPPONENT, new Sicarius(), 3, discardPile);
            field.layCardOnField(OPPONENT, new Legat(), 4, discardPile);
            field.layCardOnField(OPPONENT, new Machina(), 5, discardPile);
            field.layCardOnField(OPPONENT, new Legionarius(), 6, discardPile);
            field.layCardOnField(OPPONENT, new Mercator(), 7, discardPile);
            //field.showField(game);
            //System.out.println("player VP = " + game.getCurrentPlayer().getVictoryPoint());
            assert(game.getCurrentPlayer().getVictoryPoint() == 10);
            assert(game.getVictoryPoint() == 2);
            //activate card on dice disc 1
            Card card = field.getCard(CURRENT_PLAYER, 1);
            card.activate(game, null);
            
            //game.getField().showField(game);
            //System.out.println("player VP = " + game.getCurrentPlayer().getVictoryPoint());
            assert(game.getCurrentPlayer().getVictoryPoint() == (10));
            assert(game.getVictoryPoint() == 2);
            System.out.println("Test3 Pass");
        }
    }
}
