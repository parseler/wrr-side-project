package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {

    @Override
    List<MovementEntity> findAll();
}
