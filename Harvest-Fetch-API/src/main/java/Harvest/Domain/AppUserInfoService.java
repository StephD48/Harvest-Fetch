package Harvest.Domain;

import Harvest.Data.AppUserInfoRepository;
import Harvest.Models.AppUserInfo;
import Harvest.Models.Farmer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserInfoService {

    private final AppUserInfoRepository repository;

    public AppUserInfoService(AppUserInfoRepository repository) {
        this.repository = repository;
    }

    public List<AppUserInfo> findAll() {
        return repository.findAll();
    }

    public AppUserInfo findById(int appUserInfoId) {
        return repository.findById(appUserInfoId);
    }

    public Result<AppUserInfo> add(AppUserInfo appUserInfo) {
        Result<AppUserInfo> result = validate(appUserInfo);
        if (!result.isSuccess()) {
            return result;
        }

        if (appUserInfo.getAppUserId() != 0) {
            result.addMessage("appUserId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        appUserInfo = repository.add(appUserInfo);
        result.setPayload(appUserInfo);
        return result;
    }

    public Result<AppUserInfo> update(AppUserInfo appUserInfo) {
        Result<AppUserInfo> result = validate(appUserInfo);
        if (!result.isSuccess()) {
            return result;
        }
        if (appUserInfo.getAppUserId() <= 0) {
            result.addMessage("appUserId must be set for `update` operation", ResultType.INVALID);
            return result;
        }
        if (!repository.update(appUserInfo)) {
            String msg = String.format("appUserId: %s, not found", appUserInfo.getAppUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result deleteById(int appUserId) {
        Result result = new Result();
        if (!repository.deleteById(appUserId)) {
            result.addMessage("App User id " + appUserId + "was not found.", ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<AppUserInfo> validate(AppUserInfo appUserInfo) {
        Result<AppUserInfo> result = new Result<>();
        if (appUserInfo == null) {
            result.addMessage("appUserInfo cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(appUserInfo.getFirstName())) {
            result.addMessage("First name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getLastName())) {
            result.addMessage("Last Name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getAddress())) {
            result.addMessage("Street Address is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getZipCode())) {
            result.addMessage("Zipcode is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getCity())) {
            result.addMessage("City is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getState())) {
            result.addMessage("State is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getEmail())) {
            result.addMessage("Email is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(appUserInfo.getPhone())) {
            result.addMessage("Phone number is required", ResultType.INVALID);
        }

        return result;
    }


}
