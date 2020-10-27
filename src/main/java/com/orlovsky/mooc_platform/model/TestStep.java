package com.orlovsky.mooc_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_steps")
public class TestStep implements Step {
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private URI descriptionUri;

    @OneToMany(mappedBy = "testStep",cascade = CascadeType.ALL)
    private Collection<TestStepOption> answers;

    @Column
    private int score;

    @Column
    private int position;

    @OneToMany(mappedBy = "passedTestStep",cascade = CascadeType.ALL)
    private List<StudentProgressItem> studentProgressItems;
}
