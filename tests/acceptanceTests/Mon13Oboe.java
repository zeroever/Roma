/**
 * 
 * Test Zero written by Mon13Oboe.
 * Test 1-7 written by Jarupat Jisarojito (jjis590@cse.unsw.edu.au)
 * 
 */

package tests.acceptanceTests;

import static tests.main.CardCode.*; 
import static tests.main.PlayerCode.*;
import static tests.main.DiscCode.*;

import tests.main.*;
import tests.userProgram.*;

public class Mon13Oboe extends RomaTest{
    
    private RomaEngine r;
    private TestablePlayer p1;
    private TestablePlayer p2;
    
    public Mon13Oboe() {
        
        r = new Roma();
        p1 = r.getPlayer (PLAYER1);
        p2 = r.getPlayer (PLAYER2);
        
        int[] deck = {
                FORUM,
                MERCATOR,
                TEMPLUM,
                TURRIS,
                CENTURIO,
                TURRIS,
                ARCHITECTUS,
                LEGAT,PRAETORIANUS,
                LEGIONARIUS,
                FORUM,
                CONSILIARIUS,
                BASILICA,
                ARCHITECTUS,
                BASILICA,
                TEMPLUM
                  };
        int[] discard = {
                PRAETORIANUS,
                CENTURIO
                };

        r.setDrawPile (deck);
        r.setDiscardPile(discard);
        assert(isPermutation(r.getDiscardPile(), discard));
    }
    
    public String toString () {
        return "Mon13Oboe Tests";
     }
    
    public void run() {
        // Kitten:
        //  the "still buggy" comment gives me the spooks
        //  so commenting out testZero
        //  testZero(); //still buggy
        setInitialState();
        System.out.println("Start test 1");
        testOne();
        System.out.println("Test 1 passed");
        System.out.println("Start test 2");
        testTwo();
        System.out.println("Test 2 passed");
        System.out.println("Start test 3");
        testThree();
        System.out.println("Test 3 passed");
        System.out.println("Start test 4");
        testFour();
        System.out.println("Test 4 passed");
        System.out.println("Start test 5");
        testFive();
        System.out.println("Test 5 passed");
        System.out.println("Start test 6");
        testSix();
        System.out.println("Test 6 passed");
        System.out.println("Start test 7");
        testSeven();
        System.out.println("Test 7 passed");
    }
    
    private void setInitialState() {
        
        int hand1[] = {NERO, CONSUL};
        p1.setHand (hand1);
        p1.setMoney(0);
        p1.setVictoryPoints (8);
        
        int[] p1Field = {MERCATUS, FORUM, TEMPLUM, TURRIS, NO_CARD, NO_CARD, MERCATOR};
        
        p1.setFaceUpCards(p1Field);
        int[] faceUpCards = p1.getFaceUpCards();
        assert(faceUpCards[0]== MERCATUS);
        assert(faceUpCards[1]== FORUM);
        assert(faceUpCards[2]== TEMPLUM);
        assert(faceUpCards[3]== TURRIS);
        assert(faceUpCards[4]== NO_CARD);
        assert(faceUpCards[5]== NO_CARD);
        assert(faceUpCards[6]== MERCATOR);
        assert(p1.getMoney() == 0);
        
        
        int[] hand2 = {FORUM, SCAENICUS, SENATOR, AESCULAPINUM};
        p2.setHand (hand2);
        p2.setMoney(10);
        p2.setVictoryPoints (12);
        
        int[] p2Field = {ESSEDUM, CONSILIARIUS, LEGAT, NO_CARD, MACHINA, MERCATUS, NO_CARD};
        
        p2.setFaceUpCards(p2Field);
        
        faceUpCards = p2.getFaceUpCards();
        assert(faceUpCards[0]== ESSEDUM);
        assert(faceUpCards[1]== CONSILIARIUS);
        assert(faceUpCards[2]== LEGAT);
        assert(faceUpCards[3]== NO_CARD);
        assert(faceUpCards[4]== MACHINA);
        assert(faceUpCards[5]== MERCATUS);
        assert(faceUpCards[6]== NO_CARD);
        
        r.setCurrentPlayer(PLAYER1);
        
    }

    /*
     * Stage 1: player 1's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Mercatus       1       Essedum
     *   Forum          2       Consiliarius
     *   Templum        3       Legat
     *   Turris         4       -
     *   -              5       Machina
     *   -              6       Mercatus
     *   Mecator        7       -
     *   
     *    $0                       $10
     *    VP 8      vp 16          VP 12
     *    
     *    p1 hand : nero, consul
     *    p2 hand : FORUM, SCAENICUS, SENATOR, AESCULAPINUM
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, BASILICA,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,CENTURIO,
     *          ARCHITECTUS,BASILICA,TEMPLUM
     *    discard: PRAETORIANUS,CENTURIO
     *        
     */
    public void testOne() {
        int dice1[] = {4,5,5};
        r.setAvailableActionDice (dice1);
        
        r.useActionDieForMoney (4);
        r.useActionDieForMoney (5);
        assert(p1.getMoney() == 9);
        assert(r.getAvailableActionDice().length == 1);
        
        //activate mercator at the bribe disc
        //buying 2 VP from the opponent
        int[] args = {5, 2};
        r.activateCard (7,args);
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getMoney() == 0);
        assert(p2.getMoney() == 14);
        assert(p1.getVictoryPoints() == 10);
        assert(p2.getVictoryPoints() == 10);
        
        r.endCurrentTurn ();
        assert(!r.isGameOver ());
    }

    /*
     * Stage 2: player 2's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Mercatus       1       Essedum
     *   Forum          2       Consiliarius
     *   Templum        3       Legat
     *   Turris         4       -
     *   -              5       Machina
     *   -              6       Mercatus
     *   Mecator        7       -
     *   
     *    $0                       $14
     *    VP 10       vp 16       VP 10
     *    
     *    p1 hand : nero, consul
     *    p2 hand : FORUM, SCAENICUS, SENATOR, AESCULAPINUM
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, CENTURIO,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,BASILICA,
     *          ARCHITECTUS,BASILICA,TEMPLUM
     *    discard: PRAETORIANUS,CENTURIO
     */
    public void testTwo() {
        //has two unoccupied dice disc thus vp reduced by 2
        assert(p2.getVictoryPoints() == 8);
        assert(r.getVictoryPointsStockpile() == 18);
        
        int dice[] = {2,3,6};
        r.setAvailableActionDice (dice);
        
        //activate consiliarius 
        //all character cards should be picked up
        //essedum -> 2
        //consiliarius -> 7
        //legat -> 5  (cover machina)
        int[] args = {ESSEDUM, 2, CONSILIARIUS, 7, LEGAT, 5};
        r.activateCard (2,args);
        assert(r.getAvailableActionDice().length == 2);
        int[] p2Field = p2.getFaceUpCards();
        assert(p2Field[0] == NO_CARD);
        assert(p2Field[1] == ESSEDUM);
        assert(p2Field[2] == NO_CARD);
        assert(p2Field[3] == NO_CARD);
        assert(p2Field[4] == LEGAT);
        assert(p2Field[5] == MERCATUS);
        assert(p2Field[6] == CONSILIARIUS);
        
        //lay forum
        r.layCard(FORUM, DISC3);
        p2Field = p2.getFaceUpCards();
        assert(p2Field[0] == NO_CARD);
        assert(p2Field[1] == ESSEDUM);
        assert(p2Field[2] == FORUM);
        assert(p2Field[3] == NO_CARD);
        assert(p2Field[4] == LEGAT);
        assert(p2Field[5] == MERCATUS);
        assert(p2Field[6] == CONSILIARIUS);
        assert(p2.getMoney() == 9);
        
        //draw templum
        r.useActionDieForCards (3, new CardPicker() { public int pickCard(int cards[]) { return TEMPLUM; }} );
        assert(r.getAvailableActionDice().length == 1);
        int[] p2Hand = p2.getHand();
        int[] hand = {SCAENICUS, SENATOR, AESCULAPINUM, TEMPLUM};
        assert(isPermutation(p2Hand, hand));
        
        int[] discard = {PRAETORIANUS,CENTURIO, MACHINA, ARCHITECTUS,BASILICA};
        int[] discardPile = r.getDiscardPile();
        assert(isPermutation(discard, discardPile));
        
        //lay templum
        r.layCard(TEMPLUM, DISC4);
        p2Field = p2.getFaceUpCards();
        assert(p2Field[0] == NO_CARD);
        assert(p2Field[1] == ESSEDUM);
        assert(p2Field[2] == FORUM);
        assert(p2Field[3] == TEMPLUM);
        assert(p2Field[4] == LEGAT);
        assert(p2Field[5] == MERCATUS);
        assert(p2Field[6] == CONSILIARIUS);
        assert(p2.getMoney() == 7);
        
        r.useActionDieForMoney(6);
        assert(r.getAvailableActionDice().length == 0);
        assert(p2.getMoney() == 13);
        
        r.endCurrentTurn ();
        assert(!r.isGameOver ());
    }

    /*
     * Stage 3: player 1's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Mercatus       1       -
     *   Forum          2       Essedum
     *   Templum        3       Forum
     *   Turris         4       Templum
     *   -              5       Legat
     *   -              6       Mercatus
     *   Mecator        7       Consiliarius
     *   
     *    $0                       $13
     *    VP 10        vp 18       VP 8
     *    
     *    p1 hand : nero, consul
     *    p2 hand : SCAENICUS, SENATOR, AESCULAPINUM
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, CENTURIO,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,BASILICA,
     *          
     *    discard: PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, MACHINA
     */
    public void testThree() {
        //p1 has 2 unoccupied dice disc
        assert(p1.getVictoryPoints() == 8);
        assert(r.getVictoryPointsStockpile() == 20);
        
        int dice[] = {2,6,6};
        r.setAvailableActionDice (dice);
        
        //activate forum and templum
        //receive 12 vp
        int[] args = {6,6};
        r.activateCard (2,args);
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getVictoryPoints() == 20);
        assert(r.getVictoryPointsStockpile() == 8);
        
        r.endCurrentTurn ();
        assert(!r.isGameOver ());
    }
    
    /*
     * Stage 4: player 2's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Mercatus       1       -
     *   Forum          2       Essedum
     *   Templum        3       Forum
     *   Turris         4       Templum
     *   -              5       Legat
     *   -              6       Mercatus
     *   Mecator        7       Consiliarius
     *   
     *    $0                       $13
     *    VP 20       vp 8         VP 8
     *    
     *    p1 hand : nero, consul
     *    p2 hand : SENATOR, AESCULAPINUM
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, CENTURIO,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,BASILICA
     *          
     *    discard: PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, MACHINA
     */
    public void testFour() {
        //p2 has 1 unoccupied dice disc
        assert(p2.getVictoryPoints() == 7);
        assert(r.getVictoryPointsStockpile() == 9);
        
        int dice[] = {1,5,6};
        r.setAvailableActionDice (dice);
        
        //lay acaenicus
        r.layCard(SCAENICUS, DISC1);
        int[] p2Field = p2.getFaceUpCards();
        assert(p2Field[0] == SCAENICUS);
        assert(p2Field[1] == ESSEDUM);
        assert(p2Field[2] == FORUM);
        assert(p2Field[3] == TEMPLUM);
        assert(p2Field[4] == LEGAT);
        assert(p2Field[5] == MERCATUS);
        assert(p2Field[6] == CONSILIARIUS);
        assert(p2.getMoney() == 5);
        
        int[] hand = {SENATOR, AESCULAPINUM};
        assert(isPermutation(hand, p2.getHand()));
        
        //activate legat
        //receive 2 vp
        int[] args = {0};
        r.activateCard (5,args);
        assert(r.getAvailableActionDice().length == 2);
        assert(p2.getVictoryPoints() == 9);
        assert(r.getVictoryPointsStockpile() == 7);
        
        
        //activate scaenicus
        //copy consiliarius
        int[] args2 = {CONSILIARIUS, SCAENICUS, 5, ESSEDUM, 7, LEGAT, 2, CONSILIARIUS, 1}; 
        r.activateCard (1,args2);
        p2Field = p2.getFaceUpCards();
        assert(p2Field[0] == CONSILIARIUS);
        assert(p2Field[1] == LEGAT);
        assert(p2Field[2] == FORUM);
        assert(p2Field[3] == TEMPLUM);
        assert(p2Field[4] == SCAENICUS);
        assert(p2Field[5] == MERCATUS);
        assert(p2Field[6] == ESSEDUM);
        assert(r.getAvailableActionDice().length == 1);
        
        //activate Mercatus
        //receive 1 vp from the stockpile
        int[] args3 = {0};
        r.activateCard (6,args3);
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getVictoryPoints() == 20);
        assert(p2.getVictoryPoints() == 10);
        assert(r.getVictoryPointsStockpile () == 6);
        
        // set vp to 10 to keep the test going
        //p1.setVictoryPoints (19);
        //assert(p2.getVictoryPoints () == 10);
        
        r.endCurrentTurn ();
        assert(!r.isGameOver ());
    }
    
    /*
     * Stage 5: player 1's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Mercatus       1       Consiliarius
     *   Forum          2       Legat
     *   Templum        3       Forum
     *   Turris         4       Templum
     *   -              5       SCAENICUS
     *   -              6       Mercatus
     *   Mecator        7       ESSEDUM
     *   
     *    $0                       $5
     *    VP 20        vp 6        VP 10
     *    
     *    p1 hand : nero, consul
     *    p2 hand : SENATOR, AESCULAPINUM
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, CENTURIO,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,BASILICA
     *          
     *    discard: PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, MACHINA
     */
    public void testFive() {
        //p1 has 2 unoccupied dice disc
        assert(p1.getVictoryPoints() == 18);
        assert(r.getVictoryPointsStockpile() == 8);
        
        int dice[] = {4,6,6};
        r.setAvailableActionDice (dice);
        
        //draw basilica
        r.useActionDieForCards (4, new CardPicker() { public int pickCard(int cards[]) { return BASILICA; }} );
        assert(r.getAvailableActionDice().length == 2);
        
        int[] hand = {NERO, CONSUL, BASILICA};
        int[] p1Hand = p1.getHand();
        assert(isPermutation(p1Hand, hand));
        
        int[] discard = {MACHINA, PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, 
                         LEGIONARIUS, FORUM, CONSILIARIUS};
        int[] discardPile = r.getDiscardPile();
        assert(isPermutation(discard, discardPile));
        
        //get money
        r.useActionDieForMoney(6);
        assert(p1.getMoney() == 6);
        assert(r.getAvailableActionDice().length == 1);
        
        //lay basilica covering mercatus
        r.layCard(BASILICA, DISC1);
        
        int[] p1Field = p1.getFaceUpCards();
        assert(p1Field[0] == BASILICA);
        assert(p1Field[1] == FORUM);
        assert(p1Field[2] == TEMPLUM);
        assert(p1Field[3] == TURRIS);
        assert(p1Field[4] == NO_CARD);
        assert(p1Field[5] == NO_CARD);
        assert(p1Field[6] == MERCATOR);
        
        //mercatus is discarded
        int[] discard2 = {MACHINA, MERCATUS, PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, 
                          LEGIONARIUS, FORUM, CONSILIARIUS};
        discardPile = r.getDiscardPile();
        assert(isPermutation(discard2, discardPile));
        
        int[] hand2 = {NERO, CONSUL};
        p1Hand = p1.getHand();
        assert(isPermutation(p1Hand, hand2));
        
        //get money
        r.useActionDieForMoney(6);
        assert(p1.getMoney() == 6);
        assert(r.getAvailableActionDice().length == 0);
        
        r.endCurrentTurn ();
        assert(!r.isGameOver ());
    }
    
    
    /*
     * Stage 6: player 2's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Basilica       1       Consiliarius
     *   Forum          2       Legat
     *   Templum        3       Forum
     *   Turris         4       Templum
     *   -              5       SCAENICUS
     *   -              6       Mercatus
     *   Mecator        7       Essedum
     *   
     *    $6                       $5
     *    VP 18       vp 8         VP 10
     *    
     *    p1 hand : nero, consul
     *    p2 hand : SENATOR, AESCULAPINUM
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, CENTURIO,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,BASILICA
     *          
     *    discard: MACHINA, MERCATUS, PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, 
                   LEGIONARIUS, FORUM, CONSILIARIUS
     */
    public void testSix() {
        assert(p2.getVictoryPoints() == 10);
        assert(r.getVictoryPointsStockpile() == 8);
        
        int dice[] = {1,3,5};
        r.setAvailableActionDice (dice);
        
        //activate forum to gain 5 vp
        //NOT activate templum
        int[] args = {5};
        r.activateCard(DISC3, args);
        assert(r.getAvailableActionDice().length == 1);
        assert(p2.getVictoryPoints() == 15);
        assert(r.getVictoryPointsStockpile() == 3);
        
        //lay AESCULAPINUM, covering Consiliarius
        r.layCard(AESCULAPINUM, DISC1);
        assert(p2.getMoney() == 0);
        
        int[] hand = {SENATOR};
        int[] p2Hand = p2.getHand();
        assert(isPermutation(hand, p2Hand));
        
        int[] p2Field = p2.getFaceUpCards();
        assert(p2Field[0] == AESCULAPINUM);
        assert(p2Field[1] == LEGAT);
        assert(p2Field[2] == FORUM);
        assert(p2Field[3] == TEMPLUM);
        assert(p2Field[4] == SCAENICUS);
        assert(p2Field[5] == MERCATUS);
        assert(p2Field[6] == ESSEDUM);
        
        int[] discard = {MACHINA, MERCATUS, PRAETORIANUS,CENTURIO, ARCHITECTUS,BASILICA, 
                LEGIONARIUS, FORUM, CONSILIARIUS, CONSILIARIUS};
        assert(isPermutation(r.getDiscardPile(), discard)):r.getDiscardPile().length;
        
        //activate AESCULAPINUM for praetorianus
        int[] args2 = {PRAETORIANUS};
        r.activateCard(DISC1, args2);
        assert(r.getAvailableActionDice().length == 0);
        
        int[] discard2 = {MACHINA, MERCATUS, CENTURIO, ARCHITECTUS,BASILICA, 
                LEGIONARIUS, FORUM, CONSILIARIUS, CONSILIARIUS};
        assert(isPermutation(r.getDiscardPile(), discard2));
        
        int[] hand2 = {SENATOR, PRAETORIANUS};
        assert(isPermutation(p2.getHand(), hand2));
        
        r.endCurrentTurn ();
        assert(!r.isGameOver ());
    }
    
    /*
     * Stage 7: player 1's turn
     * 
     *     p1       dice disc     p2
     *  
     *   Basilica       1       Consiliarius
     *   Forum          2       Legat
     *   Templum        3       Forum
     *   Turris         4       Templum
     *   -              5       SCAENICUS
     *   -              6       Mercatus
     *   Mecator        7       Essedum
     *   
     *    $6                       $0
     *    VP 18       vp 3         VP 15
     *    
     *    p1 hand : nero, consul
     *    p2 hand : SENATOR, PRAETORIANUS
     *    
     *    draw : FORUM, MERCATOR, TEMPLUM, TURRIS, CENTURIO,TURRIS,ARCHITECTUS,
     *          LEGAT,PRAETORIANUS,LEGIONARIUS,FORUM,CONSILIARIUS,BASILICA
     *          
     *    discard: MACHINA, MERCATUS, CENTURIO, ARCHITECTUS,BASILICA, 
     *          LEGIONARIUS, FORUM, CONSILIARIUS, CONSILIARIUS
     */
    public void testSeven() {
        //has 2 unoccupied dice disc
        assert(p1.getVictoryPoints() == 16);
        assert(r.getVictoryPointsStockpile() == 5);
        
        int dice[] = {2,2,2};
        r.setAvailableActionDice (dice);
        
        //activate forum to gain 2 vp
        //activate templum to gain 2 vp
        //gain 2 vp from basilica
        //receive total vp = 6
        int[] args = {2, 2};
        r.activateCard(DISC2, args);
        assert(r.getAvailableActionDice().length == 0);
        assert(p1.getVictoryPoints() == 22);
        assert(r.getVictoryPointsStockpile() <= 0);
        
        /*note: In the rule page -4-
         * "if there aren't enough vp left in the stock pile for the
         * last payout, the missing points are credited to the respective player" 
         */ 
        
        
        r.endCurrentTurn ();
        assert(r.isGameOver ());
    }
    
    public void testZero() {
        RomaEngine r = new Roma();

        int[] deck = {
                CONSUL,
                GLADIATOR,
                MERCATOR,
                SCAENICUS,
                LEGIONARIUS,
                GLADIATOR,
                CONSILIARIUS,
                CONSILIARIUS,
                PRAETORIANUS,
                HARUSPEX,
                FORUM,
                ESSEDUM,
                SENATOR,
                CENTURIO,
                SENATOR,
                TRIBUNUS_PLEBIS,
                ONAGER,
                LEGAT,
                LEGIONARIUS,
                ONAGER,
                VELITES,
                MACHINA,
                LEGIONARIUS,
                FORUM,
                SICARIUS,
                MACHINA,
                PRAETORIANUS,
                FORUM,
                TURRIS,
                TRIBUNUS_PLEBIS,
                FORUM
                     };
        r.setDrawPile (deck);
        TestablePlayer p1 = r.getPlayer (1);
        TestablePlayer p2 = r.getPlayer (2);
        p1.setVictoryPoints (7);
        p2.setVictoryPoints (13);
        r.setCurrentPlayer(PLAYER1);

        // basilica | forum | templum | .....

        int dice1[] = {6,6,6};
        r.setAvailableActionDice(dice1);
        int hand1[] = {BASILICA, FORUM, TEMPLUM, MERCATOR};
        p1.setHand(hand1);
        p1.setMoney(20);
        r.layCard (BASILICA, 5);
        r.layCard (FORUM, 6);
        r.layCard (TEMPLUM, 7);
        r.layCard (MERCATOR, 1);

        //activate forum and templum to gain 12 vp
        //receive 2 more vp from basilica
        int args[] = {6, 6};
        r.activateCard (6,args);
        assert(p1.getVictoryPoints() == (21));
        assert(r.getVictoryPointsStockpile() == 2);
        
        r.endCurrentTurn();
    }
}


