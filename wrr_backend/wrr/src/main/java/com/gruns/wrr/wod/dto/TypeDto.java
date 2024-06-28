package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class TypeDto {
    private int typeId;
    private long workoutId;
    private String name;
    private Time timeCap;
}