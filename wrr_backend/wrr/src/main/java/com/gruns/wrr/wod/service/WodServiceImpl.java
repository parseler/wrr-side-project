package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.Entity.BoxEntity;
import com.gruns.wrr.wod.Entity.MovementEntity;
import com.gruns.wrr.wod.dto.BoxDto;
import com.gruns.wrr.wod.dto.MovementDto;
import com.gruns.wrr.wod.repository.BoxRepository;
import com.gruns.wrr.wod.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WodServiceImpl implements WodService {

    private final MovementRepository movementRepository;
    private final BoxRepository boxRepository;

    public WodServiceImpl(MovementRepository movementRepository, BoxRepository boxRepository) {
        this.movementRepository = movementRepository;
        this.boxRepository = boxRepository;
    }

    @Override
    public List<MovementDto> getMovementList() {

        List<MovementEntity> movementEntityList = movementRepository.findAll();

        List<MovementDto> list = new ArrayList<>();

        for (MovementEntity movementEntity : movementEntityList) {
            MovementDto movementDto = MovementDto.builder()
                    .movementId(movementEntity.getMovementId())
                    .movementName(movementEntity.getMovementName()).build();
            list.add(movementDto);
        }

        return list;
    }

    @Override
    public List<BoxDto> getBoxList() {
        List<BoxEntity> boxEntityList = boxRepository.findAll();

        List<BoxDto> list = new ArrayList<>();

        for (BoxEntity boxEntity : boxEntityList) {
            BoxDto boxDto = BoxDto.builder()
                    .boxId(boxEntity.getBoxId())
                    .boxName(boxEntity.getBoxName())
                    .build();
            list.add(boxDto);
        }

        return list;
    }
}
