package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.*;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import com.pi.ati.ort.back.utils.BimClient;
import com.pi.ati.ort.back.utils.ServerUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.bimserver.interfaces.objects.SUser;
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

    // REGISTER A NEW USER -----------------------------------------------------
    @Operation(summary = "Register a new user in back and server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
    })
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) throws ServiceException {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());

        bimClient.registerUser(user.getUsername(), user.getPassword(), user.getName());
        User createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // LOGIN -----------------------------------------------------
    @Operation(summary = "Login a user in back and server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))}),
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws ServiceException {

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
        LoginResponse user = bimClient.login(searchedUser.getUsername(), searchedUser.getPassword());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // LOGOUT -----------------------------------------------------
    @Operation(summary = "Logout a user in back and server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged out Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))}),
    })
    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                             @RequestHeader(value = "token") String token) throws ServiceException {
        bimClient.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET LOGGED USER -----------------------------------------------------
    @Operation(summary = "Get logged user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get logged user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServerUser.class))}),
    })
    @GetMapping("/logged")
    public ResponseEntity<SUser> getLoggedUser(@Parameter(in = ParameterIn.HEADER, description = "Login token", required = true, schema = @Schema(type = "string"))
                                               @RequestHeader(value = "token") String token) throws ServiceException {
        SUser user = bimClient.getLoggedUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
