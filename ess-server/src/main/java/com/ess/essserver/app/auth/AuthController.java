package com.ess.essserver.app.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping
@AllArgsConstructor
@Tag(name = "Auth Management", description = "CRUD APIs for managing auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtUtil;

    private final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        logger.info("Login request received for user: " + username);
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // Generate JWT
        String token = jwtUtil.generateJwtToken(authentication);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/logout")
    public void logout(Authentication authentication) {
    }

    @PostMapping("/refresh")
    public void refreshToken(Authentication authentication) {
    }
}