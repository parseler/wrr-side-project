package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.dto.*;

import java.util.List;

public interface WodService {
    List<MovementDto> getMovementList();
    List<BoxDto> getBoxList();
    List<TypeDto> getTypeList();
    void saveWod(WodDto wodDto);

    WodDto getRandomWod();
}