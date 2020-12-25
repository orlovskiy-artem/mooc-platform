package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.User;

public interface PaymentService {
    void payForCourse(User student,
                      Course course);

    // it is simplification of real process
    boolean checkIfCoursePaid();
}

