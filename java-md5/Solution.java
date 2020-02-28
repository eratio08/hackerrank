import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    /*
     * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
     * class should be named Solution.
     */
    final Scanner scanner = new Scanner(System.in);
    final String word = scanner.nextLine();
    scanner.close();
    final String md5 = calcMD5(word);
    System.out.println(md5);
  }

  private static String calcMD5(final String s) {
    try {
      final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
      final byte[] digest = md.digest(s.getBytes());
      return byteArrayToHex(digest);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String byteArrayToHex(final byte[] a) {
    return new java.math.BigInteger(1, a).toString(16);
  }
}