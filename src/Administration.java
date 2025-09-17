import java.time.LocalDate;
import java.util.List;
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
    static final int SELECT_PATIENT = 1;
    static final int DISPLAY_ALL = 2;

    static final int VIEW_PATIENT = 1;
    static final int VIEW_PRESCRIPTIONS = 2;
    static final int EDIT_PATIENT = 3;

    static final int EDIT_SELECTED_PATIENT = 1;
    static final int DELETE_SELECTED_PATIENT = 2;


    PatientManager patientManager;  // Manages all patients
    MedicationManager medicationManager; // Manages medication
    User currentUser;               // the current user of the program.

    /**
     * Constructor
     */
    Administration(User user) {
        currentUser = user;
        patientManager = new PatientManager();
        medicationManager = new MedicationManager();
        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }

    int makeChoice (Scanner scanner) {
        System.out.print("enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear scanner after choice
        return choice;
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
        int height = scanner.nextInt();
        scanner.nextLine();

        patientManager.addPatient(lastName, firstName, dob, weight, height);
        System.out.println("Patient added successfully!");

    }

    private boolean jumpToMainMenu = false;

    void menu (Scanner scanner) {
        boolean nextCycle = true;

        while (nextCycle) {
            jumpToMainMenu = false;
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("%d:  QUIT\n", STOP);
            System.out.format("%d:  Select patient\n", SELECT_PATIENT);
            System.out.format("%d:  Display all patients\n", DISPLAY_ALL);

            int choice = makeChoice(scanner);

            switch (choice) {

                case STOP:
                    nextCycle = false;
                    break;

                case SELECT_PATIENT:
                    System.out.print("Enter patient name or ID: ");
                    String searchInput = scanner.nextLine();
                    Patient foundPatient = patientManager.getPatient(searchInput);
                    if (foundPatient == null) {
                        List<Patient> foundPatientList = patientManager.searchPatientsByPartialName(searchInput);
                        patientManager.displayPartialMatches(foundPatientList);
                    } else {
                        patientMenu(scanner, foundPatient);
                    }
                    break;

                case DISPLAY_ALL:
                    patientManager.displayAllPatients();
                    break;
            }
        }
    }

    void patientMenu (Scanner scanner, Patient selectedPatient) {
        boolean nextCycle = true;

        while (nextCycle && !jumpToMainMenu) {

            System.out.format("%s\n", "=".repeat(80));
            System.out.println("Selected patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname());
            System.out.format("%d:  RETURN\n", STOP);
            System.out.format("%d:  Patient data\n", VIEW_PATIENT);
            System.out.format("%d:  Patient prescriptions\n", VIEW_PRESCRIPTIONS);
            System.out.format("%d:  Edit patient\n", EDIT_PATIENT);

            int choice = makeChoice(scanner);
            switch (choice) {

                case STOP:
                    nextCycle = false;
                    break;

                case VIEW_PATIENT:
                    selectedPatient.displayDetailedPatient();
                    break;

                case VIEW_PRESCRIPTIONS:
                    System.out.println();
                    medicationManager.getPatientPrescription(selectedPatient.getId());
                    break;

                case EDIT_PATIENT:
                    editPatientMenu(scanner, selectedPatient);
                    break;
            }
        }
    }




    public void editPatientMenu(Scanner scanner, Patient selectedPatient) {

        boolean nextCycle = true;
        while (nextCycle && !jumpToMainMenu) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("%d:  RETURN\n", STOP);
            System.out.format("%d:  Edit current patient\n", EDIT_SELECTED_PATIENT);
            System.out.format("%d:  Delete current patient\n", DELETE_SELECTED_PATIENT);

            int choice = makeChoice(scanner);
            switch (choice) {
                case STOP:
                    nextCycle = false;
                    break;

                case EDIT_SELECTED_PATIENT:
                    patientManager.editPatient(selectedPatient);
                    break;

                case DELETE_SELECTED_PATIENT:
                    if (patientManager.deletePatient(scanner, selectedPatient)) {
                        jumpToMainMenu = true;
                        return;
                    }
                    break;
            }
        }
    }
}
