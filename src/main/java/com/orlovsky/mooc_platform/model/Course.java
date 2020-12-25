package com.orlovsky.mooc_platform.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

//    @ManyToMany(cascade = CascadeType.PERSIST)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = User.class)
    @JoinTable(
            name="Course_Author",
            joinColumns = {@JoinColumn(name="сourse_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="author_id",referencedColumnName="id")},
            uniqueConstraints = {@UniqueConstraint(name = "course_author_uq",
                    columnNames = {"сourse_id", "author_id"})})
    Set<User> authors = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.PERSIST)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = User.class)
    @JoinTable(
            name="Course_Student",
            joinColumns = {@JoinColumn(name="сourse_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="student_id",referencedColumnName="id")},
            uniqueConstraints = {@UniqueConstraint(name = "course_student_uq",
                    columnNames = {"сourse_id", "student_id"})})
Set<User> students = new HashSet<>();

    @Column(name = "duration")
    private int duration;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EducationalStep> educationalSteps = new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,   fetch = FetchType.LAZY)
    private List<TestStep> testSteps = new ArrayList<>();

    @Column
    private int numberOfSteps;

    @Column(name = "price")
    private long price;

    protected boolean canEqual(final Object other) {
        return other instanceof Course;
    }
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + this.getDuration();
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $educationalSteps = this.getEducationalSteps();
        result = result * PRIME + ($educationalSteps == null ? 43 : $educationalSteps.hashCode());
        final Object $testSteps = this.getTestSteps();
        result = result * PRIME + ($testSteps == null ? 43 : $testSteps.hashCode());
        result = result * PRIME + this.getNumberOfSteps();
        final long $price = this.getPrice();
        result = result * PRIME + (int) ($price >>> 32 ^ $price);
        return result;
    }

    public String toString() {
        return "Course(id=" + this.getId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", duration=" + this.getDuration() + ", status=" + this.getStatus() + ", educationalSteps=" + this.getEducationalSteps() + ", testSteps=" + this.getTestSteps() + ", numberOfSteps=" + this.getNumberOfSteps() + ", price=" + this.getPrice() + ")";
    }


}
