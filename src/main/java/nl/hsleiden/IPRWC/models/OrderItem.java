package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true, nullable = false)
    private UUID productId;
    @Column(nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private PlacedOrder order;

    public OrderItem() {}

    public OrderItem(UUID productId, int amount, PlacedOrder order) {
        this.productId = productId;
        this.amount = amount;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PlacedOrder getOrder() {
        return order;
    }

    public void setOrder(PlacedOrder order) {
        this.order = order;
    }
}
