package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.dto.UserRegistrationDto;
import com.orlovsky.mooc_platform.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "id", target = "id")
    UserDTO toDto(User user);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "matchingPassword", target = "passwordConfirm")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "id", target = "id")
    User toEntity(UserDTO userDTO);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toEntity(UserRegistrationDto userRegistrationDto);


    List<UserDTO> toDtos(List<User> users);
    List<User> toEntities(List<UserDTO> userDTOs);
}
