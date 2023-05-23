package Harvest.Domain;

import Harvest.Data.OrderItemRepository;
import Harvest.Models.Farmer;
import Harvest.Models.OrderItem;
import Harvest.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    public OrderItem findById(int orderItemId) {
        return repository.findById(orderItemId);
    }

    public Result<OrderItem> add(OrderItem orderItem) {
        Result<OrderItem> result = validate(orderItem);
        if (!result.isSuccess()) {
            return result;
        }

        if (orderItem.getOrderItemId() != 0) {
            result.addMessage("orderItemId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        orderItem = repository.add(orderItem);
        result.setPayload(orderItem);
        return result;
    }

    public Result<OrderItem> update(OrderItem orderItem) {
        Result<OrderItem> result = validate(orderItem);
        if (!result.isSuccess()) {
            return result;
        }
        if (orderItem.getOrderItemId() <= 0) {
            result.addMessage("orderItemId must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (!repository.update(orderItem)) {
            String msg = String.format("orderItemId: %s, not found", orderItem.getOrderItemId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result deleteById(int orderItemId) {
        Result result = new Result();
        if (!repository.deleteById(orderItemId)) {
            result.addMessage("Order Item Id " + orderItemId + "was not found.", ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<OrderItem> validate(OrderItem orderItem) {
        Result<OrderItem> result = new Result<>();
        if (orderItem == null) {
            result.addMessage("orderItem cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(String.valueOf(orderItem.getOrderId()))) {
            result.addMessage("order id is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(String.valueOf(orderItem.getQuantity()))) {
            result.addMessage("Quantity is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(String.valueOf(orderItem.getFarmerId()))) {
            result.addMessage("Farmer id is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(String.valueOf(orderItem.getProductId()))){
            result.addMessage("Product id is required", ResultType.INVALID);
        }

        return result;
    }
}
