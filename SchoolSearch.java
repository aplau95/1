/* CSC 365-03
   Lab1
   Andrew Lau and Ryan Kirkpatrick
*/

import java.io.File;
import java.util.*;

public class schoolsearch {

    private static final String STUDENTS = "list.txt";
    private static final String TEACHERS = "teachers.txt";
    private static ArrayList<Student> studentsList = new ArrayList<>();
    private static ArrayList<Teacher> teachersList = new ArrayList<>();
	
	public static void main(String[] args){

        Scanner sc;
        Scanner sc2;
		
		try {
			sc = new Scanner(new File(STUDENTS));
			while (sc.hasNextLine()){
				String nextLine = sc.nextLine();
				String data[] = nextLine.split(",");
                Student newStudent = new Student(data[0], data[1], Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4]), Float.valueOf(data[5]));
                studentsList.add(newStudent);
            }
            System.out.println("Students in Database: " + studentsList.size());
		} catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
        
        try {
            sc2 = new Scanner(new File(TEACHERS));
            while (sc2.hasNextLine()){
				String nextLine = sc2.nextLine();
				String data[] = nextLine.split(",");
                Teacher newTeacher = new Teacher(data[0], data[1], Integer.parseInt(data[2].trim()));
                teachersList.add(newTeacher);
            }
            System.out.println("Teachers in Database: " + teachersList.size());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }


		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.print("Student Database Search - Enter Command > ");
            String command = scanner.nextLine();
            if (command.equals("Q") || command.equals("Quit")){
                break;
            }
            processCommand(command);
            System.out.println();
        }

	}


    // Traceability: implements requirements NR4
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
            } else if (cmd.equals("GT:") || cmd.equals("GradeTeacher:")) {
                processGradeTeacher(tkn);
            } else if (cmd.equals("EN") || cmd.equals("Enrollment")) {
                classroomEnrollment();
            } else if (cmd.equals("B:") || cmd.equals("Bus:")) {
	            processBus(tkn);
            } else if (cmd.equals("A:") || cmd.equals("Average:")) {
                processAverage(tkn);
            } else if (cmd.equals("Analyze:")) {
                analyze(tkn);
            } else if (cmd.equals("I") || cmd.equals("Info")) {
                processInfo();
            } else if (cmd.equals("CT:") || cmd.equals("ClassroomTeacher:")) {
                findTeachersInClassroom(tkn.nextInt());
            } else if (cmd.equals("CS:") || cmd.equals("ClassroomStudent:")) {
                findStudentsInClassroom(tkn.nextInt());
            }
            else {
                System.out.println("Invalid Command");
            }
        } else { invalidCommand(); }
    }

    // Traceability: implements requirements R11
    private static void processInfo() {
	    int gradePopulation[] = new int[7];
	    for (Student student : studentsList) {
	        switch (student.grade) {
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

    // Traceability: implements requirements R10
    private static void processAverage(Scanner tkn) {
	    if (tkn.hasNext()) {
	        int grade = tkn.nextInt();
	        int cnt = 0;
	        float gpaTotal = 0f;
	        for (Student student : studentsList) {
	            if (student.grade == grade) {
	                gpaTotal = gpaTotal + student.gpa;
	                cnt++;
                }
            }
            float average = gpaTotal/cnt;
	        System.out.println("Grade: " + grade + " - Average GPA: " + average);
        } else { System.out.println("Invalid Command: A[verage]: <number>"); }
    }

    // Traceability: implements requirements NR3
    private static void processGradeTeacher(Scanner tkn) {
        if (tkn.hasNext()) {
            ArrayList<Integer> results = new ArrayList<>();
            int grade = Integer.parseInt(tkn.next());
            for (Student student : studentsList) {
                if (student.grade == grade) {
                    if(!results.contains(student.classroom)){
                        results.add(student.classroom);
                    }
                }
            }
            for (Integer classroom: results) {
                findTeachersInClassroom(classroom);
                System.out.println();
            }
        } else { System.out.println("Invalid Command: GT: <number>"); }
    }

    // Traceability: implements requirements R8
    private static void processBus(Scanner tkn) {
	    if(tkn.hasNext()){
	        int route = tkn.nextInt();
	        for (Student student : studentsList) {
	            if (student.bus == route) {
	                System.out.println(student.lastName + "," + student.firstName + "," + student.grade + "," + student.classroom);
                }
            }
        } else { System.out.println("Invalid Command: B[us]: <Number>"); }
    }

    // Traceability: implements requirements R7 and R9
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
                for (Student student : studentsList) {
                    if (student.grade == grade) {
                        System.out.println(student.lastName + "," + student.firstName);
                    }
                }
            }
        } else { System.out.println("Invalid Command: G[rade]: <number>"); }
    }

    // Traceability: implements requirements R9
    // helper function to deal with getting the highest GPA
    private static void findHighest(int grade) {
	    ArrayList<Student> results = new ArrayList<>();
	    for (Student student : studentsList) {
	        if (student.grade == grade) {
	            results.add(student);
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
	    if (results.size() > 0) {
	        Student highest = results.get(0);
	        System.out.print("Highest: " + highest.lastName + "," + highest.firstName + "," + highest.gpa + ", ");
            findTeachersInClassroom(highest.classroom);
            System.out.print("," + highest.bus);
        }
    }

    // Traceability: implements requirements R9
    // helper function to deal with getting the lowest GPA
    private static void findLowest(int grade) {
	    ArrayList<Student> results = new ArrayList<>();
	    for (Student student : studentsList) {
	        if (student.grade == grade) {
	            results.add(student);
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
       if (results.size() > 0) {
            Student highest = results.get(0);
            System.out.print("Lowest: " + highest.lastName + "," + highest.firstName + "," + highest.gpa + ", ");
            findTeachersInClassroom(highest.classroom);
            System.out.print("," + highest.bus);
        }
    }

    // Traceability: implements requirements NR5
    private static void analyze(Scanner tkn){
        if(tkn.hasNext()){
            switch(tkn.next()) {
                case "B":
                case "Bus":
                    analyzeBus();
                    break;
                case "T":
                case "Teacher":
                    analyzeTeacher();  
                    break;
                case "G":
                case "Grade":
                    analyzeGrade();  
                    break;
                default:
                    System.out.println("Invalid Command: Anaylze: <B,T,G>");
            }
        }
        else { System.out.println("Invalid Command: Anaylze: <B,T,G>"); }
    }

    // Traceability: implements requirements NR5
    // Helper function to anaylze bus route and gpa
    private static void analyzeBus(){
        HashMap<Integer, Float> busHashGPA = new HashMap<Integer, Float>();
        HashMap<Integer, Integer> busHashFreq = new HashMap<Integer, Integer>();
        TreeSet<Integer> busList = new TreeSet<Integer>();
        for(Student student: studentsList){
            if(!busList.contains(student.bus)){
                busList.add(student.bus);
                busHashFreq.put(student.bus, 1);
                busHashGPA.put(student.bus, student.gpa);
            } else {
                busHashFreq.put(student.bus, busHashFreq.get(student.bus) + 1);
                busHashGPA.put(student.bus, busHashGPA.get(student.bus) + student.gpa);    
            }
        }
        for(Integer busNum: busList) {
            System.out.println("Bus " + busNum + " average gpa: " + busHashGPA.get(busNum) / busHashFreq.get(busNum));
        }
    }

    // Traceability: implements requirements NR4
    private static void classroomEnrollment(){
        HashMap<Integer, Integer> classroomHashFreq = new HashMap<Integer, Integer>();
        TreeSet<Integer> classroomList = new TreeSet<Integer>();
        for(Student student: studentsList){
            if(!classroomList.contains(student.classroom)){
                classroomList.add(student.classroom);
                classroomHashFreq.put(student.classroom, 1);
            } else {
                classroomHashFreq.put(student.classroom, classroomHashFreq.get(student.classroom) + 1);
            }
        }
        for(Integer classroom: classroomList) {
            System.out.println("Classroom " + classroom + " has " + classroomHashFreq.get(classroom) + " students");
        }
    }

    // Traceability: implements requirements NR5
    // Helper function to anaylze the relationship between grade and gpa
    private static void analyzeGrade(){
        HashMap<Integer, Float> gradeHashGPA = new HashMap<Integer, Float>();
        HashMap<Integer, Integer> gradeHashFreq = new HashMap<Integer, Integer>();
        TreeSet<Integer> gradeList = new TreeSet<Integer>();
        for(Student student: studentsList){
            if(!gradeList.contains(student.grade)){
                gradeList.add(student.grade);
                gradeHashFreq.put(student.grade, 1);
                gradeHashGPA.put(student.grade, student.gpa);
            } else {
                gradeHashFreq.put(student.grade, gradeHashFreq.get(student.grade) + 1);
                gradeHashGPA.put(student.grade, gradeHashGPA.get(student.grade) + student.gpa);    
            }
        }
        for(Integer grade: gradeList) {
            System.out.println("Grade " + grade + " average gpa: " + gradeHashGPA.get(grade) / gradeHashFreq.get(grade));
        }
    }

    // Traceability: implements requirements NR5
    // Helper function to anaylze the relationship between teacher and gpa
    private static void analyzeTeacher(){
        HashMap<String, Float> teacherHashGPA = new HashMap<String, Float>();
        HashMap<String, Integer> teacherHashFreq = new HashMap<String, Integer>();
        TreeSet<String> teacherList = new TreeSet<String>();
        for(Teacher teacher: teachersList){
            getStudentsGPA(teacher, teacherHashFreq, teacherHashGPA, teacherList);
        }
        for(String teacherLastName: teacherList) {
            System.out.println(teacherLastName + " average student gpa: " + teacherHashGPA.get(teacherLastName) / teacherHashFreq.get(teacherLastName));
        }
    }

    // Second helper function to anaylze the relationship between grade and gpa
    private static void getStudentsGPA(Teacher teacher, HashMap<String, Integer> teacherHashFreq, HashMap<String, Float> teacherHashGPA, TreeSet<String> teacherList){
        for(Student student: studentsList){
            if(student.classroom == teacher.classroom){
                if(!teacherList.contains(teacher.lastName)){
                    teacherList.add(teacher.lastName);
                    teacherHashFreq.put(teacher.lastName, 1);
                    teacherHashGPA.put(teacher.lastName, student.gpa);
                } else {
                    teacherHashFreq.put(teacher.lastName, teacherHashFreq.get(teacher.lastName) + 1);
                    teacherHashGPA.put(teacher.lastName, teacherHashGPA.get(teacher.lastName) + student.gpa);    
                }
            }
        }
    }

    // Traceability: implements requirements R6
    private static void processTeacher(Scanner tkn) {
	    if (tkn.hasNext()){
	        String lastName = tkn.next();
	        for (Teacher tch : teachersList) {
	            if(tch.lastName.equals(lastName)) {
	                findStudentsInClassroom(tch.classroom);
                }
            }
        } else { System.out.println("Invalid Command: T[eacher]: <lastname>"); }
    }

    // Traceability: implements requirements NR2
    private static void findTeachersInClassroom(int classroom) {
        for (Teacher teacher: teachersList) {
            if (teacher.classroom == classroom) {
                System.out.print(teacher.lastName + "," + teacher.firstName);
            }
        }
    }

    // Traceability: implements requirements NR1
    private static void findStudentsInClassroom(int classroom) {
        for (Student student: studentsList) {
            if (student.classroom == classroom) {
                System.out.println(student.lastName + "," + student.firstName);
            }
        }
    }

    // Traceability: implements requirements R4 and R5
    private static void processStudent(Scanner tkn) {
	    if (tkn.hasNext()){
	        String lastName = tkn.next();
	        if(tkn.hasNext()){
	            String bus = tkn.next();
	            if(bus.equals("B") || bus.equals("Bus")){
                    for (Student student : studentsList){
                        if (student.lastName.equals(lastName)){
                            System.out.println(student.lastName + "," + student.firstName + "," + student.bus);
                        }
                    }
                } else {
                    System.out.println("Invalid Command: S[tudent]: <lastname> [B[us]]");
                }
            } else {
	            for (Student student : studentsList){
	                if (student.lastName.equals(lastName)){
                        System.out.print(student.lastName + "," + student.firstName + "," + student.grade + "," + student.classroom + ","); 
                        findTeachersInClassroom(student.classroom);
                    }
                }

            }
        } else {
	        System.out.println("Invalid Command: S[tudent]: <lastname> [B[us]]");
        }
    }

    // Traceability: implements requirements R12
    private static void invalidCommand() {
        System.out.println("Invalid Command");
    }
}
