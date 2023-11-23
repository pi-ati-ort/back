package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

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
}
