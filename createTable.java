import java.util.*;
import java.io.*;

public class createTable {

    // public static void populateTable(String line, List<List<String>> table){
    //     // List<String> studentData = Arrays.asList(line.split(","));
    //     System.out.print(line);
    // }

    // public static void scanFiles(List<List<String>> table) {
    //     Scanner sc = new Scanner(new File("students.txt"));
    //     while (sc.hasNextLine()) {
    //         String line = sc.nextLine();
    //         populateTable(line, table);
    //     }
    // sc.close();
    // } 

    public static void scanFiles() {
        File studentsFile = new File("students.txt");
        System.out.println(studentsFile.canRead());
        Scanner sc = new Scanner(studentsFile);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // populateTable(line, table);
            System.out.println(line);
        }
    sc.close();
    } 
    
    public static void main(String args[]){ 
        // List<List<String>> studentTable = new ArrayList<List<String>>();
        // scanFiles(studentTable); 
        scanFiles(); 

    }
}
