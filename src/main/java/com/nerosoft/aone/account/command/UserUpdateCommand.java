package com.nerosoft.aone.account.command;

import an.awesome.pipelinr.Command;
import lombok.Data;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Data
public class UserUpdateCommand implements Command<CompletableFuture<Boolean>> {
    @Getter
    public long id;
    public String email;
    public String phone;
    public String biography;
    public String nickname;

    public UserUpdateCommand(long id) {
        this.id = id;
    }
}
