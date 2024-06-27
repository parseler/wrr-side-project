package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WodDto {
    private long wodId;
    private int boxId;
    private String wodName;
    private int teamSize;
    private int likes;
    private String description;
}
