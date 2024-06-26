package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "movement")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movementId;

    @NotNull
    @Column(unique = true)
    private String movementName;

    public MovementEntity() {
    }

    @Builder
    public MovementEntity(String movementName) {
        this.movementName = movementName;
    }
}