package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.BimClient;
import com.pi.ati.ort.back.classes.LoginRequest;
import com.pi.ati.ort.back.classes.RegisterRequest;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import jakarta.validation.Valid;
import org.bimserver.shared.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final BimClient bimClient;

    @Autowired
    public AuthController(UserService userService, BimClient bimClient) {
        this.userService = userService;
        this.bimClient = bimClient;
    }

    @PostMapping("/register")
     public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) throws ServiceException {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setName(registerRequest.getName());

        bimClient.registerUser(user.getUsername(), user.getPassword(), user.getName());
        User createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
     }

        @PostMapping("/login")
        public ResponseEntity<User> login(@Valid @RequestBody LoginRequest loginRequest) {

        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            User searchedUser = userService.findByUsername(loginRequest.getUsername());
        if (searchedUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        if (!searchedUser.getPassword().equals(loginRequest.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(searchedUser, HttpStatus.OK);
        }
}
