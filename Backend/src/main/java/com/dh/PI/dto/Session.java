package com.dh.PI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Session {

    private Long id;
    private List<String> role;
    private String login;
    private String token;
    private String email;
}