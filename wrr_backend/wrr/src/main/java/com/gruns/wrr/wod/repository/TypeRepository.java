package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {

    @Override
    List<TypeEntity> findAll();
}
