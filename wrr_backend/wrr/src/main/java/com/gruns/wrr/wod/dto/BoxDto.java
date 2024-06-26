package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxDto {
    private int boxId;
    private String boxName;

    public BoxDto() {
    }

    @Builder
    public BoxDto(int boxId, String boxName) {
        this.boxId = boxId;
        this.boxName = boxName;
    }
}
