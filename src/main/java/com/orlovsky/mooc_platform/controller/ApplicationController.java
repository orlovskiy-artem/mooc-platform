package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.dto.UserRegistrationDto;
import com.orlovsky.mooc_platform.exceptions.UserAlreadyExistException;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ApplicationController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title",
                "SignIn");
        model.addAttribute("user", new User());
        return "login";
    }
    @GetMapping("/")
    public String home( Model model) {
        return "redirect:/profile";
    }


    @GetMapping("/logout")
    public String logout(){
        return "login";
    }


    //    Registration
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm( Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        try {
            userService.saveUser(userRegistrationDto);
            return "redirect:/registration?success";
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
            return "redirect:/registration?failure";
        }
    }
}
