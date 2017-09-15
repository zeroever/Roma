package Logic;


public class TestField {

    /**
     * @param args
     */
    
    private static final int PLAYER_1 = 0;
    private static final int PLAYER_2 = 1;
    
    public static void main(String[] args) {
        
        Field field = new Field();
        Deck discardPile = new Deck();
        Dice[] actionDice = new Dice[3];
        
        
        //create first 10 cards
        Card[] card = new Card[10];
        for(int i = 0; i < 10; i++) {
            //card[i] = new Card(i);
        }
        
        System.out.println("Test Field class");
        
        System.out.println("Test empty field");
        
        assert(field.getEmptyDisc(PLAYER_1) == 6);
        assert(field.getEmptyDisc(PLAYER_2) == 6);
        assert(discardPile.isEmpty());
        assert(discardPile.getNumOfCards() == 0);
        
        System.out.println("lay 4 cards on player 1 field");
        for(int i = 0; i < 4; i++) {
            field.layCardOnField(PLAYER_1, card[i], i+1, discardPile);
        }
        assert(field.getEmptyDisc(PLAYER_1) == 2);
        assert(field.getEmptyDisc(PLAYER_2) == 6);
        assert(discardPile.isEmpty());
        assert(discardPile.getNumOfCards() == 0);
        
        //check pointer
        for(int i = 0; i < 4; i++) {
            assert(field.getCard(PLAYER_1, i+1).getName() == card[i].getName());
        }
        
        
        System.out.println("lay 2 cards on player 2 field");
        for(int i = 4; i < 6; i++) {
            field.layCardOnField(PLAYER_2, card[i], i+1, discardPile);
        }
        assert(field.getEmptyDisc(PLAYER_1) == 2);
        assert(field.getEmptyDisc(PLAYER_2) == 4);
        assert(discardPile.isEmpty());
        assert(discardPile.getNumOfCards() == 0);
        
        for(int i = 4; i < 6; i++) {
            assert(field.getCard(PLAYER_2, i+1).getName() == card[i].getName());
        }
        
        
        System.out.println("cover 4 cards on player 1 field");
        for(int i = 6; i < 10; i++) {
            field.layCardOnField(PLAYER_1, card[i], i-5, discardPile);
        }
        assert(field.getEmptyDisc(PLAYER_1) == 2);
        assert(field.getEmptyDisc(PLAYER_2) == 4);
        assert(!discardPile.isEmpty());
        assert(discardPile.getNumOfCards() == 4);
        
        for(int i = 6; i < 10; i++) {
            assert(field.getCard(PLAYER_1, i-5).getName() == card[i].getName());
        }
        
        //Field testField = new Field();
        	//testField.layCardOnField(PLAYER_1, new Card(0), 1, discardPile);//sicarius
        	//testField.layCardOnField(PLAYER_1, new Card(50), 2, discardPile);//turris //always there
        	//testField.layCardOnField(PLAYER_1, new Card(48), 3, discardPile);//templum //always there
        	//testField.layCardOnField(PLAYER_1, new Card(40), 4, discardPile);//forum 
        	//testField.layCardOnField(PLAYER_1, new Card(15), 5, discardPile);//nero
        	//testField.layCardOnField(PLAYER_1, new Card(19), 6, discardPile);//scaenicus
        	
        
        //discardPile.listCard();
        System.out.println("roll dice");
        for(int i = 0; i < actionDice.length; i++) {
        	actionDice[i] = new Dice();
        	actionDice[i].roll();
        	System.out.println(actionDice[i].getFace());
        }
        //actionDice[0].setUsed(true);
        actionDice[1].setUsed(true);
        
        System.out.println("All test passed.");
        System.out.println("You're awesome!");
    }

}
