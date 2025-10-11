package com.nerosoft.aone.account.service;

import an.awesome.pipelinr.Pipeline;
import com.nerosoft.aone.account.command.*;
import com.nerosoft.aone.account.dto.UserCreateDto;
import com.nerosoft.aone.account.dto.UserProfileDto;
import com.nerosoft.aone.account.dto.UserUpdateDto;
import com.nerosoft.aone.account.repository.UserRepository;
import com.nerosoft.aone.account.seedwork.CommandResult;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserRepository repository;
    private final AuthenticationFacade authentication;
    private final Pipeline pipeline;

    @Autowired
    public UserApplicationServiceImpl(UserRepository repository, AuthenticationFacade authentication, Pipeline pipeline) {
        this.repository = repository;
        this.authentication = authentication;
        this.pipeline = pipeline;
    }

    @Override
    public CompletableFuture<UserProfileDto> getProfile() throws AuthenticationException {
        var id = authentication.getUserId();

        if (id <= 0) {
            throw new BadCredentialsException("Authentication failed") {};
        }

        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        var user = optional.get();
        var dto = new UserProfileDto() {
            {
                setId(user.getId());
                setUsername(user.getUsername());
                setAvatar(user.getAvatar());
                setBiography(user.getBiography());
                setEmail(user.getEmail());
                setPhone(user.getPhone());
            }
        };

        return CompletableFuture.completedFuture(dto);
    }

    @Override
    public CompletableFuture<Long> create(UserCreateDto data) {
        var command = new UserCreateCommand(data);
        var result = pipeline.send(command);
        return result.thenApply(CommandResult::getResult);
    }

    @Override
    public void update(UserUpdateDto data) throws AuthenticationException {
        var id = authentication.getUserId();

        if (id<=0) {
            throw new AuthenticationException("Authentication failed") {
            };
        }

        var command = new UserUpdateCommand(id);
        pipeline.send(command);
    }

    @Override
    public void changePassword(String password) throws AuthenticationException {
        if(!authentication.getAuthentication().isAuthenticated()){
            throw new AuthenticationException("Authentication failed"){};
        }


    }
}
