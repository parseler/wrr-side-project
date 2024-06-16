package com.gruns.wrr.auth.util;

import com.gruns.wrr.auth.dto.CustomOAuth2User;
import com.gruns.wrr.auth.service.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final TokenService tokenService;

    public CustomAuthenticationSuccessHandler(JwtUtil jwtUtil, TokenService tokenService) {
        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String username = customOAuth2User.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String accessToken = jwtUtil.createJwt("access", username, role, 60 * 1000L);
        String refreshToken = jwtUtil.createJwt("refresh", username, role, 60 * 5 * 1000L);

        Boolean isExisted = tokenService.existsByUsername(username);
        if (isExisted) {
            tokenService.deleteByUsername(username);
        }
        tokenService.addRefreshTokenEntity(username, refreshToken, 60 * 60 * 1000L);

        response.setHeader("accessToken", accessToken);
        response.addCookie(tokenService.createCookie("refreshToken", refreshToken));
        response.sendRedirect("http://localhost:5173/");

        log.debug("onAuthenticationSuccess accessToken : {}", accessToken);
        log.debug("onAuthenticationSuccess refreshToken : {}", refreshToken);
    }
}
