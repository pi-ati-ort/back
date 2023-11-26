package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.UserRequest;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import com.pi.ati.ort.back.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    private JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Dos GET ALL USERS
    @Operation(summary = "Get all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
    })
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    //Docs GET USER BY ID
    @Operation(summary = "Get the user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))}),
    })
    @GetMapping("/users/id/{id}")
    public Optional<User> getUserbyId(@Parameter (description="The User's id") @PathVariable long id) {
        return userService.findById(id);
    }

    //Docs GET USER BY USERNAME
    @Operation(summary = "Get the user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))}),
    })
    @GetMapping("/users/{username}")
    public User getUserByUsername(@Parameter (description="The User's username") @PathVariable String username) {
        return userService.findByUsername(username);
    }

    // Docs CREATE USER
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))}),
    })
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Docs UPDATE USER BY ID
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))}),
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());

            User updatedUser = userService.createUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Docs DELETE USER BY ID
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))}),
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
