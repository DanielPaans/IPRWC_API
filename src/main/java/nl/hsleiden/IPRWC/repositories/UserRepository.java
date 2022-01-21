package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.Admin;
import nl.hsleiden.IPRWC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query(value = "UPDATE user SET password = :password WHERE id = :id ;", nativeQuery = true)
    void updatePassword(@Param("id") UUID id, @Param("password") String password);

    @Modifying
    @Query(value = "UPDATE user SET username = :username WHERE id = :id ;", nativeQuery = true)
    void updateUsername(@Param("id") UUID id, @Param("username") String username);

    @Modifying
    @Query(value = "UPDATE user SET email = :email WHERE id = :id ;", nativeQuery = true)
    void updateEmail(@Param("id") UUID id, @Param("email") String email);
}