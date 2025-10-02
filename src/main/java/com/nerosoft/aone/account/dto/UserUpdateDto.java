package com.nerosoft.aone.account.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    public String email;

    public String phone;

    public String biography;

    public String nickname;
}
