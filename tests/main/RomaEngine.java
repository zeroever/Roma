package tests.main;


public interface RomaEngine {
   /**
   must return the values of the action dice that the current player
   can use RIGHT NOW.  this means if the current player has already
   used one of the action dice, it does not appear here.
   order does not matter.
   array.length must match the number of available dice
   */
   int[] getAvailableActionDice ();
   void setAvailableActionDice (int[] actionDice);

   // The next time the battle die is rolled, it must show this value up.
   void rigBattleDie (int value);

   // playerNumber is as defined in PlayerCode
   TestablePlayer getPlayer (int playerNumber);
   int getCurrentPlayer ();

   // when this is called, no victory points are deducted.  The game
   // state does not change, and we are warped into phase 3 of the
   // specified players turn.  the rolling of action dice and deducting
   // of victory points does not occur.
   void setCurrentPlayer (int playerNumber);

   // diceValue must be between 1 to 6 inclusive.
   void useActionDieForMoney (int diceValue);

   // diceValue must be between 1 to 6 inclusive.
   // The drawPile must have at least one card in it.
   void useActionDieForCards (int diceValue, CardPicker cardPicker);

   // cards[0] is the bottom card (i.e. the last card that comes out) for these four functions.
   // Order matters! (can NOT contain no_card)
   // (eg getDrawPile.length equals the the number of cards in the pile) 
   void setDrawPile(int[] cards);
   int[] getDrawPile();
   void setDiscardPile(int[] cards); 
   int[] getDiscardPile();

   // lays a card from the current player's hand onto dice disc. If there
   // is a card already on the dice disc, then that card is added to the
   // discard pile. If there is no such card in the current player's hand,
   // nothing happens. If the current player doesn't have enough money to
   // play that card, nothing happens.
   // (the card placed goes onto one of the players face up cards slots)
   void layCard(int card, int diceDisc);

   /**
   if there is no card on diceDisc OR if there is no such action dice
   with the specified value OR if the card on diceDisc cannot be
   legally played at this point in time, then this function does
   nothing.
   @return indicate if the activation was successful 
   see https://wiki.cse/cs2911cgi/10s1/RomaAcceptanceTests/Cards

   @param diceDisc is specified by DiscCode.
   
   If the activation is successful then:
   - for numbered dice disks the action die correspondingto the card's position is used. 
   - If using the bribe disc, args[0] specifies the die to use followed by
    the arguments for the other card
    (the value of the dice indicates the money to pay.)
   */
   boolean activateCard (int diceDisc, int[] args);

   //card as defined in CardCode
   int getCost (int card);

   // returns the number of victory points in the stock pile.
   // In a situation where more victory points are taken from the stockpile
   //  then it has, feel free to return any value <= 0. gameOver will assume
   //  that: assert(getVictoryPointsStockpile() <= 0)
   // The players must obviously have the correct amount of VP however.
   int getVictoryPointsStockpile();

   //Ends the current players turn, switches player and inacts phases 1 and 2.
   void endCurrentTurn ();

   boolean isGameOver ();
}
