package com.dh.PI.controllers;

import com.dh.PI.dto.Login;
import com.dh.PI.dto.Session;
import com.dh.PI.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Session> login(@RequestBody Login login){
        return ResponseEntity.ok().body(loginService.login(login));
    }
}