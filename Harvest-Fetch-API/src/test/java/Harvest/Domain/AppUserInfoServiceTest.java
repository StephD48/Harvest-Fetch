package Harvest.Domain;

import Harvest.Data.AppUserInfoRepository;
import Harvest.Data.FarmerRepository;
import Harvest.Models.AppUserInfo;
import Harvest.Models.Farmer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static Harvest.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserInfoServiceTest {

    @Autowired
    AppUserInfoService service;

    @MockBean
    AppUserInfoRepository AppUserInfoRepository;

    @Test
    void add() {
        AppUserInfo appUserInfo = makeUser();
        AppUserInfo mockOut = makeUser();
        mockOut.setAppUserId(13);

        when(AppUserInfoRepository.add(appUserInfo)).thenReturn(mockOut);

        Result<AppUserInfo> actual = service.add(appUserInfo);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void update() {
        Result<AppUserInfo> expected = makeResult(null);
        when(AppUserInfoRepository.update(any())).thenReturn(true);
        when(AppUserInfoRepository.findById(anyInt())).thenReturn(makeUser());
        AppUserInfo arg = new AppUserInfo(1, "Lenore", "Howell", "1726 N 8th St.", "53212", "Milwaukee", "WI", "lenoreiscool@gmail.com", "3025462954", "something.com");
        Result<AppUserInfo> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        when(AppUserInfoRepository.deleteById(anyInt())).thenReturn(true);

        Result result = service.deleteById(anyInt());

        assertTrue(result.isSuccess());
    }

    private AppUserInfo makeUser(){
        AppUserInfo appUserInfo = new AppUserInfo();
        appUserInfo.setFirstName("Lenore");
        appUserInfo.setLastName("Howell");
        appUserInfo.setAddress("1726 N 8th St.");
        appUserInfo.setZipCode("53212");
        appUserInfo.setCity("Milwaukee");
        appUserInfo.setState("WI");
        appUserInfo.setEmail("lenoreiscool@gmail.com");
        appUserInfo.setPhone("3025462954");
        appUserInfo.setPhotoUrl("something.com");
        return appUserInfo;
    }
}