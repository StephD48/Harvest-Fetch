package Harvest.Data;

import Harvest.Models.AppUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppUserRepository {
    List<AppUser> findAll();

    AppUser findById(int userId);

    AppUser add(AppUser appUser);

    @Transactional
    boolean deleteById(int appUserId);
}
