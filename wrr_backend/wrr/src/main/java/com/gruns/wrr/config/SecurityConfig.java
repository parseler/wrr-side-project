package com.gruns.wrr.config;

import com.gruns.wrr.auth.service.CustomOAuth2UserService;
import com.gruns.wrr.auth.util.CustomAuthenticationSuccessHandler;
import com.gruns.wrr.auth.util.JwtFilter;
import com.gruns.wrr.auth.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final JwtUtil jwtUtil;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                          JwtUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                        configuration.setExposedHeaders(Collections.singletonList("accessToken"));

                        return configuration;
                    }
                }));

        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**", "/user/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/oauth2/authorization"))
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .successHandler(customAuthenticationSuccessHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(new JwtFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
