import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(final String[] args) {
    final int[][] arr = parseArray();
    int max = Integer.MIN_VALUE;
    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        final int sum = sumHourGlass(arr, x, y);
        if (sum > max) {
          max = sum;
        }
      }
    }
    System.out.println(max);
    scanner.close();
  }

  private static int sumHourGlass(final int[][] arr, final int x, final int y) {
    int sum = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == 1 && j != 1) {
          continue;
        } else {
          sum += arr[i + x][j + y];
        }
      }
    }
    return sum;
  }

  private static int[][] parseArray() {
    final int[][] arr = new int[6][6];
    for (int i = 0; i < 6; i++) {
      final String[] arrRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
      for (int j = 0; j < 6; j++) {
        final int arrItem = Integer.parseInt(arrRowItems[j]);
        arr[i][j] = arrItem;
      }
    }
    return arr;
  }
}
