package Harvest.Data;

import Harvest.Models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 11;

    @Autowired
    ProductJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Product> products = repository.findAll();
        assertNotNull(products);
        assertTrue(products.size() >= 8 && products.size() <= 11);
    }

    @Test
    void shouldFindBroccoli(){
        Product product = repository.findById(1);
        assertEquals(1, product.getProductId());
        assertEquals("Broccoli", product.getProductName());
    }

    @Test
    void shouldAdd(){
        Product product = makeProduct();
        Product actual = repository.add(product);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getProductId());
    }

    @Test
    void shouldUpdate(){
        Product product = makeProduct();
        product.setProductId(3);
        assertTrue(repository.update(product));
        product.setProductId(13);
        assertFalse(repository.update(product));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private Product makeProduct(){
        Product product = new Product();
        product.setProductName("Apple");
        product.setPictureUrl("https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        return product;
    }
}