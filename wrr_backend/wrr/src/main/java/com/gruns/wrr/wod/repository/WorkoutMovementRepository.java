package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.WorkoutMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutMovementRepository extends JpaRepository<WorkoutMovementEntity, Long> {
}
