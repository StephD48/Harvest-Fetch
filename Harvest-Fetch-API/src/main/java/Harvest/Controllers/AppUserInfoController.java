package Harvest.Controllers;

import Harvest.Data.AppUserRepository;
import Harvest.Data.DataAccessException;
import Harvest.Domain.AppUserInfoService;
import Harvest.Domain.Result;
import Harvest.Domain.ResultType;
import Harvest.Models.AppUser;
import Harvest.Models.AppUserInfo;
import Harvest.Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appUserInfo")
public class AppUserInfoController {

    private final AppUserInfoService appUserInfoService;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserInfoController (AppUserInfoService appUserInfoService, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserInfoService = appUserInfoService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<AppUserInfo> findAll() {
        return appUserInfoService.findAll();
    }

    @GetMapping("/{appUserInfoId}")
    public ResponseEntity<AppUserInfo> findById (@PathVariable int appUserInfoId) {
        AppUserInfo appUserInfo = appUserInfoService.findById(appUserInfoId);
        if (appUserInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appUserInfo, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> add(@RequestBody AppUserInfo appUserInfo) throws DataAccessException {
//        Result result = appUserInfoService.add(appUserInfo);
//        if (!result.isSuccess()) {
//            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody User user) {
        AppUserInfo appUserInfo = new AppUserInfo();
        appUserInfo.setFirstName(user.getFirstName());
        appUserInfo.setLastName(user.getLastName());
        appUserInfo.setAddress(user.getAddress());
        appUserInfo.setZipCode(user.getZipCode());
        appUserInfo.setCity(user.getCity());
        appUserInfo.setState(user.getState());
        appUserInfo.setEmail(user.getEmail());
        appUserInfo.setPhone(user.getPhone());
        appUserInfo.setPhotoUrl(user.getPhotoUrl());

        var result = appUserInfoService.add(appUserInfo);

        AppUser appUser = new AppUser();
        appUser.setUserName(user.getUserName());
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));
        appUser.setAppUserId(result.getPayload().getAppUserId());
        appUserRepository.add(appUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{appUserInfoId}")
    public ResponseEntity<?> update (@PathVariable int appUserInfoId, @RequestBody AppUserInfo appUserInfo) throws DataAccessException {
        if (appUserInfoId != appUserInfo.getAppUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result result = appUserInfoService.update(appUserInfo);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{appUserInfoId}")
    public ResponseEntity<Void> delete(@PathVariable int appUserInfoId) throws DataAccessException {
        Result result = appUserInfoService.deleteById(appUserInfoId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
