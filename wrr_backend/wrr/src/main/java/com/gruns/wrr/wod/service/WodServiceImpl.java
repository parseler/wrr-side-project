package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.Entity.BoxEntity;
import com.gruns.wrr.wod.Entity.MovementEntity;
import com.gruns.wrr.wod.Entity.WodEntity;
import com.gruns.wrr.wod.Entity.WorkoutEntity;
import com.gruns.wrr.wod.dto.*;
import com.gruns.wrr.wod.dto.type.TypeDto;
import com.gruns.wrr.wod.repository.BoxRepository;
import com.gruns.wrr.wod.repository.MovementRepository;
import com.gruns.wrr.wod.repository.WodRepository;
import com.gruns.wrr.wod.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WodServiceImpl implements WodService {

    private final MovementRepository movementRepository;
    private final BoxRepository boxRepository;
    private final WodRepository wodRepository;
    private final WorkoutRepository workoutRepository;

    public WodServiceImpl(MovementRepository movementRepository, BoxRepository boxRepository, WodRepository wodRepository, WorkoutRepository workoutRepository) {
        this.movementRepository = movementRepository;
        this.boxRepository = boxRepository;
        this.wodRepository = wodRepository;
        this.workoutRepository = workoutRepository;
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

    @Override
    public void saveWod(WodDto wodDto) {
        WodEntity wodEntity = WodEntity
                .builder()
                .wodId(wodDto.getWodId())
                .wodName(wodDto.getWodName())
                .boxId(wodDto.getBoxId())
                .teamSize(wodDto.getTeamSize())
                .likes(wodDto.getLikes())
                .description(wodDto.getDescription())
                .build();

        wodRepository.save(wodEntity);

        Long wodId = wodEntity.getWodId();

        for (WorkoutDto workoutDto : wodDto.getWorkouts()) {
            workoutDto.setWodId(wodId);
            saveWorkOut(workoutDto);
        }
    }

    @Override
    public void saveWorkOut(WorkoutDto workoutDto) {
        WorkoutEntity workoutEntity = WorkoutEntity
                .builder()
                .workoutId(workoutDto.getWorkoutId())
                .wodId(workoutDto.getWodId())
                .seq(workoutDto.getSeq())
                .typeName(workoutDto.getType().getWorkoutType())
                .parentWorkoutId(workoutDto.getParentWorkoutId())
                .build();

        workoutRepository.save(workoutEntity);

        long workoutId = workoutEntity.getWorkoutId();
        TypeDto typeDto = workoutDto.getType();


    }
}
