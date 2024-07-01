package com.gruns.wrr.wod.dto;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class WorkoutTypeDto {
    private long workoutTypeId;
    private long workoutId;
    private int typeId;
    private int round;
    private Time timeCap;
    private Time onTime;
    private Time offTime;
}
