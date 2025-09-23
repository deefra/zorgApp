import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MedicationManager {

    ArrayList<Medication> medications = new ArrayList<>();
    ArrayList<Prescriptions> prescriptions = new ArrayList<>();

    public MedicationManager () {
        medications.add(new Medication(1, "Fentanyl", 100));
        medications.add(new Medication(2, "Ketamine", 100));
        medications.add(new Medication(3, "Xanax", 100));

        prescriptions.add(new Prescriptions(1, 1, 300));
        prescriptions.add(new Prescriptions(1, 2, 150));
        prescriptions.add(new Prescriptions(2, 3, 100));
    }

    void displayAllMedication () {
        for (Medication m : medications) {
            m.viewMedication();
        }
    }

    String getMedicationName(int medicationId) {
        for (Medication m : medications) {
            if (medicationId == m.getMedId()) {
                return m.getMedName();
            }
        }
        return null;
    }

    void addPrescriptions(int patientId, int medicationId, int dosage) {
        prescriptions.add(new Prescriptions(patientId, medicationId, dosage));
    }

    void getPatientPrescription (int id) {

        for (Prescriptions p : prescriptions) {
            if (p.getPatientId() == id) {
                Prescriptions.displayPrescripedMedication(p, this);
            }
        }
    }

    void editPrescriptions(Patient selectedPatient, Scanner scanner) {
        ArrayList<Prescriptions> patientPrescriptions = new ArrayList<>();
        if (selectedPatient == null) return;
        for(Prescriptions p: prescriptions) {
            if (p.getPatientId() == selectedPatient.getId()) {
                patientPrescriptions.add(p);
                System.out.println(p.getMedicationId() + ":" + " | " + getMedicationName(p.getMedicationId()) + " | " + p.getDosage() + "(MG)");
            }
        }
        int choice = Administration.makeChoice(scanner);

        for (Prescriptions p: patientPrescriptions) {
            if (p.getMedicationId() == choice) {
                System.out.print("Enter new dosage(MG): ");
                int newDosage = scanner.nextInt();
                p.setDosage(newDosage);
                System.out.println("Dosage successfully updated!");
            }
        }
    }

    void deletePrescription (int patientId, int medicationId) {
        prescriptions.removeIf(p -> p.getPatientId() == patientId && p.getMedicationId() == medicationId);
    }
}
