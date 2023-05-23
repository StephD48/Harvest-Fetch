package Harvest.Controllers;

import Harvest.Domain.FarmerService;
import Harvest.Domain.ProductService;
import Harvest.Domain.Result;
import Harvest.Models.Farmer;
import Harvest.Models.FarmerProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/agency/agent")
public class FarmerProductController {
    private final FarmerService service;

    public FarmerProductController(FarmerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody FarmerProduct farmerProduct) {
        Result<Void> result = service.addProduct(farmerProduct);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody FarmerProduct farmerProduct) {
        Result<Void> result = service.updateProduct(farmerProduct);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{farmerId}/{productId}")
    public ResponseEntity<Void> deleteByKey(@PathVariable int farmerId, @PathVariable int productId) {
        if (service.deleteProductByKey(farmerId, productId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
