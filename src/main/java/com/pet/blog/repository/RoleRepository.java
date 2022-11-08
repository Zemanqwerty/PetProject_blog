package com.pet.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.blog.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
