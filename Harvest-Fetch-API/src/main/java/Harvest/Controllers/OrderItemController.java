package Harvest.Controllers;

import Harvest.Data.DataAccessException;
import Harvest.Domain.OrderItemService;
import Harvest.Domain.Result;
import Harvest.Domain.ResultType;
import Harvest.Models.OrderItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController (OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> findAll() {
        return orderItemService.findAll();
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> findById (@PathVariable int orderItemId) {
        OrderItem orderItem = orderItemService.findById(orderItemId);
        if (orderItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OrderItem orderItem) throws DataAccessException {
        Result result = orderItemService.add(orderItem);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<?> update (@PathVariable int orderItemId, @RequestBody OrderItem orderItem) throws DataAccessException {
        if (orderItemId != orderItem.getOrderItemId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result result = orderItemService.update(orderItem);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> delete(@PathVariable int orderItemId) throws DataAccessException {
        Result result = orderItemService.deleteById(orderItemId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
