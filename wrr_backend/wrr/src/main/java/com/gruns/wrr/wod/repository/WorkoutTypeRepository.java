package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.WorkoutTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutTypeRepository extends JpaRepository<WorkoutTypeEntity, Long> {

}
