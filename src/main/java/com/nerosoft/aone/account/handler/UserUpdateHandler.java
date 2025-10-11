package com.nerosoft.aone.account.handler;

import an.awesome.pipelinr.Command;
import com.nerosoft.aone.account.command.UserUpdateCommand;
import com.nerosoft.aone.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class UserUpdateHandler implements Command.Handler<UserUpdateCommand, CompletableFuture<Boolean>> {
    private final UserRepository repository;

    @Autowired
    public UserUpdateHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<Boolean> handle(UserUpdateCommand userUpdateCommand) {
        return CompletableFuture.supplyAsync(() -> {
            if (userUpdateCommand.getId() <= 0) {
                return false;
            }

            var future = repository.findById(userUpdateCommand.getId());

            if (future.isEmpty()) {
                return false;
            }

            var user = future.get();

            user.setEmail(userUpdateCommand.getEmail());
            user.setPhone(userUpdateCommand.getPhone());
            user.setBiography(userUpdateCommand.getBiography());
            user.setNickname(userUpdateCommand.getNickname());

            repository.saveAndFlush(user);

            return true;
        });
    }
}
