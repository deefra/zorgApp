import java.time.LocalDate;
import java.time.Period;

class Patient {

   private int       id;
   private String    surname;
   private String    firstName;
   private LocalDate dateOfBirth;
   private Double    weight;
   private int   height;

    // Setters

    public void setFirstname(String name) {name = firstName;}
    public void setSurname (String lastName) {lastName = surname;}
    public void setDateofBirth (LocalDate dob) {dob = dateOfBirth;}
    public void setWeight (Double setWeight) {setWeight = weight;}
    public void setHeight (int setHeight) {setHeight = height;}

    // Getters

    public int getId () {return id;}
    public String getFirstName() {return firstName;}
    public String getSurname() {return surname;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public Double getWeight() {return weight;}
    public int getHeight() {return height;}

    /**
     * Constructor
     */


    Patient(int id, String surname, String firstName, LocalDate dateOfBirth,  Double Weight, int Height) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = Weight;
        this.height = Height;
    }


    double getBMI() {
        if (weight == null) {
            return 0;
        }
        double heightM = (double) height / 100;

        return  weight /  (heightM * heightM);
    }

    int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
    /**
     * Display patient data.
     */
    void displayDetailedPatient() {
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.println();
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "firstName:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth);
        System.out.format("%-17s %s\n", "Age:", getAge());
        System.out.format("%-17s %s\n", "Weight(KG):", weight);
        System.out.format("%-17s %s\n", "Height(CM):", height);
        System.out.format("%-17s %.1f\n", "BMI:", getBMI()); // %.1f formats BMI from xx.xxxx to xx.x
        System.out.println();
    }

}
