package com.orlovsky.mooc_platform.dto;

import com.orlovsky.mooc_platform.model.Role;
import com.orlovsky.mooc_platform.validation.PasswordMatches;
import com.orlovsky.mooc_platform.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty
    private Long id;


    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    private String description;

    private Collection<Role> roles;
}
