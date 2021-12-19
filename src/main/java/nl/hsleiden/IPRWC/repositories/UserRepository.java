package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, UUID> {

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
