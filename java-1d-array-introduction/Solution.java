import java.util.*;

public class Solution {

  public static void main(final String[] args) {

    final Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    // code here
    final int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      final int num = scan.nextInt();
      a[i] = num;
    }

    scan.close();

    // Prints each sequential element in array a
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }
}