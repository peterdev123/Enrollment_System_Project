import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int userType = 0;

        System.out.println(" >>> Welcome to the Enrollment System <<<\n");

        do {
            try {
                System.out.println("Login Menu");
                System.out.println("[1] Enroller");
                System.out.println("[2] Approver");
                System.out.println("[3] Exit");
                System.out.print("Select an option: ");
                userType = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please try again.\n");
                scanner.next();
                continue;
            }

            switch (userType) {
                case 1:
                    // Enroll menu
                    Enroll enroll = new Enroll(students);
                    students = enroll.enrollerMenu();
                    break;
                case 2:
                    if (students.isEmpty()) {
                        System.out.println("\nStudent list is empty. Please standby\n");
                        break;
                    }
                    // Approver menu
                    System.out.println("\nWelcome Sir, Sayson!");
                    Approver approver = new Approver(students);
                    students = approver.approverMenu();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
            }
        } while (userType != 3);

        System.out.println("\nThank you for using the system!");
        System.out.println("Exiting the system...");
        scanner.close();
    }
}
