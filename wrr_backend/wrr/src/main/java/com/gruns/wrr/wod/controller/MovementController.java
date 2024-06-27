package com.gruns.wrr.wod.controller;

import com.gruns.wrr.wod.dto.MovementDto;
import com.gruns.wrr.wod.service.WodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movement")
public class MovementController {

    private final WodService wodService;

    public MovementController(WodService wodService) {
        this.wodService = wodService;
    }

    @PostMapping("/list")
    public ResponseEntity<?> getMovementList() {

        List<MovementDto> list = wodService.getMovementList();
        return ResponseEntity.ok(list);
    }
}
