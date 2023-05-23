package Harvest.Data;

import Harvest.Data.mappers.AppUserInfoMapper;
import Harvest.Models.AppUserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AppUserInfoJdbcTemplateRepository implements AppUserInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserInfoJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppUserInfo> findAll(){
        final String sql = "select user_id, first_name, last_name, street_address, zip_code, city, state, email, phone, photo_url "
                + "from app_user_info;";
        return jdbcTemplate.query(sql, new AppUserInfoMapper());
    }

    @Override
    public AppUserInfo findById(int appUserInfoId) {

        final String sql = "select user_id, first_name, last_name, street_address, zip_code, city, state, email, phone, photo_url "
                + "from app_user_info "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new AppUserInfoMapper(), appUserInfoId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public AppUserInfo add(AppUserInfo appUserInfo) {

        final String sql = "insert into app_user_info (first_name, last_name, street_address, zip_code, city, state, email, phone, photo_url) "
                + " values (?,?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUserInfo.getFirstName());
            ps.setString(2, appUserInfo.getLastName());
            ps.setString(3, appUserInfo.getAddress());
            ps.setString(4, appUserInfo.getZipCode());
            ps.setString(5, appUserInfo.getCity());
            ps.setString(6, appUserInfo.getState());
            ps.setString(7, appUserInfo.getEmail());
            ps.setString(8, appUserInfo.getPhone());
            ps.setString(9, appUserInfo.getPhotoUrl());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        appUserInfo.setAppUserId(keyHolder.getKey().intValue());
        return appUserInfo;
    }

    @Override
    public boolean update(AppUserInfo appUserInfo) {

        final String sql = "update app_user_info set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "street_address = ?, "
                + "zip_code = ?, "
                + "city = ?, "
                + "state = ?, "
                + "email = ?, "
                + "phone = ?, "
                + "photo_url = ? "
                + "where user_id = ?;";

        return jdbcTemplate.update(sql,
                appUserInfo.getFirstName(),
                appUserInfo.getLastName(),
                appUserInfo.getAddress(),
                appUserInfo.getZipCode(),
                appUserInfo.getCity(),
                appUserInfo.getState(),
                appUserInfo.getEmail(),
                appUserInfo.getPhone(),
                appUserInfo.getPhotoUrl(),
                appUserInfo.getAppUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int appUserId) {
        jdbcTemplate.update("delete from app_user_authority where user_id = ?;", appUserId);
        jdbcTemplate.update("delete from app_user where user_id = ?;", appUserId);
        return jdbcTemplate.update("delete from app_user_info where user_id = ?;", appUserId) > 0;
    }


}
