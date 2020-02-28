import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface FamilyBudget {
  String userRole();

  int budgetLimit();
}

class FamilyMember {

  @FamilyBudget(userRole = "SENIOR", budgetLimit = 100)
  public void seniorMember(int budget, int moneySpend) {
    System.out.println("Senior Member");
    System.out.println("Spend: " + moneySpend);
    System.out.println("Budget Left: " + (budget - moneySpend));
  }

  @FamilyBudget(userRole = "JUNIOR", budgetLimit = 50)
  public void juniorUser(int budget, int moneySpend) {
    System.out.println("Junior Member");
    System.out.println("Spend: " + moneySpend);
    System.out.println("Budget Left: " + (budget - moneySpend));
  }
}

public class Solution {

  public static void main(final String[] args) {
    final Scanner in = new Scanner(System.in);
    int testCases = Integer.parseInt(in.nextLine());

    while (testCases > 0) {
      final String role = in.next();
      final int spend = in.nextInt();

      try {

        final Class<?> annotatedClass = FamilyMember.class;
        final Method[] methods = annotatedClass.getMethods();

        for (Method method : methods) {
          // find annotated method
          if (method.isAnnotationPresent(FamilyBudget.class)) {
            final FamilyBudget family = method.getAnnotation(FamilyBudget.class);
            final String userRole = family.userRole();
            final int budgetLimit = family.budgetLimit();

            if (userRole.equals(role)) {

              if (spend <= budgetLimit) {
                method.invoke(FamilyMember.class.getDeclaredConstructor().newInstance(), budgetLimit, spend);
              } else {
                System.out.println("Budget Limit Over");
              }

            }
          }
        }

      } catch (final Exception e) {
        e.printStackTrace();
      }
      testCases--;
    }
  }

}
