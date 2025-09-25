public class Medication {

    private int id;
    private String name;
    private int quantity;

    Medication(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    int getMedId () {return id;}
    String getMedName () {return name;}

    public void setMedName (String newName) {name = newName;}
    public void setMedQuantity (int newQuantity) {quantity = newQuantity;}

    void viewMedication () {
        System.out.format("===== Medication id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Name:", name);
        System.out.format("%-17s %s\n", "Quantity:", quantity);
    }

}

