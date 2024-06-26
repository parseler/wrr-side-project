package com.gruns.wrr.user.controller;

import com.gruns.wrr.user.dto.UserDto;
import com.gruns.wrr.user.entity.UserEntity;
import com.gruns.wrr.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestBody String accessToken) {
        UserDto userDto = userService.getUserInfo(accessToken);

        if (userDto == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(userDto);
        }
    }
}
