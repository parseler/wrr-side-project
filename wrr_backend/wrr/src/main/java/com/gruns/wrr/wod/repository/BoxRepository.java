package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Integer> {

    @Override
    List<BoxEntity> findAll();
}
