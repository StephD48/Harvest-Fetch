package Harvest.security;

import Harvest.Data.AppUserJdbcTemplateRepository;
import Harvest.Models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserJdbcTemplateRepository repository;


    public AppUserService(AppUserJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = repository.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }

        return user;
    }
}
