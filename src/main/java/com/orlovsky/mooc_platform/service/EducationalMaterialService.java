package com.orlovsky.mooc_platform.service;


import com.orlovsky.mooc_platform.dto.CourseDTO;
import com.orlovsky.mooc_platform.dto.EducationalStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionRequestDTO;
import com.orlovsky.mooc_platform.model.*;

import java.util.Collection;
import java.util.List;
import java.util.MissingResourceException;

public interface EducationalMaterialService {
    // CRUD
    // Create
    Course createEmptyCourse(CourseDTO courseDTO);

    // Read
    Course getCourseById(Long courseId);

    List<Course> getAllCourses();

    TestStep getTestStepById(Long testStepId);

    EducationalStep getEducationalStepById(Long educationalStepId) throws MissingResourceException;

    Collection<Course> getAuthorCourses(List<User> authors);

    // Update
    void updateCourseInfo(Long courseId,
                          CourseDTO courseDTO);

    void addAuthor(Long courseId, Long authorId);

    EducationalStep addEducationalStep(Long courseId,
                            EducationalStepDTO educationalStepDTO);

    TestStep addTestStep(Long courseId,
                     TestStepDTO testStepDTO);

    TestStepOption addTestStepOption(Long courseId, Long testStepId, TestStepOptionRequestDTO body);

    void setCourseStatus(Long courseId, CourseStatus courseStatus);

    void activateCourse(Long courseId);

    void deactivateCourse(Long courseId);

    // Delete
    void deleteEducationalStep(Long courseId,
                               Long educationalStepId);

    void deleteTestStep(Long courseId,
                        Long testStepId);

    void deleteTestAnswer(Long testStepId, Long testAnswerId);

    void deleteAuthorById(Long courseId, Long authorId);

    void deleteCourse(Long courseId);

    List<Course> searchCoursesByKeyword(String keyword);
}
