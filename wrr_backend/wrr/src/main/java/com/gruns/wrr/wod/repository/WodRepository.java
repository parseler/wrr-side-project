package com.gruns.wrr.wod.repository;

import com.gruns.wrr.wod.Entity.Wod;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WodRepository extends MongoRepository<Wod, String> {
}
