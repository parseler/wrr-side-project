package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WorkoutDto {
    private long workoutId;
    private long wodId;
    private WorkoutTypeDto workoutType;
    private List<WorkoutMovementDto> workoutMovements;
    private int seq;
    private long parentWorkoutId;
}