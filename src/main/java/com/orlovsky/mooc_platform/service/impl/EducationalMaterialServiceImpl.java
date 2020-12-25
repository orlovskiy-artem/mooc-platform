package com.orlovsky.mooc_platform.service.impl;

import com.orlovsky.mooc_platform.dto.CourseDTO;
import com.orlovsky.mooc_platform.dto.EducationalStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
import com.orlovsky.mooc_platform.mapper.CourseMapper;
import com.orlovsky.mooc_platform.mapper.EducationalStepMapper;
import com.orlovsky.mooc_platform.mapper.TestStepMapper;
import com.orlovsky.mooc_platform.mapper.TestStepOptionRequestMapper;
import com.orlovsky.mooc_platform.model.*;
import com.orlovsky.mooc_platform.repository.*;
import com.orlovsky.mooc_platform.service.EducationalMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.MissingResourceException;

@Service
public class EducationalMaterialServiceImpl implements EducationalMaterialService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EducationalStepRepository educationalStepRepository;
    @Autowired
    private TestStepRepository testStepRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestStepOptionRepository testAnswerRepository;


    // CRUD
    // Create
    @Override
    public Course createEmptyCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.INSTANCE.toEntity(courseDTO);
        course.setStatus(CourseStatus.IN_DEVELOPMENT);
        return courseRepository.save(course);
    }

    // Read
    @Override
    public Course getCourseById(Long courseId) throws MissingResourceException{
        return courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new MissingResourceException("Course not found",
                                "Course",courseId.toString()));
    }

    @Override
    public TestStep getTestStepById(Long testStepId) throws MissingResourceException {
        return testStepRepository.findById(testStepId)
                .orElseThrow(()->
                    new MissingResourceException("Test step not found",
                            "TestStep",
                            testStepId.toString()));
    }

    @Override
    public EducationalStep getEducationalStepById(Long educationalStepId) throws MissingResourceException {
        return educationalStepRepository.findById(educationalStepId)
                .orElseThrow(() ->
                        new MissingResourceException("Educational step not found",
                                "EducationalStep",
                                educationalStepId.toString()));
    }

    @Override
    public Collection<Course> getAuthorCourses(List<User> authors) {
        return courseRepository.findCourseByAuthor(authors.get(0).getId());
    }


    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    // Update
    @Override
    public void updateCourseInfo(Long courseId,CourseDTO courseDTO) throws MissingResourceException{
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        course.setTitle(courseDTO.getTitle());
        course.setPrice(courseDTO.getPrice());
        course.setDuration(courseDTO.getDuration());
        course.setDescription(courseDTO.getDescription());
        if(courseDTO.getStatus()!=null){
            course.setStatus(courseDTO.getStatus());
        }
        courseRepository.save(course);
    }

    @Override
    public void activateCourse(Long courseId) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        course.setStatus(CourseStatus.ACTIVE);
        courseRepository.save(course);
    }

    @Override
    public void deactivateCourse(Long courseId) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        course.setStatus(CourseStatus.DEPRECATED);
        courseRepository.save(course);
    }

    @Override
    public void setCourseStatus(Long courseId, CourseStatus courseStatus) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        course.setStatus(courseStatus);
    }

    @Override
    public EducationalStep addEducationalStep(Long courseId, EducationalStepDTO educationalStepDTO) {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        int numberOfSteps = course.getNumberOfSteps();
        EducationalStep educationalStep = EducationalStepMapper.INSTANCE.toEntity(educationalStepDTO);
        educationalStep.setPosition(numberOfSteps+1);
        educationalStep.setCourse(course);
        EducationalStep educationalStepSaved = educationalStepRepository.save(educationalStep);
        course.setNumberOfSteps(course.getNumberOfSteps()+1);
        course.getEducationalSteps().add(educationalStep);
        courseRepository.save(course);
        return educationalStepSaved;
    }

    @Override
    public TestStep addTestStep(Long courseId,
                            TestStepDTO testStepDTO) {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        int numberOfSteps = course.getNumberOfSteps();
        TestStep testStep = TestStepMapper.INSTANCE.toEntity(testStepDTO);
        testStep.setPosition(numberOfSteps+1);
        testStep.setCourse(course);
        TestStep testStepSaved = testStepRepository.save(testStep);
        course.setNumberOfSteps(course.getNumberOfSteps()+1);
        course.getTestSteps().add(testStepSaved);
        courseRepository.save(course);
        return testStepSaved;
    }
    @Override
    public TestStepOption addTestStepOption(Long courseId,
                                            Long testStepId,
                                            TestStepOptionRequestDTO testStepOptionRequestDTO) throws MissingResourceException{
        if(!testStepRepository.existsById(testStepId)){
            throw new MissingResourceException("Test step not found",
                    "Test step",
                    testStepId.toString());
        }
        if(testStepOptionRequestDTO.getIsCorrect()==null){
            testStepOptionRequestDTO.setCorrect(false);
        }
        TestStep testStep = testStepRepository.getOne(testStepId);
        TestStepOption testStepOption = TestStepOptionRequestMapper.INSTANCE.toEntity(testStepOptionRequestDTO);
        testStepOption.setTestStep(testStep);
        testStep.getAnswers().add(testStepOption);
        TestStepOption testStepOptionSaved = testAnswerRepository.save(testStepOption);
        testStepRepository.save(testStep);
        return testStepOptionSaved;
    }
//    TODO
    @Override
    public void addAuthor(Long courseId, Long authorId) {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!userRepository.existsById(authorId)){
            throw new MissingResourceException("Author not found",
                    "Author",
                    authorId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        User author = userRepository.getOne(authorId);
        course.getAuthors().add(author);
        courseRepository.save(course);
    }

    // Delete
    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void deleteTestStep(Long courseId,
                               Long testStepId) throws MissingResourceException{
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!testStepRepository.existsById(testStepId)){
            return;
        }
        Course course = courseRepository.getOne(courseId);
        TestStep testStep = testStepRepository.getOne(testStepId);
        course.getTestSteps().remove(testStep);
        course.setNumberOfSteps(course.getNumberOfSteps()-1);
        courseRepository.save(course);
        testStepRepository.deleteById(testStepId);
    }

    @Override
    public void deleteTestAnswer(Long testStepId, Long testAnswerId)
            throws MissingResourceException {
        if(!testStepRepository.existsById(testStepId)){
            throw new MissingResourceException("Test step not found",
                    "TestStep",
                    testStepId.toString());
        }
        if(!testAnswerRepository.existsById(testAnswerId)){
            throw new MissingResourceException("Test answer not found",
                    "TestAnswer",
                    testAnswerId.toString());
        }
        TestStep testStep = testStepRepository.getOne(testStepId);
        TestStepOption testAnswer = testAnswerRepository.getOne(testAnswerId);
        testStep.getAnswers().remove(testAnswer);
        testAnswerRepository.delete(testAnswer);
    }
//    TODO
    @Override
    public void deleteAuthorById(Long courseId, Long authorId) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!userRepository.existsById(authorId)){
            throw new MissingResourceException("Author not found",
                    "Author",
                    authorId.toString());
        }
        Course course = courseRepository.getOne(courseId);
        User author = userRepository.getOne(authorId);
        course.getAuthors().remove(author);
        userRepository.deleteById(authorId);
        courseRepository.save(course);
    }

    @Override
    public void deleteEducationalStep(Long courseId,
                                      Long educationalStepId) throws MissingResourceException {
        if(!courseRepository.existsById(courseId)){
            throw new MissingResourceException("Course not found",
                    "Course",
                    courseId.toString());
        }
        if(!educationalStepRepository.existsById(educationalStepId)){
            return;
        }
        Course course = courseRepository.getOne(courseId);
        EducationalStep educationalStep = educationalStepRepository.getOne(educationalStepId);
        course.getTestSteps().remove(educationalStep);
        course.setNumberOfSteps(course.getNumberOfSteps()-1);
        courseRepository.save(course);
        educationalStepRepository.deleteById(educationalStepId);
    }

    @Override
    public List<Course> searchCoursesByKeyword(String keyword) {
        if (keyword != null) {
            return courseRepository.search(keyword);
        }
        return courseRepository.findAll();
    }
}
