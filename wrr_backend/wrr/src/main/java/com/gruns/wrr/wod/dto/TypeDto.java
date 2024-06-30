package com.gruns.wrr.wod.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TypeDto {
    private int typeId;
    private String typeName;
}
