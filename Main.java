
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {	
    public static void main(String[] args) {

        // Create system users
        List<User> users = new ArrayList<>();
        users.add(new User("admin", "123", "ADMIN"));
        users.add(new User("student1", "111", "STUDENT"));
        users.add(new User("instructor1", "222", "INSTRUCTOR"));

        // Create services
        AuthService authService = new AuthService(users);
        AuthorizationService authorizationService = new AuthorizationService();
        Session session = new Session();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        // Authenticate
        User loggedUser = authService.login(username, password);

        if (loggedUser == null) {
            System.out.println("❌ Invalid username or password!");
            return;
        }

        // Create session
        session.login(loggedUser);
        System.out.println("✔ Login successful! Role = " + loggedUser.getRole());

        // Example role check
        if (authorizationService.hasAccess(loggedUser, "ADMIN")) {
            System.out.println("→ Access granted: You can manage the system.");
        } else {
            System.out.println("→ Access denied: Not enough permissions.");
        }
    }
}
