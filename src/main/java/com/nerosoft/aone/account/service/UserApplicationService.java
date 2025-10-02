package com.nerosoft.aone.account.service;

import com.nerosoft.aone.account.dto.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.AuthenticationException;

import java.util.concurrent.CompletableFuture;

public interface UserApplicationService {

    @Async
    CompletableFuture<UserProfileDto> getProfile() throws AuthenticationException;

    /**
     * Create a new user asynchronously.
     *
     * @param data the user creation data
     */
    @Async
    void create(UserCreateDto data);

    @Async
    void update(UserUpdateDto data) throws org.springframework.security.core.AuthenticationException;

    @Async
    void changePassword(String password);
}
