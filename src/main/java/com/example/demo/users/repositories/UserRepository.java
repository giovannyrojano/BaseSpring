package com.example.demo.users.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.example.demo.users.models.User;

public interface UserRepository  extends JpaRepository<User, Long> {

     Optional<User> findByUsername(String username);


    @Query("SELECT u FROM User u WHERE u.deleted = :isDeleted")
    List<User> findAllFilter(boolean isDeleted);

// el QUERY utiliza un SQL especial donde no esta utilizando las tablas SQL sino que se refiere a las entidades del proyecto.
   @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userRol WHERE u.userId = :userId")
    Optional<User> findByIdWithUserRoles(@Param("userId") Long userId);
}
    
