package com.pet.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pet.blog.entity.RoleEntity;
import com.pet.blog.entity.Status;
import com.pet.blog.entity.UserEntity;
import com.pet.blog.model.ResponseModel;
import com.pet.blog.repository.RoleRepository;
import com.pet.blog.repository.UserRepository;
import com.pet.blog.security.jwt.JwtTokenProvider;



@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Async
    public CompletableFuture<ResponseModel> createUser(UserEntity user) {
        RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        
        userRepository.save(user);

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
    public CompletableFuture<UserEntity> getOne(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return CompletableFuture.completedFuture(
            user
        );
    }

    @Async
    public UserEntity getUser(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return (
            user
        );
    }
}
