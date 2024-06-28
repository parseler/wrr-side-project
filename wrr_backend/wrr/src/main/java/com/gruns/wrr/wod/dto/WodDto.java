package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WodDto {
    private long wodId;
    private int boxId;
    private String wodName;
    private int teamSize;
    private int likes;
    private String description;
    private List<WorkoutDto> workouts;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WodDto{");
        sb.append("wodId=").append(wodId);
        sb.append(", boxId=").append(boxId);
        sb.append(", wodName='").append(wodName).append('\'');
        sb.append(", teamSize=").append(teamSize);
        sb.append(", likes=").append(likes);
        sb.append(", description='").append(description).append('\'');
        sb.append(", workouts=").append(workouts);
        sb.append('}');
        return sb.toString();
    }
}
