package com.denticheck.api.domain.user.service;

import com.denticheck.api.domain.user.dto.UserRequestDTO;
import com.denticheck.api.domain.user.dto.UserResponseDTO;
import com.denticheck.api.domain.user.entity.UserEntity;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

public interface UserService {
    Long updateUser(UserRequestDTO dto) throws AccessDeniedException;
    void deleteUser(UserRequestDTO dto) throws AccessDeniedException;
    UserResponseDTO readUser();
    Optional<UserEntity> findUser(String username);
    Boolean existsUser(String username);
    Long mobileCreateUser(UserEntity userEntity);
}
