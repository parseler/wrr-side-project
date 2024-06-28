package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkoutDto {
    private long workoutId;
    private int wodId;
    private TypeDto type;
    private int seq;
    private long parentWorkoutId;
}