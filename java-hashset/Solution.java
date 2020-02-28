import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    final Scanner s = new Scanner(System.in);
    final int t = s.nextInt();
    final String[] pair_left = new String[t];
    final String[] pair_right = new String[t];

    for (int i = 0; i < t; i++) {
      pair_left[i] = s.next();
      pair_right[i] = s.next();
    }

    // Write your code here
    final Set<List<String>> unique = new HashSet<>(t);

    for (int i = 0; i < t; i++) {
      final List<String> pair = Arrays.asList(pair_left[i], pair_right[i]);
      unique.add(pair);
      System.out.println(unique.size());
    }

  }
}