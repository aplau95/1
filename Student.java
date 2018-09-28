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

	public Student(String lastName, String firstName, int grade, int classroom, int bus, float gpa){
		this.lastName = lastName;
		this.firstName = firstName;
		this.grade = grade;
		this.classroom = classroom;
		this.bus = bus;
		this.gpa = gpa;
	}
}