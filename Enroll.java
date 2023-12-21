import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;

public class Enroll {
    static String[] courses = {"CS", "CE", "ME", "IT", "EE", "BME", "IE", "CompE", "IS", "AE"};
    private Scanner scanner;
    private ArrayList<Student> students;
    private int id;

    // Constructor
    public Enroll() {

    }

    public Enroll(ArrayList<Student> students) {
        scanner = new Scanner(System.in);
        this.students = students;
        this.id = students.size() + 2000;
    }

    // Main enroller menu
    public ArrayList<Student> enrollerMenu() {
        int enrollerOption;

        do {
            System.out.println("\nEnroller Menu");
            System.out.println("[1] Add student");
            System.out.println("[2] View and modify student");
            System.out.println("[3] Log out enroller");
            enrollerOption = getUserChoice(1, 3);

            switch (enrollerOption) {
                case 1:
                    // Add a student
                    addStudent();
                    break;
                case 2:
                    // View Student
                    if (students.isEmpty()) {
                        System.out.println("\nNo students currently in the list");
                        break;
                    }
                    viewMenu();
                    break;
                case 3:
                    System.out.println("\nLogging out enroller.\n");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
            }
        } while (enrollerOption != 3);

        return students;
    }

    // View menu
    private void viewMenu() {
        int viewOption;

        do {
            System.out.println("\nView and modify student Menu");
            System.out.println("[1] View by ID");
            System.out.println("[2] View by last name");
            System.out.println("[3] View by course");
            System.out.println("[4] View all students");
            System.out.println("[5] Exit to enroller menu");
            viewOption = getUserChoice(1, 5);

            switch (viewOption) {
                case 1:
                    // View by Id
                    int inputId = viewById();
                    modifyDetails(inputId);
                    break;
                case 2:
                    // View by Last Name
                    String inputLast = viewByLastName();
                    modifyDetails(inputLast);
                    break;
                case 3:
                    viewByCourse();
                    break;
                case 4:
                    // Display all students
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("\nExiting to Enroller Menu...");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        } while (viewOption != 5);
    }

    // Display student from given ID
    private int viewById() {
        int viewId;
        while (true) {
            System.out.print("\nEnter ID of student to view (starting from 2000): ");
            viewId = scanner.nextInt();

            System.out.println();
            boolean found = false;

            for (Student student : students) {
                if (student.getStudentId() == viewId) {
                    displayStudent(student);
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            } else {
                System.out.println("ID not found. Enter again.");
            }
        }

        return viewId;
    }

    // Display student from given last name 
    private String viewByLastName() {
        String viewLast;
        while (true) {
            System.out.print("\nEnter last name of student to view: ");
            viewLast = scanner.next();

            System.out.println();
            boolean found = false;

            for (Student student : students) {
                if (student.getLastName().equalsIgnoreCase(viewLast)) {
                    displayStudent(student);
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            } else {
                System.out.println("Last name not found. Enter again.");
            }
        }

        return viewLast;
    }

    // Display students in tabular form from given course
    private void viewByCourse() {
        while (true) {
            for (int i = 0; i < courses.length; i++) {
                System.out.println((i + 1) + ". " + courses[i]);
            }
            System.out.print("Enter course to view: ");
            String viewCourse = scanner.next();

            boolean validCourse = Arrays.asList(courses).contains(viewCourse.toUpperCase());

            if (validCourse) {
                System.out.printf("\n%-10s%-15s%-15s%-15s%-5s%-25s%-20s%-15s%-10s%-10s%-10s\n", "ID", "Last Name", "First Name", "Middle Name", "Age", "Email", "Contact", "Birthdate", "Course", "Dismissed", "Graduated");
                for (Student student : students) {
                    if (student.getCourse().equalsIgnoreCase(viewCourse)) {
                        displayStudents(student);
                    }
                }
                break;
            } else {
                System.out.println("Invalid course. Please enter a valid course.");
            }
        }
    }

    // modify details menu (method overloading for ID)
    private void modifyDetails(int inputId) {
        int modifyOption;

        Student student = students.get(inputId - 2000);

        do {
            System.out.println("\nModify Details Menu");
            System.out.println("[1] Modify details");
            System.out.println("[2] Delete student");
            System.out.println("[3] Graduate student");
            System.out.println("[4] Dismiss student");
            System.out.println("[5] Exit to view student menu");
            modifyOption = getUserChoice(1, 5);

            switch (modifyOption) {
                case 1:
                    // Modify details
                    // Implement modification logi
                    if (checkStatus(student)) { 
                        System.out.println("Disabled modification because current student is deleted.");
                        break; 
                    }
                    modifyMenu(student);
                    break;
                case 2:
                    // Delete student
                    deleteStudent(student);
                    // Implement deletion logic
                    break;
                case 3:
                    // Graduate student
                    graduateStudent(student);
                    // Implement graduation logic
                    break;
                case 4:
                    // Dismiss student
                    dismissStudent(student);
                    // Implement dismissal logic
                    break;
                case 5:
                    System.out.println("\nExiting to View Student Menu...");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        } while (modifyOption != 5);
    }
    
    // modify details menu (method overloading for lastName)
    private void modifyDetails(String lastName) {
        int modifyOption;

        Student student = null;

        for (Student s : students) {
            if (s.getLastName().equalsIgnoreCase(lastName)) {
                student = s;
                break;
            }
        }

        do {
            System.out.println("\nModify Details Menu");
            System.out.println("[1] Modify details");
            System.out.println("[2] Delete student");
            System.out.println("[3] Graduate student");
            System.out.println("[4] Dismiss student");
            System.out.println("[5] Exit to view student menu");
            modifyOption = getUserChoice(1, 5);

            switch (modifyOption) {
                case 1:
                    // Modify details
                    // Implement modification logic
                    if (checkStatus(student)) { 
                        System.out.println("Disabled modification because current student is deleted.");
                        break; 
                    }
                    modifyMenu(student);
                    break;
                case 2:
                    // Delete student
                    deleteStudent(student);
                    // Implement deletion logic
                    break;
                case 3:
                    // Graduate student
                    graduateStudent(student);
                    // Implement graduation logic
                    break;
                case 4:
                    // Dismiss student
                    dismissStudent(student);
                    // Implement dismissal logic
                    break;
                case 5:
                    System.out.println("\nExiting to View Student Menu...");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        } while (modifyOption != 5);
    }

    // Add student menu
    private void addStudent() {
        int addOption;

        do {
            System.out.println("\nAdd Student Menu");
            System.out.println("[1] Add a student");
            System.out.println("[2] Add batch of students");
            System.out.println("[3] Exit to enroller menu");
            addOption = getUserChoice(1, 3);

            switch (addOption) {
                case 1:
                    // Add a student
                    System.out.println("\nEnter data for student #" + (students.size() + 2000));
                    addOne();
                    break;
                case 2:
                    // Add batch of students
                    System.out.print("\nEnter number of students to add: ");
                    int numStudents = scanner.nextInt();

                    for (int i = 0; i < numStudents; i++) {
                        System.out.print("\nEnter data for student #" + (i + 1) + ":\n");
                        addOne();
                    }
                    break;
                case 3:
                    System.out.println("\nExiting to Enroller Menu.");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
            }
        } while (addOption != 3);
    }
    
    // Add student
    private void addOne() {
        try {
            System.out.print("Enter last name: ");
            String lastName = scanner.next();

            scanner.nextLine();

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter middle name: ");
            String middleName = scanner.nextLine();

            LocalDate birthdate = null;

            while (true) {
                System.out.print("Enter birthdate (YYYY-MM-DD): ");
                String userInput = scanner.nextLine().trim();

                if (!userInput.isEmpty()) {
                    try {
                        birthdate = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        break;
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Invalid date format. Please enter a date in the format YYYY-MM-DD.");
                    }
                } else {
                    System.out.println("Empty input. Please enter a date.");
                }
            }
            
            String email;

            while (true) {
                try {
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
    
                    // Validate email using a simple regex pattern
                    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                    Pattern pattern = Pattern.compile(emailRegex);
                    Matcher matcher = pattern.matcher(email);
    
                    if (matcher.matches()) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Invalid email format.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid email. " + e.getMessage());
                }
            }

            String contact;

            while (true) {
                try {
                    System.out.print("Enter contact (Example: +63##########): ");
                    contact = scanner.nextLine();
    
                    // Validate contact using a regex pattern
                    String contactRegex = "^\\+\\d{12}$";
                    Pattern pattern = Pattern.compile(contactRegex);
                    Matcher matcher = pattern.matcher(contact);
    
                    if (matcher.matches()) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Invalid contact format. Example: +639123456789");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid contact. " + e.getMessage());
                }
            }

            String course;
            while (true) {
                for (int i = 0; i < courses.length; i++) {
                    System.out.println((i + 1) + ". " + courses[i]);
                }
                System.out.print("Select course: ");

                try {
                    int userInput = scanner.nextInt();
                    if (userInput >= 1 && userInput <= courses.length) {
                        course = courses[userInput - 1];
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and " + courses.length);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }
            // Add data to current student class
            students.add(new Student(id, firstName, lastName, middleName, email, contact, birthdate, course));
            id++;
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            scanner.nextLine();
        }
    }

    // Display student
    private void displayStudent(Student student) {
        if(student.isDelete()) {
            System.out.println("Student #" + student.getStudentId() + " is deleted from the list");
            return;
        }

    
        System.out.println("Student's data");
        System.out.printf("Student Id :  %-10d%n", student.getStudentId());
        System.out.printf("Full Name  :  %-20s%n", student.getLastName() + ", " + student.getFirstName() + " " + student.getMiddleName());
        System.out.printf("Age        :  %-10d%n", student.getAge());
        System.out.printf("Email      :  %-15s%n", student.getEmail());
        System.out.printf("Contact    :  %-15s%n", student.getContact());
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.printf("Birthdate  :  %-15s%n", (student.getBirthDate()).format(formatter));
        System.out.printf("Course     :  %-10s%n", student.getCourse());
        System.out.printf("Dismissed? :  %-5s%n", (student.isDismiss()) ? "YES" : "NO");
        System.out.printf("Graduated? :  %-5s%n", (student.isGraduate()) ? "YES" : "NO");
    }

    // Display students in tabular format
    private void displayStudents(Student student) {
        if(student.isDelete()) {
                System.out.println("                                                Student #" + student.getStudentId() + " is deleted from the list");
                return;
        }
        System.out.printf("%-10d%-15s%-15s%-15s%-5d%-25s%-20s%-15s%-10s%-10s%-10s\n",
                student.getStudentId(),
                student.getLastName(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getAge(),
                student.getEmail(),
                student.getContact(),
                student.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                student.getCourse(),
                (student.isDismiss()) ? "YES" : "NO",
                (student.isGraduate()) ? "YES" : "NO");
    }
    
    // Display all students
    private void displayAllStudents() {
        System.out.printf("\n%-10s%-15s%-15s%-15s%-5s%-25s%-20s%-15s%-10s%-10s%-10s\n", "ID", "Last Name", "First Name", "Middle Name", "Age", "Email", "Contact", "Birthdate", "Course", "Dismissed", "Graduated");
        for (Student student : students) {
            displayStudents(student);
        }
    }

    // Modify Menu
    private void modifyMenu (Student student) {
        int modifyOption;

        do {
            System.out.println("\nModify Student #" + student.getStudentId() + "'s");
            System.out.println("[1] Last name");
            System.out.println("[2] First name");
            System.out.println("[3] Middle name");
            System.out.println("[4] Email");
            System.out.println("[5] Contact");
            System.out.println("[6] Birthdate");
            System.out.println("[7] Course");
            System.out.println("[8] Exit to modify details menu");
            modifyOption = getUserChoice(1, 8);
            scanner.nextLine();

            switch (modifyOption) {
                case 1:
                    // Modify last name
                    modifyLast(student);
                    break;
                case 2:
                    // Modify first name
                    modifyFirst(student);
                    break;
                case 3:
                    // Modify middle name
                    modifyMiddle(student);
                    break;
                case 4:
                    // Modify email
                    modifyEmail(student);
                    break;
                case 5:
                    // Modify contact
                    modifyContact(student);
                    break;
                case 6:
                    // Modify birthdate
                    modifyBirthDate(student);
                    break;
                case 7:
                    // Modify course;
                    modifyCourse(student);
                    break;
                case 8:
                    System.out.println("\nExiting to View Student Menu...");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        } while (modifyOption != 8);
    } 

    // Modify last name
    private void modifyLast(Student student) {
        System.out.print("\nEnter last name to modify: ");
        String last = scanner.nextLine();
        student.setLastName(last);
        System.out.println("Setting modification...");
    }

    // Modify first name
    private void modifyFirst(Student student) {
        System.out.print("\nEnter first name to modify: ");
        String first = scanner.nextLine();
        student.setFirstName(first);
        System.out.println("Setting modification...");
    }

    // Modify middle name
    private void modifyMiddle(Student student) {
        System.out.print("\nEnter middle name to modify: ");
        String middle = scanner.nextLine();
        student.setMiddleName(middle);
        System.out.println("Setting modification...");
    }

    // Modify email
    private void modifyEmail(Student student) {
        while (true) {
            try {
                System.out.print("\nEnter email to modify: ");
                String email = scanner.nextLine();

                // Validate email using a simple regex pattern
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(email);

                if (matcher.matches()) {
                    student.setEmail(email);
                    System.out.println("Setting modification...");
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid email format.");
                }
            } catch (Exception e) {
                System.out.println("Invalid email. " + e.getMessage());
            }
        }
    }

    // Modify contact
    private void modifyContact(Student student) {
        while (true) {
            try {
                System.out.print("\nEnter contact (Example: +63##########) to modify: ");
                String contact = scanner.nextLine();

                // Validate contact using a regex pattern
                String contactRegex = "^\\+\\d{12}$";
                Pattern pattern = Pattern.compile(contactRegex);
                Matcher matcher = pattern.matcher(contact);

                if (matcher.matches()) {
                    student.setContact(contact);
                    System.out.println("Setting modification...");
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid contact format. Example: +639123456789");
                }
            } catch (Exception e) {
                System.out.println("Invalid contact. " + e.getMessage());
            }
        }
    }

    // Modify birthdate
    private void modifyBirthDate(Student student) {
        LocalDate birthdate = null;

        while (true) {
            System.out.print("\nEnter birthdate (YYYY-MM-DD) to modify: ");
            String userInput = scanner.nextLine().trim();

            if (!userInput.isEmpty()) {
                try {
                    birthdate = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    break;
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter a date in the format YYYY-MM-DD.");
                }
            } else {
                System.out.println("Empty input. Please enter a date.");
            }
        }
        student.setBirthDate(birthdate);

        System.out.println("Setting modification...");
    }

    // Modify Course
    private void modifyCourse(Student student) {
        while (true) {
            for (int i = 0; i < courses.length; i++) {
                System.out.println((i + 1) + ". " + courses[i]);
            }
            System.out.print("Select course: ");

            try {
                int userInput = scanner.nextInt();
                if (userInput >= 1 && userInput <= courses.length) {
                    String course = courses[userInput - 1];
                    if (course.equalsIgnoreCase(student.getCourse())) {
                        System.out.println("\nSorry please pick a different course aside from the student's current course.\n");
                        continue;
                    }
                    student.setApproveChangeCourse(true);
                    student.setChangeCourse(course);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + courses.length);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        System.out.println("Standby for approval.");
    }

    // Delete student and waits for approval
    private void deleteStudent(Student student) {
        if (student.isDelete()) {
            String decide;
            do {
                System.out.print("\nStudent is already deleted from the list. Do you want to revert it back? (YES | NO): ");
                decide = scanner.next();

                if (decide.equalsIgnoreCase("YES")) {
                    System.out.println("Standby for approval.");
                    student.setUndoDelete(true);
                    break;
                }else if (decide.equalsIgnoreCase("NO")) {
                    break;
                }else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
            return;
        }

        String decision;
        do {
            System.out.print("\nDo you want to delete current student? (YES | NO): ");
            decision = scanner.next();

            if (decision.equalsIgnoreCase("YES")) {
                System.out.println("Standby for approval.");
                student.setApproveDelete(true);
                break;
            }else if (decision.equalsIgnoreCase("NO")) {
                break;
            }else {
                System.out.println("\nInput YES or NO, please try again.");
            }
        } while (true);
    }

    // Graduate student and waits for approval
    private void graduateStudent(Student student) {
        if (student.isGraduate()) {
            String decide;
            do {
                System.out.print("\nStudent has already graduated. Do you want to undo status? (YES | NO): ");
                decide = scanner.next();
        
                if (decide.equalsIgnoreCase("YES")) {
                    System.out.println("Standby for approval.");
                    student.setUndoGraduate(true);
                    break;
                } else if (decide.equalsIgnoreCase("NO")) {
                    break;
                } else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
            return;
        }
        String decision;
        do {
            System.out.print("\nGraduate current student? (YES | NO): ");
            decision = scanner.next();

            if (decision.equalsIgnoreCase("YES")) {
                System.out.println("Standby for approval.");
                student.setApproveGraduate(true);
                break;
            }else if (decision.equalsIgnoreCase("NO")) {
                break;
            }else {
                System.out.println("\nInput YES or NO, please try again.");
            }
        } while (true);
    }

    // Dismiss student and waits for approval
    private void dismissStudent(Student student) {
        if (student.isDismiss()) {
            String decide;
            do {
                System.out.print("\nStudent has already been dismissed. Do you want to undo status? (YES | NO): ");
                decide = scanner.next();
        
                if (decide.equalsIgnoreCase("YES")) {
                    System.out.println("Standby for approval.");
                    student.setUndoDismiss(true);
                    break;
                } else if (decide.equalsIgnoreCase("NO")) {
                    break;
                } else {
                    System.out.println("\nInput YES or NO, please try again.");
                }
            } while (true);
            return;
        }

        String decision;
        do {
            System.out.print("\nDo you want to dismiss current student? (YES | NO): ");
            decision = scanner.next();

            if (decision.equalsIgnoreCase("YES")) {
                System.out.println("Standby for approval.");
                student.setApproveDismiss(true);
                break;
            }else if (decision.equalsIgnoreCase("NO")) {
                break;
            }else {
                System.out.println("\nInput YES or NO, please try again.");
            }
        } while (true);
    }

    private boolean checkStatus(Student student) {
        if (student.isDelete()) {
            return true;
        }
        return false;
    }

    private int getUserChoice(int min, int max) {
        int userChoice = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print("Select an option: ");
                userChoice = scanner.nextInt();

                if (userChoice >= min && userChoice <= max) {
                    validInput = true;
                } else {
                    System.out.println("\nInvalid input. Please enter a number between " + min + " and " + max + ".\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid integer.\n");
                scanner.next();
            }
        } while (!validInput);

        return userChoice;
    }
}
