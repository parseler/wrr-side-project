package com.gruns.wrr.wod.controller;

import com.gruns.wrr.wod.dto.WodDto;
import com.gruns.wrr.wod.service.WodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wod")
public class WodController {

    private final WodService wodService;

    public WodController(WodService wodService) {
        this.wodService = wodService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveWod(@RequestBody WodDto wodDto) {
        System.out.println("wodDto = " + wodDto);

        return ResponseEntity.ok().build();
    }
}
