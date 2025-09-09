import java.time.LocalDate;
import java.util.Scanner;

/**
 * class Administration represents the core of the application by showing
 * the main menu, from where all other functionality is accessible, either
 * directly or via sub-menus.
 * An Administration instance needs a User as input, which is passed via the
 * constructor to the data member 'currentUser'.
 * The patient data is available via the data member currentPatient.
 */
class Administration {
    static final int STOP = 0;
    static final int VIEW = 1;
    static final int EDIT = 2;

    static final int SEARCH_BY_ID = 1;
    static final int SEARCH_BY_NAME = 2;
    static final int DISPLAY_ALL = 3;

    static final int ADD = 1;
    static final int DELETE = 2;

    PatientManager patientManager;            // Manages all patients
    User currentUser;               // the current user of the program.

    /**
     * Constructor
     */
    Administration(User user) {
        currentUser = user;
        patientManager = new PatientManager();
        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }

    void menu(Scanner scanner) {

        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            /*
             Print menu on screen
            */
            System.out.format("%d:  STOP\n", STOP);
            System.out.format("%d:  View patient data\n", VIEW);
            System.out.format("%d:  Edit patient data\n", EDIT);

            System.out.print("enter #choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear scanner after choice
            switch (choice) {
                case STOP: // interrupt the loop
                    nextCycle = false;

                    break;

                case VIEW:
                    viewPatientMenu(scanner);

                    break;

                case EDIT:
                    editPatientMenu(scanner);

                    break;
                default:
                    System.out.println("Please enter a *valid* digit");
                    break;
            }
        }
    }

    public void viewPatientMenu(Scanner scanner) {

        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("%d:  RETURN\n", STOP);
            System.out.format("%d:  Search by ID\n", SEARCH_BY_ID);
            System.out.format("%d:  Search by name\n", SEARCH_BY_NAME);
            System.out.format("%d:  Display all patients\n", DISPLAY_ALL);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear scanner after choice
            switch (choice) {
                case STOP:
                    nextCycle = false;
                    break;

                case SEARCH_BY_ID:
                    System.out.println("Input user ID: ");
                    int patientId = scanner.nextInt();
                    patientManager.searchPatientId(patientId);
                    break;
                case SEARCH_BY_NAME:
                
                    System.out.println("Input first and last name: ");
                    String patientName = scanner.nextLine();
                    patientManager.searchPatientName(patientName);
                    break;

                case DISPLAY_ALL:
                    patientManager.displayAllPatients();
                    break;
            }
        }
    }

    void getNewPatientData(Scanner scanner) {

        System.out.println("=== Add New Patient ===");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
    
        System.out.print("Date of birth (YY-MM-DD / 2025-01-01): ");
        String dobString = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dobString);
    
        System.out.print("Weight (KG): ");
        double weight = scanner.nextDouble();
    
        System.out.print("Height (M): ");
        double height = scanner.nextDouble();
        scanner.nextLine();

        patientManager.addPatient(lastName, firstName, dob, weight, height);
        System.out.println("Patient added successfully!");

    }

    void getDeleteId (Scanner scanner) {
        System.out.println("User ID: ");
        int deleteID = scanner.nextInt();
        patientManager.deletePatient(deleteID);
    }

    public void editPatientMenu(Scanner scanner) {

        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("%d:  RETURN\n", STOP);
            System.out.format("%d:  Add patient\n", ADD);
            System.out.format("%d:  Delete patient\n", DELETE);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear scanner after choice
            switch (choice) {
                case STOP:
                    nextCycle = false;

                    break;
                case ADD:
                    getNewPatientData(scanner);

                    break;
                case DELETE:
                    getDeleteId(scanner);

                    break;
            }
        }
    }
}
