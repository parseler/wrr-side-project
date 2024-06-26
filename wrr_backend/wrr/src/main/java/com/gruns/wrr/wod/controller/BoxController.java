package com.gruns.wrr.wod.controller;

import com.gruns.wrr.wod.dto.BoxDto;
import com.gruns.wrr.wod.service.WodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {

    private final WodService wodService;

    public BoxController(WodService wodService) {
        this.wodService = wodService;
    }

    @PostMapping("/list")
    public ResponseEntity<?> getBoxList() {
        List<BoxDto> list = wodService.getBoxList();

        return ResponseEntity.ok(list);
    }
}
