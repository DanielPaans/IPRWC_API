package nl.hsleiden.IPRWC.repositories;

import nl.hsleiden.IPRWC.models.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<PlacedOrder, UUID> {
}
