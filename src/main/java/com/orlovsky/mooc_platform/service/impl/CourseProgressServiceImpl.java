package com.orlovsky.mooc_platform.service.impl;


import com.orlovsky.mooc_platform.dto.TestStepOptionDTO;
import com.orlovsky.mooc_platform.model.*;
import com.orlovsky.mooc_platform.repository.*;
import com.orlovsky.mooc_platform.service.AutoCheckService;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import com.orlovsky.mooc_platform.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.MissingResourceException;
import java.util.UUID;

@Service
public class CourseProgressServiceImpl implements CourseProgressService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EducationalStepRepository educationalStepRepository;

    @Autowired
    private TestStepRepository testStepRepository;

    @Autowired
    private  TestStepOptionRepository testStepOptionRepository;

    @Autowired
    private StudentProgressItemRepository studentProgressItemRepository;

    @Autowired
    private AutoCheckService autoCheckService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public void signUpUser(UUID courseId,
                           UUID studentId) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!studentRepository.existsById(studentId)){
            throw new MissingResourceException("Student not found",
                    "Student",
                    studentId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        Student student = studentRepository.getOne(studentId);
        course.getStudents().add(student);
        paymentService.payForCourse(student,course); //simplification
        courseRepository.save(course);
        studentRepository.save(student);
    }

    @Override
    public void makePassedEducationalStep(UUID courseId,
                                          UUID studentId,
                                          UUID educationalStepId) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!studentRepository.existsById(studentId)){
            throw new MissingResourceException("Student not found",
                    "Student",
                    studentId.toString());
        }
        if(!educationalStepRepository.existsById(educationalStepId)){
            throw new MissingResourceException("Educational step not found",
                    "EducationalStep",
                    educationalStepId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        Student student = studentRepository.getOne(studentId);
        EducationalStep educationalStep = educationalStepRepository.getOne(educationalStepId);

        StudentProgressItem studentProgressItem = StudentProgressItem.builder()
                .student(student)
                .course(course)
                .passedEducationalStep(educationalStep)
                .passedTestStep(null)
                .chosenOption(null).build();
        studentProgressItemRepository.save(studentProgressItem);
    }

    @Override
    public void makeProcessedTestStep(UUID courseId,
                                      UUID studentId,
                                      UUID testStepId,
                                      TestStepOptionDTO chosenAnswer) {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!studentRepository.existsById(studentId)){
            throw new MissingResourceException("Student not found",
                    "Student",
                    studentId.toString());
        }
        if(!testStepRepository.existsById(testStepId)){
            throw new MissingResourceException("Test step not found",
                    "TestStep",
                    testStepId.toString());
        }
        if(!testStepOptionRepository.existsById(chosenAnswer.getId())){
            throw new MissingResourceException("Test step option not found",
                    "TestStepOption",
                    chosenAnswer.getId().toString());
        }
        Course course = courseRepository.getOne(courseId);
        Student student = studentRepository.getOne(studentId);
        TestStep testStep = testStepRepository.getOne(testStepId);
        TestStepOption chosenTestOption = testStepOptionRepository.getOne(chosenAnswer.getId());
        ActionType actionType = autoCheckService.checkTestTask(testStep,chosenTestOption);
        if(actionType == ActionType.PASSED){
            StudentProgressItem studentProgressItem = StudentProgressItem.builder()
                    .student(student)
                    .course(course)
                    .passedTestStep(testStep)
                    .chosenOption(chosenTestOption)
                    .passedEducationalStep(null)
                    .build();
            studentProgressItemRepository.save(studentProgressItem);
        }
    }

    @Override
    public void congratulateStudent(Student student,
                                    Course course) {
        String congratulation = "Congratulations," + student.getFirstName() + " " + student.getLastName() +
                ",! You've done well. You've already finished course " + course.getTitle();
        System.out.println(congratulation);
    }
}
