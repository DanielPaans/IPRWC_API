package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.dao.OrderDAO;
import nl.hsleiden.IPRWC.exceptions.NoUserInOrderException;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.PlacedOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${DEFAULT_PATH}${ORDER}")
@CrossOrigin(origins = {"https://www.rockingman.nl", "https://rockingman.nl"})
public class OrderController {

    private final OrderDAO ORDER_DAO;

    public OrderController(OrderDAO order_dao) {
        ORDER_DAO = order_dao;
    }

    @PostMapping
    public PlacedOrder placeOrder(@RequestBody PlacedOrder order) throws NoUserInOrderException {
        return ORDER_DAO.storeOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> resolveOrder(@PathVariable UUID id) {
        ORDER_DAO.deleteOrder(id);
        return ResponseEntity.ok(new Response("Order resolved"));
    }
}
