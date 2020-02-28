import java.util.*;

public class test {
  public static void main(final String[] args) {
    final Scanner in = new Scanner(System.in);
    final Deque<Integer> deque = new ArrayDeque<>();
    final Set<Integer> set = new HashSet<>();
    final int n = in.nextInt();
    final int m = in.nextInt();

    int max = 0;
    for (int i = 0; i < n; i++) {
      final int num = in.nextInt();
      deque.add(num);
      set.add(num);
      if (deque.size() == m) {
        // set new unqiue counter
        if (set.size() > max) {
          max = set.size();
          if (max == m) {
            break;
          }
        }
        final int first = deque.remove();
        // remove first from set if not in subarray
        if (!deque.contains(first)) {
          set.remove(first);
        }
      }
    }
    System.out.println(max);
  }
}
