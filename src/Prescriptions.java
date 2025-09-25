public class Prescriptions {

    private int patientId;
    private int medicationId;
    private int dosage;
    private String comment;

    // Getters

    public int getPatientId () {return patientId;}
    public int getMedicationId  () {return medicationId;}
    public int getDosage () {return dosage;}

    public void setDosage(int newDosage) {dosage = newDosage;}

    Prescriptions(int patientId, int medicationId, int dosage, String comment) {
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.comment = comment;
    }

    static void displayPrescripedMedication(Prescriptions p, MedicationManager medicationManager) {
        System.out.println();
        System.out.println("Medication: " + medicationManager.getMedicationName(p.getMedicationId()));
        System.out.println("Dosage(MG): " + p.dosage);
        System.out.println();
    }
}
