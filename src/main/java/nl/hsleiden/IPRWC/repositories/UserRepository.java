package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, UUID> {
}
