package com.gruns.wrr.auth.util;

import com.gruns.wrr.auth.dto.CustomOAuth2User;
import com.gruns.wrr.auth.entity.RefreshTokenEntity;
import com.gruns.wrr.auth.repository.RefreshTokenRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public CustomAuthenticationSuccessHandler(JwtUtil jwtUtil, RefreshTokenRepository refreshTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String username = customOAuth2User.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String accessToken = jwtUtil.createJwt("access", username, role, 60 * 10 * 1000L);
        String refreshToken = jwtUtil.createJwt("refresh", username, role, 24 * 60 * 60 * 1000L);

        Boolean isExisted = refreshTokenRepository.existsByUsername(username);
        if (isExisted) {
            refreshTokenRepository.deleteByUsername(username);
        }
        addRefreshTokenEntity(username, refreshToken, 24 * 60 * 60 * 1000L);

        response.setHeader("accessToken", accessToken);
        response.addCookie(createCookie("refreshToken", refreshToken));
        response.sendRedirect("http://localhost:5173/");
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true); https 에서만 허용
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshTokenEntity(String username, String token, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(username, token, date.toString());

        refreshTokenRepository.save(refreshTokenEntity);
    }
}
