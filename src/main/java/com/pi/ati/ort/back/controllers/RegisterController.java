package com.pi.ati.ort.back.controllers;

import com.pi.ati.ort.back.classes.UserRequest;
import com.pi.ati.ort.back.entities.User;
import com.pi.ati.ort.back.services.UserService;
import jakarta.validation.Valid;
import org.bimserver.client.BimServerClient;
import org.bimserver.client.json.JsonBimServerClientFactory;
import org.bimserver.interfaces.objects.SUserType;
import org.bimserver.shared.AuthenticationInfo;
import org.bimserver.shared.ChannelConnectionException;
import org.bimserver.shared.UsernamePasswordAuthenticationInfo;
import org.bimserver.shared.exceptions.BimServerClientException;
import org.bimserver.shared.exceptions.ServiceException;
import org.bimserver.shared.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class RegisterController {

 private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
         this.userService = userService;
     }
     @PostMapping("/register")
     public ResponseEntity<User> register(@Valid @RequestBody UserRequest userRequest) throws BimServerClientException, ServiceException, ChannelConnectionException {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

         JsonBimServerClientFactory factory = new JsonBimServerClientFactory("http://localhost:8082");

         BimServerClient client = factory.create(new UsernamePasswordAuthenticationInfo("admin@admin.com", "1234"));

         ServiceInterface serviceInterface = client.getServiceInterface();

         serviceInterface.addUserWithPassword(user.getUsername(), user.getPassword(), "Nicolas", SUserType.USER, true, "");

         System.out.println("User created: " + user.getUsername() + " " + user.getPassword() + " " + "Nicolas" + " " + SUserType.USER + " " + "true");

         User createdUser = userService.createUser(user);
         return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
     }

        @PostMapping("/login")
        public ResponseEntity<User> login(@Valid @RequestBody UserRequest userRequest) {

        if (userRequest.getUsername() == null || userRequest.getPassword() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            User searchedUser = userService.findByUsername(userRequest.getUsername());
        if (searchedUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        if (!searchedUser.getPassword().equals(userRequest.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(searchedUser, HttpStatus.OK);
        }
}
