public class TestUniversityCourseSystem {
    public static void main(String[] args) {
        UniversityCourseSystem system = new UniversityCourseSystem();

        // Test 1: LinkedList storage of courses
        System.out.println("Test 1: Adding courses to LinkedList");
        system.addCourse(new Course("OOP", 2));
        system.addCourse(new Course("Data Structures", 1));
        system.addCourse(new Course("Algorithms", 3));

        // Test 2: Binary search
        System.out.println("\nTest 2: Binary search for courses");
        Course course1 = system.searchCourse("OOP");
        System.out.println("Search for OOP: " + (course1 != null ? "Found" : "Not found"));
        Course course2 = system.searchCourse("Physics");
        System.out.println("Search for Physics: " + (course2 != null ? "Found" : "Not found"));

        // Test 3: Queue for waitlist (FIFO)
        System.out.println("\nTest 3: Queue for waitlist");
        system.enrollStudent("Data Structures", new Student("Alice"));
        system.enrollStudent("Data Structures", new Student("Bob"));    // Should be waitlisted
        system.enrollStudent("Data Structures", new Student("Charlie")); // Should be waitlisted

        // Test 4: Stack for undo
        System.out.println("\nTest 4: Stack for undo operations");
        system.undoLastRegistration(); // Should remove Charlie from waitlist
        system.undoLastRegistration(); // Should remove Bob from waitlist

        // Test 5: Quick sort
        System.out.println("\nTest 5: Quick sort by enrollment count");
        system.enrollStudent("OOP", new Student("David"));
        system.enrollStudent("OOP", new Student("Emma"));
        system.enrollStudent("Algorithms", new Student("Frank"));

        Course[] courses = system.getCourseList().toArray(new Course[0]);
        system.quickSort(courses, 0, courses.length - 1);

        System.out.println("Courses sorted by enrollment (descending):");
        for (Course c : courses) {
            System.out.println(c.name + ": " + c.getEnrolledCount() + " students");
        }

        // Test 6: Recursive function
        System.out.println("\nTest 6: Recursive function to find course with most students");
        Course mostPopular = system.courseWithMostStudents();
        System.out.println("Course with most students: " + mostPopular.name);
    }
}