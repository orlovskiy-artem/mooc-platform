package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.dto.UserRegistrationDto;
import com.orlovsky.mooc_platform.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface AccountService extends UserDetailsService {
    boolean saveUser(UserRegistrationDto userRegistrationDto);

    User findUserById(UUID userId);

    List<User> getAllUsers();

    void updateUser(UUID UserId, UserDTO userDTO);

    boolean deleteUserById(UUID studentId);
}
