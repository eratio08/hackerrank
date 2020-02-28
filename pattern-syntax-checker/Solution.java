import java.util.Scanner;
import java.util.regex.*;

public class Solution {
  private final static String VALID = "Valid";
  private final static String INVALID = "Invalid";

  public static void main(String[] args) {
    final Scanner in = new Scanner(System.in);
    int testCases = Integer.parseInt(in.nextLine());
    for (int i = 0; i < testCases; i++) {
      final String pattern = in.nextLine();
      try {
        final Pattern p = Pattern.compile(pattern);
        System.out.println(VALID);
      } catch (Exception e) {
        System.out.println(INVALID);
      }
    }
  }
}