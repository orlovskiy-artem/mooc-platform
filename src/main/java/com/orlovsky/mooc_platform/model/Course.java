package com.orlovsky.mooc_platform.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="Course_Author",
            joinColumns = {@JoinColumn(name="сourse_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="author_id",referencedColumnName="id")})
    List<Author> authors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="Course_Student",
            joinColumns = {@JoinColumn(name="сourse_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="student_id",referencedColumnName="id")})
    List<Student> students = new ArrayList<>();

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
}
