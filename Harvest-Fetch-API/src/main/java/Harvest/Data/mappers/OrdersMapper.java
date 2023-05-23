package Harvest.Data.mappers;

import Harvest.Models.Orders;
import Harvest.Models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersMapper implements RowMapper<Orders> {
    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders orders = new Orders();
        orders.setOrderId(rs.getInt("order_id"));
        orders.setOrderDate(rs.getDate("order_date").toLocalDate());
        orders.setOrderTotal(rs.getBigDecimal("order_total"));
        orders.setUserId(rs.getInt("user_id"));
        return orders;
    }
}

