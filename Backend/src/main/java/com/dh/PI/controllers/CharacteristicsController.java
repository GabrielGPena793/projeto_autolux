package com.dh.PI.controllers;

import com.dh.PI.dto.characteristics.CharacteristicsDTO;
import com.dh.PI.services.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/characteristics")
public class CharacteristicsController {

    @Autowired
    private CharacteristicService service;

    @PostMapping
    public ResponseEntity<CharacteristicsDTO> create(@RequestBody CharacteristicsDTO characteristicsDTO){
        return ResponseEntity.status(201).body(service.create(characteristicsDTO));
    }

    @GetMapping
    public ResponseEntity<List<CharacteristicsDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
