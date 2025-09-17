import java.time.LocalDate;

public class Prescriptions {

    private int patientId;
    private int medicationId;
    private int dosage;

    // Getters

    public int getPatientId () {return patientId;}
    public int getMedicationId  () {return medicationId;}
    public int getDosage () {return dosage;}

    Prescriptions(int patientId, int medicationId, int dosage) {
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.dosage = dosage;
    }

    static void displayPrescripedMedication(Prescriptions p, MedicationManager medicationManager) {
        System.out.println();
        System.out.println("Medication: " + medicationManager.getMedicationName(p.getMedicationId()));
        System.out.println("Dosage(MG): " + p.dosage);
        System.out.println();
    }
}
