package Harvest.Data;

import Harvest.App;
import Harvest.Models.AppUser;
import Harvest.Models.AppUserInfo;
import Harvest.Models.Farmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Null;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserInfoJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 13;

    @Autowired
    AppUserInfoJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllUsers() {
        List<AppUserInfo> users = repository.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void shouldFindMike(){
        AppUserInfo appUserInfo = repository.findById(3);
        assertEquals(3, appUserInfo.getAppUserId());
        assertEquals("Mike", appUserInfo.getFirstName());
    }

    @Test
    void shouldAddUser(){
        AppUserInfo appUserInfo = makeUser();
        appUserInfo.setAppUserId(13);
        AppUserInfo actual = repository.add(appUserInfo);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getAppUserId());
    }

    @Test
    void shouldUpdateUser(){
        AppUserInfo appUserInfo = makeUser();
        appUserInfo.setAppUserId(3);
        assertTrue(repository.update(appUserInfo));
        appUserInfo.setAppUserId(20);
        assertFalse(repository.update(appUserInfo));
    }

    @Test
    void shouldDeleteById(){
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private AppUserInfo makeUser(){
        AppUserInfo appUserInfo = new AppUserInfo();
        appUserInfo.setFirstName("Lenore");
        appUserInfo.setLastName("Howell");
        appUserInfo.setAddress("1726 N 8th St.");
        appUserInfo.setZipCode("53212");
        appUserInfo.setCity("Milwaukee");
        appUserInfo.setState("WI");
        appUserInfo.setEmail("somethingnew@gmail.com");
        appUserInfo.setPhone("3025462954");
        appUserInfo.setPhotoUrl("something.com");
        return appUserInfo;
    }
}