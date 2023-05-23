package Harvest.Data;

import Harvest.Models.Farmer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FarmerRepository {
    List<Farmer> findAll();

    Farmer findById(int farmerId);

    Farmer add(Farmer farmer);

    boolean update(Farmer farmer);

    @Transactional
    boolean deleteById(int farmerId);

    Farmer findByAppUserId(int appUserId);
}
