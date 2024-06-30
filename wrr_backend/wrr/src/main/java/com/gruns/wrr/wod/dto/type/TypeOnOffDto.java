package com.gruns.wrr.wod.dto.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;


@Getter
@Setter
@ToString
public class TypeOnOffDto extends TypeDto {
    private int round;
    private Time onTime;
    private Time offTime;
}
