import java.util.*;

public class createTable {

    public static populateTable(String line, List<List<String>> table){
        List<String> studentData = Arrays.asList(line.split(","));
    }

    public static void scanFiles(List<List<String>> table) {
        Scanner sc = new Scanner(new File("students.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            populateTable(line, table);
        }
    sc.close();
    } 
    
    public static void main(String args[]){ 
        List<List<String>> studentTable = new ArrayList<List<String>>();
        scanFiles(studentTable); 
    }
}
