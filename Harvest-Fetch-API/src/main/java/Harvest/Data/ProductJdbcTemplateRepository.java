package Harvest.Data;

import Harvest.Data.mappers.ProductFarmerMapper;
import Harvest.Data.mappers.ProductMapper;
import Harvest.Models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductJdbcTemplateRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll(){
        final String sql = "select product_id, product_name, picture_url from product;";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    @Transactional
    public Product findById(int productId){
        final String sql = "select product_id, product_name, picture_url "
                + "from product "
                + "where product_id = ?;";

        Product result = jdbcTemplate.query(sql, new ProductMapper(), productId).stream()
                .findAny().orElse(null);

        return result;
    }

    @Override
    public Product add(Product product) {

        final String sql = "insert into product (product_name, picture_url) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getPictureUrl());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        product.setProductId(keyHolder.getKey().intValue());
        return product;
    }

    @Override
    public boolean update(Product product) {

        final String sql = "update product set "
                + "product_name = ?, "
                + "picture_url = ? "
                + "where product_id = ?";

        return jdbcTemplate.update(sql, product.getProductName(), product.getPictureUrl(), product.getProductId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int productId) {
        jdbcTemplate.update("delete from farmer_product where product_id = ?", productId);
        return jdbcTemplate.update("delete from product where product_id = ?", productId) > 0;
    }

    private void addFarmers(Product product) {

        final String sql = "select fp.farmer_id, fp.product_id, fp.price, fp.is_active, aa.organic, "
                + "p.product_id, p.product_name, p.picture_url "
                + "from farmer_product fp "
                + "inner join product p on fp.product_id = p.product_id "
                + "where p.product_id = ?";

        var productFarmers = jdbcTemplate.query(sql, new ProductFarmerMapper(), product.getProductId());
        product.setFarmers(productFarmers);
    }

}
