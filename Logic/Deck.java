package Logic;
import playingCards.*;

/**
 * Deck card - represents a group of cards 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */
public class Deck {

	private final int DECK_SIZE = 52;
	private Card[] cards;
	private int numOfCards;
	
	/**
	 * Create new empty deck
	 */
	public Deck() {
		numOfCards = 0;
		cards = new Card[DECK_SIZE];
	}
	
	/**
	 * Create a deck with some maximum size
	 * @param startingCards - maximum size of a deck
	 */
	public Deck(int deckSize) {
        numOfCards = 0;
        cards = new Card[deckSize]; 
    }
	
	/**
	 * Put all 52 cards into a deck
	 */
	public void initializeDrawDeck () {
		numOfCards = DECK_SIZE;
		cards[0] = new Sicarius();
		cards[1] = new Architectus();
		cards[2] = new Architectus();
		cards[3] = new Consiliarius();
		cards[4] = new Consiliarius();
		cards[5] = new Legat();
		cards[6] = new Legat();
		cards[7] = new Gladiator();
		cards[8] = new Gladiator();
		cards[9] = new Mercator();
		cards[10] = new Consul();
		cards[11] = new Consul();
		cards[12] = new Legionarius();
		cards[13] = new Legionarius();
		cards[14] = new Legionarius();
		cards[15] = new Nero();
		cards[16] = new Praetorianus();
		cards[17] = new Praetorianus();
		cards[18] = new Scaenicus();
		cards[19] = new Scaenicus();
		cards[20] = new Haruspex();
		cards[21] = new Haruspex();
		cards[22] = new Senator();
		cards[23] = new Senator();
		cards[24] = new Velites();
		cards[25] = new Velites();
		cards[26] = new Essedum();
		cards[27] = new Essedum();
		cards[28] = new TribunusPlebis();
		cards[29] = new TribunusPlebis();
		cards[30] = new Centurio();
		cards[31] = new Centurio();
		cards[32] = new Aesculapinum();
		cards[33] = new Aesculapinum();
		cards[34] = new Basilica();
		cards[35] = new Basilica();
		cards[36] = new Machina();
		cards[37] = new Machina();
		cards[38] = new Forum();
		cards[39] = new Forum();
		cards[40] = new Forum();
		cards[41] = new Forum();
		cards[42] = new Forum();
		cards[43] = new Forum();
		cards[44] = new Mercatus();
		cards[45] = new Mercatus();
		cards[46] = new Onager();
		cards[47] = new Onager();
		cards[48] = new Templum();
		cards[49] = new Templum();
		cards[50] = new Turris();
		cards[51] = new Turris();
	}
	
	/**
	 * shuffle the deck
	 */
	public void shuffle(){
		int random = 0;
		Card temp;
		
		for (int i=0; i<numOfCards; i++) {
			random = (int) (numOfCards * Math.random());
			temp = cards[i];
			cards[i] = cards[random];
			cards[random] = temp;
		}

	}
	
	/**
	 * Draw the top card from deck.
	 * @return pointer to the card draw
	 */
	public Card draw() {
		return cards[--numOfCards];
	}
	
	/**
	 * pick a card from the deck of player choice
	 * @param choice - number greater than or equal to 1
	 * @return pointer to the card
	 */
	public Card pickCard(int choice) {
		Card returnCard = this.cards[choice-1];
		this.cards[choice-1] = null;
		updateHand();
		numOfCards--;
		return returnCard;
	}
	
	/**
	 * Update the hand. Search if there is an empty slot or not
	 */
	public void updateHand() {
		int emptySlot = DECK_SIZE;
		int i = 0;
		//find the position of null
		while (i < numOfCards && emptySlot == DECK_SIZE){
			if (this.cards[i] == null){
				emptySlot = i;
			}
			i++;
		}
		//move the rest one slot forward
		update(emptySlot);
	}

	/**
	 * Move other cards to fill the empty slot
	 * @param emptySlot
	 */
	private void update(int emptySlot) {
		int i;
		for (i = emptySlot; i < numOfCards-1; i++){
			cards[i] = cards[i+1]; 
		}
	}
	
	/**
	 * put a card in the deck
	 * @param card
	 */
	public void putCardIn(Card card) {
		cards[numOfCards] = card; //put new card in the deck
		numOfCards++; //increase the card count
	}
	
	/**
	 * Check if the deck is empty or not
	 * @return true if the deck is empty, otherwise false
	 */
	public boolean isEmpty() {
		return (numOfCards == 0);
	}
	
	/**
	 * access a card without removing it from deck
	 * @param choice - start with 1
	 * @return card
	 */
	public Card getCard(int choice) {
		return this.cards[choice-1];
	}
	
	/**
	 * Get how many cards are in the deck
	 * @return number of cards contained in the deck
	 */
	public int getNumOfCards() {
		return numOfCards;
	}
}
