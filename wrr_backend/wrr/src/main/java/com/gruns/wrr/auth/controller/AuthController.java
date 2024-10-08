package com.gruns.wrr.auth.controller;

import com.gruns.wrr.auth.service.AuthService;
import com.gruns.wrr.auth.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
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

        Boolean isExisted = authService.existsByToken(refreshToken);
        if (!isExisted) {
            return ResponseEntity.badRequest().body("invalid refresh token");
        }

        String username = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        String reissuedAccessToken = jwtUtil.createJwt("access", username, role, 60 * 60 * 1000L);
        String reissuedRefreshToken = jwtUtil.createJwt("refresh", username, role, 60 * 60 * 12 * 1000L);

        authService.deleteByToken(refreshToken);
        authService.addRefreshTokenEntity(username, reissuedRefreshToken, 60 * 60 * 12 * 1000L);

        response.setHeader("accessToken", reissuedAccessToken);
        response.addCookie(authService.createCookie("refreshToken", reissuedRefreshToken));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/valid")
    public ResponseEntity<?> isValidToken() {
        return ResponseEntity.ok().build();
    }
}
