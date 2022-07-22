package com.dh.PI.repositories;

import com.dh.PI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.email = (:email)")
    User findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

}
