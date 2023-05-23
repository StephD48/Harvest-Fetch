package Harvest.Data;

import Harvest.Models.OrderItem;
import Harvest.Models.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrdersJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 6;

    @Autowired
    OrdersJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Orders> orders = repository.findAll();
        assertNotNull(orders);
        assertTrue(orders.size() >= 4 && orders.size() <= 7);
    }

    @Test
    void findById() {
        Orders orders = repository.findById(1);
        assertEquals(1, orders.getOrderId());
        assertEquals(9, orders.getUserId());
    }

    @Test
    void add() {
        Orders orders = makeOrder();
        Orders actual = repository.add(orders);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getOrderId());
    }

    @Test
    void update() {
        Orders orders = makeOrder();
        orders.setOrderId(4);
        assertTrue(repository.update(orders));
        orders.setOrderId(130);
        assertFalse(repository.update(orders));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    Orders makeOrder(){
        Orders orders = new Orders();
        orders.setOrderDate(LocalDate.ofEpochDay(3/26/23));
        orders.setOrderTotal(BigDecimal.valueOf(3.29));
        orders.setUserId(10);
        return orders;
    }
}