package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.LoginRequest;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Docs GET ALL USERS
    @Operation(summary = "Get all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
    })
    @GetMapping("/users")
    public List<User> getAllUsers(@Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                  @RequestHeader(value = "token") String token) {
        return userService.findAll();
    }

    //Docs GET USER BY ID
    @Operation(summary = "Get the user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
    })
    @GetMapping("/users/id/{id}")
    public Optional<User> getUserbyId(@Parameter(description = "The User's id") @PathVariable long id,
                                      @Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                      @RequestHeader(value = "token") String token) {
        return userService.findById(id);
    }

    //Docs GET USER BY USERNAME
    @Operation(summary = "Get the user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
    })
    @GetMapping("/users/{username}")
    public User getUserByUsername(@Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                  @RequestHeader(value = "token") String token, @Parameter(description = "The User's username") @PathVariable String username) {
        return userService.findByUsername(username);
    }

    // Docs UPDATE USER BY ID
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class))}),
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                               @RequestHeader(value = "token") String token, @PathVariable Long id, @RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(loginRequest.getUsername());
            user.setPassword(loginRequest.getPassword());

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
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class))}),
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                               @RequestHeader(value = "token") String token, @PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
