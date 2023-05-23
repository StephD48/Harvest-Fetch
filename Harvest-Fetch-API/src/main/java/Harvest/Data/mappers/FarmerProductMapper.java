package Harvest.Data.mappers;

import Harvest.Models.FarmerProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmerProductMapper implements RowMapper<FarmerProduct> {
    @Override
    public FarmerProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        FarmerProduct farmerProduct = new FarmerProduct();
        farmerProduct.setFarmerId(rs.getInt("farmer_id"));
        farmerProduct.setProductId(rs.getInt("product_id"));
        farmerProduct.setPrice(rs.getBigDecimal("price"));
        farmerProduct.setOrganic(rs.getBoolean("organic"));

        ProductMapper productMapper = new ProductMapper();
        farmerProduct.setProduct(productMapper.mapRow(rs, rowNum));

        return farmerProduct;
    }
}
