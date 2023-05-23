package Harvest.Data;

import Harvest.Data.mappers.OrdersMapper;
import Harvest.Models.Orders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrdersJdbcTemplateRepository implements OrdersRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrdersJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Orders> findAll(){
        final String sql = "select order_id, order_date, order_total, user_id "
                + "from orders;";
        return jdbcTemplate.query(sql, new OrdersMapper());
    }

    @Override
    public Orders findById(int orderId) {

        final String sql = "select order_id, order_date, order_total, user_id "
                + "from orders "
                + "where order_id = ?;";

        return jdbcTemplate.query(sql, new OrdersMapper(), orderId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Orders add(Orders order) {

        final String sql = "insert into orders (order_date, order_total, user_id) "
                + " values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(order.getOrderDate()));
            ps.setBigDecimal(2, order.getOrderTotal());
            ps.setInt(3, order.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        order.setOrderId(keyHolder.getKey().intValue());
        return order;
    }

    @Override
    public boolean update(Orders order) {

        final String sql = "update orders set "
                + "order_date = ?, "
                + "order_total = ?, "
                + "user_id = ? "
                + "where order_id = ?;";

        return jdbcTemplate.update(sql,
                order.getOrderDate(),
                order.getOrderTotal(),
                order.getUserId(),
                order.getOrderId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int orderId) {
        jdbcTemplate.update("delete from order_item where order_id = ?;", orderId);
        return jdbcTemplate.update("delete from orders where order_id = ?;", orderId) > 0;
    }
}
