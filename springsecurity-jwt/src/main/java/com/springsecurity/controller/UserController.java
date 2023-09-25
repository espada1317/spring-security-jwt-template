package com.springsecurity.controller;

import com.springsecurity.dto.AuthRequest;
import com.springsecurity.entity.UserInfo;
import com.springsecurity.service.JwtService;
import com.springsecurity.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserInfoService userInfoService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getUsername(),
                                authRequest.getPassword()
                        )
                );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    @PostMapping("/new")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
        userInfoService.addUser(userInfo);
        return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
    }

}
