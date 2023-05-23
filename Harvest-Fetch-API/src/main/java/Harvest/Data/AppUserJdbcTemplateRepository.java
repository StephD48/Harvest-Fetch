package Harvest.Data;

import Harvest.Data.mappers.AppUserMapper;
import Harvest.Models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {
    private final JdbcTemplate jdbcTemplate;

    // Why are there two RowMappers?
    private final RowMapper<AppUser> mapper = (resultSet, index) -> {
        AppUser user = new AppUser();
        user.setAppUserId(resultSet.getInt("user_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password_hash"));
//        user.setInfoId(resultSet.getInt("user_info_id"));
        return user;
    };

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public AppUser findByUserName(String userName) {

        String sql = "select user_id, user_name, password_hash from app_user where user_name = ?;";
        AppUser user = jdbcTemplate.query(sql, mapper, userName).stream()
                .findFirst().orElse(null);

        if (user != null) {
            addAuthorities(user);
        }
        return user;
    }

    private void addAuthorities(AppUser user) {

        String sql = "select a.name "
                + "from app_user_authority aua "
                + "inner join app_authority a on aua.app_authority_id = a.app_authority_id "
                + "where aua.user_id = ?";

        List<String> authorities = jdbcTemplate.query(
                sql,
                (rs, i) -> rs.getString("name"),
                user.getAppUserId()
        );
        user.addAuthorities(authorities);
    }

    @Override
    public List<AppUser> findAll(){
        final String sql = "select user_id, user_name, password_hash, user_info_id "
                + "from app_user;";
        return jdbcTemplate.query(sql, new AppUserMapper());
    }

    @Override
    public AppUser findById(int userId) {

        final String sql = "select user_id, user_name, password_hash, user_info_id "
                + "from app_user "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(), userId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public AppUser add(AppUser appUser) {

        final String sql = "insert into app_user (user_id, user_name, password_hash) "
                + " values (?,?,?);";
        jdbcTemplate.update(sql, appUser.getAppUserId(), appUser.getUsername(),appUser.getPassword());

        return appUser;
    }

    @Override
    @Transactional
    public boolean deleteById(int appUserId) {
        jdbcTemplate.update("delete from app_user_authority where user_id = ?;", appUserId);
        return jdbcTemplate.update("delete from app_user where user_id = ?;", appUserId) > 0;
    }
}
