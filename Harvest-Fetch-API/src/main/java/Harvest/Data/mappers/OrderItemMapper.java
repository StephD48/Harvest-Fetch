package Harvest.Data.mappers;

import Harvest.Models.OrderItem;
import Harvest.Models.Orders;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPriceCode("price_code");
        orderItem.setFarmerId(rs.getInt("farmer_id"));
        orderItem.setProductId(rs.getInt("product_id"));
        return orderItem;
    }
}
