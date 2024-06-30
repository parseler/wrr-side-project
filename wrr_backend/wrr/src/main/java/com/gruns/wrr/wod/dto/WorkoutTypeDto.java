package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Getter
@Setter
@ToString
public class WorkoutTypeDto {
    private long workoutTypeId;
    private long workoutId;
    private int typeId;
    private int round;
    private Time timeCap;
    private Time onTIme;
    private Time offTIme;
}
