package Harvest.Controllers;

import Harvest.Data.DataAccessException;
import Harvest.Domain.OrdersService;
import Harvest.Domain.Result;
import Harvest.Domain.ResultType;
import Harvest.Models.Orders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController (OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public List<Orders> findAll() {
        return ordersService.findAll();
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<Orders> findById (@PathVariable int ordersId) {
        Orders orders = ordersService.findById(ordersId);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Orders orders) throws DataAccessException {
        Result result = ordersService.add(orders);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{ordersId}")
    public ResponseEntity<?> update (@PathVariable int ordersId, @RequestBody Orders orders) throws DataAccessException {
        if (ordersId != orders.getOrderId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result result = ordersService.update(orders);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Void> delete(@PathVariable int ordersId) throws DataAccessException {
        Result result = ordersService.deleteById(ordersId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
