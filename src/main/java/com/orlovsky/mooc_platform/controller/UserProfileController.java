package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.Role;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import com.orlovsky.mooc_platform.service.impl.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/profile")
@Secured(Role.ROLE_PREFIX+"USER")
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseProgressService courseProgressService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String profile(Model model, Principal principal){
        String username = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            model.addAttribute("user",user);
            Collection<Course> courses =
            courseProgressService.getStudentsCourses(Arrays.asList(user));
            model.addAttribute("courses",courses);
            return "profile";
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }
    @GetMapping("/edit")
    public String showEditProfilePage(Model model,Principal principal){
        UserDTO userDTO = new UserDTO();
        String username = principal.getName();
        User user = userService.loadUserByUsername(username);
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDescription(user.getDescription());
        model.addAttribute("userDTO",userDTO);
        return "edit_profile";
    }

    @PostMapping("/edit")
    public String editProfile(@ModelAttribute("userDTO") UserDTO userDTO,
                              Model model,
                              Principal principal){
        String username = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            if(bCryptPasswordEncoder.matches(userDTO.getMatchingPassword(),user.getPassword())){
                userService.updateUser(user.getId(),userDTO);
                model.addAttribute("user",user);
                if(userDTO.getEmail().equals(username)){
                    return "redirect:/profile";
                }
                return "redirect:/logout";
            } else {
                return "redirect:/logout";
            }

        } catch (UsernameNotFoundException | NotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }
    @GetMapping("/delete")
    public String deleteUser(Model model,
                             Principal principal){
        String username = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            userService.deleteUserById(user.getId());
            model.addAttribute("user",user);
            return "redirect:/login";
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }
}
