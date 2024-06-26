package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.dto.BoxDto;
import com.gruns.wrr.wod.dto.MovementDto;

import java.util.List;

public interface WodService {
    List<MovementDto> getMovementList();
    List<BoxDto> getBoxList();
}