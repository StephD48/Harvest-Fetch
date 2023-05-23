package Harvest.Data;

import Harvest.Models.FarmerProduct;

public interface FarmerProductRepository {
    boolean add(FarmerProduct farmerProduct);

    boolean update(FarmerProduct farmerProduct);

    boolean deleteByKey(int productId, int farmerId);

    boolean disableProductFarmer(int productId, int farmerId);
}
