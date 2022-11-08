package com.pet.blog.security.jwt;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pet.blog.entity.Status;
import com.pet.blog.entity.UserEntity;

public final class JwtUserFactory {
    public JwtUserFactory() {}

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getStatus().equals(Status.ACTIVE),
            user.getUpdatedDate(), 
            null);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRole) {
        return userRole.stream()
            .map(role ->
                    new SimpleGrantedAuthority(role.getRoleName())
                    ).collect(Collectors.toList());
    }
}
