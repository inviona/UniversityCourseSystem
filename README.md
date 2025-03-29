# UniversityCourseSystem

📚 University Course Registration System

Welcome to the University Course Registration System, a structured and efficient solution for managing university course enrollments, waitlists, and administrative corrections.

🎯 Features

1- Linked List Implementation – Courses are stored in a linked list for dynamic management.<br>
2- Waitlist Management – A FIFO Queue ensures students are enrolled in the order they apply when a spot becomes available.<br>
3- Undo Functionality – A Stack allows administrators to revert the last registration action if needed.<br>
4- Efficient Searching & Sorting – Uses Binary Search for quick course lookup and Quick Sort for organizing courses based on enrollment numbers.<br>
5- Student Enrollment Analysis – A recursive function identifies the course with the highest number of enrolled students.<br>

🚀 How It Works

Course Management – Administrators can add courses with a specified capacity.<br>
Student Enrollment – Students enroll in courses, and if a course reaches its capacity, they are placed on a waitlist.<br>
Undo Last Registration – If a registration needs to be reverted, the last enrolled student is removed, and if applicable, a waitlisted student is promoted.<br>
Course Lookup & Organization – Courses can be searched efficiently and sorted based on the number of enrolled students.<br>
Finding the Most Popular Course – The system identifies which course has the highest enrollment.<br>

🛠️ Installation & Usage

1- Clone the repository and go to its directory. Example: <br>
git clone https://github.com/yourusername/UniversityCourseSystem.git<br>
cd UniversityCourseSystem<br>
2- Compile and run the program:<br>
javac *.java<br>
java UniversityCourseSystem<br>

Thank you for the attention and I hope you enjoy it!
