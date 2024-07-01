package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Long> {
    List<WorkoutEntity> findByWodIdOrderBySeqAsc(long wodId);
}
