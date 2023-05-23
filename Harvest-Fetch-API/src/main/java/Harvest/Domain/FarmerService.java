package Harvest.Domain;

import Harvest.Data.FarmerProductRepository;
import Harvest.Data.FarmerRepository;
import Harvest.Models.Farmer;
import Harvest.Models.FarmerProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    private final FarmerRepository repository;
    private final FarmerProductRepository farmerProductRepository;

    public FarmerService(FarmerRepository repository, FarmerProductRepository farmerProductRepository) {
        this.repository = repository;
        this.farmerProductRepository = farmerProductRepository;
    }

    public List<Farmer> findAll() {
        return repository.findAll();
    }

    public Farmer findById(int farmerId) {
        return repository.findById(farmerId);
    }

    public Result<Farmer> add(Farmer farmer) {
        Result<Farmer> result = validate(farmer);
        if (!result.isSuccess()) {
            return result;
        }

        if (farmer.getFarmerId() != 0) {
            result.addMessage("farmerId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        farmer = repository.add(farmer);
        result.setPayload(farmer);
        return result;
    }

    public Result<Farmer> update(Farmer farmer) {
        Result<Farmer> result = validate(farmer);
        if (!result.isSuccess()) {
            return result;
        }
        if (farmer.getFarmerId() <= 0) {
            result.addMessage("farmerId must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (!repository.update(farmer)) {
            String msg = String.format("farmerId: %s, not found", farmer.getFarmerId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result deleteById(int farmerId) {
        Result result = new Result();
        if (!repository.deleteById(farmerId)) {
            result.addMessage("Farmer id " + farmerId + "was not found.", ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Farmer> validate(Farmer farmer) {
        Result<Farmer> result = new Result<>();
        if (farmer == null) {
            result.addMessage("farmer cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(farmer.getFarmName())) {
            result.addMessage("Farm name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(farmer.getDetails())) {
            result.addMessage("Farm details are required", ResultType.INVALID);
        }

        return result;
    }

    public Result<Void> addProduct(FarmerProduct farmerProduct) {
        Result<Void> result = validateFarmerProduct(farmerProduct);
        if (!result.isSuccess()) {
            return result;
        }

        if (!farmerProductRepository.add(farmerProduct)) {
            result.addMessage("farmerProduct not added", ResultType.INVALID);
        }

        return result;
    }

    public Result<Void> updateProduct(FarmerProduct farmerProduct) {
        Result<Void> result = validateFarmerProduct(farmerProduct);
        if (!result.isSuccess()) {
            return result;
        }

        if (!farmerProductRepository.update(farmerProduct)) {
            String msg = String.format("update failed for farmerProduct id %s, farmer id %s: not found",
                    farmerProduct.getFarmerId(),
                    farmerProduct.getProduct().getProductId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteProductByKey(int farmerId, int productId) {
        return farmerProductRepository.deleteByKey(farmerId, productId);
    }

    private Result<Void> validateFarmerProduct(FarmerProduct farmerProduct) {
        Result<Void> result = new Result<>();
        if (farmerProduct == null) {
            result.addMessage("farmerProduct cannot be null", ResultType.INVALID);
        }

        if (farmerProduct.getProduct() == null) {
            result.addMessage("product cannot be null", ResultType.INVALID);
        }
        return result;
    }
}