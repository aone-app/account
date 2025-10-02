package com.nerosoft.aone.account.repository;

import com.nerosoft.aone.account.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
    public List<UserAuthority> findByUserId(Long userId);
}
