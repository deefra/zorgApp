import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * PatientManager handles all patient-related operations including
 * storing, retrieving, and managing the collection of patients.
 * */

public class PatientManager {

    Administration administration;
    private ArrayList<Patient> patients = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public PatientManager() {
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176));
        patients.add(new Patient(2, "Van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148));
        patients.add(new Patient(3, "Van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210));
    }

    void displayPatient(Patient p) {
          System.out.format("===== Patient id=%d ==============================\n", p.getId());
          System.out.println();
          System.out.println("Name: " + p.getFirstName() + " " + p.getSurname());
          System.out.println("DOB: " + p.getDateOfBirth());
          System.out.println("Weight(KG): " + p.getWeight());
          System.out.println("Height(M): " + p.getHeight());
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
        }
        return null;
    }

    Patient searchPatientByName(String patientName) {
        String cleanPatientName = patientName.toLowerCase().replace(" ", "");

       for (Patient p : patients) {
           String searchResult = p.getFirstName() + p.getSurname();
           searchResult = searchResult.toLowerCase().replace(" ", "");
           if (searchResult.equals(cleanPatientName)) {
               return p;
           }
       }

       return null;
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
                System.out.println(p.getFirstName() + " " + p.getSurname() + " | DOB: " + p.getDateOfBirth() + " | ID: " + p.getId());
            }
        }
    }

    void addPatient(String lastName, String firstName, LocalDate dob, double weight, int height) {
        int ID = patients.size() + 1;
        patients.add(new Patient(ID, lastName, firstName, dob, weight, height));
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
                System.out.println("Patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname() + "deleted successfully!");
                return true;

            } if (Objects.equals(confirmation, quit)) {
                return false;
            } else {
                System.out.println("Use capital letters!");
                System.out.print("Try again: ");
            }
        }
    }

    void editPatient(Patient selectedPatient) {

        if (selectedPatient != null) {
            displayPatient(selectedPatient);
            System.out.format("%s\n", "=".repeat(80));
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
                selectedPatient.setDateofBirth(LocalDate.parse(newDob));
            }

            System.out.print("New Weight(KG)(press Enter to keep current): ");
            String newWeight = scanner.nextLine();
            if (!newWeight.trim().isEmpty()) {
                selectedPatient.setWeight(Double.parseDouble(newWeight));
            }
            System.out.print("New Height(M)(press Enter to keep current): ");
            String newHeight = scanner.nextLine();
            if (!newHeight.trim().isEmpty()) {
                selectedPatient.setHeight(Integer.parseInt(newHeight));
            }

        } else {
            System.out.println("Patient not found!");
        }
    }
}
