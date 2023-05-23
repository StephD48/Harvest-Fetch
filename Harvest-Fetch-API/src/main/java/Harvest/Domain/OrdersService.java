package Harvest.Domain;

import Harvest.Data.OrdersRepository;
import Harvest.Models.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository repository;

    public OrdersService(OrdersRepository repository) {
        this.repository = repository;
    }

    public List<Orders> findAll() {
        return repository.findAll();
    }

    public Orders findById(int orderId) {
        return repository.findById(orderId);
    }

    public Result<Orders> add(Orders orders) {
        Result<Orders> result = validate(orders);
        if (!result.isSuccess()) {
            return result;
        }

        if (orders.getOrderId() != 0) {
            result.addMessage("orderId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        orders = repository.add(orders);
        result.setPayload(orders);
        return result;
    }

    public Result<Orders> update(Orders orders) {
        Result<Orders> result = validate(orders);
        if (!result.isSuccess()) {
            return result;
        }
        if (orders.getOrderId() <= 0) {
            result.addMessage("orderId must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (!repository.update(orders)) {
            String msg = String.format("orderId: %s, not found", orders.getOrderId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Orders> validate(Orders orders) {
        Result<Orders> result = new Result<>();
        if (orders == null) {
            result.addMessage("orders cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(String.valueOf(orders.getOrderDate()))) {
            result.addMessage("order date is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(String.valueOf(orders.getOrderTotal()))) {
            result.addMessage("Quantity is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(String.valueOf(orders.getUserId()))) {
            result.addMessage("Farmer id is required", ResultType.INVALID);
        }

        return result;
    }

    public Result deleteById(int orderId) {
        Result result = new Result();
        if (!repository.deleteById(orderId)) {
            result.addMessage("Order Item Id " + orderId + "was not found.", ResultType.NOT_FOUND);
        }
        return result;
    }
}
