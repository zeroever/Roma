package tests.main;

public interface TestablePlayer {
   int getVictoryPoints ();
   void setVictoryPoints (int victoryPoints);
   int getMoney ();
   void setMoney (int money);

   // returns the cards in the hand. order doesn't matter (can NOT contain no_card)
   int[] getHand ();

   // order of cards in hand doesn't matter
   void setHand (int[] hand);

   // returns an array of size 7 as defined in DiscCode.
   // If no card exists next to a dice disc
   // then the value at that position = CardCode.NO_CARD
   int[] getFaceUpCards ();

   // takes an array of size 7 as defined in DiscCode.
   // If no card should exist next to a dice disc
   // then the value at that position = CardCode.NO_CARD
   void setFaceUpCards (int[] faceUpCards);
}
