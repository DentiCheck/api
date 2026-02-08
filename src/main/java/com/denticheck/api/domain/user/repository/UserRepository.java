package com.denticheck.api.domain.user.repository;

import com.denticheck.api.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    void deleteByUsername(String username);
}
