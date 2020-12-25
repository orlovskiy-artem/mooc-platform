package com.orlovsky.mooc_platform.dto;

import com.orlovsky.mooc_platform.model.CourseStatus;
import com.orlovsky.mooc_platform.model.EducationalStep;
import com.orlovsky.mooc_platform.model.TestStep;
import com.orlovsky.mooc_platform.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private List<User> authors;
    private int duration;
    private CourseStatus status;
    private long price;
    private List<EducationalStep> educationalSteps;
    private List<TestStep> testSteps;
    private int numberOfSteps;
}
