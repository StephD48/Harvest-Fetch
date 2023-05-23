package Harvest.Controllers;

import Harvest.Data.DataAccessException;
import Harvest.Domain.FarmerService;
import Harvest.Domain.Result;
import Harvest.Domain.ResultType;
import Harvest.Models.Farmer;
import Harvest.Models.FarmerProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/farmer")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController (FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping
    public List<Farmer> findAll() {
        return farmerService.findAll();
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<Farmer> findById (@PathVariable int farmerId) {
        Farmer farmer = farmerService.findById(farmerId);
        if (farmer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(farmer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Farmer farmer) throws DataAccessException {
        Result result = farmerService.add(farmer);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{farmerId}")
    public ResponseEntity<?> update (@PathVariable int farmerId, @RequestBody Farmer farmer) throws DataAccessException {
        if (farmerId != farmer.getFarmerId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result result = farmerService.update(farmer);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{farmerId}")
    public ResponseEntity<Void> delete(@PathVariable int farmerId) throws DataAccessException {
        Result result = farmerService.deleteById(farmerId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
