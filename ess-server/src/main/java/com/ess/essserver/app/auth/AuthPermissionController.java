package com.ess.essserver.app.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/permissions")
@AllArgsConstructor
@Tag(name = "Auth Permissions Management", description = "CRUD APIs for managing permissions")
public class AuthPermissionController {

    private final Logger logger = Logger.getLogger(AuthPermissionController.class.getName());

    @PostMapping()
    public void createPermission() {

    }

    @PutMapping("/{id}")
    public void updatePermission(@PathVariable String id) {

    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable String id) {

    }

    @GetMapping("/{id}")
    public void findPermission(@PathVariable String id) {

    }

    @GetMapping
    public void findAllPermission() {
    }

}