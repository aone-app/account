package com.nerosoft.aone.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
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
    public Long id;

    @Column(unique = true)
    public String username;

    @Column(name = "password_hash")
    public String passwordHash;

    @Column(name = "password_salt")
    public String passwordSalt;

    public String nickname;

    public String email;

    public String phone;

    public String biography;

    public String avatar;

    @Column(name = "access_failed_count")
    public int accessFailedCount;

    @Column(name = "lockout_end")
    public Date lockoutEnd;

    @Column(name = "is_deleted")
    public boolean isDeleted;

    @Column(name = "deleted_at")
    public Date deletedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public HashSet<UserAuthority> authorities = new HashSet<>();

    public static User create(String username) {
        User user = new User();
        user.username = username;
        return user;
    }

    public void setPassword(String password) {

    }

    public void addAuthority(String provider, String openId, String name) {
        UserAuthority authority = UserAuthority.create(this.id, provider, openId, name);
        this.authorities.add(authority);
    }
}
