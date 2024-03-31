package org.example.springbootrest_2_2.controller;

import jakarta.validation.Valid;
import org.example.springbootrest_2_2.exeption.InvalidCredentials;
import org.example.springbootrest_2_2.exeption.UnauthorizedUser;
import org.example.springbootrest_2_2.model.Authorities;
import org.example.springbootrest_2_2.model.User;
import org.example.springbootrest_2_2.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User user) {
        return service.getAuthorities(user);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentials msg) {
        System.out.println("Exception: " + msg.getMessage());
        return new ResponseEntity<>("Exception: " + msg.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleUnauthorizedUser(UnauthorizedUser msg) {
        System.out.println("Exception: " + msg.getMessage());
        return new ResponseEntity<>("Exception: " + msg.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
