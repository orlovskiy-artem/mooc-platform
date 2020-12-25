package com.orlovsky.mooc_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_answers")
public class TestStepOption {
    @Id
    @Column(name = "id",updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String optionText;

    @Column
    private Boolean isCorrect;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "test_step_id")
    private TestStep testStep;
}