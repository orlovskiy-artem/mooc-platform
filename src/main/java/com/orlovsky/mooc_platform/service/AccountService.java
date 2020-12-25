package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.dto.UserEditRoleDTO;
import com.orlovsky.mooc_platform.dto.UserRegistrationDto;
import com.orlovsky.mooc_platform.exceptions.UserAlreadyExistException;
import com.orlovsky.mooc_platform.model.User;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    User saveUser(UserRegistrationDto userRegistrationDto) throws UserAlreadyExistException;

    User findUserById(Long userId) throws NotFoundException;

    List<User> getAllUsers();

    void updateUser(Long UserId, UserDTO userDTO) throws NotFoundException;

    void updateRoles(Long userId, UserEditRoleDTO editRoleDTO) throws NotFoundException;

    Boolean checkRole(Long userId, String role) throws NotFoundException;

    void deleteUserById(Long studentId);
}
