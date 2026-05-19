package com.ess.essserver.app.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Mock user for demonstration.
        // In a real application, retrieve the user from the database or another source.
        if ("user".equals(username)) {
            return User.builder()
                    .username(username)
                    .password(passwordEncoder.encode("1")) // {noop} means no password encoding
                    .roles("USER") // Assigning the role "USER"
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}