package com.nerosoft.aone.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;

/**
 * This class represents a user in the system.
 * It contains user-related information and methods for managing user data.
 */
@Data
@Entity(name = "user")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(unique = true)
    public String username;

    public String passwordHash;

    public String passwordSalt;

    public String nickname;

    public String email;

    public String phone;

    public String biography;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public HashSet<UserAuthority> authorities = new HashSet<>();

    public static User Create(String username) {
        User user = new User();
        user.username = username;
        return user;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updatePassword(String password) {

    }

    public void addAuthority(String provider, String openId, String name) {
        UserAuthority authority = UserAuthority.create(this.id, provider, openId, name);
        this.authorities.add(authority);
    }
}
