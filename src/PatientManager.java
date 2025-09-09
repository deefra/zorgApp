import java.time.LocalDate;
import java.util.ArrayList;

/**
 * PatientManager handles all patient-related operations including
 * storing, retrieving, and managing the collection of patients.
 * */

public class PatientManager {

    private ArrayList<Patient> patients = new ArrayList<>();

    public PatientManager() {
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 1.76));
        patients.add(new Patient(2, "Van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 1.40));
        patients.add(new Patient(3, "Van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 2.10));
    }

    void displayAllPatients() {
      for (Patient p : patients) {
          System.out.format("===== Patient id=%d ==============================\n", p.id);
          System.out.println("Name: " + p.firstName + " " + p.surname);
          System.out.println("DOB: " + p.dateOfBirth);
          System.out.println("Weight(KG): " + p.Weight);
          System.out.println("Height(M): " + p.Height);
      }
    }

    void searchPatientId(int id) {
        for (Patient p : patients) {
            if (p.id==id) {
                p.viewData();
            }
        }
    }

    void searchPatientName(String patientName) {
        String cleanPatientName = patientName.toLowerCase().replace(" ", "");
       for (Patient p : patients) {
           String searchResult = p.firstName + p.surname;
           searchResult = searchResult.toLowerCase().replace(" ", "");
           if (searchResult.equals(cleanPatientName)) {
               p.viewData();
           }
       }
    }

    public void addPatient(String lastName, String firstName, LocalDate dob, double weight, double height) {
        int ID = patients.size() + 1;
        patients.add(new Patient(ID, lastName, firstName, dob, weight, height));
    }

    void deletePatient (int deleteID) {
        patients.removeIf(p -> p.id == deleteID);
    }
}
