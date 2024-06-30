package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.dto.BoxDto;
import com.gruns.wrr.wod.dto.MovementDto;
import com.gruns.wrr.wod.dto.WodDto;
import com.gruns.wrr.wod.dto.WorkoutDto;

import java.util.List;

public interface WodService {
    List<MovementDto> getMovementList();
    List<BoxDto> getBoxList();
    void saveWod(WodDto wodDto);
    void saveWorkOut(WorkoutDto workoutDto);
}