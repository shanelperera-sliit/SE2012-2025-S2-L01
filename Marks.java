import java.util.Scanner;

public class Marks {
    static Scanner scanner = new Scanner(System.in);
    static final int SUBJECT_COUNT = 3;
    static double[][] marks;
    static int n;
    static String[] subjects = {"Mathematics", "Chemistry", "Physics"};

    public static void main(String[] args) {
		System.out.println("--- STUDENT MARKS MANAGER ---");
        System.out.print("Enter number of students: ");
        n = scanner.nextInt();
        scanner.nextLine();
        marks = new double[n][SUBJECT_COUNT];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < SUBJECT_COUNT; j++) {
                marks[i][j] = -1;
            }
        }

        while (true) {
            System.out.println("\n*** MAIN MENU ***");
            System.out.println("Add student marks: add [studentID]");
            System.out.println("Update student marks: update [studentID] [subjectID]");
            System.out.println("Get the average for a subject: average [subjectID]");
            System.out.println("Get the average for a student: average_s [studentID]");
            System.out.println("Get the total mark of a student: total [studentID]");
			System.out.println("Display the grades of the student: grade");
            System.out.println("Exit program: exit");

            System.out.print("\nEnter Command: ");
            String commandLine = scanner.nextLine().trim();
            String[] cmdParts = commandLine.split(" ");

            if (cmdParts.length == 0) {
                System.out.println("No command entered.");
                continue;
            }

            String command = cmdParts[0].toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Exiting program.");
                break;
            }

            switch (command) {
                case "add":
                    if (cmdParts.length != 2) {
                        System.out.println("Usage: add [studentID]");
                        break;
                    }
                    else {
                        int studentID = Integer.parseInt(cmdParts[1]);
                        addMarks(studentID);
                    }
                    break;
                case "update":
                    if (cmdParts.length != 3) {
                        System.out.println("Usage: update [studentID] [subjectID]");
                        break;
                    }
                    else {
                        int studentID = Integer.parseInt(cmdParts[1]);
                        int subjectID = Integer.parseInt(cmdParts[2]);
                        updateMarks(studentID, subjectID);
                    }
                    break;
                case "average":
                    if (cmdParts.length != 2) {
                        System.out.println("Usage: average [subjectID]");
                        break;
                    }
                    else {
                        int subjectID = Integer.parseInt(cmdParts[1]);
                        averageForSubject(subjectID);
                    }
                    break;
                case "average_s":
                    if (cmdParts.length != 2) {
                        System.out.println("Usage: average_s [studentID]");
                        break;
                    }
                    else {
                        int studentID = Integer.parseInt(cmdParts[1]);
                        averageForStudent(studentID);
                    }
                    break;
                case "total":
                    if (cmdParts.length != 2) {
                        System.out.println("Usage: total [studentID]");
                        break;
                    }
                    else {
                        int studentID = Integer.parseInt(cmdParts[1]);
                        totalForStudent(studentID);
                    }
                    break;
				case "grade":
					displayGrades();
					break;
                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }

    static void addMarks(int studentID) {
        if (!isValidStudent(studentID)) {
            System.out.println("Invalid student ID.");
            return;
        }

        System.out.printf("Enter marks for student %d (Mathematics, Chemistry, Physics)\n", studentID);
        for (int subject = 0; subject < SUBJECT_COUNT; subject++) {
            System.out.print(subjects[subject] + ": ");
            marks[studentID - 1][subject] = scanner.nextDouble();
        }
        scanner.nextLine();
        System.out.println("Marks Added.");
    }

    static void updateMarks(int studentID, int subjectID) {
        if (!isValidStudent(studentID) || !isValidSubject(subjectID)) {
            System.out.println("Invalid student or subject ID.");
            return;
        }
		
        System.out.print("Enter the new mark: ");
        double newMark = scanner.nextDouble();
        scanner.nextLine();
        marks[studentID - 1][subjectID - 1] = newMark;
        System.out.println("Mark updated.");
    }

    static void averageForSubject(int subjectID) {
        if (!isValidSubject(subjectID)) {
            System.out.println("Invalid subject ID.");
            return;
        }

        double sum = 0;
        int count = 0;
        for(int i = 0; i < n; i++) {
            double mark = marks[i][subjectID - 1];
            if (mark != -1) {
                sum += mark;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No marks entered for " + subjects[subjectID - 1]);
        }
        else {
            double avg = sum / count;
            System.out.printf("Average marks for %s: %.2f\n", subjects[subjectID - 1], avg);
        }
    }

    static void averageForStudent(int studentID) {
        if (!isValidStudent(studentID)) {
            System.out.println("Invalid student ID.");
            return;
        }

        double[] studentMarks = marks[studentID - 1];

        double sum = 0;
        int count = 0;
        for (double mark : studentMarks) {
            if (mark != -1) {
                sum += mark;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No marks entered for student " + studentID);
        } else {
            double average = sum / count;
            System.out.printf("Average marks for student %d: %.2f\n", studentID, average);
        }
    }

    static void totalForStudent(int studentID) {
        if (!isValidStudent(studentID)) {
            System.out.println("Invalid student ID.");
            return;
        }

        double sum = 0;
        int count = 0;
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            double mark = marks[studentID - 1][i];
            if (mark != -1) {
                sum += mark;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No marks entered for student " + studentID);
        } else {
            System.out.printf("Total marks for student %d: %.2f\n", studentID, sum);
        }
    }

    static boolean isValidStudent(int id) {
        return id >= 1 && id <= n;
    }

    static boolean isValidSubject(int id) {
        return id >= 1 && id <= SUBJECT_COUNT;
    }
	
	static void displayGrades() {
		System.out.println("\n--- GRADES SUMMARY ---");
		System.out.printf("%-15s", "Student");
		for (String subject : subjects) {
			System.out.printf("%-15s", subject);
		}
		System.out.println();
		System.out.println("-".repeat(52));

		for (int i = 0; i < n; i++) {
			System.out.printf("%-15s", "Student " + (i + 1));
			for (int j = 0; j < SUBJECT_COUNT; j++) {
				double mark = marks[i][j];
				if (mark == -1) {
					System.out.printf("%-15s", "N/A");
				} else {
					System.out.printf("%-15s", getGrade(mark));
				}
			}
			System.out.println();
		}
	}

	static String getGrade(double mark) {
		if (mark >= 90) 
			return "Grade A";
		else if (mark >= 80) 
			return "Grade B";
		else if (mark >= 70) 
			return "Grade C";
		else if (mark >= 60) 
			return "Grade D";
		else 
			return "Fail";
	}

}