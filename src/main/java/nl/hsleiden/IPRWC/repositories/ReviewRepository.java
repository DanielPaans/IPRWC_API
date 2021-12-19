package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    @Query(value = "SELECT * FROM review WHERE user_id = :id ;", nativeQuery = true)
    List<Review> getReviewsByUserId(@Param("id") UUID id);

    @Modifying
    @Query(value = "UPDATE review SET description = :description WHERE id = :id ;", nativeQuery = true)
    void updateReviewDescription(@Param("id") UUID id, @Param("description") String description);

    @Modifying
    @Query(value = "UPDATE review SET rating = :rating WHERE id = :id ;", nativeQuery = true)
    void updateReviewRating(@Param("id") UUID id, @Param("rating") int rating);
}
