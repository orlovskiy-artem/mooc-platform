package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.dto.TestStepDTO;
import com.orlovsky.mooc_platform.dto.TestStepOptionDTO;
import com.orlovsky.mooc_platform.model.ActionType;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.Step;
import com.orlovsky.mooc_platform.model.User;
import javassist.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface CourseProgressService {
    void signUpUser(Long courseId,
                    Long studentId);

    void makePassedEducationalStep(Long courseId,
                                   Long studentId,
                                   Long educationalStepId);

    void makeProcessedTestStep(Long courseId,
                               Long studentId,
                               Long testStepId,
                               TestStepOptionDTO chosenAnswer);


    ActionType makeProcessedTestStep(Long courseId,
                                     Long studentId,
                                     Long testStepId,
                                     TestStepDTO testStepDTO);

    Collection<Course> getStudentsCourses(List<User> students);

    Step getCurrentStep(Long courseId, Long studentId);

    void congratulateStudent(User student,
                             Course course);

    void deleteStudentFromCourse(Long courseId, Long studentId) throws NotFoundException;
}
