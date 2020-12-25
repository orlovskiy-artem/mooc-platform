package com.orlovsky.mooc_platform.service.impl;


import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionDTO;
import com.orlovsky.mooc_platform.model.*;
import com.orlovsky.mooc_platform.repository.*;
import com.orlovsky.mooc_platform.service.AutoCheckService;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import com.orlovsky.mooc_platform.service.PaymentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseProgressServiceImpl implements CourseProgressService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository studentRepository;

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

//    TODO
    @Override
    public void signUpUser(Long courseId,
                           Long studentId) throws MissingResourceException {
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
        User student = studentRepository.getOne(studentId);
        course.getStudents().add(student);
        paymentService.payForCourse(student,course); //simplification
        courseRepository.save(course);
        studentRepository.save(student);
    }


    @Override
    public void makePassedEducationalStep(Long courseId,
                                          Long studentId,
                                          Long educationalStepId) throws MissingResourceException {
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
        User student = studentRepository.getOne(studentId);
        EducationalStep educationalStep = educationalStepRepository.getOne(educationalStepId);

        StudentProgressItem studentProgressItem = StudentProgressItem.builder()
                .student(student)
                .course(course)
                .passedEducationalStep(educationalStep)
                .passedTestStep(null)
                .stepPosition(educationalStep.getPosition())
                .build();
        studentProgressItemRepository.save(studentProgressItem);
    }

//    TODO
    @Override
    public void makeProcessedTestStep(Long courseId,
                                      Long studentId,
                                      Long testStepId,
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
        User student = studentRepository.getOne(studentId);
        TestStep testStep = testStepRepository.getOne(testStepId);
        TestStepOption chosenTestOption = testStepOptionRepository.getOne(chosenAnswer.getId());
        ActionType actionType = autoCheckService.checkTestTask(testStep,chosenTestOption);
        if(actionType == ActionType.PASSED){
            StudentProgressItem studentProgressItem = StudentProgressItem.builder()
                    .student(student)
                    .course(course)
                    .passedTestStep(testStep)
                    .passedEducationalStep(null)
                    .stepPosition(testStep.getPosition())
                    .build();
            studentProgressItemRepository.save(studentProgressItem);
        }
    }

    @Override
    public ActionType makeProcessedTestStep(Long courseId,
                                            Long studentId,
                                            Long testStepId,
                                            TestStepDTO testStepDTO) {
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
        Course course = courseRepository.getOne(courseId);
        User student = studentRepository.getOne(studentId);
        TestStep testStep = testStepRepository.getOne(testStepId);
        for (int i =0;i<testStepDTO.getAnswers().size();i++){
            if(testStepDTO.getAnswers().get(i).getIsCorrect()==null) {
                testStepDTO.getAnswers().get(i).setIsCorrect(false);
            }
        }
//        TestStepOption chosenTestOption = testStepOptionRepository.getOne(chosenAnswer.getId());
        ActionType actionType = autoCheckService.checkTestTask(testStep,testStepDTO);
        if(actionType == ActionType.PASSED){
            StudentProgressItem studentProgressItem = StudentProgressItem.builder()
                    .student(student)
                    .course(course)
                    .passedTestStep(testStep)
                    .passedEducationalStep(null)
                    .stepPosition(testStep.getPosition())
                    .build();
            studentProgressItemRepository.save(studentProgressItem);
        }
        return actionType;
    }

    @Override
    public Collection<Course> getStudentsCourses(List<User> students) {
        return courseRepository.findCoursesByStudentsIdsIn(students.get(0).getId());
    }

    @Override
    public Step getCurrentStep(Long courseId, Long studentId){
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
//        User student = studentRepository.getOne(studentId);
        Integer lastStepPosition = studentProgressItemRepository.findLastStepPosition(courseId,studentId);
        Integer nextStepPosition = null;
        Step currentStep = null;
        if(lastStepPosition==null){
            nextStepPosition = 1;
        }else{
            nextStepPosition=lastStepPosition+=1;
        }
        Optional<EducationalStep> educationalStepOptional =
                educationalStepRepository.findByCourseAndPosition(course,nextStepPosition);
        if(educationalStepOptional.isPresent()){
            currentStep=educationalStepOptional.get();
        } else {
            Optional<TestStep> testStepOptional =
                    testStepRepository.findByCourseAndPosition(course,nextStepPosition);
            if(testStepOptional.isPresent()){
                currentStep=testStepOptional.get();
            }
        }
        return currentStep;
    }

    @Override
    public void congratulateStudent(User student,
                                    Course course) {
        String congratulation = "Congratulations," + student.getFirstName() + " " + student.getLastName() +
                ",! You've done well. You've already finished course " + course.getTitle();
        System.out.println(congratulation);
    }

    @Override
    public void deleteStudentFromCourse(Long courseId, Long studentId) throws NotFoundException {

        Course course = courseRepository.getOne(courseId);
        if(course==null){
            throw new NotFoundException("Course not found");
        }
        User student = studentRepository.getOne(studentId);
        if(student==null){
            throw new NotFoundException("Student not found");
        }
        course.getStudents().remove(student);
        studentProgressItemRepository.deleteByCourseAndStudent(courseId,studentId);
        courseRepository.saveAndFlush(course);

    }
}
