package com.stackroute.userauthenticationservice.controller;

import com.stackroute.userauthenticationservice.message.request.Login;
import com.stackroute.userauthenticationservice.message.response.JwtResponse;
import com.stackroute.userauthenticationservice.repository.UserRepository;
import com.stackroute.userauthenticationservice.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping(value = "/api/auth", method = {RequestMethod.GET, RequestMethod.POST})

    public class AuthAPIs {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;
        @Autowired
        PasswordEncoder encoder;


        @Autowired
        JwtProvider jwtProvider;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {
             //authentication object containing username and password is created if the same is present in our mysql database.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
             //token is generated
            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //jwtresponse with all the credentials is returned.
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        }



        }


