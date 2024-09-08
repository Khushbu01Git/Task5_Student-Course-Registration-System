import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class Course {
private String courseCode;
private String title;
private String description;
private int capacity;
private String schedule;
private int enrolledStudents;
public Course(String courseCode, String title, String description, int capacity, String schedule) {
this.courseCode = courseCode;
this.title = title;
this.description = description;
this.capacity = capacity;
this.schedule = schedule;
this.enrolledStudents = 0;
}
public String getCourseCode() {
return courseCode;
}
public String getTitle() {
return title;
}
public String getDescription() {
return description;
}
public int getCapacity() {
return capacity;
}
public String getSchedule() {
return schedule;
}
public int getEnrolledStudents() {
return enrolledStudents;
}
public boolean addStudent() {
if (enrolledStudents < capacity) {
enrolledStudents++;
return true;
} else {
return false;
}
}
public boolean removeStudent() {
if (enrolledStudents > 0) {
enrolledStudents--;
return true;
} else {
return false;
}
}
public boolean hasAvailableSlots() {
return enrolledStudents < capacity;
}
}
class Student {
private String studentId;
private String name;
private ArrayList<Course> registeredCourses;
public Student(String studentId, String name) {
this.studentId = studentId;
this.name = name;
this.registeredCourses = new ArrayList<>();
}
public String getStudentId() {
return studentId;
}
public String getName() {
return name;
}
public ArrayList<Course> getRegisteredCourses() {
return registeredCourses;
}
public void registerCourse(Course course) {
if (course.hasAvailableSlots()) {
registeredCourses.add(course);
course.addStudent();
System.out.println("Successfully registered for course: " + course.getTitle());
} else {
System.out.println("Course is full. Cannot register.");
}
}
public void dropCourse(Course course) {
if (registeredCourses.contains(course)) {
registeredCourses.remove(course);
course.removeStudent();
System.out.println("Successfully dropped course: " + course.getTitle());
} else {
System.out.println("You are not registered for this course.");
}
}
}
public class CourseRegistrationSystem {
private static ArrayList<Course> courses = new ArrayList<>();
private static HashMap<String, Student> students = new HashMap<>();
private static Scanner scanner = new Scanner(System.in);
public static void main(String[] args) {
courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer
science.", 30, "Mon, Wed 10:00-11:30"));
courses.add(new Course("ENG102", "English Literature", "Explore classic and modern English literature.",
25, "Tue, Thu 14:00-15:30"));
courses.add(new Course("MATH201", "Calculus I", "Introduction to calculus concepts and techniques.", 35,
"Mon, Wed, Fri 09:00-10:00"));
students.put("S001", new Student("S001", "Alice"));
students.put("S002", new Student("S002", "Bob"));
students.put("S003", new Student("S003", "Charlie"));
mainMenu();
}
private static void mainMenu() {
int choice;
do {
System.out.println("\nMain Menu:");
System.out.println("1. Display Available Courses");
System.out.println("2. Register for a Course");
System.out.println("3. Drop a Course");
System.out.println("4. Display Student's Registered Courses");
System.out.println("5. Exit");
System.out.print("Enter your choice: ");
choice = scanner.nextInt();
switch (choice) {
case 1:
displayCourses();
break;
case 2:
registerCourse();
break;
case 3:
dropCourse();
break;
case 4:
displayStudentCourses();
break;
case 5:
System.out.println("Exiting the system. Goodbye!");
break;
default:
System.out.println("Invalid choice. Please try again.");
}
} while (choice != 5);
}
private static void displayCourses() {
System.out.println("\nAvailable Courses:");
for (Course course : courses) {
System.out.println(course.getCourseCode() + ": " + course.getTitle());
System.out.println("Description: " + course.getDescription());
System.out.println("Schedule: " + course.getSchedule());
System.out.println("Capacity: " + course.getCapacity() + " | Enrolled: " + course.getEnrolledStudents());
System.out.println("Available Slots: " + (course.getCapacity() - course.getEnrolledStudents()));
System.out.println();
}
}
private static void registerCourse() {
System.out.print("\nEnter your Student ID: ");
String studentId = scanner.next();
Student student = students.get(studentId);
if (student != null) {
System.out.print("Enter the Course Code to register: ");
String courseCode = scanner.next();
Course course = findCourseByCode(courseCode);
if (course != null) {
student.registerCourse(course);
} else {
System.out.println("Course not found.");
}
} else {
System.out.println("Student ID not found.");
}
}
private static void dropCourse() {
System.out.print("\nEnter your Student ID: ");
String studentId = scanner.next();
Student student = students.get(studentId);
if (student != null) {
System.out.print("Enter the Course Code to drop: ");
String courseCode = scanner.next();
Course course = findCourseByCode(courseCode);
if (course != null) {
student.dropCourse(course);
} else {
System.out.println("Course not found.");
}
} else {
System.out.println("Student ID not found.");
}
}
private static void displayStudentCourses() {
System.out.print("\nEnter your Student ID: ");
String studentId = scanner.next();
Student student = students.get(studentId);
if (student != null) {
System.out.println("\nRegistered Courses for " + student.getName() + ":");
for (Course course : student.getRegisteredCourses()) {
System.out.println(course.getCourseCode() + ": " + course.getTitle() + " (" + course.getSchedule() + ")");
}
} else {
System.out.println("Student ID not found.");
}
}
private static Course findCourseByCode(String courseCode) {
for (Course course : courses) {
if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
return course;
}
}
return null;
}
}