import java.util.ArrayList;
import java.util.Scanner;

public class MedicationManager {

    ArrayList<Medication> medications = new ArrayList<>();
    ArrayList<Prescriptions> prescriptions = new ArrayList<>();

    public MedicationManager () {
        medications.add(new Medication(1, "Fentanyl", 100));
        medications.add(new Medication(2, "Ketamine", 100));
        medications.add(new Medication(3, "Xanax", 100));

        prescriptions.add(new Prescriptions(1, 1, 300, "If you tweak, take a leak.", false));
        prescriptions.add(new Prescriptions(1, 2, 150, "", true));
        prescriptions.add(new Prescriptions(2, 3, 100, "First dosage in the morning when you wake up. Second dosage when you go to sleep in the evening.", true));
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
        System.out.print("New name(press enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            medication.setMedName(newName);
        }
        System.out.print("New quantity(press enter to keep current): ");
        String newQuantity = scanner.nextLine();
        try {
            if (!newQuantity.trim().isEmpty()) {
                medication.setMedQuantity(Integer.parseInt(newQuantity));
                System.out.println("Medication updated successfully!");
            }
        } catch (Exception e) {
            System.out.println("Invalid quantity input, keeping current...");
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

    void getPatientPrescription (int id, User currentUser) {
        for (Prescriptions p : prescriptions) {
            if (p.getPatientId() == id && currentUser.getRole() >= 2 || p.getPatientId() == id && p.getNarcoticStatus()) {
                Prescriptions.displayPrescripedMedication(p, this);
            }
        }
    }

    void addPrescriptions(int patientId, int medicationId, int dosage, String comment, boolean narcotic) {
        prescriptions.add(new Prescriptions(patientId, medicationId, dosage, comment, narcotic));
    }

    void editPrescriptions(Patient selectedPatient, Scanner scanner, User currentUser) {
        ArrayList<Prescriptions> patientPrescriptions = new ArrayList<>();
        if (selectedPatient == null) return;
        for(Prescriptions p: prescriptions) {
            if (p.getPatientId() == selectedPatient.getId()) {
                patientPrescriptions.add(p);
                System.out.println(p.getMedicationId() + ":" + " | " + getMedicationName(p.getMedicationId()) + " | " + p.getDosage() + "(MG)");
            }
        }

        boolean found = false;

        try {
            int choice = Administration.makeChoice(scanner);
            for (Prescriptions p: patientPrescriptions) {
                if (p.getMedicationId() == choice) {
                    if (currentUser.getRole() >= 2) {
                        System.out.print("Enter new dosage(MG): ");
                        int newDosage = scanner.nextInt();
                        scanner.nextLine(); // Clear scanner
                        p.setDosage(newDosage);
                        System.out.println("Dosage successfully updated!");
                        System.out.print("Enter new comment: ");
                        String newComment = scanner.nextLine();
                        p.setComment(newComment);
                        found = true;
                    } else {
                        System.out.print("Enter new comment: ");
                        String newComment = scanner.nextLine();
                        p.setComment(newComment);
                        System.out.println("Comment updated!");
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("Invalid choice!");
            }
        } catch (Exception e) {
            System.out.println("Error! Make sure your input is valid!");
            scanner.nextLine(); // Clear scanner after choice
        }


    }

    void deletePrescription (int patientId, int medicationId) {
        prescriptions.removeIf(p -> p.getPatientId() == patientId && p.getMedicationId() == medicationId);
    }
}
