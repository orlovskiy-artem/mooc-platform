package com.orlovsky.mooc_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_steps")
public class TestStep implements Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private String description;

    @OneToMany(mappedBy = "testStep",cascade = CascadeType.ALL)
    private List<TestStepOption> answers;

    @Column
    private int score;

    @Column
    private int position;

    @OneToMany(mappedBy = "passedTestStep",cascade = CascadeType.ALL)
    private List<StudentProgressItem> studentProgressItems;

    protected boolean canEqual(final Object other) {
        return other instanceof TestStep;
    }
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $answers = this.getAnswers();
        result = result * PRIME + ($answers == null ? 43 : $answers.hashCode());
        result = result * PRIME + this.getScore();
        result = result * PRIME + this.getPosition();
        return result;
    }

    public String toString() {
        return "TestStep(id=" + this.getId() + ", courseId=" + this.getCourse().getId() +
                ", description=" + this.getDescription() +
                ", score=" + this.getScore() + ", position=" + this.getPosition() +  ")";
    }


}
