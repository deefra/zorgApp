import java.util.Scanner;

class ZorgApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administration administration = new Administration(scanner);
        scanner.close();
    }
}
