package com.gruns.wrr.auth.util;

import com.gruns.wrr.auth.dto.CustomOAuth2User;
import com.gruns.wrr.user.dto.UserDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 재발급일 경우 Access Token 유효 검증 생략
        String requestURI = request.getRequestURI();
        if ("/auth/reissue".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Access Token 유효 검증
        String accessToken = request.getHeader("accessToken");

        if (accessToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            jwtUtil.isExpired(accessToken);
        } catch(ExpiredJwtException e) {
            PrintWriter writer = response.getWriter();
            writer.println("expired access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
        if (!"access".equals(category)) {
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // SecurityContextHolder 저장
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        UserDto userDto = UserDto.builder()
                .username(username)
                .role(role)
                .build();

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
