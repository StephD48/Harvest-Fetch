package Harvest.Data.mappers;

import Harvest.Models.ProductFarmer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductFarmerMapper implements RowMapper<ProductFarmer> {
    @Override
    public ProductFarmer mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductFarmer productFarmer = new ProductFarmer();
        productFarmer.setProductId(rs.getInt("product_id"));
        productFarmer.setPrice(rs.getBigDecimal("price"));
        productFarmer.setPrice(rs.getBigDecimal("is_active"));
        productFarmer.setOrganic(rs.getBoolean("organic"));

        FarmerMapper farmerMapper = new FarmerMapper();
        productFarmer.setFarmer(farmerMapper.mapRow(rs, rowNum));

        return productFarmer;
    }
}
