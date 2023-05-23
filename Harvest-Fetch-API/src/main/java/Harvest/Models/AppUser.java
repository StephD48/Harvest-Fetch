package Harvest.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUser implements UserDetails {

    private int appUserId;

    private String userName;

    private String password; // hash

    private int infoId;

    private ArrayList<GrantedAuthority> authorities = new ArrayList<>();

    public AppUser() {

    }

    public AppUser(String userName, String password, Collection<String> authorityNames) {
        this.userName = userName;
        this.password = password;
        addAuthorities(authorityNames);
    }

    public AppUser(int appUserId, String userName, int infoId) {
        this.appUserId = appUserId;
        this.userName = userName;
        this.infoId = infoId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void addAuthorities(Collection<String> authorityNames) {
        authorities.clear();
        for (String name : authorityNames) {
            authorities.add(new SimpleGrantedAuthority(name));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int nfoId) {
        this.infoId = infoId;
    }
}
