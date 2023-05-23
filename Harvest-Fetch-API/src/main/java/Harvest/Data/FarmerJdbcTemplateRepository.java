package Harvest.Data;

import Harvest.Data.mappers.FarmerMapper;
import Harvest.Data.mappers.FarmerProductMapper;
import Harvest.Models.Farmer;
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
public class FarmerJdbcTemplateRepository implements FarmerRepository {
    private final JdbcTemplate jdbcTemplate;
    public FarmerJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Farmer> findAll(){
        final String sql = "select farmer_id, farm_name, farm_photo_url, details, user_id "
                + "from farmer;";
        return jdbcTemplate.query(sql, new FarmerMapper());
    }

    @Override
    public Farmer findById(int farmerId) {

        final String sql = "select farmer_id, farm_name, farm_photo_url, details, user_id "
                + "from farmer "
                + "where farmer_id = ?;";

        Farmer farmer = jdbcTemplate.query(sql, new FarmerMapper(), farmerId).stream()
                .findFirst()
                .orElse(null);
        if (farmer != null){
            addProducts(farmer);
        }
        return farmer;
    }

    @Override
    public Farmer add(Farmer farmer) {

        final String sql = "insert into farmer (farm_name, farm_photo_url, details, user_id) "
                + " values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, farmer.getFarmName());
            ps.setString(2, farmer.getPhotoUrl());
            ps.setString(3, farmer.getDetails());
            ps.setInt(4, farmer.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        farmer.setFarmerId(keyHolder.getKey().intValue());
        return farmer;
    }

    @Override
    public boolean update(Farmer farmer) {

        final String sql = "update farmer set "
                + "farm_name = ?, "
                + "farm_photo_url = ?, "
                + "details = ?, "
                + "user_id = ? "
                + "where farmer_id = ?;";

        return jdbcTemplate.update(sql,
                farmer.getFarmName(),
                farmer.getPhotoUrl(),
                farmer.getDetails(),
                farmer.getUserId(),
                farmer.getFarmerId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int farmerId) {
        jdbcTemplate.update("delete from farmer_product where farmer_id = ?;", farmerId);
        return jdbcTemplate.update("delete from farmer where farmer_id = ?;", farmerId) > 0;
    }

    @Override
    public Farmer findByAppUserId(int appUserId) {
        final String sql = "select farmer_id, farm_name, farm_photo_url, details, user_id "
                + "from farmer "
                + "where user_id = ?;";

        Farmer farmer = jdbcTemplate.query(sql, new FarmerMapper(), appUserId).stream()
                .findFirst()
                .orElse(null);
        if (farmer != null){
            addProducts(farmer);
        }
        return farmer;
    }

    private void addProducts(Farmer farmer) {

        final String sql = "select fp.farmer_id, fp.product_id, fp.price, fp.is_active, fp.organic, "
                + "p.product_id, p.product_name, p.picture_url "
                + "from farmer_product fp "
                + "inner join product p on fp.product_id = p.product_id "
                + "where fp.farmer_id = ? and fp.is_active = true";

        var farmerProducts = jdbcTemplate.query(sql, new FarmerProductMapper(), farmer.getFarmerId());
        farmer.setProducts(farmerProducts);
    }


}
