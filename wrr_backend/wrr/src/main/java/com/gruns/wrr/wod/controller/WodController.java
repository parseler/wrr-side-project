package com.gruns.wrr.wod.controller;

import com.gruns.wrr.wod.dto.WodDto;
import com.gruns.wrr.wod.service.WodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wod")
@RequiredArgsConstructor
public class WodController {

    private final WodService wodService;

    @PostMapping()
    public ResponseEntity<?> saveWod(@RequestBody WodDto wodDto) {

        wodService.saveWod(wodDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/random")
    public ResponseEntity<?> getRandomWod() {

        WodDto wodDto = wodService.getRandomWod();

        return ResponseEntity.ok(wodDto);
    }
}