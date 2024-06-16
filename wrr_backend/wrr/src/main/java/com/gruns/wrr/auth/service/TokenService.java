package com.gruns.wrr.auth.service;

import jakarta.servlet.http.Cookie;

public interface TokenService {
    public Cookie createCookie(String key, String value);
    public void addRefreshTokenEntity(String username, String token, Long expiredMs);
}
