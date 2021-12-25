package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.OrderItem;
import nl.hsleiden.IPRWC.models.PlacedOrder;
import nl.hsleiden.IPRWC.repositories.OrderItemRepository;
import nl.hsleiden.IPRWC.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderDAO {

    private final OrderRepository ORDER_REPOSITORY;
    private final OrderItemRepository ORDER_ITEM_REPOSITORY;

    public OrderDAO(OrderRepository orderRepository, OrderItemRepository order_item_repository) {
        this.ORDER_REPOSITORY = orderRepository;
        ORDER_ITEM_REPOSITORY = order_item_repository;
    }

    public PlacedOrder storeOrder(PlacedOrder order) {
        PlacedOrder placedOrder = ORDER_REPOSITORY.save(order);
        for(OrderItem item : placedOrder.getOrderItems()) {
            ORDER_ITEM_REPOSITORY.storeOrderItem(   UUID.randomUUID(), item.getAmount(),
                                                    placedOrder.getId(), item.getProductId());
        }
        return placedOrder;
    }

    public void deleteOrder(UUID orderId) {
        ORDER_REPOSITORY.deleteById(orderId);
    }
}
