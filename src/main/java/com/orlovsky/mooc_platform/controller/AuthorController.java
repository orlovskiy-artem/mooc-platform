package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.dto.CourseDTO;
import com.orlovsky.mooc_platform.dto.EducationalStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.Role;
import com.orlovsky.mooc_platform.model.TestStep;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import com.orlovsky.mooc_platform.service.EducationalMaterialService;
import com.orlovsky.mooc_platform.service.impl.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/teach")
@Secured(Role.ROLE_PREFIX+"AUTHOR")
public class AuthorController {
    @Autowired
    EducationalMaterialService educationalMaterialService;

    @Autowired
    CourseProgressService courseProgressService;


    @Autowired
    UserService userService;

    @GetMapping("/courses/showNewCourseForm")
    public String showNewCourseForm(Model model){
        CourseDTO courseDTO = new CourseDTO();
        model.addAttribute("course",courseDTO);
        return "create_course";
    }

    @PostMapping("/courses")
    public String createCourse(@NonNull @ModelAttribute("course") CourseDTO courseDTO,
                               Principal principal){
        Course course = educationalMaterialService.createEmptyCourse(courseDTO);
        User author = userService.loadUserByUsername(principal.getName());
        educationalMaterialService.addAuthor(course.getId(),author.getId());
        return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/add_test_step")
    public String showAddTestStepPage(@PathVariable(name = "courseId") Long courseId, Model model){
        TestStepDTO testStepDTO = new TestStepDTO();
        Course course = educationalMaterialService.getCourseById(courseId);
        model.addAttribute("course",course);
        model.addAttribute("testStep",testStepDTO);
        return "add_test_step";
    }
    @GetMapping("/courses/{courseId}/test_step/{testStepId}/add_test_step_option")
    public String showAddTestStepOptionPage(@PathVariable(name = "courseId") Long courseId,
                                            @PathVariable(name = "testStepId") Long testStepId,
                                            Model model){
        TestStepOptionRequestDTO optionDTO = new TestStepOptionRequestDTO();
        Course course = educationalMaterialService.getCourseById(courseId);
        TestStep testStep = educationalMaterialService.getTestStepById(testStepId);
        model.addAttribute("testStepOptionDTO",optionDTO);
        model.addAttribute("testStep",testStep);
        model.addAttribute("course",course);
        return "add_test_step_option";
    }
    @PostMapping("/courses/{courseId}/test_step/{testStepId}/add_test_step_option")
    public String addTestStepOption(@PathVariable(name = "courseId") Long courseId,
                                            @PathVariable(name = "testStepId") Long testStepId,
                                            @NonNull @ModelAttribute("testStepOptionDto")
                                                TestStepOptionRequestDTO optionDTO,
                                            Model model){
        educationalMaterialService.addTestStepOption(courseId,testStepId,optionDTO);
        return "redirect:/teach/courses/" + courseId + "/steps";
    }


    @GetMapping("/courses")
    public String showAuthorsCoursesPage(Model model,Principal principal){
        User author = userService.loadUserByUsername(principal.getName());
        Collection<Course> courses = educationalMaterialService.getAuthorCourses(Arrays.asList(author));
        model.addAttribute("author",author);
        model.addAttribute("courses",courses);
        return "teacher_courses";
    }
    @GetMapping("/courses/{courseId}")
    public String showCoursePage(Principal principal,@PathVariable(name = "courseId") Long courseId,Model model){
        String username = principal.getName();
        try{
            Course course = educationalMaterialService.getCourseById(courseId);
            model.addAttribute("course",course);
            User user = userService.loadUserByUsername(username);
            if(!course.getAuthors().contains(user)){
                return "errors/error-403";
            }
            model.addAttribute("course",course);

            return "show_course";
        } catch (Exception e){
            return "errors/error-404";
        }

    }

    @GetMapping("/courses/{courseId}/add_educational_step")
    public String showAddEducationalStepPage(@PathVariable(name = "courseId") Long courseId, Model model){
        EducationalStepDTO educationalStepDTO = new EducationalStepDTO();
        Course course = educationalMaterialService.getCourseById(courseId);
        model.addAttribute("course",course);
        model.addAttribute("educationalStep",educationalStepDTO);
        return "add_educational_step";
    }
    @PostMapping("/courses/{courseId}/add_test_step")
    public String addTestStep(@PathVariable(name = "courseId") Long courseId,
                              @ModelAttribute("testStep") TestStepDTO testStepDTO){
        educationalMaterialService.addTestStep(courseId,testStepDTO);
        return "redirect:/teach/courses/" + courseId + "/steps";
    }

    @PostMapping("/courses/{courseId}/add_educational_step")
    public String addEducationalStep(@PathVariable(name = "courseId") Long courseId,
                                       @ModelAttribute("educationalStep") EducationalStepDTO educationalStepDTO){
        educationalMaterialService.addEducationalStep(courseId,educationalStepDTO);
        return "redirect:/teach/courses/" + courseId + "/steps";
    }


    @GetMapping("/courses/{courseId}/steps")
    public String showStepsPage(Principal principal,
                                @PathVariable(name = "courseId") Long courseId,
                                Model model){
        String username  = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            Course course = educationalMaterialService.getCourseById(courseId);
            if(!course.getAuthors().contains(user)){
                return "errors/error-403";
            }
            model.addAttribute("course",course);
            return "manage_steps";

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }



    }



//    @GetMapping("/courses/{courseId}/steps")
//    public String showStepsPage(Principal principal,
//                                @PathVariable(name = "courseId") Long courseId,
//                                Model model){
//        String username = principal.getName();
//        Course course = educationalMaterialService.getCourseById(courseId);
//        model.addAttribute("course",course);
//        return "manage_steps";
//    }

    @GetMapping("/courses/{courseId}/educational_step/{educationalStepId}/delete")
    public String deleteEducationalStep(@PathVariable(name = "courseId") Long courseId,
                                        @PathVariable(name = "educationalStepId") Long educationalStepId
                                        ){
        educationalMaterialService.deleteEducationalStep(courseId,educationalStepId);
        return "redirect:/teach/courses/" + courseId + "/steps";
    }

    @GetMapping("/courses/{courseId}/test_step/{testStepId}/delete")
    public String deleteTestStep(@PathVariable(name = "courseId") Long courseId,
                                 @PathVariable(name = "testStepId") Long testStepId
    ){
        educationalMaterialService.deleteTestStep(courseId,testStepId);
        return "redirect:/teach/courses/" + courseId + "/steps";
    }

}
