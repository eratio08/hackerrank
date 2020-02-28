import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Create the Student and Priorities classes here.
 */
class Student implements Comparable<Student> {
  private final int id;
  private final String name;
  private final double cgpa;

  public Student(final int id, final String name, final double cgpa) {
    this.id = id;
    this.name = name;
    this.cgpa = cgpa;
  }

  public int getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getCGPA() {
    return cgpa;
  }

  @Override
  public int compareTo(final Student other) {
    final int cgpaOrd = Double.compare(other.getCGPA(), this.getCGPA());
    if (cgpaOrd != 0) {
      return cgpaOrd;
    }
    final int lexOrd = this.getName().compareTo(other.getName());
    if (lexOrd != 0) {
      return lexOrd;
    }
    return Integer.compare(this.getID(), other.getID());
  }

  @Override
  public String toString() {
    return String.format("Student{name=%s,cgpq=%.1f,id=%s}", name, cgpa, id);
  }
}

class Priorities {

  private final java.util.PriorityQueue<Student> queue = new java.util.PriorityQueue<>();

  public List<Student> getStudents(final List<String> events) {
    for (String event : events) {
      if ("SERVED".equals(event)) {
        queue.poll();
      } else {
        final String[] line = event.split(" ");
        queue.add(new Student(Integer.parseInt(line[3]), line[1], Double.parseDouble(line[2])));
      }
    }
    return queueToList();
  }

  private List<Student> queueToList() {
    final Student[] queArr = queue.toArray(new Student[0]);
    java.util.Arrays.sort(queArr);
    return java.util.Arrays.asList(queArr);
  }
}

public class Solution {
  private final static Scanner scan = new Scanner(System.in);
  private final static Priorities priorities = new Priorities();

  public static void main(String[] args) {
    int totalEvents = Integer.parseInt(scan.nextLine());
    List<String> events = new ArrayList<>();

    while (totalEvents-- != 0) {
      String event = scan.nextLine();
      events.add(event);
    }

    List<Student> students = priorities.getStudents(events);

    if (students.isEmpty()) {
      System.out.println("EMPTY");
    } else {
      for (Student st : students) {
        System.out.println(st.getName());
      }
    }
  }
}