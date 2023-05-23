package Harvest.Data;

import Harvest.Models.AppUser;
import Harvest.Models.Farmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FarmerJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 6;

    @Autowired
    FarmerJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllFarmers() {
        List<Farmer> farmers = repository.findAll();
        assertNotNull(farmers);
        assertTrue(farmers.size() > 0);
    }

    @Test
    void shouldFindMemphisFarm(){
        Farmer farmer = repository.findById(3);
        assertEquals(3, farmer.getFarmerId());
        assertEquals("Memphis Farm", farmer.getFarmName());
    }

    @Test
    void shouldAddFarm(){
        Farmer farmer = makeFarmer();
        farmer.setUserId(6);
        Farmer actual = repository.add(farmer);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getFarmerId());
    }

    @Test
    void shouldUpdateFarmer(){
        Farmer farmer = makeFarmer();
        farmer.setFarmerId(3);
        assertTrue(repository.update(farmer));
        farmer.setFarmerId(13);
        assertFalse(repository.update(farmer));
    }

    @Test
    void shouldDeleteById(){
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private Farmer makeFarmer(){
        Farmer farmer = new Farmer();
        farmer.setFarmName("Wisconsin Dairy Farm");
        farmer.setPhotoUrl("https://images.pexels.com/photos/1112080/pexels-photo-1112080.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        farmer.setDetails("Wisconsin Farm that provides great milk and cheese! Yummy!");
        farmer.setUserId(6);
        return farmer;
    }
}