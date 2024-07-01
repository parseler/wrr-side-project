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
    @Transactional(readOnly = true)
    public WodDto getRandomWod() {

        WodEntity wodEntity = wodRepository.findRandomWod();

        long wodId = wodEntity.getWodId();
        List<WorkoutEntity> workoutEntityList = workoutRepository.findByWodIdOrderBySeqAsc(wodId);

        List<WorkoutDto> workoutDtoList = new ArrayList<>();

        for (WorkoutEntity workoutEntity : workoutEntityList) {
            long workoutId = workoutEntity.getWorkoutId();
            WorkoutTypeDto workoutTypeDto = makeWorkoutType(workoutId);
            List<WorkoutMovementDto> workoutMovementDtoList = makeWorkoutMovements(workoutId);

            WorkoutDto workoutDto = WorkoutDto
                    .builder()
                    .workoutId(workoutId)
                    .parentWorkoutId(workoutEntity.getParentWorkoutId())
                    .workoutType(workoutTypeDto)
                    .workoutMovements(workoutMovementDtoList)
                    .seq(workoutEntity.getSeq())
                    .wodId(wodId)
                    .build();
            workoutDtoList.add(workoutDto);
        }

        return WodDto
                .builder()
                .wodId(wodId)
                .wodName(wodEntity.getWodName())
                .boxId(wodEntity.getBoxId())
                .description(wodEntity.getDescription())
                .likes(wodEntity.getLikes())
                .teamSize(wodEntity.getTeamSize())
                .workouts(workoutDtoList)
                .build();
    }

    private List<WorkoutMovementDto> makeWorkoutMovements(long workoutId) {
        List<WorkoutMovementEntity> workoutMovementEntities = workoutMovementRepository.findByWorkoutIdOrderBySeqAsc(workoutId);

        List<WorkoutMovementDto> workoutMovementDtoList = new ArrayList<>();

        for (WorkoutMovementEntity workoutMovementEntity : workoutMovementEntities) {
            WorkoutMovementDto workoutMovementDto = WorkoutMovementDto
                    .builder()
                    .workoutMovementId(workoutMovementEntity.getWorkoutMovementId())
                    .workoutId(workoutId)
                    .movementId(workoutMovementEntity.getMovementId())
                    .repsUnit(workoutMovementEntity.getRepsUnit())
                    .repsMale(workoutMovementEntity.getRepsMale())
                    .repsFemale(workoutMovementEntity.getRepsFemale())
                    .weightUnit(workoutMovementEntity.getWeightUnit())
                    .weightMale(workoutMovementEntity.getWeightMale())
                    .weightFemale(workoutMovementEntity.getWeightFemale())
                    .seq(workoutMovementEntity.getSeq())
                    .synchro(workoutMovementEntity.getSynchro())
                    .build();
            workoutMovementDtoList.add(workoutMovementDto);
        }

        return workoutMovementDtoList;
    }

    private WorkoutTypeDto makeWorkoutType(long workoutId) {

        WorkoutTypeEntity workoutTypeEntity = workoutTypeRepository.findByWorkoutId(workoutId);
        return WorkoutTypeDto
                .builder()
                .workoutTypeId(workoutTypeEntity.getWorkoutTypeId())
                .workoutId(workoutId)
                .typeId(workoutTypeEntity.getTypeId())
                .round(workoutTypeEntity.getRound())
                .timeCap(workoutTypeEntity.getTimeCap())
                .onTime(workoutTypeEntity.getOnTime())
                .offTime(workoutTypeEntity.getOffTime())
                .build();
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
                .onTime(workoutTypeDto.getOnTime())
                .offTime(workoutTypeDto.getOffTime())
                .build();

        workoutTypeRepository.save(workoutTypeEntity);
    }
}