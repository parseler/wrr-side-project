package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Getter
public class TypeDto {
    private int typeId;
    private long workoutId;
    private String name;
    private Time timeCap;

    public TypeDto() {
    }

    @Builder
    public TypeDto(int typeId, long workoutId, String name, Time timeCap) {
        this.typeId = typeId;
        this.workoutId = workoutId;
        this.name = name;
        this.timeCap = timeCap;
    }
}