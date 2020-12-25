package com.orlovsky.mooc_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for users, who want to become authors.
 */
@Controller
public class TeacherBecomingController {

    @GetMapping("/teacherbecoming")
    public String showTeacherBecomingPage(Model model){
        return "teacherbecoming";
    }
}
