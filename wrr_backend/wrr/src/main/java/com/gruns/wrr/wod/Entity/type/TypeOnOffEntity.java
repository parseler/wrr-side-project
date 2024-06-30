package com.gruns.wrr.wod.Entity.type;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "type_onoff")
@Builder
public class TypeOnOffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeOnoffId;

    private long workoutId;
    private int round;
    private Time timeCap;
    private Time onTime;
    private Time offTime;
}
