package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.LoggedInUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface LoggedInUserRepository extends UserRepository<LoggedInUser> {
    Optional<LoggedInUser> findLoggedInUserByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO user_product VALUES(:user, :product) ;",nativeQuery = true)
    void addToRecentlySearched(@Param("user") UUID id, @Param("product") UUID productId);
}
