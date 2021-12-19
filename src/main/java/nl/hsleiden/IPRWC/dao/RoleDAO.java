package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Role;
import nl.hsleiden.IPRWC.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleDAO {

    private final RoleRepository ROLE_REPOSITORY;

    public RoleDAO(RoleRepository roleRepository) {
        ROLE_REPOSITORY = roleRepository;
    }

    public Role getRole(String name) {
        return ROLE_REPOSITORY.findRoleByName(name);
    }
}
