package Harvest.Controllers;


import Harvest.Domain.ProductService;
import Harvest.Domain.Result;
import Harvest.Models.AppUser;
import Harvest.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable int productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Product product) {
        Result<Product> result = productService.add(product);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> update(@PathVariable int productId, @RequestBody Product product) {
        if (productId != product.getProductId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Product> result = productService.update(product);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable int productId, @AuthenticationPrincipal AppUser appUser) {
        if (productService.removeProductFromUser(productId, appUser.getAppUserId())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
