package nl.hsleiden.IPRWC.controllers;

import lombok.extern.java.Log;
import nl.hsleiden.IPRWC.dao.RoleDAO;
import nl.hsleiden.IPRWC.dao.UserDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.LoggedInUser;
import nl.hsleiden.IPRWC.models.Product;
import nl.hsleiden.IPRWC.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("${DEFAULT_PATH}${USER}")
@CrossOrigin(origins = {"https://www.rockingman.nl", "https://rockingman.nl"})
public class UserController {

    protected final UserDAO USER_DAO;
    protected final RoleDAO ROLE_DAO;

    public UserController(UserDAO userDAO, RoleDAO roleDAO) {
        USER_DAO = userDAO;
        ROLE_DAO = roleDAO;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        if(user.getRole() == null) { user.setRole(ROLE_DAO.getRole("ROLE_USER"));}
        return USER_DAO.storeUser(user);
    }

    //TODO: change to shoppingcart probably
//    @PostMapping("/{id}")
//    public ResponseEntity<Response> addProductToUser(@PathVariable UUID id, @RequestBody Product product) {
//        USER_DAO.addProductToUser(id, product);
//        return ResponseEntity.ok(new Response(product));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> changeCredentials(@PathVariable UUID id, @RequestParam("username") Optional<String> username,
                                                      @RequestParam("password") Optional<String> password) {
        username.ifPresent(s -> USER_DAO.changeUsername(id, s));
        password.ifPresent(s -> USER_DAO.changePassword(id, s));

        return ResponseEntity.ok(new Response("Updated credentials"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable UUID id) {
        USER_DAO.deleteUser(id);
        return ResponseEntity.ok(new Response("user deleted"));
    }

    public User getUser(String username) {
        return USER_DAO.getUser(username);
    }
}
