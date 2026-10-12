package com.roshan.monitoringplatform.controller;

import com.roshan.monitoringplatform.dto.UserRegistrationRequest;
import com.roshan.monitoringplatform.service.userRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userRegistrationController {

    private final userRegistrationService user;

    public userRegistrationController(userRegistrationService regService ){
        this.user= regService;
    }

    @PostMapping("/registration")
    public ResponseEntity registerUser(@RequestBody UserRegistrationRequest request){


        return user.register(request);

    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserRegistrationRequest request){
        return user.login(request);

    }
}
