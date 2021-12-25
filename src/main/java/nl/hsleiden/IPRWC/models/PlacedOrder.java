package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class PlacedOrder {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(nullable = false)
    private UUID userId;

    public PlacedOrder() {}

    public PlacedOrder(List<OrderItem> orderItems, UUID userId) {
        this.orderItems = orderItems;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
