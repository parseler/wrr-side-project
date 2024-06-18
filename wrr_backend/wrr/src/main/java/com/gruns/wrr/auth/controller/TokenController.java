package com.gruns.wrr.auth.controller;

import com.gruns.wrr.auth.service.TokenService;
import com.gruns.wrr.auth.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TokenController {

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;

    public TokenController(JwtUtil jwtUtil, TokenService tokenService) {
        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.badRequest().body("refresh token null");
        }

        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.badRequest().body("expired refresh token");
        }

        String category = jwtUtil.getCategory(refreshToken);

        if (!category.equals("refresh")) {
            return ResponseEntity.badRequest().body("invalid refresh token");
        }

        Boolean isExisted = tokenService.existsByToken(refreshToken);
        if (!isExisted) {
            return ResponseEntity.badRequest().body("invalid refresh token");
        }

        String username = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        String reissuedAccessToken = jwtUtil.createJwt("access", username, role, 60 * 10 * 1000L);
        String reissuedRefreshToken = jwtUtil.createJwt("refresh", username, role, 60 * 60 * 1000L);

        tokenService.deleteByToken(refreshToken);
        tokenService.addRefreshTokenEntity(username, reissuedRefreshToken, 60 * 60 * 1000L);

        response.setHeader("accessToken", reissuedAccessToken);
        response.addCookie(tokenService.createCookie("refreshToken", reissuedRefreshToken));

        return ResponseEntity.ok().build();
    }
}
