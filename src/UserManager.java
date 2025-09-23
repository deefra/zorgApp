import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    private final ArrayList<User> users = new ArrayList<>();

    public UserManager () {
        users.add(new User ("Admin", "Admin", 1, 3));
        users.add(new User("Ben Huisarts", "1234", 2, 0));
        users.add(new User("Ben Apotheker", "1234", 3, 0));
        users.add(new User("Ben Tandarts", "1234", 4, 0));
    }

    User loginUser (Scanner scanner) {
        User foundUser = null;
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for(User u : users) {
            if (u.userName.equals(username) && u.password.equals(password)) {
                foundUser = u;
            }
        } return foundUser;
    }
}
