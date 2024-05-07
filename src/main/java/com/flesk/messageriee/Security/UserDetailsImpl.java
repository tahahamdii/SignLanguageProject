package com.flesk.messageriee.Security;
import com.flesk.messageriee.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final String role;
    private String id;
    private String username;
    @Getter
    private String email;
    @Getter
    private String birthday;
    private String password;


    public UserDetailsImpl(String id, String username, String email, String birthday, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday= birthday;
        this.password = password;
        this.role=role;

    }


    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBirthday(),
                user.getPassword(),
                user.getRole()
                // Initialisez d'autres champs utilisateur au besoin
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        // Vous pouvez personnaliser les rôles/autorités selon vos besoins
    }


    public String getId(){ return  id;}

    @Override
    public String getPassword() {
        return password;
    }



    public String getRole() {
        return role;
    }

    @Override
    public String getUsername() {
        return username;
    }


    // Autres méthodes à implémenter (isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked)

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