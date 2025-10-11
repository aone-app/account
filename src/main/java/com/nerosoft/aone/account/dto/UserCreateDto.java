package com.nerosoft.aone.account.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    public String username;

    public String password;

    public String email;

    public String phone;
}
