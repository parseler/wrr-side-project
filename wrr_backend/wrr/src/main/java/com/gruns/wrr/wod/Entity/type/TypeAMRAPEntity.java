package com.gruns.wrr.wod.Entity.type;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "type_amrap")
@Builder
public class TypeAMRAPEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeAmrapId;

    private long workoutId;
    private Time timeCap;
}
