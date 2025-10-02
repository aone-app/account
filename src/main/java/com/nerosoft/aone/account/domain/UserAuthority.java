package com.nerosoft.aone.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * This class represents the authority or role assigned to a user in the system.
 * It is used for managing user permissions and access control.
 */
@Data
@Entity(name = "user_authority")
public final class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    public String provider;

    @Column(name = "open_id")
    public String openId;

    public String name;

    @Column(name = "created_at")
    public Date createdAt;

    public static UserAuthority create(long userId, String provider, String openId, String name) {
        UserAuthority authority = new UserAuthority();
        authority.userId = userId;
        authority.provider = provider;
        authority.openId = openId;
        authority.name = name;
        authority.createdAt = new Date();
        return authority;
    }
}
