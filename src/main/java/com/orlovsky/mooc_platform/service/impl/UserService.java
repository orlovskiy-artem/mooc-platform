package com.orlovsky.mooc_platform.service.impl;


import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.dto.UserEditRoleDTO;
import com.orlovsky.mooc_platform.dto.UserRegistrationDto;
import com.orlovsky.mooc_platform.exceptions.UserAlreadyExistException;
import com.orlovsky.mooc_platform.mapper.UserMapper;
import com.orlovsky.mooc_platform.model.Role;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.repository.RoleRepository;
import com.orlovsky.mooc_platform.repository.UserRepository;
import com.orlovsky.mooc_platform.service.AccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.MessageFormat;
import java.util.*;

@Service
@Transactional
public class UserService implements AccountService {
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        // Username is an email.
        final Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            System.out.println(optionalUser.get());
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

    @Override
    public User findUserById(Long userId) throws NotFoundException {
        Optional<User> userFromDb = userRepository.findById(userId);
        if( ! userFromDb.isPresent()){
            throw new NotFoundException("User not found");
        }
        return userFromDb.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(UserRegistrationDto userRegistrationDto) throws UserAlreadyExistException {
        Optional<User> userFromDB = userRepository.findByEmail(userRegistrationDto.getEmail());
        if (userFromDB.isPresent()) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User user = UserMapper.INSTANCE.toEntity(userRegistrationDto);

        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName("USER"))));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(Long userId, UserDTO userDTO) throws NotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " +
                                    userId.toString()+" not found"));
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDescription(userDTO.getDescription());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateRoles(Long userId, UserEditRoleDTO editRoleDTO) throws NotFoundException {
        List<Role> roles = new ArrayList<>();
        if(!(editRoleDTO.getIsUser()==null) && editRoleDTO.getIsUser()){
            roles.add(roleRepository.findByName("USER"));
        }
        if(!(editRoleDTO.getIsAuthor()==null) && editRoleDTO.getIsAuthor()){
            roles.add(roleRepository.findByName("AUTHOR"));
        }
        if(!(editRoleDTO.getIsAdmin()==null) && editRoleDTO.getIsAdmin()){
            roles.add(roleRepository.findByName("ADMIN"));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " +
                userId.toString()+" not found"));
        for (Role role:user.getRoles()) {
            if(!roles.contains(role)){
                role.getUsers().remove(user);
            }
        }
        user.setRoles(new HashSet<>(roles));

        userRepository.saveAndFlush(user);
    }


    @Override
    public Boolean checkRole(Long userId, String role) throws NotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " +
                        userId.toString()+" not found"));
        return user.getRoles().contains(roleRepository.findByName(role));
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

}

