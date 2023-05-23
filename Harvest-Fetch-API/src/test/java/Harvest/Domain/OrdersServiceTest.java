package Harvest.Domain;

import Harvest.Data.OrderItemRepository;
import Harvest.Data.OrdersRepository;
import Harvest.Models.OrderItem;
import Harvest.Models.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static Harvest.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrdersServiceTest {

    @Autowired
    OrdersService service;

    @MockBean
    OrdersRepository ordersRepository;

    @Test
    void shouldAdd() {
        Orders orders = makeOrder();
        Orders mockOut = makeOrder();
        mockOut.setOrderId(6);

        when(ordersRepository.add(orders)).thenReturn(mockOut);

        Result<Orders> actual = service.add(orders);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void update() {
        Result<Orders> expected = makeResult(null);
        when(ordersRepository.update(any())).thenReturn(true);
        when(ordersRepository.findById(anyInt())).thenReturn(makeOrder());
        Orders arg = new Orders(3, LocalDate.ofEpochDay(3/26/23), new BigDecimal(3.29), 10);
        Result<Orders> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        when(ordersRepository.deleteById(anyInt())).thenReturn(true);

        Result result = service.deleteById(anyInt());

        assertTrue(result.isSuccess());
    }

    Orders makeOrder(){
        Orders orders = new Orders();
        orders.setOrderDate(LocalDate.ofEpochDay(3/26/23));
        orders.setOrderTotal(BigDecimal.valueOf(3.29));
        orders.setUserId(10);
        return orders;
    }
}