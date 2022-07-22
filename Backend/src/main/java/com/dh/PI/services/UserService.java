package com.dh.PI.services;

import com.dh.PI.dto.userDTO.UserRequestDTO;
import com.dh.PI.dto.userDTO.UserResponseDTO;
import com.dh.PI.exceptions.CreateNewAccountException;
import com.dh.PI.model.User;
import com.dh.PI.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){

        if (repository.existsByEmail(userRequestDTO.getEmail())){
            throw new CreateNewAccountException("Já existe uma conta com esse email");
        }

        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);
        user.getRoles().add("USERS");

        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(encoder.encode(pass));
        return new UserResponseDTO(repository.save(user));
    }
}