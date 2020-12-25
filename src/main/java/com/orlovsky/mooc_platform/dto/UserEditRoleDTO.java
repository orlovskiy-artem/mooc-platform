package com.orlovsky.mooc_platform.dto;

import com.orlovsky.mooc_platform.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditRoleDTO {
    @NotNull
    @NotEmpty
    private Long id;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    private Boolean isAuthor;
    private Boolean isAdmin;
    private Boolean isUser;
}
