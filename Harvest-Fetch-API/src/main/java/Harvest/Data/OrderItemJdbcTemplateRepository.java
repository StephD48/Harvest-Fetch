package Harvest.Data;

import Harvest.Data.mappers.OrderItemMapper;
import Harvest.Models.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderItemJdbcTemplateRepository implements OrderItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderItem> findAll(){
        final String sql = "select order_item_id, order_id, quantity, price_code, farmer_id, product_id "
                + "from order_item;";
        return jdbcTemplate.query(sql, new OrderItemMapper());
    }

    @Override
    public OrderItem findById(int orderItemId) {

        final String sql = "select order_item_id, order_id, quantity, price_code, farmer_id, product_id "
                + "from order_item "
                + "where order_item_id = ?;";

        return jdbcTemplate.query(sql, new OrderItemMapper(), orderItemId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public OrderItem add(OrderItem orderItem) {

        final String sql = "insert into order_item (order_id, quantity, price_code, farmer_id, product_id) "
                + " values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getQuantity());
            ps.setString(3, orderItem.getPriceCode());
            ps.setInt(4, orderItem.getFarmerId());
            ps.setInt(5, orderItem.getProductId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        orderItem.setOrderItemId(keyHolder.getKey().intValue());
        return orderItem;
    }

    @Override
    public boolean update(OrderItem orderItem) {

        final String sql = "update order_item set "
                + "quantity = ?, "
                + "price_code = ?, "
                + "farmer_id = ?, "
                + "product_id = ? "
                + "where order_item_id = ?;";

        return jdbcTemplate.update(sql,
                orderItem.getQuantity(),
                orderItem.getPriceCode(),
                orderItem.getFarmerId(),
                orderItem.getProductId(),
                orderItem.getOrderItemId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int orderItemId) {
        return jdbcTemplate.update("delete from order_item where order_item_id = ?;", orderItemId) > 0;
    }
}
