package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workout_movement")
@Builder
public class WorkoutMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutMovementId;

    private long workoutId;
    private int movementId;
    private String repsUnit;
    private int repsMale;
    private int repsFemale;
    private String weightUnit;
    private float weightMale;
    private float weightFemale;
    private int seq;
    private int synchro;
}
