package com.gruns.wrr.wod.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class WorkoutDto {
    private long workoutId;
    private long wodId;
    private WorkoutTypeDto workoutType;
    private List<WorkoutMovementDto> workoutMovements;
    private int seq;
    private long parentWorkoutId;
}