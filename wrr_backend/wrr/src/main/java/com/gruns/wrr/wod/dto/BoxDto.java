package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoxDto {
    private int boxId;
    private String boxName;
}
