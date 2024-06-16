package com.gruns.wrr.auth.repository;

import com.gruns.wrr.auth.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Boolean existsByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

    Boolean existsByToken(String token);

    @Transactional
    void deleteByToken(String token);

}
