import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Approver {
    private Scanner scanner;
    private ArrayList<Student> students;

    // Constructor
    public Approver (ArrayList<Student> students) {
        scanner = new Scanner(System.in);
        this.students = students;
    }

    // Main approver menu
    public ArrayList<Student> approverMenu() {
        int userType = 0;

        do {
            try {
                System.out.println("\nApprove Menu");
                System.out.println("[1] Approve pending deletion request");
                System.out.println("[2] Approve pending graduate request");
                System.out.println("[3] Approve pending dismiss request");
                System.out.println("[4] Approve pending change course request");
                System.out.println("[5] Log out approver");

                System.out.print("Select user type: ");
                userType = scanner.nextInt();

                switch (userType) {
                    case 1:
                        // Approve delete function
                        approveDeleteStudent();
                        break;
                    case 2:
                        // Approve graduate function
                        approveGraduateStudent();
                        break;
                    case 3: 
                        // Approve dismiss function
                        approveDismissStudent();
                        break;
                    case 4:
                        // Approve change course function
                        approveChangeCourseStudent();
                        break;
                    case 5:
                        // Log out to login menu
                        System.out.println("Logging out approver.");
                        break;
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid integer.");
                scanner.next(); 
            }
        } while (userType != 5);

        System.out.println("\nGoodbye Sir, Sayson!");
        return students;
    }

    // Approve delete function
    public void approveDeleteStudent() {
        boolean found = false;
        System.out.println("\nList of student's deletion pending requests:");
        for (Student student : students) {
            if (student.isApproveDelete() || student.isUndoDelete()) {
                System.out.println("Student #" + student.getStudentId());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No pending deletion requests. Please standby.");
            return;
        }

        Student student;
        int inputId;

        do {
            try {
                System.out.print("\nEnter student ID from the list to approve request: ");
                inputId = scanner.nextInt();

                student = students.get(inputId - 2000);

                if (student.isApproveDelete() || student.isUndoDelete()) {
                    break;
                }

                System.out.println("Invalid. Please enter ID from the list of students with pending deletion requests.");
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid student ID. ID is out of bounds. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); 
            }

        } while (true);
        if (student.isApproveDelete()) {
            do {
                System.out.print("\nApprove delete request of student #" + student.getStudentId() + "? (YES | NO): ");
                String decision = scanner.next();

                if (decision.equalsIgnoreCase("YES")) {
                    System.out.println("Deleting student #" + student.getStudentId() + " from the list...");
                    student.setDelete(true);
                    student.setApproveDelete(false);
                    break;
                }else if (decision.equalsIgnoreCase("NO")) {
                    System.out.println("Rejecting deletion request for student #" + student.getStudentId() + "...\n");
                    student.setApproveDelete(false);
                    break;
                }else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
        }

        if (student.isUndoDelete()) {
            do {
                System.out.print("\nApprove undo status request of student #" + student.getStudentId() + "? (YES | NO): ");
                String decision = scanner.next();

                if (decision.equalsIgnoreCase("YES")) {
                    System.out.println("Reverting deletion status from student #" + student.getStudentId());
                    student.setDelete(false);
                    student.setUndoDelete(false);
                    break;
                }else if (decision.equalsIgnoreCase("NO")) {
                    System.out.println("Rejecting undo deletion request for student #" + student.getStudentId() + "...\n");
                    student.setUndoDelete(false);
                    break;
                }else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
        }
    }

    // Approve graduate function
    public void approveGraduateStudent() {
        boolean found = false;
        System.out.println("\nList of students with pending graduation requests:");
        for (Student student : students) {
            if (student.isApproveGraduate() || student.isUndoGraduate()) {
                System.out.println("Student #" + student.getStudentId());
                found = true;
            }
        }
    
        if (!found) {
            System.out.println("No pending graduation requests. Please standby.");
            return;
        }
    
        Student student;
        int inputId;
    
        do {
            try {
                System.out.print("\nEnter student ID from the list to approve graduation request: ");
                inputId = scanner.nextInt();
    
                student = students.get(inputId - 2000);
    
                if (student.isApproveGraduate() || student.isUndoGraduate()) {
                    break;
                }

                System.out.println("Invalid. Please enter ID from the list of students with pending graduate requests.");
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid student ID. ID is out of bounds. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); 
            }
    
        } while (true);
    
        if (student.isApproveGraduate()) {
            do {
                System.out.print("\nApprove graduation request of student #" + student.getStudentId() + "? (YES | NO): ");
                String decision = scanner.next();
    
                if (decision.equalsIgnoreCase("YES")) {
                    System.out.println("Approving graduation for student #" + student.getStudentId() + "...");
                    student.setGraduate(true);
                    student.setApproveGraduate(false);
                    break;
                } else if (decision.equalsIgnoreCase("NO")) {
                    System.out.println("Rejecting graduation request for student #" + student.getStudentId() + "...\n");
                    student.setApproveGraduate(false);
                    break;
                } else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
        }
    
        if (student.isUndoGraduate()) {
            do {
                System.out.print("\nApprove undo graduation status request of student #" + student.getStudentId() + "? (YES | NO): ");
                String decision = scanner.next();
    
                if (decision.equalsIgnoreCase("YES")) {
                    System.out.println("Reverting graduation status for student #" + student.getStudentId() + " to the list...");
                    student.setGraduate(false);
                    student.setUndoGraduate(false);
                    break;
                } else if (decision.equalsIgnoreCase("NO")) {
                    System.out.println("Rejecting undo graduation request for student #" + student.getStudentId() + "...\n");
                    student.setUndoGraduate(false);
                    break;
                } else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
        }
    }

    // Approve dismiss function
    public void approveDismissStudent() {
        boolean found = false;
        System.out.println("\nList of students with pending dismissal requests:");
        for (Student student : students) {
            if (student.isApproveDismiss() || student.isUndoDismiss()) {
                System.out.println("Student #" + student.getStudentId());
                found = true;
            }
        }
    
        if (!found) {
            System.out.println("No pending dismissal requests. Please standby.");
            return;
        }
    
        Student student;
        int inputId;
    
        do {
            try {
                System.out.print("\nEnter student ID from the list to approve dismissal request: ");
                inputId = scanner.nextInt();
    
                student = students.get(inputId - 2000);
    
                if (student.isApproveDismiss() || student.isUndoDismiss()) {
                    break;
                }

                System.out.println("Invalid. Please enter ID from the list of students with pending dismiss requests.");
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid student ID. ID is out of bounds. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); 
            }
    
        } while (true);
    
        if (student.isApproveDismiss()) {
            do {
                System.out.print("\nApprove dismissal request of student #" + student.getStudentId() + "? (YES | NO): ");
                String decision = scanner.next();
    
                if (decision.equalsIgnoreCase("YES")) {
                    System.out.println("Approving dismissal for student #" + student.getStudentId() + "...");
                    student.setDismiss(true);
                    student.setApproveDismiss(false);
                    break;
                } else if (decision.equalsIgnoreCase("NO")) {
                    System.out.println("Rejecting dismissal request for student #" + student.getStudentId() + "...\n");
                    student.setApproveDismiss(false);
                    break;
                } else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
        }
    
        if (student.isUndoDismiss()) {
            do {
                System.out.print("\nApprove undo dismissal status request of student #" + student.getStudentId() + "? (YES | NO): ");
                String decision = scanner.next();
    
                if (decision.equalsIgnoreCase("YES")) {
                    System.out.println("Reverting dismissal status for student #" + student.getStudentId() + " to the list...");
                    student.setDismiss(false);
                    student.setUndoDismiss(false);
                    break;
                } else if (decision.equalsIgnoreCase("NO")) {
                    System.out.println("Rejecting undo dismissal request for student #" + student.getStudentId() + "...\n");
                    student.setUndoDismiss(false);
                    break;
                } else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
        }
    }

    // Approve course change function
    public void approveChangeCourseStudent() {
        boolean found = false;
        System.out.println("\nList of students with pending course change requests:");
        for (Student student : students) {
            if (student.isApproveChangeCourse()) {
                System.out.println("Student #" + student.getStudentId() + ": " + student.getCourse() + " --> " + student.getChangeCourse());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No pending requests. Please standby.");
            return;
        }
    
        Student student;
        int inputId;
    
        do {
            try {
                System.out.print("\nEnter student ID from the list to approve request: ");
                inputId = scanner.nextInt();
    
                student = students.get(inputId - 2000);
    
                if (!student.isApproveChangeCourse()) {
                    System.out.println("Invalid. Please enter ID from the list of students with pending course change requests.");
                    continue;
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid student ID. ID is out of bounds. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); 
            }
    
        } while (true);
    
        do {
            System.out.print("\nApprove course change request of student #" + student.getStudentId() + " to " + student.getChangeCourse() + "? (YES | NO): ");
            String decision = scanner.next();
    
            if (decision.equalsIgnoreCase("YES")) {
                System.out.println("Approving course change for student #" + student.getStudentId() + "...");
                student.setCourse(student.getChangeCourse());
                student.setApproveChangeCourse(false);
                student.setChangeCourse(null);
                break;
            } else if (decision.equalsIgnoreCase("NO")) {
                System.out.println("Rejecting course change for student #" + student.getStudentId() + "...\n");
                student.setApproveChangeCourse(false);
                student.setChangeCourse(null);
                break;
            } else {
                System.out.println("\nInput YES or NO, please try again.");
            }
        } while (true);
    }

}