import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PatientManager {

    private final ArrayList<Patient> patients = new ArrayList<>();

    public PatientManager() {
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(2, "Van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(3, "Van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
    }

    void displayPatient(Patient p) {
          System.out.format("===== Patient id=%d ==============================\n", p.getId());
          System.out.println();
          System.out.println("Name: " + p.getFirstName() + " " + p.getSurname());
          System.out.println("DOB: " + p.getDateOfBirth());
          System.out.println("Weight(KG): " + p.getWeight());
          System.out.println("Height(CM): " + p.getHeight());
          System.out.println();

    }

    void displayAllPatients () {
        for (Patient p : patients) {
            displayPatient(p);
        }
    }

    Patient getPatient (String nameOrID){

        Patient foundPatient = null;

        try {
            int ID = Integer.parseInt(nameOrID);
            foundPatient = searchPatientById(ID);

        } catch (NumberFormatException e) {
            foundPatient = searchPatientByName(nameOrID);

        }

        return foundPatient;
    }

    Patient searchPatientById(int id) {
        for (Patient p : patients) {
            if (p.getId()==id) {
                return p;
            }
        } return null;
    }

    Patient searchPatientByName(String patientName) {
        String cleanPatientName = patientName.toLowerCase().replace(" ", "");

       for (Patient p : patients) {
           String searchResult = p.getFirstName().toLowerCase().replace(" ", "") + p.getSurname().toLowerCase().replace(" ", "");
           if (searchResult.equals(cleanPatientName)) {
               return p;
           }
       } return null;
    }

    List<Patient> searchPatientsByPartialName(String partialName) {
        String cleanPartialName = partialName.toLowerCase().replace(" ", "");
        List<Patient> matches = new ArrayList<>();

        for (Patient p : patients) {
            String patientFullName = (p.getFirstName() + p.getSurname()).toLowerCase().replace(" ", "");
            if (patientFullName.contains(cleanPartialName)) {
                matches.add(p);
            }
        }

        return matches;
    }

    public void displayPartialMatches(List<Patient> partialResults) {
        if (partialResults.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("Patients found:");
            for (Patient p : partialResults) {
                System.out.println("ID: " + p.getId() + " | " + p.getFirstName() + " " + p.getSurname() + " | DOB: " + p.getDateOfBirth());
            }
        }
    }

    void addPatient(String lastName, String firstName, LocalDate dob, double weight, int height, Double lungVolume) {
        int ID = patients.size() + 1;
        patients.add(new Patient(ID, lastName, firstName, dob, weight, height, lungVolume));
    }

    boolean deletePatient (Scanner scanner, Patient selectedPatient) {
        System.out.println("Are you sure you wish to delete " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname() + "?");
        System.out.println("If you do, type: DELETE");
        System.out.println("If you do not, type: QUIT");
        System.out.print("Enter choice: ");

        String delete = "DELETE";
        String quit = "QUIT";

        while (true) {
            String confirmation = scanner.nextLine();

            if (Objects.equals(confirmation, delete)) {
                patients.remove(selectedPatient);
                System.out.println("Patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname() + " deleted successfully!");
                return true;
            } if (Objects.equals(confirmation, quit)) {
                return false;
            } else {
                System.out.println("Use capital letters!");
                System.out.print("Try again: ");
            }
        }
    }

    public void editPatient(Patient selectedPatient, Scanner scanner) {
        if (selectedPatient == null) return;
        displayPatient(selectedPatient);
        System.out.format("%s\n", "=".repeat(80));
//        scanner.nextLine(); // Clear buffer
        System.out.print("New First Name (press Enter to keep current): ");
        String newFirst = scanner.nextLine();
        if (!newFirst.trim().isEmpty()) {
            selectedPatient.setFirstname(newFirst);
        }
        System.out.print("New Last Name (press Enter to keep current): ");
        String newLast = scanner.nextLine();
        if (!newLast.trim().isEmpty()) {
            selectedPatient.setSurname(newLast);
        }
        System.out.print("New date of birth (YY-MM-DD) (press Enter to keep current): ");
        String newDob = scanner.nextLine();
        if (!newDob.trim().isEmpty()) {
            try {
                selectedPatient.setDateofBirth(LocalDate.parse(newDob));
            } catch (Exception e) {
                System.out.println("Invalid date format. Keeping current.");
            }
        }
        System.out.print("New Weight(KG) (press Enter to keep current): ");
        String newWeight = scanner.nextLine();
        if (!newWeight.trim().isEmpty()) {
            try {
                selectedPatient.setWeight(Double.parseDouble(newWeight));
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight. Keeping current.");
            }
        }
        System.out.print("New Height(CM) (press Enter to keep current): ");
        String newHeight = scanner.nextLine();
        if (!newHeight.trim().isEmpty()) {
            try {
                selectedPatient.setHeight(Integer.parseInt(newHeight));
            } catch (NumberFormatException e) {
                System.out.println("Invalid height. Keeping current.");
            }
        }
    }

    void getNewPatientData(Scanner scanner) {

        System.out.println("=== Add New Patient ===");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Date of birth (YY-MM-DD): ");
        String dobString = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dobString);

        System.out.print("Weight (KG): ");
        double weight = scanner.nextDouble();

        System.out.print("Height (M): ");
        int height = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Lung volume (L): ");
        double lungVolume = scanner.nextDouble();

        addPatient(lastName, firstName, dob, weight, height, lungVolume);
        System.out.println("Patient " + firstName + lastName + " added successfully!");

    }
}
