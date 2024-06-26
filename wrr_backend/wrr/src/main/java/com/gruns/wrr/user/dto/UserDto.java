package com.gruns.wrr.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String email;
    private String profileImageUrl;
    private String name;
    private String role;
    private String username;

    public UserDto() {
    }

    @Builder
    public UserDto(String email, String profileImageUrl, String name, String role, String username) {
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.role = role;
        this.username = username;
    }
}