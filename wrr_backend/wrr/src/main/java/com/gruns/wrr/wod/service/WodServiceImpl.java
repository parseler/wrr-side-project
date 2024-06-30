package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.Entity.*;
import com.gruns.wrr.wod.dto.*;
import com.gruns.wrr.wod.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WodServiceImpl implements WodService {

    private final MovementRepository movementRepository;
    private final BoxRepository boxRepository;
    private final TypeRepository typeRepository;
    private final WodRepository wodRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutTypeRepository workoutTypeRepository;
    private final WorkoutMovementRepository workoutMovementRepository;

    public WodServiceImpl(MovementRepository movementRepository, BoxRepository boxRepository, TypeRepository typeRepository, WodRepository wodRepository, WorkoutRepository workoutRepository, WorkoutTypeRepository workoutTypeRepository, WorkoutMovementRepository workoutMovementRepository) {
        this.movementRepository = movementRepository;
        this.boxRepository = boxRepository;
        this.typeRepository = typeRepository;
        this.wodRepository = wodRepository;
        this.workoutRepository = workoutRepository;
        this.workoutTypeRepository = workoutTypeRepository;
        this.workoutMovementRepository = workoutMovementRepository;
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
    public List<TypeDto> getTypeList() {
        List<TypeEntity> typeEntityList = typeRepository.findAll();

        List<TypeDto> list = new ArrayList<>();
        for (TypeEntity typeEntity : typeEntityList) {
            TypeDto typeDto = TypeDto.builder()
                    .typeId(typeEntity.getTypeId())
                    .typeName(typeEntity.getTypeName())
                    .build();
            list.add(typeDto);
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

        WodEntity savedWodEntity = wodRepository.save(wodEntity);
        Long wodId = savedWodEntity.getWodId();

        for (WorkoutDto workoutDto : wodDto.getWorkouts()) {
            workoutDto.setWodId(wodId);
            saveWorkout(workoutDto);
        }
    }

    @Override
    public WodDto getRandomWod() {

        WodEntity wodEntity = wodRepository.findRandomWod();
        System.out.println("wodEntity = " + wodEntity);
        return null;
    }

    private void saveWorkout(WorkoutDto workoutDto) {
        WorkoutEntity workoutEntity = WorkoutEntity
                .builder()
                .workoutId(workoutDto.getWorkoutId())
                .wodId(workoutDto.getWodId())
                .seq(workoutDto.getSeq())
                .parentWorkoutId(workoutDto.getParentWorkoutId())
                .build();

        WorkoutEntity savedWorkoutEntity = workoutRepository.save(workoutEntity);

        long workoutId = savedWorkoutEntity.getWorkoutId();
        WorkoutTypeDto workoutTypeDto = workoutDto.getWorkoutType();
        workoutTypeDto.setWorkoutId(workoutId);
        saveWorkoutType(workoutTypeDto);

        for (WorkoutMovementDto workoutMovementDto : workoutDto.getWorkoutMovements()) {
            workoutMovementDto.setWorkoutId(workoutId);
            saveWorkoutMovement(workoutMovementDto);
        }
    }

    private void saveWorkoutMovement(WorkoutMovementDto workoutMovementDto) {
        WorkoutMovementEntity workoutMovementEntity = WorkoutMovementEntity
                .builder()
                .workoutMovementId(workoutMovementDto.getWorkoutMovementId())
                .movementId(workoutMovementDto.getMovementId())
                .workoutId(workoutMovementDto.getWorkoutId())
                .repsUnit(workoutMovementDto.getRepsUnit())
                .repsMale(workoutMovementDto.getRepsMale())
                .repsFemale(workoutMovementDto.getRepsFemale())
                .weightUnit(workoutMovementDto.getWeightUnit())
                .weightMale(workoutMovementDto.getWeightMale())
                .weightFemale(workoutMovementDto.getWeightFemale())
                .seq(workoutMovementDto.getSeq())
                .synchro(workoutMovementDto.getSynchro())
                .build();

        workoutMovementRepository.save(workoutMovementEntity);
    }

    private void saveWorkoutType(WorkoutTypeDto workoutTypeDto) {
        WorkoutTypeEntity workoutTypeEntity = WorkoutTypeEntity
                .builder()
                .workoutTypeId(workoutTypeDto.getWorkoutTypeId())
                .workoutId(workoutTypeDto.getWorkoutId())
                .typeId(workoutTypeDto.getTypeId())
                .round(workoutTypeDto.getRound())
                .timeCap(workoutTypeDto.getTimeCap())
                .onTime(workoutTypeDto.getOnTIme())
                .offTime(workoutTypeDto.getOffTIme())
                .build();

        workoutTypeRepository.save(workoutTypeEntity);
    }
}