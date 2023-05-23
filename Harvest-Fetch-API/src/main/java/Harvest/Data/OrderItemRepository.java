package Harvest.Data;

import Harvest.Models.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> findAll();

    OrderItem findById(int orderItemId);

    OrderItem add(OrderItem orderItem);

    boolean update(OrderItem orderItem);

    @Transactional
    boolean deleteById(int orderItemId);
}
