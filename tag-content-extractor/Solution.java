import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static Pattern p = Pattern.compile("<(.+)>([^<]+)</\\1>");

  public static void main(String[] args) {
    final Scanner in = new Scanner(System.in);
    int testCases = Integer.parseInt(in.nextLine());
    while (testCases > 0) {
      String line = in.nextLine();
      Matcher m = p.matcher(line);
      boolean matchFound = false;
      while (m.find()) {
        System.out.println(m.group(2));
        matchFound = true;
      }
      if (!matchFound) {
        System.out.println("Node");
      }
      testCases--;
    }
  }
}
