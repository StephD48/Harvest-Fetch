package Harvest.Data.mappers;

import Harvest.Models.Farmer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmerMapper implements RowMapper<Farmer> {

    @Override
    public Farmer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Farmer farmer = new Farmer();
        farmer.setFarmerId(rs.getInt("farmer_id"));
        farmer.setFarmName(rs.getString("farm_name"));
        farmer.setPhotoUrl(rs.getString("farm_photo_url"));
        farmer.setDetails(rs.getString("details"));
        farmer.setUserId(rs.getInt("user_id"));
        return farmer;
    }
}
