package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Admin;
import nl.hsleiden.IPRWC.repositories.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminDAO {

    private final AdminRepository ADMIN_REPOSITORY;
    private final PasswordEncoder PASSWORD_ENCODER;

    public AdminDAO(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        ADMIN_REPOSITORY = adminRepository;
        PASSWORD_ENCODER = passwordEncoder;
    }

    public List<Admin> findAdmins() {
        return ADMIN_REPOSITORY.findAll();
    }

    public Admin storeAdmin(Admin admin) {
        admin.setPassword(PASSWORD_ENCODER.encode(admin.getPassword()));
        return ADMIN_REPOSITORY.save(admin);
    }

    public void deleteAdmin(UUID id) {
        ADMIN_REPOSITORY.deleteById(id);
    }

    public void changeUsername(UUID id, String username) {
        ADMIN_REPOSITORY.updateUsername(id, username);
    }

    public void changePassword(UUID id, String password) {
        ADMIN_REPOSITORY.updatePassword(id, PASSWORD_ENCODER.encode(password));
    }
}
