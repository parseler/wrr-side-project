package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.dto.WodDto;

public interface WodService {
    void saveWod(WodDto wodDto);
    WodDto getRandomWod();
}