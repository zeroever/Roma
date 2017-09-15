package tests.acceptanceTests;

import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;

import tests.main.*;
import tests.userProgram.Roma;


//**************************************
/* Written by Thu09Bell                *
 *   Contact: skil749@cse.unsw.edu.au  *
 *   if there are any problems.        *
 * Thanks!                             *
 ***************************************/

// UDD == Unoccupied Dice Disk

public class Thu09BellAcceptanceTest extends RomaTest {
	private TestablePlayer[] players;
	private Roma roma;

	@Override
	public String toString() {
		return "Thu09BellAcceptanceTest";
		//return this.getClass().getName();
	}
	
	public static void main(String[] args) {
		Thu09BellAcceptanceTest test = new Thu09BellAcceptanceTest();
		test.run();
	}
	
	public Thu09BellAcceptanceTest() {
		roma = new Roma();
		players = new TestablePlayer[2];
		for (int i = 0; i < players.length; i++) {
			players[i] = roma.getPlayer(i+PLAYER1);
		}
	}
	
	public void run() {
		initialState();
		stateZero();
		stateOne();
		stateTwo();
		stateThree();
		stateFour();
		stateFive();
		assert(!roma.isGameOver()); // Just for fun.
		stateSix();
		stateSeven();
		stateEight();
		stateNine();
		assert(!roma.isGameOver());
		stateTen();
		stateEleven();
		stateTwelve();
		stateThirteen();
		stateFourteen();
		stateFifteen();
		assert(!roma.isGameOver());
		stateSixteen();
		stateSeventeen();
		stateEighteen();
		gameOver();
		System.out.println("Passed!");
	}
	
	void initialState() {
		for (int i = 0; i < players.length; i++) {
			assert(players[i].getVictoryPoints() == 10);
			assert(players[i].getMoney() == 0);
			assert(players[i].getHand().length == 0) : players[i].getHand().length;
			
			int[] faceUpCards = players[i].getFaceUpCards();

			assert(faceUpCards.length == 7);
			for (int j = 0; j < faceUpCards.length; j++) {
				assert(faceUpCards[j] == NO_CARD);
			}
		}
		assert(roma.getDrawPile().length == 52);
		assert(roma.getDiscardPile().length == 0);
		roma.setCurrentPlayer(INITIAL_PLAYER);
		assert(roma.getCurrentPlayer() == INITIAL_PLAYER);
	}
	
	void stateZero() {
	   rigTheDeck(20); //initial deck size
	  
	   
		roma.setCurrentPlayer(INITIAL_PLAYER);
		
		int faceUpCards[] = {TRIBUNUS_PLEBIS, ARCHITECTUS, NO_CARD,
				AESCULAPINUM, LEGAT, NO_CARD,
				MACHINA // NOT USED. Just avoids victory point deductions.
		};
		roma.getPlayer(roma.getCurrentPlayer()).setFaceUpCards(faceUpCards);
		
		roma.setCurrentPlayer(PLAYER2);
		assert(roma.getCurrentPlayer() == PLAYER2);
		
		
		int secondFaceUpCards[] = {HARUSPEX, NO_CARD, FORUM,
				BASILICA, NO_CARD, CONSILIARIUS,
				MACHINA // NOT USED.
		};
		roma.getPlayer(roma.getCurrentPlayer()).setFaceUpCards(secondFaceUpCards);
		
		// GAME START
		assert(roma.getCurrentPlayer() == PLAYER2);
		roma.endCurrentTurn();	//This affects state one, the game has officially started here.
		assert(roma.getCurrentPlayer() == PLAYER1);
		
		assert(roma.getPlayer(PLAYER1).getVictoryPoints() == 8);
		assert(players[0].getVictoryPoints() == 8) : players[0].getVictoryPoints();
	}

   private void rigTheDeck(int sizeOfDeck) {
      //Rigging the deck
	   int[] initialDeck = {
	         FORUM, // s16 haruspex cards
	         TURRIS, // padding
            CONSUL, // --
            GLADIATOR, // + s15
            SICARIUS, // s13 haruspex
            NERO, // s9 haruspex
            MERCATUS, // --
            CENTURIO,
            SENATOR,
            HARUSPEX, // + s7
            TEMPLUM, // s6 haruspex
            TRIBUNUS_PLEBIS, // --
            VELITES,
            FORUM,
            FORUM,
            ARCHITECTUS,
            GLADIATOR, // + s5
            MERCATOR, // s4 Haruspex
            ESSEDUM, // -- s3
            GLADIATOR, // -- s1
	   };
	   
	   
	   int[] deck = new int[sizeOfDeck];
	   for (int i = 0; i < sizeOfDeck; i++) {
	      deck[i] = initialDeck[i];
	   }
	   
	   
	   roma.setDrawPile(deck);
	   
	   System.out.println("Checking the cardPile is in the correct order and of length " + deck.length);
	   assert(roma.getDrawPile().length == deck.length);
	   for (int cardIndex = deck.length - 1; cardIndex > 0; cardIndex--) {
	      assert(roma.getDrawPile()[cardIndex] == deck[cardIndex]) : "Cards in cardDeck not in correct order.";
	   }
   }
	
	void stateOne() {		
		assert (roma.getCurrentPlayer() == PLAYER1);
		assert(players[0].getVictoryPoints() == 8);
		
		int money = players[0].getMoney();
		int cardsInPile = roma.getDrawPile().length;
		int discardedPile = roma.getDiscardPile().length;
		assert(discardedPile == 0);
		
		int[] dice = {6, 4, 1};
		roma.setAvailableActionDice(dice);
		roma.useActionDieForMoney(6);
		roma.useActionDieForMoney(4);
		money += 10;
		
		assert(players[0].getMoney() == money): "Actual money " + players[0].getMoney();
		
		assert(roma.getDiscardPile().length == discardedPile);
		{
			int[] drawPileTemp = roma.getDrawPile();
			assert(drawPileTemp[drawPileTemp.length - 1] == GLADIATOR);
		}
		roma.useActionDieForCards(1, new CardPicker() { public int pickCard(int cards[]) { return GLADIATOR; } });
		assert(players[0].getHand().length == 1);
		assert(players[0].getHand()[0] == GLADIATOR);
		
		assert(roma.getDiscardPile().length == discardedPile); // No change. Only took one card.
		
		cardsInPile--;
			
		//only card in my hand
		assert(roma.getCost(GLADIATOR) == 6): "Actual cost " +roma.getCost(GLADIATOR);
		

		assert(roma.getDiscardPile().length == discardedPile);
		roma.layCard(players[0].getHand()[0], DiscCode.DISC3);
		
		money -= roma.getCost(GLADIATOR);


		
		assert(roma.getDrawPile().length == cardsInPile) : roma.getDrawPile().length;
		assert(players[0].getMoney() == money): "Actual money " + players[0].getMoney();
		assert(players[0].getHand().length == 0);
		assert(players[0].getVictoryPoints() == 8): "This didn't change";
		
		roma.endCurrentTurn();
	}
	
	void stateTwo() {
		int money = players[1].getMoney();
		int victoryPoints = players[1].getVictoryPoints();
		int stockpile = roma.getVictoryPointsStockpile();
		
		assert(victoryPoints == 8); 	// ^^ end turn above minuses  2 victory points from this player.
		
		int[] dice = {6, 5, 3};
		roma.setAvailableActionDice(dice);
		roma.useActionDieForMoney(5);
		money += 5;
				
		int args[] = {6};
		roma.activateCard(DiscCode.DISC3, args);
		victoryPoints += 8;
		stockpile -= 8; 	
		
		assert(roma.getVictoryPointsStockpile() == stockpile):
			"VP stockpile should change - now " + roma.getVictoryPointsStockpile();
		
		assert(players[1].getMoney() == money);
		assert(players[1].getVictoryPoints() == victoryPoints);
		roma.endCurrentTurn();
	}
	
	void stateThree() {
		//Don't be confused by the picture. It's alittle behind. See StateFour.jpg (it shows what happens here)
		int money = players[0].getMoney();
		int victoryPoints = players[0].getVictoryPoints();
		int stockpile = roma.getVictoryPointsStockpile(); 
		
		int[] dice = {5, 2, 1};
		roma.setAvailableActionDice(dice);
		roma.useActionDieForMoney(2);
		money += 2;
		roma.useActionDieForCards(1, new CardPicker() { public int pickCard(int cards[]) { return ESSEDUM; } });
		
		int[] args = {};
		roma.activateCard(5, args);
		victoryPoints += 2;
		stockpile -= 2; 
		
		assert(roma.getVictoryPointsStockpile() == stockpile);
		assert(players[0].getMoney() == money); // 6
		assert(players[0].getVictoryPoints() == victoryPoints); // 9 (two Legat - one UDD)
		assert(players[0].getHand().length == 1);
		roma.endCurrentTurn();
	}
	
	void stateFour() {
	   int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int cardsInHand = players[1].getHand().length;
      int stockpile = roma.getVictoryPointsStockpile(); 
                
      assert(victoryPoints == 14); // 2 UDD             
      int[] dice = {5, 4, 1};
                
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(5);
      roma.useActionDieForMoney(4);
      money += 9;
                
      int[] args2 = {MERCATOR};
      roma.activateCard(1, args2); //Activating Haruspex, getting a MERCATOR.
      cardsInHand++;
      cardsInPile--;
      
      rigTheDeck(cardsInPile);   // Restoring order because haruspex shuffles.
      
      assert(players[1].getHand().length == cardsInHand);
                
      int[] hand = players[1].getHand();        
      for (int i = 0; i < hand.length; i++) {
         if (hand[i] == MERCATOR) {
            roma.layCard(hand[i], 5); 
            cardsInHand--;
         }
      }
      money -= roma.getCost(MERCATOR);  
                
      assert(roma.getVictoryPointsStockpile() == stockpile);
      assert(players[1].getMoney() == money); 
      assert(roma.getDrawPile().length == cardsInPile);
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(players[1].getHand().length == cardsInHand);
      roma.endCurrentTurn();
	}
	
	void stateFive() {
		int money = players[0].getMoney();
		int victoryPoints = players[0].getVictoryPoints();
		int cardsInPile = roma.getDrawPile().length;
		int discardedPile = roma.getDiscardPile().length;
		
		assert(roma.getCurrentPlayer() == PLAYER1);
		
		int[] dice = {4, 4, 6};
		roma.setAvailableActionDice(dice);
		roma.useActionDieForMoney(4);
		roma.useActionDieForMoney(4);
		money += 8;
		

		
		TestablePlayer player = roma.getPlayer(PLAYER1);
		
		assert(player.getHand().length == 1): "actual "+players[1].getHand().length;
		roma.useActionDieForCards(6, new CardPicker() { public int pickCard(int cards[]) { return FORUM; } });
		assert(player.getHand().length == 2): "actual "+players[1].getHand().length;
		
		 // not shown by the camera, but I do have 2 cards.
		
		
		
		cardsInPile -= 6;
		discardedPile += 5; // I keep one card.
		
		//Two cards are in my hand, I want to lay a forum so I need to find it (order NOT important in the hand)
		int[] hand = players[0].getHand();	// Save it locally JUST INCASE the order changes everytime you call getHand()
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == FORUM) {
				roma.layCard(hand[i], 2); // I've covered ARCHITECTUS
				discardedPile++;	//Doing so sends it to the discard pile.
			}
		}
		money -= roma.getCost(FORUM);			 // Need to pay for it.
		

		
		assert(roma.getDiscardPile().length == discardedPile) : "Got " + roma.getDiscardPile().length + " expected " + discardedPile; // 5 (cardDisk) + 1 (cover)
		assert(roma.getDrawPile().length == cardsInPile);	// (initial) - 6 (cardDisk)
		assert(players[0].getMoney() == money);	// 9 = (6 (initial) + 8 (money disc) - 5 (laying forum))
		assert(players[0].getVictoryPoints() == victoryPoints); // 8 = (9 (initial) - 1 (UDD))
		assert(players[0].getHand().length == 1);
		roma.endCurrentTurn();
	}
	
	void stateSix() {
	   int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int stockpile = roma.getVictoryPointsStockpile(); 
      int cardsInHand = players[1].getHand().length;

      int[] dice = {1, 1, 3};
      roma.setAvailableActionDice(dice);
                
      assert(victoryPoints == 13);//UDD
                
      int[] args = {1};
      roma.activateCard(3, args);
      victoryPoints += 3;//Basilica + 1
      stockpile -= 3;   

      int[] args2 = {TEMPLUM};
      roma.activateCard(1, args2); //Activating Haruspex, getting a TEMPLUM.
      cardsInPile--;
      cardsInHand++;
      rigTheDeck(cardsInPile);

            
      int[] hand = players[1].getHand();   // Save it locally JUST INCASE the order changes everytime you call getHand()
      for (int i = 0; i < hand.length; i++) {
         if (hand[i] == TEMPLUM) {
            roma.layCard(hand[i], 2); 
            cardsInHand--;
         }
      }
      money -= roma.getCost(TEMPLUM);          // Need to pay for it.
              
      assert(players[1].getFaceUpCards()[1] == TEMPLUM);  // Templum on DD2
              
      assert(roma.getDrawPile().length == cardsInPile);
      assert(players[1].getMoney() == money);
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(players[1].getHand().length == cardsInHand);	
      roma.endCurrentTurn();
	}
	
	void stateSeven() {
	   
      int money = players[0].getMoney();
      int victoryPoints = players[0].getVictoryPoints();
      int stockpile = roma.getVictoryPointsStockpile(); 
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      
      int[] dice = {4, 2, 6};
      roma.setAvailableActionDice(dice);
      
      int[] args = {6};
      roma.activateCard(DiscCode.DISC2, args);
      victoryPoints += 6;
      stockpile -= 6; 
      
      roma.useActionDieForCards(4, new CardPicker() { public int pickCard(int cards[]) { return HARUSPEX; } });
      cardsInPile -= 4;
      discardedPile += 3; // I keep one card.
      
      assert(players[0].getHand().length == 2); // haruspex + essedum
      

      
      int[] hand = players[0].getHand();  // Save it locally
      for (int i = 0; i < hand.length; i++) {
         if (hand[i] == HARUSPEX) {
            roma.layCard(hand[i], DiscCode.DISC6);
         }
      }
      assert(players[0].getHand().length == 1);
      
      money -= roma.getCost(HARUSPEX);           // Need to pay for it.
      
      
      assert(roma.getDiscardPile().length == discardedPile); // (initial) + 3 (cardDisk)
      assert(roma.getDrawPile().length == cardsInPile);   // (initial) - 4 (cardDisk)
      assert(players[0].getMoney() == money) : players[0].getMoney() + " | " + money; //  (initial) - 4 (haruspex)
      assert(players[0].getVictoryPoints() == victoryPoints); // (initial) - 1VP (UDD) + 6VP (Forum)
      assert(roma.getVictoryPointsStockpile() == stockpile); // (initial) - 6VP (Forum)
      roma.endCurrentTurn();		
	}
	
	void stateEight() {
      int money = players[1].getMoney();
      int oppMoney = players[0].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int oppVictoryPoints = players[0].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int cardsInHand = players[1].getHand().length;

      int[] dice = {6, 1, 4};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(4);
      money += 4;
                


      int[] args = {CONSILIARIUS, 6, MERCATOR, 1, HARUSPEX, 5};
      roma.activateCard(6, args);
      assert(players[1].getFaceUpCards()[0] == MERCATOR);
      assert(players[1].getFaceUpCards()[4] == HARUSPEX);
      assert(players[1].getFaceUpCards()[5] == CONSILIARIUS);

      int[] args2 = {4};
      roma.activateCard(1, args2); //Activating Mercator, buying 4 victory points.
      money -= 8;
      victoryPoints += 4;
      oppMoney += 8;
      oppVictoryPoints -= 4;
              
      assert(roma.getDrawPile().length == cardsInPile);
      assert(players[1].getMoney() == money);
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(players[0].getMoney() == oppMoney);
      assert(players[0].getVictoryPoints() == oppVictoryPoints);
      assert(players[1].getHand().length == cardsInHand);      	
      roma.endCurrentTurn();
	}
	
	void stateNine() {
      int money = players[0].getMoney();
      int victoryPoints = players[0].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      int cardsInHand = players[0].getHand().length;
      
      int[] dice = {6, 5, 5};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(5);
      money += 5;
      

      int[] args = {NERO};
      roma.activateCard(6, args); //Activating Haruspex, getting a Nero.
      cardsInPile--;
      cardsInHand++;
      rigTheDeck(cardsInPile);

      assert(players[0].getHand().length == cardsInHand);
      
      //Two cards are in my hand, I want to lay nero so I need to find it (order NOT important in the hand)
      int[] hand = players[0].getHand();   // Save it locally JUST INCASE the order changes everytime you call getHand()
      for (int i = 0; i < hand.length; i++) {
         if (hand[i] == NERO) {
            roma.layCard(hand[i], 5); // I've covered LEGAT
            discardedPile++;   //Doing so sends it to the discard pile.
            cardsInHand--;
         }
      }
      money -= roma.getCost(NERO);          // Need to pay for it.
      

      
      
      int[] args2 = {3};         // The Blue forum lies on dice disk 3.
      roma.activateCard(5, args2);
      discardedPile += 2;       // Both nero and forum disappear
      

      assert(players[0].getFaceUpCards()[4] == NO_CARD);  // Red Nero on DD5
      assert(players[1].getFaceUpCards()[2] == NO_CARD);   // Blue Forum on DD3
      
      assert(roma.getDiscardPile().length == discardedPile); // (initial) + 3 = (2 destroyed / 1 covered)
      assert(roma.getDrawPile().length == cardsInPile);   // (initial) - 1 (Haruspex)
      assert(players[0].getMoney() == money);   // (initial) + 5 (money disc) - (Nero Cost)
      assert(players[0].getVictoryPoints() == victoryPoints); // no change
      assert(players[0].getHand().length == cardsInHand);  // (initial) + 1 (Nero) - 1 (Nero)
      roma.endCurrentTurn();
	}
	
	void stateTen() {
      int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int stockpile = roma.getVictoryPointsStockpile();
      
      int[] dice = {6, 3, 2};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(6);
      roma.useActionDieForMoney(3);
      roma.useActionDieForMoney(2);
      money += 11;
      
      assert(roma.getVictoryPointsStockpile() == stockpile); 
      assert(players[1].getMoney() == money);
      assert(players[1].getVictoryPoints() == victoryPoints);
      roma.endCurrentTurn();		
	}
	
	void stateEleven() {
      int money = players[0].getMoney();
      int discardedPile = roma.getDiscardPile().length;
      int cardsInHand = players[0].getHand().length;
                    
      int[] dice = {3, 4, 6};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(6);
      money += 6;
      

      int[] args = {DiscCode.DISC1};
      roma.activateCard(DiscCode.DISC3, args); // activate gladiator, sending back mercator
      
      assert(players[1].getFaceUpCards()[0] == CardCode.NO_CARD);
      int[] opponentHand = players[1].getHand();
      boolean hasMercatorInHand = false;
      for (int i = 0; i < opponentHand.length && !hasMercatorInHand; i++) {
         if (opponentHand[i] == MERCATOR) {
            hasMercatorInHand = true;
         }
      }
      assert(hasMercatorInHand);
                
      int[] args2 = {NERO};
      roma.activateCard(DiscCode.DISC4, args2); // activate Aesculapinum, get Nero from discarded pile
      cardsInHand++;
      discardedPile--;
      int[] hand = players[0].getHand();
      boolean hasNeroInHand = false;
      for (int i = 0; i < hand.length && !hasNeroInHand; i++) {
         if (hand[i] == NERO) {
            roma.layCard(hand[i], DiscCode.DISC5); 
            cardsInHand--;
            hasNeroInHand = true;
         }
      }
      assert(hasNeroInHand);
      money -= roma.getCost(NERO);          // Need to pay for it.
      assert(players[0].getFaceUpCards()[4] == NERO);
         
      assert(roma.getDiscardPile().length == discardedPile);
      assert(players[0].getMoney() == money);   
      assert(players[0].getHand().length == cardsInHand);
      roma.endCurrentTurn();		
}
	
	void stateTwelve() {
      int money = players[1].getMoney();
      int opponentMoney = players[0].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int opponentVictoryPoints = players[0].getVictoryPoints();
      int discardedPile = roma.getDiscardPile().length;
      int cardsInHand = players[1].getHand().length;
                
      int[] dice = {1, 1, 6};
      roma.setAvailableActionDice(dice);
      roma.useActionDieForMoney(1);
      roma.useActionDieForMoney(1);
      money += 2;
                
      int[] hand = players[1].getHand();
      boolean isFinished = false;
      for (int i = 0; i < hand.length && !isFinished; i++) {
         if (hand[i] == MERCATOR) {
            roma.layCard(hand[i], DiscCode.DISC6); 
            cardsInHand--;
            discardedPile++; // covered Consiliarius
            isFinished = true;
         }
      }
      money -= roma.getCost(MERCATOR);          // Need to pay for it.
      assert(players[1].getFaceUpCards()[5] == MERCATOR);

      int[] args = {3};
      roma.activateCard(DiscCode.DISC6, args); // activate Mercator, buying 3 VP
      money -= 3*2;
      opponentMoney += 3*2;
      victoryPoints += 3;
      opponentVictoryPoints -= 3;
                
      assert(roma.getDiscardPile().length == discardedPile);
      assert(players[1].getMoney() == money);   
      assert(players[0].getMoney() == opponentMoney);
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(players[0].getVictoryPoints() == opponentVictoryPoints);
      assert(players[1].getHand().length == cardsInHand);
      roma.endCurrentTurn();		
	}
	
	void stateThirteen() {
      int money = players[0].getMoney();
      int victoryPoints = players[0].getVictoryPoints();
      int cardsInHand = players[0].getHand().length;;
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      int cardsInOppHand = players[1].getHand().length;
      
      int[] dice = {3, 5, 6};
      roma.setAvailableActionDice(dice);
      
      roma.useActionDieForMoney(5);
      money += 5;
      
      int[] args = {SICARIUS};
      roma.activateCard(DiscCode.DISC6, args);
      cardsInPile -= 1;
      cardsInHand += 1;
      rigTheDeck(cardsInPile);
      
      int [] args2 = {DiscCode.DISC6};
      roma.activateCard(DiscCode.DISC3, args2);
      cardsInOppHand += 1;
      
      
      assert(players[0].getMoney() == money);
      assert(players[0].getHand().length == cardsInHand);
      assert(roma.getDrawPile().length == cardsInPile);
      assert(players[1].getHand().length == cardsInOppHand);
      assert(players[0].getVictoryPoints() == victoryPoints); //no change
      assert(roma.getDrawPile().length == cardsInPile);
      assert(roma.getDiscardPile().length == discardedPile); //no change
      roma.endCurrentTurn();            		
	}
	
	void stateFourteen() {
      int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int oppVictoryPoints = players[0].getVictoryPoints();
      int oppMoney = players[0].getMoney();
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      
      int[] dice = {4, 6, 1};
      roma.setAvailableActionDice(dice);
      
      roma.useActionDieForMoney(4);
      money += 4;
      roma.useActionDieForMoney(6);
      money += 6;
      
      int[] hand = players[1].getHand();
      boolean isFinished = false;
      for (int i = 0; i < hand.length && !isFinished; i++) {
         if (hand[i] == MERCATOR) {
            roma.layCard(hand[i], DiscCode.DISC1); 
            isFinished = true;
         }
      }
      money -= roma.getCost(MERCATOR);
      
      int spend = 4; // can only spend a multiple of 2 money
      int vpBought = 2;
      int[] args = {vpBought};
      roma.activateCard(DiscCode.DISC1, args);
      money -= spend;
      oppMoney += spend;
      oppVictoryPoints -= vpBought;
      victoryPoints += vpBought;
      
      assert(players[1].getMoney() == money); 
      assert(players[0].getMoney() == oppMoney); 
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(players[0].getVictoryPoints() == oppVictoryPoints);
      assert(roma.getDrawPile().length == cardsInPile); // no change
      assert(roma.getDiscardPile().length == discardedPile); // no change
      roma.endCurrentTurn();		
	}
	
	void stateFifteen() {
      int victoryPoints = players[0].getVictoryPoints();
      int stockpile = roma.getVictoryPointsStockpile();
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      int cardInHand = players[0].getHand().length;      

      int[] dice = {2, 2, 4};
      roma.setAvailableActionDice(dice);
    
      int args[] = {4};
      roma.activateCard(2, args);
      victoryPoints += 4; // Forum (got 4 victory points)
      stockpile -= 4;         

      roma.useActionDieForCards(2, new CardPicker() { public int pickCard(int cards[]) { return CONSUL; } });
      cardInHand += 1; //keep the card in hand

      cardsInPile -= 2; //take 2 card from the pile
      discardedPile += 1;  //discard 1 card
      
      assert(players[0].getHand().length == cardInHand);
      assert(roma.getVictoryPointsStockpile() == stockpile);
      assert(players[0].getVictoryPoints() == victoryPoints);
      assert(roma.getDrawPile().length == cardsInPile); // decrease by 2
      assert(roma.getDiscardPile().length == discardedPile); //increase by 1
      roma.endCurrentTurn();
	}
	
	void stateSixteen() {
      int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      int cardsInHand = players[1].getHand().length;
         
      int[] dice = {4, 5, 5};
      roma.setAvailableActionDice(dice);
         
      roma.useActionDieForMoney(5);
      money += 5;
      
      roma.useActionDieForMoney(4);
      money += 4;

      //Activate a Haruspex to get Forum.
      int[] args = {FORUM};
      roma.activateCard(DiscCode.DISC5, args); 
      cardsInPile--;
      cardsInHand++;
      rigTheDeck(cardsInPile);

      assert(players[1].getMoney() == money);
      assert(players[1].getHand().length == cardsInHand);
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(roma.getDrawPile().length == cardsInPile);
      assert(roma.getDiscardPile().length == discardedPile);
      roma.endCurrentTurn(); 		
	}
	
	void stateSeventeen() {
	   //activated TRIBUNUS_PLEBIS and FORUM, and obtain 1 vPoint from opponent, and 4 vPoints from stockpile
      int money = players[0].getMoney();
      int victoryPoints = players[0].getVictoryPoints();
      int oppVictoryPoints = players[1].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      int stockpile = roma.getVictoryPointsStockpile();
      int cardsInHand = players[0].getHand().length;
              
      int[] dice = {1, 2, 4};
      roma.setAvailableActionDice(dice);

      // Activate a Tribunus_Plebis
      int[] args2 = {};
      roma.activateCard(DiscCode.DISC1, args2);
      victoryPoints += 1; // Tribunus_plebis (got 1 victory point from opponent)
      oppVictoryPoints -= 1;
              
      
      //Activate a Forum
      int[] args = {4};
      roma.activateCard(DiscCode.DISC2, args); 
      victoryPoints += 4; // Forum (got 4 victory points from stockpile)
      stockpile -= 4;
      

      assert(players[0].getMoney() == money);
      assert(players[0].getHand().length == cardsInHand);
      assert(players[0].getVictoryPoints() == victoryPoints);
      assert(players[1].getVictoryPoints() == oppVictoryPoints);
      assert(roma.getDrawPile().length == cardsInPile);
      assert(roma.getDiscardPile().length == discardedPile);
      assert(roma.getVictoryPointsStockpile() == stockpile);
      roma.endCurrentTurn(); 		
	}
	
	void stateEighteen() {
	   // place a forum
      // activate templum and forum, obtain 6 vPoints from stockpile due to Forum, and 2 vPoints due to templum
      int money = players[1].getMoney();
      int victoryPoints = players[1].getVictoryPoints();
      int cardsInPile = roma.getDrawPile().length;
      int discardedPile = roma.getDiscardPile().length;
      int stockpile = roma.getVictoryPointsStockpile();
      int cardsInHand = players[1].getHand().length;
              
      int[] dice = {2, 3, 6};
      roma.setAvailableActionDice(dice);

      int[] hand = players[1].getHand();
      boolean isFinished = false;
      for (int i = 0; i < hand.length && !isFinished; i++) {
         if (hand[i] == FORUM) {
            roma.layCard(hand[i], DiscCode.DISC3);
            money -= roma.getCost(hand[i]);
            cardsInHand--;
            isFinished = true;
         }
      }
      
      assert(players[1].getMoney() == money);
      assert(isFinished);
      
      // Activate a FORUM
      int args[] = {6, 2};  // forum and templum
      roma.activateCard(DiscCode.DISC3, args);
      victoryPoints += 6; // (got 6 victory point from stockpile)
      stockpile -= 6;
      victoryPoints += 2; // (got 2 vPoints due to templum on the left
      stockpile -= 2;
      victoryPoints += 2; // (got 2 vPoints due to basilica on the right
      stockpile -= 2;
      

      assert(players[1].getMoney() == money);
      assert(players[1].getHand().length == cardsInHand);
      assert(players[1].getVictoryPoints() == victoryPoints);
      assert(roma.getDrawPile().length == cardsInPile);
      assert(roma.getDiscardPile().length == discardedPile);
      assert(roma.getVictoryPointsStockpile() == stockpile);
	}
		
	void gameOver() {
	   assert(roma.getVictoryPointsStockpile() == 0); // The game ends with exactly 0 in the VP.
	   assert(roma.isGameOver());
	   
	}
}

