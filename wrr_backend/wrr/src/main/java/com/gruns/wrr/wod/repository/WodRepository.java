package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.WodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WodRepository extends JpaRepository<WodEntity, Long> {

}
