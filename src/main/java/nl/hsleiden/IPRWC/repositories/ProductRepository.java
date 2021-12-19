package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT p.id, amount, description, name, price, image_path " +
            "FROM product p JOIN product_category ON p.id = product_id " +
            "WHERE category_id = :id AND name LIKE :keyword% ;", nativeQuery = true)
    List<Product> findProductsByCategory(@Param("id") UUID id, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM product WHERE name LIKE :keyword% ; ", nativeQuery = true)
    List<Product> findProductsByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Query(value = "UPDATE product SET name = :name WHERE id = :id ;", nativeQuery = true)
    void updateProductName(@Param("id") UUID id, @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE product SET description = :description WHERE id = :id ;", nativeQuery = true)
    void updateProductDescription(@Param("id") UUID id, @Param("description") String description);

    @Modifying
    @Query(value = "UPDATE product SET amount = :amount WHERE id = :id ;", nativeQuery = true)
    void updateProductAmount(@Param("id") UUID id, @Param("amount") int amount);

    @Modifying
    @Query(value = "UPDATE product SET price = :price WHERE id = :id ;", nativeQuery = true)
    void updateProductPrice(@Param("id") UUID id, @Param("price") float price);

    @Modifying
    @Query(value = "UPDATE product SET imagePath = :imagePath WHERE id = :id ;", nativeQuery = true)
    void updateProductImage(@Param("id") UUID id, @Param("imagePath") String imagePath);

    @Modifying
    @Query(value = "INSERT INTO product_category VALUES(:product, :category) ;",nativeQuery = true)
    void addCategory(@Param("product") UUID id, @Param("category") UUID categoryId);

    @Modifying
    @Query(value = "DELETE FROM product_category WHERE product_id = :product AND category_id = :category ;",nativeQuery = true)
    void removeCategory(@Param("product") UUID id, @Param("category") UUID categoryId);
}
