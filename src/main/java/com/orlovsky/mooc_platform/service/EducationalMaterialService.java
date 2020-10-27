package com.orlovsky.mooc_platform.service;


import com.orlovsky.mooc_platform.dto.*;
import com.orlovsky.mooc_platform.model.*;

import java.util.List;
import java.util.MissingResourceException;
import java.util.UUID;

public interface EducationalMaterialService {
    // CRUD
    // Create
    Course createEmptyCourse(CourseDTO courseDTO);

    // Read
    Course getCourseById(UUID courseId);

    List<Course> getAllCourses();

    TestStep getTestStepById(UUID testStepId);

    EducationalStep getEducationalStepById(UUID educationalStepId) throws MissingResourceException;

    // Update
    void updateCourseInfo(UUID courseId,
                          CourseDTO courseDTO);

    void addAuthor(UUID courseId, UUID authorId);

    EducationalStep addEducationalStep(UUID courseId,
                            EducationalStepDTO educationalStepDTO);

    TestStep addTestStep(UUID courseId,
                     TestStepDTO testStepDTO);

    TestStepOption addTestStepOption(UUID courseId, UUID testStepId, TestStepOptionRequestDTO body);

    void setCourseStatus(UUID courseId, CourseStatus courseStatus);

    void activateCourse(UUID courseId);

    void deactivateCourse(UUID courseId);

    // Delete
    void deleteEducationalStep(UUID courseId,
                               UUID educationalStepId);

    void deleteTestStep(UUID courseId,
                        UUID testStepId);

    void deleteTestAnswer(UUID testStepId, UUID testAnswerId);

    void deleteAuthorById(UUID courseId, UUID authorId);

    void deleteCourse(UUID courseId);
}
