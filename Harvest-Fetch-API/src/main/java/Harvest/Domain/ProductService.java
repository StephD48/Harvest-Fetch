package Harvest.Domain;

import Harvest.Data.FarmerProductRepository;
import Harvest.Data.FarmerRepository;
import Harvest.Data.ProductRepository;
import Harvest.Models.Farmer;
import Harvest.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final FarmerRepository farmerRepository;
    private FarmerProductRepository farmerProductRepository;

    public ProductService(ProductRepository repository, FarmerRepository farmerRepository, FarmerProductRepository farmerProductRepository) {
        this.repository = repository;
        this.farmerRepository = farmerRepository;
        this.farmerProductRepository = farmerProductRepository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(int productId) {
        return repository.findById(productId);
    }

    public Result<Product> add(Product product) {
        Result<Product> result = validate(product);
        if (!result.isSuccess()) {
            return result;
        }

        if (product.getProductId() != 0) {
            result.addMessage("productId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        product = repository.add(product);
        result.setPayload(product);
        return result;
    }

    public Result<Product> update(Product product) {
        Result<Product> result = validate(product);
        if (!result.isSuccess()) {
            return result;
        }
        if (product.getProductId() <= 0) {
            result.addMessage("productId must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (!repository.update(product)) {
            String msg = String.format("farmerId: %s, not found", product.getProductId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int productId) {
        return repository.deleteById(productId);
    }

    private Result<Product> validate(Product product) {
        Result<Product> result = new Result<>();
        if (product == null) {
            result.addMessage("product cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(product.getProductName())) {
            result.addMessage("Product name is required", ResultType.INVALID);
        }

        return result;
    }

    public boolean removeProductFromUser(int productId, int appUserId) {
        // Get the Farmer id based off the app user id
        Farmer farmer = farmerRepository.findByAppUserId(appUserId);
        // add null

        //use farmer id and product id to delete the farmer_product
        return farmerProductRepository.disableProductFarmer(productId, farmer.getFarmerId());
    }
}
