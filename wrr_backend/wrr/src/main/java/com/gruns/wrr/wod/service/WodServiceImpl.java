package com.gruns.wrr.wod.service;

import com.gruns.wrr.wod.Entity.*;
import com.gruns.wrr.wod.dto.*;
import com.gruns.wrr.wod.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WodServiceImpl implements WodService {

    private final WodRepository wodRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public WodDto getRandomWod() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(1));
        AggregationResults<Wod> result = mongoTemplate.aggregate(aggregation, "wod", Wod.class);

        Wod wod = result.getMappedResults().get(0);

        if (wod != null) {
            throw new NoSuchElementException();
        }

        return WodDto.builder()
                .id(wod.getId())
                .name(wod.getName())
                .description(wod.getDescription())
                .box(wod.getBox())
                .likes(wod.getLikes())
                .build();
    }

    @Override
    public void saveWod(WodDto wodDto) {
        Wod wod = Wod.builder()
                .name(wodDto.getName())
                .description(wodDto.getDescription())
                .box(wodDto.getBox())
                .likes(0)
                .build();

        wodRepository.save(wod);
    }
}