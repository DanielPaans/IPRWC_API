package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.LoggedInUser;
import nl.hsleiden.IPRWC.models.User;
import nl.hsleiden.IPRWC.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDAO {

    private final UserRepository USER_REPOSITORY;
    private final PasswordEncoder PASSWORD_ENCODER;

    public UserDAO(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        USER_REPOSITORY = userRepository;
        PASSWORD_ENCODER = passwordEncoder;
    }

    public User storeUser(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        User savedUser = USER_REPOSITORY.save(user);
        savedUser.setPassword(null);
        return savedUser;
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
// TODO: add to shoppingCart
//    public void addProductToUser(UUID id, Product product) {
//        USER_REPOSITORY.addToRecentlySearched(id, product.getId());
//    }

    public User getUser(UUID id) {
        return USER_REPOSITORY.getById(id);
    }

    public void deleteUser(UUID id) {
        USER_REPOSITORY.deleteById(id);
    }
}
