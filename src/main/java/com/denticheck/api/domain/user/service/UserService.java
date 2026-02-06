package com.denticheck.api.domain.user.service;

import com.denticheck.api.domain.user.dto.UserRequestDTO;
import com.denticheck.api.domain.user.dto.UserResponseDTO;

import java.nio.file.AccessDeniedException;

public interface UserService {
    Long updateUser(UserRequestDTO dto) throws AccessDeniedException;
    void deleteUser(UserRequestDTO dto) throws AccessDeniedException;
    UserResponseDTO readUser();
}
