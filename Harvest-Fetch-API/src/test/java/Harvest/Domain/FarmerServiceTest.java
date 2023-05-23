package Harvest.Domain;

import Harvest.Data.FarmerRepository;
import Harvest.Models.Farmer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static Harvest.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FarmerServiceTest {

    @Autowired
    FarmerService service;

    @MockBean
    FarmerRepository farmerRepository;

    @Test
    void shouldAdd() {

    }

    @Test
    void update() {
        Result<Farmer> expected = makeResult(null);
        when(farmerRepository.update(any())).thenReturn(true);
        when(farmerRepository.findById(anyInt())).thenReturn(makeFarmer());
        Farmer arg = new Farmer(3, "Wisconsin Dairy Farmer", "https://images.pexels.com/photos/1112080/pexels-photo-1112080.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Wisconsin Farm that provides great milk and cheese! Yummy!", 1);
        Result<Farmer> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        when(farmerRepository.deleteById(anyInt())).thenReturn(true);

        Result result = service.deleteById(anyInt());

        assertTrue(result.isSuccess());
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