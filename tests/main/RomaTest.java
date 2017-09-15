package tests.main;

import java.util.Arrays;

public abstract class RomaTest {
   public boolean isPermutation (int[] a, int[] b) {
      int[] aCopy = a.clone ();
      int[] bCopy = b.clone ();
      Arrays.sort (aCopy);
      Arrays.sort (bCopy);
      return Arrays.equals (aCopy, bCopy);
   }
   public abstract void run();
   public abstract String toString();
}
