package com.dh.PI.controllers;


import com.dh.PI.dto.CityDTO;
import com.dh.PI.model.City;
import com.dh.PI.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cities")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping
    public ResponseEntity<CityDTO> create(@RequestBody CityDTO cityDTO){
        return ResponseEntity.status(201).body(service.create(cityDTO));
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PutMapping
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO cityDTO){
        return ResponseEntity.status(201).body(service.update(cityDTO));
    }

}
