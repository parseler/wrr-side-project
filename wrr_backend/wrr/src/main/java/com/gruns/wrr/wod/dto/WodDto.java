package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class WodDto {
    private long wodId;
    private int boxId;
    private String wodName;
    private int teamSize;
    private int likes;
    private String description;
    private List<WorkoutDto> workouts;

    public WodDto() {
    }

    @Builder
    public WodDto(long wodId, int boxId, String wodName, int teamSize, int likes, String description, List<WorkoutDto> workouts) {
        this.wodId = wodId;
        this.boxId = boxId;
        this.wodName = wodName;
        this.teamSize = teamSize;
        this.likes = likes;
        this.description = description;
        this.workouts = workouts;
    }
}
