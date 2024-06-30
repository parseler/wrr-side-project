package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "wod")
@AllArgsConstructor
@Builder
public class WodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wodId;

    @NotNull
    private int boxId;

    private String wodName;
    private int teamSize;
    private int likes;
    private String description;
}
