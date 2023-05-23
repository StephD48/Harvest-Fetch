package Harvest.Data;

import Harvest.Models.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersRepository {
    List<Orders> findAll();

    Orders findById(int orderId);

    Orders add(Orders order);

    boolean update(Orders order);

    @Transactional
    boolean deleteById(int orderId);
}
