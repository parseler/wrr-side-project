package com.gruns.wrr.wod.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
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