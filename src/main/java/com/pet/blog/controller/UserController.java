package com.pet.blog.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.blog.entity.UserEntity;
import com.pet.blog.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/create")
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
