package com.nerosoft.aone.account.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public final class User {
    @Id
    public String id;

    @Column(unique = true)
    public String username;

    public String passwordHash;

    public String passwordSalt;

    public String nickname;

    public String email;

    public String phone;

    public String biography;


}
