package com.gruns.wrr.wod.controller;

import com.gruns.wrr.wod.dto.BoxDto;
import com.gruns.wrr.wod.dto.MovementDto;
import com.gruns.wrr.wod.dto.TypeDto;
import com.gruns.wrr.wod.dto.WodDto;
import com.gruns.wrr.wod.service.WodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        wodService.saveWod(wodDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/random")
    public ResponseEntity<?> getRandomWod() {

        WodDto wodDto = wodService.getRandomWod();

        if (wodDto == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(wodDto);
        }
    }

    @PostMapping("/movement/list")
    public ResponseEntity<?> getMovementList() {

        List<MovementDto> list = wodService.getMovementList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/box/list")
    public ResponseEntity<?> getBoxList() {
        List<BoxDto> list = wodService.getBoxList();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/type/list")
    public ResponseEntity<?> getTypeList() {
        List<TypeDto> list = wodService.getTypeList();

        return ResponseEntity.ok(list);
    }
}