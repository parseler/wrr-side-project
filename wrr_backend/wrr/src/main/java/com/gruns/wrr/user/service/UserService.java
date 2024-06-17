package com.gruns.wrr.user.service;

import com.gruns.wrr.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserInfo(String accessToken);
}
