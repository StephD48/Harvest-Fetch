package Harvest.Data.mappers;

import Harvest.Models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setAppUserId(rs.getInt("user_id"));
        appUser.setUserName(rs.getString("user_name"));
        appUser.setPassword(rs.getString("password_hash"));
//        appUser.setAppUserId(rs.getInt("user_info_id"));
        return appUser;
    }
}
