import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SchoolSearch {

	private static final String STUDENTS = "students.txt";
    private static ArrayList<Student> students = new ArrayList<>();
	
	public static void main(String[] args){

		Scanner sc;
		
		try {
			sc = new Scanner(new File(STUDENTS));
			while (sc.hasNextLine()){
				String nextLine = sc.nextLine();
				String data[] = nextLine.split(",");
                Student newStudent = new Student(data[0], data[1], Integer.valueOf(data[2]), Integer.valueOf(data[3]),
                        Integer.valueOf(data[4]), Float.valueOf(data[5]), data[6], data[7]);
                students.add(newStudent);
			}
			System.out.println("Students in Database: " + students.size());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}


		Scanner scanner = new Scanner(System.in);
		while (true){
            System.out.print("Student Database Search - Enter Command > ");
            String command = scanner.nextLine();
            if (command.equals("Q") || command.equals("Quit")){
                break;
            }
            processCommand(command);
            System.out.println();
        }

	}

    private static void processCommand(String userInput) {
	    Scanner tkn = new Scanner(userInput);
	    if (tkn.hasNext()){
	        String cmd =  tkn.next();
	        if (cmd.equals("S:") || cmd.equals("Student:")){
	            processStudent(tkn);
            } else if (cmd.equals("T:") || cmd.equals("Teacher:")) {
	            processTeacher(tkn);
            } else if (cmd.equals("G:") || cmd.equals("Grade:")) {
	            processGrade(tkn);
            } else if (cmd.equals("B:") || cmd.equals("Bus:")) {
	            processBus(tkn);
            } else if (cmd.equals("A:") || cmd.equals("Average:")) {
	            processAverage(tkn);
            } else if (cmd.equals("I") || cmd.equals("Info")) {
	            processInfo();
            }
            else {
                System.out.println("Invalid Command");
            }
        } else { invalidCommand(); }
    }

    private static void processInfo() {
	    int gradePopulation[] = new int[7];
	    for (Student stu : students) {
	        switch (stu.grade) {
                case 0:
                    gradePopulation[0]++;
                    break;
                case 1:
                    gradePopulation[1]++;
                    break;
                case 2:
                    gradePopulation[2]++;
                    break;
                case 3:
                    gradePopulation[3]++;
                    break;
                case 4:
                    gradePopulation[4]++;
                    break;
                case 5:
                    gradePopulation[5]++;
                    break;
                case 6:
                    gradePopulation[6]++;
                    break;
                default:
                    System.out.println("Grade outside of range (0-6)");
                    break;
            }
        }
        for (int i = 0; i < gradePopulation.length; i++)
            System.out.println("Grade " + i + " : " + gradePopulation[i]);
    }

    private static void processAverage(Scanner tkn) {
	    if (tkn.hasNext()) {
	        int grade = tkn.nextInt();
	        int cnt = 0;
	        float gpaTotal = 0f;
	        for (Student stu : students) {
	            if (stu.grade == grade) {
	                gpaTotal = gpaTotal + stu.gpa;
	                cnt++;
                }
            }
            float average = gpaTotal/cnt;
	        System.out.println("Grade: " + grade + " - Average GPA: " + average);
        } else { System.out.println("Invalid Command: A[verage]: <number>"); }
    }

    private static void processBus(Scanner tkn) {
	    if(tkn.hasNext()){
	        int route = tkn.nextInt();
	        for (Student stu : students) {
	            if (stu.bus == route) {
	                System.out.println(stu.lastName + "," + stu.firstName + "," + stu.grade + "," + stu.classroom);
                }
            }
        } else { System.out.println("Invalid Command: B[us]: <Number>"); }
    }

    private static void processGrade(Scanner tkn) {
	    if (tkn.hasNext()){
	        int grade = tkn.nextInt();
	        if (tkn.hasNext()) {
	           String word = tkn.next();
	           if(word.equals("H") || word.equals("High")){
	               findHighest(grade);
               } else if (word.equals("L") || word.equals("Low")) {
	               findLowest(grade);
               } else { System.out.println("Invalid Command: G[rade]: <number> [[H[igh]] | [L[ow]]]"); }

            } else {
                for (Student stu : students) {
                    if (stu.grade == grade) {
                        System.out.println(stu.lastName + "," + stu.firstName);
                    }
                }
            }
        } else { System.out.println("Invalid Command: G[rade]: <number>"); }
    }

    private static void findHighest(int grade) {
	    ArrayList<Student> results = new ArrayList<>();
	    for (Student stu : students) {
	        if (stu.grade == grade) {
	            results.add(stu);
            }
        }
        Collections.sort(results, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
               Float s1GPA = new Float(s1.gpa);
               Float s2GPA = new Float(s2.gpa);
               return s1GPA.compareTo(s2GPA) * -1; //Reverses Sorting to Descending (Highest First)
            }
        });
	    for (Student highest : results) {
            System.out.println(highest.lastName + "," + highest.firstName + "," + highest.gpa + ","
                    + highest.teacherLastName + "," + highest.teacherFirstName + "," + highest.bus);
        }
	    if (results.size() > 0) {
	        Student highest = results.get(0);
	        System.out.println(highest.lastName + "," + highest.firstName + "," + highest.gpa + ","
                    + highest.teacherLastName + "," + highest.teacherFirstName + "," + highest.bus);
        }
    }

    private static void findLowest(int grade) {
	    ArrayList<Student> results = new ArrayList<>();
	    for (Student stu : students) {
	        if (stu.grade == grade) {
	            results.add(stu);
            }
        }
        Collections.sort(results, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                Float s1GPA = new Float(s1.gpa);
                Float s2GPA = new Float(s2.gpa);
                return s1GPA.compareTo(s2GPA);
            }
        });
        for (Student highest : results) {
            System.out.println(highest.lastName + "," + highest.firstName + "," + highest.gpa + ","
                    + highest.teacherLastName + "," + highest.teacherFirstName + "," + highest.bus);
        }
        if (results.size() > 0) {
            Student highest = results.get(0);
            System.out.println("Lowest: " + highest.lastName + "," + highest.firstName + "," + highest.gpa + ","
                    + highest.teacherLastName + "," + highest.teacherFirstName + "," + highest.bus);
        }
    }

    private static void processTeacher(Scanner tkn) {
	    if (tkn.hasNext()){
	        String lastName = tkn.next();
	        for (Student stu : students) {
	            if(stu.teacherLastName.equals(lastName)) {
	                System.out.println(stu.lastName + "," + stu.firstName);
                }
            }
        } else { System.out.println("Invalid Command: T[eacher]: <lastname>"); }
    }

    private static void processStudent(Scanner tkn) {
	    if (tkn.hasNext()){

	        String lastName = tkn.next();
	        if(tkn.hasNext()){

	            String bus = tkn.next();
	            if(bus.equals("B") || bus.equals("Bus")){
                    for (Student stu : students){
                        if (stu.lastName.equals(lastName)){
                            System.out.println(stu.lastName + "," + stu.firstName + "," + stu.bus);
                        }
                    }

                } else {
                    System.out.println("Invalid Command: S[tudent]: <lastname> [B[us]]");
                }

            } else {
	            for (Student stu : students){
	                if (stu.lastName.equals(lastName)){
	                    System.out.println(stu.lastName + "," + stu.firstName + "," + stu.grade + "," + stu.classroom +
                                "," + stu.teacherLastName + "," + stu.teacherFirstName);
                    }
                }

            }
        } else {
	        System.out.println("Invalid Command: S[tudent]: <lastname> [B[us]]");
        }
    }

    private static void invalidCommand() {
        System.out.println("Invalid Command");
    }
}