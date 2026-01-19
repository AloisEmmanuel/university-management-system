

import java.util.List;

public class AuthService {
    private List<User> users;

    public AuthService(List<User> users) {
        this.users = users;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Login failed
    }
}
