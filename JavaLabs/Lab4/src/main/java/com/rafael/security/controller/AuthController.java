package com.rafael.security.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.rafael.security.model.AddUserModel;
import com.rafael.security.model.AuthorizationData;
import com.rafael.security.model.Owner;
import com.rafael.security.model.User;
import com.rafael.security.repository.UserInfoRepository;
import com.rafael.security.service.JwtService;
import com.rafael.security.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;

    @Autowired
    public AuthController(final JwtService jwtService, final AuthenticationManager authenticationManager, final UserInfoService userInfoService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthorizationData node) throws Exception {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(node.getUsername(), node.getPassword()));
        if (auth.isAuthenticated()) {
            return jwtService.generateToken((node.getUsername()), "authService");
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody AddUserModel node) {
        Owner owner = new Owner();
        owner.setName(node.getName());
        owner.setBirthday(node.getBirthday());
        User user = new User();
        user.setUsername(node.getUsername());
        user.setPassword(node.getPassword());
        user.setRole("ROLE_" + node.getRole());

        return userInfoService.addUser(user, owner);
    }
}
