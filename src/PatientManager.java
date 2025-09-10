import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PatientManager handles all patient-related operations including
 * storing, retrieving, and managing the collection of patients.
 * */

public class PatientManager {

    private ArrayList<Patient> patients = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public PatientManager() {
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 1.76));
        patients.add(new Patient(2, "Van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 1.40));
        patients.add(new Patient(3, "Van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 2.10));
    }

    void displayPatient(Patient p) {
          System.out.format("===== Patient id=%d ==============================\n", p.id);
          System.out.println("Name: " + p.firstName + " " + p.surname);
          System.out.println("DOB: " + p.dateOfBirth);
          System.out.println("Weight(KG): " + p.Weight);
          System.out.println("Height(M): " + p.Height);

    }

    void displayAllPatients () {
        for (Patient p : patients) {
            displayPatient(p);
        }
    }

    Patient searchPatientById(int id) {
        for (Patient p : patients) {
            if (p.id==id) {
                return p;
            }
        }
        return null;
    }

    Patient searchPatientByName(String patientName) {
        String cleanPatientName = patientName.toLowerCase().replace(" ", "");
       for (Patient p : patients) {
           String searchResult = p.firstName + p.surname;
           searchResult = searchResult.toLowerCase().replace(" ", "");
           if (searchResult.equals(cleanPatientName)) {
               return p;
           }
       }
        return null;
    }

    void addPatient(String lastName, String firstName, LocalDate dob, double weight, double height) {
        int ID = patients.size() + 1;
        patients.add(new Patient(ID, lastName, firstName, dob, weight, height));
    }

    void deletePatient (int userID) {
        patients.removeIf(p -> p.id == userID);
    }

    void editPatient(String nameOrID) {
        Patient foundPatient = null;

        try {
            int ID = Integer.parseInt(nameOrID);
            foundPatient = searchPatientById(ID);

        } catch (NumberFormatException e) {
            foundPatient = searchPatientByName(nameOrID);

        }

        if (foundPatient != null) {
            displayPatient(foundPatient);
            System.out.println("New First Name (press Enter to keep current): ");
            String newFirst = scanner.nextLine();
            if (!newFirst.trim().isEmpty()) {
                foundPatient.firstName = newFirst;
            }

            System.out.println("New Last Name (press Enter to keep current): ");
            String newLast = scanner.nextLine();
            if (!newLast.trim().isEmpty()) {
                foundPatient.surname = newLast;
            }

            System.out.println("New date of birth (YY-MM-DD) (press Enter to keep current): ");
            String newDob = scanner.nextLine();
            if (!newDob.trim().isEmpty()) {
                foundPatient.dateOfBirth = LocalDate.parse(newDob);
            }

            System.out.println("New Weight in kilo's(press Enter to keep current): ");
            String newWeight = scanner.nextLine();
            if (!newWeight.trim().isEmpty()) {
                foundPatient.Weight = Double.parseDouble(newWeight);
            }
            System.out.println("New Height in meters(press Enter to keep current): ");
            String newHeight = scanner.nextLine();
            if (!newHeight.trim().isEmpty()) {
                foundPatient.Height = Double.parseDouble(newHeight);
            }

        } else {
            System.out.println("Patient not found!");
        }
    }
}
