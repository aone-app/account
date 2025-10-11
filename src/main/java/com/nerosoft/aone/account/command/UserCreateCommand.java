package com.nerosoft.aone.account.command;

import an.awesome.pipelinr.Command;
import com.nerosoft.aone.account.dto.UserCreateDto;
import com.nerosoft.aone.account.seedwork.CommandResult;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

/**
 * Command to create a new user
 */
public class UserCreateCommand implements Command<CompletableFuture<CommandResult<Long>>> {
    @Getter
    private final UserCreateDto data;

    public UserCreateCommand(UserCreateDto data) {
        this.data = data;
    }
}
