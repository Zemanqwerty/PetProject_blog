package com.pet.blog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.blog.entity.UserEntity;
import com.pet.blog.security.jwt.JwtTokenProvider;
import com.pet.blog.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping
    public String logout() {
        return "User logout";
    }

    @PostMapping("/signin")
    public CompletableFuture signin(@RequestBody UserEntity user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserEntity userObj = userService.getUser(username);

            if (userObj == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, userObj.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return CompletableFuture.completedFuture(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public CompletableFuture createUser(@RequestBody UserEntity user) {
        try {
            return CompletableFuture.completedFuture(userService.createUser(user));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @GetMapping("/get_all")
    public CompletableFuture getAll() {
        try {
            return CompletableFuture.completedFuture(userService.getAll());
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @GetMapping("/get_one")
    public CompletableFuture getOne(@RequestParam Long id) {
        try {
            return CompletableFuture.completedFuture(userService.getOne(id));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @GetMapping("/get_one_by_username")
    public CompletableFuture getOneByUsername(@RequestParam String username) {
        try {
            return CompletableFuture.completedFuture(userService.getUser(username));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }
}
