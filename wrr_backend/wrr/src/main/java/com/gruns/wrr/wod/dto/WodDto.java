package com.gruns.wrr.wod.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class WodDto {
    private String id;
    private String name;
    private String box;
    private String description;
    private int likes;
}