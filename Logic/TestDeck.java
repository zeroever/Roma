package Logic;


public class TestDeck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deck drawDeck = new Deck();
		Deck hand = new Deck();
		Deck discardPile = new Deck();
		
		drawDeck.initializeDrawDeck();
		drawDeck.shuffle();
		
		System.out.println("Test Deck");
		
		assert( !drawDeck.isEmpty() );
        assert( hand.isEmpty() );
        assert( discardPile.isEmpty() );
		assert( drawDeck.getNumOfCards() == 52);
		assert( hand.getNumOfCards() == 0);
		assert( discardPile.getNumOfCards() == 0);
		
		System.out.println("Draw 1 card from drawDeck to hand");
		
		hand.putCardIn(drawDeck.draw());
		assert( !drawDeck.isEmpty() );
        assert( !hand.isEmpty() );
        assert( discardPile.isEmpty() );
		assert( drawDeck.getNumOfCards() == 51);
        assert( hand.getNumOfCards() == 1);
        assert( discardPile.getNumOfCards() == 0);
        
        System.out.println("Draw 3 cards from drawDeck to hand");
        
        for(int i = 0; i < 3; i++) {
            hand.putCardIn(drawDeck.draw());
        }
        assert( !drawDeck.isEmpty() );
        assert( !hand.isEmpty() );
        assert( discardPile.isEmpty() );
        assert( drawDeck.getNumOfCards() == 48);
        assert( hand.getNumOfCards() == 4);
        assert( discardPile.getNumOfCards() == 0);
        
        System.out.println("Put 1 card from hand to discard pile");
        
        discardPile.putCardIn(hand.pickCard(2));
        assert( !drawDeck.isEmpty() );
        assert( !hand.isEmpty() );
        assert( !discardPile.isEmpty() );
        assert( drawDeck.getNumOfCards() == 48);
        assert( hand.getNumOfCards() == 3);
        assert( discardPile.getNumOfCards() == 1);
		
        System.out.println("Put 3 card from hand to discard pile");
        
        for(int i = 0; i < 3; i++) {
            discardPile.putCardIn(hand.draw());
        }
        assert( !drawDeck.isEmpty() );
        assert( hand.isEmpty() );
        assert( !discardPile.isEmpty() );
        assert( drawDeck.getNumOfCards() == 48);
        assert( hand.getNumOfCards() == 0);
        assert( discardPile.getNumOfCards() == 4);
        
        
        System.out.println("Draw all cards in the draw deck to hand");
        
        for(int i = 0; !drawDeck.isEmpty(); i++) {
            hand.putCardIn(drawDeck.draw());
        }
        assert( drawDeck.isEmpty() );
        assert( !hand.isEmpty() );
        assert( !discardPile.isEmpty() );
        assert( drawDeck.getNumOfCards() == 0);
        assert( hand.getNumOfCards() == 48);        
        assert( discardPile.getNumOfCards() == 4);
        
        System.out.println("Put 40 cards from hand to discard pile");
        
        for(int i = 0; i < 40; i++) {
            discardPile.putCardIn(hand.draw());
        }
        assert( drawDeck.isEmpty() );
        assert( !hand.isEmpty() );
        assert( !discardPile.isEmpty() );
        assert( drawDeck.getNumOfCards() == 0);
        assert( hand.getNumOfCards() == 8);        
        assert( discardPile.getNumOfCards() == 44);
        
        System.out.println("All tests passed.");
        System.out.println("You're awesome!");
		
	}

}
