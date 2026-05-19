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
@RequestMapping("/roles")
@AllArgsConstructor
@Tag(name = "Auth Role Endpoints", description = "CRUD APIs for managing roles")
public class AuthRoleController {

    private final Logger logger = Logger.getLogger(AuthRoleController.class.getName());

    @PostMapping()
    public void createRole() {

    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable String id) {

    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable String id) {

    }

    @GetMapping("/{id}")
    public void findRole(@PathVariable String id) {

    }

    @GetMapping
    public void findAllRole() {
    }

}