package Logic;

/**
 * Die object representation
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public class Dice {
	
	public static final int NO_DIE = -1;
	
	private boolean used;
	private int face;
	
	public Dice() {
		used = false;
	}

	/**
	 * Roll a die
	 * @return number of die rolled
	 */
	public int roll() {
		face = 0;
		double randomDouble;
	    
        randomDouble = 6 * Math.random();         
        face = ((int) randomDouble) + 1;
        
		return face;
	}
	
	/**
	 * Check if the die is used or not
	 * @return true if the die is used, otherwise false
	 */
	public boolean isUsed() {
		return used;
	}
	
	/**
	 * Set the die face
	 * @param value - value to be set as a die face
	 */
	public void setFace(int value) {
	    this.face = value;
	}
	
	/**
	 * Set the die to be used
	 * @param used - true for used
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}
	
	/**
	 * Get the die face
	 * @return value of the die face
	 */
	public int getFace() {
		return face;
	}
	
}
