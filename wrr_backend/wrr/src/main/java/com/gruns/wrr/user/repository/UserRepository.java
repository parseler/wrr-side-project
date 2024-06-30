package com.gruns.wrr.user.repository;

import com.gruns.wrr.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}