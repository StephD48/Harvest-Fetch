package Harvest.Data;

import Harvest.Models.FarmerProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmerProductJdbcTemplateRepository implements FarmerProductRepository{
    private final JdbcTemplate jdbcTemplate;

    public FarmerProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(FarmerProduct farmerProduct) {

        final String sql = "insert into farmer_product (farmer_id, product_id, price, organic, is_active) "
                + " values (?,?,?,?,?);";

        return jdbcTemplate.update(sql,
                farmerProduct.getFarmerId(),
                farmerProduct.getProduct().getProductId(),
                farmerProduct.getPrice(),
                farmerProduct.isOrganic(true),
                farmerProduct.isActive(1)) > 0;
    }

    @Override
    public boolean update(FarmerProduct farmerProduct){
        final String sql = "update farmer_product set "
                + "price = ?, "
                + "is_active = ?, "
                + "organic = ? "
                + "where farmer_id = ? and product_id = ?;";

        return jdbcTemplate.update(sql,
                farmerProduct.getPrice(),
                farmerProduct.isActive(1),
                farmerProduct.isOrganic(true),
                farmerProduct.getFarmerId(),
                farmerProduct.getProduct().getProductId()) > 0;
    }

    @Override
    public boolean deleteByKey(int farmerId, int productId) {

        final String sql = "delete from farmer_product "
                + "where farmer_id = ? and product_id = ?;";

        return jdbcTemplate.update(sql, farmerId, productId) > 0;
    }

    @Override
    public boolean disableProductFarmer(int productId, int farmerId) {
        final String sql = "update farmer_product set "
                + "is_active = false "
                + "where product_id = ? and farmer_id = ?;";

        return jdbcTemplate.update(sql, productId, farmerId) > 0;
    }
}
