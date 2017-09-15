package tests.userProgram;

import tests.main.CardCode;
import tests.main.TestablePlayer;
import Logic.*;

public class TestPlayer implements TestablePlayer {

	private Player player;
	private Field field;
	private Deck discardPile;
	
	public TestPlayer(Game game, int playerNumber) {
		player = game.getPlayer(playerNumber-1);
		field = game.getField();
		discardPile = game.getDiscardPile();
	}
	
	public int[] getFaceUpCards() {
		int[] faceUpCard = new int[Field.SIZE];
		Card card;
		for(int i = 0; i < Field.SIZE; i++) {
			card = field.getCard(player.getId(), i+1);
			if (card == null) {
				faceUpCard[i] = CardCode.NO_CARD;
			} else {
				faceUpCard[i] = card.getIndex();
			}
		}
		return faceUpCard;
	}

	public int getVictoryPoints() {
		return player.getVictoryPoint();
	}

	public void setFaceUpCards(int[] faceUpCards) {
		Card card = null;
		for(int i = 0; i < faceUpCards.length; i++) {
			if (faceUpCards[i] == CardCode.NO_CARD) {
				card = null;
			} else {
				card = CardCollection.getCard(faceUpCards[i]);
			}
			field.layCardOnField(player.getId(), card, i+1, discardPile);
		}

	}
	
	public int[] getHand () {
		Deck playerHand = this.player.getHand();
		int numCards = playerHand.getNumOfCards();
		int[] hand = new int[numCards];
		for(int i = 0; i < numCards; i++) {
			hand[i] = playerHand.getCard(i+1).getIndex();
		}
		return hand;
	}
	
	public void setHand(int[] hand) {
		Deck playerHand = player.getHand();
		//clear hand
		int numCards = playerHand.getNumOfCards();
		for(int i = 0; i < numCards; i++) {
			playerHand.draw(); //pick the first card out
		}
		for(int i = 0; i < hand.length; i++) {
			playerHand.putCardIn(CardCollection.getCard(hand[i]));
		}
	}

	public void setMoney(int money) {
		player.modifyMoney(-player.getMoney()+money);
	}

	public void setVictoryPoints(int victoryPoints) {
		player.modifyVictoryPoint(-player.getVictoryPoint()+victoryPoints);
	}

	public int getMoney() {
		return player.getMoney();
	}

}
