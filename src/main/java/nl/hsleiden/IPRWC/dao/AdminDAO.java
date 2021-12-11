package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Admin;
import nl.hsleiden.IPRWC.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminDAO {

    private final AdminRepository ADMIN_REPOSITORY;

    public AdminDAO(AdminRepository admin_repository) {
        ADMIN_REPOSITORY = admin_repository;
    }

    public Admin storeAdmin(Admin admin) {
        return ADMIN_REPOSITORY.save(admin);
    }

    public void deleteAdmin(UUID id) {
        ADMIN_REPOSITORY.deleteById(id);
    }

//    public String changePassword(UUID id, String password) {
//        ADMIN_REPOSITORY.updatePasswordById(i)
//    }
}
