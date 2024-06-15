package com.gruns.wrr.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(nullable = false)
    private String name; // nickname

    private String role;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(unique = true)
    private String username; // providerName + providerId

    public UserEntity() {
    }

    @Builder
    public UserEntity(String email, String profileImageUrl, String name, String role, LocalDate createdDate, String username) {
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.role = role;
        this.createdDate = createdDate;
        this.username = username;
    }

    public void updateProfile(String profileImageUrl, String name) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
    }
}
