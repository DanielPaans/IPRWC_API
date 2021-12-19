package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.LoggedInUser;
import nl.hsleiden.IPRWC.models.Product;
import nl.hsleiden.IPRWC.models.User;
import nl.hsleiden.IPRWC.repositories.LoggedInUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserDAO {

    private final LoggedInUserRepository USER_REPOSITORY;
    private final PasswordEncoder PASSWORD_ENCODER;

    public UserDAO(LoggedInUserRepository loggedInUserRepository, PasswordEncoder passwordEncoder) {
        USER_REPOSITORY = loggedInUserRepository;
        PASSWORD_ENCODER = passwordEncoder;
    }

    public LoggedInUser storeUser(LoggedInUser user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        return USER_REPOSITORY.save(user);
    }

    public List<LoggedInUser> getUsers() {
        return USER_REPOSITORY.findAll();
    }

    public void changeUsername(UUID id, String username) {
        USER_REPOSITORY.updateUsername(id, username);
    }

    public void changePassword(UUID id, String password) {
        USER_REPOSITORY.updatePassword(id, PASSWORD_ENCODER.encode(password));
    }

    public void changeEmail(UUID id, String email) {
        USER_REPOSITORY.updateEmail(id, email);
    }

    public void addProductToUser(UUID id, Product product) {
        USER_REPOSITORY.addToRecentlySearched(id, product.getId());
    }

    public User getUser(UUID id) {
        return USER_REPOSITORY.getById(id);
    }

    public void deleteUser(UUID id) {
        USER_REPOSITORY.deleteById(id);
    }
}
