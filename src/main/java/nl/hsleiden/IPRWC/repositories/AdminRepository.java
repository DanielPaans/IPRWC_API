package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.Admin;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface AdminRepository extends UserRepository<Admin> {
    Optional<Admin> findAdminByUsername(String username);
}
