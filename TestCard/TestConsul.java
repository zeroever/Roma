package TestCard;

import Logic.Card;
import Logic.Deck;
import Logic.Dice;
import Logic.Field;
import Logic.Game;
import Logic.Player;
import playingCards.Consul;
import playingCards.Legat;
import playingCards.Sicarius;

public class TestConsul {

    public static void main(String[] args) {
        final int CURRENT_PLAYER = 0;
        final int OPPONENT = 1;
        
        Game game = new Game();
        Dice[] actionDice = game.getActionDice();
        Field field = game.getField();
        Deck discardPile = game.getDiscardPile();
        Player currentPlayer = game.getCurrentPlayer();
        
        field.layCardOnField(CURRENT_PLAYER, new Consul(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Legat(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Consul(), 4, discardPile);
        
        field.layCardOnField(OPPONENT, new Sicarius(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 6, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 4, discardPile);
        
        currentPlayer.throwDice(actionDice);
        
        {
            System.out.println("=== No action dice used ===");
            
            System.out.println("All Action Dice faces are : ");
            for(int i = 0; i < actionDice.length; i++) {
                if(!actionDice[i].isUsed() ) {
                    System.out.print(actionDice[i].getFace() + " ");
                }
            }
            System.out.println();
            
            //activate card on dice disc 1
            actionDice[1].setFace(1);
            Card card = field.getCard(CURRENT_PLAYER, 1);
            card.activate(game, new int[]{1,0});
            //assert(actionDice[1].getFace() == 1);
            
            System.out.println("All Action Dice faces are : ");
            for(int i = 0; i < actionDice.length; i++) {
                if(!actionDice[i].isUsed() ) {
                    System.out.print(actionDice[i].getFace() + " ");
                }
            }
            System.out.println();
        } 
        
        {
            System.out.println("=== One action dice used ===");
            actionDice[0].setUsed(true);
            
            System.out.println("All Action Dice faces are : ");
            for(int i = 0; i < actionDice.length; i++) {
                if(!actionDice[i].isUsed() ) {
                    System.out.print(actionDice[i].getFace() + " ");
                }
            }
            System.out.println();
            actionDice[2].setFace(3);
            System.out.print(actionDice[2].getFace() + " ");
            //activate card on dice disc 1
            Card card = field.getCard(CURRENT_PLAYER, 1);
            card.activate(game, new int[]{3, 1});
            System.out.println("All Action Dice faces are : ");
            for(int i = 0; i < actionDice.length; i++) {
                if(!actionDice[i].isUsed() ) {
                    System.out.print(actionDice[i].getFace() + " ");
                }
            }
            System.out.println();
        }
        
        
        actionDice[1].setFace(6);
        //System.out.print(actionDice[1].getFace() + " ");
        //activate card on dice disc 1
        Card card = field.getCard(CURRENT_PLAYER, 1);
        card.activate(game, new int[]{6, 1});
        System.out.println("All Action Dice faces are : ");
        for(int i = 0; i < actionDice.length; i++) {
            if(!actionDice[i].isUsed() ) {
                System.out.print(actionDice[i].getFace() + " ");
            }
        }
        
        actionDice[0].setFace(1);
        //System.out.print(actionDice[0].getFace() + " ");
        //activate card on dice disc 1
        card.activate(game, new int[]{0, 0});
        System.out.println("All Action Dice faces are : ");
        for(int i = 0; i < actionDice.length; i++) {
            if(!actionDice[i].isUsed() ) {
                System.out.print(actionDice[i].getFace() + " ");
            }
        }
        
        actionDice[0].setFace(1);
        actionDice[1].setUsed(true);
        actionDice[2].setUsed(true);
        //System.out.print(actionDice[0].getFace() + " ");
        //activate card on dice disc 1
        card.activate(game, new int[]{0, 1});
        System.out.println("All Action Dice faces are : ");
        for(int i = 0; i < actionDice.length; i++) {
            if(!actionDice[i].isUsed() ) {
                System.out.print(actionDice[i].getFace() + " ");
            }
        }
    }
}
