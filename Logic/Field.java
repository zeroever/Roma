package Logic;
import java.util.*;

import playingCards.Turris;

/**
 * Card field
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */
public class Field {
	
	public static final int SIZE = 7;
	public static final int BRIBE = 7;
	private Card[][] cardOnField;
	private ArrayList<Integer>[] blockDisc = new ArrayList[2];

	public Field() {
		cardOnField = new Card[Player.TOTAL_PLAYER][SIZE];
		blockDisc[0] = new ArrayList<Integer>();
		blockDisc[1] = new ArrayList<Integer>();
	}
	
	public void resetField() {
	    cardOnField = new Card[Player.TOTAL_PLAYER][SIZE];
    }
	
	/**
	 * Lay card on field
	 * @param id - player id range between 0 and 1
	 * @param card - card to be laid
	 * @param diceDisc - dice disc number range between 1 and 7
	 * @param discardPile
	 */
	public void layCardOnField(int id, Card card, int diceDisc, Deck discardPile) {
			
		//check if the slot is empty or not
		if(cardOnField[id][diceDisc-1] != null) {
			//move the old card to discardPile
			removeCard(id, diceDisc, discardPile);
		} 
		cardOnField[id][diceDisc-1] = card;
		checkTurris(id);
	}

	/**
	 * Check if there is a turris on the field or not.
	 * If there are at least one turris on the field then activate the turris
	 * @param id - player id. It indicates which player field will be check
	 */
	private void checkTurris(int id) {
		Turris turris;
		int numOfTurris;
		if ((numOfTurris = countTurris(id)) > 0){
			turris = (Turris) getTurris(id);
			turris.activate(id, numOfTurris, this);
		}
	}
	
	/**
	 * Count how many turris is on the field
	 * @param id - player id. It indicates which player field will be check
	 * @return number of turris on player field
	 */
	private int countTurris(int id){
		int numOfTurris = 0;
		for(int i = 1; i <= SIZE; i++) {
            if(getCard(id, i) != null && 
               getCard(id, i).getName() == "Turris") {
            	numOfTurris++;
            }
        }
		return numOfTurris;
	}
	
	/**
	 * Getting a turris from the field
	 * @param id - player id. It indicates which player field will be check
	 * @return turris card
	 */
	private Turris getTurris(int id){
		Turris turris = null;
		for(int i = 1; i <= SIZE; i++) {
            if(getCard(id, i) != null &&
               getCard(id, i).getName() == "Turris") {
            	turris = (Turris) getCard(id, i);
            }
        }
		return turris;
	}
	
	/**
	 * Get the field of player
	 * @param player
	 * @return deck class represents the card on player field
	 */
	public Deck getPlayerField(Player player) {
	    int id = player.getId();
	    Deck playerField = new Deck();
	    for(int i = 0; i < Field.SIZE; i++) {
	        playerField.putCardIn(cardOnField[id][i]);
	    }
	    return playerField;
	}
	
	/**
	 * Count player empty dice disc
	 * @param id - - player id. It indicates which player field will be check
	 * @return number of empty dice disc
	 */
	public int getEmptyDisc(int id) {
		int emptyDisc = 0;
		
		for(int i = 0; i < this.cardOnField[id].length; i++) {
			if(this.cardOnField[id][i] == null) {
				emptyDisc++;
			}
		}
		
		return emptyDisc;
	}

	/**
	 * Get pointer to a card
	 * @param playerId - range between 0 and 1
	 * @param choice - range between 1 to 7
	 * @return pointer to a card
	 */
	public Card getCard(int playerId, int choice) {
		return this.cardOnField[playerId][choice-1];
	}
	
	/**
	 * Check if the dice disc of the player empty or not
	 * @param id - player id. It indicates which player field will be check
	 * @param diceDisc - dice dice that will be check
	 * @return true if the dice disc is empty, otherwise return false
	 */
	public boolean isEmpty(int id, int diceDisc){
		boolean empty = false;
		if(this.cardOnField[id][diceDisc-1] == null){
			empty = true;
		}
		
		return empty;
	}

	/**
	 * remove the card from field and put it in the discardPile
	 * @param id
	 * @param choice - disc dice number
	 * @param discardPile - any deck that you want to put the removed card into
	 */
	public void removeCard(int id, int choice, Deck discardPile) {
	    Card discardCard = cardOnField[id][choice-1];
	    cardOnField[id][choice-1] = null;
	    if(discardCard.getName() == "Turris") {
	    	Turris turris = (Turris) discardCard;
	    	turris.deactivate(id, this);
	    }
		discardCard.setAdditionalDefense(0);
		discardPile.putCardIn(discardCard);

	}
	
	/**
	 * reset the defense value of every card on field
	 * @param id - player id. It indicates which player field will be check
	 */
	public void resetDefense(int id) {
		Card card;
		for(int i = 1; i <= SIZE; i++) {
			card = getCard(id, i);
            if(card != null) {
            	card.setAdditionalDefense(0);
            }
        }
		checkTurris(id);
	}
	
	/**
	 * Set the dice disc to blocked 
	 * @param id - player id. It indicates which player field will be check
	 * @param blockDisc - dice disc that will be blocked
	 */
	public void setBlock(int id, int blockDisc) {
		this.blockDisc[id].add(blockDisc);
	}
	
	/**
	 * Reset the block dice disc
	 * @param id - player id. It indicates which player field will be check
	 */
	public void resetBlock(int id) {
		this.blockDisc[id].clear();
	}

	/**
	 * Check if the dice disc is blocked or not
	 * @param id - player id. It indicates which player field will be check
	 * @param diceDisc - dice disc that will be checked
	 * @return true if the dice disc is blocked otherwise return false
	 */
	public boolean isBlock(int id, int diceDisc) {
		return this.blockDisc[id].contains(diceDisc);
	}
	
	/**
	 * Set the card to faced up
	 * It is called after preparing phase
	 */
	public void revealCard() {
		for (int i = 1; i <= SIZE; i++){
			if(!isEmpty(0,i) && !getCard(0, i).isFaceUp()){
				getCard(0,i).setFaceUp(true);
			}
			if(!isEmpty(1,i) && !getCard(1, i).isFaceUp()){
				getCard(1,i).setFaceUp(true);
			}
		}
	}
}
