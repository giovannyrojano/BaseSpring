package com.example.demo.users.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.users.models.User;
import com.example.demo.users.repositories.UserRepository;


@Service

public class UserService {
     private final UserRepository userRepository;
      //   private final TransactionTemplate transactionTemplate;
      
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


        public User getUserWithUserRoles(Long userId) {
         Optional<User> optionalUser = userRepository.findByIdWithUserRoles(userId);
        return optionalUser.orElse(null);
    }

}
