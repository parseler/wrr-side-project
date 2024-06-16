package com.gruns.wrr.auth.service;

import com.gruns.wrr.auth.entity.RefreshTokenEntity;
import com.gruns.wrr.auth.repository.RefreshTokenRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public TokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60);
        //cookie.setSecure(true); https 에서만 허용
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
    @Override
    public void addRefreshTokenEntity(String username, String token, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(username, token, date.toString());

        refreshTokenRepository.save(refreshTokenEntity);
    }
}
