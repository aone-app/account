package com.nerosoft.aone.account.handler;

import an.awesome.pipelinr.Command;
import com.nerosoft.aone.account.command.UserCreateCommand;
import com.nerosoft.aone.account.domain.User;
import com.nerosoft.aone.account.repository.UserRepository;
import com.nerosoft.aone.account.seedwork.CommandResult;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

@Component
public class UserCreateHandler implements Command.Handler<UserCreateCommand, CompletableFuture<CommandResult<Long>>> {
    private final UserRepository repository;

    @Autowired
    public UserCreateHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<CommandResult<Long>> handle(UserCreateCommand userCreateCommand) {
        return CompletableFuture.supplyAsync(() -> {

            Semaphore semaphore = new Semaphore(0);
            try {
                semaphore.acquire();

                var exists = checkUsernameExists(userCreateCommand.getData().getUsername());
                if (exists) {
                    throw new EntityExistsException("Username already exists");
                }

                var user = User.create(userCreateCommand.getData().getUsername());
                user.setPassword(userCreateCommand.getData().getPassword());

                repository.saveAndFlush(user);

                return new CommandResult<>(user.getId());

            } catch (Exception e) {
                return new CommandResult<>(0L, e.getMessage());
            } finally {
                semaphore.release();
            }
        });
    }

    private synchronized boolean checkUsernameExists(String username) {
        return repository.existsByUsername(username);
    }

}
