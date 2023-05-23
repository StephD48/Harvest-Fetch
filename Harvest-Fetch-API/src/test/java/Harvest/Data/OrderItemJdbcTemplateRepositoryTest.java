package Harvest.Data;

import Harvest.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderItemJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 10;

    @Autowired
    OrderItemJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<OrderItem> orderItem = repository.findAll();
        assertNotNull(orderItem);
        assertTrue(orderItem.size() >= 8 && orderItem.size() <= 11);
    }

    @Test
    void findById() {
        OrderItem orderItem = repository.findById(1);
        assertEquals(1, orderItem.getOrderItemId());
    }

    @Test
    void add() {
        OrderItem orderItem = makeOrderItem();
        OrderItem actual = repository.add(orderItem);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getOrderItemId());
    }

    @Test
    void update() {
        OrderItem orderItem = makeOrderItem();
        orderItem.setOrderItemId(4);
        assertTrue(repository.update(orderItem));
        orderItem.setOrderItemId(130);
        assertFalse(repository.update(orderItem));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    OrderItem makeOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(5);
        orderItem.setQuantity(3);
        orderItem.setPriceCode("price_1LrQa7djDeJ2H8lxa19R8Mbb");
        orderItem.setFarmerId(3);
        orderItem.setProductId(9);
        return orderItem;
    }
}