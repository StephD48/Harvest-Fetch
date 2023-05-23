package Harvest.Domain;

import Harvest.Data.FarmerRepository;
import Harvest.Data.OrderItemRepository;
import Harvest.Models.Farmer;
import Harvest.Models.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static Harvest.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderItemServiceTest {

    @Autowired
    OrderItemService service;

    @MockBean
    OrderItemRepository orderItemRepository;

    @Test
    void shouldAdd() {
        OrderItem orderItem = makeOrderItem();
        OrderItem mockOut = makeOrderItem();
        mockOut.setOrderItemId(10);

        when(orderItemRepository.add(orderItem)).thenReturn(mockOut);

        Result<OrderItem> actual = service.add(orderItem);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void update() {
        Result<OrderItem> expected = makeResult(null);
        when(orderItemRepository.update(any())).thenReturn(true);
        when(orderItemRepository.findById(anyInt())).thenReturn(makeOrderItem());
        OrderItem arg = new OrderItem(3, 5, 3, "price_1LrQa7djDeJ2H8lxa19R8Mbb", 3, 9);
        Result<OrderItem> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        when(orderItemRepository.deleteById(anyInt())).thenReturn(true);

        Result result = service.deleteById(anyInt());

        assertTrue(result.isSuccess());
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