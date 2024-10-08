package com.gruns.wrr.user.service;

import com.gruns.wrr.auth.util.JwtUtil;
import com.gruns.wrr.user.dto.UserDto;
import com.gruns.wrr.user.entity.UserEntity;
import com.gruns.wrr.user.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDto getUserInfo(String accessToken) {

        String username = jwtUtil.getUsername(accessToken);
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            return null;
        }

        return UserDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .profileImageUrl(userEntity.getProfileImageUrl())
                .username(userEntity.getUsername()).build();
    }
}