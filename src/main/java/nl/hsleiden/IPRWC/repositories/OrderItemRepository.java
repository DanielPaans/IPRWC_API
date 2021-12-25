package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Modifying
    @Query(value = "INSERT INTO order_item VALUES (:id, :amount, :orderId, :productId);", nativeQuery = true)
    void storeOrderItem(@Param("id") UUID id, @Param("amount") int amount,
                        @Param("orderId") UUID orderId, @Param("productId") UUID productId);
}
