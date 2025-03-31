import java.util.*;
/**
 * Course represents an academic course with enrollment management capabilities,
 * including capacity limits and waitlist functionality.
 */
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

    /**
     * Checks if the course has reached its maximum enrollment capacity.
     * @return true if course is at capacity, false otherwise
     */
    public boolean isFull() {
        return enrolled.size() >= capacity;
    }

    /**
     * Enrolls a student in the course or adds them to the waitlist if full.
     *
     * @param s The student to enroll
     */
    public void enroll(Student s) {
        if (!isFull()) {
            // Add student directly if space is available
            enrolled.add(s);
        } else {
            // Add to waitlist if course is at capacity
            waitlist.add(s);
        }
    }

    /**
     * Removes a student from the enrolled list and promotes the first
     * waitlisted student if any are waiting.
     * @param s The student to remove
     */
    public void removeStudent(Student s) {
        enrolled.remove(s);
        // Automatically promote first waitlisted student when a spot opens
        if (!waitlist.isEmpty()) {
            enrolled.add(waitlist.poll());
        }
    }

    /**
     * Gets the current number of enrolled students.
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }
}