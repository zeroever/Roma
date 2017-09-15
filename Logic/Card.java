package Logic;

/**
 * Card object representation
 * 
 * This is an abstract class because each the real concrete class of card will need
 * to implement its own activation method.
 * 
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public abstract class Card {
	
    public static final int CHARACTER = 0;
    public static final int BUILDING = 1;
    
    private String name;
    private int index;  //card constant representation (look at CardCode)
    private int defense;
    private int additionalDefense = 0;
    private int cost;
    private int activationDice;  //- (0,1,2) number of dice required to activate the card
    private boolean faceUp;
    private String ability;
    private int type; //character or building
    
    
    public Card (String name, int cost, int defense, int activatedDice,
                 String ability, int type, int index) {
		this.name = name;
		this.defense = defense;
		this.cost = cost;
		this.activationDice = activatedDice;
		this.ability = ability;
		this.type = type;
		this.faceUp = true;
		this.index = index;
    }
	
    /**
     * Get card constant
     * @return card constant value
     */
    public int getIndex() {
    	return this.index;
    }
    
    /**
     * Get card name
     * @return card name
     */
    public String getName() {
    	return this.name;
    }
    
    /**
     * Get card defense + additional defense
     * @return total defense value of the card
     */
    public int getDefense() {
    	return this.defense + this.additionalDefense;
    }
    
    /**
     * Get card cost
     * @return cost of the card
     */
    public int getCost() {
    	return this.cost;
    }
    
    /**
     * Get how many action die required to activate the card
     * @return number of action die required
     */
    public int getActivationDice() {
    	return this.activationDice;	
    }
    
    /**
     * Check if the card is faced up or not
     * @return true if the card is faced up, otherwise false
     */
    public boolean isFaceUp() {
    	return this.faceUp;
    }
    
    /**
     * Get card ability
     * @return String description of card ability
     */
    public String getAbility() {
    	return this.ability;
    }
    
    /**
     * Get card type either CHARACTER or BUILDING
     * @return CHARACTER or BUILDING
     */
    public int getType() {
    	return this.type;
    }
    
    /**
     * Return the result of battle given attack value and defense value
     * @param attack - attack value
     * @param defense - defense value
     * @return true if the attack is greater than defense
     */
    public boolean battle(int attack, int defense){
    	boolean result = false;
    	if (attack >= defense) {
    		result = true;
    	}
    	return result;
    }
    
    /**
     * Modify additional defense value
     * @param value - value that will be ADD to current additional defense
     */
    public void modifyAdditionalDefense(int value) {
    	this.additionalDefense = this.additionalDefense + value;
    }
    
    /**
     * Set the additional defense
     * @param value - value of new additional defense
     */
    public void setAdditionalDefense(int value) {
    	this.additionalDefense = value;
    }
    
    /**
     * Get additional defense
     * @return additional defense value
     */
    public int getAddtionalDefense() {
    	return additionalDefense;
    }
    
    /**
     * Set the card to face up
     * @param faceUp - true for faced up
     */
    public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
    
    /**
     * Card activation 
     * @param game
     * @param args - argument for card activation
     * @return true if the card activation is a success, otherwise false
     */
    public abstract boolean activate(Game game, int[] args);

}