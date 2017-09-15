package tests.userProgram;

import static tests.main.CardCode.*;
import tests.main.*;
import Logic.*;
import TextUI.DisplayInterface;
import TextUI.TextDisplay;

import java.util.*;


public class Roma implements RomaEngine {

	
	private Game game;
	
	public Roma() {
	    game = new Game();
	    DisplayInterface display = new TextDisplay(game);
	    game.setIO(display, null);
	}
	
	public boolean activateCard (int diceDisc, int[] args) {
		Player currentPlayer = game.getCurrentPlayer();
		Field field = game.getField();
		Dice[] actionDice = game.getActionDice(); 
		boolean success = false;
		int dieNumber = Dice.NO_DIE;
		if(diceDisc == DiscCode.BRIBE_DISC) {
			dieNumber = findDie(actionDice, args[0]);
			int[] parameter = new int[args.length-1];
			for(int i = 1; i < args.length; i++) {
				parameter[i-1] = args[i];
			}
		    args = parameter;
		    
		} else {
			dieNumber = findDie(actionDice, diceDisc);
		}
		
		if( field.getCard(currentPlayer.getId(), diceDisc).getIndex() == SCAENICUS) {
		    //OUR description of args for scaenicus
		    //args[0] contains the dice disc of the card to copy the action of
		    int cardToCopy = args[0];
		    
		    for(int i = 1; i <= Field.SIZE; i++) {
		    	Card card = field.getCard(currentPlayer.getId(), i);
		        if(card != null && card.getIndex() == cardToCopy) {
		            args[0] = i;
		        }
		    }
		}
		
		success = currentPlayer.activateCard(game, diceDisc, dieNumber, args);
		return success;
	}

	public void endCurrentTurn() {
		game.switchTurn();
		game.deduceVictoryPoint();

	}

	public int[] getAvailableActionDice() {
		Dice[] dice = game.getActionDice();
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		for(int i = 0; i < dice.length; i++) {
			if(!dice[i].isUsed()) {
				integers.add(dice[i].getFace());
			}
		}
		
		int[] actionDice = new int[integers.size()];
	    for (int i=0; i < actionDice.length; i++)
	    {	
	    	actionDice[i] = integers.get(i).intValue();
	    }
	    return actionDice;

	}

	public int getCost(int card) {
		return CardCollection.getCard(card).getCost();
	}

	public int getCurrentPlayer() {
		return game.getCurrentPlayer().getId()+1;
	}

	public int[] getDiscardPile() {
		return deckToInt(game.getDiscardPile());
	}

	public int[] getDrawPile() {
		return deckToInt(game.getDrawDeck());
	}

	public TestablePlayer getPlayer(int playerNumber) {
		return new TestPlayer(game, playerNumber);
	}

	public boolean isGameOver() {
		return game.isEnd();
	}

	public void layCard(int card, int diceDisc) {
		Field field = game.getField();
		Card layCard = null;
		Player player = game.getCurrentPlayer();
		Deck playerHand = player.getHand();
		Deck discardPile = game.getDiscardPile();
		boolean found = false;
		
		//find the card inside hand
		int cardPosition = 0;
		while( cardPosition < playerHand.getNumOfCards() && !found) {
			layCard = playerHand.getCard(cardPosition+1);
			if(layCard.getIndex() == card) {
				found = true;
			} else {
				cardPosition++;
			}
		}
		
		player.layCard(field, discardPile, cardPosition+1, diceDisc);
	}

	public void rigBattleDie(int value) {
		game.getBattleDice().setFace(value);

	}

	public void setAvailableActionDice(int[] actionDice) {
		Dice[] dice = game.getActionDice();
		
		int i; 
		for(i = 0; i < actionDice.length; i++) {
			dice[i].setFace(actionDice[i]);
			dice[i].setUsed(false);
		}
		
		//set the rest to be used
		for(; i < 3; i++) {
			dice[i].setUsed(true);
		}

	}

	public void setCurrentPlayer(int playerNumber) {
		if (playerNumber != game.getCurrentPlayer().getId()+1) {
			game.switchTurn();
		}
	}

	public void setDiscardPile(int[] cards) {
		Deck discardPile = game.getDiscardPile();
		intToDeck(discardPile, cards);
	}

	public void setDrawPile(int[] cards) {
		Deck drawDeck = game.getDrawDeck();
		intToDeck(drawDeck, cards);
	}
	
	public void useActionDieForCards(int diceValue, CardPicker cardPicker) {
		int[] cards = null;
		int cardCode = cardPicker.pickCard(cards);
		
		Deck playerHand = game.getCurrentPlayer().getHand();
		Deck discardPile = game.getDiscardPile();
		Deck drawDeck = game.getDrawDeck();
		Deck drawCards = new Deck();
		
		boolean found = false;
		for(int i = 0; i < diceValue; i++) {
			Card card = drawDeck.draw();
			drawCards.putCardIn(card);
			if(!found && card.getIndex() == cardCode) {
				found = true;
				//put the card in hand
				playerHand.putCardIn(drawCards.pickCard(i+1));
			}
		}
		
		assert(found): "Invalid cardPicker";
		
		//discard the rest
		while( drawCards.getNumOfCards() != 0) {
			discardPile.putCardIn(drawCards.draw());
		}
		
		setUsed(diceValue);
	}

	public void useActionDieForMoney(int diceValue) {
		Dice[] actionDice = game.getActionDice();
		int dieNumber = findDie(actionDice, diceValue);
		if(dieNumber != Dice.NO_DIE) {
		    game.takeMoney(dieNumber);
			//game.getCurrentPlayer().takeMoney(actionDice, dieNumber);
		}
		//game.getCurrentPlayer().modifyMoney(diceValue);
		//setUsed(diceValue);
	}
	
	private int findDie(Dice[] actionDice, int diceValue) {
		int i = 0;
		boolean found = false;
		int dieNumber = Dice.NO_DIE;
		while ( i< actionDice.length && !found) {
			if (!actionDice[i].isUsed() && actionDice[i].getFace() == diceValue) {
				found = true;
				dieNumber = i;
			}
			i++;
		}
		return dieNumber;
	}

	private void setUsed(int diceValue) {
		Dice[] actionDice = game.getActionDice();
		
		int i = 0;
		boolean found = false;
		while ( i< actionDice.length && !found) {
			
			if (!actionDice[i].isUsed() && actionDice[i].getFace() == diceValue) {
				actionDice[i].setUsed(true);
				found = true;
			}
			i++;
		}
	}
	/*
	private int getCard(int index, Deck deck){
		int card = CardCode.NO_CARD;
		for(int i = 1; i <= deck.getNumOfCards(); i++) {
            if(deck.getCard(i).getIndex() == index) {
            	card = i;
            }
        }
		return card;
	}
	*/
	private int[] deckToInt(Deck deck) {
	    int numCards = deck.getNumOfCards();
		int[] cards = new int[numCards];
		for (int i =0; i < numCards; i++) {
			cards[i] = deck.getCard(i+1).getIndex();
		}
		return cards;
	}
	
	private void intToDeck(Deck deck, int[] cards) {
	    int numCards = deck.getNumOfCards();
		for(int i = 0; i < numCards; i++) {
			deck.draw();
		}
		Card card = null;
		for(int i = 0; i < cards.length; i++) {
			card = CardCollection.getCard(cards[i]);
			deck.putCardIn(card);
		}
	}

	public int getVictoryPointsStockpile() {
		return game.getVictoryPoint();
	}
}
