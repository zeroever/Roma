package TextUI;

import Logic.*;

/**
 * Display output of the game
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public interface DisplayInterface {

    /**
     * Listing the card inside a deck
     * @param deck
     */
    public void listCard(Deck deck);
    
    /**
     * Display player status on the screen
     */
    public abstract void showStatus();

    /**
     * Display card field on the screen
     */
    public abstract void showField();

    /**
     * Display text on the screen
     * @param text
     */
    public abstract void showText(String text);

    /**
     * Display main menu on the screen
     */
    public abstract void showMainMenu();

    /**
     * Display action menu on the screen
     */
    public abstract void showActionMenu();

    /**
     * Display rules on the screen
     */
    public abstract void showRules();

    /**
     * Clear the screen
     */
    public abstract void clear();
    
    /**
     * Display card description on the screen
     * @param s - string of card description
     */
    public abstract void showCard(String s);
}