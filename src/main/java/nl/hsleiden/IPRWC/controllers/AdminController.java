package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.dao.RoleDAO;
import nl.hsleiden.IPRWC.dao.UserDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("${DEFAULT_PATH}${ADMIN}")
@CrossOrigin(origins = {"https://www.rockingman.nl", "https://rockingman.nl"})
public class AdminController {

    private final UserDAO USER_DAO;
    private final RoleDAO ROLE_DAO;

    public AdminController(UserDAO adminDAO, RoleDAO roleDAO) {
        USER_DAO = adminDAO;
        ROLE_DAO = roleDAO;
    }

    @PostMapping
    public User createAdmin(@RequestBody User admin) {
        if(admin.getRole() == null) { admin.setRole(ROLE_DAO.getRole("ROLE_ADMIN"));}
        return USER_DAO.storeUser(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removeAdmin(@PathVariable UUID id) {
        USER_DAO.deleteUser(id);
        return ResponseEntity.ok(new Response("admin removed"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> changeCredentials(@PathVariable UUID id, @RequestParam("username")Optional<String> username,
                                                      @RequestParam("password") Optional<String> password) {
        username.ifPresent(s -> USER_DAO.changeUsername(id, s));
        password.ifPresent(s -> USER_DAO.changePassword(id, s));

        return ResponseEntity.ok(new Response("Updated admin credentials"));
    }
}
