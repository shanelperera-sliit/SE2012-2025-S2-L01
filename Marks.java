import java.util.Scanner;

public class Marks {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("*** MAIN MENU ***");
		System.out.println("Add student marks: add [studentID]");
		System.out.println("Update student marks: update [studentID] [subjectID]");
		System.out.println("Get the average for a subject: average_s [studentID]");
		System.out.println("Get the average for a student: average [studentID]");
		System.out.println("Get the total mark of a student: total [studentID]");
		
		System.out.print("Enter Command: ");
		String command = scanner.nextLine();
		System.out.println(command);
	}
}