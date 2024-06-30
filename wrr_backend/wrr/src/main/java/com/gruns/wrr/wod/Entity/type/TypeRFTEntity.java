package com.gruns.wrr.wod.Entity.type;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "type_rft")
@Builder
public class TypeRFTEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeRftId;

    private long workoutId;
    private int round;
    private Time timeCap;
}
