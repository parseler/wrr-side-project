package com.gruns.wrr.wod.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class WorkoutMovementDto {
    private long workoutMovementId;
    private long workoutId;
    private int movementId;
    private String repsUnit;
    private int repsMale;
    private int repsFemale;
    private String weightUnit;
    private float weightMale;
    private float weightFemale;
    private int seq;
    private int synchro;
}
