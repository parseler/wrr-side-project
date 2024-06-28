package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WodDto {
    private long wodId;
    private int boxId;
    private String wodName;
    private int teamSize;
    private int likes;
    private String description;
    private List<WorkoutDto> workouts;
}