import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    /*
     * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
     * class should be named Solution.
     */
    final Scanner scanner = new Scanner(System.in);
    final int n = scanner.nextInt(); // length
    final int m = scanner.nextInt(); // operations
    final BitSet b1 = new BitSet(n);
    final BitSet b2 = new BitSet(n);
    for (int i = 0; i < m; i++) {
      final String operation = scanner.next();
      final int setNr = scanner.nextInt();
      final int setNrOrIndex = scanner.nextInt();
      switch (operation) {
        case "AND": {
          selectBitSet(b1, b2, setNr).and(selectBitSet(b1, b2, setNrOrIndex));
          break;
        }
        case "OR": {
          selectBitSet(b1, b2, setNr).or(selectBitSet(b1, b2, setNrOrIndex));
          break;
        }
        case "XOR": {
          selectBitSet(b1, b2, setNr).xor(selectBitSet(b1, b2, setNrOrIndex));
          break;
        }
        case "FLIP": {
          selectBitSet(b1, b2, setNr).flip(setNrOrIndex);
          break;
        }
        case "SET": {
          selectBitSet(b1, b2, setNr).set(setNrOrIndex);
        }
      }
      final long sum1 = b1.stream().count();
      final long sum2 = b2.stream().count();
      System.out.format("%d %d\n", sum1, sum2);
    }

  }

  private static BitSet selectBitSet(final BitSet b1, final BitSet b2, final int nr) {
    return nr == 1 ? b1 : b2;
  }

}