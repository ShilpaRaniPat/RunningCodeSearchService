package com.stackroute.userauthenticationservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//To test the authentication
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestAPIs {
    @GetMapping("/userDTO") //rest end point can be accessed only with jwt token
//    @PreAuthorize("authenticated")
    public String userAccess() {
        return "Authenticated Contents!";
    }//just a message
}