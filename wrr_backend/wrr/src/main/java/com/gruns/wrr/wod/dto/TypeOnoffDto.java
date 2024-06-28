package com.gruns.wrr.wod.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;


@Getter
@Setter
public class TypeOnoffDto extends TypeDto {
    private int round;
    private Time onTime;
    private Time offTime;
}
