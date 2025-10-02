package com.nerosoft.aone.account.command;

import an.awesome.pipelinr.Command;
import com.nerosoft.aone.account.dto.UserCreateDto;
import com.nerosoft.aone.account.seedwork.CommandResult;

import java.util.concurrent.CompletableFuture;

public class UserCreateCommand implements Command<CompletableFuture<CommandResult<Long>>> {
    private final UserCreateDto data;
    public UserCreateCommand(UserCreateDto data) {
        this.data = data;
    }

    public UserCreateDto getData() {
        return data;
    }
}
