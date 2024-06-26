package com.gruns.wrr.user.service;

import com.gruns.wrr.user.dto.UserDto;

public interface UserService {
    UserDto getUserInfo(String accessToken);
}