package com.insureapp.InsureApp.repository.security;

import com.insurapp.InsureApp.repository.model.Roles;
import com.insurapp.InsureApp.repository.model.User;
import com.insurapp.InsureApp.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InsurmeAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.getByName(id);

        if (user != null && user.getUserId() > 0 && passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorities = getGrantedAuth(user.getRoles());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), password, authorities);
            System.out.println("Authentication successful: " + token.isAuthenticated());
            return token;
        }
        return null;
    }


    private List<GrantedAuthority> getGrantedAuth(Roles roles){

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
