package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.Admin;
import nl.hsleiden.IPRWC.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AdminRepository extends UserRepository<Admin> {
    Optional<Admin> findAdminByUsername(String username);

}
