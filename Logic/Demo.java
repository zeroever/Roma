package Logic;

import java.util.Scanner;
import TestCard.*;

/**
 * Demonstration of card activation
 * @author KaKong and JarupatJisarojito
 * Tutorial: Mon13Oboe
 */

public class Demo {
	
	
	public static void run (){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Select card to demo its ability");
		System.out.println("1. Sicarius");
		System.out.println("2. Centurio");
		System.out.println("3. Tribunus Plebis");
		System.out.println("4. Essedum");
		System.out.println("5. Velites");
		System.out.println("6. Senator");
		System.out.println("7. Scaenicus");
		System.out.println("8. Haruspex");
		System.out.println("9. Consul");
		System.out.println("10. Consiliarius");
		System.out.println("11. Gladiator");
		System.out.println("12. Mercator");
		System.out.println("13. Machina");
		System.out.println("14. Legionarius");
		System.out.println("15. Legat");
		System.out.println("16. Nero");
		System.out.println("17. Aesculapinum");
		System.out.println("18. Onager");
		System.out.println("19. Forum");
		System.out.println("You found An Easter Egg");
		System.out.println("Enter 42 to exit this card demo");
		int actionChoice = input.nextInt();
		String[] args = null;
		
		while(actionChoice < 20) {
			if(actionChoice == 1) {
			    System.out.println("**********");
			    System.out.println("Test Sicarius");
				TestSicarius.main(args);
			} else if(actionChoice == 2) {
			    System.out.println("**********");
			    System.out.println("Test Centurio");
				TestCenturio.main(args);
			} else if(actionChoice == 3) {
			    System.out.println("**********");
                System.out.println("Test Tribunus Plebis");
				TestTribunusPlebis.main(args);
			} else if(actionChoice == 4) {
			    System.out.println("**********");
                System.out.println("Test Essedum");
				TestEssedum.main(args);
			} else if(actionChoice == 5) {
			    System.out.println("**********");
                System.out.println("Test Velites");
				TestVelites.main(args);
			} else if(actionChoice == 6) {
			    System.out.println("**********");
                System.out.println("Test Senator");
				TestSenator.main(args);
			} else if(actionChoice == 7) {
			    System.out.println("**********");
                System.out.println("Test Scaenicus");
				TestScaenicus.main(args);
			} else if(actionChoice == 8) {
			    System.out.println("**********");
                System.out.println("Test Haruspex");
				TestHaruspex.main(args);
			} else if(actionChoice == 9) {
			    System.out.println("**********");
                System.out.println("Test Consul");
                TestConsul.main(args);
            } else if(actionChoice == 10) {
                System.out.println("**********");
                System.out.println("Test Consiliarius");
                TestConsiliarius.main(args);
            } else if(actionChoice == 11) {
                System.out.println("**********");
                System.out.println("Test Gladiator");
                TestGladiator.main(args);
            } else if(actionChoice == 12) {
                System.out.println("**********");
                System.out.println("Test Mercator");
                TestMercator.main(args);
            } else if(actionChoice == 13) {
                System.out.println("**********");
                System.out.println("Test Machina");
                TestMachina.main(args);
            } else if(actionChoice == 14) {
                System.out.println("**********");
                System.out.println("Test Legionarius");
                TestLegionarius.main(args);
            } else if(actionChoice == 15) {
                System.out.println("**********");
                System.out.println("Test Legat");
                TestLegat.main(args);
            } else if(actionChoice == 16) {
                System.out.println("**********");
                System.out.println("Test Nero");
                TestNero.main(args);
            } else if(actionChoice == 17) {
                System.out.println("**********");
                System.out.println("Test Aesculapinum");
                TestAesculapinum.main(args);
            } else if(actionChoice == 18) {
                System.out.println("**********");
                System.out.println("Test Onager");
                TestOnager.main(args);
            } else if(actionChoice == 19) {
                System.out.println("**********");
                System.out.println("Test Forum");
                TestForum.main(args);
            }
			
			System.out.println();
			System.out.println("Enter 42 to exit demo");
			actionChoice = input.nextInt();
		}
	}

}
