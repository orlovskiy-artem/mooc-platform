package com.orlovsky.mooc_platform.service.impl;


import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.dto.UserRegistrationDto;
import com.orlovsky.mooc_platform.mapper.UserMapper;
import com.orlovsky.mooc_platform.model.Role;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.repository.RoleRepository;
import com.orlovsky.mooc_platform.repository.UserRepository;
import com.orlovsky.mooc_platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements AccountService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(UUID userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(UserRegistrationDto userRegistrationDto) {
        User userFromDB = userRepository.findByUsername(userRegistrationDto.getUsername());
        if (userFromDB != null) {
            return false;
        }
        System.out.println(userRegistrationDto.toString());
        User user = UserMapper.INSTANCE.toEntity(userRegistrationDto);

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public void updateUser(UUID UserId, UserDTO userDTO) {}

    @Override
    public boolean deleteUserById(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}

