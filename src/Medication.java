import java.lang.reflect.Array;
import java.util.ArrayList;

public class Medication {

    private int id;
    private String name;
    private int quantity;

    Medication(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Getters

    int getMedId () {return id;}
    String getMedName () {return name;}

    void viewMedication () {
        System.out.format("===== Medication id=%d ==============================\n", id);
        System.out.println();
        System.out.format("%-17s %s\n", "Name:", name);
        System.out.format("%-17s %s\n", "Quantity:", quantity);
        System.out.println();
    }

}

