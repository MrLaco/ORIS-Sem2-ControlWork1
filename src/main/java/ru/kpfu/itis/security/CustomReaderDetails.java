package ru.kpfu.itis.security;

import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import ru.kpfu.itis.model.*;

import java.util.*;

@AllArgsConstructor
public class CustomReaderDetails implements UserDetails {

    private Reader reader;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        reader.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return authorities;
    }

    @Override
    public String getPassword() {
        return reader.getPassword();
    }

    @Override
    public String getUsername() {
        return reader.getEmail();
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
}
