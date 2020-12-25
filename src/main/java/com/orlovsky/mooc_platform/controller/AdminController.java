package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.dto.UserEditRoleDTO;
import com.orlovsky.mooc_platform.model.Role;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.service.impl.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/admin")
@Secured(Role.ROLE_PREFIX+"ADMIN")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String showAll(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          HttpServletRequest request,
                          Principal principal){
        if(!checkIfAdmin(principal)){
            return "errors/error-403";
        }
//          model.addAttribute("users",userRepository.findAll(new PageRequest(page,4)));
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/edit/{userId}")
    public String showEditUserPage(Model model,
                           @PathVariable("userId") Long userId){
        User user = null;
        try {
            user = userService.findUserById(userId);
            Boolean isUser =userService.checkRole(user.getId(),"USER");
            Boolean isAdmin =userService.checkRole(user.getId(),"ADMIN");
            Boolean isAuthor = userService.checkRole(user.getId(),"AUTHOR");
            UserEditRoleDTO userEditRoleDTO =
                    UserEditRoleDTO.builder()
                            .email(user.getEmail())
                            .id(user.getId())
                            .isUser(isUser)
                            .isAdmin(isAdmin)
                            .isAuthor(isAuthor)
                            .build();
            model.addAttribute("userEditRoleDTO",userEditRoleDTO);
            return "edit_user";
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }

    @PostMapping("/users/edit/{userId}")
    public String editUser(Model model,
                           @PathVariable("userId") Long userId,
                           @ModelAttribute("userEditRoleDTO") UserEditRoleDTO userEditRoleDTO){
        try {
            userService.updateRoles(userId,userEditRoleDTO);
            return "redirect:/admin/users";
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }

    @GetMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long id ){
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }


    private Boolean checkIfAdmin(Principal principal){
        User user = userService.loadUserByUsername(principal.getName());
        Boolean isAdmin = false;
        for (Role role: user.getRoles()) {
            if(role.getName().equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        return isAdmin;
    }



}
