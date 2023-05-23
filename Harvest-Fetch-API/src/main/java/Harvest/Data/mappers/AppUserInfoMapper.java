package Harvest.Data.mappers;

import Harvest.Models.AppUser;
import Harvest.Models.AppUserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserInfoMapper implements RowMapper<AppUserInfo> {
    @Override
    public AppUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUserInfo app = new AppUserInfo();
        app.setAppUserId(rs.getInt("user_id"));
        app.setFirstName(rs.getString("first_name"));
        app.setLastName(rs.getString("last_name"));
        app.setAddress(rs.getString("street_address"));
        app.setZipCode(rs.getString("zip_code"));
        app.setCity(rs.getString("city"));
        app.setState(rs.getString("state"));
        app.setEmail(rs.getString("email"));
        app.setPhone(rs.getString("phone"));
        app.setPhotoUrl(rs.getString("photo_url"));
        return app;
    }
}
