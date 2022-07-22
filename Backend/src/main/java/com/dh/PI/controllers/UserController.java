package com.dh.PI.controllers;

import com.dh.PI.dto.userDTO.UserRequestDTO;
import com.dh.PI.dto.userDTO.UserResponseDTO;
import com.dh.PI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.status(201).body(service.createUser(userRequestDTO));
    }
}
