package com.gruns.wrr.wod.Entity.type;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "type_emom")
@Builder
public class TypeEMOMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TypeEmomId;

    private long workoutId;
    private int round;
    private Time timeCap;
}
