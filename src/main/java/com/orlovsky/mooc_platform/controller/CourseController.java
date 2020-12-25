package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.service.EducationalMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
public class CourseController {
    @Autowired
    EducationalMaterialService educationalMaterialService;

    @GetMapping("/courses")
    public String showAll(Model model){
        model.addAttribute("courses",educationalMaterialService.getAllCourses());
        return "courses";
    }
    @GetMapping("/courses/search")
    public String showAll(Model model, @Param("keyword") String keyword){
        List<Course> courseList = educationalMaterialService.searchCoursesByKeyword(keyword);
        log.info("Keyword: {}",keyword);
        log.info("courses: {}",courseList.size());
        model.addAttribute("courses", courseList);
        model.addAttribute("keyword", keyword);
        return "courses_search";
    }
    @GetMapping("/courses/{courseId}")
    public String showCoursePage(@PathVariable(name = "courseId") Long courseId,Model model){
        Course course = educationalMaterialService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "show_course";
    }

    @GetMapping("/courses/{courseId}/steps")
    public String showStepsPage(@PathVariable(name = "courseId") Long courseId,Model model){
        Course course = educationalMaterialService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "manage_steps";
    }
    @GetMapping("/courses/delete/{courseId}")
    public String deleteCourse(@PathVariable(name = "courseId") Long courseId,Model model){
        educationalMaterialService.deleteCourse(courseId);
        return "redirect:/courses";
    }

}
