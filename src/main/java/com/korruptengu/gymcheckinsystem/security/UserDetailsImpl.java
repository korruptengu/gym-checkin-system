package com.korruptengu.gymcheckinsystem.security;

import com.korruptengu.gymcheckinsystem.entity.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("Role_" + user.getURole().name()));
    }

    public Long getId(){
        return id;
    }

    @Override public String getUsername() {
        return username;
    }
    @Override public String getPassword() {
        return password;
    }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override public boolean isAccountNonExpired() {
        return true;
    }
    @Override public boolean isAccountNonLocked() {
        return true;
    }
    @Override public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override public boolean isEnabled() {
        return true;
    }
}
