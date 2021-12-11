package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.dao.AdminDAO;
import nl.hsleiden.IPRWC.dao.RoleDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${DEFAULT_PATH}${ADMIN}")
public class AdminController {

    private final AdminDAO ADMIN_DAO;
    private final RoleDAO ROLE_DAO;

    public AdminController(AdminDAO admin_dao, RoleDAO role_dao) {
        ADMIN_DAO = admin_dao;
        ROLE_DAO = role_dao;
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        if(admin.getRole() == null) { admin.setRole(ROLE_DAO.getRole("ROLE_ADMIN"));}
        return ADMIN_DAO.storeAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removeAdmin(@PathVariable UUID id) {
        ADMIN_DAO.deleteAdmin(id);
        return ResponseEntity.ok(new Response("admin removed"));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Response> changeCredentials(@PathVariable UUID id, @RequestParam("username")Optional<String> username,
//                                                      @RequestParam("password") Optional<String> password) {
//        username.ifPresent(s -> ADMIN_DAO.chan);
//    }
}
