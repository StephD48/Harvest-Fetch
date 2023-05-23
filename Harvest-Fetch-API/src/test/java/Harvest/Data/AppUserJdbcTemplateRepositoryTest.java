package Harvest.Data;

import Harvest.Models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 13;

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllUsers() {
        List<AppUser> users = repository.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void shouldFindTestOne(){
        AppUser testOne = repository.findById(1);
        assertEquals(1, testOne.getAppUserId());
        assertEquals("user", testOne.getUsername());
    }

    @Test
    void shouldAddUser(){
        AppUser appUser = makeUser();
        AppUser actual = repository.add(appUser);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getAppUserId());
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private AppUser makeUser() {
        AppUser appUser = new AppUser();
        appUser.setUserName("TestSeven");
        return appUser;
    }
}