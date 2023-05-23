package Harvest.Data;

import Harvest.Models.Farmer;
import Harvest.Models.FarmerProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FarmerProductJdbcTemplateRepositoryTest {

    @Autowired
    FarmerProductJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd(){
        FarmerProduct farmerProduct = makeFarmerProduct();
        assertTrue(repository.add(farmerProduct));
    }

    FarmerProduct makeFarmerProduct(){
        FarmerProduct farmerProduct = new FarmerProduct();
        farmerProduct.setFarmerId(5);
        farmerProduct.setProductId(1);
        farmerProduct.setPrice(BigDecimal.valueOf(2.93));
        farmerProduct.isActive(1);
        farmerProduct.isOrganic(true);
        return farmerProduct;
    }

    @Test
    void shouldUpdate(){
        FarmerProduct farmerProduct = makeFarmerProduct();
        farmerProduct.setProductId(4);
        farmerProduct.setFarmerId(1);
        assertTrue(repository.update(farmerProduct));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteByKey(1, 3));
        assertFalse(repository.deleteByKey(1, 3));
    }

}