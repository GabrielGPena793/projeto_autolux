package com.dh.PI.services;

import com.dh.PI.config.SecurityConfig;
import com.dh.PI.dto.Login;
import com.dh.PI.dto.Session;
import com.dh.PI.exceptions.LoginException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.User;
import com.dh.PI.repositories.UserRepository;
import com.dh.PI.security.JWTCreator;
import com.dh.PI.security.JWTObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    public Session login(Login login) {
        User user = repository.findByEmail(login.getEmail());
        if(user!=null) {
            boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new LoginException("Senha inválida, tente novamente!");
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Session session = new Session();
            session.setLogin(user.getName() + " " + user.getLastname());
            session.setRole(user.getRoles());
            session.setId(user.getId());
            session.setEmail(user.getEmail());
            //gerando token jwt e colocando na sessão para retornar ao cliente
            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
            jwtObject.setRoles(user.getRoles());
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        }else {
            throw new ResourceNotFoundException("Não existe uma conta cadastrada com esse email");
        }
    }
}