package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.dto.TestStepOptionDTO;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.Student;

import java.util.UUID;

public interface CourseProgressService {
    void signUpUser(UUID courseId,
                    UUID studentId);

    void makePassedEducationalStep(UUID courseId,
                                   UUID studentId,
                                   UUID educationalStepId);

    void makeProcessedTestStep(UUID courseId,
                               UUID studentId,
                               UUID testStepId,
                               TestStepOptionDTO chosenAnswer);


    void congratulateStudent(Student student,
                             Course course);
}
