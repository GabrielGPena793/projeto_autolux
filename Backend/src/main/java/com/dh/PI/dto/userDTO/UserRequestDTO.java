package com.dh.PI.dto.userDTO;

import com.dh.PI.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {

    private String name;
    private String lastname;
    private String email;
    private String password;

}
