package Harvest.Domain;

import Harvest.Data.FarmerRepository;
import Harvest.Data.ProductRepository;
import Harvest.Models.Farmer;
import Harvest.Models.Product;
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
class ProductServiceTest {

    @Autowired
    ProductService service;

    @MockBean
    ProductRepository productRepository;

    @Test
    void shouldAdd() {
        Product product = makeProduct();
        Product mockOut = makeProduct();
        mockOut.setProductId(6);

        when(productRepository.add(product)).thenReturn(mockOut);

        Result<Product> actual = service.add(product);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void update() {
        Result<Product> expected = makeResult(null);
        when(productRepository.update(any())).thenReturn(true);
        when(productRepository.findById(anyInt())).thenReturn(makeProduct());
        Product arg = new Product(4, "apple", "some-image.com");
        Result<Product> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        Result<Void> expected = makeResult(null);
        when(productRepository.deleteById(anyInt())).thenReturn(true);
        assertTrue(service.deleteById(5));
    }

    private Product makeProduct(){
        Product product = new Product();
        product.setProductName("Apple");
        product.setPictureUrl("https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        return product;
    }
}