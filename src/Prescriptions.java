public class Prescriptions {

    private int patientId;
    private int medicationId;
    private int dosage;
    private boolean narcotic;
    private String comment;

    // Getters

    public int getPatientId () {return patientId;}
    public int getMedicationId  () {return medicationId;}
    public int getDosage () {return dosage;}
    public boolean getNarcoticStatus() {return narcotic;}

    public void setDosage(int newDosage) {dosage = newDosage;}
    public void setComment (String newComment) {comment = newComment;}

    Prescriptions(int patientId, int medicationId, int dosage, String comment, boolean narcotic) {
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.narcotic = narcotic;
        this.comment = comment;
    }

    static void displayPrescripedMedication(Prescriptions p, MedicationManager medicationManager) {
        System.out.println();
        System.out.println("Medication: " + medicationManager.getMedicationName(p.getMedicationId()));
        System.out.println("Dosage(MG): " + p.dosage);

        if (!p.comment.isEmpty()) {
            System.out.println("Comment: " + p.comment);
        }

        System.out.println();
    }
}
