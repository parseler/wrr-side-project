package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutDto {
    private long workoutId;
    private int wodId;
    private String typeName;
    private int seq;
    private long parentWorkoutId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WorkoutDto{");
        sb.append("workoutId=").append(workoutId);
        sb.append(", wodId=").append(wodId);
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", seq=").append(seq);
        sb.append(", parentWorkoutId=").append(parentWorkoutId);
        sb.append('}');
        return sb.toString();
    }
}