package com.nerosoft.aone.account.controller;

import com.nerosoft.aone.account.dto.UserCreateDto;
import com.nerosoft.aone.account.dto.UserProfileDto;
import com.nerosoft.aone.account.dto.UserUpdateDto;
import com.nerosoft.aone.account.service.UserApplicationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserApplicationService service;

    @Autowired
    public UserController(UserApplicationService service) {
        this.service = service;
    }

    /**
     * Get user profile
     *
     * @return UserProfileDto
     */
    @GetMapping("profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserProfileDto> getProfile() {
        var profile = service.getProfile();
        return ResponseEntity.ok(profile.join());
    }

    /**
     * Create a new user
     *
     * @param data UserCreateDto
     * @return User ID
     */
    @PostMapping
    public ResponseEntity<@NonNull Long> create(@RequestBody UserCreateDto data) {
        var id = service.create(data);
        return ResponseEntity.ok(id.join());
    }

    /**
     * Update user information
     *
     * @param data UserUpdateDto
     * @return success
     */
    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<@NonNull Boolean> update(@RequestBody UserUpdateDto data) {
        service.update(data);
        return ResponseEntity.ok(true);
    }


}
