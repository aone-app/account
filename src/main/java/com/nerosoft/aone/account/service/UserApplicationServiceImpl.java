package com.nerosoft.aone.account.service;

import com.nerosoft.aone.account.dto.UserCreateDto;
import com.nerosoft.aone.account.dto.UserProfileDto;
import com.nerosoft.aone.account.dto.UserUpdateDto;
import com.nerosoft.aone.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.concurrent.CompletableFuture;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserRepository repository;
    private final AuthenticationFacade authentication;

    @Autowired
    public UserApplicationServiceImpl(UserRepository repository,AuthenticationFacade authentication) {
        this.repository = repository;
        this.authentication = authentication;
    }

    @Override
    public CompletableFuture<UserProfileDto> getProfile() throws AuthenticationException {
        if(!authentication.getAuthentication().isAuthenticated()){
            throw new AuthenticationException("Authentication failed"){};
        }

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void create(UserCreateDto data) {

    }

    @Override
    public void update(UserUpdateDto data) throws AuthenticationException {
        if(!authentication.getAuthentication().isAuthenticated()){
            throw new AuthenticationException("Authentication failed"){};
        }
    }

    @Override
    public void changePassword(String password) {

    }
}
