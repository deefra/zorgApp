import java.util.ArrayList;
import java.util.Scanner;

public class MedicationManager {

    ArrayList<Medication> medications = new ArrayList<>();
    ArrayList<Prescriptions> prescriptions = new ArrayList<>();

    public MedicationManager () {
        medications.add(new Medication(1, "Fentanyl", 100));
        medications.add(new Medication(2, "Ketamine", 100));
        medications.add(new Medication(3, "Xanax", 100));

        prescriptions.add(new Prescriptions(1, 1, 300, "If you tweak, take a leak."));
        prescriptions.add(new Prescriptions(1, 2, 150, ""));
        prescriptions.add(new Prescriptions(2, 3, 100, "First dosage in the morning when you wake up. Second dosage when you go to sleep in the evening."));
    }

    Medication getMedication (String medicationNameOrID) {
        Medication foundMedication = null;

        try {
            int ID = Integer.parseInt(medicationNameOrID);
            foundMedication = searchMedicationByID(ID);
        } catch (NumberFormatException e) {
            foundMedication = searchMedicationByName(medicationNameOrID);
        }

        return foundMedication;
    }

    void editMedication(Medication medication, Scanner scanner) {
        medication.viewMedication();
        System.out.print("New name (press enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            medication.setMedName(newName);
        }
        System.out.print("New quantity: ");
        int newQuantity;
        try {
            newQuantity = scanner.nextInt();
            scanner.nextLine();
            if (newQuantity >= 0) {
                medication.setMedQuantity(newQuantity);
            }
        } catch (Exception e) {
            System.out.println("Invalid quantity input. Keeping current.");
            scanner.nextLine();
        }
        System.out.println("Medication updated successfully!");
    }

    String getMedicationName(int medicationId) {
        for (Medication m : medications) {
            if (medicationId == m.getMedId()) {
                return m.getMedName();
            }
        }
        return null;
    }

    void displayAllMedication () {
        for (Medication m : medications) {
            m.viewMedication();
        }
    }

    Medication searchMedicationByID (int id) {
        for (Medication m: medications) {
            if (m.getMedId()==id) {
                return m;
            }
        } return null;
    }

    Medication searchMedicationByName (String medicationName) {
        String cleanMedicationName = medicationName.toLowerCase().replace(" ", "");
        for(Medication m: medications) {
            if (cleanMedicationName.equals(m.getMedName().toLowerCase().replace(" ", ""))){
                return m;
            }
        } return null;
    }

    void getPatientPrescription (int id) {
        for (Prescriptions p : prescriptions) {
            if (p.getPatientId() == id) {
                Prescriptions.displayPrescripedMedication(p, this);
            }
        }
    }

    void addPrescriptions(int patientId, int medicationId, int dosage, String comment) {
        prescriptions.add(new Prescriptions(patientId, medicationId, dosage, comment));
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
