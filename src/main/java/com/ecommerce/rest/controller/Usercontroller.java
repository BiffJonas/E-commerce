package com.ecommerce.rest.controller;

import com.ecommerce.rest.repository.UserRepository;
import com.ecommerce.rest.model.User;
import com.ecommerce.rest.model.Ware;
import com.ecommerce.rest.exception.UserNotFoundException ;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class Usercontroller {
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
        User _user = userRepository.save(new User(user.getUsername(), user.getPassword()));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);

        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String name) {
        try {
            
            List<User> users = new ArrayList<>();
            if (name == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByNameContaining(name).forEach(users::add);

            if(users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable(value = "id") long userId)
    throws UserNotFoundException {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Could not find user with id of: " + userId));
    return ResponseEntity.ok(user);

}
}
