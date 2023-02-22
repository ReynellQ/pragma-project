package com.pragma.userservice.infrastructure.output.jpa.repository;

import com.pragma.userservice.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository interface with methods to query database
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPersonalId(Long personalId);

}
