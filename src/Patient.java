import java.time.LocalDate;
import java.time.Period;

class Patient {
   static final int RETURN      = 0;
   static final int SURNAME     = 1;
   static final int FIRSTNAME   = 2;
   static final int DATEOFBIRTH = 3;
   static final int WEIGHT      = 4;
   static final int HEIGHT      = 5;

   int       id;
   String    surname;
   String    firstName;
   LocalDate dateOfBirth;
   Double    Weight;
   Double   Height;

    /**
     * Constructor
     */
    Patient(int id, String surname, String firstName, LocalDate dateOfBirth,  Double Weight, Double Height) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.Weight = Weight;
        this.Height = Height;
    }

    String getSurname() {
        return surname;
    }

    String getFirstName() {
        return firstName;
    }

    double getBMI() {
        if (Weight == null || Height == null) {
            return 0;
        }
        return  Weight /  (Height * Height);
    }

    int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
    /**
     * Display patient data.
     */
    void viewData() {
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "firstName:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth);
        System.out.format("%-17s %s\n", "Age:", getAge());
        System.out.format("%-17s %s\n", "Weight(KG):", Weight);
        System.out.format("%-17s %s\n", "Height(M):", Height);
        System.out.format("%-17s %.1f\n", "BMI:", getBMI()); // %.1f formats BMI from xx.xxxx to xx.x
    }

}
