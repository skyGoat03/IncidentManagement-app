package com.roshan.monitoringplatform.service;

import com.roshan.monitoringplatform.Entity.User;
import com.roshan.monitoringplatform.Exception.serivceAlreadyExistsException;
import com.roshan.monitoringplatform.Repository.userRepository;
import com.roshan.monitoringplatform.dto.UserRegistrationRequest;
import org.apache.coyote.Response;
import org.hibernate.dialect.function.json.JsonPathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class userRegistrationService {

    private final userRepository UserRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public userRegistrationService(userRepository userRepo, PasswordEncoder passwordEncoder , JWTService jwtService) {
        this.UserRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity register(UserRegistrationRequest request) {


        User user = new User();

        Boolean emailExists = UserRepo.existsByEmail(request.getEmail());
        System.out.println("Reached here " + emailExists + request.getEmail());
        if (emailExists) {
            System.out.println("Reached here 1");
            throw new serivceAlreadyExistsException("Email Already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println("Reached here insteaed  ");
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashedPassword);
        user.setRole(User.Roles.USER);
        user.setCreatedAt(Instant.now());


        try {
            User saved = UserRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("User registered Successfully");

    }


    public ResponseEntity<?> login(UserRegistrationRequest request) {


        User user = UserRepo.findByEmail(request.getEmail());
        System.out.println("USER object" + user);
        if (user != null) {
            Boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPasswordHash());

            if (isPasswordMatch) {
                String token = jwtService.generateToken(user.getEmail());
                return  ResponseEntity.ok(token);

            }

        }


        return ResponseEntity.status(401).body("Invalid email or password");
    }

}
