import java.util.Scanner;

class ZorgApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User(1, "Demo");
        Administration administration = new Administration(user);
        administration.menu(scanner);
        scanner.close();
    }
}
