import java.util.*;

/**
 * UniversityCourseSystem manages course registrations, enrollments, and related operations
 * using various data structures to handle course information and registration actions.
 */
public class UniversityCourseSystem {
    // Main list to store all courses
    LinkedList<Course> courseList = new LinkedList<>();
    // Stack to store registration actions for undo functionality
    Stack<RegistrationAction> undoStack = new Stack<>();

    /**
     * Adds a new course to the system.
     */
    public void addCourse(Course course) {
        courseList.add(course);
    }

    /**
     * Enrolls a student in a course or adds them to the waitlist if the course is full.
     * Records the action for potential undo operations.
     *
     */
    public void enrollStudent(String courseName, Student student) {
        Course course = searchCourse(courseName);
        if (course != null) {
            // Check if course is full before enrollment attempt
            boolean wasWaitlisted = course.isFull();

            course.enroll(student);
            // Store enrollment action for potential undo
            undoStack.push(new RegistrationAction(course, student, wasWaitlisted));

            if (wasWaitlisted) {
                System.out.println(student.name + " is waitlisted for " + course.name);
            } else {
                System.out.println(student.name + " is enrolled in " + course.name);
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    /**
     * Undoes the last registration action (enrollment or waitlisting).
     * If a student is removed from an enrolled spot and there are waitlisted students,
     * the first waitlisted student is moved to the enrolled status.
     */
    public void undoLastRegistration() {
        if (!undoStack.isEmpty()) {
            RegistrationAction action = undoStack.pop();

            if (action.wasWaitlisted) {
                action.course.waitlist.remove(action.student);
                System.out.println("Undid registration for " + action.student.name + " from waitlist in " + action.course.name);
            } else {
                action.course.removeStudent(action.student);
                System.out.println("Undid registration for " + action.student.name + " from " + action.course.name);

                // Promote first waitlisted student to enrolled status if available
                if (!action.course.waitlist.isEmpty()) {
                    Student studentExample = action.course.waitlist.poll();
                    action.course.enroll(studentExample);
                    System.out.println(studentExample.name + " moved from waitlist to enrolled in " + action.course.name);
                }
            }

        } else {
            System.out.println("No actions to undo.");
        }
    }

    /**
     * Searches for a course by name using binary search on a sorted array.
     */
    public Course searchCourse(String courseName) {
        Course[] courses = courseList.toArray(new Course[0]);

        // Sort courses by name for binary search
        Arrays.sort(courses, Comparator.comparing(c -> c.name));

        int index = binarySearch(courses, courseName);
        if (index >= 0) {
            return courses[index];
        } else {
            return null;
        }
    }

    /**
     * Performs binary search on a sorted array of courses to find a course by name.
     * @return Index of the found course or -1 if not found
     */
    private int binarySearch(Course[] courses, String courseName) {
        int left = 0, right = courses.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = courses[mid].name.compareTo(courseName);
            if (cmp == 0) return mid;
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    /**
     * Sorts courses using quicksort algorithm based on enrolled student count.
     *
     * @param courseArray Array of courses to sort
     * @param low Starting index
     * @param high Ending index
     */
    public void quickSort(Course[] courseArray, int low, int high) {
        if (low < high) {
            int nr = partition(courseArray, low, high);
            quickSort(courseArray, low, nr - 1);
            quickSort(courseArray, nr + 1, high);
        }
    }

    /**
     * Helper method for quicksort that partitions the array.
     * Arranges elements so that courses with more students come before those with fewer.
     *
     * @param courseArray Array of courses
     * @param low Starting index
     * @param high Ending index
     */
    private int partition(Course[] courseArray, int low, int high) {
        Course pivot = courseArray[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            // Sort in descending order of enrolled count
            if (courseArray[j].getEnrolledCount() > pivot.getEnrolledCount()) {
                i++;
                // Swap courses
                Course temp = courseArray[i];
                courseArray[i] = courseArray[j];
                courseArray[j] = temp;
            }
        }
        // Place pivot in correct position
        Course temp = courseArray[i + 1];
        courseArray[i + 1] = courseArray[high];
        courseArray[high] = temp;
        return i + 1;
    }

    /**
     * Finds the course with the highest number of enrolled students using recursion.
     */
    public Course courseWithMostStudents() {
        return recursiveFindMost(courseList, 0, null);
    }

    /**
     * Recursive helper method to find the course with the most students.
     * @param maxCourse Current course with maximum enrollment
     */
    private Course recursiveFindMost(List<Course> list, int index, Course maxCourse) {
        // Base case: reached the end of the list
        if (index == list.size()) return maxCourse;

        Course current = list.get(index);
        // Update maxCourse if current course has more students
        if (maxCourse == null || current.getEnrolledCount() > maxCourse.getEnrolledCount()) {
            maxCourse = current;
        }
        // Recursive call with next index
        return recursiveFindMost(list, index + 1, maxCourse);
    }

    /**
     * Getter for the course list.
     */
    public LinkedList<Course> getCourseList() {
        return courseList;
    }
}