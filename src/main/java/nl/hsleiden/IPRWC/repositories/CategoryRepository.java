package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = "SELECT * FROM category WHERE LOWER(name) = LOWER(:name);", nativeQuery = true)
    List<Category> findCategoryByName(@Param("name") String name);
}
