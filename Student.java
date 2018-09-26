/* CSC 365-03
   Lab1
   Andrew Lau and Ryan Kirkpatrick
*/

public class Student {
		
	public String lastName;
	public String firstName;
	public int grade;
	public int classroom;
	public int bus;
	public float gpa;
	public String teacherLastName;
	public String teacherFirstName;

	public Student(String lastName, String firstName, int grade, int classroom, int bus, float gpa, String teacherLastName,
		String teacherFirstName){
		this.lastName = lastName;
		this.firstName = firstName;
		this.grade = grade;
		this.classroom = classroom;
		this.bus = bus;
		this.gpa = gpa;
		this.teacherLastName = teacherLastName;
		this.teacherFirstName = teacherFirstName;
	}

	public void printStudent() {
		System.out.println(lastName + ", " + firstName);
		System.out.println("Grade: " + grade);
		System.out.println("Class: " + classroom + " Bus: " + bus + " GPA: " + gpa);
		System.out.println("Teacher: " + teacherLastName + ", " + teacherFirstName + "\n");
	}

}