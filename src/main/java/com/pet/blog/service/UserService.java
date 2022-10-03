package com.pet.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pet.blog.entity.UserEntity;
import com.pet.blog.model.ResponseModel;
import com.pet.blog.repository.UserRepository;


@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Async
    public CompletableFuture<ResponseModel> createUser(UserEntity user) {
        userRepository.save(user);
        return CompletableFuture.completedFuture(
            ResponseModel.toModel(200, "success", "user was created")
        );
    }

    @Async
    public CompletableFuture<List<UserEntity>> getAll() {
        return CompletableFuture.completedFuture(
            (List<UserEntity>) userRepository.findAll()
        );
    }

    @Async
    public CompletableFuture<Optional<UserEntity>> getOne(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return CompletableFuture.completedFuture(
            user
        );
    }

    @Async
    public CompletableFuture<Optional<UserEntity>> getUser(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return CompletableFuture.completedFuture(
            user
        );
    }
}
