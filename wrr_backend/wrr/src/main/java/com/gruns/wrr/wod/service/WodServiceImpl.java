package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.Entity.*;
import com.gruns.wrr.wod.dto.*;
import com.gruns.wrr.wod.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WodServiceImpl implements WodService {

    private final WodRepository wodRepository;

    @Override
    public WodDto getRandomWod() {

    }

    @Override
    public void saveWod(WodDto wodDto) {

    }
}