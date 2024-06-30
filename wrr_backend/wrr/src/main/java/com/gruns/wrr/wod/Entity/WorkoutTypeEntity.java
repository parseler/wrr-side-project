package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "workout_type")
public class WorkoutTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutTypeId;

    private long workoutId;
    private int typeId;
    private int round;
    private Time timeCap;
    private Time onTime;
    private Time offTime;
}
