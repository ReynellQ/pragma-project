package com.pragma.userservice.infrastructure.output.jpa.repository;

import com.pragma.userservice.infrastructure.output.jpa.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {
}
