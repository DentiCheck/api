package com.denticheck.api.domain.user.repository;

import com.denticheck.api.domain.user.entity.UserEntity;
import com.denticheck.api.domain.user.entity.UserStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    void deleteByUsername(String username);
}
