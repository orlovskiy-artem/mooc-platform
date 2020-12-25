package com.orlovsky.mooc_platform.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_progress_items")
public class StudentProgressItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "passed_educational_step")
    private EducationalStep passedEducationalStep;

    @ManyToOne
    @JoinColumn(name = "passed_test_step")
    private TestStep passedTestStep;

//    @ManyToOne
//    @JoinColumn(name = "chosen_option_id")
//    private TestStepOption chosenOption;

    @JoinColumn(name = "step_position")
    private Integer stepPosition;

    protected boolean canEqual(final Object other) {
        return other instanceof StudentProgressItem;
    }
    public String toString() {
        return "StudentProgressItem(id=" + this.getId() + ", student=" + this.getStudent().getId() +
                ", course=" + this.getCourse().getId() +
                ", passedEducationalStep=" + this.getPassedEducationalStep() +
                ", passedTestStep=" + this.getPassedTestStep() +
                ", stepPosition=" + this.getStepPosition() + ")";
    }


}