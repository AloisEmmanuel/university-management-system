
public class AuthorizationService {

    public boolean hasAccess(User user, String requiredRole) {
        if (user == null) return false;
        return user.getRole().equals(requiredRole);
    }
}
