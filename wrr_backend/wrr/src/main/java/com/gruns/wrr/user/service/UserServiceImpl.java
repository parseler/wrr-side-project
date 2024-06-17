package com.gruns.wrr.user.service;

import com.gruns.wrr.auth.util.JwtUtil;
import com.gruns.wrr.user.entity.UserEntity;
import com.gruns.wrr.user.repository.UserRepository;
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
    public UserEntity getUserInfo(String accessToken) {

        String username = jwtUtil.getUsername(accessToken);
        return userRepository.findByUsername(username);
    }
}
