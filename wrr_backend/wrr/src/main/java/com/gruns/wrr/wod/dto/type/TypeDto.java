package com.gruns.wrr.wod.dto.type;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Getter
@Setter
@ToString
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "workoutType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TypeEMOMDto.class, name = "EMOM"),
        @JsonSubTypes.Type(value = TypeAMRAPDto.class, name = "AMRAP"),
        @JsonSubTypes.Type(value = TypeOnOffDto.class, name = "OnOff"),
        @JsonSubTypes.Type(value = TypeRFTDto.class, name = "RFT")
})
public abstract class TypeDto {
    private int typeId;
    private long workoutId;
    private String workoutType;
    private Time timeCap;
}