package Harvest.Data;

import Harvest.Models.AppUserInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppUserInfoRepository {
    List<AppUserInfo> findAll();

    AppUserInfo findById(int appUserInfoId);

    AppUserInfo add(AppUserInfo appUserInfo);

    boolean update(AppUserInfo appUserInfo);

    @Transactional
    boolean deleteById(int appUserId);
}
