package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "workout")
@Builder
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutId;

    private long wodId;
    private String typeName;
    private int seq;
    private long parentWorkoutId;
}