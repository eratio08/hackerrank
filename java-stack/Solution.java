import java.util.*;

class Solution {

  public static void main(final String[] argh) {
    final Scanner sc = new Scanner(System.in);
    final List<String[]> lines = new ArrayList<>();

    while (sc.hasNext()) {
      final String input = sc.next();
      if (input.equals("EOF")) {
        break;
      } else {
        lines.add(input.split(""));
      }
    }

    for (String[] line : lines) {
      System.out.println(isBalanced(line));
    }

  }

  private static boolean isBalanced(final String[] line) {
    final Stack<String> stack = new Stack<>();
    for (String s : line) {
      if (stack.size() == 0) {
        stack.push(s);
      } else {
        final String lastItem = stack.peek();
        if (isPair(s, lastItem)) {
          stack.pop();
        } else {
          stack.push(s);
        }
      }
    }
    return stack.size() == 0;
  }

  private static boolean isPair(final String close, final String open) {
    final boolean isRound = ")".equals(close) && "(".equals(open);
    final boolean isSquare = "]".equals(close) && "[".equals(open);
    final boolean isCurly = "}".equals(close) && "{".equals(open);
    return isRound || isSquare || isCurly;
  }
}
