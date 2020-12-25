package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
import com.orlovsky.mooc_platform.dto.TestStepProcessDTO;
import com.orlovsky.mooc_platform.mapper.TestStepOptionRequestMapper;
import com.orlovsky.mooc_platform.model.*;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import com.orlovsky.mooc_platform.service.EducationalMaterialService;
import com.orlovsky.mooc_platform.service.impl.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@Secured(Role.ROLE_PREFIX+"USER")
@RequestMapping("/progress")
public class CourseProgressController {
    @Autowired
    EducationalMaterialService educationalMaterialService;

    @Autowired
    CourseProgressService courseProgressService;

    @Autowired
    UserService userService;

    @GetMapping("/courses/register/{courseId}")
    public String showAll(Model model,
                          Principal principal,
                          @PathVariable("courseId") Long courseId){
        String username  = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            model.addAttribute("user",user);
            courseProgressService.signUpUser(courseId,user.getId());
            model.addAttribute("course",educationalMaterialService.getCourseById(courseId));
//            return "show_course";
            return "redirect:/progress/courses/"+courseId;
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }
    @GetMapping("/courses/{courseId}")
    public String showStepsPage(Model model,
                               @PathVariable("courseId") Long courseId){
        Course course = educationalMaterialService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "progress";
    }

    @GetMapping("/courses/{courseId}/drop")
    public String dropCourse(Principal principal,
                             @PathVariable("courseId") Long courseId){

        String username  = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            System.out.println(user);
            System.out.println(courseId);

            courseProgressService.deleteStudentFromCourse(courseId,user.getId());
            return "redirect:/profile";
        } catch (UsernameNotFoundException | NotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }


    @GetMapping("/courses/{courseId}/get-last-step")
    public String showProgressStepPage(Model model,
                          Principal principal,
                          @PathVariable("courseId") Long courseId){
        String username  = principal.getName();
        try{
            User user = userService.loadUserByUsername(username);
            model.addAttribute("user",user);
            model.addAttribute("course",educationalMaterialService.getCourseById(courseId));
            Step optionalStep = courseProgressService.getCurrentStep(courseId,user.getId());
            if(optionalStep==null){
                return "redirect:/progress/courses/"+courseId+"/congratulate";
            }
            if(optionalStep instanceof TestStep){
                TestStep testStep = (TestStep) optionalStep;
                List<TestStepOptionRequestDTO> answers = new ArrayList<>();

                List<Boolean> answersChecked = new ArrayList<>();
                for (TestStepOption testStepOption:testStep.getAnswers()) {
                    answers.add(TestStepOptionRequestMapper.INSTANCE.toDto(testStepOption));
                    answersChecked.add(false);
                }
                TestStepDTO testStepDTO = TestStepDTO.builder()
                        .description(testStep.getDescription())
                        .courseId(testStep.getCourse().getId())
                        .position(testStep.getPosition())
                        .score(testStep.getScore())
                        .id(testStep.getId())
                        .answers(answers).build();
                TestStepProcessDTO testStepProcessDTO = TestStepProcessDTO.builder()
                        .answersChecked(answersChecked)
                        .courseId(courseId)
                        .description(testStep.getDescription())
                        .id(testStep.getId())
                        .position(testStep.getPosition())
                        .score(testStep.getScore())
                        .build();
                model.addAttribute("testStepDTO",testStepDTO);
                model.addAttribute("testStepProcessDTO",testStepProcessDTO);
                return "show_test_step";
            }
            else if(optionalStep instanceof EducationalStep){
                log.info("educationalStep {}",optionalStep);
                model.addAttribute("educationalStep",optionalStep);
                return "show_educational_step";
            }
            else {
                return "errors/error-404";
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }


    @GetMapping("/courses/{courseId}/get-last-step/wrong")
    public String showTestStepPageWrong(Model model,
                                       Principal principal,
                                       @PathVariable("courseId") Long courseId){
        String username  = principal.getName();
        model.addAttribute("isCorrect",false);
        try{
            User user = userService.loadUserByUsername(username);
            model.addAttribute("user",user);
            model.addAttribute("course",educationalMaterialService.getCourseById(courseId));
            Step optionalStep = courseProgressService.getCurrentStep(courseId,user.getId());
            if(optionalStep==null){
                return "redirect:/progress/courses/"+courseId+"/congratulate";
            }
            if(optionalStep instanceof TestStep){
                TestStep testStep = (TestStep) optionalStep;
                List<TestStepOptionRequestDTO> answers = new ArrayList<>();

                List<Boolean> answersChecked = new ArrayList<>();
                for (TestStepOption testStepOption:testStep.getAnswers()) {
                    answers.add(TestStepOptionRequestMapper.INSTANCE.toDto(testStepOption));
                    answersChecked.add(false);
                }
                TestStepDTO testStepDTO = TestStepDTO.builder()
                        .description(testStep.getDescription())
                        .courseId(testStep.getCourse().getId())
                        .position(testStep.getPosition())
                        .score(testStep.getScore())
                        .id(testStep.getId())
                        .answers(answers).build();
                TestStepProcessDTO testStepProcessDTO = TestStepProcessDTO.builder()
                        .answersChecked(answersChecked)
                        .courseId(courseId)
                        .description(testStep.getDescription())
                        .id(testStep.getId())
                        .position(testStep.getPosition())
                        .score(testStep.getScore())
                        .build();
                model.addAttribute("testStepDTO",testStepDTO);
                model.addAttribute("testStepProcessDTO",testStepProcessDTO);
                return "show_test_step";
            }
            else if(optionalStep instanceof EducationalStep){
                log.info("educationalStep {}",optionalStep);
                model.addAttribute("educationalStep",optionalStep);
                return "show_educational_step";
            }
            else {
                return "errors/error-404";
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return "errors/error-404";
        }
    }



    @GetMapping("/courses/{courseId}/steps/educational-steps/{educationalStepId}/pass")
    public String passEducationalStep(Model model,
                                      Principal principal,
                                      @PathVariable("courseId") Long courseId,
                                      @PathVariable("educationalStepId") Long educationalStepId){
        String username  = principal.getName();
        User user = userService.loadUserByUsername(username);
        model.addAttribute("user",user);
        courseProgressService.makePassedEducationalStep(courseId,user.getId(),educationalStepId);
        return "redirect:/progress/courses/"+courseId+"/get-last-step";
    }

    @PostMapping("/courses/{courseId}/steps/test-steps/{testStepId}/pass")
    public String passTestStep(Model model,
                               Principal principal,
                               @PathVariable("courseId") Long courseId,
                               @PathVariable("testStepId") Long testStepId,
                               @ModelAttribute("testStepProcessDTO") TestStepProcessDTO stepProcessDTO){
        TestStep testStep = educationalMaterialService.getTestStepById(testStepId);
        List<TestStepOption> testStepOptions = testStep.getAnswers();
        List<TestStepOptionRequestDTO> answers = new ArrayList<>();
        for (int i = 0; i<testStepOptions.size();i++) {
            TestStepOptionRequestDTO requestDTO = TestStepOptionRequestDTO.builder()
                    .id(testStepOptions.get(i).getId())
                    .optionText(testStepOptions.get(i).getOptionText())
                    .isCorrect(stepProcessDTO.getAnswersChecked().get(i))
                    .build();
            answers.add(requestDTO);
        }
        TestStepDTO testStepDTO = TestStepDTO.builder()
                .id(testStepId)
                .score(testStep.getScore())
                .position(testStep.getPosition())
                .courseId(testStep.getCourse().getId())
                .description(testStep.getDescription())
                .answers(answers)
                .build();

        String username  = principal.getName();
        User user = userService.loadUserByUsername(username);
        model.addAttribute("user",user);
        ActionType actionType = courseProgressService.makeProcessedTestStep(courseId,user.getId(),testStepId,testStepDTO);
        if(actionType==ActionType.PASSED){
            return "redirect:/progress/courses/"+courseId+"/get-last-step";
        } else {
            return "redirect:/progress/courses/"+courseId+"/get-last-step/wrong";
        }

    }

    @GetMapping("/courses/{courseId}/congratulate")
    public String congratulateStudent(Model model,
                                      Principal principal,
                                      @PathVariable("courseId") Long courseId){
        String username  = principal.getName();
        User user = userService.loadUserByUsername(username);
        model.addAttribute("user",user);
        Course course = educationalMaterialService.getCourseById(courseId);
        courseProgressService.congratulateStudent(user,course);
        model.addAttribute("course",course);
        return "congratulation";
    }


}
