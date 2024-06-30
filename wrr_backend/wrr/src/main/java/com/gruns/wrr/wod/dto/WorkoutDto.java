package com.gruns.wrr.wod.dto;

import com.gruns.wrr.wod.dto.type.TypeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkoutDto {
    private long workoutId;
    private long wodId;
    private TypeDto type;
    private int seq;
    private long parentWorkoutId;
}