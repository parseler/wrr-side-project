package com.gruns.wrr.wod.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Entity
@Getter
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movementId;

    @Nullable
    private String movementName;
}
