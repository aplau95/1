import org.graalvm.compiler.core.common.FieldsScanner.CalcOffset;

public class Student {
		
	public String lastName;
	public String firstName;
	public int classroom;

    public Teacher(String lastName, String firstName, int classroom) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.classroom = classroom;
    }
}