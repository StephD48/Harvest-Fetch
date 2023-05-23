package Harvest.Data;

import Harvest.Models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductRepository {


    List<Product> findAll();

    @Transactional
    Product findById(int productId);

    Product add(Product product);

    boolean update(Product product);

    @Transactional
    boolean deleteById(int productId);
}
