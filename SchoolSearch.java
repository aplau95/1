import java.io.File;
import java.util.Scanner;

public class SchoolSearch {

	private static final String STUDENTS = "students.txt";
	
	public static void main(String[] args){

		Scanner sc;
		
		try {
			sc = new Scanner(new File(STUDENTS));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println("Hello World");
	}
}