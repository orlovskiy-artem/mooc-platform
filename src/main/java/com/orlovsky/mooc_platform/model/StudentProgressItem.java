package com.orlovsky.mooc_platform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_progress_item")
public class StudentProgressItem {
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "passed_educational_step")
    private EducationalStep passedEducationalStep;

    @ManyToOne
    @JoinColumn(name = "passed_test_step")
    private TestStep passedTestStep;

    @ManyToOne
    @JoinColumn(name = "chosen_option_id")
    private TestStepOption chosenOption;
}