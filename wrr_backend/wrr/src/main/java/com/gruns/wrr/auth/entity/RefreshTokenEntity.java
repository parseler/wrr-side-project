package com.gruns.wrr.auth.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    private String username;
    private String token;
    private String expiration;

    public RefreshTokenEntity() {
    }

    @Builder
    public RefreshTokenEntity(String username, String token, String expiration) {
        this.username = username;
        this.token = token;
        this.expiration = expiration;
    }
}
