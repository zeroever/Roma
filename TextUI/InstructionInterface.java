package TextUI;

import Logic.Deck;
import Logic.Dice;
import Logic.Game;

/**
 * Asking for a player action
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public interface InstructionInterface {

    /**
     * ask player to select a card
     * 
     * @return integer value starting from 1 represent the position of card
     */
    public abstract int selectCard(Deck deck);

    /**
     * ask player to select a dice disc
     * 
     * @return integer value between 1-7
     */
    public abstract int selectDiceDisc();

    /**
     * ask player to toss again player can decide not to
     * 
     * @return true for yes, otherwise false
     */
    public abstract boolean tossAgain();

    /**
     * ask player to select action die
     * 
     * @param actionDice
     * @return integer between 0 - 2
     */
    public abstract int selectDie(Dice[] actionDice);

    /**
     * Ask the player for card activation argument
     * @param game
     * @param cardCode - card to be activated
     * @param activatedCardPosition - position of card to be activated
     * @return array of integer represents an argument for card activation
     */
    public abstract int[] getActivateParameter(Game game, int cardCode,
            int activatedCardPosition);

}