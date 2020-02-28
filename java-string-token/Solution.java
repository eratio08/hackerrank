import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String s = scan.nextLine();
    // Write your code here.
    final int length = s.length();
    if (length < 1 || length > 400000) {
      return;
    }
    final String[] words = s.trim().replaceAll("[ !,?._'@]+", " ").replaceAll("\\s+", " ").split(" ");
    System.out.println(words.length);
    for (final String word : words) {
      System.out.println(word);
    }
    scan.close();
  }
}
