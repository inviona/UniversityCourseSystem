import java.util.*;

public class UniversityCourseSystem {
    LinkedList<Course> courseList = new LinkedList<>();
    Stack<RegistrationAction> undoStack = new Stack<>();

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void enrollStudent(String courseName, Student student) {
        Course course = searchCourse(courseName);
        if (course != null) {
            boolean wasWaitlisted = course.isFull();

            course.enroll(student);
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


    public void undoLastRegistration() {
        if (!undoStack.isEmpty()) {
            RegistrationAction action = undoStack.pop();

            if (action.wasWaitlisted) {
                action.course.waitlist.remove(action.student);
            } else {
                action.course.removeStudent(action.student);
                System.out.println("Undid registration for " + action.student.name + " from " + action.course.name);

                // If there are students in the waitlist, move the first one to enrolled
                if (!action.course.waitlist.isEmpty()) {
                    Student studentExample = action.course.waitlist.poll();
                    action.course.enroll(studentExample);
                    System.out.println(studentExample.name + " moved from waitlist to enrolled in " + action.course.name);
                }
            }

            System.out.println("Undid registration for " + action.student.name + " from " + action.course.name);
        } else {
            System.out.println("No actions to undo.");
        }
    }

    public Course searchCourse(String courseName) {
        Course[] courses = courseList.toArray(new Course[0]);

        //the line below is used to sort the array by name
        Arrays.sort(courses, Comparator.comparing(c -> c.name));

        int index = binarySearch(courses, courseName);
        return index >= 0 ? courses[index] : null;
    }

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

    private void quickSort(Course[] courseArray, int low, int high) {
        if (low < high) {
            int nr = partition(courseArray, low, high);
            quickSort(courseArray, low, nr - 1);
            quickSort(courseArray, nr + 1, high);
        }
    }

    private int partition(Course[] courseArray, int low, int high) {
        Course pivot = courseArray[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (courseArray[j].getEnrolledCount() > pivot.getEnrolledCount()) {
                i++;
                Course temp = courseArray[i];
                courseArray[i] = courseArray[j];
                courseArray[j] = temp;
            }
        }
        Course temp = courseArray[i + 1];
        courseArray[i + 1] = courseArray[high];
        courseArray[high] = temp;
        return i + 1;
    }

    public Course courseWithMostStudents() {
        return recursiveFindMost(courseList, 0, null);
    }

    private Course recursiveFindMost(List<Course> list, int index, Course maxCourse) {
        if (index == list.size()) return maxCourse;
        Course current = list.get(index);
        if (maxCourse == null || current.getEnrolledCount() > maxCourse.getEnrolledCount()) {
            maxCourse = current;
        }
        return recursiveFindMost(list, index + 1, maxCourse);
    }

    public static void main(String[] args) {
        UniversityCourseSystem system = new UniversityCourseSystem();
        system.addCourse(new Course("OOP", 3));
        system.addCourse(new Course("Data Structures and Algorithms", 1));
        system.addCourse(new Course("Web Programming", 2));

        system.enrollStudent("Web Programming", new Student("Inviona"));
        system.enrollStudent("Web Programming", new Student("Sindi"));
        system.enrollStudent("Web Programming", new Student("Uendi"));
        system.undoLastRegistration();

        system.enrollStudent("OOP", new Student("Juri"));




        system.enrollStudent("OOP", new Student("andi"));

        system.undoLastRegistration();

        Course biggestCourse = system.courseWithMostStudents();
        System.out.println("Course with most students: " + biggestCourse.name);
    }
}
