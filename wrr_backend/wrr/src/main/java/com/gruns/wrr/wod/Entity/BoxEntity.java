package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "box")
public class BoxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boxId;

    @Column(unique = true)
    private String boxName;

    public BoxEntity() {
    }

    @Builder
    public BoxEntity(Integer boxId, String boxName) {
        this.boxId = boxId;
        this.boxName = boxName;
    }
}
