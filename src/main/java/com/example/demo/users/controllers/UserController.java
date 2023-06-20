package com.example.demo.users.controllers;

import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.tools.response.response;
import com.example.demo.users.models.User;
import com.example.demo.users.repositories.UserRepository;
import com.example.demo.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService; // Assign the userService parameter to the userService field
    }



@GetMapping("")
public ResponseEntity<response<User>> getUsers(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted ){

   // UserRepository userRepository = new UserRepository();
   Iterable<User> users= this.userRepository.findAll();

   // return response.createSuccessResponse("SI","1", List.of("1", "2","3"));
   // return response.createSuccessResponse("SI",user, new ArrayList<User>(){{ add(new User()); add(new User());   }}  );
return   response.successResponse(users);

}


@GetMapping("/{id}")
// public response<User> getUser(){
public ResponseEntity<response<User>> getUser(@PathVariable("id") Long Id){

    // User user= new User();
    User user= this.userRepository.findById(Id).orElse(null);
    return   response.successResponse(user);

}
 @PostMapping("")
public ResponseEntity<response<User>> saveUser(@RequestBody User user) {
     User userInserted =this.userRepository.save(user);
    return   response.successResponse(userInserted);
    }

@PutMapping("/{id}")
public ResponseEntity<response<User>> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
     User   userUpdated=null;   
    User userExist = userRepository.findById(id).orElse(null);
        if (userExist != null) {
            userExist.setUsername(user.getUsername());
      userUpdated  = userRepository.save(userExist);
        }
       
     return   response.successResponse(userUpdated);
    }

    @DeleteMapping("/{userId}")
    public  ResponseEntity<response<User>> deleteUser(@PathVariable("userId") Long userId) {
        userRepository.deleteById(userId);
        return   response.successResponse();
    }
    

    /*     @GetMapping("/rol/{userId}")
    public  ResponseEntity<response<Map<String, Object>>> userRol(@PathVariable("userId") Long userId) {
       Map<String, Object> userRol= userService.getUserWithRoles(userId);
        return   response.successResponse(userRol);
    }
    */

    
   @GetMapping("/rol")
    Iterable<User> get() {
        return userRepository.findAll();
    }

    @GetMapping("/rol/{userId}")
    public ResponseEntity<String> getUserWithUserRoles(@PathVariable Long userId) {

            return ResponseEntity.ok("Hello from secured endpoint");
       /*  User user = userService.getUserWithUserRoles(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    */
    }

}
