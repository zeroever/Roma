package Logic;

import TextUI.TextDisplay;
import TextUI.DisplayInterface;
import playingCards.Essedum;
import playingCards.Gladiator;
import playingCards.Haruspex;
import playingCards.Legionarius;
import playingCards.Machina;
import playingCards.Senator;
import playingCards.Sicarius;

public class TestPlayer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println ("Testing Player");
		
		System.out.println ("Testing throwDice");
		testThrowDice();
		System.out.println ("throwDice passed");
		
		System.out.println ("Testing takeMoney");
		testTakeMoney();
		System.out.println ("takeMoney passed");
		
		System.out.println ("Testing layCard");
		testLayCard();
		System.out.println ("layCard passed");
		
		System.out.println ("Testing lay4Card");
		testLay4Card();
		System.out.println ("lay4Card passed");
		
		System.out.println ("Testing drawCard");
		testDrawCard();
		System.out.println ("drawCard passed");
		
		System.out.println("Testing drawNCard");
		testDrawNCard();
		System.out.println("drawNCard passed");
		
		testActivate();
		
		System.out.println ("All tests passed - you are Awesome!");
	}

	private static void testDrawNCard() {
		
		Deck deck = new Deck();
		deck.initializeDrawDeck();
		Player player = new Player("Me", 0);
		
		player.drawNCard(deck, 4);
		assert(player.getHand().getNumOfCards() == 4);
		player.showHand();
		
		System.out.println();
	}

	private static void testThrowDice() {
		// TODO Auto-generated method stub
		Dice[] actionDice = new Dice[3];
		for(int i = 0; i < actionDice.length; i++) {
			actionDice[i] = new Dice();
		}
		
		Player player = new Player("I", 1);
		
		player.throwDice(actionDice);
		
	}

	private static void testLay4Card() {
		// TODO Auto-generated method stub
		Field field = new Field();
		Game game = new Game();
		Deck discardPile = new Deck();
		Deck deck = new Deck();
		DisplayInterface display = new TextDisplay(game);
		deck.initializeDrawDeck();
		Player player = new Player("I", 1);
		
		player.drawNCard(deck, 4);
		player.layCard(field, discardPile, 1, 2);
		
		display.showField();
	}

	private static void testLayCard() {
		// TODO Auto-generated method stub
		Player player = new Player("I", 1);
		Field field = new Field();
		Game game = new Game();
		Deck deck = new Deck();
		DisplayInterface display = new TextDisplay(game);
		deck.initializeDrawDeck();
		Deck discardPile = new Deck();
		Dice[] actionDice = new Dice[3];
		for(int i = 0; i < actionDice.length; i++) {
			actionDice[i] = new Dice();
		}
		player.throwDice(actionDice);
		
		player.drawNCard(deck, 5);
		//player.takeMoney(actionDice);
		player.layCard(field, discardPile, 1, 2);
		
		display.showField();
	}

	private static void testTakeMoney() {
		// TODO Auto-generated method stub
		Player player = new Player("We", 1);
		Dice[] actionDice = new Dice[3];
		for(int i = 0; i < actionDice.length; i++) {
			actionDice[i] = new Dice();
		}
		player.throwDice(actionDice);
		player.takeMoney(actionDice, 2);
		
		System.out.println("you have "+player.getMoney());
	}

	private static void testDrawCard() {
		// TODO Auto-generated method stub
		
		Game game = new Game();
		
		Deck drawDeck = game.getDrawDeck();
		Deck discardPile = game.getDiscardPile();
	
		
		drawDeck.initializeDrawDeck();
		//drawDeck.shuffle();
		for (int i=0; i < 52; i++){
			discardPile.putCardIn(drawDeck.draw());
		}
		System.out.println("*****before");
		System.out.println("show discard pile");
		System.out.println(game.getDiscardPile().getNumOfCards());
		//game.getDiscardPile().listCard();
		System.out.println("show draw deck");
		System.out.println(game.getDrawDeck().getNumOfCards());
		//drawDeck.listCard();
		Player playerA = new Player("A", 0);
		Dice[] actionDice = new Dice[3];
		System.out.println("Test player A");
		
		for(int i = 0; i < actionDice.length; i++) {
			actionDice[i] = new Dice();
		}
		
		playerA.throwDice(actionDice);
		
		//show the dice to player
		for(int i = 0; i < actionDice.length; i++) {
			System.out.println(actionDice[i].getFace());
		}
		
		playerA.drawCard(0, drawDeck, discardPile, actionDice);
		
		
		System.out.println("****after");
		System.out.println("show hand");
		playerA.showHand();
		
		System.out.println("show discard pile");
		System.out.println(game.getDiscardPile().getNumOfCards());
		//game.getDiscardPile().listCard();
		System.out.println("show draw deck");
		System.out.println(game.getDrawDeck().getNumOfCards());
		//game.getDrawDeck().listCard();
	}
	
	private static void testActivate() {
		// TODO Auto-generated method stub
		final int CURRENT_PLAYER = 0;
		final int OPPONENT = 1;
		Game game = new Game();
		
		Player player = game.getCurrentPlayer();
		Dice[] actionDice = game.getActionDice();
		Deck discardPile = game.getDiscardPile();
		Field field = game.getField();
		DisplayInterface display = new TextDisplay(game);
		player.throwDice(actionDice);
		
		field.layCardOnField(CURRENT_PLAYER, new Haruspex(), 1, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Legionarius(), 2, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Gladiator(), 3, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Sicarius(), 4, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Essedum(), 5, discardPile);
        field.layCardOnField(CURRENT_PLAYER, new Legionarius(), 6, discardPile);
        
        field.layCardOnField(OPPONENT, new Essedum(), 1, discardPile);
        field.layCardOnField(OPPONENT, new Sicarius(), 2, discardPile);
        field.layCardOnField(OPPONENT, new Senator(), 3, discardPile);
        field.layCardOnField(OPPONENT, new Gladiator(), 4, discardPile);
        field.layCardOnField(OPPONENT, new Essedum(), 5, discardPile);
        field.layCardOnField(OPPONENT, new Machina(), 6, discardPile);
        
        display.showField();
        actionDice[0].setUsed(true);
        actionDice[1].setUsed(true);
        actionDice[2].setUsed(true);
        
        player.activateCard(game, 1, 0, new int[]{1});
	}
}
