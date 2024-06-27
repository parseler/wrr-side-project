package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MovementDto {
    private int movementId;
    private String movementName;

    public MovementDto() {
    }

    @Builder
    public MovementDto(int movementId, String movementName) {
        this.movementId = movementId;
        this.movementName = movementName;
    }
}
