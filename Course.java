import java.util.*;

public class Course {
    String name;
    int capacity;
    ArrayList<Student> enrolled;
    Queue<Student> waitlist;

    public Course(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.enrolled = new ArrayList<>();
        this.waitlist = new LinkedList<>();
    }

    public boolean isFull() {
        return enrolled.size() >= capacity;
    }

    public void enroll(Student s) {
        if (!isFull()) {
            enrolled.add(s);
        } else {
            waitlist.add(s);
        }
    }

    public void removeStudent(Student s) {
        enrolled.remove(s);
        if (!waitlist.isEmpty()) {
            enrolled.add(waitlist.poll());
        }
    }

    public int getEnrolledCount() {
        return enrolled.size();
    }
}
