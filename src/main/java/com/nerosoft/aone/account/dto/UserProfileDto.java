package com.nerosoft.aone.account.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private long id;
    private String username;
    private String email;
    private String phone;
    private String biography;
    private String nickname;
    private String avatar;
}
