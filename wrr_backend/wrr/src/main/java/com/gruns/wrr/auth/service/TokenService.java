package com.gruns.wrr.auth.service;

import jakarta.servlet.http.Cookie;
import org.springframework.transaction.annotation.Transactional;

public interface TokenService {
    public Cookie createCookie(String key, String value);

    public void addRefreshTokenEntity(String username, String token, Long expiredMs);

    Boolean existsByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

    Boolean existsByToken(String token);

    @Transactional
    void deleteByToken(String token);
}
