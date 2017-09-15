package tests.main;
/*
 * Code based on design02 acceptance tests by rupert shuttleworth
 */

import static java.lang.System.out;
import tests.unitTests.*;
import tests.acceptanceTests.*;
import tests.unitTests.gg.*;


public class RomaTester {
	public static final RomaTest[] tests = {
		new MercatorTest(),
		new SenatorTest(),
        new TestCenturio(),
        new TestNero3(),
		new TestConsul(),
		new TestLegat(),
		new TestPraetorianus(),
		new TestScaenicus(),
		new TestGetFaceUpCards(),

        new RomaRockstarsTest(), 
        new AuronNew4(),
        new Mon13Oboe(),
        new LucMaxVitoTests(),

		new ProperTest(),
		new SimpleGameOverTest(),
		new Thu09BellAcceptanceTest(),
		new Mon11TubaTests(),
		new EpochNew(),
		new FullySickBadassKebabTest(),

//        // uncomment these lines for unstable tests
//        new Mrglrglmrglmrrrlggg(),
//        new Wed09OboeTests(),

//      FINAL VERSION EDIT
//		since these tests hav been confirmed by a large number of people
//		they will be included in the final acceptance tests
		
        new GGTestAesculapinum2(),
        new GGTestAesculapinum(),
        new GGTestArchitectus(),
        new GGTestBasilica(),
        new GGTestCenturio(),
        new GGTestConsul(),
        new GGTestEssedum(),
        new GGTestForum(),
        new GGTestGladiator(),
        new GGTestHaruspex(),
        new GGTestLegat(),
        new GGTestLegionarius(),
        new GGTestMercatus(),
        new GGTestNero(),
        new GGTestOnager(),
        new GGTestPraetorianus(),
        new GGTestSenator(),
        new GGTestSicarius(),
        new GGTestTemplum(),
        new GGTestTribunusPlebis(),
        new GGTestTurris(),
        new GGTestVelites(),
 
    };

    public static void main (String[] args) {
        out.println ("Roma Acceptance tests starting...");

        boolean assertionsEnabled = false;
        try {
            assert false;
        } catch (AssertionError e) {
            assertionsEnabled = true;
        }

        boolean failed = false;

        if (!assertionsEnabled) {
            out.println ("Pleased enable assertions, run with java -ea");
            failed = true;
        }
        for (int i = 0; i < tests.length && !failed; i++) {
            RomaTest test = tests[i];
            
            out.println ("Running " + test);
            try {
                test.run();
            } catch (Throwable t) {
                out.println ("TEST FAILED");
                out.println ("Printing stack trace...");
                t.printStackTrace ();
                failed = true;
            }
        }
        if (failed) {
            out.println ("Acceptance tests failed.");
        } else {
            out.println("--> ALL tests passed. You are AWESOME!<--");
        }
    }
}
