import java.util.Scanner;

class ZorgApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User(1, "Demo");
        Administration administration = new Administration(user);
        administration.menu(scanner);

        // Not going to use login while testing

//        System.out.println("First & last name: ");
//        String name = scanner.nextLine();
//
//        System.out.println("Password: ");
//        String password = scanner.nextLine();
//
//        if (!password.isEmpty()) {
//            System.out.println("Welcome " + name +"!");
//
//            User user = new User(1, name);
//            Administration administration = new Administration(user);
//
//            administration.menu(scanner);
//        } else  {
//            System.out.println("Invalid password!");
//        }

        scanner.close();
    }
}
